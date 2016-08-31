package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TTopKeyWord entity. @author MyEclipse Persistence Tools
 */

public class TTopKeyWord implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String keyWord;
	private Integer count;
	private String type;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public TTopKeyWord() {
	}

	public TTopKeyWord(Integer cindex, String keyWord, Integer count, String type, Timestamp ctime) {		
		this.cindex = cindex;
		this.keyWord = keyWord;
		this.count = count;
		this.type = type;
		this.ctime = ctime;
	}

	public Integer getCindex() {
		return cindex;
	}

	public void setCindex(Integer cindex) {
		this.cindex = cindex;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}


	

}