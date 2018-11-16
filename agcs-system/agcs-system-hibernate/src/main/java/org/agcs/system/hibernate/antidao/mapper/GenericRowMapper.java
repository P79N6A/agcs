package org.agcs.system.hibernate.antidao.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import org.agcs.system.hibernate.antidao.util.BigDecimalConverter;
import org.agcs.system.hibernate.antidao.util.CamelCaseUtils;
import org.agcs.system.hibernate.antidao.util.DateConverter;
import org.agcs.system.hibernate.antidao.util.IntegerConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class GenericRowMapper<T> implements RowMapper<T>{
	
	private Class<T> clazz;
	
	public GenericRowMapper(Class<T> claz)
	  {
	    this.clazz = claz;
	  }

	public T mapRow(ResultSet resultset, int rowNum) throws SQLException {
		try {
	      ResultSetMetaData rsmd = resultset.getMetaData();
	      int columnCount = rsmd.getColumnCount();
	      Object bean = this.clazz.newInstance();
	      ConvertUtils.register(new DateConverter(), Date.class);
	      ConvertUtils.register(new BigDecimalConverter(), BigDecimal.class);
	      ConvertUtils.register(new IntegerConverter(), Integer.class);
	      for (int i = 1; i <= columnCount; i++) {
	        String key = JdbcUtils.lookupColumnName(rsmd, i);
	        Object obj = JdbcUtils.getResultSetValue(resultset, i);
	        String camelKey = CamelCaseUtils.toCamelCase(key);
	        BeanUtils.setProperty(bean, camelKey, obj);
	      }
	      return (T) bean;
	    }catch (Exception e){
	      throw new SQLException("mapRow error.", e);
	    }
	}
	
}
