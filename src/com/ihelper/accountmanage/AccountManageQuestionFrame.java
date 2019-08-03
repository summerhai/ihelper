/*
 * Created by JFormDesigner on Sat Dec 15 18:50:12 CST 2012
 */

package com.ihelper.accountmanage;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import com.ihelper.advicemanage.AdviceFrame;
import com.ihelper.mainframe.MainFrame;
import com.ihelper.moneymanage.MoneyManageFrame;
import com.ihelper.schedulemanage.ScheduleManageFrame;
import com.ihelper.studymanage.StudyNoteFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;
import com.ihelper.tools.Skins;

/**
 * @author Han LaiMing
 */
public class AccountManageQuestionFrame  {
	private Container mainContainer1;
	private JFrame mainFrame1;
	private JPanel mainJPanel1;
	public AccountManageQuestionFrame(String username,String isFirst,JFrame mainFrame,Container mainContainer,JPanel mainJPanel){
		mainJPanel.removeAll();
		usernameString=username;
		mainContainer1=mainContainer;
		mainFrame1=mainFrame;
		mainJPanel1=mainJPanel;
		isString=isFirst;	
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
		label1 = new JLabel();
		questionLabel = new JLabel();
		answerLabel = new JLabel();
		answerTextField = new JTextField();
		questionTextField = new JTextField();
		okButton = new JButton();
		cancelButton = new JButton();
		noteLabel = new JLabel();
		
		accountButton = new JButton();
		scheduleButton = new JButton();
		studyButton = new JButton();
		moneyButton = new JButton();
		mainLabel = new JLabel();
		adviceButton = new JButton();
		questionText = new JLabel();
		copyRightLabel = new JLabel();
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
				
			}
		}
		//======== mainFrame ========
		{
			mainContainer1.setLayout(null);

			//======== accountManageJPanel ========
			{

				mainJPanel1.setLayout(null);

				//---- label1 ----
				label1.setText("\u63d0\u793a\uff1a\u67e5\u770b\u8d26\u53f7\u9700\u8981\u56de\u7b54\u4f60\u7684\u4e13\u5c5e\u95ee\u9898\uff08\u7b2c\u4e00\u6b21\u9700\u8981\u8bbe\u7f6e\u95ee\u9898\u54e6\uff09");
				mainJPanel1.add(label1);
				label1.setBounds(150, 100, 355, label1.getPreferredSize().height);

				//---- questionLabel ----
				questionLabel.setText("\u8bf7\u8f93\u5165\u4f60\u7684\u95ee\u9898\uff1a");
				mainJPanel1.add(questionLabel);
				questionLabel.setBounds(150, 140, 115, questionLabel.getPreferredSize().height);

				//---- answerLabel ----
				answerLabel.setText("\u95ee\u9898\u7684\u7b54\u6848\uff1a");
				mainJPanel1.add(answerLabel);
				answerLabel.setBounds(150, 170, 95, answerLabel.getPreferredSize().height);
				if(isString.equals("l")){
					//设置问题
					mainJPanel1.add(answerTextField);
					answerTextField.setBounds(260, 170, 175, 25);
					mainJPanel1.add(questionTextField);
					questionTextField.setBounds(260, 135, 175, 25);

					//---- okButton ----
					okButton.setText("\u786e\u5b9a");
					okButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//将问题和答案写入
							try {
								//如果用户此时没有设置问题和答案，关闭的话，以后访问会出问题，添加判断
								if(questionTextField.getText().equals("") || answerTextField.getText().equals("")){
									JOptionPane.showConfirmDialog(null, "问题或答案不能为空...", "账号访问问题提示", JOptionPane.WARNING_MESSAGE);
									return;
								}
								else {
									byte[] salt = PBECoder.initSalt();
									File file=new File("./Save/"+usernameString+"/"+usernameString+"_data.ih");
									FileWriter outputFile = new FileWriter(file, true);
									outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(questionTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
									outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(answerTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
									outputFile.close();
								}
								
							
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							System.out.println("问题与答案写入完毕");
							//数据文件中首位要改为s，否则回到主界面后还会重复设置问题，陷入死循环
							try {
								if(questionTextField.getText().equals("")){
									JOptionPane.showConfirmDialog(null, "问题不能为空...", "账号访问问题提示", JOptionPane.WARNING_MESSAGE);
									return;
								}
								else {
									byte[] salt = PBECoder.initSalt();
									File outputFile=new File("./Save/"+usernameString+"/"+usernameString+"_data.ih");
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
									  new AccountManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
								}
								
							} catch (FileNotFoundException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							//mainFrame1.setVisible(false);
							//new AccountManageFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);	
						}
					});
					mainJPanel1.add(okButton);
					okButton.setBounds(new Rectangle(new Point(320, 230), okButton.getPreferredSize()));

					//---- cancelButton ----
					cancelButton.setText("\u91cd\u7f6e");
					mainJPanel1.add(cancelButton);
					cancelButton.setBounds(400, 230, 57, 23);
					cancelButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							answerTextField.setText("");
						}
					});
				}
				if(isString.equals("s")){
					//回答问题
					mainJPanel1.add(answerTextField);
					answerTextField.setBounds(260, 170, 175, 25);
					
					noteLabel.setBounds(240, 140, 175, 25);
					mainJPanel1.add(noteLabel);
					
					int startLine = 1,endLine = 9;
					try {
						byte[] salt = PBECoder.initSalt();
						List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_data.ih"), startLine, endLine);
						questionText.setText(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(endLine-2)), "ihelperforyou", salt)))));
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					mainJPanel1.add(questionText);
					questionText.setBounds(260, 135, 175, 25);
					
					//---- okButton ----
					okButton.setText("\u786e\u5b9a");
					okButton.setCursor(hander);
					okButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//回答问题
							int startLine = 1,endLine = 9;
							List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_data.ih"), startLine, endLine);
							byte[] salt;
							try {
								salt = PBECoder.initSalt();
								if(answerTextField.getText().equals(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(endLine-1)), "ihelperforyou", salt)))))){
									//此处需要跳转到账号管理的主界面
									new AccountManageFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);						
								}
								else {
									answerTextField.setText("");
									JOptionPane.showConfirmDialog(null, "您输入的问题答案不正确，再想想？", "提示", JOptionPane.WARNING_MESSAGE);
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
					});
					mainJPanel1.add(okButton);
					okButton.setBounds(new Rectangle(new Point(320, 230), okButton.getPreferredSize()));

					//---- cancelButton ----
					cancelButton.setText("\u91cd\u7f6e");
					mainJPanel1.add(cancelButton);
					cancelButton.setCursor(hander);
					cancelButton.setBounds(400, 230, 57, 23);
					cancelButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							answerTextField.setText("");
						}
					});
				}
				

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
			mainFrame1.pack();
			mainFrame1.setLocationRelativeTo(mainFrame1.getOwner());
			mainFrame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JLabel label1;
	private JLabel questionLabel;
	private JLabel answerLabel;
	private JTextField answerTextField;
	private JLabel noteLabel;
	private JButton okButton;
	private JButton cancelButton;
	private String usernameString;
	private String isString;
	private JButton accountButton;
	private JButton scheduleButton;
	private JButton studyButton;
	private JButton moneyButton;
	private JLabel mainLabel;
	private JButton adviceButton;
	private JLabel copyRightLabel;
	private JTextField questionTextField;
	private JLabel questionText;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
