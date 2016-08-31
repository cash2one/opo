package com.darg.opo.action.threadBean;

import java.io.IOException;

import com.darg.opo.htmlUtil.TestLoginKedajiaowu;

public class ThreadKedaJiaowu extends Thread {
	@Override
	public void run() {
		try {			
			TestLoginKedajiaowu login = new TestLoginKedajiaowu();
			String cookie = null;
			String result = null;
			try {
				cookie = login.testLogin("110210221", "13228261009");			
				result = login.getPage(cookie);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(result);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
