/*
 * Created by JFormDesigner on Sat Dec 15 20:10:32 CST 2012
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.crypto.SealedObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;

/**
 * @author Han LaiMing
 */
public class AccountManageFrame extends DefaultTableModel{
	private int rowCount;
	private JPanel mainJPanel1;
	private Container mainContainer1;
	private JFrame mainFrame1;
	private JLabel copyRightLabel;
	public AccountManageFrame(JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String username){
		mainJPanel.removeAll();
		mainFrame1=mainFrame;
		mainContainer1=mainContainer;
		mainJPanel1=mainJPanel;
		usernameString=username;	
		initComponents();
	}

	public boolean isCellEditable(int row,int colum) {

		return false;
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
		
		String[] columnTitle = {"账户所在名" ,"账户名","密码", "类别","备注"};

		try {			
			int i=0;//计算文件中的行数
			File file = new File("./Save/"+usernameString+"/"+usernameString+"_account.ih");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String   instring;   
			while((instring   =   br.readLine())   !=   null)   {   
	              if(instring   !=  null )   
	              {   
	                    i++; 
	              }
	          }
			String[][] tableData = new String[i/5][5];
			System.out.println("i="+i);
			int startLine = 1,endLine = i;
			List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_account.ih"), startLine, endLine);
			//将文件中0,5,10,15<i行等数据取出来，是账户所在名
			ArrayList<String> networkNameList = new ArrayList<String>();
			//将文件中1,6,11,16<i行等数据取出来，是账户名
			ArrayList<String> accountNameList = new ArrayList<String>();
			//将文件中2,7,12,17<i行等数据取出来，是密码
			ArrayList<String> passwordList = new ArrayList<String>();
			//将文件中3,8,13,18<i行等数据取出来，是类型
			ArrayList<String> chooseTypeList = new ArrayList<String>();
			//将文件中4,9,14,19<i行等数据取出来，是备注
			ArrayList<String> remarkList = new ArrayList<String>();
			byte[] salt = PBECoder.initSalt();
			for(int j=0;j<i;){
				networkNameList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
				j=j+5;
			}
			for(int j=1;j<i;){
				accountNameList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
				j=j+5;
			}
			for(int j=2;j<i;){
				passwordList.add(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(j)), "ihelperforyou", salt)))));
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
			
			ArrayList lists[] = new ArrayList[5];
			lists[0] = networkNameList;
			lists[1] = accountNameList;
			lists[2] = passwordList;
			lists[3] = chooseTypeList;
			lists[4] = remarkList;
			
			//JTable table = new JTable(new MyTableModel()); //OLD
			 //ADDED THIS
			//行数为i/6,列数为5,设置表格数据不可编辑
			//因为没有给tableData设置大小，编译器在这里不给赋值，丫的,解决办法：在确定i之后，初始化tableData数组大小
			for(int k=0;k<i/5;k++){
				for(int j=0;j<5;j++){
					tableData[k][j] = (String)lists[j].get(k);
					
				}
			}
			
			TableModel model = new DefaultTableModel(tableData, columnTitle);
			accountTable = new JTable(model);
			
			RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			accountTable.setRowSorter(sorter);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
			  
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing

		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		accountTable = new JTable();
		accountScrollPane = new JScrollPane();
		addNewAccountButton = new JButton();
		searchButton = new JButton();
		editButton = new JButton();
		deleteButton = new JButton();
		inputTextField = new JTextField();
		copyRightLabel = new JLabel();
		owner=new JDialog(mainFrame1);
		
		showTabel();
		
		accountTable.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked(MouseEvent e)
		{
		if(e.getClickCount()==1)
		{	 rowCount=accountTable.getSelectedRow();
		}
		}
		});
		//======== mainFrame ========
		{

			mainContainer1.setLayout(null);

			//======== mainJPanel ========
			{
				
				mainJPanel1.setLayout(null);

				//======== accountScrollPane ========
				{
					accountScrollPane.setViewportView(accountTable);
				}
				mainJPanel1.add(accountScrollPane);
				accountScrollPane.setBounds(20, 30, 610, 360);

				//---- addNewAccountButton ----
				addNewAccountButton.setText("\u65b0\u589e");
				addNewAccountButton.setCursor(hander);
				addNewAccountButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new AddAccountFrame(mainFrame1,mainContainer1,mainJPanel1,usernameString);					
					}
				});
				mainJPanel1.add(addNewAccountButton);
				addNewAccountButton.setBounds(new Rectangle(new Point(50, 0), addNewAccountButton.getPreferredSize()));
				
				editButton.setText("修改");
				mainJPanel1.add(editButton);
				editButton.setCursor(hander);
				editButton.setBounds(new Rectangle(new Point(180, 0), addNewAccountButton.getPreferredSize()));
				editButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						//跳转到修改窗口,鼠标点击哪一行的数据
						
