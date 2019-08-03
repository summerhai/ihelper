/**
 * Copyright  100 yearsAdministrator

 * All rights reserved.
 */
package com.ihelper.login;

/**
 * @author Administrator
 *
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.ihelper.mainframe.MainFrame;
import com.ihelper.mainframe.SparePasswordFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;
import com.ihelper.tools.Skins;
public class UserLogin extends JFrame implements  ActionListener {	
	

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
//怎样从文件中读取指定行的数据
 public static List<String> getFileContent(String path) {
	  List<String> strList = new ArrayList<String>();
	  try {
	   File file = new File(path);
	   InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GB2312");
	   BufferedReader reader = new BufferedReader(read);
	   String line;
	   while((line = reader.readLine()) != null) {
	    strList.add(line);
	   }
	  } catch (UnsupportedEncodingException e) {
	   e.printStackTrace();
	  } catch (FileNotFoundException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	  return strList;
	 }

	 /**
	  * 
	 * @Title: listFileByRow 
	 * @Description: 获取指定行的值  
	 * @param @param path - 文件路径
	 * @param @param row - 指定行   
	 * @return String - 返回指定行的数据,没有指定行时数据返回空字符串
	 * @throws
	  */
	 public static String listFileByRow(String path, Integer row) {
	  List<String> strList = getFileContent(path);
	  int size = strList.size();
	  if(size >= (row - 1)) 
	   return strList.get(row - 1);
	  else
	   return "";
	   
	 }


	 public static List<String> listFileByRegionRow(String path, Integer startLine, Integer endLine) {
	  List<String> strList = getFileContent(path);
	  //指定区间的值存到regionList 
	  List<String> regionList = new ArrayList<String>();  
	  int size = strList.size();
	  if(size >= (endLine - 1)) {
	   for (int i=startLine; i<=endLine; i++)
	    regionList.add(strList.get(i-1));
	  } 
	  return regionList;
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
 
 public void NormalLogin(){

	 usernameJLabel.setText("用户名");
	  usernameJLabel.setBounds(90, 40, 50, 30);
	  mainFrameContentPane.add(usernameJLabel);
	  
	  usernameField.setBounds(150, 40, 120, 30);
	  mainFrameContentPane.add(usernameField);

	  passwordJLabel.setText("密码");
	  passwordJLabel.setBounds(90, 90, 50, 30);
	  mainFrameContentPane.add(passwordJLabel);
	  
	  passwordField.setBounds(150, 90, 120, 30);
	  mainFrameContentPane.add(passwordField);
	  		  
	  loginButton.setText("登陆");
	  loginButton.setBounds(220,190,60,30);
	  loginButton.addActionListener(this);
	  mainFrameContentPane.add(loginButton);
	  
	  registerButton.setText("注册");
	  registerButton.setBounds(150, 190, 60, 30);	  
	  registerButton.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new RegisterFrame();
		}
	});
	  mainFrameContentPane.add(registerButton); 	  

	  hintStr.setBounds(130, 120, 180, 30);
	  hintStr.setText("");
	  hintStr.setForeground(Color.RED);
	  mainFrameContentPane.add(hintStr);
	  
	  mainFrame.pack(); 
	  mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	  mainFrame.setLocationRelativeTo(null);
	  mainFrame.addWindowListener(new WindowAdapter() {  
          public void windowClosing(WindowEvent e) {  
              int option = JOptionPane.showConfirmDialog(null, "是否完全退出该系统？",  
                      "温馨提示", JOptionPane.YES_NO_OPTION,  
                      JOptionPane.QUESTION_MESSAGE);  
              if (option == JOptionPane.YES_OPTION)  
                  System.exit(0);  
          }  
      }); 
	  
 }
 
 
 public UserLogin  () throws UnknownHostException {	
	 super();	  
	 try {
		   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//将外观设置为系统外观：
		  } catch (Exception e) {
		  }

	 
	 mainFrame = new JFrame();
	 usernameJLabel = new JLabel();
	 passwordJLabel = new JLabel();
	 usernameField = new JTextField();
	 passwordField = new JPasswordField();
	 loginButton = new JButton();
	 registerButton = new JButton();
	 hintStr=new JLabel();
	 
	 mainFrame=new JFrame("ihelper 登陆系统");
	 mainFrame.setResizable(false);
	 mainFrame.setVisible(true);
	 
	 mainFrame.setPreferredSize(new Dimension(365,270));
	 mainFrameContentPane = mainFrame.getContentPane();
	 mainFrameContentPane.setLayout(null);
	 mainFrameContentPane.setBackground(Color.GRAY);
	 owner=new JDialog(mainFrame);
	 Cursor hander = new Cursor(Cursor.HAND_CURSOR);
	 loginButton.setCursor(hander);
	 registerButton.setCursor(hander);
	 NormalLogin();
	 
	 
 }

 public void actionPerformed(ActionEvent e) {
  username=usernameField.getText().trim();
  password=new String(passwordField.getPassword());
  if(password==null){
	  password="";
  }else{
	  password=password.trim();
  }
  if(username!=null && username.length()>0){
   hintStr.setText("正在验证客户端，请稍候...");
   start();
  }
 }
 
 public void start() throws NumberFormatException{
  //建立联网线程
  new Thread(new Runnable(){
   public void run(){
    try {
    	//验证
    String result=validate(username,password);
    	//ACK表示用户名密码mac都正确，NAK表示用户名密码有错，SpareACK表示mac地址不对；
    	//FirstACK表示第一次登陆正确，FirstNAK表示第一次登陆用户名或密码有误；
     if(result.equals("FirstACK")){ 
      hintStr.setText("验证成功，欢迎您第一次光临!");
      Thread.sleep(1000);
      mainFrame.dispose();
      new SparePasswordFrame(owner,username,"l");
      
     }else if(result.equals("FirstNAK")){
    	 passwordField.setText(null);
      hintStr.setText("第一次登陆用户名或密码错误，请重新输入");
      
     }
     else if(result.equals("ACK")){
    	 hintStr.setText("验证成功，欢迎您再次光临!");
         Thread.sleep(1000);
         mainFrame.dispose();
         new MainFrame(username);
    	 
     }
     else if(result.equals("NAK")){
    	 passwordField.setText(null);
         hintStr.setText("登陆用户名或密码错误，请重新输入");
    	 
     }
     else if(result.equals("SpareACK")){ 
         hintStr.setText("验证成功，欢迎您再次光临!");
         Thread.sleep(1000);
         mainFrame.dispose();
         new SparePasswordFrame(owner,username,"s");//需要判断数据文件中首位
         
        }
     else {
    	 NormalLogin();
     }
     
    } catch (InterruptedException e) {
     e.printStackTrace();
    }finally{
    }
   }    
  }).start();  
  
 }
	/**
 * @param username2
 * @param password2
 * @return
 */
