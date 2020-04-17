package com.icss.entity;

public class User {
	private String uname;  //用户名
	private String pwd;    //密码
	private int role;      //角色，管理员，VIP，注册用户 （身份不同，业务系统的访问权限不同）
	private double account;  //账户
	private String address; //用户地址
	private String phonenum; //电话号
	
	public double getAccount() {
		return account;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	

}
