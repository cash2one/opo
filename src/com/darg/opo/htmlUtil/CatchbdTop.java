package com.darg.opo.htmlUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.darg.opo.commutil.CommonUtil;

public class CatchbdTop {

	private static final String bdURL = CommonUtil.GRAB_LOGINURL;// "http://top.baidu.com/buzz/top10.html"

	public static Map<String, String> CatchHtml() {
		Map<String, String> hashmap = new HashMap<String, String>();// rz add
		try {
			URL url = new URL(bdURL);
			InputStreamReader isr = new InputStreamReader(url.openStream(), "GBK");
			BufferedReader br = new BufferedReader(isr);
			String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容
			// 定义2个正则 用于匹配我们需要的数据
			String RealHhot = "<a.class=\"list-title\".*";
			String clickRate = "<span.class=\"icon.{5}\">\\d+</span>";

			// 创建一个CatchMethod类的对象 gMethod
			CatchMethod gMethod = new CatchMethod();
			// 开始读取数据 如果读到的数据不为空 则往里面读
			List<String> key_list=new ArrayList<>();
			List<String> count_list=new ArrayList<>();
			while ((strRead = br.readLine()) != null) {
				/**
				 * 用于捕获时事热点RealHhot
				 */
				String strGet = gMethod.regularGroup(RealHhot, strRead);
				String s = "";
				if (!strGet.equals("") && !strGet.equals("")) {
					// 这里同样用到了substring方法 来剔除'<' 和 "</a>" 标签
					strGet = strGet.substring(0, strGet.indexOf("</a>"));
					s = strGet.replaceAll("<a.class=\"list-title\".*\">", "");
					 //System.out.println("实时热点:" + s);
					key_list.add(s);
				}
				/**
				 * 用于捕获点击量clickRate
				 */
				String count = "";
				String strGet_count = gMethod.regularGroup(clickRate, strRead);
				if (!strGet_count.equals("")) {
					strGet_count = strGet_count.substring(0, strGet_count.indexOf("</span>"));
					count = strGet_count.replaceAll("<span.class=\"icon.{5}\">", "");
					//System.out.println("点击量:" + count);
					count_list.add(count);
				}				
			}
			if(key_list.size()>0 && key_list.size()==count_list.size()){
				int n=key_list.size();
				for (int i = 0; i < n; i++) {
					if(!key_list.get(i).isEmpty() && !count_list.get(i).isEmpty()){
						
						hashmap.put(key_list.get(i), count_list.get(i));
					}
				}
			}
			// 当读完数据后 关闭 BufferReader
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return hashmap;
	}

	public static void main(String[] args) {
		CatchbdTop dd = new CatchbdTop();
		Map<String, String> hashmap1 = new HashMap<String, String>();
		hashmap1 = dd.CatchHtml();
		Iterator<Entry<String, String>> entryS = hashmap1.entrySet().iterator();
		while (entryS.hasNext()) {
			// System.out.println("Key: "+entryS.next().getKey());//同样这样key 和
			// value 不能同时输出，因为少一半
			Map.Entry<String, String> entry = entryS.next();
			System.out.println("key:" + entry.getKey() + " value: " + entry.getValue());
		}
	}

}
