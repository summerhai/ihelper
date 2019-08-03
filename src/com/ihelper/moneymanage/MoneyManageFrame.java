/*
 * Created by JFormDesigner on Thu Dec 27 16:11:53 CST 2012
 */

package com.ihelper.moneymanage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.nio.channels.SelectableChannel;
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
import com.ihelper.tools.Skins;

/**
 * @author Han LaiMing
 */
public class MoneyManageFrame  {


	public MoneyManageFrame(JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String username){
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


		  
public void showTabel(){
	//从username_account.ih文件中读取账户数据，显示在JTable里
	
	String[] columnTitle = {"费用项" ,"金额","时间", "类别","备注"};

	try {			
		int i=0;//计算文件中的行数
		File file = new File("./Save/"+usernameString+"/"+usernameString+"_money.ih");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String   instring;   
		while((instring   =   br.readLine())   !=   null)   {   
             if(instring   !=  null )   
             {   
                   i++; 
             }
         }
		String[][] moneyTableData = new String[i/5][5];
		System.out.println("i="+i);
		int startLine = 1,endLine = i;
		List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_money.ih"), startLine, endLine);

		//将文件中1,7,13,19<i行等数据取出来，是费用项
		ArrayList<String> moneyNameList = new ArrayList<String>();
		//将文件中2,8,14,20<i行等数据取出来，是金额
		ArrayList<String> moneyList = new ArrayList<String>();
		//将文件中3,9,15,21<i行等数据取出来，是时间
		ArrayList<String> timeList = new ArrayList<String>();
		//将文件中4,10,16,22<i行等数据取出来，是类型
		ArrayList<String> chooseTypeList = new ArrayList<String>();
		//将文件中5,11,17,23<i行等数据取出来，是备注
		ArrayList<String> remarkList = new ArrayList<String>();
		byte[] salt = PBECoder.initSalt();
		for(int j=0;j<i;){
			moneyNameList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
			j=j+5;
		}
		for(int j=1;j<i;){
			moneyList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
			j=j+5;
		}
		for(int j=2;j<i;){
			timeList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
			j=j+5;
		}
		for(int j=3;j<i;){
			chooseTypeList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
			j=j+5;
		}
		for(int j=4;j<i;){
			remarkList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
			j=j+5;
		}
		//将List里的数据整理到一个String[][]二维数组里面
		
		ArrayList<String> lists[] = new ArrayList[5];
		lists[0] = moneyNameList;
		lists[1] = moneyList;
		lists[2] = timeList;
		lists[3] = chooseTypeList;
		lists[4] = remarkList;
		
		//行数为i/6,列数为5,设置表格数据不可编辑
		//因为没有给tableData设置大小，编译器在这里不给赋值，丫的,解决办法：在确定i之后，初始化tableData数组大小
		for(int k=0;k<i/5;k++){
			for(int j=0;j<5;j++){
				moneyTableData[k][j] = (String)lists[j].get(k);
			}
		}			
		TableModel model = new DefaultTableModel(moneyTableData, columnTitle);
		moneyManageTable = new JTable(model);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		moneyManageTable.setRowSorter(sorter);

	} catch (Exception e) {
		// TODO: handle exception
	}
}

public static boolean isNum(String str){
	return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
}
		
private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		moneyManageScrollPane = new JScrollPane(moneyManageTable);
		moneyManageTable = new JTable();
		addNewMoneyButton = new JButton();
		searchTextField = new JTextField();
		searchButton = new JButton();
		deleteMoneyButton = new JButton();
		computeMoneyButton = new JButton();
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
		showTabel();
		moneyManageTable.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked(MouseEvent e)
		{
		if(e.getClickCount()==1)
		{	 rowCount=moneyManageTable.getSelectedRow();
		}
		}
		});
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
		//======== mainFrame ========
		{
			mainContainer1.setLayout(null);

			//======== moneyManageJPanel ========
			{

				mainJPanel1.setLayout(null);

				//======== moneyManageScrollPane ========
				{
					moneyManageScrollPane.setViewportView(moneyManageTable);
				}
				mainJPanel1.add(moneyManageScrollPane);
				moneyManageScrollPane.setBounds(60, 70, 555, 270);

				//---- addNewNoteButton ----
				addNewMoneyButton.setText("\u65b0\u5efa");
				mainJPanel1.add(addNewMoneyButton);
				addNewMoneyButton.setCursor(hander);
				addNewMoneyButton.setBounds(125, 40, addNewMoneyButton.getPreferredSize().width, 23);
				addNewMoneyButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new AddMoneyFrame(usernameString,null,null,null,null,null,true,mainFrame1,mainContainer1,mainJPanel1);
					}
				});
				
				mainJPanel1.add(searchTextField);
				searchTextField.setBounds(415, 40, 105, searchTextField.getPreferredSize().height);

				//---- searchButton ----
				searchButton.setText("GO！");
				searchButton.setCursor(hander);
				mainJPanel1.add(searchButton);
				searchButton.setBounds(530, 40, 57, 23);
				searchButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						Boolean isFindBoolean = false;
						// TODO Auto-generated method stub
						String searchString = searchTextField.getText();
						//从当前数据中查找相关数据
						for(int i=0;i<moneyManageTable.getRowCount();i++){
							for(int j=0;j<moneyManageTable.getColumnCount();j++){
								//找出与字符串相等或相近的数据
								if(isContain((String)moneyManageTable.getValueAt(i, j), searchString)){
									isFindBoolean = true;
									
									String moneyNameString = (String)moneyManageTable.getValueAt(i, 0);
									String moneyString = (String)moneyManageTable.getValueAt(i, 1);
									String timeString = (String)moneyManageTable.getValueAt(i, 2);
									String chooseTypeString = (String)moneyManageTable.getValueAt(i, 3);
									String remarkString = (String)moneyManageTable.getValueAt(i, 4);
									if(chooseTypeString.equals("工资")||chooseTypeString.equals("奖金")||chooseTypeString.equals("RP捡钱")||chooseTypeString.equals("其他赚钱")){
										isSelectBoolean = true;
									}
									else isSelectBoolean = false;
									new AddMoneyFrame(usernameString,moneyNameString,moneyString,timeString,chooseTypeString,remarkString,isSelectBoolean,mainFrame1,mainContainer1,mainJPanel1);		
									
								}
							}
						}
						if(!isFindBoolean){
							searchTextField.setText("");
							JOptionPane.showMessageDialog(null, "没有找到相关数据");
						}
						
					}
						

					});


				//---- deleteNoteButton ----
				deleteMoneyButton.setText("\u5220\u9664");
				mainJPanel1.add(deleteMoneyButton);
				deleteMoneyButton.setCursor(hander);
				deleteMoneyButton.setBounds(230, 40, 57, 23);
				deleteMoneyButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(rowCount == -1){
							JOptionPane.showMessageDialog(null, "你没有选择要删除的数据哦");
						}
						String moneyNameString = (String)moneyManageTable.getValueAt(rowCount, 0);
						String moneyString = (String)moneyManageTable.getValueAt(rowCount, 1);
						String timeString = (String)moneyManageTable.getValueAt(rowCount, 2);
						String moneyTypeString = (String)moneyManageTable.getValueAt(rowCount, 3);
						String remarkString = (String)moneyManageTable.getValueAt(rowCount, 4);
						String confirmString = "您确定要删除这条财务数据吗";
						int click = JOptionPane.showConfirmDialog(null, confirmString,"删除",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
						if(click == JOptionPane.YES_NO_OPTION){
							//删除就是将源文件读出，然后识别出要删除的数据，尤其是首位，不写入文件就行了
							try {
								int count = 0;
								Boolean isFindMoneyName = false;
								File outputFile=new File("./Save/"+usernameString+"/"+usernameString+"_money.ih");
								  BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile));
								  String buff = bufferedReader.readLine();
								  Vector<String> tempVector = new Vector<String>();
								  byte[] salt = PBECoder.initSalt();
								  while(buff!=null&&!isFindMoneyName){							  
									  //优先判定财务所在名是否匹配
									  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(moneyNameString.getBytes()).getBytes(), "ihelperforyou", salt)))){
										  isFindMoneyName = true;
										  count =1;
									  }
									  else {
											tempVector.add(buff);
										}
									  buff=bufferedReader.readLine();	  	  
								  }
								  while(buff!=null&&isFindMoneyName){
									  if(count != 5){
										  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(moneyString.getBytes()).getBytes(), "ihelperforyou", salt)))){
												count =2;
											  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(timeString.getBytes()).getBytes(), "ihelperforyou", salt)))){
												  count =3;
											  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(moneyTypeString.getBytes()).getBytes(), "ihelperforyou", salt)))){
												  count =4;
											  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remarkString.getBytes()).getBytes(), "ihelperforyou", salt)))){
												  count =5;
											  }
											  else {
													tempVector.add(buff);
												}
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
								  new MoneyManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
								  
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

				//---- computeMoneyButton ----
				computeMoneyButton.setText("\u5408\u8ba1");
				mainJPanel1.add(computeMoneyButton);
				computeMoneyButton.setCursor(hander);
				computeMoneyButton.setBounds(320, 40, 75, 23);
				computeMoneyButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						//将收入总和减去支出总和,并分类说明消费支出项，给出建议
						String monthString = JOptionPane.showInputDialog(null, "请输入统计的月份，统计所有请输0", "统计", JOptionPane.QUESTION_MESSAGE);
						if(isNum(monthString)){
							int month = Integer.parseInt(monthString);
							computeMoney(month);
							
						}else {
							JOptionPane.showMessageDialog(null, "请输入数字");
							actionPerformed(arg0);
						}
						
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

}

