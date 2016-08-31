package com.darg.opo.testOther;

import java.sql.Timestamp;

import com.darg.opo.commutil.CommonUtil;

public class TimestapYesterday {
public static void main(String[] args) {
	Timestamp now=CommonUtil.getNowTime_tamp();	
	System.out.println(now);
	
    Timestamp tp=new Timestamp(System.currentTimeMillis());
	Long lg=tp.getTime()-86400000;
	Timestamp tp2=new Timestamp(lg);	
			
			
	System.out.println(tp2);
}
}
