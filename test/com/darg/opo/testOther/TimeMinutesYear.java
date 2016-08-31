package com.darg.opo.testOther;

import java.sql.Timestamp;

public class TimeMinutesYear {
	public void YearMonthToTimestap(){
		String str="2010年05月18日 14:00";
		str=str.replace("年", "-");
		str=str.replace("月", "-");
		str=str.replace("日", "");
		System.out.println(str);
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
        String tsStr = "2011-05-09 11:49:45";  
        try {  
            ts = Timestamp.valueOf(tsStr);  
            System.out.println(ts);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
public static void main(String[] args) {
	/*String[] array="34分钟".split("分钟");
	int nn=Integer.parseInt(array[0]);
	Timestamp tp = new Timestamp(System.currentTimeMillis());
	Long lg = tp.getTime() - 86400000/(24*60)*34;
	Timestamp startCreatetime = new Timestamp(lg);
	System.out.println(startCreatetime);*/
	TimeMinutesYear t=new TimeMinutesYear();
	t.YearMonthToTimestap();
	
}
}