private int changeFormat(String mString) {
	// TODO Auto-generated method stub
	String[] tempdata=mString.split("-");
	return Integer.parseInt(tempdata[1]);
}

/**
 * 
 */
private void computeMoney(int month) {
	// TODO Auto-generated method stub
	try {			
		int line=0;//计算文件中的行数
		File file = new File("./Save/"+usernameString+"/"+usernameString+"_money.ih");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String   instring;   
		while((instring   =   br.readLine())   !=   null)   {   
             if(instring   !=  null )   
             {   
                   line++; 
             }
         }
		int startLine = 1,endLine = line;
		List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_money.ih"), startLine, endLine);
		//判断是求得每月的消费记录还是全体的
		if(month!=0){
			//计算month月份的消费情况
			int getMoney=0,getMoneyG=0,getMoneyJ=0,getMoneyR=0,getMoneyQ=0;
			int outMoney=0,outMoneyC=0,outMoneyY=0,outMoneyR=0,outMoneyQ=0,outMoneyG=0;
			ArrayList<String> dateList = new ArrayList<String>();
			byte[] salt = PBECoder.initSalt();
			for(int i=2;i<line;){
				dateList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))));
				i=i+5;
			}
				//取出yymmdd格式中的mm，并转成int与month求证是
				for(int j=0;j<dateList.size();j++){
					if(changeFormat(dateList.get(j))==month){
						//确定月份是对的
						if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("工资")){
							getMoneyG +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("奖金")){
							getMoneyJ +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("RP捡钱")){
							getMoneyR +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("其他赚钱")){
							getMoneyQ +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("吃饭")){
							outMoneyC +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("约会")){
							outMoneyY +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("RP掉钱")){
							outMoneyR +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("购物")){
							outMoneyG +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}
						else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+3)), "ihelperforyou", salt)))).equals("其他花钱")){
							outMoneyQ +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(5*j+1)), "ihelperforyou", salt)))));
						}			
					}
				}							
			getMoney = getMoneyG+getMoneyJ+getMoneyQ+getMoneyR;
			outMoney = outMoneyC+outMoneyG+outMoneyQ+outMoneyR+outMoneyY;
			String advice= null;
			if(getMoney-outMoney>0&&getMoney-outMoney<1000){
				advice = "本月有零钱哟，继续保持~~";
			}
			else if(getMoney-outMoney<=0){
				advice = "钱不够花啊，节俭点吧~~";
			}
			else{
				advice = "运势大吉，吼吼！";
			}
			String consumerString = "<html>您"+month+"月消费情况是：<br>"+"总收入是:"+getMoney+
					",其中工资有"+getMoneyG+"，奖金有"+getMoneyJ+"，RP捡钱有"+getMoneyR+
					",其他赚钱有"+getMoneyQ+"<br>"+"总支出是:"+outMoney+
					",其中吃饭有"+outMoneyC+"，约会有"+outMoneyY+"，RP掉钱有"+outMoneyR+
					",购物有"+outMoneyG+",其他花钱有"+outMoneyQ+"<br>您的盈余为："+(getMoney-outMoney)+"<br>温馨提示："+advice;
			
			JOptionPane.showConfirmDialog(null, consumerString, "您的财务情况", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
		}else {
			int getMoney=0,getMoneyG=0,getMoneyJ=0,getMoneyR=0,getMoneyQ=0;
			int outMoney=0,outMoneyC=0,outMoneyY=0,outMoneyR=0,outMoneyQ=0,outMoneyG=0;
			byte[] salt = PBECoder.initSalt();
			for(int i=0;i<regionList.size();i++){
				if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("工资")){
					getMoneyG +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("奖金")){
					getMoneyJ +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("RP捡钱")){
					getMoneyR +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("其他赚钱")){
					getMoneyQ +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("吃饭")){
					outMoneyC +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("约会")){
					outMoneyY +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("RP掉钱")){
					outMoneyR +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("购物")){
					outMoneyG +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}
				else if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i)), "ihelperforyou", salt)))).equals("其他花钱")){
					outMoneyQ +=Integer.parseInt(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(i-2)), "ihelperforyou", salt)))));
				}							
				}
			getMoney = getMoneyG+getMoneyJ+getMoneyQ+getMoneyR;
			outMoney = outMoneyC+outMoneyG+outMoneyQ+outMoneyR+outMoneyY;
			String advice= null;
			if(getMoney-outMoney>0&&getMoney-outMoney<1000){
				advice = "本月有零钱哟，继续保持~~";
			}
			else if(getMoney-outMoney<=0){
				advice = "钱不够花啊，节俭点吧~~";
			}
			else{
				advice = "运势大吉，吼吼！";
			}
			String consumerString = "<html>您近期消费情况是：<br>"+"总收入是:"+getMoney+
					",其中工资有"+getMoneyG+"，奖金有"+getMoneyJ+"，RP捡钱有"+getMoneyR+
					",其他赚钱有"+getMoneyQ+"<br>"+"总支出是:"+outMoney+
					",其中吃饭有"+outMoneyC+"，约会有"+outMoneyY+"，RP掉钱有"+outMoneyR+
					",购物有"+outMoneyG+",其他花钱有"+outMoneyQ+"<br>您的盈余为："+(getMoney-outMoney)+"<br>温馨提示："+advice;
			
			JOptionPane.showConfirmDialog(null, consumerString, "您的财务情况", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
		
	}catch (Exception e) {
		// TODO: handle exception
	}
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

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JScrollPane moneyManageScrollPane;
	private JTable moneyManageTable;
	private JButton addNewMoneyButton;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton deleteMoneyButton;
	private JButton computeMoneyButton;
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
	private int rowCount=0;
	private Boolean isSelectBoolean;
	private JComboBox themeBox;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