public String validate(String username, String password) {
	
	while(!new File("./Save/"+username+"/"+username+"_data.ih").exists()){
		hintStr.setText("您的用户名还没有注册哦");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		hintStr.setText("");
	}
	int startLine = 1,endLine = 6;
	 List<String> regionList = listFileByRegionRow(("./Save/"+username+"/"+username+"_data.ih"), startLine, endLine);
	  //这里判断是否是第一次登陆,在点击登陆后，判定数据文件内首位标识是r还是l，还是s，
	  //如果是r，表示注册完，但是还没登陆，登陆成功后改为l；是l表示已经第一次登陆了，然后改为s，如果第一位是s，表示这不是第一次登陆了
	  //第一次登陆验证成功，返回FirstACK；第一次登陆验证失败返回FirstNAK；
	  //不是第一次登陆，用户名密码常用mac地址验证通过，返回ACK；用户名或密码有错，返回NAK；
	  //用户名密码都正确，但是mac不对，返回SpareACK；
	  //注册完是r，第一次登陆过程中由于需要输入备用密码和问题提示，标识l，不是第一次登陆的标识为s，以防混乱
	  localMac = getMac(localOs, localMac);

		try {
			//解密数据,读取出来解密成为原始数据
			  byte[] salt = PBECoder.initSalt();
			  usernameFromFile = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine+1)), "ihelperforyou", salt))));
			  passwordFromFile = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine+2)), "ihelperforyou", salt))));
			  firstMacAddress = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine)), "ihelperforyou", salt))));
			  firstFromFile = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt))));
			  System.out.println(firstMacAddress+"test");
			  if(firstFromFile.equals("r")){
				 
				  if(usernameFromFile.equals(username)&&passwordFromFile.equals(password))
				  {
							File outputFile=new File("./Save/"+username+"/"+username+"_data.ih");
							  BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile));
							  String buff = bufferedReader.readLine();
							  Vector<String> tempVector = new Vector<String>();
							  while(buff!=""&&buff!=null){
								  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64("r".getBytes()).getBytes(), "ihelperforyou", salt)))){
									  tempVector.add(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64("l".getBytes()).getBytes(), "ihelperforyou", salt)));
								  }
								  else {
									tempVector.add(buff);
								}
								  buff=bufferedReader.readLine();
							  }
							  bufferedReader.close();
							  
							  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
							  for(int i=0;i<tempVector.size();i++)
							  {
								  bufferedWriter.write(tempVector.get(i).toString()+"\r\n");					  
							  }
							  bufferedWriter.flush();
							  bufferedWriter.close();			  
						
					  return "FirstACK";//进入备用密码输入界面
				  }
				  else return "FirstNAK";
			  }
			  else if(firstFromFile.equals("l"))
			  {			 
				  if(usernameFromFile.equals(username)&&passwordFromFile.equals(password))
				  {
					  if(localMac.equals(firstMacAddress)){
						  
								File outputFile=new File("./Save/"+usernameField.getText()+"/"+username+"_data.ih");
								  BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile));
								  String buff = bufferedReader.readLine();
								  Vector<String> tempVector = new Vector<String>();
								  while(buff!=""&&buff!=null){
									  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64("l".getBytes()).getBytes(), "ihelperforyou", salt)))){
										  tempVector.add(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64("s".getBytes()).getBytes(), "ihelperforyou", salt)));
									  }
									  else {
										tempVector.add(buff);
									}
									  buff=bufferedReader.readLine();
								  }
								  bufferedReader.close();
								  
								  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
								  for(int i=0;i<tempVector.size();i++)
								  {
									  bufferedWriter.write(tempVector.get(i).toString()+"\r\n");					  
								  }
								  bufferedWriter.flush();
								  bufferedWriter.close();			  
								  
							
						  return "ACK";
					  }
					  else return "SpareACK";//此时需要用户输入第一次登录时设置的备用密码
			  }
				  
				  else return "NAK";
			 }
			  
			  else if(firstFromFile.equals("s"))
			  {

				  if(usernameFromFile.equals(username)&&passwordFromFile.equals(password))
				  {
					  if(localMac.equals(firstMacAddress)){
						  return "ACK";
					  }
					  else return "SpareACK";//此时需要用户输入第一次登录时设置的备用密码
				  } 
				  else return "NAK";
			 }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Nothing";
}
	private JFrame mainFrame;
	private JLabel usernameJLabel;
	private JLabel passwordJLabel;
	private Container mainFrameContentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton registerButton;
	private JLabel hintStr;
	private JDialog owner;
	private String localMac;
	private String localOs;
	private String username;
	private String password;
	private String usernameFromFile;
	private String passwordFromFile;
	private String firstMacAddress;
	private String firstFromFile;
	
	public static void main(String[] args){	
		try {
			UserLogin userLogin = new UserLogin();
			userLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}