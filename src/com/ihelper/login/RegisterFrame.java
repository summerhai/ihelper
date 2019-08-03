/*
 * Created by JFormDesigner on Thu Dec 20 13:23:40 CST 2012
 */

package com.ihelper.login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import com.ihelper.tools.Coder;
import com.ihelper.tools.Email_Autherticator;
import com.ihelper.tools.PBECoder;
import com.ihelper.tools.SendMailToUser;

/**
 * @author Han LaiMing
 */
public class RegisterFrame{

	public RegisterFrame() {
		initComponents();
	}
	
	
	 /** 
	  * 获取当前操作系统名称. 
	  * return 操作系统名称 例如:windows xp,linux 等. 
	  */ 
	 public static String getOSName() {  
	     return System.getProperty("os.name").toLowerCase();  
	 }  
	 
	 /** 
	  * 获取unix网卡的mac地址. 
	  * 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法. 
	  * @return mac地址 
	  */  
	 public static String getUnixMACAddress() {  
	     String mac = null;  
	     BufferedReader bufferedReader = null;  
	     Process process = null;  
	     try {  
	         process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息  
	         bufferedReader = new BufferedReader(new InputStreamReader(process  
	                 .getInputStream()));  
	         String line = null;  
	         int index = -1;  
	         while ((line = bufferedReader.readLine()) != null) {  
	             index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]  
	             if (index >= 0) {// 找到了  
	                 mac = line.substring(index +"hwaddr".length()+ 1).trim();//  取出mac地址并去除2边空格  
	                 break;  
	             }  
	         }  
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     } finally {  
	         try {  
	             if (bufferedReader != null) {  
	                 bufferedReader.close();  
	             }  
	         } catch (IOException e1) {  
	             e1.printStackTrace();  
	         }  
	         bufferedReader = null;  
	         process = null;  
	     }  

