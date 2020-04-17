package com.icss.entity;

import java.util.Date;

public class Order {
	private String buyid;
	private String   uname; 
	private double   allPay;
	private Date   payTime;
	
	public String getBuyid() {
		return buyid;
	}
	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public double getAllPay() {
		return allPay;
	}
	public void setAllPay(double allPay) {
		this.allPay = allPay;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}		
}
