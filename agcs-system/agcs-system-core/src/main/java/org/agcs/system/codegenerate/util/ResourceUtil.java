package org.agcs.system.codegenerate.util;

import java.util.ResourceBundle;

public class ResourceUtil {
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("config\\brave_config");
	private static final ResourceBundle dbbundle = ResourceBundle.getBundle("config\\brave_database");
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String URL;
	public static String USERNAME = "root";
	public static String PASSWORD = "root";
	public static String DATABASE_NAME = "sys";
	public static String DATABASE_TYPE = "mysql";
	
	public static String FREEMARKER_CLASSPATH = "/template";
	public static String WEB_ROOT_PACKAGE;
	public static String SOURCE_ROOT_PACKAGE;
	public static String BUSINESS_PACKAGE;
	public static String JSPPATH;
	public static String CODEPATH;
	
	public static String SYSTEM_ENCODING;
	public static boolean FIELD_CONVERT = true;
	public static String TABLE_PRIMARY_KEY;
	
	static{
		DRIVER_NAME = getDRIVER_NAME();
		URL = getURL();
		USERNAME = getUSERNAME();
		PASSWORD = getPASSWORD();
		DATABASE_TYPE = getDATABASE_TYPE();
		DATABASE_NAME = getDATABASENAME();
		WEB_ROOT_PACKAGE = getWebRootPackage().replace(".", "/");
		SOURCE_ROOT_PACKAGE = getSourceRootPackage().replace(".", "/");
		BUSINESS_PACKAGE = getBusinessPackage();
		String bussiPackageUrl = getBusinessPackage().replace(".", "/");
		JSPPATH = (new StringBuilder(WEB_ROOT_PACKAGE).append("/pages/")).toString();
		CODEPATH = (new StringBuilder(SOURCE_ROOT_PACKAGE).append("/").append(bussiPackageUrl).append("/")).toString();
		SYSTEM_ENCODING = getSystemEncoding();
		FIELD_CONVERT = getFIELDCONVERT();
		TABLE_PRIMARY_KEY = getTABLEPRIMARYKEY();
	}
	
	public static final String getDRIVER_NAME(){
		return dbbundle.getString("driver_name");
	}
	
	public static final String getURL(){
		return dbbundle.getString("url");
	}
	
	public static final String getUSERNAME(){
		return dbbundle.getString("username");
	}
	
	public static final String getPASSWORD(){
		return dbbundle.getString("password");
	}
	
	public static final String getDATABASENAME(){
		return dbbundle.getString("database_name");
	}
	
	public static final String getDATABASE_TYPE(){
		return dbbundle.getString("database_type");
	}
	
	public static final String getWebRootPackage(){
		return bundle.getString("webroot_package");
	}
	
	public static final String getSourceRootPackage(){
		return bundle.getString("source_root_package");
	}
	
	public static final String getBusinessPackage(){
		return bundle.getString("bussi_package");
	}
	
	public static final String getSystemEncoding(){
		return bundle.getString("system_encoding");
	}
	
	public static final boolean getFIELDCONVERT(){
		String bool = bundle.getString("field_convert");
		return bool.equals("true");
	}
	
	public static final String getTABLEPRIMARYKEY(){
		return bundle.getString("generate_table_id");
	}

}
