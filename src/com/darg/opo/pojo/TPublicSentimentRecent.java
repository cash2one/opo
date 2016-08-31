package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TPublicSentimentRecent entity. @author MyEclipse Persistence Tools
 */

public class TPublicSentimentRecent implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String keyWord;
	private Timestamp ctime;
	private String flag;

	// Constructors

	/** default constructor */
	public TPublicSentimentRecent() {
	}

	/** full constructor */
	public TPublicSentimentRecent(String keyWord, Timestamp ctime,String flag) {
		this.keyWord = keyWord;
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

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}