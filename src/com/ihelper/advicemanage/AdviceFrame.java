/*
 * Created by JFormDesigner on Sat Dec 29 00:56:52 CST 2012
 */

package com.ihelper.advicemanage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.ihelper.accountmanage.AccountManageQuestionFrame;
import com.ihelper.advicemanage.AdviceFrame;
import com.ihelper.mainframe.MainFrame;
import com.ihelper.moneymanage.MoneyManageFrame;
import com.ihelper.schedulemanage.ScheduleManageFrame;
import com.ihelper.studymanage.StudyNoteFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.Email_Autherticator;
import com.ihelper.tools.Mail;
import com.ihelper.tools.PBECoder;
import com.ihelper.tools.RunBrowser;
import com.ihelper.tools.Skins;

/**
 * @author Han LaiMing
 */
public class AdviceFrame  {

	public AdviceFrame(JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String username){
		mainJPanel.removeAll();
		usernameString=username;
		mainFrame1=mainFrame;
		mainContainer1=mainContainer;
		mainJPanel1=mainJPanel;
		initComponents();
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

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		adviceLabel = new JLabel();
		adviceScrollPane = new JScrollPane();
		adviceTextArea = new JTextArea();
		submitButton = new JButton();
		emailLabel = new JLabel();
		emailTextField = new JTextField();
		emailPasswordLabel = new JLabel();
		emailPasswordField = new JPasswordField();
		accountButton = new JButton();
		scheduleButton = new JButton();
		studyButton = new JButton();
		moneyButton = new JButton();
		mainLabel = new JLabel();
		adviceButton = new JButton();
		copyRightLabel = new JLabel();
		blogLabel = new JLabel();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		accountButton.setCursor(hander);
		scheduleButton.setCursor(hander);
		studyButton.setCursor(hander);
		moneyButton.setCursor(hander);
		mainLabel.setCursor(hander);
		adviceButton.setCursor(hander);
		
		{
			//======== mainFrame ========
			{
				mainFrame1.setTitle("ihelper");
				mainFrame1.setName("mainFrame");
				mainFrame1.setVisible(true);
				mainFrame1.setResizable(false);
				mainFrame1.setPreferredSize(new Dimension(820, 530));
				mainContainer1 = mainFrame1.getContentPane();
				mainContainer1.setLayout(null);

				//---- accountButton ----
				accountButton.setText("\u8d26\u53f7\u7ba1\u7406");
				accountButton.setAutoscrolls(true);
				accountButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						mainJPanel1.removeAll();
						try {
							int startLine = 1,endLine = 6;
							byte[] salt = PBECoder.initSalt();
							List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_data.ih"), startLine, endLine);
							if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt)))).equals("l")){
								System.out.println("设置问题");
								new AccountManageQuestionFrame(usernameString,"l",mainFrame1,mainContainer1,mainJPanel1);//设置问题
											
							}
							if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt)))).equals("s")){
								System.out.println("回答问题");
								new AccountManageQuestionFrame(usernameString,"s",mainFrame1,mainContainer1,mainJPanel1);//回答问题
								
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
				mainContainer1.add(accountButton);
				accountButton.setBounds(30, 60, 90, 25);

				//---- scheduleButton ----
				scheduleButton.setText("\u65e5\u7a0b\u5b89\u6392");
				scheduleButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						mainJPanel1.removeAll();
						new ScheduleManageFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);
					}
				});
				mainContainer1.add(scheduleButton);
				scheduleButton.setBounds(30, 130, 90, 25);

				//---- studyButton ----
				studyButton.setText("\u5b66\u4e60\u7b14\u8bb0");
				mainContainer1.add(studyButton);
				studyButton.setBounds(30, 200, 90, 25);
				studyButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						//先跳到学习笔记界面，类似于日志的那种，点击标题可以进入编辑页面，新建也是跳到xdoc界面
						mainJPanel1.removeAll();
						new StudyNoteFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);
					}
				});

				//---- moneyButton ----
				moneyButton.setText("\u8d22\u52a1\u7ba1\u7406");
				mainContainer1.add(moneyButton);
				moneyButton.setBounds(30, 270, 90, 25);
				moneyButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						mainJPanel1.removeAll();
						new MoneyManageFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);
					}
				});

				//---- mainLabel ----
				mainLabel.setText("\u4e13\u4e3a\u5b66\u751f\u7fa4\u4f53\u8bbe\u8ba1\u7684\u7684\u667a\u80fd\u52a9\u7406\u8f6f\u4ef6ihelper");
				mainLabel.setForeground(Color.green);
				mainContainer1.add(mainLabel);
				mainLabel.setBounds(60, 5, 380, 50);

				//---- adviceButton ----
				adviceButton.setText("\u5efa\u8bae\u53cd\u9988");
				mainContainer1.add(adviceButton);
				adviceButton.setBounds(30, 340, 90, 25);
				adviceButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						new AdviceFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);
					}
				});
				
				copyRightLabel.setText("Copyright © 2013 Han Laiming");
				copyRightLabel.setForeground(Color.blue);
				mainContainer1.add(copyRightLabel);
				copyRightLabel.setBounds(360, 450, 190, 50);
							
				mainContainer1.add(mainJPanel1);
				mainJPanel1.setBounds(125, 70, 665, 390);

			}
		}
		//======== mainFrame ========
		{
			mainContainer1.setLayout(null);

			//======== adviceManageJPanel ========
			{

				mainJPanel1.setLayout(null);

				//---- adviceLabel ----
				adviceLabel.setText("<html>\u5982\u679c\u60a8\u53d1\u73b0ihelper\u7684\u4efb\u4f55\u4e0d\u8db3\uff0c\u6216\u8005\u60a8\u5bf9\u6211\u4eec\u7684\u4ea7\u54c1\u6709\u4efb\u4f55\u7684\u5efa\u8bae\uff0c\u6211\u90fd\u5c06\u865a\u5fc3\u63a5\u53d7\uff0c<br>\u597d\u7684\u4ea7\u54c1\u5e94\u8be5\u80fd\u591f\u63a5\u53d7\u5b9e\u8df5\u7684\u68c0\u9a8c\u3002<br>\u5982\u8fd8\u6709\u4efb\u4f55\u7591\u95ee\uff0c\u8bf7\u53d1\u90ae\u4ef6\u81f3ihelper_foryou@126.com\uff0c\u6211\u4f1a\u6089\u5fc3\u4e3a\u60a8\u89e3\u7b54\uff0c\u795d\u60a8\u4f7f\u7528\u6109\u5feb\uff01");
				mainJPanel1.add(adviceLabel);
				adviceLabel.setBounds(70, 15, 555, 70);
				blogLabel.setText("欢迎留言我的博客http://blog.csdn.net/minglaihan/article/details/8764415");
				
				blogLabel.setCursor(hander);
				blogLabel.setForeground(Color.blue);
				blogLabel.setBounds(70, 70, 500, 50);
				mainJPanel1.add(blogLabel);
				blogLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						new RunBrowser().runBroswer();
					}
				});
				//======== adviceScrollPane ========
				{
					adviceScrollPane.setViewportView(adviceTextArea);
				}
				mainJPanel1.add(adviceScrollPane);
				adviceScrollPane.setBounds(75, 110, 455, 140);

				//---- submitButton ----
				submitButton.setText("\u63d0\u4ea4");
				mainJPanel1.add(submitButton);
				submitButton.setBounds(445, 300, 70, 30);
				submitButton.setCursor(hander);
				submitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						//根据用户输入的邮箱和密码发送反馈信息到ihelper_foryou@126.com
						//先存入信息，email，password，advice
						try {
							File file=new File("./Save/"+usernameString+"/"+usernameString+"_advice.ih");
							FileWriter outputFile = new FileWriter(file, true);
							byte[] salt = PBECoder.initSalt();
							outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(emailTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
							outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(emailPasswordField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
							outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(adviceTextArea.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
							
							outputFile.close();
							System.out.println("建议数据写入完毕");

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Email_Autherticator email_Autherticator = new Email_Autherticator(emailTextField.getText(), emailPasswordField.getText().toString());
						email_Autherticator.getPasswordAuthentication();
						new Mail(usernameString,emailTextField.getText(),adviceTextArea.getText(),email_Autherticator);
						JOptionPane.showConfirmDialog(null, "您的宝贵建议我们已收到，我们一定会妥善处理，谢谢您的支持！", "发送成功", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
						adviceTextArea.setText("");
						emailTextField.setText("");
						emailPasswordField.setText("");
					}
				});

				//---- emailLabel ----
				emailLabel.setText("\u60a8\u7684\u90ae\u7bb1\uff1a");
				mainJPanel1.add(emailLabel);
				emailLabel.setBounds(90, 270, 90, emailLabel.getPreferredSize().height);
				mainJPanel1.add(emailTextField);
				emailTextField.setBounds(200, 270, 195, emailTextField.getPreferredSize().height);

				//---- emailPasswordLabel ----
				emailPasswordLabel.setText("\u90ae\u7bb1\u7684\u5bc6\u7801\uff1a");
				mainJPanel1.add(emailPasswordLabel);
				emailPasswordLabel.setBounds(90, 305, 90, 15);
				mainJPanel1.add(emailPasswordField);
				emailPasswordField.setBounds(200, 305, 195, 21);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < mainJPanel1.getComponentCount(); i++) {
						Rectangle bounds = mainJPanel1.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = mainJPanel1.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					mainJPanel1.setMinimumSize(preferredSize);
					mainJPanel1.setPreferredSize(preferredSize);
				}
			}
			mainContainer1.add(mainJPanel1);
			mainJPanel1.setBounds(125, 70, 665, 420);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < mainContainer1.getComponentCount(); i++) {
					Rectangle bounds = mainContainer1.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = mainContainer1.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				mainContainer1.setMinimumSize(preferredSize);
				mainContainer1.setPreferredSize(preferredSize);
			}
			mainFrame1.pack();
			mainFrame1.setLocationRelativeTo(mainFrame1.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JLabel adviceLabel;
	private JScrollPane adviceScrollPane;
	private JTextArea adviceTextArea;
	private JButton submitButton;
	private JLabel emailLabel;
	private JTextField emailTextField;
	private JLabel emailPasswordLabel;
	private JPasswordField emailPasswordField;
	private String usernameString;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
	private JButton accountButton;
	private JButton scheduleButton;
	private JButton studyButton;
	private JButton moneyButton;
	private JLabel mainLabel;
	private JButton adviceButton;
	private JLabel copyRightLabel;
	private JLabel blogLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
