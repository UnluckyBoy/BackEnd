package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.User;
import com.service.BackService;


@Controller()
@RequestMapping("/Controller/User")
public class ServerController {
	
	@Autowired
	private BackService backService;
	private User user;
	
	
	//用户注册模块
	@RequestMapping("/register")
	@ResponseBody
	public Map Register(@RequestParam("account") String account,@RequestParam("password") String password,
			HttpServletRequest request,Model model){
		
		Map<String,String> RegisterMap=new HashMap<String, String>();
		Map<String,String> result_map=new HashMap<String, String>();
		String response="true";
		RegisterMap.put("account",account);
		RegisterMap.put("password",password);
		//System.out.println("map:"+RegisterMap);
		if(backService.login(RegisterMap)==null){
			System.out.println("用户未注册"+RegisterMap);
			backService.register(RegisterMap);
			result_map.put("result", response);
		}else{
			System.out.println("用户已注册"+RegisterMap);
			response="false";
			result_map.put("result", response);
		}
		return result_map;
	}
	
	//用户登录模块
	@RequestMapping("/login")
	@ResponseBody
	public Map Login(@RequestParam("account") String account,@RequestParam("password") String password,
			HttpServletRequest request,HttpSession session,Model model){
		
		Map<String,String> session_map=new HashMap<String, String>();
		Map<String,String> result_map=new HashMap<String, String>();
		String response="true";
		session_map.put("account",account);
		session_map.put("password",password);
		user=backService.login(session_map);
		if(user==null){
			System.out.println("登录失败"+session_map);
			response="false";
			result_map.put("result", response);
		}else{
			if(user.getName().equals("admin")){
				result_map.put("result", "admin");
			}else{
				result_map.put("result", response);
				session.setAttribute("user_session",user);
			}
			/*result_map.put("result", response);
			session.setAttribute("user_session",user);*/
		}
		//System.out.println("登录session:"+session.getAttribute("user_session"));
		return result_map;
	}
	
	//获取用户信息
	@RequestMapping("/getInfo")
	@ResponseBody
	public Map GetUserInfo(@RequestParam("account") String account,
			HttpServletRequest request,Model model){
	/*public Map GetUserInfo(HttpSession session,HttpServletRequest request,Model model){
		user=(User)session.getAttribute("user_session");
		String account=user.getAccount();*/
		Map result_map=new HashMap();
		Map<String,String> userMap=new HashMap<String,String>();
		List<Object> userList=new ArrayList<Object>();
		user=backService.getInfo(account);
		//System.out.println("获取user："+user.toString());
		userMap.put("id",String.valueOf(user.getId()));
		userMap.put("name",user.getName());
		userMap.put("account",user.getAccount());
		userMap.put("password",user.getPassword());
		userMap.put("sex",user.getSex());
		userMap.put("address",user.getUser_address());
		userMap.put("type",String.valueOf(user.getUser_type()));
		userMap.put("empl_company",user.getEmpl_company());
		userList.add(userMap);
		result_map.put("user",userList);
		//System.out.println("获取："+result_map);
		return result_map;
	}
	
	//更改用户信息
	@RequestMapping("/upInfo")
	@ResponseBody
	public Map UpUser(@RequestParam("account") String account,@RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword,@RequestParam("name") String name,@RequestParam("sex") String sex,
			@RequestParam("address") String address,HttpServletRequest request,Model model) 
	throws UnsupportedEncodingException{
		
		String nameKey=new String(name.getBytes("iso-8859-1"),"utf-8");//中文字符处理
		String sexKey=new String(sex.getBytes("iso-8859-1"),"utf-8");
		String addressKey=new String(address.getBytes("iso-8859-1"),"utf-8");
		String response="true";
		Map<String,String> UpuserMap=new HashMap<String,String>();
		
		user=backService.getInfo(account);
		User NewUser=new User(user.getId(),(nameKey.equals("")?user.getName():nameKey),account,
				(newpassword.equals("")?user.getPassword():newpassword),(sexKey.equals("")?user.getSex():sexKey),
				(addressKey.equals("")?user.getUser_address():addressKey),user.getUser_type(),user.getEmpl_company());
		
		/*System.out.println("访问URL中的数据:"+"\n账户:"+account+" 用户名:"+nameKey+
				" 密码:"+password+" 新密码:"+newpassword+" 性别:"+sexKey+" 住址:"+addressKey);
		System.out.println("NewUser中的数据:"+NewUser.toString());
		System.out.println("user中的数据："+user.toString());*/
		if(password.equals(user.getPassword())){
			if(backService.upUser(NewUser)>0){
				UpuserMap.put("result", response);
				return UpuserMap;
			}else{
				System.out.println("更新错误");
				response="false";
				UpuserMap.put("result", response);
				return UpuserMap;
			}
		}else{
			System.out.println("密码匹配错误");
			response="false";
			UpuserMap.put("result", response);
			return UpuserMap;
		}
		//return UpuserMap;
	}
	
}
