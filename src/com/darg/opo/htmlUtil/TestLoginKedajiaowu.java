package com.darg.opo.htmlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TestLoginKedajiaowu {

	public String testLogin(String username, String password) throws IllegalStateException, IOException {
		HttpClient httpclient = new DefaultHttpClient();

		//设置登录参数  
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("zjh", username));
		formparams.add(new BasicNameValuePair("mm", password));
		UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(formparams, "GBK");

		//新建Http  post请求  
		HttpPost httppost = new HttpPost("http://jw.hebust.edu.cn/loginAction.do");
		httppost.setEntity(entity1);

		//处理请求，得到响应  
		HttpResponse response = httpclient.execute(httppost);

//		Header[] headers = response.getAllHeaders();
//
//		for (int i = 0; i < headers.length; i++) {
//			System.out.println(headers[i].getName() + "  " + headers[i].getValue());
//		}

		String set_cookie = response.getFirstHeader("Set-Cookie").getValue();

//		//打印Cookie值  
//		System.out.println(set_cookie.substring(0, set_cookie.indexOf(";")));
//
//		//打印返回的结果  
//		HttpEntity entity = response.getEntity();
//
//		StringBuilder result = new StringBuilder();
//		if (entity != null) {
//			InputStream instream = entity.getContent();
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(instream));
//			String temp = "";
//			while ((temp = br.readLine()) != null) {
//				String str = new String(temp.getBytes(), "utf-8");
//				result.append(str);
//			}
//		}
		return set_cookie;
	}

	public String getPage(String cookie) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		// 根据获得的Cookie值，设置头信息，然后发送请求，获得内容  
		HttpGet httpget = new HttpGet("http://jw.hebust.edu.cn/menu/s_main.jsp");
		httpget.setHeader("Cookie", cookie);
		HttpResponse response = httpclient.execute(httpget);
		//打印返回的结果  
		HttpEntity entity = response.getEntity();

		StringBuilder result = new StringBuilder();
		if (entity != null) {
			InputStream instream = entity.getContent();

			BufferedReader br = new BufferedReader(new InputStreamReader(instream,"GBK"));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				String str = new String(temp.getBytes(), "GBK");
				result.append(str);
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		TestLoginKedajiaowu login = new TestLoginKedajiaowu();
		String cookie = null;
		String result = null;
		try {
			cookie = login.testLogin("110210221", "13228261009");
			//System.out.println(cookie);
			result = login.getPage(cookie);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println(result);
	}

}
