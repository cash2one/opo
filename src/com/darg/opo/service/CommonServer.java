package com.darg.opo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.darg.opo.action.threadBean.ThreadBaiduRecent;
import com.darg.opo.action.threadBean.ThreadKedaJiaowu;
import com.darg.opo.ansj.count.CountTool;
import com.darg.opo.ansj.count.MutableInteger;
import com.darg.opo.commutil.CommonUtil;
import com.darg.opo.dao.T360topKeyWordDAO;
import com.darg.opo.dao.TAddrTempDAO;
import com.darg.opo.dao.TBaiduNewsSerchDAO;
import com.darg.opo.dao.TNetAddrDAO;
import com.darg.opo.dao.TNetResultDAO;
import com.darg.opo.dao.TPublicSentimentHeadDAO;
import com.darg.opo.dao.TPublicSentimentHresultDAO;
import com.darg.opo.dao.TPublicSentimentKeywordDAO;
import com.darg.opo.dao.TPublicSentimentRecentDAO;
import com.darg.opo.dao.TPublicSentimentRresultDAO;
import com.darg.opo.dao.TPublicSentimentResultDAO;
import com.darg.opo.dao.TTopKeyWordDAO;
import com.darg.opo.htmlUtil.Catch360;
import com.darg.opo.htmlUtil.Catchbdnews;
import com.darg.opo.htmlUtil.Html;
import com.darg.opo.htmlUtil.HtmlBaiduNewsSerch;
import com.darg.opo.pojo.T360topKeyWord;
import com.darg.opo.pojo.TAddrTemp;
import com.darg.opo.pojo.TBaiduNewsSerch;
import com.darg.opo.pojo.TNetAddr;
import com.darg.opo.pojo.TNetResult;
import com.darg.opo.pojo.TPublicSentimentHead;
import com.darg.opo.pojo.TPublicSentimentHresult;
import com.darg.opo.pojo.TPublicSentimentKeyword;
import com.darg.opo.pojo.TPublicSentimentRecent;
import com.darg.opo.pojo.TPublicSentimentResult;
import com.darg.opo.pojo.TPublicSentimentRresult;
import com.darg.opo.pojo.TTopKeyWord;

public class CommonServer implements ICommonServer {
	private final Logger logger = Logger.getLogger(CommonServer.class);
	private TTopKeyWordDAO tTopKeyWordDAO;// 网络热点dao
	private TPublicSentimentKeywordDAO tSentimentKeywordDAO;// 敏感关键词
	private TPublicSentimentHeadDAO tSentimentHeadDAO;// 组合关键词
	private TNetAddrDAO tNetAddrDAO;// 定位地址
	private TPublicSentimentRecentDAO tSentimentRecentDAO;// 最新关键词
	private TPublicSentimentRresultDAO tSentimentRresultDAO;// 最新消息