	     return mac;  
	 }  

	 /** 
	  * 获取widnows网卡的mac地址. 
	  * @return mac地址 
	  */  
	 public static String getWindowsMACAddress() {  
	     String mac = null;  
	     BufferedReader bufferedReader = null;  
	     Process process = null;  
	     try {  
	         process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息  
	         bufferedReader = new BufferedReader(new InputStreamReader(process  
	                 .getInputStream()));  
	         String line = null;  
	         int index = -1;  
	         while ((line = bufferedReader.readLine()) != null) {  
	             index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical address]  
	             if (index >= 0) {// 找到了  
	                 index = line.indexOf(":");// 寻找":"的位置  
	                 if (index>=0) {  
	                     mac = line.substring(index + 1).trim();//  取出mac地址并去除2边空格  
	                 }  
	                 break;  
	             }  
	         }  
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     } finally {  
	         try {  
	             if (bufferedReader != null) {  
	                 bufferedReader.close();  
	             }  
	         } catch (IOException e1) {  
	             e1.printStackTrace();  
	         }  
	         bufferedReader = null;  
	         process = null;  
	     }  

	     return mac;  
	 }  
	 
	 /** 
	  * windows 7 专用 获取MAC地址 
	  *  
	  * @return 
	  * @throws Exception 
	  */  
	 public static String getMACAddress() throws Exception {  
	       
	     // 获取本地IP对象  
	     InetAddress ia = InetAddress.getLocalHost();  
	     // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
	     byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();  

	     // 下面代码是把mac地址拼装成String  
	     StringBuffer sb = new StringBuffer();  

	     for (int i = 0; i < mac.length; i++) {  
	         if (i != 0) {  
	             sb.append("-");  
	         }  
	         // mac[i] & 0xFF 是为了把byte转化为正整数  
	         String s = Integer.toHexString(mac[i] & 0xFF);  
	         sb.append(s.length() == 1 ? 0 + s : s);  
	     }  

	     // 把字符串所有小写字母改为大写成为正规的mac地址并返回  
	     return sb.toString().toUpperCase();  
	 } 
	 
	 public static String getMac(String os,String mac){
		 os = getOSName();    
	     if(os.equals("windows 7")){  
	           //本地是windows  
	   	  try {
				mac = getMACAddress();			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}               
	     }else if(os.startsWith("windows")){
	   	  mac = getWindowsMACAddress();
	     }else{  
	           //本地是非windows系统 一般就是unix  
	   	  mac = getUnixMACAddress();  
	             
	     }
	     return mac;
	 }
	 
	public String getUsername()
	{
		return usernameTextField.getText();
	}
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		registerDialog = new JDialog();
		usernameLabel = new JLabel();
		usernameTextField = new JTextField();
		rePasswordLabel = new JLabel();
		passwordLabel = new JLabel();
		passwordTextField = new JPasswordField();
		rePasswordTextField = new JPasswordField();
		cancelButton = new JButton();
		okButton = new JButton();
		emailLabel = new JLabel();
		emailTextField = new JTextField();
		alertLabel = new JLabel();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		isFirst = "r";
		os=getOSName();
		macAddress=getMac(os, macAddress);

		//======== registerFrame ========
		{
			registerDialog.setTitle("注册");
			registerDialog.setVisible(true);
			registerDialog.setResizable(false);
			final Container registerFrameContentPane = registerDialog.getContentPane();
			registerFrameContentPane.setLayout(null);
			registerFrameContentPane.setBackground(Color.GRAY);

			//---- usernameLabel ----
			usernameLabel.setText("\u7528\u6237\u540d");
			registerFrameContentPane.add(usernameLabel);
			usernameLabel.setBounds(30, 25, 110, 20);
			registerFrameContentPane.add(usernameTextField);
			usernameTextField.setBounds(120, 25, 105, usernameTextField.getPreferredSize().height);

			//---- rePasswordLabel ----
			rePasswordLabel.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");
			registerFrameContentPane.add(rePasswordLabel);
			rePasswordLabel.setBounds(30, 125, 95, 20);

			//---- passwordLabel ----
			passwordLabel.setText("\u5bc6\u7801");
			registerFrameContentPane.add(passwordLabel);
			passwordLabel.setBounds(30, 75, 110, 20);
			registerFrameContentPane.add(passwordTextField);
			passwordTextField.setBounds(120, 75, 105, 21);
			registerFrameContentPane.add(rePasswordTextField);
			rePasswordTextField.setBounds(120, 125, 105, 21);
			
			alertLabel.setBounds(200, 230, 100, 50);
			alertLabel.setVisible(true);
			registerFrameContentPane.add(alertLabel);
			
			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			registerFrameContentPane.add(cancelButton);
			cancelButton.setCursor(hander);
			cancelButton.setBounds(new Rectangle(new Point(260, 285), cancelButton.getPreferredSize()));
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					registerDialog.dispose();
				}
			});
			
			//---- okButton ----
			okButton.setText("\u786e\u5b9a");
			registerFrameContentPane.add(okButton);
			okButton.setBounds(175, 285, 57, 23);
			okButton.setCursor(hander);
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource().equals(okButton)){
						usernameString=usernameTextField.getText();
						passwordString=passwordTextField.getText().trim();
						if(new File("./Save/"+usernameTextField.getText()+"/"+usernameTextField.getText()+"_data.ih").exists()){
							JOptionPane.showMessageDialog(registerDialog, "您注册的用户名已存在","注册提示",JOptionPane.OK_OPTION);
							usernameTextField.setText("");
							return;
						}
						  if(!passwordTextField.getText().equals(rePasswordTextField.getText())){
							  JOptionPane.showMessageDialog(registerDialog, "您两次输入的密码不一致", "注册提示", JOptionPane.OK_OPTION);
							  passwordTextField.setText("");
							  rePasswordTextField.setText("");
							  return;
						  }
						  else if(!isEmail(emailTextField.getText())){
							  JOptionPane.showMessageDialog(registerDialog, "您输入的邮箱格式不正确", "注册提示", JOptionPane.OK_OPTION);	
							  emailTextField.setText("");
							  return;
						  }
						  else {
							  try {
								  File dataFile = new File("./Save/");
								  dataFile.mkdirs();
								  File userFile = new File("./Save/"+usernameTextField.getText()+"/");
								  userFile.mkdirs();
								 
								  File outputFile=new File("./Save/"+usernameTextField.getText()+"/"+usernameTextField.getText()+"_data.ih");
								  FileOutputStream fos=new FileOutputStream(outputFile);  
								  //数据写入文件   先BASE64加密，然后PBE加密	因为MD5不可逆，而程序有些地方要读数据并显示出来      
								  byte[] username;
								  byte[] password;
								  byte[] rePassword;
								  byte[] email;
								  byte[] firstOrNot;
								  byte[] mac;
								  
								try {
									byte[] salt = PBECoder.initSalt();
									username = PBECoder.encrypt(Coder.encryptBASE64(usernameTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt);
									password = PBECoder.encrypt(Coder.encryptBASE64(passwordTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt);
									rePassword = PBECoder.encrypt(Coder.encryptBASE64(rePasswordTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt);
									email = PBECoder.encrypt(Coder.encryptBASE64(emailTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt);
									firstOrNot = PBECoder.encrypt(Coder.encryptBASE64(isFirst.getBytes()).getBytes(), "ihelperforyou", salt);
									mac = PBECoder.encrypt(Coder.encryptBASE64(macAddress.getBytes()).getBytes(), "ihelperforyou", salt);
									  System.out.println("未转为16进制的mac数据"+mac);
									  String firstOrNotString=Coder.parseByte2HexStr(firstOrNot);
									  fos.write((firstOrNotString+"\r\n").getBytes());
									  
									  String macString=Coder.parseByte2HexStr(mac);
									  fos.write((macString+"\r\n").getBytes());
									  System.out.println("转为16进制的mac数据"+macString);
									  
									  String usernameString=Coder.parseByte2HexStr(username);
									  fos.write((usernameString+"\r\n").getBytes());

									  String passwordString=Coder.parseByte2HexStr(password);              
									  fos.write((passwordString+"\r\n").getBytes());
									  
									  String rePasswordString=Coder.parseByte2HexStr(rePassword);              
									  fos.write((rePasswordString+"\r\n").getBytes());
									  
									  String emailString=Coder.parseByte2HexStr(email);              
									  fos.write((emailString+"\r\n").getBytes());

									  fos.close();
								
								
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}


								  System.out.println("数据写入完毕");
								  //发送邮件到注册邮箱
								  Email_Autherticator email_Autherticator = new Email_Autherticator("ihelper_foryou@126.com", "112358han");
									email_Autherticator.getPasswordAuthentication();
									String body = "亲爱的用户"+usernameString+"你好：\n我们很高兴的通知你，你在ihelper成功注册账号，你的账号："+usernameString+",密码："+passwordString+"\n请妥善保管你的账号信息，并祝你使用愉快，有任何疑问可以发邮件到ihelper_foryou@126.com。\n";
									new SendMailToUser(usernameString,emailTextField.getText(),body,email_Autherticator);
								  String succesString = "恭喜你"+usernameString+"你已成功注册账号"+"\n我们已将你的注册信息发送到你的邮箱，请妥善保管";
								  JOptionPane.showConfirmDialog(registerDialog, succesString, "注册成功", JOptionPane.OK_OPTION);
								  registerDialog.dispose();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			});

			//---- emailLabel ----
			emailLabel.setText("\u8054\u7cfb\u90ae\u7bb1\uff1a");
			registerFrameContentPane.add(emailLabel);
			emailLabel.setBounds(30, 175, 95, 20);
			registerFrameContentPane.add(emailTextField);
			emailTextField.setBounds(120, 175, 105, 21);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < registerFrameContentPane.getComponentCount(); i++) {
					Rectangle bounds = registerFrameContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = registerFrameContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				registerFrameContentPane.setMinimumSize(preferredSize);
				registerFrameContentPane.setPreferredSize(preferredSize);
			}
			registerDialog.pack();
			registerDialog.setLocationRelativeTo(registerDialog.getOwner());

		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	public static Boolean isEmail(String email){
		Pattern p = Pattern.compile("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog registerDialog;
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	private JLabel rePasswordLabel;
	private JLabel passwordLabel;
	private JLabel alertLabel;
	private JPasswordField passwordTextField;
	private JPasswordField rePasswordTextField;
	private JButton cancelButton;
	private JButton okButton;
	private JLabel emailLabel;
	private JTextField emailTextField;
	private String isFirst;
	private String macAddress;
	private String os,usernameString,passwordString;

	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
