package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TNetAddr entity. @author MyEclipse Persistence Tools
 */

public class TNetAddr implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String url;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public TNetAddr() {
	}

	/** full constructor */
	public TNetAddr(String url, Timestamp ctime) {
		this.url = url;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getCindex() {
		return this.cindex;
	}

	public void setCindex(Integer cindex) {
		this.cindex = cindex;
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