	private T360topKeyWordDAO t360topKeyWordDAO;// 360网络热点 rz add
	private TNetResultDAO tNetResultDAO;// 定网预警结果 rz add
	private TPublicSentimentResultDAO todayWarnDAO;// 当日预警结果 rz add
	private TPublicSentimentHresultDAO oldWarnDAO;// 往日预警结果 rz add
	private TBaiduNewsSerchDAO tBaiduNewsSerchDAO;// 百度新闻搜索 rz add
	private TAddrTempDAO tOpoHotWordDAO;//舆情热词dao rz add
	/**
	 * 抓取百度top10
	 * 
	 * @author rz
	 */
	public void setGrapBaiduTopTask() {
		String[] params = { "雪花雨痕泪", "sr740139800@" };
		try {

			String html = Html.createHtml(CommonUtil.GRAB_LOGINURL, CommonUtil.GRAB_FORWARDURL, params);
			if (html != null && !"".equals(html)) {
				Document doc = Jsoup.parse(html);
				Elements table_s = doc.select("table.list-table");
				Elements tr = table_s.get(0).getElementsByTag("tr");
				List<TTopKeyWord> listTTopKeyWord = new ArrayList<>();
				Timestamp time = CommonUtil.getNowTime_tamp();
				for (Element tablerow : tr) {
					Elements e_keyword = tablerow.getElementsByClass("keyword");
					Elements e_last = tablerow.getElementsByClass("last");
					String keyWord = e_keyword.text();// 标题
					String count = e_last.text();// 搜素数
					if (keyWord != null && count != null && !keyWord.isEmpty() && !count.isEmpty()) {
						TTopKeyWord tTopKeyWord = new TTopKeyWord();
						keyWord = keyWord.replace("search", "");
						tTopKeyWord.setKeyWord(keyWord);
						tTopKeyWord.setCount(Integer.parseInt(count));
						tTopKeyWord.setType("1");
						tTopKeyWord.setCtime(time);
						listTTopKeyWord.add(tTopKeyWord);
					}
				}
				for (TTopKeyWord tTopKeyWord : listTTopKeyWord) {// 保存数据库
					tTopKeyWordDAO.save(tTopKeyWord);

				}
				// Elements linksElements =
				// doc.select("div#page>div#content>div#main>div.left>div#recommend>ul>li>a");
				// 以上代码的意思是 找id为“page”的div里面 id为“content”的div里面 id为“main”的div里面
				// class为“left”的div里面 id为“recommend”的div里面ul里面li里面a标签
				/*
				 * for (Element ele:linksElements) {
				 * 
				 * //String href = ele.attr("href"); String href=ele.html();
				 * System.out.println(href); }
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 抓取百度top
	 * 
	 * @author rz
	 */
	public void setGrapBaiduTop_cloundTask() {
		String[] params = { "雪花雨痕泪", "sr740139800@" };
		try {
			String html = Html.createHtml(CommonUtil.GRAB_LOGINURL_CLOUND, CommonUtil.GRAB_FORWARDURL_CLOUD, params);
			if (html != null && !"".equals(html)) {
				Document doc = Jsoup.parse(html);
				Elements ul_s = doc.select("ul.item-list");
				List<TTopKeyWord> listTTopKeyWord = new ArrayList<>();
				Timestamp time = CommonUtil.getNowTime_tamp();
				for (int i = 0; i < ul_s.size(); i++) {
					Elements li_s = ul_s.get(i).select("li");
					for (Element element : li_s) {
						Elements e_keyword = element.getElementsByClass("list-title");
						String keyWord = e_keyword.text();// 标题
						Elements e_count_fall = element.getElementsByClass("icon-fall");// 降
						Elements e_count_rise = element.getElementsByClass("icon-rise");// 升
						Elements e_count_fair = element.getElementsByClass("icon-fair");// 平
						String count = "";
						String count_fall = e_count_fall.text();// 搜素数
						String count_rise = e_count_rise.text();// 搜素数
						String count_fair = e_count_fair.text();// 搜素数
						if (!count_fall.isEmpty()) {
							count = count_fall;// 搜素数
						} else if (!count_rise.isEmpty()) {
							count = count_rise;// 搜素数
						} else {
							count = count_fair;// 搜素数
						}
						if (keyWord != null && count != null && !keyWord.isEmpty() && !count.isEmpty()) {
							TTopKeyWord tTopKeyWord = new TTopKeyWord();
							keyWord = keyWord.replace("search", "");
							tTopKeyWord.setKeyWord(keyWord);
							tTopKeyWord.setCount(Integer.parseInt(count));
							tTopKeyWord.setType(String.valueOf(i + 2));
							tTopKeyWord.setCtime(time);
							listTTopKeyWord.add(tTopKeyWord);
						}
					}
				}
				for (TTopKeyWord tTopKeyWord : listTTopKeyWord) {// 保存数据库
					tTopKeyWordDAO.save(tTopKeyWord);

				}
				/*
				 * for (TTopKeyWord tTopKeyWord : listTTopKeyWord) {
				 * System.out.println
				 * (tTopKeyWord.getKeyWord()+": "+tTopKeyWord.getCount
				 * ()+" type: "+tTopKeyWord.getType()); }
				 */

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 抓取360新闻墙top
	 * 
	 * @author rz
	 */
	public void setGrap360HotWallTop_Task() {

		try {
			List<T360topKeyWord> list = new ArrayList<>();
			list = Catch360.CatchRedian();
			HashMap<String, MutableInteger> counter = new HashMap<>();//计算热词用的map
			for (T360topKeyWord t360topKeyWord : list) {
				t360topKeyWordDAO.save(t360topKeyWord);
				CountTool.count(counter, t360topKeyWord);//计算热词并赋值入counter
			}
			List<Entry<String, MutableInteger>> sortResult = CountTool.sort(counter);
			//			for (Iterator iterator = sortResult.iterator(); iterator.hasNext();) {
			//				Entry<String, MutableInteger> entry = (Entry<String, MutableInteger>) iterator.next();
			//				System.out.println(entry.getKey()+"  "+entry.getValue());
			//			}
			tranSaveTAddrTemp(sortResult.subList(0, 45));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存预警热词
	 */
	@Override
	public void tranSaveTAddrTemp(List<Entry<String, MutableInteger>> hotWordList) {
		Timestamp ts = CommonUtil.getNowTime_tamp();
		for (Iterator iterator = hotWordList.iterator(); iterator.hasNext();) {
			Entry<String, MutableInteger> entry = (Entry<String, MutableInteger>) iterator.next();
			TAddrTemp t = new TAddrTemp(entry.getKey(), entry.getValue().getUrl(), ts);
			tOpoHotWordDAO.save(t);
		}
	}
	/**
	 * 抓取百度搜索根据最新关键词设置来抓取
	 * 
	 * @author rz
	 */
	@SuppressWarnings("unchecked")
	public void setGrapBaiduSerch_Task() {

		try {
			List<TPublicSentimentRecent> listRecentKeyWord = new ArrayList<>();// 最新关键词List
			listRecentKeyWord = tSentimentRecentDAO.findAll();
			for (TPublicSentimentRecent tPublicSentimentRecent : listRecentKeyWord) {
				// 开启线程
				ThreadBaiduRecent thread = new ThreadBaiduRecent();
				thread.settRecent(tPublicSentimentRecent);
				thread.start();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 往往日预警表里插入数据
	 * 
	 * @author rz
	 */
	public void setOldWarnResult_Task() {
		// 先根据敏感词
		List<TPublicSentimentKeyword> sentimentKeyWordList = new ArrayList<>();
		List<TTopKeyWord> tTopKeyWordList = null;
		List<TPublicSentimentHead> unionWordList = new ArrayList<>();
		String unionWord = "";
		String str = "";
		TPublicSentimentHresult oldWarn = null;
		List<TPublicSentimentHresult> listOldWarn = null;
		sentimentKeyWordList = tSentimentKeywordDAO.findAll();
		
		Timestamp time = CommonUtil.getNowTime_tamp();
		Map<String, String> hashmap = new HashMap<String, String>();
		for (TPublicSentimentKeyword Keyword : sentimentKeyWordList) {
			str = Keyword.getKeyWord();
			tTopKeyWordList = new ArrayList<>();
			tTopKeyWordList = tTopKeyWordDAO.findBauduHotFyByTime("1", str);
			if (tTopKeyWordList != null && tTopKeyWordList.size() > 0) {
				for (TTopKeyWord tTopKeyWord : tTopKeyWordList) {
					hashmap.put(tTopKeyWord.getKeyWord(), String.valueOf(tTopKeyWord.getCindex()));
				}

			}
		}
		// 组合敏感词+敏感词

		unionWordList = tSentimentHeadDAO.findAll();
		for (TPublicSentimentHead tPublicSentimentHead : unionWordList) {
			unionWord = tPublicSentimentHead.getHead();// 组合关键词
			for (TPublicSentimentKeyword Keyword : sentimentKeyWordList) {
				str = Keyword.getKeyWord();
				tTopKeyWordList = new ArrayList<>();
				tTopKeyWordList = tTopKeyWordDAO.findBauduHotFyByTime("1", unionWord, str);
				if (tTopKeyWordList != null && tTopKeyWordList.size() > 0) {
					for (TTopKeyWord tTopKeyWord : tTopKeyWordList) {
						hashmap.put(tTopKeyWord.getKeyWord(), String.valueOf(tTopKeyWord.getCindex()));
					}

				}
			}
		}
		// hashMap保存往日预警数据数据库
		Iterator<Entry<String, String>> entryS = hashmap.entrySet().iterator();
		while (entryS.hasNext()) {
			Map.Entry<String, String> entry = entryS.next();
			oldWarn = new TPublicSentimentHresult();
			listOldWarn = new ArrayList<>();
			// 先通过关键词查询
			listOldWarn = oldWarnDAO.findByPropertyKeyWordLike(entry.getKey());
			if (listOldWarn.size() > 0) {// 有

			} else {// 没有
				oldWarn.setKeyWord(entry.getKey());
				oldWarn.setFlag("1");// 正常
				oldWarn.setCtime(time);
				oldWarnDAO.save(oldWarn);// 保存
			}

		}

	}

	/**
	 * 往今日预警里插入数据
	 */
	public void setTodayWarnResult_Task() {
		// 先根据敏感词
		List<TPublicSentimentKeyword> sentimentKeyWordList = new ArrayList<>();// 敏感词
		List<TTopKeyWord> tTopKeyWordList = null;
		List<TPublicSentimentHead> unionWordList = new ArrayList<>();// 组合敏感词
		String unionWord = "";
		String str = "";
		TPublicSentimentResult todayWarn = null;
		List<TPublicSentimentResult> listTodayWarn = null;
		sentimentKeyWordList = tSentimentKeywordDAO.findAll();
		
		Timestamp time = CommonUtil.getNowTime_tamp();
		Map<String, Integer> hashmap = new HashMap<String, Integer>();
		for (TPublicSentimentKeyword Keyword : sentimentKeyWordList) {
			str = Keyword.getKeyWord();
			tTopKeyWordList = new ArrayList<>();
			tTopKeyWordList = tTopKeyWordDAO.findBauduHotFyByTime("1", str);			
			for (TTopKeyWord tTopKeyWord : tTopKeyWordList) {
				hashmap.put(tTopKeyWord.getKeyWord(), tTopKeyWord.getCount());
			}

			
		}
		// 组合敏感词+敏感词

		unionWordList = tSentimentHeadDAO.findAll();
		for (TPublicSentimentHead tPublicSentimentHead : unionWordList) {
			unionWord = tPublicSentimentHead.getHead();// 组合关键词
			for (TPublicSentimentKeyword Keyword : sentimentKeyWordList) {
				str = Keyword.getKeyWord();
				tTopKeyWordList = new ArrayList<>();
				tTopKeyWordList = tTopKeyWordDAO.findBauduHotFyByTime("1", unionWord, str);				
				for (TTopKeyWord tTopKeyWord : tTopKeyWordList) {
					hashmap.put(tTopKeyWord.getKeyWord(), tTopKeyWord.getCount());
				}				
			}
		}
		// hashMap保存往日预警数据数据库
		Iterator<Entry<String, Integer>> entryS = hashmap.entrySet().iterator();
		while (entryS.hasNext()) {
			Map.Entry<String, Integer> entry = entryS.next();
			todayWarn = new TPublicSentimentResult();
			listTodayWarn = new ArrayList<>();
			// 先通过关键词查询
			listTodayWarn=todayWarnDAO.findByPropertyKeyWordLike(entry.getKey());
			if(listTodayWarn.size()>0){//有
				
			}else{//新加
				todayWarn.setKeyWord(entry.getKey());
				todayWarn.setTodayCount(entry.getValue());
				todayWarn.setCtime(time);
				todayWarn.setFlag("1");
				todayWarnDAO.save(todayWarn);
			}

		}
	}

	/**
	 * 往定网预警里插入数据
	 * 
	 * @author rz
	 */
	public void setNetResult_Task() {
		// 先查询设置的定位网址，然后遍历查询百度news搜索表
		List<TNetAddr> listNetAddrs = new ArrayList<>();// 设置的定位网址list
		List<TBaiduNewsSerch> listBaiduNewsSerchs = null;// 百度news搜索list
		TNetResult tNetResultOne = null;// 定网预警one
		List<TNetResult> listNetResults = null;// 定网预警list
		listNetAddrs = tNetAddrDAO.findAll();
		for (TNetAddr tNetAddr : listNetAddrs) {
			listBaiduNewsSerchs = new ArrayList<>();
			listBaiduNewsSerchs = tBaiduNewsSerchDAO.findByNewsUrlLike(tNetAddr.getUrl());
			for (TBaiduNewsSerch tBaiduNewsSerch : listBaiduNewsSerchs) {
				// 先查询通过关键字，定网预警是否有
				listNetResults = new ArrayList<>();
				listNetResults = tNetResultDAO.findByProperty("keyWord", tBaiduNewsSerch.getTitle());
				if (listNetResults.size() > 0) {

				} else {// 新增
					tNetResultOne = new TNetResult();
					tNetResultOne.setKeyWord(tBaiduNewsSerch.getTitle());
					tNetResultOne.setUrl(tBaiduNewsSerch.getNewsUrl());
					tNetResultOne.setCtime(tBaiduNewsSerch.getCtime());
					tNetResultOne.setFlag("1");// 正常
					tNetResultDAO.save(tNetResultOne);
				}
			}

		}

	}

	/**
	 * 加载出敏感词列表
	 * 
	 * @author zhenghsuai
	 * 
	 * @return List<TPublicSentimentKeyword>
	 */

	public List<TPublicSentimentKeyword> findKeyword() {
		List<TPublicSentimentKeyword> keywordList = new ArrayList<TPublicSentimentKeyword>();
		keywordList = tSentimentKeywordDAO.findAll();
		return keywordList;

	}

	/**
	 * 删除单个敏感词
	 * 
	 * @author zhengshuai
	 * @param keyword_id
	 * @return
	 * @dataTime 2015/08/14
	 */

	public void keyword_Delete(String keyword_id) {
		int keyword_Id = Integer.parseInt(keyword_id);
		TPublicSentimentKeyword keyword = new TPublicSentimentKeyword();
		keyword = tSentimentKeywordDAO.findById(keyword_Id);
		tSentimentKeywordDAO.delete(keyword);
	}

	/**
	 * 查询单个敏感词列表
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/08/14
	 */

	public TPublicSentimentKeyword keywordList_look(String keyword_id) {
		int keyword_Id = Integer.parseInt(keyword_id);
		List<TPublicSentimentKeyword> keywordList = new ArrayList<TPublicSentimentKeyword>();
		TPublicSentimentKeyword keyword = new TPublicSentimentKeyword();
		keyword = tSentimentKeywordDAO.findById(keyword_Id);

		return keyword;
	}

	/**
	 * 添加敏感词
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/08/15
	 */
	public String keyword_add(TPublicSentimentKeyword keyword) {
		String info = "erro";
		try {
			tSentimentKeywordDAO.save(keyword);
			info = "success";
		} catch (Exception e) {
			info = "error";
			e.printStackTrace();
		}
		return info;

	}

	/**
	 * 修改敏感词
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/08/15
	 */
	public void keyword_modify(TPublicSentimentKeyword keyword) {
		tSentimentKeywordDAO.merge(keyword);

	}

	/**
	 * 模糊查询敏感词
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/08/22
	 */
	@Override
	public List<TPublicSentimentKeyword> findVagueKeywords(String keyword) throws Exception {

		List<TPublicSentimentKeyword> list = new ArrayList<>();
		list = tSentimentKeywordDAO.findSensitiveKeywords(keyword);
		return list;
	}

	/**
	 * 查询一段时间内的最新消息
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/08/26
	 */
	@Override
	public List<TPublicSentimentRresult> findRResult(String dateTime01, String dateTime02) throws Exception {

		List<TPublicSentimentRresult> list = new ArrayList<>();
		// List list01=new List;
		list = tSentimentRresultDAO.searchRResult(dateTime01, dateTime02);
		return list;
	}

	/**
	 * 查询一段时间内的最新消息
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/08/26
	 */
	@Override
	public List<TBaiduNewsSerch> findBaiduNewsSerch(String dateTime01, String dateTime02) throws Exception {

		List<TBaiduNewsSerch> list = new ArrayList<>();
		// List list01=new List;
		list = tBaiduNewsSerchDAO.findBaiduNewsSerch(dateTime01, dateTime02);
		return list;
	}

	/**
	 * 查询一段时间内的往日预警
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/09/01
	 */
	@Override
	public List<TPublicSentimentHresult> findHResult(String dateTime01, String dateTime02) throws Exception {

		List<TPublicSentimentHresult> list = new ArrayList<>();
		// List list01=new List;
		list = oldWarnDAO.searchHResult(dateTime01, dateTime02);
		return list;
	}

	/**
	 * 查询一段时间内的网络热点
	 * 
	 * @author zhengshuai
	 * @return
	 * @dataTime 2015/09/02
	 */
	@Override
	public List<TTopKeyWord> findTopKeyword(String dateTime01, String dateTime02) throws Exception {

		List<TTopKeyWord> list = new ArrayList<>();
		// List list01=new List;
		list = tTopKeyWordDAO.searchTopKeyword(dateTime01, dateTime02);
		return list;
	}

	/**
	 * 查询网络热点根据时间
	 * 
	 * @author rz
	 * @return
	 */
	@Override
	public List<TTopKeyWord> findListTTopKeyWordByTime() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询组合关键词list
	 * 
	 * @author rz
	 * @return
	 */
	@Override
	public List<TPublicSentimentHead> serchUnionKeywordListFy(String g_serchValue) throws Exception {
		List<TPublicSentimentHead> list = new ArrayList<>();
		list = tSentimentHeadDAO.serchUnionKeywordListFy(g_serchValue);
		return list;
	}

	/**
	 * 查询组合关键词One通过id
	 * 
	 * @author rz
	 * @return
	 */
	@Override
	public TPublicSentimentHead findUnionKeywordByCindex(int cindex) throws Exception {
		TPublicSentimentHead one = new TPublicSentimentHead();
		one = tSentimentHeadDAO.findById(cindex);
		return one;
	}

	/**
	 * 更改组合关键词
	 * 
	 * @author rz
	 * @param cindex
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@Override
	public String tranModifyUnionKeyword(int cindex, String head) throws Exception {
		TPublicSentimentHead one = new TPublicSentimentHead();
		one = tSentimentHeadDAO.findById(cindex);
		one.setHead(head);
		one.setCtime(CommonUtil.getNowTime_tamp());
		tSentimentHeadDAO.merge(one);
		return "success";
	}

	/**
	 * 添加保存组合关键词
	 * 
	 * @author rz
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@Override
	public String tranSaveUnionKeyword(String head) throws Exception {
		TPublicSentimentHead one = new TPublicSentimentHead();
		one.setHead(head);
		one.setCtime(CommonUtil.getNowTime_tamp());
		tSentimentHeadDAO.save(one);
		return "success";
	}

	/**
	 * 删除单个组合关键词
	 * 
	 * @author rz
	 * @param cindex
	 * @return
	 * @throws Exception
	 */
	@Override
	public String tranDeleteUnionKeyword(int cindex) throws Exception {
		TPublicSentimentHead one = new TPublicSentimentHead();
		one = tSentimentHeadDAO.findById(cindex);
		tSentimentHeadDAO.delete(one);
		return "success";
	}

	/**
	 * 百度热点list查询通过当天的时间
	 * 
	 * @author rz
	 ** @param g_serchValue
	 * @return
	 */
	@Override
	public List<TTopKeyWord> findBaiduHotByTime(String g_serchValue) throws Exception {
		List<TTopKeyWord> list = new ArrayList<>();
		list = tTopKeyWordDAO.findBaiduHotByTime(g_serchValue);

		return list;
	}

	/**
	 * 网络热点top50list查询通过时间
	 * 
	 * @author rz
	 ** @param g_serchValue
	 * @return
	 */
	@Override
	public List<TTopKeyWord> findBaiduHot50ByTime(String g_serchValue) throws Exception {
		List<TTopKeyWord> list = new ArrayList<>();
		list = tTopKeyWordDAO.findBaiduHot50ByTime(g_serchValue);

		return list;
	}

	/**
	 * 网络热点Hot搜索排名前面list查询通过当天一天时间时间
	 * 
	 * @author rz
	 * @param g_size
	 * @return
	 */
	@Override
	public List<TTopKeyWord> findBauduHotG_sizeByTime(String g_size, String type) throws Exception {
		List<TTopKeyWord> list = new ArrayList<>();
		list = tTopKeyWordDAO.findBauduHotG_sizeByTime(g_size, type);
		return list;
	}

	/**
	 * 360网络热点Hot搜索排名前面list查询通过当天一天时间时间
	 * 
	 * @author rz
	 * @param g_size
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<T360topKeyWord> find360HotG_sizeByTime(String g_size) throws Exception {
		List<T360topKeyWord> list = new ArrayList<>();
		list = t360topKeyWordDAO.find360HotG_sizeByTime(g_size);
		return list;
	}
	/**
	 *  舆情热词地球
	 * @author rz
	 * @param g_size
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<TAddrTemp> findEarthHotWordG_size(String g_size) throws Exception {
		List<TAddrTemp> list = new ArrayList<>();
		list = tOpoHotWordDAO.findEarthHotWordG_size(g_size);
		return list;
	}
	/**
	 *  定网预警查询通过时间
	 * @author rz
	 * @param g_size
	 * @param dateTime01
	 * @param dateTime02
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TNetResult> findNetAddrsWarnG_sizeByTwoTime(String dateTime01, String dateTime02) throws Exception {
		List<TNetResult> list = new ArrayList<>();
		list = tNetResultDAO.findNetAddrsWarnG_sizeByTwoTime(dateTime01,dateTime02);
		return list;
	}
	/**
	 * 查询百度新闻搜索Bytitle * @author rz
	 * 
	 * @param title
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TBaiduNewsSerch> findBaiduNewsSerchsBytitleLike(String title) throws Exception {
		List<TBaiduNewsSerch> list = new ArrayList<>();
		list = tBaiduNewsSerchDAO.findBytitleLike(title);
		return list;
	}

	/**
	 * 删除百度新闻搜索 * @author rz
	 * 
	 * @param tBaiduNewsSerch
	 * @throws Exception
	 */
	@Override
	public void tranDeleteBaiduNewsSerch(TBaiduNewsSerch tBaiduNewsSerch) throws Exception {
		tBaiduNewsSerchDAO.delete(tBaiduNewsSerch);
	}

	/**
	 * 保存百度新闻搜索 * @author rz
	 * 
	 * @param tBaiduNewsSerch
	 * @throws Exception
	 */
	@Override
	public void tranSaveBaiduNewsSerch(TBaiduNewsSerch tBaiduNewsSerch) {
		try {
			tBaiduNewsSerchDAO.save(tBaiduNewsSerch);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改百度新闻搜索 * @author rz
	 * 
	 * @param tBaiduNewsSerch
	 * @throws Exception
	 */
	@Override
	public void tranModifyBaiduNewsSerch(TBaiduNewsSerch tBaiduNewsSerch) throws Exception {
		tBaiduNewsSerchDAO.merge(tBaiduNewsSerch);
	}

	/**
	 * 查找所有最新关键字
	 * 
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TPublicSentimentRecent> findAllNewkeywords(String Newkeyword) throws Exception {

		List<TPublicSentimentRecent> list = new ArrayList<>();
		list = tSentimentRecentDAO.findNewkeywords(Newkeyword);
		return list;
	}

	/**
	 * 根据关键字Id查询单个最新关键字
	 * 
	 * @author zf
	 * @param cindex
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TPublicSentimentRecent> findSinglekeywords(Integer cindex) throws Exception {

		List<TPublicSentimentRecent> list = new ArrayList<>();
		list = tSentimentRecentDAO.findSinglekeywords(cindex);
		return list;
	}

	/**
	 * 修改最新关键字
	 * 
	 * @author zf
	 * @param keyword_modify
	 * @param cindex_modify
	 * @throws Exception
	 */
	@Override
	public void tranModifykeywordInfo(String Newkeyword, Integer cindex) throws Exception {
		// 先查询

		TPublicSentimentRecent tPublicSentimentRecent = new TPublicSentimentRecent();
		tPublicSentimentRecent = tSentimentRecentDAO.findById(cindex);
		// 更该
		tPublicSentimentRecent.setKeyWord(Newkeyword);
		tPublicSentimentRecent.setCtime(CommonUtil.getNowTime_tamp());
		tSentimentRecentDAO.merge(tPublicSentimentRecent);
	}

	/**
	 * 删除最新关键字
	 * 
	 * @author zf
	 * @param cindex
	 * @throws Exception
	 */
	@Override
	public void tranDeletekeywordInfo(Integer cindex) throws Exception {
		// 先查询

		TPublicSentimentRecent tPublicSentimentRecent = new TPublicSentimentRecent();
		tPublicSentimentRecent = tSentimentRecentDAO.findById(cindex);
		// 删除

		tSentimentRecentDAO.delete(tPublicSentimentRecent);
	}

	/**
	 * 添加最新关键字
	 * 
	 * @author zf
	 * @param Newkeyword
	 * @param cindex
	 * @throws Exception
	 */
	@Override
	public void tranSavenewkeywordInfo(String Newkeyword) throws Exception {

		TPublicSentimentRecent tPublicSentimentRecent = new TPublicSentimentRecent();

		tPublicSentimentRecent.setKeyWord(Newkeyword);
		tPublicSentimentRecent.setCtime(CommonUtil.getNowTime_tamp());
		tPublicSentimentRecent.setFlag("1");
		tSentimentRecentDAO.merge(tPublicSentimentRecent);
	}
	
	/**
	 * 查找当日预警
	 * 
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TPublicSentimentResult> findAlarmInfo(String g_size) throws Exception {

		List<TPublicSentimentResult> list = new ArrayList<>();
		list = todayWarnDAO.findAlarmInfo(g_size);
		return list;
	}
	/**
	 * 修改当日预警删除标示符
	 * 
	 * @author zf
	 * @throws Exception
	 */
	@Override
	public void tranModifyAlarmInfo(String g_size) throws Exception {
		// 先查询		
		List<TPublicSentimentResult> list = new ArrayList<>();
		List<TNetResult> listNet = new ArrayList<>();
		list = todayWarnDAO.findAlarmInfo(g_size);
		listNet = tNetResultDAO.findNetAlarmInfo();
		for (TPublicSentimentResult tPublicSentimentResult : list) {
			tPublicSentimentResult.setFlag("0");
			todayWarnDAO.merge(tPublicSentimentResult);
		}
		for (TNetResult netResult : listNet) {	
			netResult.setFlag("0");
			tNetResultDAO.merge(netResult);
		}
	}
	/**
	 * 查看查找当日预警
	 * 
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TPublicSentimentResult> findAlarmLookInfo() throws Exception {

		List<TPublicSentimentResult> list = new ArrayList<>();
		list = todayWarnDAO.findAlarmLookInfo();
		return list;
	}
	
	/**
	 * 查找定网预警
	 * 
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TNetResult> findNetAlarmInfo() throws Exception {

		List<TNetResult> list = new ArrayList<>();
		list = tNetResultDAO.findNetAlarmInfo();
		return list;
	}

	/**
	 * 查找网址
	 * 
	 * @author yyz
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TNetAddr> findNetAddrListFy(String g_serchValue) throws Exception {
		List<TNetAddr> list = new ArrayList<>();
		list = tNetAddrDAO.findNetAddrListFy(g_serchValue);
		return list;
	}

	/**
	 * 添加网址
	 * 
	 * @author yyz
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@Override
	public String tranSaveTNetAddr(String url) throws Exception {
		TNetAddr netAddr = new TNetAddr();
		netAddr.setUrl(url);
		netAddr.setCtime(CommonUtil.getNowTime_tamp());
		tNetAddrDAO.save(netAddr);

		return "success";
	}

	/**
	 * @author yyz
	 * @param cindex
	 * @return
	 * @throws Exception
	 * @Override
	 */
	public List<TNetAddr> findSingleNetAddr(String cindex) throws Exception {
		List<TNetAddr> list = new ArrayList<>();
		TNetAddr one = new TNetAddr();
		one = tNetAddrDAO.findById((Integer.parseInt(cindex)));
		list.add(one);
		return list;
	}

	/**
	 * 修改单个网址
	 * 
	 * @author yyz
	 * @param Newkeyword
	 * @param cindex
	 * @throws Exception
	 */
	@Override
	public String tranModifyNetAddrOne(String url, Integer cindex) throws Exception {
		TNetAddr one = new TNetAddr();
		one = tNetAddrDAO.findById(cindex);
		one.setUrl(url);
		one.setCtime(CommonUtil.getNowTime_tamp());
		tNetAddrDAO.merge(one);
		return "success";
	}

	/**
	 * 删除网址
	 * 
	 * @author yyz
	 * @param
	 * @param cindex
	 * @throws Exception
	 */
	@Override
	public String tranDeleteNetAddrOne(Integer cindex) throws Exception {
		TNetAddr one = new TNetAddr();
		one = tNetAddrDAO.findById(cindex);
		tNetAddrDAO.delete(one);
		return "success";
	}

	public TTopKeyWordDAO gettTopKeyWordDAO() {
		return tTopKeyWordDAO;
	}

	public void settTopKeyWordDAO(TTopKeyWordDAO tTopKeyWordDAO) {
		this.tTopKeyWordDAO = tTopKeyWordDAO;
	}

	public TPublicSentimentKeywordDAO gettSentimentKeywordDAO() {
		return tSentimentKeywordDAO;
	}

	public void settSentimentKeywordDAO(TPublicSentimentKeywordDAO tSentimentKeywordDAO) {
		this.tSentimentKeywordDAO = tSentimentKeywordDAO;
	}

	public TPublicSentimentHeadDAO gettSentimentHeadDAO() {
		return tSentimentHeadDAO;
	}

	public void settSentimentHeadDAO(TPublicSentimentHeadDAO tSentimentHeadDAO) {
		this.tSentimentHeadDAO = tSentimentHeadDAO;
	}

	public TNetAddrDAO gettNetAddrDAO() {
		return tNetAddrDAO;
	}

	public void settNetAddrDAO(TNetAddrDAO tNetAddrDAO) {
		this.tNetAddrDAO = tNetAddrDAO;
	}

	public TPublicSentimentRecentDAO gettSentimentRecentDAO() {
		return tSentimentRecentDAO;
	}

	public void settSentimentRecentDAO(TPublicSentimentRecentDAO tSentimentRecentDAO) {
		this.tSentimentRecentDAO = tSentimentRecentDAO;
	}

	public TPublicSentimentRresultDAO gettSentimentRresultDAO() {
		return tSentimentRresultDAO;
	}

	public void settSentimentRresultDAO(TPublicSentimentRresultDAO tSentimentRresultDAO) {
		this.tSentimentRresultDAO = tSentimentRresultDAO;
	}

	public T360topKeyWordDAO getT360topKeyWordDAO() {
		return t360topKeyWordDAO;
	}

	public void setT360topKeyWordDAO(T360topKeyWordDAO t360topKeyWordDAO) {
		this.t360topKeyWordDAO = t360topKeyWordDAO;
	}

	public TNetResultDAO gettNetResultDAO() {
		return tNetResultDAO;
	}

	public void settNetResultDAO(TNetResultDAO tNetResultDAO) {
		this.tNetResultDAO = tNetResultDAO;
	}

	public TPublicSentimentResultDAO getTodayWarnDAO() {
		return todayWarnDAO;
	}

	public void setTodayWarnDAO(TPublicSentimentResultDAO todayWarnDAO) {
		this.todayWarnDAO = todayWarnDAO;
	}

	public TPublicSentimentHresultDAO getOldWarnDAO() {
		return oldWarnDAO;
	}

	public void setOldWarnDAO(TPublicSentimentHresultDAO oldWarnDAO) {
		this.oldWarnDAO = oldWarnDAO;
	}

	public TBaiduNewsSerchDAO gettBaiduNewsSerchDAO() {
		return tBaiduNewsSerchDAO;
	}

	public void settBaiduNewsSerchDAO(TBaiduNewsSerchDAO tBaiduNewsSerchDAO) {
		this.tBaiduNewsSerchDAO = tBaiduNewsSerchDAO;
	}

	public TAddrTempDAO gettOpoHotWordDAO() {
		return tOpoHotWordDAO;
	}

	public void settOpoHotWordDAO(TAddrTempDAO tOpoHotWordDAO) {
		this.tOpoHotWordDAO = tOpoHotWordDAO;
	}


}
