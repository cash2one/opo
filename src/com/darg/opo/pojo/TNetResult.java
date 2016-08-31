package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TNetResult entity. @author MyEclipse Persistence Tools
 */

public class TNetResult implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String keyWord;
	private String url;
	private Timestamp ctime;
	private String flag;

	// Constructors

	/** default constructor */
	public TNetResult() {
	}

	/** minimal constructor */
	public TNetResult(String flag) {
		this.flag = flag;
	}

	/** full constructor */
	public TNetResult(String keyWord, String url, Timestamp ctime, String flag) {
		this.keyWord = keyWord;
		this.url = url;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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