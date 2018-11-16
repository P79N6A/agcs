package org.agcs.system.core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.servlet.http.HttpServletRequest;

public class ToolsUtil {
	/**
	 * 调用系统自带计算器
	 * 
	 * @return
	 */
	public static String calculator() {
		try {
			Runtime.getRuntime().exec("calc");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "no";
		}
		return "yes";
	}

	/**
	 * 检查Windows版本
	 * 
	 * @return
	 */
	public static String winver() {
		try {
			Runtime.getRuntime().exec("winver");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "no";
		}
		return "yes";
	}

	// 获取访问IP
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	//获取服务器IP
	public static String getHostIP() throws Exception{
		InetAddress netAddress = InetAddress.getLocalHost();
		String ip = netAddress.getHostAddress();
		return ip;
	}

	public static String filtChar(String str, String[] filterStrs) {
		for (int i = 0; i < filterStrs.length; i++) {
			if (str.indexOf(filterStrs[i]) != -1)
				str = str.replaceAll(filterStrs[i], "");
		}
		return str;
	}

	// 过滤掉所有特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	// 过滤所有空格
	public static String allWhite(String s) {
		if (s.indexOf(" ") != -1) {
			String s2 = s.replaceAll(" ", "");
			return s2;
		} else {
			return s;
		}
	}

	// 过滤html标签
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "&lt;[\\s]*?script[^&gt;]*?&gt;[\\s\\S]*?&lt;[\\s]*?\\/[\\s]*?script[\\s]*?&gt;"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																													// }
			String regEx_style = "&lt;[\\s]*?style[^&gt;]*?&gt;[\\s\\S]*?&lt;[\\s]*?\\/[\\s]*?style[\\s]*?&gt;"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																													// }
			String regEx_html = "&lt;[^&gt;]+&gt;"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	// 过滤html标签
	public static String Html2ScriptText(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;

		try {
			String regEx_script = "&lt;[\\s]*?script[^&gt;]*?&gt;[\\s\\S]*?&lt;[\\s]*?\\/[\\s]*?script[\\s]*?&gt;"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																													// }
			// String regEx_style =
			// "&lt;[\\s]*?style[^&gt;]*?&gt;[\\s\\S]*?&lt;[\\s]*?\\/[\\s]*?style[\\s]*?&gt;";
			// //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
			// String regEx_html = "&lt;[^&gt;]+&gt;"; //定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			// p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
			// m_style = p_style.matcher(htmlStr);
			// htmlStr = m_style.replaceAll(""); //过滤style标签

			// p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
			// m_html = p_html.matcher(htmlStr);
			// htmlStr = m_html.replaceAll(""); //过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	public static void main(String[] args) {
		/*
		 * String[] filterStrs = {"TMD","他妈的"};//可以从properties文件中读取 String str =
		 * "时代他妈的dfdTMD大幅度"; for(int i = 0; i < filterStrs.length; i ++){
		 * if(str.indexOf(filterStrs[i]) != -1) str =
		 * str.replaceAll(filterStrs[i],""); } System.out.println(str);
		 */

		String str = "<script>sdfsadfsdfsfd</script>asdfsa";
		System.out.println(str);
		System.out.println(Html2Text(str));
	}

	// 生成随机3位数的浏览次数
	public static Integer randFlux() {
		Random rand = new Random();
		String flux = "";
		for (int i = 0; i < 3; i++) {
			flux += rand.nextInt(4);
		}
		return Integer.parseInt(flux);
	}

	// 生成随机2位数的收藏次数
	public static Integer randCollect() {
		Random rand = new Random();
		String collect = "";
		for (int i = 0; i < 2; i++) {
			collect += rand.nextInt(8);
		}
		return Integer.parseInt(collect);
	}
	
	public static double doubleFormat(double number, String format){
		if(StringUtil.isEmpty(format)){
			format = "%.2f";
		}
		return Double.parseDouble(String.format(format, number));
	}
	
	public static String mapBykey2str(Map<String, Object> paramMap, String key){
		if(paramMap != null){
			return paramMap.get(key)==null?null:paramMap.get(key).toString();
		}
		return null;
	}
}
