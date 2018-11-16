package org.agcs.system.hibernate.antidao.aop;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ognl.Ognl;
import ognl.OgnlException;

import org.agcs.system.hibernate.antidao.annotation.Arguments;
import org.agcs.system.hibernate.antidao.annotation.ResultType;
import org.agcs.system.hibernate.antidao.annotation.Sql;
import org.agcs.system.hibernate.antidao.dao.IAntiBaseDao;
import org.agcs.system.hibernate.antidao.mapper.GenericRowMapper;
import org.agcs.system.hibernate.antidao.mapper.MiniColumnMapRowMapper;
import org.agcs.system.hibernate.antidao.mapper.MiniColumnOriginalMapRowMapper;
import org.agcs.system.hibernate.antidao.util.AntiDaoSysConst;
import org.agcs.system.hibernate.antidao.util.AntiDaoUtil;
import org.agcs.system.hibernate.antidao.util.FreemarkerFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class AutiDaoHandler implements MethodInterceptor{
	
	private IAntiBaseDao antiBaseDao;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private String keyType = "lower";
	private boolean formatSql = false;
	private boolean showSql = false;
	private String UPPER_KEY = "upper";
	private String LOWER_KEY = "lower";
	private BasicFormatterImpl formatter = new BasicFormatterImpl();

	private static final Logger log = Logger.getLogger(AutiDaoHandler.class);
	
	@SuppressWarnings("rawtypes")
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Method method = methodInvocation.getMethod();
		Object[] args = methodInvocation.getArguments();
		Object returnObj = null;
		if(!AntiDaoUtil.isAbstract(method)){
			return methodInvocation.proceed();
		}
		Map rs = new HashMap();
		if(antiDaoHiber(rs, method, args)){
			return rs.get("returnObj");
		}
		String templateSql = null;
		Map sqlParamsMap = new HashMap();
		templateSql = installTemplateSql(method, sqlParamsMap, args);
		String executeSql = parseSqlTemplate(method, templateSql, sqlParamsMap);
		Map sqlMap = installPlaceholderSqlParam(executeSql, sqlParamsMap);
		returnObj = getReturnAntidaoResult(this.jdbcTemplate, method, executeSql, sqlMap);
	    if (this.showSql) {
	      log.info("MiniDao-SQL:\n" + (this.formatSql ? this.formatter.format(executeSql) : executeSql) + "\n");
	    }
		return returnObj;
	}
	
	private boolean antiDaoHiber(Map rs, Method method, Object[] args) {
		if(AntiDaoSysConst.METHOD_SAVE_BY_HIBER.equals(method.getName())){
			this.antiBaseDao.save(args[0]);
			return true;
		}
		if(AntiDaoSysConst.METHOD_GET_BY_ID_HIBER.equals(method.getName())){
			Class clz = (Class)args[0];
			rs.put("returnObj", this.antiBaseDao.get(clz, args[1].toString()));
			return true;
		}
		if(AntiDaoSysConst.METHOD_GET_BY_ENTITY_HIBER.equals(method.getName())){
			rs.put("returnObj", this.antiBaseDao.get(args[0]));
			return true;
		}
		if(AntiDaoSysConst.METHOD_UPDATE_BY_HIBER.equals(method.getName())){
			this.antiBaseDao.saveOrUpdate(args[0]);
			return true;
		}
		if(AntiDaoSysConst.METHOD_DELETE_BY_HIBER.equals(method.getName())){
			this.antiBaseDao.delete(args[0]);
			return true;
		}
		if(AntiDaoSysConst.METHOD_DELETE_BY_ID_HIBER.equals(method.getName())){
			Class clz = (Class)args[0];
		    this.antiBaseDao.deleteEntityById(clz, args[1].toString());
			return true;
		}
		if(AntiDaoSysConst.METHOD_LIST_BY_HIBER.equals(method.getName())){
			rs.put("returnObj", this.antiBaseDao.loadAll(args[0]));
			return true;
		}
		return false;
	}
	
	private String installTemplateSql(Method method, Map sqlParamsMap,
			Object[] args) throws Exception{
		String templateSql = null;
		boolean argumentsFlag = method.isAnnotationPresent(Arguments.class);
		if(argumentsFlag){
			Arguments arguments = method.getAnnotation(Arguments.class);
			if(arguments.value().length > args.length){
				throw new Exception("[注释标签]参数数目，不能大于[方法参数]参数数目");
			}
			int argsNum = 0;
			for(String v : arguments.value()){
				sqlParamsMap.put(v, args[argsNum]);
				argsNum++;
			}
		}else{
			if(args.length > 1){
				throw new Exception("方法参数数目>=2，方法必须使用注释标签@Arguments");
			}
			if(args.length == 1){
				sqlParamsMap.put(AntiDaoSysConst.SQL_FTL_DTO, args[0]);
			}
		}
		if(method.isAnnotationPresent(Sql.class)){
			Sql sql = method.getAnnotation(Sql.class);
			if(StringUtils.isNotEmpty(sql.value())){
				templateSql = sql.value();
			}
		}
		return templateSql;
	}
	
	private String parseSqlTemplate(Method method, String templateSql,
			Map sqlParamsMap) {
		String executeSql = null;
		if(StringUtils.isNotEmpty(templateSql)){
			executeSql = new FreemarkerFactory().parseTemplateContent(templateSql, sqlParamsMap);
		}else{
			String sqlTempletPath = "/" + method.getDeclaringClass().getName().replace(".", "/").replace("/dao/", "/sql/") + "_" + method.getName() + ".sql";
			executeSql = new FreemarkerFactory().parseTemplate(sqlTempletPath, sqlParamsMap);
		}
		return getSqlText(executeSql);
	}
	
	private Map installPlaceholderSqlParam(String executeSql, Map sqlParamsMap) throws OgnlException {
		Map map = new HashMap();
		//此正则不支持属性带下划线“_”，待改进
		String regEx = ":[ tnx0Bfr]*[0-9a-z.A-Z]+";
		Pattern pat = Pattern.compile(regEx);
		Matcher m = pat.matcher(executeSql);
		while (m.find()) {
			String ognl_key = m.group().replace(":", "").trim();
			//ognl_key = "glptUser.user_code";
			map.put(ognl_key, Ognl.getValue(ognl_key, sqlParamsMap));
		}
		return map;
	}
	
	private Object getReturnAntidaoResult(JdbcTemplate jdbcTemplate, Method method, String executeSql, Map<String, Object> paramMap){
	    String methodName = method.getName();

	    if (checkActiveKey(methodName)) {
	      if (paramMap != null) {
	        return Integer.valueOf(this.namedParameterJdbcTemplate.update(executeSql, paramMap));
	      }
	      return Integer.valueOf(jdbcTemplate.update(executeSql));
	    }
	    if (checkBatchKey(methodName)) {
	      return batchUpdate(jdbcTemplate, executeSql);
	    }

	    Class returnType = method.getReturnType();
	    if (returnType.isPrimitive()) {
	      Number number = (Number)jdbcTemplate.queryForObject(executeSql, BigDecimal.class);
	      if ("int".equals(returnType))
	        return Integer.valueOf(number.intValue());
	      if ("long".equals(returnType))
	        return Long.valueOf(number.longValue());
	      if ("double".equals(returnType))
	        return Double.valueOf(number.doubleValue());
	    } else {
	      if (returnType.isAssignableFrom(List.class)){
	        ResultType resultType = (ResultType)method.getAnnotation(ResultType.class);
	        String[] values = (String[])null;
	        if (resultType != null) {
	          values = resultType.value();
	        }
	        if ((values == null) || (values.length == 0) || ("java.util.Map".equals(values[0]))) {
	          if (paramMap != null) {
	            return this.namedParameterJdbcTemplate.query(executeSql, paramMap, getColumnMapRowMapper());
	          }
	          return jdbcTemplate.query(executeSql, getColumnMapRowMapper());
	        }

	        Class clazz = null;
	        try {
	          clazz = Class.forName(values[0]);
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	        if (paramMap != null) {
	          return this.namedParameterJdbcTemplate.query(executeSql, paramMap, new GenericRowMapper(clazz));
	        }
	        return jdbcTemplate.query(executeSql, new GenericRowMapper(clazz));
	      }

	      if (returnType.isAssignableFrom(Map.class)){
	        if (paramMap != null) {
	          return (Map)this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, getColumnMapRowMapper());
	        }
	        return (Map)jdbcTemplate.queryForObject(executeSql, getColumnMapRowMapper());
	      }
	      if (returnType.isAssignableFrom(String.class)){
	        try
	        {
	          if (paramMap != null) {
	            return this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, String.class);
	          }
	          return jdbcTemplate.queryForObject(executeSql, String.class);
	        }
	        catch (EmptyResultDataAccessException e) {
	          return null;
	        }
	      }
	      if (AntiDaoUtil.isWrapClass(returnType)) {
	        try
	        {
	          if (paramMap != null) {
	            return this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, returnType);
	          }
	          return jdbcTemplate.queryForObject(executeSql, returnType);
	        }
	        catch (EmptyResultDataAccessException e) {
	          return null;
	        }
	      }

	      RowMapper rm = BeanPropertyRowMapper.newInstance(returnType);
	      try {
	        if (paramMap != null) {
	          return this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, rm);
	        }
	        return jdbcTemplate.queryForObject(executeSql, rm);
	      }
	      catch (EmptyResultDataAccessException e) {
	        return null;
	      }
	    }

	    return null;
	}
	
	private String getSqlText(String sql){
	    return sql.replaceAll("\\n", " ").replaceAll("\\t", " ")
	      .replaceAll("\\s{1,}", " ").trim();
	}
	
	private static boolean checkActiveKey(String methodName){
	    String[] keys = AntiDaoSysConst.INF_METHOD_ACTIVE.split(",");
	    for (String s : keys) {
	      if (methodName.startsWith(s))
	        return true;
	    }
	    return false;
	}
	
	private static boolean checkBatchKey(String methodName){
	    String[] keys = AntiDaoSysConst.INF_METHOD_BATCH.split(",");
	    for (String s : keys) {
	      if (methodName.startsWith(s))
	        return true;
	    }
	    return false;
	}
	
	private int[] batchUpdate(JdbcTemplate jdbcTemplate, String executeSql){
	    String[] sqls = executeSql.split(";");
	    if (sqls.length < 100) {
	      return jdbcTemplate.batchUpdate(sqls);
	    }
	    int[] result = new int[sqls.length];
	    List sqlList = new ArrayList();
	    for (int i = 0; i < sqls.length; i++) {
	      sqlList.add(sqls[i]);
	      if (i % 100 == 0) {
	        addResulArray(result, i + 1, jdbcTemplate.batchUpdate((String[])sqlList.toArray(new String[0])));
	        sqlList.clear();
	      }
	    }
	    addResulArray(result, sqls.length, jdbcTemplate.batchUpdate((String[])sqlList.toArray(new String[0])));
	    return result;
	}
	
	private void addResulArray(int[] result, int index, int[] arr){
	    int length = arr.length;
	    for (int i = 0; i < length; i++)
	      result[(index - length + i)] = arr[i];
	}
	
	private RowMapper<Map<String, Object>> getColumnMapRowMapper(){
	    if (getKeyType().equalsIgnoreCase(this.LOWER_KEY)){
	      return new MiniColumnMapRowMapper();
	    }
	    if (getKeyType().equalsIgnoreCase(this.UPPER_KEY)) {
	      return new ColumnMapRowMapper();
	    }
	    return new MiniColumnOriginalMapRowMapper();
	}

	public IAntiBaseDao getAntiBaseDao() {
		return antiBaseDao;
	}

	public void setAntiBaseDao(IAntiBaseDao antiBaseDao) {
		this.antiBaseDao = antiBaseDao;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public boolean isFormatSql() {
		return formatSql;
	}

	public void setFormatSql(boolean formatSql) {
		this.formatSql = formatSql;
	}

	public boolean isShowSql() {
		return showSql;
	}

	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	
}
