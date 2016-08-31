package com.darg.opo.htmlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;

import com.darg.opo.commutil.CommonUtil;
import com.darg.opo.pojo.TBaiduNewsSerch;

public class Catchbdnews {
	// private static final String strUrl = CommonUtil.GRAB_FOODPOISONURL;
	public static String CatchNewHtml(String strUrl) {
		String str = "";
		try {
			// String strUrl = CommonUtil.GRAB_FOODPOISONURL;
			URL url = new URL(strUrl);
			// InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
			InputStreamReader isr = new InputStreamReader(url.openStream(), "utf-8");
			// 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
			BufferedReader br = new BufferedReader(isr);
			String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容
			// 定义5个正则 用于匹配我们需要的数据
			String title = ">.*</a></h3>";
			String Website = "<h3 class=\"c-title\"><.*?\".*?\"";
			String author = "<p.class=\"c-author\">.*?&";
			String time = "<p.class=\"c-author\">.*?</p>";
			String content = "<p.class=\"c-author\">.*?</p>.*?<span";
			// 创建一个CatchMethod类的对象 gMethod
			CatchMethod gMethod = new CatchMethod();
			int j = 0;
			int i = 0;
			// 开始读取数据 如果读到的数据不为空 则往里面读
			while ((strRead = br.readLine()) != null) {
				String strGet = gMethod.regularGroup(title, strRead);
				if (!strGet.equals("")) {
					// 用于捕获标题title
					strGet = strGet.substring(1, strGet.indexOf("</a></h3>"));
					String si = strGet.replaceAll("<em>", "");
					String titleOne = si.replaceAll("</em>", "");
					// System.out.println("标题:" + titleOne);
					str = str + titleOne + "#";
					// 用于捕获作者author
					strGet = gMethod.regularGroup(author, strRead);
					if (!strGet.equals("")) {
						strGet = strGet.substring(0, strGet.indexOf("&"));
						String authorOne = strGet.replaceAll("<p.class=\"c-author\">", "");
						//System.out.println("作者:" + authorOne);
						str = str + authorOne + "#";
					}
					// 用于捕获 时间time
					strGet = gMethod.regularGroup(time, strRead);
					if (!strGet.equals("")) {
						strGet = strGet.substring(0, strGet.indexOf("</p>"));
						String timeOne = strGet.replaceAll("<p.class=\"c-author\">.*?;.*?;", "");
						//System.out.println("时间:" + timeOne);
						str = str + timeOne + "#";
					}
					// 用于捕获内容content
					strGet = gMethod.regularGroup(content, strRead);
					if (!strGet.equals("")) {
						strGet = strGet.substring(0, strGet.indexOf("<span"));
						String s = strGet.replaceAll("<p.class=\"c-author\">.*?</p>", "");
						String d = s.replaceAll("<em>", "");
						String contentOne = d.replaceAll("</em>", "");
						//System.out.println("内容:" + contentOne);
						str = str + contentOne + "0##0";
					}
					j = j + 1;
					//System.out.println("****************j:  " + j);
				}

				String strGetTwo = gMethod.regularGroup(Website, strRead);
				if (!strGetTwo.equals("")) {
					// 用于捕获网址Website
					if (!strGetTwo.equals("")) {
						String s = strGetTwo.replaceAll("<h3 class=\"c-title\"><.*?\"", "");
						String web = s.replaceAll("\"", "");
						//System.out.println("网址:" + web);
						str = str + web + "#";
					}
					i = i + 1;
					//System.out.println("****************i:  " + i);

				}
				if (j == 10 && i == 10) {
					break;
				}

			}
			// 当读完数据后 关闭 BufferReader
			br.close();
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) {
		String keyWord="";
		String urlFirst="http://news.baidu.com/ns?word=";			
		String urlMiddle="&pn=";
		int size=20;
		String urlLast="&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0";
		String url=urlFirst+"天津"+urlMiddle+(size*1)+urlLast;
		
	//	String str=ddCatchbdnews.CatchNewHtml(CommonUtil.GRAB_FOODPOISONURL);
		String str=Catchbdnews.CatchNewHtml(url);
		String[] arrayOne=str.split("0##0");
		TBaiduNewsSerch tBaiduNewsSerch=null;
		for (String string : arrayOne) {
			if(!string.isEmpty()){
				String[] arrayTwo=string.split("#");
				Timestamp time=CommonUtil.getNowTime_tamp();
				if(arrayTwo!=null && arrayTwo.length==5){
					tBaiduNewsSerch=new TBaiduNewsSerch();
					tBaiduNewsSerch.setNewsUrl(arrayTwo[0]);//url								
					tBaiduNewsSerch.setTitle(arrayTwo[1]);//标题
					tBaiduNewsSerch.setAuthor(arrayTwo[2]);//作者
					//tBaiduNewsSerch.set(arrayTwo[3]);//发布时间------------需要正则匹配
					if(arrayTwo[3].contains("分钟")){
						String[] arrayMinuters=arrayTwo[3].split("分钟");
						int minuters=Integer.parseInt(arrayMinuters[0]);
						Timestamp tp = new Timestamp(System.currentTimeMillis());
						Long lg = tp.getTime() - 86400000/(24*60)*minuters;
						Timestamp publicTime = new Timestamp(lg);
						tBaiduNewsSerch.setPublicTime(publicTime);
					}else if(arrayTwo[3].contains("小时")){
						String[] arrayHour=arrayTwo[3].split("小时");
						int h=Integer.parseInt(arrayHour[0]);
						Timestamp tp = new Timestamp(System.currentTimeMillis());
						Long lg = tp.getTime() - (86400000/24)*h;
						Timestamp publicTime = new Timestamp(lg);
						tBaiduNewsSerch.setPublicTime(publicTime);
					}else if(arrayTwo[3].contains("年") && arrayTwo[3].contains("月")){
						tBaiduNewsSerch.setPublicTime(CommonUtil.StringChinaDateToTimestap(arrayTwo[3]));										
					}else{
						tBaiduNewsSerch.setPublicTime(time);
					}
					tBaiduNewsSerch.setContent(arrayTwo[4]);//内容
					tBaiduNewsSerch.setCtime(time);//抓取时间
				System.out.println(tBaiduNewsSerch.toString());
					
					
				}
			}
		}
	}

}
