package com.darg.opo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map.Entry;

import com.darg.opo.ansj.count.MutableInteger;
import com.darg.opo.dao.TNetResultDAO;
import com.darg.opo.pojo.T360topKeyWord;
import com.darg.opo.pojo.TAddrTemp;
import com.darg.opo.pojo.TBaiduNewsSerch;
import com.darg.opo.pojo.TNetAddr;


import com.darg.opo.pojo.TNetResult;
import com.darg.opo.pojo.TPublicSentimentHresult;
import com.darg.opo.pojo.TPublicSentimentKeyword;
import com.darg.opo.pojo.TPublicSentimentHead;
import com.darg.opo.pojo.TPublicSentimentRecent;
import com.darg.opo.pojo.TPublicSentimentResult;
import com.darg.opo.pojo.TPublicSentimentRresult;
import com.darg.opo.pojo.TTopKeyWord;

public interface ICommonServer {

	/**
	 * 加载敏感词列表
	 * 
	 * @author zhengshuai
	 * @dataTime 2015/08/14
	 */
	public List<TPublicSentimentKeyword> findKeyword() throws Exception;

	/**
	 * 删除单个敏感词
	 * 
	 * @author zhengshuai
	 * @dataTime 2015/08/14
	 */
	public void keyword_Delete(String keyword_id) throws Exception;

	/**
	 * 查询单个敏感词
	 * 
	 * @author zhengshuai
	 * @dataTime 2015/08/14
	 * 
	 */
	public TPublicSentimentKeyword keywordList_look(String keyword_id) throws Exception;

	/**
	 * 添加单个敏感词
	 * 
	 * @author zhengshuai
	 * @dataTime 2015/08/15
	 */
	public String keyword_add(TPublicSentimentKeyword keyword) throws Exception;

	/**
	 * 修改单个敏感词
	 * 
	 * @author zhengshuai
	 * @dataTime 2015/08/15
	 */
	public void keyword_modify(TPublicSentimentKeyword keyword) throws Exception;

	/**
	 * 模糊查询敏感词
	 * 
	 * @author zhengshuai
	 * @dataTime 2015/08/22
	 */
	public List<TPublicSentimentKeyword> findVagueKeywords (String keyword)throws  Exception;
	
	/**
	 * 查询一段时间内的最新消息
	 * @author zhengshuai
	 * @dataTime 2015/08/26
	 */
	public List<TPublicSentimentRresult> findRResult (String dateTime01, String dateTime02)throws  Exception;
	/**
	 * 百度新闻搜索最新消息按照一段日期查询
	 * @author zhengshuai
	 * @dataTime 2015/08/26
	 */
	public List<TBaiduNewsSerch> findBaiduNewsSerch (String dateTime01, String dateTime02)throws  Exception;
	/**
	 * 查询一段时间内的往日预警
	 * @author zhengshuai
	 * @dataTime 2015/09/01
	 */
	public List<TPublicSentimentHresult> findHResult (String dateTime01, String dateTime02)throws  Exception;
	/**
	 * 查询一段时间内的网络热点
	 * @author zhengshuai
	 * @dataTime 2015/09/02
	 */
	public List<TTopKeyWord> findTopKeyword (String dateTime01, String dateTime02)throws  Exception;

	/**
	 * 查询网络热点根据时间
	 * @author rz
	 * @return
	 */
	public List<TTopKeyWord> findListTTopKeyWordByTime() throws Exception;

	/**
	 * 查询组合关键词list
	 * 
	 * @author rz
	 * @return
	 */
	public List<TPublicSentimentHead> serchUnionKeywordListFy(String g_serchValue) throws Exception;

	/**
	 * 查询组合关键词One通过id
	 * 
	 * @author rz
	 * @return
	 */
	public TPublicSentimentHead findUnionKeywordByCindex(int cindex) throws Exception;

	/**
	 * 更改组合关键词
	 * 
	 * @author rz
	 * @param cindex
	 * @param head
	 * @return
	 * @throws Exception
	 */
	public String tranModifyUnionKeyword(int cindex, String head) throws Exception;

	/**
	 * 添加保存组合关键词
	 * 
	 * @author rz
	 * @param head
	 * @return
	 * @throws Exception
	 */
	public String tranSaveUnionKeyword(String head) throws Exception;
	/**
	 * 删除单个组合关键词
	 * 
	 * @author rz
	 * @param head
	 * @return
	 * @throws Exception
	 */
	public String tranDeleteUnionKeyword(int cindex) throws Exception;

