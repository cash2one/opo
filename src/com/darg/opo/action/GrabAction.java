package com.darg.opo.action;

import com.darg.opo.pojo.TTopKeyWord;
import com.darg.opo.service.ICommonServer;
import com.opensymphony.xwork2.ActionSupport;


public class GrabAction extends ActionSupport{
	private ICommonServer commonService;
	private String g_message;// 公用返回消息（success,error,nodata,其他）
	
	/**
	 * 测试
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/12
	 */
	public String testAction() {
		try {			
				g_message = "success";
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "success";

	}
	
	/**
	 * 测试
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/12
	 */
	public String test2Action() {
		try {			
			g_message = "success";				
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "success";

	}

	
	/* service 这里只能有set开始(由于spring的原因注入导致) */
	/*
	 * public ICommonServer getCommonService() { return commonService; }
	 */

	public void setCommonService(ICommonServer commonService) {
		this.commonService = commonService;
	}

	public String getG_message() {
		return g_message;
	}

	public void setG_message(String g_message) {
		this.g_message = g_message;
	}

	

}
