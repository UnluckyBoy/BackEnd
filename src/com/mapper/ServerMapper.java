package com.mapper;

import java.util.*;

import com.pojo.Order;
import com.pojo.User;

public interface ServerMapper {

	/*
	 * SQL语句接口
	 */
	public User login(Map<String,String> map);
	public User getInfo(String account);
	public void register(Map<String,String> map);//注册
	public int upUser(User user);//修改用户
	public List getOrder(String account);//用户获取订单表
	public List exeGetOrder();//员工获取订单
	public List AdGetOrder();//管理员查看待审核订单
	public int AdupOrder(int orderId);//管理员审核订单
	public void UserInserOrder(Order order);//用户创建订单
	public List OrderIsInser(Map<String,String> map);//查询用户订单是否创建
	public int exeUpOrder(Map<String,String> map);//工作人员执行订单
}