	/**
	 * 百度热点list查询通过当天的时间
	 * 
	 * @author rz
	 * @return
	 */
	public List<TTopKeyWord> findBaiduHotByTime(String g_serchValue) throws Exception;
	/**
	 *  网络热点top50list查询通过时间
	 * @param g_serchValue
	 * @author rz
	 * @return
	 */
	public List<TTopKeyWord> findBaiduHot50ByTime(String g_serchValue) throws Exception;
	/**
	 *  网络热点Hot搜索排名前面list查询通过当天一天时间时间
	 * @author rz
	 * @param g_size
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TTopKeyWord> findBauduHotG_sizeByTime(String g_size,String type) throws Exception;
	/**
	 *  360网络热点Hot搜索排名前面list查询通过当天一天时间时间
	 * @author rz
	 * @param g_size
	 * @return
	 * @throws Exception
	 */
	public List<T360topKeyWord> find360HotG_sizeByTime(String g_size) throws Exception;
	/**
	 *  舆情热词地球
	 * @author rz
	 * @param g_size
	 * @return
	 * @throws Exception
	 */
	public List<TAddrTemp> findEarthHotWordG_size(String g_size) throws Exception;
	/**
	 *  定网预警查询通过时间
	 * @author rz
	 * @param g_size
	 * @param dateTime01
	 * @param dateTime02
	 * @return
	 * @throws Exception
	 */
	public List<TNetResult> findNetAddrsWarnG_sizeByTwoTime(String dateTime01, String dateTime02) throws Exception;
	/**
	 * 查询百度新闻搜索Bytitle
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public List<TBaiduNewsSerch> findBaiduNewsSerchsBytitleLike(String title)throws Exception;
	/**
	 * 删除百度新闻搜索
	 * @param tBaiduNewsSerch
	 * @throws Exception
	 */
	public void tranDeleteBaiduNewsSerch(TBaiduNewsSerch tBaiduNewsSerch)throws Exception;
	/**
	 * 保存百度新闻搜索
	 * @param tBaiduNewsSerch
	 * @throws Exception
	 */
	public void tranSaveBaiduNewsSerch(TBaiduNewsSerch tBaiduNewsSerch)throws Exception;
	/**
	 * 修改百度新闻搜索
	 * @param tBaiduNewsSerch
	 * @throws Exception
	 */
	public void tranModifyBaiduNewsSerch(TBaiduNewsSerch tBaiduNewsSerch)throws Exception;

	/**
	 * 查找所有最新关键字
	 * 
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	public List<TPublicSentimentRecent> findAllNewkeywords(String Newkeyword) throws Exception;

	/**
	 * 根据关键字Id查询单个最新关键字
	 * 
	 * @author zf
	 * @param cindex
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<TPublicSentimentRecent> findSinglekeywords(Integer cindex) throws Exception;

	/**
	 * 修改最新关键字
	 * 
	 * @author zf
	 * @param Newkeyword
	 * @param cindex
	 * @throws Exception
	 */
	public void tranModifykeywordInfo(String Newkeyword, Integer cindex) throws Exception;

	/**
	 * 删除最新关键字
	 * 
	 * @author zf
	 * @param
	 * @param cindex
	 * @throws Exception
	 */
	public void tranDeletekeywordInfo(Integer cindex) throws Exception;

	/**
	 * 添加最新关键字
	 * 
	 * @author zf
	 * @param newkeyword
	 * @param cindex
	 * @throws Exception
	 */
	public void tranSavenewkeywordInfo(String Newkeyword) throws Exception;
	
	/**
	 * 查询当日预警
	 * 
	 * @author zf
	 * @throws Exception
	 */
	public List<TPublicSentimentResult> findAlarmInfo(String g_size) throws Exception;
	
	/**
	 * 解除当日预警
	 * 
	 * @author zf
	 * @throws Exception
	 */
	public void tranModifyAlarmInfo(String g_size) throws Exception;
	
	/**
	 * 查看查询当日预警
	 * 
	 * @author zf
	 * @throws Exception
	 */
	public List<TPublicSentimentResult> findAlarmLookInfo()throws Exception;
	
	/**
	 * 查询定网预警
	 * 
	 * @author zf
	 * @throws Exception
	 */
	public List<TNetResult> findNetAlarmInfo() throws Exception;

	/**
	 * 查找所有网址
	 * 
	 * @author yyz
	 * @return
	 * @throws Exception
	 */
	public List<TNetAddr> findNetAddrListFy(String g_serchValue) throws Exception;

	/**
	 * 添加网址
	 * 
	 * @author yyz
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String tranSaveTNetAddr(String url) throws Exception;

	/**
	 * 查找单个网址
	 * 
	 * @author yyz
	 * @param cindex
	 * @return
	 * @throws Exception
	 */
	public List<TNetAddr> findSingleNetAddr(String cindex) throws Exception;

	/**
	 * 修改单个网址
	 * @author yyz
	 * @param Newkeyword
	 * @param cindex
	 * @throws Exception
	 */
	public String tranModifyNetAddrOne(String url, Integer cindex) throws Exception;
	/**
	 * 删除网址
	 * @author yyz
	 * @param
	 * @param cindex
	 * @throws Exception
	 */
	public String tranDeleteNetAddrOne(Integer cindex) throws Exception;
	/**
	 * zjy
	 * 保存热词
	 */
	public void tranSaveTAddrTemp(List<Entry<String, MutableInteger>> hotWordList);

	
	

	

}
