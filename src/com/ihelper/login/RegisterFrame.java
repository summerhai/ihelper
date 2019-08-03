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
	  * ��ȡ��ǰ����ϵͳ����. 
	  * return ����ϵͳ���� ����:windows xp,linux ��. 
	  */ 
	 public static String getOSName() {  
	     return System.getProperty("os.name").toLowerCase();  
	 }  
	 
	 /** 
	  * ��ȡunix������mac��ַ. 
	  * ��windows��ϵͳĬ�ϵ��ñ�������ȡ.���������ϵͳ����������µ�ȡmac��ַ����. 
	  * @return mac��ַ 
	  */  
	 public static String getUnixMACAddress() {  
	     String mac = null;  
	     BufferedReader bufferedReader = null;  
	     Process process = null;  
	     try {  
	         process = Runtime.getRuntime().exec("ifconfig eth0");// linux�µ����һ��ȡeth0��Ϊ���������� ��ʾ��Ϣ�а�����mac��ַ��Ϣ  
	         bufferedReader = new BufferedReader(new InputStreamReader(process  
	                 .getInputStream()));  
	         String line = null;  
	         int index = -1;  
	         while ((line = bufferedReader.readLine()) != null) {  
	             index = line.toLowerCase().indexOf("hwaddr");// Ѱ�ұ�ʾ�ַ���[hwaddr]  
	             if (index >= 0) {// �ҵ���  
	                 mac = line.substring(index +"hwaddr".length()+ 1).trim();//  ȡ��mac��ַ��ȥ��2�߿ո�  
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
	  * ��ȡwidnows������mac��ַ. 
	  * @return mac��ַ 
	  */  
	 public static String getWindowsMACAddress() {  
	     String mac = null;  
	     BufferedReader bufferedReader = null;  
	     Process process = null;  
	     try {  
	         process = Runtime.getRuntime().exec("ipconfig /all");// windows�µ������ʾ��Ϣ�а�����mac��ַ��Ϣ  
	         bufferedReader = new BufferedReader(new InputStreamReader(process  
	                 .getInputStream()));  
	         String line = null;  
	         int index = -1;  
	         while ((line = bufferedReader.readLine()) != null) {  
	             index = line.toLowerCase().indexOf("physical address");// Ѱ�ұ�ʾ�ַ���[physical address]  
	             if (index >= 0) {// �ҵ���  
	                 index = line.indexOf(":");// Ѱ��":"��λ��  
	                 if (index>=0) {  
	                     mac = line.substring(index + 1).trim();//  ȡ��mac��ַ��ȥ��2�߿ո�  
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
	  * windows 7 ר�� ��ȡMAC��ַ 
	  *  
	  * @return 
	  * @throws Exception 
	  */  
	 public static String getMACAddress() throws Exception {  
	       
	     // ��ȡ����IP����  
	     InetAddress ia = InetAddress.getLocalHost();  
	     // �������ӿڶ��󣨼������������õ�mac��ַ��mac��ַ������һ��byte�����С�  
	     byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();  

	     // ��������ǰ�mac��ַƴװ��String  
	     StringBuffer sb = new StringBuffer();  

	     for (int i = 0; i < mac.length; i++) {  
	         if (i != 0) {  
	             sb.append("-");  
	         }  
	         // mac[i] & 0xFF ��Ϊ�˰�byteת��Ϊ������  
	         String s = Integer.toHexString(mac[i] & 0xFF);  
	         sb.append(s.length() == 1 ? 0 + s : s);  
	     }  

	     // ���ַ�������Сд��ĸ��Ϊ��д��Ϊ�����mac��ַ������  
	     return sb.toString().toUpperCase();  
	 } 
	 
	 public static String getMac(String os,String mac){
		 os = getOSName();    
	     if(os.equals("windows 7")){  
	           //������windows  
	   	  try {
				mac = getMACAddress();			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}               
	     }else if(os.startsWith("windows")){
	   	  mac = getWindowsMACAddress();
	     }else{  
	           //�����Ƿ�windowsϵͳ һ�����unix  
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
			registerDialog.setTitle("ע��");
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
							JOptionPane.showMessageDialog(registerDialog, "��ע����û����Ѵ���","ע����ʾ",JOptionPane.OK_OPTION);
							usernameTextField.setText("");
							return;
						}
						  if(!passwordTextField.getText().equals(rePasswordTextField.getText())){
							  JOptionPane.showMessageDialog(registerDialog, "��������������벻һ��", "ע����ʾ", JOptionPane.OK_OPTION);
							  passwordTextField.setText("");
							  rePasswordTextField.setText("");
							  return;
						  }
						  else if(!isEmail(emailTextField.getText())){
							  JOptionPane.showMessageDialog(registerDialog, "������������ʽ����ȷ", "ע����ʾ", JOptionPane.OK_OPTION);	
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
								  //����д���ļ�   ��BASE64���ܣ�Ȼ��PBE����	��ΪMD5�����棬��������Щ�ط�Ҫ�����ݲ���ʾ����      
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
									  System.out.println("δתΪ16���Ƶ�mac����"+mac);
									  String firstOrNotString=Coder.parseByte2HexStr(firstOrNot);
									  fos.write((firstOrNotString+"\r\n").getBytes());
									  
									  String macString=Coder.parseByte2HexStr(mac);
									  fos.write((macString+"\r\n").getBytes());
									  System.out.println("תΪ16���Ƶ�mac����"+macString);
									  
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


								  System.out.println("����д�����");
								  //�����ʼ���ע������
								  Email_Autherticator email_Autherticator = new Email_Autherticator("ihelper_foryou@126.com", "112358han");
									email_Autherticator.getPasswordAuthentication();
									String body = "�װ����û�"+usernameString+"��ã�\n���Ǻܸ��˵�֪ͨ�㣬����ihelper�ɹ�ע���˺ţ�����˺ţ�"+usernameString+",���룺"+passwordString+"\n�����Ʊ�������˺���Ϣ����ף��ʹ����죬���κ����ʿ��Է��ʼ���ihelper_foryou@126.com��\n";
									new SendMailToUser(usernameString,emailTextField.getText(),body,email_Autherticator);
								  String succesString = "��ϲ��"+usernameString+"���ѳɹ�ע���˺�"+"\n�����ѽ����ע����Ϣ���͵�������䣬�����Ʊ���";
								  JOptionPane.showConfirmDialog(registerDialog, succesString, "ע��ɹ�", JOptionPane.OK_OPTION);
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
