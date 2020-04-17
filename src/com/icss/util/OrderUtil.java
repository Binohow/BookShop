package com.icss.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * 订单格式：D20190312-12345
 *
 */
public class OrderUtil {
	
	private static AtomicLong  al;
	
	static{
		//万一服务器瘫痪，重启会导致订单号冲突，所以万一重启，要配置最大的序号作为初始值
		//al = new AtomicLong(oi.getOrderAinitialValue()+1);    //实际运营数据
		al = new AtomicLong(System.currentTimeMillis());        //为了开发方便，模拟数据
	}
	
	/**
	 * 生成一个新的订单编号 
	 * 如D20200418-1234445
	 * @return
	 */
	public static String createNewOrderNo(){		
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);		
		int month = rightNow.get(Calendar.MONTH) + 1;	
		String sMonth,sDay; 
		if(month<10){
			sMonth =  "0" + Integer.toString(month);
		}else{
			sMonth = Integer.toString(month);
		}
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		if(day<10){
			sDay = "0" + Integer.toString(day);
		}else{
			sDay = Integer.toString(day);
		}
		long xh = al.getAndIncrement();                            //高并发情况下，流水号也不会冲突
		String dno = "D" + year + sMonth + sDay + "-" + xh;
		
		return dno;
	}

}
