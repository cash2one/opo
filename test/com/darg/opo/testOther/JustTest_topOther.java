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

public class JustTest_topOther {
	public static void main(String[] args) {
		  String [] params={"雪花雨痕泪","sr740139800@"};  
		try {
			String html = Html.createHtml(CommonUtil.GRAB_LOGINURL_CLOUND,CommonUtil.GRAB_FORWARDURL_CLOUD,params);;
			if (html!=null && !"".equals(html)) {
				
				Document doc = Jsoup.parse(html);
				Elements ul_s = doc.select("ul.item-list");
				List<TTopKeyWord> listTTopKeyWord = new ArrayList<>();
				Timestamp time = CommonUtil.getNowTime_tamp();
				for (int i = 0; i < ul_s.size(); i++) {
					Elements li_s=ul_s.get(i).select("li");
					for (Element element : li_s) {
						Elements e_keyword=element.getElementsByClass("list-title");
						String keyWord=e_keyword.text();//标题
						Elements e_count_fall = element.getElementsByClass("icon-fall");//降
						Elements e_count_rise = element.getElementsByClass("icon-rise");//升
						Elements e_count_fair = element.getElementsByClass("icon-fair");//平
						String count="";
						String count_fall=e_count_fall.text();//搜素数
						String count_rise=e_count_rise.text();//搜素数
						String count_fair=e_count_fair.text();//搜素数
						if(!count_fall.isEmpty()){
							count=count_fall;//搜素数
						}else if(!count_rise.isEmpty()){
							count=count_rise;//搜素数
						}else{
							count=count_fair;//搜素数
						}						
						if(keyWord!=null && count!=null && !keyWord.isEmpty() && !count.isEmpty()){
			            	 TTopKeyWord tTopKeyWord=new TTopKeyWord();				             
				             keyWord=keyWord.replace("search", "");			            	
			            	 tTopKeyWord.setKeyWord(keyWord);
			            	 tTopKeyWord.setCount(Integer.parseInt(count));
			            	 tTopKeyWord.setType(String.valueOf(i+2));
			            	 tTopKeyWord.setCtime(time);
			            	 listTTopKeyWord.add(tTopKeyWord);
			             }									
					}
				}
				 for (TTopKeyWord tTopKeyWord : listTTopKeyWord) {
						System.out.println(tTopKeyWord.getKeyWord()+": "+tTopKeyWord.getCount()+" type: "+tTopKeyWord.getType());
				}
				
				 
				 
				 
				 
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
