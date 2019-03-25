package com.pojo;

public class Order {
	private int id;
	private String order_executor;
	private String order_user;
	private String order_item;
	private String order_st;
	private String order_stats;
	private String order_execution;
	private String order_ft;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_executor() {
		return order_executor;
	}
	public void setOrder_executor(String order_executor) {
		this.order_executor = order_executor;
	}
	public String getOrder_user() {
		return order_user;
	}
	public void setOrder_user(String order_user) {
		this.order_user = order_user;
	}
	public String getOrder_item() {
		return order_item;
	}
	public void setOrder_item(String order_item) {
		this.order_item = order_item;
	}
	public String getOrder_st() {
		return order_st;
	}
	public void setOrder_st(String order_st) {
		this.order_st = order_st;
	}
	public String getOrder_ft() {
		return order_ft;
	}
	public void setOrder_ft(String order_ft) {
		this.order_ft = order_ft;
	}
	public String getOrder_stats() {
		return order_stats;
	}
	public void setOrder_stats(String order_stats) {
		this.order_stats = order_stats;
	}
	public String getOrder_execution() {
		return order_execution;
	}
	public void setOrder_execution(String order_execution) {
		this.order_execution = order_execution;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", order_executor=" + order_executor
				+ ", order_user=" + order_user + ", order_item=" + order_item
				+ ", order_st=" + order_st + ", order_stats=" + order_stats
				+ ", order_execution=" + order_execution + ", order_ft="
				+ order_ft + "]";
	}
	public Order() {
		super();
	}
	public Order(String order_executor, String order_user, String order_item,
			String order_st, String order_stats, String order_execution,
			String order_ft) {
		super();
		this.order_executor = order_executor;
		this.order_user = order_user;
		this.order_item = order_item;
		this.order_st = order_st;
		this.order_stats = order_stats;
		this.order_execution = order_execution;
		this.order_ft = order_ft;
	}
	public Order(int id, String order_executor, String order_user,
			String order_item, String order_st, String order_stats,
			String order_execution, String order_ft) {
		super();
		this.id = id;
		this.order_executor = order_executor;
		this.order_user = order_user;
		this.order_item = order_item;
		this.order_st = order_st;
		this.order_stats = order_stats;
		this.order_execution = order_execution;
		this.order_ft = order_ft;
	}
}
