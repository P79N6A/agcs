package org.agcs.system.hibernate.antidao.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class MiniColumnOriginalMapRowMapper implements RowMapper<Map<String, Object>>{

	public Map<String, Object> mapRow(ResultSet resultset, int rowNum)
			throws SQLException {
		ResultSetMetaData rsmd = resultset.getMetaData();
	    int columnCount = rsmd.getColumnCount();
	    Map mapOfColValues = createColumnMap(columnCount);
	    for (int i = 1; i <= columnCount; i++) {
	      String key = JdbcUtils.lookupColumnName(rsmd, i);
	      Object obj = JdbcUtils.getResultSetValue(resultset, i);
	      mapOfColValues.put(key, obj);
	    }
	    return mapOfColValues;
	}
	
	protected Map<String, Object> createColumnMap(int columnCount) {
	    return new LinkedMap(columnCount);
	}

}
