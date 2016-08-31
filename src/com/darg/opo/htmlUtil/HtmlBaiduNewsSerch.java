package com.darg.opo.htmlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.darg.opo.commutil.CommonUtil;
import com.darg.opo.pojo.TBaiduNewsSerch;

public class HtmlBaiduNewsSerch {

	public static String grabHtml(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		InputStream in = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	public static List<TBaiduNewsSerch> catchListTBaiduNewsSerch(String url_str) {
		List<TBaiduNewsSerch> list = new ArrayList<>();
		TBaiduNewsSerch one = new TBaiduNewsSerch();
		try {
			// String url_str="http://news.baidu.com/ns?word=阅兵&pn=0&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0";			
			String html = HtmlBaiduNewsSerch.grabHtml(url_str);
			Document doc = Jsoup.parse(html);
			Elements divClassList = doc.select(".result");
			Timestamp timestamp = CommonUtil.getNowTime_tamp();
			for (Element element : divClassList) {
				//one = new TBaiduNewsSerch();
				Elements e_title = element.getElementsByClass("c-title");// 标题
				Elements e_newsUrl_a = e_title.select("a");//链接
				Elements e_author = element.getElementsByClass("c-author");// 作者和时间
				Elements e_content = element.getElementsByClass("c-summary");// 作者和时间，内容
				e_content.select("P").remove();
				e_content.select("span").remove();
				String title = e_title.text();// 标题
				String authorTime = e_author.text();// 作者和时间
				String[] arrayAuthorTime = authorTime.split("  ");

				if (arrayAuthorTime.length < 2) {
					continue;
				}
				String author = arrayAuthorTime[0];// 作者
				String time = arrayAuthorTime[1];// 时间
				String content = e_content.text();// 内容
				String newsUrl = e_newsUrl_a.attr("href");//链接

				one.setTitle(title);// 标题
				one.setAuthor(author);// 作者
				one.setContent(content);// 内容
				one.setCtime(timestamp);//抓取时间
				one.setNewsUrl(newsUrl);

				if (time.contains("分钟")) {
					String[] arrayMinuters = time.split("分钟");
					int minuters = Integer.parseInt(arrayMinuters[0]);
					Timestamp tp = new Timestamp(System.currentTimeMillis());
					Long lg = tp.getTime() - 86400000 / (24 * 60) * minuters;
					Timestamp publicTime = new Timestamp(lg);
					one.setPublicTime(publicTime);// 发表时间
				} else if (time.contains("小时")) {
					String[] arrayHour = time.split("小时");
					int h = Integer.parseInt(arrayHour[0]);
					Timestamp tp = new Timestamp(System.currentTimeMillis());
					Long lg = tp.getTime() - (86400000 / 24) * h;
					Timestamp publicTime = new Timestamp(lg);
					one.setPublicTime(publicTime);// 发表时间
				} else if (time.contains("年") && time.contains("月")) {
					one.setPublicTime(CommonUtil.StringChinaDateToTimestap(time));// 发表时间
				} else {
					one.setPublicTime(timestamp);// 发表时间
				}
				list.add(one);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

	public static void main(String[] args) {
		HtmlBaiduNewsSerch.catchListTBaiduNewsSerch("http://news.baidu.com/ns?word=阅兵&pn=0&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0");
		/*try {
			String url_str = "http://news.baidu.com/ns?word=阅兵&pn=0&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0";
			String html = HtmlBaiduNewsSerch.grabHtml(url_str);
			Document doc = Jsoup.parse(html);
			Elements divClassList = doc.select(".result");
			for (Element element : divClassList) {
				Elements e_title = element.getElementsByClass("c-title");// 标题
				// Elements linksElements = doc.select("div#page>div#content>div#main>div.left>div#recommend>ul>li>a"); 
				Elements e_newsUrl_a=e_title.select("a");//链接
				Elements e_author = element.getElementsByClass("c-author");// 作者和时间
				Elements e_content = element.getElementsByClass("c-summary");// 作者和时间，内容
				
				e_content.select("P").remove();
				e_content.select("span").remove();
				String title = e_title.text();// 标题
				String authorTime = e_author.text();// 作者和时间
				String[] arrayAuthorTime = authorTime.split("  ");
				String author = arrayAuthorTime[0];// 作者
				String time = arrayAuthorTime[1];// 时间
				String content = e_content.text();// 内容
				String newsUrl = e_newsUrl_a.attr("href");//链接

				System.out.println(title);
				System.out.println(author);
				System.out.println(time);
				System.out.println(content);
				System.out.println(newsUrl);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
