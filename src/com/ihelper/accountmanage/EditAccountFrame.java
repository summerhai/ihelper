/*
 * Created by JFormDesigner on Mon Dec 24 18:06:03 CST 2012
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

import javax.swing.*;

import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;

/**
 * @author Han LaiMing
 */
public class EditAccountFrame  {
	public EditAccountFrame(String username,String netNameString,String accNameString,String pasNameString,String choNameString,String remNameString,JFrame mainFrame,Container mainContainer,JPanel mainJPanel){

		usernameString=username;
		netString=netNameString;
		accString=accNameString;
		pasString=pasNameString;
		choString=choNameString;
		remString=remNameString;
		mainContainer1=mainContainer;
		mainFrame1=mainFrame;
		mainJPanel1=mainJPanel;
		initComponents();
	}
	
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		editAccountDialog = new JDialog();
		networkNameLabel = new JLabel();
		networkNameTextField = new JTextField();
		passwordLabel = new JLabel();
		accountLabel = new JLabel();
		chooseTypeLabel = new JLabel();
		chooseTypeComboBox = new JComboBox();
		remarkLabel = new JLabel();
		accountNameTextField = new JTextField();
		passwordTextField = new JTextField();
		remarkTextField = new JTextField();
		cancelButton = new JButton();
		okButton = new JButton();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);

		//======== editAccountDialog ========
		{
			editAccountDialog.setTitle("Edit Account");
			editAccountDialog.setVisible(true);
			Container editAccountDialogContentPane = editAccountDialog.getContentPane();
			editAccountDialogContentPane.setLayout(null);

			//---- networkNameLabel ----
			networkNameLabel.setText("\u8d26\u53f7\u6240\u5728\u540d\uff1a");
			editAccountDialogContentPane.add(networkNameLabel);
			networkNameLabel.setBounds(30, 25, 110, 20);
			editAccountDialogContentPane.add(networkNameTextField);
			networkNameTextField.setText(netString);
			networkNameTextField.setBounds(120, 25, 105, networkNameTextField.getPreferredSize().height);

			//---- passwordLabel ----
			passwordLabel.setText("\u5bc6\u7801\uff1a");
			editAccountDialogContentPane.add(passwordLabel);
			passwordLabel.setBounds(30, 125, 95, 20);

			//---- accountLabel ----
			accountLabel.setText("\u8d26\u53f7\uff1a");
			editAccountDialogContentPane.add(accountLabel);
			accountLabel.setBounds(30, 75, 110, 20);

			//---- chooseTypeLabel ----
			chooseTypeLabel.setText("\u9009\u62e9\u7c7b\u522b");
			editAccountDialogContentPane.add(chooseTypeLabel);
			chooseTypeLabel.setBounds(30, 175, 110, 20);
			editAccountDialogContentPane.add(chooseTypeComboBox);
			if(choString.equals("游戏")){
				chooseTypeComboBox.addItem("游戏");
				chooseTypeComboBox.addItem("论坛");
				chooseTypeComboBox.addItem("通讯");
				chooseTypeComboBox.addItem("其他");
			}
			else if(choString.equals("论坛")){
				chooseTypeComboBox.addItem("论坛");
				chooseTypeComboBox.addItem("游戏");
				chooseTypeComboBox.addItem("通讯");
				chooseTypeComboBox.addItem("其他");
			}
			else if(choString.equals("通讯")){
				chooseTypeComboBox.addItem("通讯");
				chooseTypeComboBox.addItem("游戏");
				chooseTypeComboBox.addItem("论坛");
				chooseTypeComboBox.addItem("其他");
			}
			else if(choString.equals("其他")){
				chooseTypeComboBox.addItem("其他");
				chooseTypeComboBox.addItem("游戏");
				chooseTypeComboBox.addItem("论坛");
				chooseTypeComboBox.addItem("通讯");
			}
			chooseTypeComboBox.setBounds(120, 175, 110, chooseTypeComboBox.getPreferredSize().height);

			//---- remarkLabel ----
			remarkLabel.setText("\u5907\u6ce8");
			editAccountDialogContentPane.add(remarkLabel);
			remarkLabel.setBounds(30, 225, 110, 20);
			editAccountDialogContentPane.add(accountNameTextField);
			accountNameTextField.setBounds(120, 75, 105, 21);
			accountNameTextField.setText(accString);
			editAccountDialogContentPane.add(passwordTextField);
			passwordTextField.setText(pasString);
			passwordTextField.setBounds(120, 125, 105, 21);
			remarkTextField.setText(remString);
			editAccountDialogContentPane.add(remarkTextField);		
			remarkTextField.setBounds(120, 225, 105, 21);

			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			editAccountDialogContentPane.add(cancelButton);
			cancelButton.setBounds(new Rectangle(new Point(260, 285), cancelButton.getPreferredSize()));
			cancelButton.setCursor(hander);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					editAccountDialog.dispose();
				}
			});
			
			//---- okButton ----
			okButton.setText("\u4fdd\u5b58");
			editAccountDialogContentPane.add(okButton);
			okButton.setBounds(175, 285, 57, 23);
			okButton.setCursor(hander);
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//将修改后的数据存到原来的地方,格式不要弄乱，而且如果文件中有重名的怎么办，确保首先读取账户所在名，一般都不会相同，即使相同，还有下一参数判定
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
							  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(netString.getBytes()).getBytes(), "ihelperforyou", salt)))){
								  tempVector.add(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(networkNameTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt)));
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
								  if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(accString.getBytes()).getBytes(), "ihelperforyou", salt)))){
										tempVector.add(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(accountNameTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt)));
										count =2;
									  }
									  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(pasString.getBytes()).getBytes(), "ihelperforyou", salt)))){
										  tempVector.add(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(passwordTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt)));
										  count =3;
									  }
									  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(choString.getBytes()).getBytes(), "ihelperforyou", salt)))){
										  tempVector.add(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(chooseTypeComboBox.getSelectedItem().toString().getBytes()).getBytes(), "ihelperforyou", salt)));
										  count =4;
									  }
									  else if(buff.equals(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remString.getBytes()).getBytes(), "ihelperforyou", salt)))){
										  tempVector.add(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remarkTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt)));
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
					editAccountDialog.dispose();
					//如何做到同步显示数据更新
					
				}
			});

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < editAccountDialogContentPane.getComponentCount(); i++) {
					Rectangle bounds = editAccountDialogContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = editAccountDialogContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				editAccountDialogContentPane.setMinimumSize(preferredSize);
				editAccountDialogContentPane.setPreferredSize(preferredSize);
			}
			editAccountDialog.pack();
			editAccountDialog.setLocationRelativeTo(editAccountDialog.getOwner());
			editAccountDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog editAccountDialog;
	private JLabel networkNameLabel;
	private JTextField networkNameTextField;
	private JLabel passwordLabel;
	private JLabel accountLabel;
	private JLabel chooseTypeLabel;
	private JComboBox chooseTypeComboBox;
	private JLabel remarkLabel;
	private JTextField accountNameTextField;
	private JTextField passwordTextField;
	private JTextField remarkTextField;
	private JButton cancelButton;
	private JButton okButton;
	private String netString;
	private String accString;
	private String pasString;
	private String choString;
	private String remString;
	private String usernameString;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
