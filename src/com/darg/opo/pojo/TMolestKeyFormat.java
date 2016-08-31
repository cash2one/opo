package com.darg.opo.pojo;

import java.sql.Timestamp;

/**
 * TMolestKeyFormat entity. @author MyEclipse Persistence Tools
 */

public class TMolestKeyFormat implements java.io.Serializable {

	// Fields    

	private Integer cindex;
	private String pre;
	private String next;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public TMolestKeyFormat() {
	}

	/** full constructor */
	public TMolestKeyFormat(String pre, String next, Timestamp ctime) {
		this.pre = pre;
		this.next = next;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getCindex() {
		return this.cindex;
	}

	public void setCindex(Integer cindex) {
		this.cindex = cindex;
	}

	public String getPre() {
		return this.pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public String getNext() {
		return this.next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}