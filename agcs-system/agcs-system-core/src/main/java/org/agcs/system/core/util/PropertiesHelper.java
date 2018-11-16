package org.agcs.system.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2392784650532373075L;
	
	private static PropertiesHelper instance;
	
	private PropertiesHelper() {
        InputStream is = getClass().getResourceAsStream("/xtpagram.properties");
        try {
            load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static PropertiesHelper getInstance(){
		if(instance != null){
			return instance;
		}else{
			makeInstance();
			return instance;
		}
	}
	
	private static synchronized void makeInstance(){
		if(instance == null){
			instance = new PropertiesHelper();
		}
	}
	
	public static void main(String[] args) {   	
    	System.out.println(PropertiesHelper.getInstance().getProperty("islog").equals("true"));//调用
	}
	

}
