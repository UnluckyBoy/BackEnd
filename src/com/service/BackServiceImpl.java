package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.ServerMapper;
import com.pojo.Order;
import com.pojo.User;

@Service
@Transactional
public class BackServiceImpl implements BackService{
	
	@Autowired
	private ServerMapper serverMapper;

	@Override
	public User login(Map<String, String> map) {
		return serverMapper.login(map);
		//serverMapper.login(map);
	}

	@Override
	public User getInfo(String account) {
		// TODO Auto-generated method stub
		return serverMapper.getInfo(account);
	}

	@Override
	public void register(Map<String, String> map) {
		// TODO Auto-generated method stub
		serverMapper.register(map);
	}

	@Override
	public int upUser(User user) {
		// TODO Auto-generated method stub
		return serverMapper.upUser(user);
	}

	@Override
	public List getOrder(String account) {
		// TODO Auto-generated method stub
		return serverMapper.getOrder(account);
	}

	@Override
	public List exeGetOrder() {
		// TODO Auto-generated method stub
		return serverMapper.exeGetOrder();
	}

	@Override
	public List AdGetOrder() {
		// TODO Auto-generated method stub
		return serverMapper.AdGetOrder();
	}

	@Override
	public int AdupOrder(int orderId) {
		// TODO Auto-generated method stub
		return serverMapper.AdupOrder(orderId);
	}

	@Override
	public void UserInserOrder(Order order) {
		// TODO Auto-generated method stub
		serverMapper.UserInserOrder(order);
	}

	@Override
	public List OrderIsInser(Map<String,String> map) {
		// TODO Auto-generated method stub
		return serverMapper.OrderIsInser(map);
	}

	@Override
	public int exeUpOrder(Map<String, String> map) {
		return serverMapper.exeUpOrder(map);
	}
}
