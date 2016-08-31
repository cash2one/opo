package com.darg.opo.pojo;

import java.sql.Timestamp;


/**
 * T360topKeyWord entity. @author MyEclipse Persistence Tools
 */

public class T360topKeyWord  implements java.io.Serializable {


    // Fields    

     private Integer cindex;
     private String title;
     private String ltitle;
     private String newsUrl;
     private String imgUrl;
     private String keyword;
     private Integer count;
     private Timestamp ctime;


    // Constructors

    /** default constructor */
    public T360topKeyWord() {
    }

    
    /** full constructor */
    public T360topKeyWord(String title, String ltitle, String newsUrl, String imgUrl, String keyword, Integer count, Timestamp ctime) {
        this.title = title;
        this.ltitle = ltitle;
        this.newsUrl = newsUrl;
        this.imgUrl = imgUrl;
        this.keyword = keyword;
        this.count = count;
        this.ctime = ctime;
    }

   
    // Property accessors

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

    public String getLtitle() {
        return this.ltitle;
    }
    
    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getNewsUrl() {
        return this.newsUrl;
    }
    
    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getKeyword() {
        return this.keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }

    public Timestamp getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }
   








}