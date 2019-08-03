package com.ihelper.tools;

import java.util.Date; 
import java.util.Properties; 
import javax.mail.Address; 
import javax.mail.Authenticator; 
import javax.mail.Message; 
import javax.mail.SendFailedException; 
import javax.mail.Session; 
import javax.mail.Transport; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage; 

public class Mail {
	public Mail(String username,String emailname,String body,Authenticator authenticator){
		personalName=username;
		mail_from=emailname;
		mail_body=body;
		auth=authenticator;
		if(mail_from.contains("qq.com")){
			host = "smtp.qq.com";
		}
		else if(mail_from.contains("163.com")){
			host = "smtp.163.com";
		}
		else if(mail_from.contains("126.com")){
			host = "smtp.163.com";
		}
		else if(mail_from.contains("hotmail.com")){
			host = "smtp.hotmail.com";
		}
		else if(mail_from.contains("gmail.com")){
			host = "smtp.gmail.com";
		}
		
		try {
			sendMail();
		} catch (SendFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private String host; 
  
    private String mail_head_name = "this is head of this mail"; 
  
    private String mail_head_value = "this is head of this mail"; 
  
    private String mail_to ; 
  
    private String mail_from = "ihelper_foryou@126.com"; 
  
    private String mail_subject = "这是一封来自ihelper官方的注册邮件"; 

    private String mail_body; 
  
    private String personalName;
    private Authenticator auth;
    
    private void sendMail() throws SendFailedException{ 
  try { 
      Properties props = new Properties();//获取系统环境 
      Authenticator auth = new Email_Autherticator();//进行邮件服务用户认证 
      props.put("mail.smtp.host", host); 
      props.put("mail.smtp.auth", "true"); 
      System.out.println(props); 
      Session session = Session.getDefaultInstance(props,auth); 
      //设置session,和邮件服务器进行通讯 
      MimeMessage message = new MimeMessage(session); 
      message.setContent("Hello","text/plain");//设置邮件格式 
      message.setSubject(mail_subject);//设置邮件主题 
      message.setText(mail_body);//设置邮件内容 
      message.setHeader(mail_head_name, mail_head_value);//设置邮件标题 
      message.setSentDate(new Date());//设置邮件发送时期 
      Address address = new InternetAddress(mail_from,personalName); 
      message.setFrom(address);//设置邮件发送者的地址 
      Address toaddress = new InternetAddress(mail_to);//设置邮件接收者的地址 
      message.addRecipient(Message.RecipientType.TO,toaddress); 
      System.out.println(message); 
      Transport.send(message); 
      System.out.println("Send Mail Ok!"); 
  } catch (Exception e) { 
      e.printStackTrace(); 
  } 

    } 
}