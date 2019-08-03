/*
 * Created by JFormDesigner on Mon Dec 17 15:54:03 CST 2012
 */

package com.ihelper.mainframe;

import java.awt.*;
import java.awt.event.*;
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

import javax.swing.*;

import com.ihelper.login.RegisterFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;

/**
 * @author Han LaiMing
 */
public class SparePasswordFrame {

	public SparePasswordFrame(Dialog owner,String username,String isFirst) {
		super();
		usernameString=username;
		isString=isFirst;
		System.out.println(usernameString+isFirst);
		initComponents();

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
		sparePassword = new JDialog();
		networkNameLabel = new JLabel();
		sparePasswordJLabel = new JLabel();
		sparePasswordTextField = new JTextField();
		cancelButton = new JButton();
		okButton = new JButton();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
		
		//======== sparePassword ========
		{
			sparePassword.setTitle("SparePassword");
			sparePassword.setVisible(true);
			Container sparePasswordContentPane = sparePassword.getContentPane();
			sparePasswordContentPane.setLayout(null);
			sparePasswordContentPane.setBackground(Color.GRAY);

			//---- networkNameLabel ----
			networkNameLabel.setText("\u4e3a\u4e86\u786e\u4fdd\u60a8\u7684\u8d26\u53f7\u5b89\u5168\uff0cihelper\u91c7\u53d6\u53cc\u91cd\u5bc6\u7801\u65b9\u5f0f\uff0c");
			networkNameLabel.setFocusTraversalPolicyProvider(true);
			sparePasswordContentPane.add(networkNameLabel);
			networkNameLabel.setBounds(55, 35, 325, 35);

			//---- sparePasswordJLabel ----
			sparePasswordJLabel.setText("\u5907\u7528\u5bc6\u7801");
			sparePasswordContentPane.add(sparePasswordJLabel);
			sparePasswordJLabel.setBounds(30, 165, 110, 20);
			sparePasswordContentPane.add(sparePasswordTextField);
			sparePasswordTextField.setBounds(135, 165, 105, 21);

			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			sparePasswordContentPane.add(cancelButton);
			cancelButton.setCursor(hander);
			cancelButton.setBounds(new Rectangle(new Point(260, 285), cancelButton.getPreferredSize()));

			//---- okButton ----
			okButton.setText("\u786e\u5b9a");
			okButton.setBounds(175, 285, 57, 23);
			okButton.setCursor(hander);
			sparePasswordContentPane.add(okButton);
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						if(isString.equals("l")){
							try {
								byte[] salt = PBECoder.initSalt();
								File file=new File("./Save/"+usernameString+"/"+usernameString+"_data.ih");
								FileWriter outputFile = new FileWriter(file, true);
								//加密
								outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(sparePasswordTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");  
								outputFile.close();
								System.out.println("备用密码写入完毕");
								sparePassword.dispose();
								new MainFrame(usernameString);
								
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
							
							if(isString=="s"){
								System.out.println("sssss");
								int startLine = 1,endLine = 9;
								List<String> regionList = listFileByRegionRow(("./Save/"+usernameString+"/"+usernameString+"_data.ih"), startLine, endLine);
//								System.out.println(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), usernameString, salt)));
								//								new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(startLine-1)), username, salt));
								try {
									byte[] salt = PBECoder.initSalt();
									System.out.println(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(6)), "ihelperforyou", salt)))));
									if(new String(Coder.decryptBASE64(new String(PBECoder.decrypt(Coder.parseHexStr2Byte(regionList.get(6)), "ihelperforyou", salt)))).equals(sparePasswordTextField.getText())){
										System.out.println("in");
										
										sparePassword.dispose();
										new MainFrame(usernameString);
									}
									else 
									{
										sparePasswordTextField.setText(null);
										JOptionPane.showConfirmDialog(null, "备用密码输入错误，再想想？", "备用密码提示", JOptionPane.WARNING_MESSAGE);
										alert.setText("备用密码输入错误，再想想？");
									}
								} catch (HeadlessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}				
			});

			//---- label1 ----
			label1.setText("\u6e29\u99a8\u63d0\u793a\uff1a");
			sparePasswordContentPane.add(label1);
			label1.setBounds(35, 10, 125, 25);

			//---- label2 ----
			label2.setText("\u60a8\u9700\u8981\u5728\u4e0b\u8fb9\u8f93\u5165\u5907\u7528\u5bc6\u7801\uff0c\u5f53\u60a8\u5728\u5176\u4ed6\u673a\u5668\u4e0a\u767b\u5f55\u65f6\uff0c");
			sparePasswordContentPane.add(label2);
			label2.setBounds(55, 75, 315, 20);

			//---- label3 ----
			label3.setText("\u4f1a\u63d0\u793a\u60a8\u8f93\u5165\u5907\u7528\u5bc6\u7801\uff0c\u8bf7\u8c28\u614e\u5904\u7406");
			sparePasswordContentPane.add(label3);
			label3.setBounds(55, 110, 220, label3.getPreferredSize().height);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < sparePasswordContentPane.getComponentCount(); i++) {
					Rectangle bounds = sparePasswordContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = sparePasswordContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				sparePasswordContentPane.setMinimumSize(preferredSize);
				sparePasswordContentPane.setPreferredSize(preferredSize);
			}
			sparePassword.pack();
			sparePassword.setLocationRelativeTo(sparePassword.getOwner());
			sparePassword.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog sparePassword;
	private JLabel networkNameLabel;
	private JLabel sparePasswordJLabel;
	private JTextField sparePasswordTextField;
	private JButton cancelButton;
	private JButton okButton;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel alert;
	private String usernameString;
	private String isString;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
