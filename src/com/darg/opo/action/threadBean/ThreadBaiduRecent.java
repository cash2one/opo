package com.darg.opo.action.threadBean;

import java.util.ArrayList;
import java.util.List;

import com.darg.opo.commutil.SpringContextsUtil;
import com.darg.opo.htmlUtil.HtmlBaiduNewsSerch;
import com.darg.opo.pojo.TBaiduNewsSerch;
import com.darg.opo.pojo.TPublicSentimentRecent;
import com.darg.opo.service.ICommonServer;

public class ThreadBaiduRecent extends Thread {
	private TPublicSentimentRecent tRecent;
	
	ICommonServer commonService=(ICommonServer) SpringContextsUtil.getBean("commonService");
	
	@Override
	public void run() {
		try {
			String keyWord="";
			String urlFirst="http://news.baidu.com/ns?word=";			
			String urlMiddle="&pn=";
			int size=20;
			String urlLast="&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0";
			/*String url="http://news.baidu.com/ns?word="
					+"%E9%A3%9F%E7%89%A9%E4%B8%AD%E6%AF%92"
					+"&pn=80&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0";*/
			String url="";				
			List<TBaiduNewsSerch> listBaiduNewsSerchs=null;
			List<TBaiduNewsSerch> listIsNO=null;
			if(tRecent!=null && !tRecent.getKeyWord().isEmpty()){
				keyWord=tRecent.getKeyWord();
				for (int i = 0; i < 1; i++) {//抓取10页
					url=urlFirst+keyWord+urlMiddle+(size*i)+urlLast;	
					listBaiduNewsSerchs=new ArrayList<>();
					listBaiduNewsSerchs=HtmlBaiduNewsSerch.catchListTBaiduNewsSerch(url);					
					for (TBaiduNewsSerch tBaiduNewsSerch : listBaiduNewsSerchs) {
						listIsNO=new ArrayList<>();
						listIsNO=commonService.findBaiduNewsSerchsBytitleLike(tBaiduNewsSerch.getTitle());//通过标题查询
						if(listIsNO!=null && listIsNO.size()>0){//有，更新时间
							//commonServer.tranDeleteBaiduNewsSerch(listBaiduNewsSerchs.get(0));
							tBaiduNewsSerch.setCindex(listIsNO.get(0).getCindex());
							commonService.tranModifyBaiduNewsSerch(tBaiduNewsSerch);
							
						}else{//没有新建
							commonService.tranSaveBaiduNewsSerch(tBaiduNewsSerch);
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public TPublicSentimentRecent gettRecent() {
		return tRecent;
	}

	public void settRecent(TPublicSentimentRecent tRecent) {
		this.tRecent = tRecent;
	}


}
