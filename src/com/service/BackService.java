package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.Order;
import com.pojo.User;

public interface BackService {

	/*
	 * Mapper�ӿ�
	 */
	public User login(Map<String,String> map);
	public User getInfo(String account);
	public void register(Map<String,String> map);//ע��
	public int upUser(User user);//�޸��û�
	public List getOrder(String account);//�û���ȡ������
	public List exeGetOrder();//Ա����ȡ����
	public List AdGetOrder();//����Ա�鿴����˶���
	public int AdupOrder(int orderId);//����Ա��˶���
	public void UserInserOrder(Order order);//�û���������
	public List OrderIsInser(Map<String,String> map);//��ѯ�û������Ƿ񴴽�
	public int exeUpOrder(Map<String,String> map);//������Աִ�ж���
}