package org.agcs.system.hibernate.dao.impl;

import java.text.MessageFormat;

import org.agcs.system.hibernate.util.DBTypeUtil;

public class JdbcDao {
	
	/**
	 * 数据库类型
	 */
	public static final String DATABSE_TYPE_MYSQL ="mysql";
	public static final String DATABSE_TYPE_POSTGRE ="postgresql";
	public static final String DATABSE_TYPE_ORACLE ="oracle";
	public static final String DATABSE_TYPE_SQLSERVER ="sqlserver";
	/**
	 * 分页SQL
	 */
	public static final String MYSQL_SQL = "{0} limit {1},{2}";         //mysql
	public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}"; //oracle
	public static final String SQLSERVER_SQL = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}"; //sqlserver
	
	
	/**
	 * 根据数据库类型动态拼接分页sql
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public static String createPageSql(String sql, int page, int rows){
		int beginNum = (page - 1)*rows;
		String[] param = new String[3];
		param[0] = sql;
		param[1] = beginNum+"";
		param[2] = rows+"";
		if(DBTypeUtil.getDBType().toLowerCase().indexOf(DATABSE_TYPE_MYSQL) != -1){
			sql = MessageFormat.format(MYSQL_SQL, param);
		}else if(DBTypeUtil.getDBType().toLowerCase().indexOf(DATABSE_TYPE_ORACLE) != -1){
			int endRows = beginNum+rows;
			param[1] = endRows+"";
			param[2] = beginNum+"";
			sql = MessageFormat.format(ORACLE_SQL, param);
		}else if(DBTypeUtil.getDBType().toLowerCase().indexOf(DATABSE_TYPE_SQLSERVER) != -1){
			int endRows = beginNum+rows;
			param[0] = sql.substring(getAfterSelectInsertPoint(sql));
			param[1] = endRows+"";
			param[2] = beginNum+"";
			sql = MessageFormat.format(SQLSERVER_SQL, param);
		}
		return sql;
	}
	
	private static int getAfterSelectInsertPoint(String sql) {
	    int selectIndex = sql.toLowerCase().indexOf("select");
	    int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
	    return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
    }

}
