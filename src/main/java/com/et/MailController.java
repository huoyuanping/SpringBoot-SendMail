package com.et;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	@Autowired
	private JavaMailSender jms;
	
	@GetMapping("/send")
	public String send(String email_to,String email_subject,String email_content){
		SimpleMailMessage sm=new SimpleMailMessage();
		//谁发的
		sm.setFrom("hyp2549882772@126.com");
		//发给谁
		/*sm.setTo("2549882772@qq.com");
		sm.setSubject("测试邮件");
		sm.setText("测试");*/
		
		sm.setTo(email_to);
		sm.setSubject(email_subject);
		sm.setText(email_content);
		jms.send(sm);
		return "1";
		
	}
	/**
	 * post请求体获取参数所以可以通过相互传递map的方式
	 * @param map
	 * @return
	 */
	@PostMapping("/send")
	public String send(@RequestBody Map<String,Object> map){
		SimpleMailMessage mailMessage  =   new  SimpleMailMessage();
		mailMessage.setFrom("hyp2549882772@126.com");
		mailMessage.setTo(map.get("email_to").toString());
		mailMessage.setSubject(map.get("email_subject").toString());
		mailMessage.setText(map.get("email_content").toString());
		jms.send(mailMessage);
		return "1";
	}
	
	@GetMapping("/user/{userId}")
	public Map getUser(@PathVariable String userId){
		Map map=new HashMap();
		map.put("id", userId);
		map.put("name","zs_"+userId);
		System.out.println(map);
		return map;
	}
	
	@GetMapping("/a")
	public String a(){
		
		return "1";
		
	}

}
