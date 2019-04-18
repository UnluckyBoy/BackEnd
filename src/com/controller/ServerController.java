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

import EncryptionModel.Pwd3DESUtil;

import com.pojo.User;
import com.service.BackService;


@Controller()
@RequestMapping("/Controller/User")
public class ServerController {
	
	private static String PASSWORD_EncryKEY = "EncryptionKey";
	
	@Autowired
	private BackService backService;
	private User user;
	
	
	//�û�ע��ģ��
	@RequestMapping("/register")
	@ResponseBody
	public Map Register(@RequestParam("account") String account,@RequestParam("password") String password,
			HttpServletRequest request,Model model){
		
		Map<String,String> RegisterMap=new HashMap<String, String>();
		Map<String,String> result_map=new HashMap<String, String>();
		String response="true";
		RegisterMap.put("account",account);
		
		//���������
		String encryPwd = Pwd3DESUtil.encode3Des(PASSWORD_EncryKEY, password);
		
		RegisterMap.put("password",encryPwd);
		//System.out.println("map:"+RegisterMap);
		if(backService.login(RegisterMap)==null){
			System.out.println("�û�δע��"+RegisterMap);
			backService.register(RegisterMap);
			result_map.put("result", response);
		}else{
			System.out.println("�û���ע��"+RegisterMap);
			response="false";
			result_map.put("result", response);
		}
		return result_map;
	}
	
	//�û���¼ģ��
	@RequestMapping("/login")
	@ResponseBody
	public Map Login(@RequestParam("account") String account,@RequestParam("password") String password,
			HttpServletRequest request,HttpSession session,Model model){
		
		Map<String,String> session_map=new HashMap<String, String>();
		Map<String,String> result_map=new HashMap<String, String>();
		String response="true";
		session_map.put("account",account);
		
		String encryPwd = Pwd3DESUtil.encode3Des(PASSWORD_EncryKEY, password);
		session_map.put("password",encryPwd);
		user=backService.login(session_map);
		if(user==null){
			System.out.println("��¼ʧ��"+session_map);
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
		//System.out.println("��¼session:"+session.getAttribute("user_session"));
		return result_map;
	}
	
	//��ȡ�û���Ϣ
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
		//System.out.println("��ȡuser��"+user.toString());
		userMap.put("id",String.valueOf(user.getId()));
		userMap.put("name",user.getName());
		userMap.put("account",user.getAccount());
		
		//��������н���
		userMap.put("password",Pwd3DESUtil.decode3Des(PASSWORD_EncryKEY, user.getPassword()));
		userMap.put("sex",user.getSex());
		userMap.put("address",user.getUser_address());
		userMap.put("type",String.valueOf(user.getUser_type()));
		userMap.put("empl_company",user.getEmpl_company());
		userList.add(userMap);
		result_map.put("user",userList);
		//System.out.println("��ȡ��"+result_map);
		return result_map;
	}
	
	//�����û���Ϣ
	@RequestMapping("/upInfo")
	@ResponseBody
	public Map UpUser(@RequestParam("account") String account,@RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword,@RequestParam("name") String name,@RequestParam("sex") String sex,
			@RequestParam("address") String address,HttpServletRequest request,Model model) 
	throws UnsupportedEncodingException{
		
		String nameKey=new String(name.getBytes("iso-8859-1"),"utf-8");//�����ַ�����
		String EnPwd=Pwd3DESUtil.encode3Des(PASSWORD_EncryKEY, password);//�������������ƥ��
		String sexKey=new String(sex.getBytes("iso-8859-1"),"utf-8");
		String addressKey=new String(address.getBytes("iso-8859-1"),"utf-8");
		String response="true";
		Map<String,String> UpuserMap=new HashMap<String,String>();
		
		user=backService.getInfo(account);
		User NewUser=new User(user.getId(),(nameKey.equals("")?user.getName():nameKey),account,
				(newpassword.equals("")?user.getPassword():Pwd3DESUtil.encode3Des(PASSWORD_EncryKEY, newpassword)),
				(sexKey.equals("")?user.getSex():sexKey),(addressKey.equals("")?user.getUser_address():addressKey),
				user.getUser_type(),user.getEmpl_company());
		
		//У���û�ԭ����
		if(EnPwd.equals(user.getPassword())){
			if(backService.upUser(NewUser)>0){
				UpuserMap.put("result", response);
				return UpuserMap;
			}else{
				System.out.println("���´���");
				response="false";
				UpuserMap.put("result", response);
				return UpuserMap;
			}
		}else{
			System.out.println("����ƥ�����");
			response="false";
			UpuserMap.put("result", response);
			return UpuserMap;
		}
		//return UpuserMap;
	}
	
}
