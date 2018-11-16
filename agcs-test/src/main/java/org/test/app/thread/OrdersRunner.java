package org.test.app.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.agcs.system.core.util.DESCorder;
import org.agcs.system.core.util.HttpUtil;
import org.agcs.system.core.util.StringUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.test.app.common.MemberVo;
import org.test.app.common.SysTest;
import org.test.app.common.ThreadParent;

import sun.misc.BASE64Encoder;

public class OrdersRunner extends ThreadParent implements Runnable {
	
	private static final Logger log = Logger.getLogger(OrdersRunner.class);
	private String token;
	private String trncode;
	private String orderNo;
	private String orderState;
	private String pagestr = "1";
	private String rowsstr = "20";
	
	public OrdersRunner(){
		
	}
	
	public OrdersRunner(String memberId, String orderNo, String orderState, String accountId,
			           String rightsType, String pagestr, String rowsstr){
		
		this.orderNo = orderNo;
		this.orderState =  orderState;
		this.accountId = accountId;
		this.rightsType = rightsType;
		this.pagestr = pagestr;
		this.rowsstr = rowsstr;
	}

	@Override
	public void run() {
		try {
			if(StringUtil.isEmpty(token)){
				String resu = login();
				System.out.println("Login "+Thread.currentThread().getName()+":"+resu);
//				String result = execCountMoneys();
//				System.out.println(Thread.currentThread().getName()+":"+result);
				String result = custCustomvideo();
				System.out.println(Thread.currentThread().getName()+":"+result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private String login() throws Exception{
		String result = "";
		if(StringUtil.isEmpty(token)){
			
		}
		result = HttpUtil.do_get(SysTest.URL+"rest/api/member/login", "phone="+this.phone+"&password="+this.password+"&version="+this.version+"&timestamp="+System.currentTimeMillis());
		JSONObject json = JSONObject.fromObject(result);
		JSONArray smsArray = json.getJSONArray("data");
		Collection smsColl = JSONArray.toCollection(smsArray);
		if(smsColl!=null && !smsColl.isEmpty())  
        {  
            Iterator it=smsColl.iterator();  
            while(it.hasNext())  
            {  
                JSONObject jsonObj=JSONObject.fromObject(it.next()); 
                MemberVo member = (MemberVo) JSONObject.toBean(jsonObj,MemberVo.class);
                token = member.getToken();
                token = DESCorder.decryptMode(token, (member.getId()).substring(0, 24), "utf-8");
            }  
        }  
		trncode = json.getString("trncode");
//		System.out.println(token);
//		System.out.println(trncode);
		return result;
	}
	
	/**
	 * App报价
	 * @return
	 * @throws Exception
	 */
	private String execCountMoneys() throws Exception{
		String result = "";
		String tokenString = token.substring(token.length()-24, token.length()) + DESCorder.encryptModeToString(token+trncode, token.substring(token.length()-24, token.length()));
		String classifyNum = "女戒";
		String materal = "18K黄";
		String craft = "拉砂";
		String goldWeight = "23";
		String necklaceType = "";
		String size = "23";
		String carat = "30";
		String diamondColor = "F";
		String clarity = "VVS2";
		String diamondType = "3VG";
		String flourescence = "中荧光";
		String sideDmdNum = "";
		String sideDmdCarat = "";
		String param = "classifyNum="+classifyNum+"&materal="+materal+"&craft="+craft+"&goldWeight="+goldWeight+
					   "&necklaceType="+necklaceType+"&size="+size+"&carat="+carat+"&diamondColor="+diamondColor+
					   "&clarity="+clarity+"&diamondType="+diamondType+"&flourescence="+flourescence+
					   "&sideDmdNum="+sideDmdNum+"&sideDmdCarat="+sideDmdCarat;
		param = param + "&token="+tokenString+"&version="+this.version+"&timestamp="+System.currentTimeMillis();
		result = HttpUtil.do_get(SysTest.URL+"rest/api/order/execCountMoneys", param);
		
		return result;
	}
	
	/**
	 * 视频上传
	 * @return
	 * @throws Exception
	 */
	private String custCustomvideo() throws Exception{
		String result = "";
		String tokenString = token.substring(token.length()-24, token.length()) + DESCorder.encryptModeToString(token+trncode, token.substring(token.length()-24, token.length()));
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream("G:\\地久天长69R23261\\ABC_8322.jpg");
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String imgStr = encoder.encode(data);
		List<NameValuePair> name_value_pair3 = new ArrayList<NameValuePair>();
		//name_value_pair3.add(new BasicNameValuePair("orderNo", "11607201650462612476"));
		name_value_pair3.add(new BasicNameValuePair("memberId", "8af4eaba5388fb9801538904710f0012"));
		name_value_pair3.add(new BasicNameValuePair("extraFileUrl", imgStr));
		
		name_value_pair3.add(new BasicNameValuePair("version", this.version));
		name_value_pair3.add(new BasicNameValuePair("timestamp", System.currentTimeMillis()+""));
		name_value_pair3.add(new BasicNameValuePair("token", tokenString));
		result = HttpUtil.do_post(SysTest.URL+"rest/api/order/custCustomvideo", name_value_pair3);
		return result;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getPagestr() {
		return pagestr;
	}

	public void setPagestr(String pagestr) {
		this.pagestr = pagestr;
	}

	public String getRowsstr() {
		return rowsstr;
	}

	public void setRowsstr(String rowsstr) {
		this.rowsstr = rowsstr;
	}
	
}
