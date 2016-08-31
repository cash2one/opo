package com.darg.opo.action;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.darg.opo.pojo.TNetAddr;
import com.darg.opo.commutil.CommonUtil;
import com.darg.opo.pojo.T360topKeyWord;
import com.darg.opo.pojo.TAddrTemp;
import com.darg.opo.pojo.TBaiduNewsSerch;
import com.darg.opo.pojo.TNetResult;
import com.darg.opo.pojo.TPublicSentimentHead;
import com.darg.opo.pojo.TPublicSentimentHresult;
import com.darg.opo.pojo.TPublicSentimentKeyword;
import com.darg.opo.pojo.TPublicSentimentRecent;
import com.darg.opo.pojo.TPublicSentimentResult;
import com.darg.opo.pojo.TPublicSentimentRresult;
import com.darg.opo.pojo.TTopKeyWord;
import com.darg.opo.service.ICommonServer;
import com.opensymphony.xwork2.ActionSupport;

public class OpoMangeAction extends ActionSupport {
	private ICommonServer commonService;
	private String g_message;// 公用返回消息（success,error,nodata,其他）
	private String g_serchValue;// 公用搜索文本框值
	private List<TPublicSentimentHead> listUnionKeyword;// 组合关键词list
	private TPublicSentimentHead unionKeywordOne;// 组合关键词one
	private List<TTopKeyWord> listTTopKeyWord;// 网络热点list
	private Integer cindex;// 最新关键词Id
	private List<TPublicSentimentRecent> listNewkeywords;// 最新关键词list
	private List<TPublicSentimentRecent> listSingNewkeywords;// 单个最新关键词list
	private List<T360topKeyWord> listT360topKeyWords;// 360热点List
	private List<TPublicSentimentResult> listAlarm;// 当日预警List
	private List<TPublicSentimentResult> listLookAlarm; // 查看当日预警List
	private List<TNetResult> listNetAlarm;//当日定网预警List

	private String Newkeyword;// 修改最新关键词
	private List<TNetAddr> listNetAddr;// 最新网址list
	private String url;// 修改网址
	private String g_id;// 公用Id
	private String g_size;// 公用size
	private String head;// 组合关键字段

	// TPublicSentimentKeyword表格的相关变量

	private String keyword;
	private Timestamp cTime;

	// 创建敏感词关键表格所需要的
	private List<TPublicSentimentKeyword> tKeywordList;
	private TPublicSentimentKeyword tkeyword;
	private String keyword_id;

	// 查询一段时间内的最新消息 所需变量
	private String RResultDate;
	private List<TPublicSentimentRresult> rresultList;
	private List<TBaiduNewsSerch> tBaiduNewsSerchsList;// rz add

	// 查询一段时间内的往日预警 所需变量
	private String HResultDate;
	private List<TPublicSentimentHresult> hresultList;

	// 查询一段时间内的往日预警 所需变量
	private String topKeywordDate;
	private List<TTopKeyWord> topKeywordList;
	// 查询一段时间内的定网预警 所需变量
	private List<TNetResult> netAddrWarnList;// 定网预警List rz add
	private List<TAddrTemp> addrTempsList;// 舆情热词地球