//						int i = accountTable.getSelectedRow();
//						System.out.println(accountTable.getSelectedRows().length);
						if(rowCount == -1){
							JOptionPane.showMessageDialog(null, "你没有选择要修改的数据哦");
						}
						System.out.println(rowCount+"i=");
						
						String netNameString = (String)accountTable.getValueAt(rowCount, 0);
						String accNameString = (String)accountTable.getValueAt(rowCount, 1);
						String pasNameString = (String)accountTable.getValueAt(rowCount, 2);
						String choNameString = (String)accountTable.getValueAt(rowCount, 3);
						String remNameString = (String)accountTable.getValueAt(rowCount, 4);
	
						new EditAccountFrame(usernameString,netNameString,accNameString,pasNameString,choNameString,remNameString,mainFrame1,mainContainer1,mainJPanel1);					
						System.out.println("edit之后执行");

					}
				});
							
				deleteButton.setText("删除");
				mainJPanel1.add(deleteButton);
				deleteButton.setCursor(hander);
				deleteButton.setBounds(new Rectangle(new Point(310, 0), addNewAccountButton.getPreferredSize()));
				deleteButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(rowCount == -1){
							JOptionPane.showMessageDialog(null, "你没有选择要删除的数据哦");
						}
						String netNameString = (String)accountTable.getValueAt(rowCount, 0);
						String accNameString = (String)accountTable.getValueAt(rowCount, 1);
						String pasNameString = (String)accountTable.getValueAt(rowCount, 2);
						String choNameString = (String)accountTable.getValueAt(rowCount, 3);
						String remNameString = (String)accountTable.getValueAt(rowCount, 4);
						String confirmString = "您确定要删除这条账户数据吗";
						int click = JOptionPane.showConfirmDialog(null, confirmString,"删除",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
						if(click == JOptionPane.YES_NO_OPTION){
							//删除就是将源文件读出，然后识别出要删除的数据，尤其是首位，不写入文件就行了
							try {
								int count = 0;
								Boolean isFindNetName = false;
								File outputFile=new File("./Save/"+usernameString+"/"+usernameString+"_account.ih");
								  BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile));
								  String buff = bufferedReader.readLine();
								  Vector<String> tempVector = new Vector<String>();
								  byte[] salt = PBECoder.initSalt();
								  while(buff!=null&&!isFindNetName){							  
									  //优先判定账户所在名是否匹配
									  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(netNameString.getBytes()).getBytes(), "ihelperforyou", salt)))){
										  isFindNetName = true;
										  count =1;
									  }
									  else {
											tempVector.add(buff);
										}
									  buff=bufferedReader.readLine();	  	  
								  }
								  while(buff!=null&&isFindNetName){
									  if(count != 5){
										  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(accNameString.getBytes()).getBytes(), "ihelperforyou", salt)))){
												count =2;
											  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(pasNameString.getBytes()).getBytes(), "ihelperforyou", salt)))){
												  count =3;
											  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(choNameString.getBytes()).getBytes(), "ihelperforyou", salt)))){
												  count =4;
											  }
											  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remNameString.getBytes()).getBytes(), "ihelperforyou", salt)))){
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
								  new AccountManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
								  
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
				
				//---- serachButton ----
				searchButton.setText("GO！");
				mainJPanel1.add(searchButton);
				searchButton.setCursor(hander);
				searchButton.setBounds(new Rectangle(new Point(535, 0), addNewAccountButton.getPreferredSize()));
				mainJPanel1.add(inputTextField);
				inputTextField.setBounds(400, 0, 115, inputTextField.getPreferredSize().height);

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
			searchButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String searchString = inputTextField.getText();
					boolean isFind = false;
					//从当前数据中查找相关数据
					for(int i=0;i<accountTable.getRowCount();i++){
						for(int j=0;j<accountTable.getColumnCount();j++){
							//找出与字符串相等或相近的数据
							if(isContain((String)accountTable.getValueAt(i, j), searchString)){
								isFind = true;
								String netNameString = (String)accountTable.getValueAt(i, 0);
								String accNameString = (String)accountTable.getValueAt(i, 1);
								String pasNameString = (String)accountTable.getValueAt(i, 2);
								String choNameString = (String)accountTable.getValueAt(i, 3);
								String remNameString = (String)accountTable.getValueAt(i, 4);		
								new EditAccountFrame(usernameString, netNameString, accNameString, pasNameString, choNameString, remNameString, mainFrame1, mainContainer1, mainJPanel1);
								
							}
							
						}
					}
					if(!isFind){
						inputTextField.setText("");
						JOptionPane.showMessageDialog(null, "没有找到相关数据");
					}
					
				}
			});
			copyRightLabel.setText("Copyright © 2013 Han Laiming");
			copyRightLabel.setForeground(Color.blue);
			mainContainer1.add(copyRightLabel);
			copyRightLabel.setBounds(360, 450, 190, 50);
			mainJPanel1.setBounds(125, 70, 665, 390);
//			mainJPanel1.removeAll();
			mainFrame1.pack();
			mainFrame1.setLocationRelativeTo(mainFrame1.getOwner());
			mainFrame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	

	private JScrollPane accountScrollPane;
	private JTable accountTable;
	private JButton addNewAccountButton;
	private JButton searchButton;
	private JButton editButton;
	private JButton deleteButton;
	private JTextField inputTextField;
	private JDialog owner;
	private String usernameString;

	// JFormDesigner - End of variables declaration  //GEN-END:variables

}
