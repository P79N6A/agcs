package org.agcs.system.codegenerate.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.agcs.system.codegenerate.pojo.Column;
import org.agcs.system.codegenerate.pojo.TableConvert;
import org.agcs.system.codegenerate.util.ResourceUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
* @Title: DatabaseTools.java 
* @Package org.braveframwork.codegenerate.database 
* @Description:  数据库工具类
* @author vivian
* @date 2016-1-21 下午5:44:01 
* @version V1.0
 */
public class DatabaseTools {
	
	private static final Logger log = Logger.getLogger(DatabaseTools.class);
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	/**
	 * 验证表是否存在
	 * @param tableName
	 * @return
	 */
	public boolean checkTableExist(String tableName) throws Exception{
		try {
			Class.forName(ResourceUtil.DRIVER_NAME);
			conn = DriverManager.getConnection(ResourceUtil.URL, ResourceUtil.USERNAME, ResourceUtil.PASSWORD);
			//1005标识结果集可滚动，对数据更新敏感，1007表示数据只读
			stmt = conn.createStatement(1005, 1007);
			if(ResourceUtil.DATABASE_TYPE.equals("mysql")){
				sql = (new StringBuffer("select column_name,data_type,column_comment,0,0 from information_schema.columns where table_name = '")).append(tableName).append("'").append(" and table_schema = '").append(ResourceUtil.DATABASE_NAME).append("'").toString();
			}else if(ResourceUtil.DATABASE_TYPE.equals("oracle")){
				sql = (new StringBuilder("select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = '")).append(tableName.toUpperCase()).append("'").toString();
			}else if(ResourceUtil.DATABASE_TYPE.equals("sqlserver")){
				sql = MessageFormat.format("select cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join sys.objects c on a.object_id=c.object_id and c.type='''U''' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0}", new Object[] {
		                TableConvert.getV(tableName.toLowerCase())
		            });
			}else{
				throw new Exception("不支持此数据库类型!");
			}
			log.info("---method--checkTableExist--sql--"+sql);
			rs = stmt.executeQuery(sql);
			if(rs.last()){
				return rs.getRow()>0;
			}
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Column> getOriginalTableColumn(String tableName) throws Exception{
		List<Column> columnList = new ArrayList<Column>();
		try {
			Class.forName(ResourceUtil.DRIVER_NAME);
			conn = DriverManager.getConnection(ResourceUtil.URL, ResourceUtil.USERNAME, ResourceUtil.PASSWORD);
			//1005标识结果集可滚动，对数据更新敏感，1007表示数据只读
			stmt = conn.createStatement(1005, 1007);
			if(ResourceUtil.DATABASE_TYPE.equals("mysql")){
				sql = MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}", new Object[] {
                    TableConvert.getV(tableName.toUpperCase()), TableConvert.getV(ResourceUtil.DATABASE_NAME)
                });
			}else if(ResourceUtil.DATABASE_TYPE.equals("oracle")){
				sql = MessageFormat.format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}", new Object[] {
                    TableConvert.getV(tableName.toUpperCase())
                });
			}else if(ResourceUtil.DATABASE_TYPE.equals("sqlserver")){
				sql = MessageFormat.format("select cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join sys.objects c on a.object_id=c.object_id and c.type='''U''' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0}", new Object[] {
                    TableConvert.getV(tableName.toLowerCase())
                });
			}else{
				throw new Exception("不支持此数据库类型!");
			}
			rs = stmt.executeQuery(sql);
			int n = 0;
			Column column;
			while(rs.next()){
				column = new Column();
				if(ResourceUtil.FIELD_CONVERT){
					column.setFiledName(formatField(rs.getString(1).toLowerCase()));
				}else{
					column.setFiledName(rs.getString(1).toLowerCase());
				}
				column.setFiledDbName(rs.getString(1));
				column.setPrecision(TableConvert.getNullString(rs.getString(4)));
				column.setScale(TableConvert.getNullString(rs.getString(5)));
				column.setCharmaxLength(TableConvert.getNullString(rs.getString(6)));
				column.setNullable(TableConvert.getNullAble(rs.getString(7)));
				column.setFiledType(formatDataType(rs.getString(2).toLowerCase(), column.getPrecision(), column.getScale()));
				formatFieldClassType(column);
				column.setFiledComment(StringUtils.isBlank(rs.getString(3)) ? column.getFiledName() : rs.getString(3));
				columnList.add(column);
				n++;
			}
			if(n > 0){
				return columnList;
			}else{
				throw new Exception("\u8BE5\u8868\u4E0D\u5B58\u5728\u6216\u8005\u8868\u4E2D\u6CA1\u6709\u5B57\u6BB5");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(stmt != null)
            {
                stmt.close();
                stmt = null;
                System.gc();
            }
            if(conn != null)
            {
                conn.close();
                conn = null;
                System.gc();
            }
		}
	}
	
	public static String formatField(String field)
    {
        String strs[] = field.split("_");
        field = "";
        int m = 0;
        for(int length = strs.length; m < length; m++)
            if(m > 0)
            {
                String tempStr = strs[m].toLowerCase();
                tempStr = (new StringBuilder(String.valueOf(tempStr.substring(0, 1).toUpperCase()))).append(tempStr.substring(1, tempStr.length())).toString();
                field = (new StringBuilder(String.valueOf(field))).append(tempStr).toString();
            } else
            {
                field = (new StringBuilder(String.valueOf(field))).append(strs[m].toLowerCase()).toString();
            }

        return field;
    }
	
	private static String formatDataType(String dataType, String precision, String scale){
		if(dataType.contains("char")){
			dataType = "java.lang.String";
		}else if(dataType.contains("int")){
			dataType = "java.lang.Integer";
		}else if(dataType.contains("float")){
			dataType = "java.lang.Float";
		}else if(dataType.contains("double")){
			dataType = "java.lang.Double";
		}else if(dataType.contains("number")){
			if(StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0){
                dataType = "java.math.BigDecimal";
			}else if(StringUtils.isNotBlank(precision) && Integer.parseInt(precision) > 10){
                dataType = "java.lang.Long";
			}else{
                dataType = "java.lang.Integer";
			}
		}else if(dataType.contains("decimal")){
			dataType = "BigDecimal";
		}else if(dataType.contains("date")){
			dataType = "java.util.Date";
		}else if(dataType.contains("time")){
			dataType = "java.util.Date";
		}else if(dataType.contains("blob")){
			dataType = "byte[]";
		}else if(dataType.contains("clob")){
			dataType = "java.sql.Clob";
		}else if(dataType.contains("numeric")){
			dataType = "BigDecimal";
		}else if(dataType.equalsIgnoreCase("text")){
			dataType = "java.lang.String";
		}else{
			dataType = "java.lang.Object";
		}
		return dataType;
	}
	
	private void formatFieldClassType(Column column){
		String fieldType = column.getFiledType();
		String scale = column.getScale();
		column.setClassType("inputxt");
		if("N".equals(column.getNullable())){
			column.setOptionType("*");
		}
		if(fieldType.contains("time")){
			column.setClassType("easyui-datetimebox");
		}else if("date".equals(fieldType)){
			column.setClassType("easyui-datebox");
		}else if(fieldType.contains("int")){
			column.setOptionType("n");
		}else if("number".equals(fieldType)){
			if(StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0){
				column.setOptionType("d");
			}
		}else if("float".equals(fieldType) || "double".equals(fieldType) || "decimal".equals(fieldType) || "numeric".equals(fieldType)){
			column.setOptionType("d");
		}
	}

}
