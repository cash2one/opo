package com.darg.opo.commutil;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

	/**
	 * 服务器url
	 */
	public static final String PROJECTURLSTR = "http://192.168.50.2:8080/opo";

	/**
	 * session中的登录者信息
	 */
	public static final String LOGIN_INFO = "LOGIN_INFO";
	/**
	 * 网页抓取中百度top的登录界面url
	 */
	public static final String GRAB_LOGINURL = "http://top.baidu.com/buzz/top10.html";
	/**
	 * 网页抓取百度top页面url
	 */
	public static final String GRAB_FORWARDURL = "http://top.baidu.com/buzz/top10.html";
	/**
	 * 网页抓取中百度风云榜的登录界面url
	 */
	public static final String GRAB_LOGINURL_CLOUND = "http://top.baidu.com/category?c=513&fr=topindex";
	/**
	 * 网页抓取百度风云榜页面url
	 */
	public static final String GRAB_FORWARDURL_CLOUD = "http://top.baidu.com/category?c=513&fr=topindex";
	/**
	 * 网页抓取百度新闻(食物中毒)页面url
	 */
	public static final String GRAB_FOODPOISONURL = "http://news.baidu.com/ns?word=%CA%B3%CE%EF%D6%D0%B6%BE&tn=news&from=news&cl=2&rn=20&ct=1";


	/**
	 * 产生六位随机数
	 */
	public static String get6Ran() {
		Random r = new Random();
		int pwd = r.nextInt(1000000);
		while (pwd < 100000) {
			pwd = r.nextInt(1000000);
		}
		return String.valueOf(pwd);
	}

	/**
	 * 生成19位数字ID
	 * 
	 * @return 19位数字id
	 */
	public static String get19NumID() {
		String id = null;
		id = String.valueOf(System.currentTimeMillis()) + String.valueOf(get6Ran());
		return id;
	}

	/**
	 * 获取系统的当前时间
	 * 
	 * @return 返回 Timestamp 类型的时间
	 */
	public static Timestamp getNowTime_tamp() {
		// 获取当前时间
		Date now = new Date();
		Long tim = now.getTime();
		Timestamp time = new Timestamp(tim);
		return time;
	}
	/**
	 *昨天Timestamp时间
	 */
	public static Timestamp getYesterdayTime_tamp() {	
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		Long lg = tp.getTime() - 86400000;
		Timestamp startCreatetime = new Timestamp(lg);
		return startCreatetime;
	}
	/**
	 * 前天Timestamp时间
	 */
	public static Timestamp getBeforeYesterdayTime_tamp() {	
		Timestamp tp = new Timestamp(System.currentTimeMillis());
		Long lg = tp.getTime() - 86400000*2;
		Timestamp startCreatetime = new Timestamp(lg);
		return startCreatetime;
	}
	/**
	 * 获取系统的当前时间
	 * 
	 * @return 字符串类型的XXXX-XX-XX的时间
	 */
	public static String getNowTime() {
		// 获取当前时间
		Date now = new Date();
		Long tim = now.getTime();
		Timestamp time = new Timestamp(tim);
		return time.toString().substring(0, 10);
	}

	/**
	 * 2010年05月18日 14:00 字符串转换成Timestamp
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp StringChinaDateToTimestap(String str) {
		str = str.replace("年", "-");
		str = str.replace("月", "-");
		str = str.replace("日", "");
		str=str+":38.426";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 2011-05-09 11:49:45 字符串转换成Timestamp
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp StringEnglishDateToTimestap(String str) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String stringToMD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 去除字符串中的空格
	 * 
	 * @author rz
	 */
	public static String delSpace(String fileName) {
		String name = "";
		if (fileName != null && fileName.length() > 0) {
			name = fileName.replaceAll(" ", "");
		} else {
			name = "";
		}
		return name;
	}

	public static void main(String[] args) {
	String str="2010年05月18日 14:00";
		str = str.replace("年", "-");
		str = str.replace("月", "-");
		str = str.replace("日", "");
		str=str+":38.426";
		// 获取当前时间
				Date now = new Date();
				Long tim = now.getTime();
				Timestamp ts = new Timestamp(tim);
		try {			
			ts = Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(ts);
	}


}
