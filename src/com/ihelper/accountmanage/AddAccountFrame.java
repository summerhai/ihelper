/*
 * Created by JFormDesigner on Sat Dec 15 21:20:30 CST 2012
 */

package com.ihelper.accountmanage;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.*;

import com.hg.doc.ch;
import com.ihelper.tools.Coder;
import com.ihelper.tools.PBECoder;

/**
 * @author Han LaiMing
 */
public class AddAccountFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
	public AddAccountFrame(JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String username) {
		usernameString=username;
		mainFrame1=mainFrame;
		mainContainer1=mainContainer;
		mainJPanel1=mainJPanel;
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		addAccountDialog = new JDialog();
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

		//======== addAccountDialog ========
		{
			addAccountDialog.setTitle("Add Account");
			addAccountDialog.setVisible(true);
			Container addAccountDialogContentPane = addAccountDialog.getContentPane();
			addAccountDialogContentPane.setLayout(null);

			//---- networkNameLabel ----
			networkNameLabel.setText("\u8d26\u53f7\u6240\u5728\u540d\uff1a");
			addAccountDialogContentPane.add(networkNameLabel);
			networkNameLabel.setBounds(30, 25, 110, 20);
			addAccountDialogContentPane.add(networkNameTextField);
			networkNameTextField.setBounds(120, 25, 105, networkNameTextField.getPreferredSize().height);

			//---- passwordLabel ----
			passwordLabel.setText("\u5bc6\u7801\uff1a");
			addAccountDialogContentPane.add(passwordLabel);
			passwordLabel.setBounds(30, 125, 95, 20);

			//---- accountLabel ----
			accountLabel.setText("\u8d26\u53f7\uff1a");
			addAccountDialogContentPane.add(accountLabel);
			accountLabel.setBounds(30, 75, 110, 20);

			//---- chooseTypeLabel ----
			chooseTypeLabel.setText("\u9009\u62e9\u7c7b\u522b");
			addAccountDialogContentPane.add(chooseTypeLabel);
			chooseTypeLabel.setBounds(30, 175, 110, 20);
			addAccountDialogContentPane.add(chooseTypeComboBox);
			chooseTypeComboBox.setBounds(120, 175, 110, chooseTypeComboBox.getPreferredSize().height);
			
			chooseTypeComboBox.addItem("游戏");
			chooseTypeComboBox.addItem("论坛");
			chooseTypeComboBox.addItem("通讯");
			chooseTypeComboBox.addItem("其他");
			//---- remarkLabel ----
			remarkLabel.setText("\u5907\u6ce8");
			addAccountDialogContentPane.add(remarkLabel);
			remarkLabel.setBounds(30, 225, 110, 20);
			addAccountDialogContentPane.add(accountNameTextField);
			accountNameTextField.setBounds(120, 75, 105, 21);
			addAccountDialogContentPane.add(passwordTextField);
			passwordTextField.setBounds(120, 125, 105, 21);
			addAccountDialogContentPane.add(remarkTextField);
			remarkTextField.setBounds(120, 225, 105, 21);

			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			addAccountDialogContentPane.add(cancelButton);
			cancelButton.setBounds(new Rectangle(new Point(260, 285), cancelButton.getPreferredSize()));
			cancelButton.setCursor(hander);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addAccountDialog.dispose();
				}
			});
			//---- okButton ----
			okButton.setText("\u786e\u5b9a");
			okButton.setCursor(hander);
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						
						File file=new File("./Save/"+usernameString+"/"+usernameString+"_account.ih");
						FileWriter outputFile = new FileWriter(file, true);
						byte[] salt = PBECoder.initSalt();
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(networkNameTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(accountNameTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(passwordTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(chooseTypeComboBox.getSelectedItem().toString().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remarkTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.close();
						System.out.println("账号数据写入完毕");
						new AccountManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
						System.out.println("add ok之后执行showtable");
						addAccountDialog.dispose();
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
			addAccountDialogContentPane.add(okButton);
			okButton.setBounds(175, 285, 57, 23);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < addAccountDialogContentPane.getComponentCount(); i++) {
					Rectangle bounds = addAccountDialogContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = addAccountDialogContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				addAccountDialogContentPane.setMinimumSize(preferredSize);
				addAccountDialogContentPane.setPreferredSize(preferredSize);
			}
			addAccountDialog.pack();
			addAccountDialog.setLocationRelativeTo(addAccountDialog.getOwner());
			addAccountDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog addAccountDialog;
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
	private String usernameString;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
