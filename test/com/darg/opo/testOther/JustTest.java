package com.darg.opo.testOther;

	import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.darg.opo.commutil.CommonUtil;
import com.darg.opo.htmlUtil.Html;
import com.darg.opo.pojo.TTopKeyWord;

	public class JustTest {
	/*	public static void main(String[] args) {
			String html = CommonUtil.getHtmlByUrl("http://top.baidu.com/buzz/top10.html/");
		
			if (html!=null&&!"".equals(html)) {
			
				Document doc = Jsoup.parse(html);
				Elements linksElements = doc.select("div#top_picture_image_0>a");
				//以上代码的意思是 找id为“page”的div里面   id为“content”的div里面   id为“main”的div里面   class为“left”的div里面   id为“recommend”的div里面ul里面li里面a标签
				for (Element ele:linksElements) {
					
					String href = ele.attr("href");
					String title =ele.h();
					System.out.println(href+","+title);
				}
			}
			
		}*/
		public static void main(String[] args) {
			  String [] params={"雪花雨痕泪","sr740139800@"};  
			try {
				String html = Html.createHtml(CommonUtil.GRAB_LOGINURL,CommonUtil.GRAB_FORWARDURL,params);;
				if (html!=null && !"".equals(html)) {
					
					Document doc = Jsoup.parse(html);
					Elements table_s = doc.select("table.list-table");
					Elements tr=table_s.get(0).getElementsByTag("tr"); 
					List<TTopKeyWord> listTTopKeyWord = new ArrayList<>();
					Timestamp time = CommonUtil.getNowTime_tamp();
					 for (Element tablerow : tr) { 						  
			             Elements e_keyword = tablerow.getElementsByClass("keyword");  
			             Elements e_last = tablerow.getElementsByClass("last"); 
			             String keyWord=e_keyword.text();//标题
			             String count=e_last.text();//搜素数
			             if(keyWord!=null && count!=null && !keyWord.isEmpty() && !count.isEmpty()){
			            	 TTopKeyWord tTopKeyWord=new TTopKeyWord();				             
				             keyWord=keyWord.replace("search", "");			            	
			            	 tTopKeyWord.setKeyWord(keyWord);
			            	 tTopKeyWord.setCount(Integer.parseInt(count));
			            	 tTopKeyWord.setCtime(time);
			            	 listTTopKeyWord.add(tTopKeyWord);
			             }			             			           			             
					 }
					 for (TTopKeyWord tTopKeyWord : listTTopKeyWord) {
						System.out.println(tTopKeyWord.getKeyWord()+": "+tTopKeyWord.getCount());
					}
					
					//以上代码的意思是 找id为“page”的div里面   id为“content”的div里面   id为“main”的div里面   class为“left”的div里面   id为“recommend”的div里面ul里面li里面a标签
					/*for (Element ele:linksElements) {
						
						//String href = ele.attr("href");	
						String href=ele.html();
						System.out.println(href);
					}*/
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
