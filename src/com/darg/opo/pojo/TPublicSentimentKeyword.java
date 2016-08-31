package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TPublicSentimentKeyword entity. @author MyEclipse Persistence Tools
 */

public class TPublicSentimentKeyword implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String keyWord;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public TPublicSentimentKeyword() {
	}

	/** full constructor */
	public TPublicSentimentKeyword(String keyWord, Timestamp ctime) {
		this.keyWord = keyWord;
		this.ctime = ctime;
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

}