/*
 * Created by JFormDesigner on Sat Dec 15 20:10:10 CST 2012
 */

package com.ihelper.mainframe;

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

import javax.swing.*;

import com.ihelper.accountmanage.AccountManageQuestionFrame;
import com.ihelper.advicemanage.AdviceFrame;
import com.ihelper.mainframe.MainFrame;
import com.ihelper.moneymanage.MoneyManageFrame;
import com.ihelper.schedulemanage.ScheduleManageFrame;
import com.ihelper.studymanage.StudyNoteFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;
import com.ihelper.tools.RunBrowser;
import com.ihelper.tools.Skins;


/**
 * @author Han LaiMing
 */
public class MainFrame  {
	
	public MainFrame(String username){
		usernameString=username;
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        Skins.setThemeNebulaBrickWall();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initComponents();
			}
		});
		
	}

	private void accountButtonMouseClicked(MouseEvent e) {
		
		int startLine = 1,endLine = 6;
		List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_data.ih"), startLine, endLine);
		byte[] salt;
		try {
			salt = PBECoder.initSalt();
			if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt)))).equals("l")){
				System.out.println("设置问题");
				new AccountManageQuestionFrame(usernameString,"l",mainFrame,mainContainer,mainJPanel);//设置问题
			}
			
			if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt)))).equals("s")){
				System.out.println("回答问题");
				new AccountManageQuestionFrame(usernameString,"s",mainFrame,mainContainer,mainJPanel);//回答问题
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}


	//怎样从文件中读取指定行的数据
	 public static List<String> getFileContent(String path) {
		  List<String> strList = new ArrayList<String>();
		  try {
		   File file = new File(path);
		   InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
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
		mainFrame = new JFrame();
		accountButton = new JButton();
		scheduleButton = new JButton();
		studyButton = new JButton();
		moneyButton = new JButton();
		mainLabel = new JLabel();
		adviceButton = new JButton();
		mainJPanel = new JPanel();
		copyRightLabel = new JLabel();
		themeBox = new JComboBox();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		accountButton.setCursor(hander);
		scheduleButton.setCursor(hander);
		studyButton.setCursor(hander);
		moneyButton.setCursor(hander);
		mainLabel.setCursor(hander);
		adviceButton.setCursor(hander);
		themeBox.setCursor(hander);
		

		//======== mainFrame ========
		{
			mainFrame.setTitle("ihelper");
			mainFrame.setName("mainFrame");
			mainFrame.setVisible(true);
			mainFrame.setResizable(false);
			mainFrame.setPreferredSize(new Dimension(820, 530));
			mainContainer = mainFrame.getContentPane();
			mainContainer.setLayout(null);

			//---- accountButton ----
			accountButton.setText("\u8d26\u53f7\u7ba1\u7406");
			accountButton.setAutoscrolls(true);
			accountButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mainJPanel.removeAll();
					accountButtonMouseClicked(e);
				}
			});
			mainContainer.add(accountButton);
			accountButton.setBounds(30, 60, 90, 25);

			//---- scheduleButton ----
			scheduleButton.setText("\u65e5\u7a0b\u5b89\u6392");
			scheduleButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mainJPanel.removeAll();
					new ScheduleManageFrame(mainFrame,mainContainer,mainJPanel,usernameString);
				}
			});
			mainContainer.add(scheduleButton);
			scheduleButton.setBounds(30, 130, 90, 25);

			//---- studyButton ----
			studyButton.setText("\u5b66\u4e60\u7b14\u8bb0");
			mainContainer.add(studyButton);
			studyButton.setBounds(30, 200, 90, 25);
			studyButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//先跳到学习笔记界面，类似于日志的那种，点击标题可以进入编辑页面，新建也是跳到xdoc界面
					mainJPanel.removeAll();
					new StudyNoteFrame(mainFrame,mainContainer,mainJPanel,usernameString);
				}
			});

			//---- moneyButton ----
			moneyButton.setText("\u8d22\u52a1\u7ba1\u7406");
			mainContainer.add(moneyButton);
			moneyButton.setBounds(30, 270, 90, 25);
			moneyButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					mainJPanel.removeAll();
					new MoneyManageFrame(mainFrame,mainContainer,mainJPanel,usernameString);
				}
			});

			//---- mainLabel ----
			mainLabel.setText("\u4e13\u4e3a\u5b66\u751f\u7fa4\u4f53\u8bbe\u8ba1\u7684\u7684\u667a\u80fd\u52a9\u7406\u8f6f\u4ef6ihelper");
			mainLabel.setForeground(Color.green);
			mainContainer.add(mainLabel);
			mainLabel.setBounds(60, 5, 380, 50);
			mainLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					new RunBrowser().runBroswer();
				}
			});
			
			
			

			themeBox .setBounds(600, 15, 150, 30);
			themeBox.addItem("设置主题");
			themeBox.addItem("绚烂之花");
			themeBox.addItem("宁静夜空");
			themeBox.addItem("魅力射手");
			themeBox.addItem("无尽蓝天");
			themeBox.addItem("草原之美");
			themeBox.addItem("阳光之下");
			themeBox.addItem("宁静竹筏");
			themeBox.addItem("巨船来袭");
			themeBox.addItem("清澈海洋");
			themeBox.addItem("蓝色之爱");
			themeBox.addItem("两极之美");			
			mainContainer.add(themeBox);

			themeBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					// TODO Auto-generated method stub
					if(themeBox.getSelectedItem().equals("绚烂之花")){
						Skins.setThemeOfficeBlue();
					}
					else if(themeBox.getSelectedItem().equals("宁静夜空")){
						Skins.setThemeBusiness();
					}
					else if(themeBox.getSelectedItem().equals("魅力射手")){
						Skins.setThemeBusinessBlackSteel();
					}
					else if(themeBox.getSelectedItem().equals("无尽蓝天")){
						Skins.setThemeCreme();
					}
					else if(themeBox.getSelectedItem().equals("草原之美")){
						Skins.setThemeEmeraldDusk();
					}
					else if(themeBox.getSelectedItem().equals("阳光之下")){
						Skins.setThemeSahara();
					}
					else if(themeBox.getSelectedItem().equals("宁静竹筏")){
						Skins.setThemeRaven();
					}
					else if(themeBox.getSelectedItem().equals("巨船来袭")){
						Skins.setThemeOfficeSilver();
					}
					else if(themeBox.getSelectedItem().equals("清澈海洋")){
						Skins.setThemeNebula();
					}
					else if(themeBox.getSelectedItem().equals("蓝色之爱")){
						Skins.setThemeNebulaBrickWall();
					}
					else if(themeBox.getSelectedItem().equals("两极之美")){
						Skins.setThemeAutumn();
					}
					//将用户最终选择的主题存入到theme中，在开始读取数据是否存在主题，如存在则读取显示
					try {
						File file1=new File("./Save/"+usernameString+"/"+usernameString+"_theme.ih");
						FileOutputStream temp = new FileOutputStream(file1);
						temp.close();
						File file=new File("./Save/"+usernameString+"/"+usernameString+"_theme.ih");
						FileWriter outputFile = new FileWriter(file, true);
						byte[] salt = PBECoder.initSalt();
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(themeBox.getSelectedItem().toString().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.close(); 					
						System.out.println("主题数据写入完毕");
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}

				}
			});
			//---- adviceButton ----
			adviceButton.setText("\u5efa\u8bae\u53cd\u9988");
			mainContainer.add(adviceButton);
			adviceButton.setBounds(30, 340, 90, 25);
			adviceButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					new AdviceFrame(mainFrame,mainContainer,mainJPanel,usernameString);
				}
			});
			
			copyRightLabel.setText("Copyright © 2013 Han Laiming");
			copyRightLabel.setForeground(Color.blue);
			mainContainer.add(copyRightLabel);
			copyRightLabel.setBounds(360, 450, 190, 50);
			
			
			mainContainer.add(mainJPanel);
			mainJPanel.setBounds(125, 70, 665, 390);
			//判断文件中是否有主题数据,会出现UI问题
			File outputFile=new File("./Save/"+usernameString+"/"+usernameString+"_theme.ih");
			if(outputFile.exists()){
			try {
				System.out.println("start 1");
				 byte[] salt = PBECoder.initSalt();
//				
					int startLine = 1,endLine = 1;
					List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_theme.ih"), startLine, endLine);
					System.out.println("start 2");
					String themeString = new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), "ihelperforyou", salt))));
					System.out.println(themeString);
					System.out.println("start 3");
					if(themeString.equals("绚烂之花")){
						System.out.println("start 4");
						Skins.setThemeOfficeBlue();
					}
					else if(themeString.equals("宁静夜空")){
						Skins.setThemeBusiness();
					}
					else if(themeString.equals("魅力射手")){
						Skins.setThemeBusinessBlackSteel();
					}
					else if(themeString.equals("无尽蓝天")){
						Skins.setThemeCreme();
					}
					else if(themeString.equals("草原之美")){
						System.out.println("what?");
						Skins.setThemeEmeraldDusk();
					}
					else if(themeString.equals("阳光之下")){
						Skins.setThemeSahara();
					}
					else if(themeString.equals("宁静竹筏")){
						Skins.setThemeRaven();
					}
					else if(themeString.equals("巨船来袭")){
						Skins.setThemeOfficeSilver();
					}
					else if(themeString.equals("清澈海洋")){
						Skins.setThemeNebula();
					}
					else if(themeString.equals("蓝色之爱")){
						System.out.println("ff");
						Skins.setThemeNebulaBrickWall();
					}
					else if(themeString.equals("两极之美")){
						Skins.setThemeAutumn();
					}
					else 
					{
						Skins.setThemeEmeraldDusk();
					}
			} catch (FileNotFoundException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < mainContainer.getComponentCount(); i++) {
					Rectangle bounds = mainContainer.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = mainContainer.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				mainContainer.setMinimumSize(preferredSize);
				mainContainer.setPreferredSize(preferredSize);
			}
			mainFrame.pack();
			mainFrame.setLocationRelativeTo(mainFrame.getOwner());
			mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JFrame mainFrame;
	private JButton accountButton;
	private JButton scheduleButton;
	private JButton studyButton;
	private JButton moneyButton;
	private JLabel mainLabel;
	private JButton adviceButton;
	private JPanel mainJPanel;
	private Container mainContainer;
	private String usernameString;
	private JLabel copyRightLabel;
	private JComboBox themeBox;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
