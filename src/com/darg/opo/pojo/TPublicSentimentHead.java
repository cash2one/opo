package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TPublicSentimentHead entity. @author MyEclipse Persistence Tools
 */

public class TPublicSentimentHead implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String head;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public TPublicSentimentHead() {
	}

	/** full constructor */
	public TPublicSentimentHead(String head, Timestamp ctime) {
		this.head = head;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getCindex() {
		return this.cindex;
	}

	public void setCindex(Integer cindex) {
		this.cindex = cindex;
	}

	public String getHead() {
		return this.head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}