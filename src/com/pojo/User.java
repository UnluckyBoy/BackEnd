package com.pojo;

import java.util.List;

public class User {

	private int id;
	private String user_name;
	private String user_account;
	private String user_password;
	private String user_sex;
	private String user_location;
	private int user_type;
	private String employe_company;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return user_name;
	}
	public void setName(String name) {
		this.user_name = name;
	}
	public String getAccount() {
		return user_account;
	}
	public void setAccount(String account) {
		this.user_account = account;
	}
	public String getPassword() {
		return user_password;
	}
	public void setPassword(String password) {
		this.user_password = password;
	}
	public String getSex() {
		return user_sex;
	}
	public void setSex(String sex) {
		this.user_sex = sex;
	}
	
	public String getUser_address() {
		return user_location;
	}
	public void setUser_address(String user_address) {
		this.user_location = user_address;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getEmpl_company() {
		return employe_company;
	}
	public void setEmpl_company(String empl_company) {
		this.employe_company = empl_company;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name
				+ ", user_account=" + user_account + ", user_password="
				+ user_password + ", user_sex=" + user_sex + ", user_address="
				+ user_location + ", user_type=" + user_type
				+ ", employe_company=" + employe_company + "]";
	}
	public User() {
		super();
	}
	public User(String user_name, String user_account, String user_password,
			String user_sex, String user_address, int user_type,
			String employe_company) {
		super();
		this.user_name = user_name;
		this.user_account = user_account;
		this.user_password = user_password;
		this.user_sex = user_sex;
		this.user_location = user_address;
		this.user_type = user_type;
		this.employe_company = employe_company;
	}
	public User(int id, String user_name, String user_account,
			String user_password, String user_sex, String user_address,
			int user_type, String employe_company) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.user_account = user_account;
		this.user_password = user_password;
		this.user_sex = user_sex;
		this.user_location = user_address;
		this.user_type = user_type;
		this.employe_company = employe_company;
	}
	
}
