package com.darg.opo.pojo;

import java.sql.Timestamp;


/**
 * TBaiduNewsSerch entity. @author MyEclipse Persistence Tools
 */

public class TBaiduNewsSerch  implements java.io.Serializable {


    // Fields    

     private Integer cindex;
     private String title;
     private String author;
     private String content;
     private String newsUrl;
     private Timestamp publicTime;
     private Timestamp ctime;


    // Constructors

    /** default constructor */
    public TBaiduNewsSerch() {
    }

	/** minimal constructor */
    public TBaiduNewsSerch(String title, String author, String content, String newsUrl, Timestamp publicTime) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.newsUrl = newsUrl;
        this.publicTime = publicTime;
    }
    
    /** full constructor */
    public TBaiduNewsSerch(String title, String author, String content, String newsUrl, Timestamp publicTime, Timestamp ctime) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.newsUrl = newsUrl;
        this.publicTime = publicTime;
        this.ctime = ctime;
    }

   
    // Property accessors

    @Override
	public String toString() {
		return "TBaiduNewsSerch [cindex=" + cindex + ", title=" + title + ", author=" + author + ", content=" + content + ", newsUrl=" + newsUrl + ", publicTime=" + publicTime + ", ctime=" + ctime + "]";
	}

	public Integer getCindex() {
        return this.cindex;
    }
    
    public void setCindex(Integer cindex) {
        this.cindex = cindex;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsUrl() {
        return this.newsUrl;
    }
    
    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public Timestamp getPublicTime() {
        return this.publicTime;
    }
    
    public void setPublicTime(Timestamp publicTime) {
        this.publicTime = publicTime;
    }

    public Timestamp getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }
   








}