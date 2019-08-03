/*
 * Created by JFormDesigner on Sun Dec 16 12:02:59 CST 2012
 */

package com.ihelper.schedulemanage;

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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ihelper.accountmanage.AccountManageQuestionFrame;
import com.ihelper.advicemanage.AdviceFrame;
import com.ihelper.moneymanage.MoneyManageFrame;
import com.ihelper.schedulemanage.ScheduleManageFrame;
import com.ihelper.studymanage.StudyNoteFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;
import com.ihelper.tools.MyAlarm;
import com.ihelper.tools.Skins;

/**
 * @author Han LaiMing
 */
public class ScheduleManageFrame {

	public ScheduleManageFrame(JFrame mainFrame,Container mainFrameContentPane,JPanel mainJPanel,String username) {
		mainJPanel.removeAll();
		mainFrame1=mainFrame;
		mainContainer1=mainFrameContentPane;
		mainJPanel1=mainJPanel;
		usernameString=username;
		initComponents();
	}

	private void addNewScheduleButtonMouseClicked(MouseEvent e) {
		// TODO add your code here
		new AddScheduleFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);
	}

	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	private void addNewLessonsButtonMouseClicked(MouseEvent e) {
		// TODO add your code here
		String lines = JOptionPane.showInputDialog(mainFrame1, "请输入一天的课总节数：");
		System.out.println(lines);
		if(isNum(lines)){
			int lineNumber = Integer.parseInt(lines);
			new AddLessonsFrame(usernameString,lineNumber,mainFrame1,mainContainer1,mainJPanel1);
		}
		else {
			JOptionPane.showMessageDialog(mainFrame1, "请输入数字");
		}
		
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
	
	private void showTable() {
		// TODO Auto-generated method stub
		//从username_schedule.ih文件中读取账户数据，显示在JTable里
		//添加功能按钮：添加提醒
		
				String[] scheduleTitle = {"待办事件" ,"时间","备注", "类型"};
				String[] lessonTitle = {"","周一","周二","周三","周四","周五","周六","周日"};
				try {
					byte[] salt = PBECoder.initSalt();
					int scheduleLines=0,lessonLines=0;//计算文件中的行数
					if(!new File("./Save/"+usernameString+"/"+usernameString+"_lesson.ih").exists()){
					}//判断是否有lesson文件，如果没有，说明还没有新建课表，此时是空的
					else {
						File lessonFile = new File("./Save/"+usernameString+"/"+usernameString+"_lesson.ih");
						BufferedReader lessonReader = new BufferedReader(new FileReader(lessonFile));
						String lessonString;
						while((lessonString   =   lessonReader.readLine())   !=   null)   {   
				              if(lessonString   !=  null )   
				              {   
				                    lessonLines++; 
				              }
				          }
						System.out.println(lessonLines);
						String[][] lessonTableData = new String[lessonLines/8][8];
						List<String> lessonList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_lesson.ih"), 1, lessonLines);
						
						//将文件中1,6,12,17<i行等数据取出来，是待办事项名
						ArrayList<String> lessonNumberList = new ArrayList<String>();
						ArrayList<String> mondayLessonsList = new ArrayList<String>();
						ArrayList<String> tuesdayLessonsList = new ArrayList<String>();
						ArrayList<String> wednesdayLessonList = new ArrayList<String>();
						ArrayList<String> thursdayLessonsList = new ArrayList<String>();
						ArrayList<String> fridaydayLessonsList = new ArrayList<String>();
						ArrayList<String> saturdayLessonsList = new ArrayList<String>();
						ArrayList<String> sundayLessonsList = new ArrayList<String>();
						
						//将List里的数据整理到一个String[][]二维数组里面
						
						ArrayList<?> lessonLists[] = new ArrayList[8];
						lessonLists[0] = lessonNumberList;
						lessonLists[1] = mondayLessonsList;
						lessonLists[2] = tuesdayLessonsList;
						lessonLists[3] = wednesdayLessonList;
						lessonLists[4] = thursdayLessonsList;
						lessonLists[5] = fridaydayLessonsList;
						lessonLists[6] = saturdayLessonsList;
						lessonLists[7] = sundayLessonsList;
						
						for(int j=0;j<lessonLines;){
							lessonNumberList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						for(int j=1;j<lessonLines;){
							mondayLessonsList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						for(int j=2;j<lessonLines;){
							tuesdayLessonsList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						for(int j=3;j<lessonLines;){
							wednesdayLessonList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						for(int j=4;j<lessonLines;){
							thursdayLessonsList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						for(int j=5;j<lessonLines;){
							fridaydayLessonsList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						for(int j=6;j<lessonLines;){
							saturdayLessonsList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						for(int j=7;j<lessonLines;){
							sundayLessonsList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(lessonList.get(j)), "ihelperforyou", salt)))));
							j=j+8;
						}
						
						//行数为,列数为,设置表格数据不可编辑
						//因为没有给tableData设置大小，编译器在这里不给赋值，丫的,解决办法：在确定i之后，初始化tableData数组大小
						for(int k=0;k<lessonLines/8;k++){
							for(int j=0;j<8;j++){
								lessonTableData[k][j] = (String)lessonLists[j].get(k);
							}
						}
						TableModel model = new DefaultTableModel(lessonTableData, lessonTitle);
						lessonTable = new JTable(model);
						RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
						lessonTable.setRowSorter(sorter);
	
					}
					//显示日程的数据表
					File scheduleFile = new File("./Save/"+usernameString+"/"+usernameString+"_schedule.ih");					
					BufferedReader scheduleReader = new BufferedReader(new FileReader(scheduleFile));
					String   scheduleString;   
					while((scheduleString   =   scheduleReader.readLine())   !=   null)   {   
			              if(scheduleString   !=  null )   
			              {   
			                    scheduleLines++; 
			              }
			          }
					
					String[][] scheduleTableData = new String[scheduleLines/4][4];

					List<String> scheduleList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_schedule.ih"), 1, scheduleLines);
					
					
					//将文件中1,6,12,17<i行等数据取出来，是待办事项名
					ArrayList<String> scheduleNameList = new ArrayList<String>();
					//将文件中2,7,13,18<i行等数据取出来，是时间
					ArrayList<String> timeNameList = new ArrayList<String>();
					//将文件中3,8,14,19<i行等数据取出来，是备注
					ArrayList<String> remarkList = new ArrayList<String>();
					//将文件中4,9,15,20<i行等数据取出来，是类型
					ArrayList<String> chooseTypeList = new ArrayList<String>();
					
					for(int j=0;j<scheduleLines;){
						scheduleNameList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(scheduleList.get(j)), "ihelperforyou", salt)))));
						j=j+4;
					}
					for(int j=1;j<scheduleLines;){
						timeNameList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(scheduleList.get(j)), "ihelperforyou", salt)))));
						j=j+4;
					}
					for(int j=2;j<scheduleLines;){
						remarkList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(scheduleList.get(j)), "ihelperforyou", salt)))));
						j=j+4;
					}
					for(int j=3;j<scheduleLines;){
						chooseTypeList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(scheduleList.get(j)), "ihelperforyou", salt)))));
						j=j+4;
					}
					//将List里的数据整理到一个String[][]二维数组里面
					
					ArrayList<String> scheduleLists[] = new ArrayList[4];
					scheduleLists[0] = scheduleNameList;
					scheduleLists[1] = timeNameList;
					scheduleLists[2] = remarkList;
					scheduleLists[3] = chooseTypeList;
					
					//行数为i/6,列数为5,设置表格数据不可编辑
					//因为没有给tableData设置大小，编译器在这里不给赋值，丫的,解决办法：在确定i之后，初始化tableData数组大小
					for(int k=0;k<scheduleLines/4;k++){
						for(int j=0;j<4;j++){
							scheduleTableData[k][j] = (String)scheduleLists[j].get(k);
						}
					}
					TableModel model = new DefaultTableModel(scheduleTableData, scheduleTitle);
					scheduleTable = new JTable(model);
					RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
					scheduleTable.setRowSorter(sorter);
		
				} catch (Exception e) {
					// TODO: handle exception
				}
	}
	
	private void accountButtonMouseClicked(MouseEvent e) {
		
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
	
	public void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		
		scrollPane1 = new JScrollPane(scheduleLabel);
		scheduleTable = new JTable();
		scrollPane2 = new JScrollPane(lessonLabel);
		lessonTable = new JTable();
		addNewScheduleButton = new JButton();
		addNewLessonsButton = new JButton();
		deleteButton = new JButton();
		scheduleLabel = new JLabel();
		lessonLabel = new JLabel();
		scheduleDialog = new JDialog(mainFrame1);
		lessonDialog = new JDialog(mainFrame1);
		accountButton = new JButton();
		scheduleButton = new JButton();
		studyButton = new JButton();
		moneyButton = new JButton();
		mainLabel = new JLabel();
		adviceButton = new JButton();
		copyRightLabel = new JLabel();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		accountButton.setCursor(hander);
		scheduleButton.setCursor(hander);
		studyButton.setCursor(hander);
		moneyButton.setCursor(hander);
		mainLabel.setCursor(hander);
		adviceButton.setCursor(hander);

		showTable();
		
		scheduleTable.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked(MouseEvent e)
		{
		if(e.getClickCount()==1)
		{	 rowCount=scheduleTable.getSelectedRow();
		}
		}
		});
		//======== mainFrame ========
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
						accountButtonMouseClicked(e);
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
			mainContainer1.setLayout(null);

			//======== mainJPanel ========
			{
				mainJPanel1.setLayout(null);

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(scheduleTable);
				}
				
				mainJPanel1.add(scrollPane1);
				scrollPane1.setBounds(30, 55, 285, 270);
				scheduleTable.addMouseListener(new MouseAdapter() {
					public void mouseClieked(){
						
					}
				});

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(lessonTable);
				}
				mainJPanel1.add(scrollPane2);
				scrollPane2.setBounds(330, 55, 315, 270);				
				lessonTable.addMouseListener(new MouseAdapter() {
					public void mouseClieked(){
						
					}
				});

				//---- addNewScheduleButton ----
				addNewScheduleButton.setText("\u65b0\u5efa");
				addNewScheduleButton.setCursor(hander);
				addNewScheduleButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						addNewScheduleButtonMouseClicked(e);
					}
				});
				mainJPanel1.add(addNewScheduleButton);
				addNewScheduleButton.setBounds(new Rectangle(new Point(85, 25), addNewScheduleButton.getPreferredSize()));
				
				deleteButton.setText("删除");
				deleteButton.setCursor(hander);
				deleteButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(rowCount == -1){
							JOptionPane.showMessageDialog(null, "你没有选择要删除的数据哦");
						}
						String scheduleNameString = (String)scheduleTable.getValueAt(rowCount, 0);
						String timeString = (String)scheduleTable.getValueAt(rowCount, 1);
						String remarkString = (String)scheduleTable.getValueAt(rowCount, 2);
						String chooseTypeString = (String)scheduleTable.getValueAt(rowCount, 3);
			
						String confirmString = "您确定要删除这条备忘数据吗";
						int click = JOptionPane.showConfirmDialog(null, confirmString,"删除",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
						if(click == JOptionPane.YES_NO_OPTION){
							//删除就是将源文件读出，然后识别出要删除的数据，尤其是首位，不写入文件就行了
							try {
								int count = 0;
								Boolean isFindNetName = false;
								File outputFile=new File("./Save/"+usernameString+"/"+usernameString+"_schedule.ih");
								  BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile));
								  String buff = bufferedReader.readLine();
								  Vector<String> tempVector = new Vector<String>();
								 byte[] salt = PBECoder.initSalt();
								  while(buff!=null&&!isFindNetName){
									  
										  //优先判定日程所在名是否匹配
									  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(scheduleNameString.getBytes()).getBytes(), "ihelperforyou", salt)))){
											  isFindNetName = true;
											  count =1;
										  }
										  else {
												tempVector.add(buff);
											}								
									  buff=bufferedReader.readLine();
								  }
								  while(buff!=null&&isFindNetName){
									  
										  if(count != 4){
											  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(timeString.getBytes()).getBytes(), "ihelperforyou", salt)))){
													count =2;
												  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remarkString.getBytes()).getBytes(), "ihelperforyou", salt)))){
													  count =3;
												  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(chooseTypeString.getBytes()).getBytes(), "ihelperforyou", salt)))){
													  count =4;
												  }
										  }
										  else {
											tempVector.add(buff);
									  }						  
									  buff=bufferedReader.readLine();
								  }
								  bufferedReader.close();
								  //此处需要判断要删除数据是否是最后一条账号信息，如果是,最后一行不加\n，证实可行；
								  //但是如果不是最后一条账户删除后，文件里多了一个空行，导致数据读取出错
								  //所删除的账号下边多一个空行，shandiaojik
								  BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
								  for(int i=0;i<tempVector.size();i++)
								  {
									  bufferedWriter.write(tempVector.get(i).toString()+"\r\n");					  
								  }

								  bufferedWriter.flush();
								  bufferedWriter.close();
								  new ScheduleManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
								  
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
							
						}
					}
				});
				mainJPanel1.add(deleteButton);
				deleteButton.setBounds(new Rectangle(new Point(210, 25), addNewScheduleButton.getPreferredSize()));
				
				
				//---- addNewLessonsButton ----
				addNewLessonsButton.setText("\u65b0\u5efa");
				addNewLessonsButton.setCursor(hander);
				addNewLessonsButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						addNewLessonsButtonMouseClicked(e);
					}
				});
				mainJPanel1.add(addNewLessonsButton);
				addNewLessonsButton.setBounds(430, 25, 60, 23);

				//---- label1 ----
				scheduleLabel.setText("\u5f85\u529e\u4e8b\u9879");
				mainJPanel1.add(scheduleLabel);
				scheduleLabel.setBounds(new Rectangle(new Point(155, 30), scheduleLabel.getPreferredSize()));

				//---- label2 ----
				lessonLabel.setText("\u8bfe\u8868");
				mainJPanel1.add(lessonLabel);
				lessonLabel.setBounds(510, 30, 48, 15);

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
			mainFrame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
	}


	private JScrollPane scrollPane1;
	private JTable scheduleTable;
	private JScrollPane scrollPane2;
	private JTable lessonTable;
	private JButton addNewScheduleButton;
	private JButton addNewLessonsButton;
	private JButton deleteButton;
	private JLabel scheduleLabel;
	private JLabel lessonLabel;
	private JDialog scheduleDialog;
	private JDialog lessonDialog;
	private String usernameString;
	private JButton accountButton;
	private JButton scheduleButton;
	private JButton studyButton;
	private JButton moneyButton;
	private JLabel mainLabel;
	private JLabel copyRightLabel;
	private JButton adviceButton;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
	private int rowCount=0;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
