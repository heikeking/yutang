package com.itteam.yutang.bean;

public class UserBean {
	public String userId;
	public String userName;
	public String userNumber;
	public String account;
	public String password;

	public UserBean(String userId, String userName, String userNumber, String account, String password) {
		this.account =account;
		this.password =password;
		this.userId = userId;
		this.userName =userName;
		this.userNumber =userNumber;
	}
}
