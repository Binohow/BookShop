package com.icss.entity;

public class User {
	private String uname;  //�û���
	private String pwd;    //����
	private int role;      //��ɫ������Ա��VIP��ע���û� ����ݲ�ͬ��ҵ��ϵͳ�ķ���Ȩ�޲�ͬ��
	private double account;  //�˻�
	private String address; //�û���ַ
	private String phonenum; //�绰��
	
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
