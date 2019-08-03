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

public class SendMailToUser {

	
    private String host = "smtp.126.com"; 
  
    private String mail_head_name = "this is head of this mail"; 
  
    private String mail_head_value = "this is head of this mail"; 
  
    private String mail_to; 
  
    private String mail_from = "ihelper_foryou@126.com";
  
    private String mail_subject = "这是一封来自ihelper官方的注册邮件"; 
  
    private String mail_body; 
  
    private String personalName; 
    private Authenticator auth;
	public SendMailToUser(String username,String emailname,String body,Authenticator authenticator){
		personalName=username;
		mail_to=emailname;
		mail_body=body;
		auth=authenticator;
//		if(mail_to.contains("qq.com")){
//			host = "smtp.qq.com";
//		}
//		else if(mail_to.contains("163.com")){
//			host = "smtp.163.com";
//		}
//		else if(mail_to.contains("126.com")){
//			host = "smtp.126.com";
//		}
//		else if(mail_to.contains("hotmail.com")){
//			host = "smtp.hotmail.com";
//		}
		try {
			sendMail();
		} catch (SendFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    private void sendMail() throws SendFailedException{ 
  try { 
      Properties props = new Properties();//��ȡϵͳ���� 
       //auth�����ʼ������û���֤ 
      props.put("mail.smtp.host", host); 
      props.put("mail.smtp.auth", "true"); 
      System.out.println(props); 
      Session session = Session.getDefaultInstance(props,auth); 
      //����session,���ʼ�����������ͨѶ 
      MimeMessage message = new MimeMessage(session); 
      message.setContent("Hello","text/plain");//�����ʼ���ʽ 
      message.setSubject(mail_subject);//�����ʼ����� 
      message.setText(mail_body);//�����ʼ����� 
      message.setHeader(mail_head_name, mail_head_value);//�����ʼ����� 
      message.setSentDate(new Date());//�����ʼ�����ʱ�� 
      Address address = new InternetAddress(mail_from,personalName); 
      message.setFrom(address);//�����ʼ������ߵĵ�ַ 
      Address toaddress = new InternetAddress(mail_to);//�����ʼ������ߵĵ�ַ 
      message.addRecipient(Message.RecipientType.TO,toaddress); 
      System.out.println(message); 
      Transport.send(message); 
      System.out.println("Send Mail Ok!"); 
  } catch (Exception e) { 
      e.printStackTrace(); 
  } 

    } 
}