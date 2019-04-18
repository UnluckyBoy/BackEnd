package com.pojo;

public class Company {

	private int id;
	private String name;
	private String adress;
	private String company_contact;
	public Company() {
		super();
	}
	public Company(String name, String adress, String company_contact) {
		super();
		this.name = name;
		this.adress = adress;
		this.company_contact = company_contact;
	}
	public Company(int id, String name, String adress, String company_contact) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.company_contact = company_contact;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", adress=" + adress
				+ ", company_contact=" + company_contact + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCompany_contact() {
		return company_contact;
	}
	public void setCompany_contact(String company_contact) {
		this.company_contact = company_contact;
	}
}
