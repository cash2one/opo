package com.darg.opo.htmlUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.darg.opo.commutil.CommonUtil;
import com.darg.opo.pojo.T360topKeyWord;
/**
 * 360新闻墙抓取
 * @author srrenyu
 *
 */
public class Catch360 {
	@SuppressWarnings("deprecation")
	public	static List CatchRedian() {
		@SuppressWarnings("resource")
		HttpClient httpClient = new DefaultHttpClient();
		Date date = new Date();
		double NowDateTime = Math.floor((date).getTime() / 18e5);
		List<T360topKeyWord> list360=new ArrayList<>();
		try {
			HttpGet httpGet = new HttpGet("http://sh.qihoo.com/api/hot_new.json?jsonp=1&callback=HotAjax.loadOk&type=day0&t=" + NowDateTime);
			// 执行 get 请求
			HttpResponse httpResponse = httpClient.execute(httpGet);

			// 获取响应实体
			HttpEntity httpEntity = httpResponse.getEntity();
			// 打印响应状态			
			if (httpEntity != null) {
				// 响应内容的长度
				long length = httpEntity.getContentLength();
				// 响应内容
				String content = EntityUtils.toString(httpEntity);
				String strGet1 = content.substring(16, content.indexOf("])"));
				String[] json = strGet1.split("},");
				Timestamp time=CommonUtil.getNowTime_tamp();
				for (int i = 0; i < json.length; i++) {
					JSONObject objJson = JSONObject.fromObject(json[i] + "}");
					String title = objJson.getString("title");
					String ltitle = objJson.getString("ltitle");
					String imgUrl = objJson.getString("img");
					String keyword = objJson.getString("keyword");
					String count = objJson.getString("id");
					T360topKeyWord t360topKeyWord=new T360topKeyWord();
					if (!title.isEmpty()) {//标题
						t360topKeyWord.setTitle(title);
					}else{
						continue;
					}
					if (!ltitle.isEmpty()) {//副标题
						t360topKeyWord.setLtitle(ltitle);
					}else{
						continue;
					}
					if (!imgUrl.isEmpty()) {//图片url
						t360topKeyWord.setImgUrl(imgUrl);
					}else{
						t360topKeyWord.setImgUrl("");
					}
					if (!keyword.isEmpty()) {//关键字
						t360topKeyWord.setKeyword(keyword);
					}else{
						t360topKeyWord.setKeyword("");
					}
					if (!count.isEmpty()) {//搜索数
						t360topKeyWord.setCount(Integer.parseInt(count));
					}else{
						t360topKeyWord.setCount(0);
					}
					String newsUrl="http://news.haosou.com/ns?q="+title+"&src=newsresou&tn=news";
					//http://news.haosou.com/ns?q=女用伟哥获准上市&src=newsresou&tn=news
					t360topKeyWord.setNewsUrl(newsUrl);
					t360topKeyWord.setCtime(time);
					list360.add(t360topKeyWord);
				}
			/*	for (T360topKeyWord t360topKeyWord : list360) {
					System.out.println(t360topKeyWord.getTitle()+"  :"+t360topKeyWord.getCount());
				}	*/		
				httpGet.abort();// 中断HTTP请求
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接，释放资源
			httpClient.getConnectionManager().shutdown();

		}
		return list360;
	}

	public static void main(String[] args) {
		Catch360 sInsert = new Catch360();
		sInsert.CatchRedian();
	}

}
