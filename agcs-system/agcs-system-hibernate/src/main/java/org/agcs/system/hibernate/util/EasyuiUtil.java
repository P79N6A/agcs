package org.agcs.system.hibernate.util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.agcs.system.core.util.JsonUtil;
import org.agcs.system.hibernate.qbc.DataGrid;

/**
* @Title: EasyuiUtil.java 
* @Package org.braveframwork.hibernate.util 
* @Description:  easyui公共操作类
* @author vivian
* @date 2016-2-17 下午2:22:19 
* @version V1.0
 */
public class EasyuiUtil {
	
	/**
	 * 表格操作
	 * @param response
	 * @param dg
	 */
	public static void datagrid(HttpServletResponse response,DataGrid dg){
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			String diamondjson = JsonUtil.listToJson(dg.getResultList(), dg.getTotal());
			PrintWriter pw=response.getWriter();
			pw.write(diamondjson);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
