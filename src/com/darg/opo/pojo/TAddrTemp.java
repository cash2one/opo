package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TAddrTemp entity. @author MyEclipse Persistence Tools
 */

public class TAddrTemp implements java.io.Serializable {

	// Fields    

	private Integer id;
	private String keyWord;
	private String url;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public TAddrTemp() {
	}

	/** full constructor */
	public TAddrTemp(String keyWord, String url, Timestamp ctime) {
		this.keyWord = keyWord;
		this.url = url;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}