/*
 * Created by JFormDesigner on Sun Dec 16 12:44:59 CST 2012
 */

package com.ihelper.schedulemanage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;

/**
 * @author Han LaiMing
 */
public class AddLessonsFrame extends AbstractTableModel{
	private int lineNumber;
	private String usernameString;
	private Container mainContainer1;
	private JFrame mainFrame1;
	private JPanel mainJPanel1;
	public AddLessonsFrame(String username,int lines,JFrame mainFrame,Container mainContainer,JPanel mainJPanel) {

		lineNumber=lines;
		usernameString=username;
		mainContainer1=mainContainer;
		mainFrame1=mainFrame;
		mainJPanel1=mainJPanel;
		initComponents();
	}

	private void createLessonTable(int lineNum) {
		System.out.println("开始执行createLessonTable");
		// TODO Auto-generated method stub
		//创建一个空表,利用JTable的方法实现添加课表，双击出现提示输入框，获取数据，保存后
		String[] titleStrings = {"节数\\日期","星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
		try {			
			
			String[][] lessonTableData = new String[lineNum][8];

			ArrayList<String> lessonNumberList = new ArrayList();
			ArrayList<String> mondayLessonsList = new ArrayList();
			ArrayList<String> tuesdayLessonsList = new ArrayList();
			ArrayList<String> wednesdayLessonList = new ArrayList();
			ArrayList<String> thursdayLessonsList = new ArrayList();
			ArrayList<String> fridaydayLessonsList = new ArrayList();
			ArrayList<String> saturdayLessonsList = new ArrayList();
			ArrayList<String> sundayLessonsList = new ArrayList();
			
			for(int i=0;i<lineNum;i++){
				lessonNumberList.add("第"+(i+1)+"节");
				mondayLessonsList.add("");
				tuesdayLessonsList.add("");
				wednesdayLessonList.add("");
				thursdayLessonsList.add("");
				fridaydayLessonsList.add("");
				saturdayLessonsList.add("");
				sundayLessonsList.add("");	
			}
			
			//将List里的数据整理到一个String[][]二维数组里面
			
			ArrayList lists[] = new ArrayList[8];
			lists[0] = lessonNumberList;
			lists[1] = mondayLessonsList;
			lists[2] = tuesdayLessonsList;
			lists[3] = wednesdayLessonList;
			lists[4] = thursdayLessonsList;
			lists[5] = fridaydayLessonsList;
			lists[6] = saturdayLessonsList;
			lists[7] = sundayLessonsList;
			
			//行数为i/6,列数为5,设置表格数据不可编辑
			//因为没有给tableData设置大小，编译器在这里不给赋值，丫的,解决办法：在确定i之后，初始化tableData数组大小
			for(int k=0;k<lineNum;k++){
				for(int j=0;j<8;j++){
					lessonTableData[k][j] = (String)lists[j].get(k);
				}
			}
			lessonTable = new JTable(lessonTableData,titleStrings);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex){
		if(columnIndex == 0){
		return false;
		}
		return true;
		}
	
	private void initComponents() {
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		addLessonDialog = new JDialog();
		okButton = new JButton();
		cancelButton = new JButton();
		lessonScrollPane1 = new JScrollPane(lessonTable);
		lessonTable = new JTable();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		lessonTable.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked(MouseEvent e)
		{
		if(e.getClickCount()==1){	 
			rowCount=lessonTable.getSelectedRow();
			}
		else if(e.getClickCount() == 2){
			rowNumber = lessonTable.getSelectedRow();
			columNumber = lessonTable.getSelectedColumn();
		}
		}
		});
		createLessonTable(lineNumber);

		//======== addLessonDialog ========
		{
			addLessonDialog.setTitle("Add Lesson");
			addLessonDialog.setVisible(true);
			addLessonDialog.setPreferredSize(new Dimension(560, 360));
			Container addLessonDialogContentPane = addLessonDialog.getContentPane();
			addLessonDialogContentPane.setLayout(null);

			//---- okButton ----
			okButton.setText("\u786e\u5b9a");
			addLessonDialogContentPane.add(okButton);
			okButton.setCursor(hander);
			okButton.setBounds(new Rectangle(new Point(205, 285), okButton.getPreferredSize()));
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//将表中编辑的数据保存到username._lesson.ih
					try {
						File file1=new File("./Save/"+usernameString+"/"+usernameString+"_lesson.ih");
						FileOutputStream temp = new FileOutputStream(file1);
						temp.close();
						File file=new File("./Save/"+usernameString+"/"+usernameString+"_lesson.ih");
						FileWriter outputFile = new FileWriter(file, true);
						byte[] salt =PBECoder.initSalt();
						for(int i=0;i<lineNumber;i++){
							for(int j=0;j<8;j++){
								outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(lessonTable.getValueAt(i, j).toString().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n"); 
							}
						}
						outputFile.close();
						System.out.println("课表数据写入完毕");
						new ScheduleManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
						addLessonDialog.dispose();
						//添加的数据同步显示到account的主界面，表格里面..怎么实现呢
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			});
			
			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			addLessonDialogContentPane.add(cancelButton);
			cancelButton.setCursor(hander);
			cancelButton.setBounds(new Rectangle(new Point(290, 285), okButton.getPreferredSize()));

			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addLessonDialog.dispose();
				}
			});
			//======== lessonScrollPane1 ========
			{
				lessonScrollPane1.setViewportView(lessonTable);
			}
			addLessonDialogContentPane.add(lessonScrollPane1);
			lessonScrollPane1.setBounds(15, 10, 515, 260);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < addLessonDialogContentPane.getComponentCount(); i++) {
					Rectangle bounds = addLessonDialogContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = addLessonDialogContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				addLessonDialogContentPane.setMinimumSize(preferredSize);
				addLessonDialogContentPane.setPreferredSize(preferredSize);
			}
			addLessonDialog.pack();
			addLessonDialog.setLocationRelativeTo(addLessonDialog.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
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



	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog addLessonDialog;
	private JButton okButton;
	private JButton cancelButton;
	private JScrollPane lessonScrollPane1;
	private JTable lessonTable;
	private int rowCount;
	private int rowNumber;
	private int columNumber;
	private Vector<String> saveLesson;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return lessonTable.getColumnCount();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lessonTable.getRowCount();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		System.out.println("getValueAt执行");
		return lessonTable.getValueAt(row, column);
	}
}
