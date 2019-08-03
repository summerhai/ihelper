/*
 * Created by JFormDesigner on Wed Dec 26 22:03:26 CST 2012
 */

package com.ihelper.studymanage;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.ihelper.accountmanage.AccountManageQuestionFrame;
import com.ihelper.advicemanage.AdviceFrame;
import com.ihelper.moneymanage.MoneyManageFrame;
import com.ihelper.schedulemanage.ScheduleManageFrame;
import com.ihelper.studymanage.StudyNoteFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;
import com.ihelper.tools.Skins;

/**
 * @author Han LaiMing
 */
public class StudyNoteFrame  {

	public StudyNoteFrame(JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String username){

		mainJPanel.removeAll();
		mainFrame1=mainFrame;
		mainContainer1=mainContainer;
		mainJPanel1=mainJPanel;
		usernameString=username;	
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
		
		studyNoteScrollPane = new JScrollPane();
		message1Label = new JLabel();
		message2Label = new JLabel();
		addNewNoteButton = new JButton();
		searchTextField = new JTextField();
		searchButton = new JButton();
		targetButton = new JButton();
		messageButton = new JButton();
		accountButton = new JButton();
		scheduleButton = new JButton();
		studyButton = new JButton();
		moneyButton = new JButton();
		mainLabel = new JLabel();
		adviceButton = new JButton();
		copyRightLabel = new JLabel();
		targetLabel = new JLabel();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		
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
		//将文件名赋值给label
		//先判断是否存在文件夹,演示的时候只建一个文件。。。打开文件有错
		if(new File("./Save/"+usernameString+"/"+usernameString+"_studyNotes/").exists()){
			File file = new File("./Save/"+usernameString+"/"+usernameString+"_studyNotes/");
			File[] files = file.listFiles();
			fileName = new ArrayList<String>();	
			for(int i=0;i<files.length;i++) {
				    fileName.add(files[i].getName());
				}
			labels = new JLabel[fileName.size()];
			String fileNameString;
			for(int i=0,y=90;i<fileName.size();i++){

				labels[i] = new JLabel();
				mainJPanel1.add(labels[i]);
				labels[i].setText((i+1)+"."+fileName.get(i));
				labels[i].setFont(new Font("微软雅黑", 0, 20));
				labels[i].setForeground(Color.orange);
				labels[i].setBounds(120, y, 300, 80);
				y+=35;
				
				labels[i].setCursor(hander);
				
				fileNameString=fileName.get(i);			
				newFile =new File(fileNameString);
				labels[i].addMouseListener(new MouseAdapter() {
					public   void   mouseClicked(MouseEvent   e) {
						// TODO Auto-generated method stub
						String pathString = null;
						pathString = newFile.getPath();
						new AddStudyNoteFrame(usernameString, mainFrame1, mainContainer1, mainJPanel1,pathString);
						
					}
				});
			}
		}
		
		{
			mainContainer1 = mainFrame1.getContentPane();
			mainContainer1.setLayout(null);
			mainJPanel1.setLayout(null);

			{
				mainJPanel1.add(studyNoteScrollPane);
				studyNoteScrollPane.setBounds(80, 70, 510, 270);
				//首先判断是否已经有励志语及目标语句的信息
				File isFile=new File("./Save/"+usernameString+"/"+usernameString+"_studyNotes.ih");
				
				if(isFile.exists()){
					String message1Name;
					String message2Name;
					try {
						byte[] salt = PBECoder.initSalt();
						int startLine = 1,endLine = 2;
						List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_studyNotes.ih"), startLine, endLine);
						message1Name = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt))));
						message2Name = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine)), "ihelperforyou", salt))));
						
						
						mainJPanel1.add(message1Label);
						message1Label.setBounds(55, 70, 50, 265);
						message1Label.setText(message1Name);
						message1Label.setFont(new Font("微软雅黑",1,14));
						message1Label.setForeground(Color.RED);
						
						mainJPanel1.add(message2Label);
						message2Label.setBounds(605, 70, 50, 265);
						message2Label.setText(message2Name);
						message2Label.setFont(new Font("微软雅黑",1,14));
						message2Label.setForeground(Color.RED);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					mainJPanel1.add(message1Label);
					message1Label.setBounds(55, 70, 50, 265);
					
					mainJPanel1.add(message2Label);
					message2Label.setBounds(605, 70, 50, 265);
				}
				//此处添加判断是否已经有学习目标，如果有，用弹出窗口进行提示
				File targetFile = new File("./Save/"+usernameString+"/"+usernameString+"_studyNotesTarget.ih");
				if(targetFile.exists()){
					try {
						byte[] salt = PBECoder.initSalt();
						int startLine = 1,endLine = 4;
						List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_studyNotesTarget.ih"), startLine, endLine);
						String studyTarget = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt))));
						String startTime = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine)), "ihelperforyou", salt))));
						String endTime = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine+1)), "ihelperforyou", salt))));
						String remark = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine+2)), "ihelperforyou", salt))));
						   
						   
						SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd");   
						 Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间   
						String   str   =   formatter.format(curDate);   
						long lastDays = getDaySub(str, endTime);
						String alertMessage = "你的学习目标是："+studyTarget+",从"+startTime+"到"+endTime+",还剩下"+lastDays+"天,继续加油！";

						JOptionPane.showConfirmDialog(null, alertMessage, "提示", JOptionPane.WARNING_MESSAGE);
						targetLabel.setText(alertMessage);
						targetLabel.setFont(new Font("微软雅黑", 1, 14));
						targetLabel.setForeground(Color.red);
						targetLabel.setBounds(100, 5, 500, 40);
						mainJPanel1.add(targetLabel);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				

				//---- addNewNoteButton ----
				addNewNoteButton.setText("\u65b0\u5efa");
				mainJPanel1.add(addNewNoteButton);
				addNewNoteButton.setCursor(hander);
				addNewNoteButton.setBounds(125, 40, addNewNoteButton.getPreferredSize().width, 23);
				
				addNewNoteButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						File file = new File("./Save/"+usernameString+"/"+usernameString+"_studyNotes/");
						file.mkdir();
						new AddStudyNoteFrame(usernameString,mainFrame1,mainContainer1,mainJPanel1,null);
					}
				});
				
				mainJPanel1.add(searchTextField);
				searchTextField.setBounds(415, 40, 105, searchTextField.getPreferredSize().height);

				//---- searchButton ----
				searchButton.setText("GO！");
				mainJPanel1.add(searchButton);
				searchButton.setBounds(530, 40, 60, 23);
				searchButton.setCursor(hander);
				searchButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String searchString = searchTextField.getText();
						Boolean isFindBoolean = false;
						//从当前数据中查找相关数据
						for(int i=0;i<labels.length;i++){
								//找出与字符串相等或相近的数据
								if(isContain((String)labels[i].getText(), searchString)){
									labels[i].setForeground(Color.green);
									labels[i].setFont(new Font("微软雅黑",1,18));
									isFindBoolean = true;

							}
						} 
						if(!isFindBoolean){
							searchTextField.setText("");
							JOptionPane.showMessageDialog(null, "没有找到相关数据");
						}

					}
				});
				//---- deleteNoteButton ----
				targetButton.setText("学习目标");
				mainJPanel1.add(targetButton);
				targetButton.setBounds(220, 40, 80, 23);
				targetButton.setCursor(hander);
				targetButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//跳到学习目标
						new StudyTargetDialog(mainFrame1,mainContainer1,mainJPanel1,usernameString);
						
					}
				});
				
				//---- messageButton ----
				messageButton.setText("\u52b1\u5fd7\u8bed");
				mainJPanel1.add(messageButton);
				messageButton.setBounds(320, 40, 75, 23);
				messageButton.setCursor(hander);
				messageButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String message1 = JOptionPane.showInputDialog(null, "请输入你的第一句励志格言：", "学习无止境",JOptionPane.QUESTION_MESSAGE);
						String message2 = JOptionPane.showInputDialog(null, "请输入你的第二句励志格言：", "继续努力哟",JOptionPane.QUESTION_MESSAGE);
						String changedMessage1 = changeVertical(message1);
						String changedMessage2 = changeVertical(message2);
						message1Label.setText(changedMessage1);
						message2Label.setText(changedMessage2);
						message1Label.setFont(new Font("微软雅黑", 1, 14));
						message1Label.setForeground(Color.red);
						message2Label.setFont(new Font("微软雅黑", 1, 14));
						message2Label.setForeground(Color.red);
						try {
							File file1=new File("./Save/"+usernameString+"/"+usernameString+"_studyNotes.ih");
							FileOutputStream temp = new FileOutputStream(file1);
							temp.close();
							byte[] salt = PBECoder.initSalt();
							File file = new File("./Save/"+usernameString+"/"+usernameString+"_studyNotes.ih");
							FileWriter outputFile = new FileWriter(file, true);
							
							outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(message1Label.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n"); 				
							outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(message2Label.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");

							outputFile.close();
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
						//将励志语信息存到username_studyNote.ih												
					}
				});

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
	
	public Boolean isContain(String string,String subString){
		if(string.equals(subString)){
			return true;
		}
		else if(string.contains(subString)){
			return true;
		}
		else 
		return false;
		
	}
	
	public static long getDaySub(String beginDateStr,String endDateStr) 
	{ 
	long day=0; 
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	Date beginDate; 
	Date endDate; 
	try 
	{ 
	beginDate = format.parse(beginDateStr); 
	endDate= format.parse(endDateStr); 
	day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000); 
	//System.out.println("相隔的天数="+day); 
	} catch (ParseException e) 
	{ 
	// TODO 自动生成 catch 块 
	e.printStackTrace(); 
	} 
	return day; 
	} 

	/**
	 * @param message1
	 */
	private  String changeVertical(String message) {
		// 将字符串每一个字符都加<br>
		String changedMessage = null;
		try {
			ArrayList<String> messageList = new ArrayList<String>();
			messageList.add("<html>"+message.charAt(0)+"<br>");
			for(int i=1;i<message.length()-1;i++){
				messageList.add(message.charAt(i)+"<br>");
			}
			messageList.add(message.charAt(message.length()-1)+"</html>");
			changedMessage = messageList.get(0);
			for(int i=1;i<messageList.size();i++){
				changedMessage = changedMessage.concat(messageList.get(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return changedMessage;
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing

	private JScrollPane studyNoteScrollPane;
	private JLabel message1Label;
	private JLabel message2Label;
	private JButton addNewNoteButton;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton targetButton;
	private JButton messageButton;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
	private String usernameString;
	private JButton accountButton;
	private JButton scheduleButton;
	private JButton studyButton;
	private JButton moneyButton;
	private JLabel mainLabel;
	private JButton adviceButton;
	private JLabel copyRightLabel;
	private JLabel targetLabel;
	private ArrayList<String> fileName;
	private JLabel[] labels;
	private File newFile;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
