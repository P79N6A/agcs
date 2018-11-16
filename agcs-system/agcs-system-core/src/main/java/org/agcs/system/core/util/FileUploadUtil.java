package org.agcs.system.core.util;

import java.net.URL;
import org.codehaus.xfire.client.Client;

public class FileUploadUtil {
	
	private static final String interface_url = Env.getInstance().getProperty("interface_url");
	
	/**
	 * 图片上传
	 * @param phone
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static String upload(String key, String val, String code, String data) throws Exception{
		try {
			Client client = new Client(new URL(interface_url));
			Object[] results = client.invoke("invoke",new Object[] { key, val, code, data});
			return results[0]+"";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
