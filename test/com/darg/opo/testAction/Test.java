package com.darg.opo.testAction;

import com.darg.opo.dao.TAddrTempDAO;
import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport{

	TAddrTempDAO tAddrTempDAO;
	
	
	public void settAddrTempDAO(TAddrTempDAO tAddrTempDAO) {
		this.tAddrTempDAO = tAddrTempDAO;
	}
	
	
	@Override
	public String execute() throws Exception {
		System.out.println(tAddrTempDAO.findAll());
		return SUCCESS;
	}

}