	/**
	 * 测试
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/12
	 */
	public String testAction() {
		try {
			g_message = "success";

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 查询组合关键词list
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/15
	 */
	public String serchUnionKeywordListFy() {
		try {
			listUnionKeyword = commonService.serchUnionKeywordListFy(g_serchValue);
			if (listUnionKeyword.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 查询单个组合关键词
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/24
	 */
	public String findUnionKeyword() {
		try {
			unionKeywordOne = commonService.findUnionKeywordByCindex(Integer.parseInt(g_id));
			if (unionKeywordOne != null && !unionKeywordOne.getHead().isEmpty()) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 单个组合关键词修改
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/25
	 */
	public String modify_unionKeyword() {
		try {
			if (g_id != null && !g_id.isEmpty()) {
				g_message = commonService.tranModifyUnionKeyword(Integer.parseInt(g_id), head);
			} else {
				g_message = "error";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 添加保存组合关键词
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/25
	 */
	public String save_unionKeyword() {
		try {
			if (head != null && !head.isEmpty()) {
				g_message = commonService.tranSaveUnionKeyword(head);
			} else {
				g_message = "error";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 删除单个组合关键词
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/26
	 */
	public String delete_unionKeyword() {
		try {
			if (g_id != null && !g_id.isEmpty()) {
				g_message = commonService.tranDeleteUnionKeyword(Integer.parseInt(g_id));
			} else {
				g_message = "error";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 百度热点list查询通过当天的时间
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/20
	 */
	public String findBaiduHotByTime() {
		try {
			listTTopKeyWord = commonService.findBaiduHotByTime(g_serchValue);
			if (listTTopKeyWord.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 网络热点top50list查询通时间
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/20
	 */
	public String findBaiduHot50ByTime() {
		try {
			listTTopKeyWord = commonService.findBaiduHot50ByTime(g_serchValue);
			if (listTTopKeyWord.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 网络热点Hot搜索排名前面list查询通过当天一天时间时间
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/27
	 */
	public String findBauduHotG_sizeByTime() {
		try {
			listTTopKeyWord = commonService.findBauduHotG_sizeByTime(g_size, g_serchValue);
			if (listTTopKeyWord.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 360新闻墙网络热点Hot随机20条查询通过当天一天时间时间
	 * 
	 * @author renzhong
	 * @dateTime 2015/08/27
	 */
	public String find360HotG_sizeByTime() {
		try {
			listT360topKeyWords = commonService.find360HotG_sizeByTime(g_size);
			if (listT360topKeyWords.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 舆情热词地球
	 * 
	 * @author renzhong
	 * @dateTime 2015/09/08
	 */
	public String findEarthHotWordG_size() {
		try {
			addrTempsList = commonService.findEarthHotWordG_size(g_size);
			if (addrTempsList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 定网预警查询通过时间
	 * 
	 * @author renzhong
	 * @dateTime 2015/09/08
	 */
	public String findNetAddrsWarnG_sizeByTwoTime() {
		try {
			// 截取日期字符串并转换
			String topKeywordDate01 = topKeywordDate.replace("一", "—");
			String[] time = topKeywordDate01.split("—");
			String[] time01 = time[0].split("/");
			String[] time02 = time[1].split("/");
//			int time020 = Integer.parseInt(time02[2]) + 1;
//			time02[2] = String.valueOf(time020);
			String m01 = time01[1], m02 = time02[1], d01 = time01[2], d02 = time02[2];
			if (time01[1].length() <= 1) {
				m01 = "0" + time01[1];
			}
			if (time01[2].length() <= 1) {
				d01 = "0" + time01[2];
			}
			if (time02[1].length() <= 1) {
				m02 = "0" + time02[1];
			}
			if (time02[2].length() <= 1) {
				d02 = "0" + time02[2];
			}

			String ttime01 = time01[0] + m01 + d01+"000000";
			String ttime02 = time02[0] + m02 + d02+"235959";
			netAddrWarnList = commonService.findNetAddrsWarnG_sizeByTwoTime(ttime01, ttime02);
			if (netAddrWarnList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 加载敏感词列表
	 * 
	 * @author zhengshuai
	 * @param
	 * @dateTime 2015/08/14
	 */

	public String findKeywordList() {
		try {
			tKeywordList = commonService.findKeyword();
			if (tKeywordList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}
		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 敏感词单个删除
	 * 
	 * @author zhengshuai
	 * @param keyword_id
	 * @dateTime 2015/08/14
	 */
	public String keyword_Delete() {
		try {
			if (keyword_id != null) {
				commonService.keyword_Delete(keyword_id);
				g_message = "success";
			} else {
				g_message = "find no id";
			}
		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 敏感词单个列表查看
	 * 
	 * @author zhengshuai
	 * @param keyword_id
	 * @return keywordList
	 * @dataTime 2015/08/14
	 */
	public String keywordList_look() {
		try {
			tkeyword = commonService.keywordList_look(keyword_id);
			g_message = "success";
		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 添加单个敏感词
	 * 
	 * @author zhengshuai
	 * @param
	 * @dataTime 2015/08/15
	 */
	public String keyword_add() {
		try {

			TPublicSentimentKeyword keyword_class = new TPublicSentimentKeyword();

			// int cindex =Integer.parseInt(keyword_id);
			// keyword_class.setCindex(cindex);

			keyword_class.setKeyWord(keyword);

			// 获取当前系统时间（Timestamp类）
			Date date = new Date();
			cTime = new Timestamp(System.currentTimeMillis());

			keyword_class.setCtime(cTime);
			g_message = commonService.keyword_add(keyword_class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 修改单个敏感词
	 * 
	 * @author zhengshuai
	 * @param
	 * @dataTime 2015/08/15
	 */
	public String keyword_modify() {
		try {
			if (keyword_id != null) {
				// 查询
				TPublicSentimentKeyword keyword_class = new TPublicSentimentKeyword();
				keyword_class = commonService.keywordList_look(keyword_id);
				// 修改
				keyword_class.setKeyWord(keyword);
				commonService.keyword_modify(keyword_class);

				g_message = "success";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 模糊查询敏感词
	 * 
	 * @author zhengshuai
	 * @dateTime 2015/08/22
	 */
	public String findVagueKeywords() {
		try {

			tKeywordList = commonService.findVagueKeywords(keyword);

			if (tKeywordList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 按时间段搜寻最新消息
	 * 
	 * @author zhengshuai
	 * @dateTime 2015/08/22
	 */
	public String findRResult() {
		try {

			// 截取日期字符串并转换
			String RResultDate01 = RResultDate.replace("一", "—");
			String[] time = RResultDate01.split("—");
			String[] time01 = time[0].split("/");
			String[] time02 = time[1].split("/");
//			int time020 = Integer.parseInt(time02[2]) + 1;
//			time02[2] = String.valueOf(time020);
			String m01 = time01[1], m02 = time02[1], d01 = time01[2], d02 = time02[2];
			if (time01[1].length() <= 1) {
				m01 = "0" + time01[1];
			}
			if (time01[2].length() <= 1) {
				d01 = "0" + time01[2];
			}
			if (time02[1].length() <= 1) {
				m02 = "0" + time02[1];
			}
			if (time02[2].length() <= 1) {
				d02 = "0" + time02[2];
			}

			String ttime01 = time01[0] + m01 + d01+"000000";
			String ttime02 = time02[0] + m02 + d02+"235959";
			rresultList = commonService.findRResult(ttime01, ttime02);

			if (rresultList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 百度新闻搜索最新消息按照一段日期查询
	 * 
	 * @author zhengshuai
	 * @dateTime 2015/08/22
	 */
	public String findBaiduNewsSerch() {
		try {
			// 截取日期字符串并转换
			String RResultDate01 = RResultDate.replace("一", "—");
			String[] time = RResultDate01.split("—");
			String[] time01 = time[0].split("/");
			String[] time02 = time[1].split("/");
//			int time020 = Integer.parseInt(time02[2]) + 1;
//			time02[2] = String.valueOf(time020);
			String m01 = time01[1], m02 = time02[1], d01 = time01[2], d02 = time02[2];
			if (time01[1].length() <= 1) {
				m01 = "0" + time01[1];
			}
			if (time01[2].length() <= 1) {
				d01 = "0" + time01[2];
			}
			if (time02[1].length() <= 1) {
				m02 = "0" + time02[1];
			}
			if (time02[2].length() <= 1) {
				d02 = "0" + time02[2];
			}

			String ttime01 = time01[0] + m01 + d01+"000000";
			String ttime02 = time02[0] + m02 + d02+"235959";
			tBaiduNewsSerchsList = commonService.findBaiduNewsSerch(ttime01, ttime02);
			if (tBaiduNewsSerchsList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 按时间段搜寻往日预警
	 * 
	 * @author zhengshuai
	 * @dateTime 2015/09/01
	 */
	public String findHResult() {
		try {

			// 截取日期字符串并转换
			String HResultDate01 = HResultDate.replace("一", "—");
			String[] time = HResultDate01.split("—");
			String[] time01 = time[0].split("/");
			String[] time02 = time[1].split("/");
//			int time020 = Integer.parseInt(time02[2]) + 1;
//			time02[2] = String.valueOf(time020);
			
			String m01 = time01[1], m02 = time02[1], d01 = time01[2], d02 = time02[2];
			if (time01[1].length() <= 1) {
				m01 = "0" + time01[1];
			}
			if (time01[2].length() <= 1) {
				d01 = "0" + time01[2];
			}
			if (time02[1].length() <= 1) {
				m02 = "0" + time02[1];
			}
			if (time02[2].length() <= 1) {
				d02 = "0" + time02[2];
			}	
			String ttime01 = time01[0] + m01 + d01+"000000";
			String ttime02 = time02[0] + m02 + d02+"235959";
			hresultList = commonService.findHResult(ttime01, ttime02);

			if (hresultList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 按时间段搜寻网络热点
	 * 
	 * @author zhengshuai
	 * @dateTime 2015/09/02
	 */
	public String findTopKeyword() {
		try {

			// 截取日期字符串并转换
			String topKeywordDate01 = topKeywordDate.replace("一", "—");
			String[] time = topKeywordDate01.split("—");
			String[] time01 = time[0].split("/");
			String[] time02 = time[1].split("/");
//			int time020 = Integer.parseInt(time02[2]) + 1;
//			time02[2] = String.valueOf(time020);
			String m01 = time01[1], m02 = time02[1], d01 = time01[2], d02 = time02[2];
			if (time01[1].length() <= 1) {
				m01 = "0" + time01[1];
			}
			if (time01[2].length() <= 1) {
				d01 = "0" + time01[2];
			}
			if (time02[1].length() <= 1) {
				m02 = "0" + time02[1];
			}
			if (time02[2].length() <= 1) {
				d02 = "0" + time02[2];
			}			
			String ttime01 = time01[0] + m01 + d01+"000000";
			String ttime02 = time02[0] + m02 + d02+"235959";

			topKeywordList = commonService.findTopKeyword(ttime01, ttime02);

			if (topKeywordList.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 查找所有最新关键字
	 * 
	 * @author zufei
	 * @dateTime 2015/08/15
	 */
	public String findNewkeywords() {
		try {

			listNewkeywords = commonService.findAllNewkeywords(Newkeyword);

			if (listNewkeywords.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 查找单个最新关键字
	 * 
	 * @author zufei
	 * @dateTime 2015/08/15
	 */
	public String findSingNewkeywords() {
		try {

			listSingNewkeywords = commonService.findSinglekeywords(cindex);

			if (listSingNewkeywords.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 修改最新关键字
	 * 
	 * @author zufei
	 * @dateTime 2015/08/16
	 */
	public String modifykeywordInfo() {

		try {

			if (cindex != null) {
				commonService.tranModifykeywordInfo(Newkeyword, cindex);
				;
				g_message = "success";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 删除最新关键字
	 * 
	 * @author zufei
	 * @dateTime 2015/08/16
	 */
	public String DeleteNewkeywords() {

		try {

			if (cindex != null) {
				commonService.tranDeletekeywordInfo(cindex);
				;
				g_message = "success";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 添加最新关键字
	 * 
	 * @author zufei
	 * @dateTime 2015/08/16
	 */
	public String addnewkeywordInfo() {

		try {

			if (Newkeyword != null) {
				commonService.tranSavenewkeywordInfo(Newkeyword);
				;
				g_message = "success";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 查询当日预警、定网预警
	 * 
	 * @author zufei
	 * @dateTime 2015/09/07
	 */
	public String findAlarmInfo() {

		try {

			listAlarm = commonService.findAlarmInfo(g_size);
			listNetAlarm = commonService.findNetAlarmInfo();

			if (listAlarm.size() > 0 || listNetAlarm.size()>0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 修改当日预警
	 * 
	 * @author zufei
	 * @dateTime 2015/09/07
	 */
	public String modifyAlarmInfo() {

		try {

			commonService.tranModifyAlarmInfo(g_size);
			g_message = "success";

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 查看查询当日预警
	 * 
	 * @author zufei
	 * @dateTime 2015/09/08
	 */
	public String findAlarmLookInfo() {

		try {

			listLookAlarm = commonService.findAlarmLookInfo();

			if (listLookAlarm.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}
	// ----------------------------------------------------------------
	/**
	 * 查找所有网址
	 * 
	 * @author yyz
	 * @dateTime 2015/08/15
	 */
	public String findNetAddrListFy() {
		try {
			listNetAddr = commonService.findNetAddrListFy(g_serchValue);
			if (listNetAddr.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加最新网址
	 * 
	 * @author yyz
	 * @dateTime 2015/08/25
	 */
	public String save_netAddrInfo() {

		try {
			if (url != null) {
				g_message = commonService.tranSaveTNetAddr(url);
			} else {
				g_message = "error";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * 查找单个最新网址
	 * 
	 * @author yyz
	 * @dateTime 2015/08/25
	 */
	public String findSingNetAddr() {
		try {

			listNetAddr = commonService.findSingleNetAddr(g_id);

			if (listNetAddr.size() > 0) {
				g_message = "success";
			} else {
				g_message = "nodata";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改保存单个最新网址
	 * 
	 * @author yyz
	 * @dateTime 2015/08/25
	 */
	public String modify_netAddr() {
		try {
			if (g_id != null && !g_id.isEmpty() && url != null && !url.isEmpty()) {
				g_message = commonService.tranModifyNetAddrOne(url, Integer.parseInt(g_id));
			} else {
				g_message = "error";
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除最新网址
	 * 
	 * @author yyz
	 * @dateTime 2015/08/25
	 */
	public String deleteNetAddrOne() {

		try {

			if (g_id != null && !g_id.isEmpty()) {
				g_message = commonService.tranDeleteNetAddrOne(Integer.parseInt(g_id));
				;
			} else {
				g_message = "error";
			}

		} catch (Exception e) {
			g_message = "error";
			e.printStackTrace();
		}
		return "success";

	}

	/* service 这里只能有set开始(由于spring的原因注入导致) */
	/*
	 * public ICommonServer getCommonService() { return commonService; }
	 */

	public List<TNetAddr> getListNetAddr() {
		return listNetAddr;
	}

	public void setCommonService(ICommonServer commonService) {
		this.commonService = commonService;
	}

	public String getG_message() {
		return g_message;
	}

	/*
	 * public void setG_message(String g_message) { this.g_message = g_message;
	 * }
	 */

	public void setG_serchValue(String g_serchValue) {
		this.g_serchValue = g_serchValue;
	}

	public List<TPublicSentimentHead> getListUnionKeyword() {
		return listUnionKeyword;
	}

	public List<TTopKeyWord> getListTTopKeyWord() {
		return listTTopKeyWord;
	}

	public List<TPublicSentimentKeyword> gettKeywordList() {
		return tKeywordList;
	}

	public String getKeyword_id() {
		return keyword_id;
	}

	public void setKeyword_id(String keyword_id) {
		this.keyword_id = keyword_id;
	}

	/*
	 * public String getKeyword() { return keyword; }
	 */

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/*
	 * public Timestamp getcTime() { return cTime; }
	 */

	public void setcTime(Timestamp cTime) {
		this.cTime = cTime;
	}

	public TPublicSentimentKeyword getTkeyword() {
		return tkeyword;
	}

	/*
	 * public void setTkeyword(TPublicSentimentKeyword tkeyword) { this.tkeyword
	 * = tkeyword; }
	 */

	/*
	 * public void setListTTopKeyWord(List<TTopKeyWord> listTTopKeyWord) {
	 * this.listTTopKeyWord = listTTopKeyWord; }
	 */

	public Integer getCindex() {
		return cindex;
	}

	public void setCindex(Integer cindex) {
		this.cindex = cindex;
	}

	public List<TPublicSentimentRecent> getListNewkeywords() {
		return listNewkeywords;
	}

	public List<TPublicSentimentRecent> getListSingNewkeywords() {
		return listSingNewkeywords;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNewkeyword() {
		return Newkeyword;
	}

	public void setNewkeyword(String newkeyword) {
		Newkeyword = newkeyword;
	}

	public void setRResultDate(String rResultDate) {
		RResultDate = rResultDate;
	}

	public TPublicSentimentHead getUnionKeywordOne() {
		return unionKeywordOne;
	}

	public List<TPublicSentimentRresult> getRresultList() {
		return rresultList;
	}

	public String getRResultDate() {
		return RResultDate;
	}

	public void setRresultList(List<TPublicSentimentRresult> rresultList) {
		this.rresultList = rresultList;
	}

	/*
	 * public void setUnionKeywordOne(TPublicSentimentHead unionKeywordOne) {
	 * this.unionKeywordOne = unionKeywordOne; }
	 */
	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public void setHead(String head) {
		this.head = head;
	}

	/*
	 * public String getG_size() { return g_size; }
	 */
	public void setG_size(String g_size) {
		this.g_size = g_size;
	}

	public List<T360topKeyWord> getListT360topKeyWords() {
		return listT360topKeyWords;
	}

	public String getHResultDate() {
		return HResultDate;
	}

	public void setHResultDate(String hResultDate) {
		HResultDate = hResultDate;
	}

	public List<TPublicSentimentHresult> getHresultList() {
		return hresultList;
	}

	public void setHresultList(List<TPublicSentimentHresult> hresultList) {
		this.hresultList = hresultList;
	}

	public String getTopKeywordDate() {
		return topKeywordDate;
	}

	public void setTopKeywordDate(String topKeywordDate) {
		this.topKeywordDate = topKeywordDate;
	}

	public List<TTopKeyWord> getTopKeywordList() {
		return topKeywordList;
	}

	public void setTopKeywordList(List<TTopKeyWord> topKeywordList) {
		this.topKeywordList = topKeywordList;
	}

	public List<TBaiduNewsSerch> gettBaiduNewsSerchsList() {
		return tBaiduNewsSerchsList;
	}

	/*
	 * public void settBaiduNewsSerchsList(List<TBaiduNewsSerch>
	 * tBaiduNewsSerchsList) { this.tBaiduNewsSerchsList = tBaiduNewsSerchsList;
	 * }
	 */
	public List<TPublicSentimentResult> getListAlarm() {
		return listAlarm;
	}

	/*
	 * public void setListAlarm(List<TPublicSentimentResult> listAlarm) {
	 * this.listAlarm = listAlarm; }
	 */
	public List<TNetResult> getNetAddrWarnList() {
		return netAddrWarnList;
	}

	/*
	 * public void setNetAddrWarnList(List<TNetResult> netAddrWarnList) {
	 * this.netAddrWarnList = netAddrWarnList; }
	 */

	public List<TAddrTemp> getAddrTempsList() {
		return addrTempsList;
	}

	/*
	 * public void setAddrTempsList(List<TAddrTemp> addrTempsList) {
	 * this.addrTempsList = addrTempsList; }
	 */
	public List<TPublicSentimentResult> getListLookAlarm() {
		return listLookAlarm;
	}

	public List<TNetResult> getListNetAlarm() {
		return listNetAlarm;
	}

	/*public void setListLookAlarm(List<TPublicSentimentResult> listLookAlarm) {
		this.listLookAlarm = listLookAlarm;
	}*/
	
}
