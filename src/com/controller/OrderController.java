package com.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import SystemTime.GetSystemTime;

import com.pojo.Order;
import com.pojo.User;
import com.service.BackService;

@Controller()
@RequestMapping("/Controller/Order")
public class OrderController {
	
	@Autowired
	private BackService backService;
	private Order order;
	//private GetSystemTime time;
	
	//����Ա�ύ��˶���
	@RequestMapping("/adminController/order")
	@ResponseBody()
	public Map AdInserOrder(@RequestParam("orderId") int orderId,HttpServletRequest request,Model model){
		
		//List<Object> orderList=new ArrayList<Object>();
		Map<String,String> orderMap=new HashMap<String,String>();
		if(backService.AdupOrder(orderId)>0){
			orderMap.put("result", "true");
		}else{
			orderMap.put("result", "false");
		}
		//orderMap.put("Order", orderList);
		return orderMap;
	}
	
	//������Ա��Ա����ִ�ж���ʱ�޸Ķ���״̬
	@RequestMapping("/exeController/order")
	@ResponseBody()
	public Map ExeInserOrder(@RequestParam("orderId") int orderId,@RequestParam("executor") String executor,
			HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		
		String executorKey=new String(executor.getBytes("iso-8859-1"),"utf-8");
		GetSystemTime ft=new GetSystemTime();
		Map<String,String> InserorderMap=new HashMap<String,String>();
		
		InserorderMap.put("executor", executorKey);
		InserorderMap.put("orderId", String.valueOf(orderId));
		InserorderMap.put("ft", ft.GetTime());
		InserorderMap.put("execution", "������");
		System.out.println("InserorderMap��"+InserorderMap);
		
		Map<String,String> orderMap=new HashMap<String,String>();
		if(backService.exeUpOrder(InserorderMap)>0){
			orderMap.put("result", "true");
		}else{
			orderMap.put("result", "false");
		}
		return orderMap;
		//return null;
	}
	
	//�û���������
	@RequestMapping("/userController/order")
	@ResponseBody()
	public Map UserInserOrder(@RequestParam("user") String user,@RequestParam("item") String item,
			HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		
		Map<String,String> orderMap=new HashMap<String,String>();
		String userKey=new String(user.getBytes("iso-8859-1"),"utf-8");
		String itemKey=new String(item.getBytes("iso-8859-1"),"utf-8");
		Map<String,String> IsMap=new HashMap<String,String>();
		GetSystemTime time=new GetSystemTime();
		IsMap.put("user", userKey);
		IsMap.put("item", itemKey);
		IsMap.put("st", time.GetTime());
		//System.out.println("IsMap:"+IsMap);
		
		if(!(backService.OrderIsInser(IsMap).isEmpty())){
			//System.out.println("�ö����Ѵ�����");
			orderMap.put("result", "false");
		}else{
			Order NewOrder=new Order(null,userKey,itemKey,time.GetTime(),"δ���",null,null);
			backService.UserInserOrder(NewOrder);
			orderMap.put("result", "true");
			//System.out.println("order:"+NewOrder.toString());
		}
		return orderMap;
	}
	
	//�û�ɾ��δ������
	@RequestMapping("/userController/orderdel")
	@ResponseBody()
	public Map DelOrder(@RequestParam("account") String account,@RequestParam("id") int id,
			HttpServletRequest request,Model model){
		List<Object> orderReList=new ArrayList<Object>();
		Map orderMap=new HashMap();
		
		backService.DelOrder(id);
		orderReList=backService.getOrder(account);
		orderMap.put("Order", orderReList);
		/*int delCode=backService.DelOrder(id);
		if(delCode!=0){
			orderReList=backService.getOrder(account);
			orderMap.put("Order", orderReList);
			System.out.println("ɾ����ˢ�£�"+orderMap.toString());
		}else{
			
		}*/
		
		return orderMap;
	}
}
