package org.agcs.system.web.controller.glpt;

import javax.servlet.http.HttpServletRequest;

import org.agcs.system.core.util.StringUtil;
import org.agcs.system.mq.producer.IProducerService;
import org.agcs.system.mq.producer.ITransactionProducerService;
import org.agcs.system.mq.producer.Tag;
import org.agcs.system.mq.producer.Topic;
import org.agcs.system.web.common.AjaxJson;
import org.agcs.system.web.entity.glpt.GlptNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
* @Title: Controller
* @Description:  消息管理
* @author vivian
* @date 2016-02-18
* @version V1.0
 */
@Controller
@RequestMapping("/glptRocketMqController")
public class GlptRocketMqController {
	
	@Autowired
	private IProducerService produceService;
	@Autowired
	private ITransactionProducerService transactionProduceService;
	
	@RequestMapping(params = "goSendMq")
	public ModelAndView goSendMq(HttpServletRequest request) {
		return new ModelAndView("glpt/sendRocketMq");
	}
	
	/**
	 * 发送普通消息
	 */
	@RequestMapping(params = "sendNormalMq")
	@ResponseBody
	public AjaxJson sendNormalMq(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			String msg = request.getParameter("msg");
			produceService.pushNormalMQ(Topic.ORDER_QG.toString(), Tag.ORDER_QG.toString(), request.getSession().getId(), msg);
			j.setMsg("消息发送成功");
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
			}
			exceptionstr += StringUtil.isEmpty(estr)?"":estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			j.setSuccess(false);
			j.setMsg(exceptionstr);
			
		}
		return j;
	}
	
	/**
	 * 发送顺序消息
	 */
	@RequestMapping(params = "sendOrderMq")
	@ResponseBody
	public AjaxJson sendOrderMq(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			String msg = request.getParameter("msg");
			int order = 5;
			produceService.pushOrderMQ(Topic.ORDER_QG.toString(), Tag.ORDER_QG.toString(), request.getSession().getId(), order, msg);
			j.setMsg("消息发送成功");
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
			}
			exceptionstr += StringUtil.isEmpty(estr)?"":estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			j.setSuccess(false);
			j.setMsg(exceptionstr);
			
		}
		return j;
	}
	
	/**
	 * 发送顺序消息
	 */
	@RequestMapping(params = "sendTransactionMq")
	@ResponseBody
	public AjaxJson sendTransactionMq(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		try {
			String msg = request.getParameter("msg");
			transactionProduceService.pushTransactionMQ(Topic.ORDER_QG.toString(), Tag.ORDER_QG.toString(), request.getSession().getId(), msg);
			j.setMsg("消息发送成功");
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
			}
			exceptionstr += StringUtil.isEmpty(estr)?"":estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			j.setSuccess(false);
			j.setMsg(exceptionstr);
			
		}
		return j;
	}

}
