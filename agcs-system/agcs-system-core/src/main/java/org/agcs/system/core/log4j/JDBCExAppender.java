package org.agcs.system.core.log4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.agcs.system.core.util.StringUtil;
import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

public class JDBCExAppender extends JDBCAppender{
	private static List<String> classList = new ArrayList<String>();
	static{
		classList.add("org.brave.web");
		classList.add("org.braveframwork");
		classList.add("org.hibernate.hql");
	}
	public JDBCExAppender(){
		super();
	}
	
	@Override
	protected void execute(String sql)
	        throws SQLException
	{
		if(!StringUtil.isEmpty(sql)){
			Connection con = null;
	        Statement stmt = null;
			try {
		        con = getConnection();
		        stmt = con.createStatement();
		        stmt.executeUpdate(sql);
			} catch (Exception e) {
				String exceptionstr = "异常信息:";
				e.printStackTrace();
				StackTraceElement stackTraceElement = e.getStackTrace()[0];
				String estr = e.getMessage();
				if(estr != null){
					estr = estr.replaceAll("\r", "");
					estr = estr.replaceAll("\n", "");
					estr = estr.replaceAll("\t", "");
					estr = estr.replaceAll("\f", "");
					estr = estr.replaceAll("\b", "");
					exceptionstr += estr + "</br>文件名:"
							+ stackTraceElement.getFileName() + "</br>行数:"
							+ stackTraceElement.getLineNumber() + "</br>方法名:"
							+ stackTraceElement.getMethodName();
				}else{
					exceptionstr += " 获取连接异常";
				}
				throw new SQLException(exceptionstr);
			}finally{
				if(stmt != null)
		            stmt.close();
		        closeConnection(con);
			}
		}
	}
	
	private static boolean insertLogDb(String classname){
		boolean bool = false;
		if(!StringUtil.isEmpty(classname)){
			for(String temp:classList){
				if(classname.contains(temp)){
					return true;
				}
			}
		}
		return bool;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected String getLogStatement(LoggingEvent event)
    {
		if(insertLogDb(event.categoryName) || "WARN".equals(event.level.toString())){
			String fqnOfCategoryClass=event.fqnOfCategoryClass;
			Category logger=Category.getRoot();
			Priority level=event.level;
			Object message=event.getMessage();
			Throwable throwable=null;
			ExLoggingEvent exEvent = new ExLoggingEvent(fqnOfCategoryClass, logger, level, message, throwable);
			return getLayout().format(exEvent);
		}
		return null;
        
    }
	
}
