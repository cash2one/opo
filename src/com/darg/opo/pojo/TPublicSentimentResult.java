package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TPublicSentimentResult entity. @author MyEclipse Persistence Tools
 */

public class TPublicSentimentResult implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String keyWord;
	private Integer todayCount;
	private Timestamp ctime;
	private String flag;

	// Constructors

	/** default constructor */
	public TPublicSentimentResult() {
	}

	/** minimal constructor */
	public TPublicSentimentResult(Integer todayCount, String flag) {
		this.todayCount = todayCount;
		this.flag = flag;
	}

	/** full constructor */
	public TPublicSentimentResult(String keyWord, Integer todayCount, Timestamp ctime, String flag) {
		this.keyWord = keyWord;
		this.todayCount = todayCount;
		this.ctime = ctime;
		this.flag = flag;
	}

	// Property accessors

	public Integer getCindex() {
		return this.cindex;
	}

	public void setCindex(Integer cindex) {
		this.cindex = cindex;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getTodayCount() {
		return this.todayCount;
	}

	public void setTodayCount(Integer todayCount) {
		this.todayCount = todayCount;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}