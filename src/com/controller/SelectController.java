package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.BackService;

@Controller()
@RequestMapping("/SelectController/Order")
public class SelectController {
	
	@Autowired
	private BackService backService;
	
	//用户获取个人订单
	@RequestMapping("/userget/orderInfo")
	@ResponseBody()
	public Map GetOrder(@RequestParam("account") String account,HttpServletRequest request,Model model){
		
		List<Object> orderList=new ArrayList<Object>();
		Map orderMap=new HashMap();
		orderList=backService.getOrder(account);
		orderMap.put("Order", orderList);
		return orderMap;
	}
	
	//执行人员获取订单
	@RequestMapping("/executor/orderInfo")
	@ResponseBody()
	public Map ExeGetOrder(HttpServletRequest request,Model model){
		
		List<Object> orderList=new ArrayList<Object>();
		Map orderMap=new HashMap();
		orderList=backService.exeGetOrder();
		orderMap.put("Order", orderList);
		return orderMap;
	}
	
	//管理员审核订单
	@RequestMapping("/adminController/orderInfo")
	@ResponseBody()
	public Map ControllerOrder(HttpServletRequest request,Model model){
		
		List<Object> orderList=new ArrayList<Object>();
		Map orderMap=new HashMap();
		orderList=backService.AdGetOrder();
		orderMap.put("Order", orderList);
		return orderMap;
	}
}
