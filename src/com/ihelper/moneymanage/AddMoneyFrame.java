/*
 * Created by JFormDesigner on Sat Dec 15 21:20:30 CST 2012
 */

package com.ihelper.moneymanage;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.*;

import com.ihelper.tools.Coder;
import com.ihelper.tools.DateChooser;
import com.ihelper.tools.PBECoder;

/**
 * @author Han LaiMing
 */
public class AddMoneyFrame {


	public AddMoneyFrame(String username,String moneyName,String money,String time,String chooseType,String remark,Boolean isSelected,JFrame mainFrame,Container mainContainer,JPanel mainJPanel) {	
		usernameString=username;
		mainFrame1=mainFrame;
		mainContainer1=mainContainer;
		mainJPanel1=mainJPanel;
		moneyNameString=moneyName;
		moneyString=money;
		timeString=time;
		chooseTypeString=chooseType;
		remarkString=remark;
		isSelectedBoolean=isSelected;
		initComponents();
	}
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	private void okButtonActionPerformed(ActionEvent e) {
		// TODO add your code here
		try {
			File file=new File("./Save/"+usernameString+"/"+usernameString+"_money.ih");
			FileWriter outputFile = new FileWriter(file, true);
			byte[] salt = PBECoder.initSalt();
			outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(moneyNameTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
			outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(moneyTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
			outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(timeTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
			outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(chooseTypeComboBox.getSelectedItem().toString().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
			outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remarkTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
			outputFile.close();

			System.out.println("财务数据写入完毕");
			new MoneyManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
			addMoneyDialog.dispose();
			//添加的数据同步显示到account的主界面，表格里面..怎么实现呢
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		addMoneyDialog = new JDialog();
		moneyNameLabel = new JLabel();
		moneyNameTextField = new JTextField();
		timeLabel = new JLabel();
		moneyLabel = new JLabel();
		chooseTypeLabel = new JLabel();
		chooseTypeComboBox = new JComboBox();
		remarkLabel = new JLabel();
		moneyTextField = new JTextField();
		timeTextField = new JTextField();
		remarkTextField = new JTextField();
		cancelButton = new JButton();
		okButton = new JButton();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);
	
		DateChooser timeChooser = DateChooser.getInstance("yyyy-MM-dd");
		timeTextField = new JTextField();
		timeChooser.register(timeTextField);
		//======== addMoneyDialog ========
		{
			
			addMoneyDialog.setTitle("Add Money");
			addMoneyDialog.setVisible(true);
			Container addMoneyDialogContentPane = addMoneyDialog.getContentPane();
			addMoneyDialogContentPane.setLayout(null);
			
			//---- incomeRadioButton ----
			if(isSelectedBoolean){
				System.out.println("true");
				incomeRadioButton = new JRadioButton("收入",true);
				outRadioButton = new JRadioButton();
				moneyTypeButtonGroup = new ButtonGroup();//按钮分组
				if(chooseTypeString != null){
					if(chooseTypeString.equals("工资")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("工资");
						chooseTypeComboBox.addItem("奖金");
						chooseTypeComboBox.addItem("RP捡钱");
						chooseTypeComboBox.addItem("其他赚钱");
					}
					else if(chooseTypeString.equals("奖金")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("奖金");
						chooseTypeComboBox.addItem("工资");				
						chooseTypeComboBox.addItem("RP捡钱");
						chooseTypeComboBox.addItem("其他赚钱");
					}
					else if(chooseTypeString.equals("RP捡钱")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("RP捡钱");
						chooseTypeComboBox.addItem("奖金");
						chooseTypeComboBox.addItem("工资");									
						chooseTypeComboBox.addItem("其他赚钱");
					}
					else if(chooseTypeString.equals("其他赚钱")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("其他赚钱");
						chooseTypeComboBox.addItem("RP捡钱");
						chooseTypeComboBox.addItem("奖金");
						chooseTypeComboBox.addItem("工资");														
					}
				}
				else{
					chooseTypeComboBox.removeAllItems();
					chooseTypeComboBox.addItem("工资");
					chooseTypeComboBox.addItem("奖金");
					chooseTypeComboBox.addItem("RP捡钱");
					chooseTypeComboBox.addItem("其他赚钱");
				}
				
						
			}
			else{
				outRadioButton = new JRadioButton("支出",true);
				incomeRadioButton = new JRadioButton();
				moneyTypeButtonGroup = new ButtonGroup();    //按钮分组
				if(chooseTypeString !=null){
					System.out.println("here?");
					if(chooseTypeString.equals("工资")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("吃饭");
						chooseTypeComboBox.addItem("约会");
						chooseTypeComboBox.addItem("RP掉钱");
						chooseTypeComboBox.addItem("购物");
						chooseTypeComboBox.addItem("其他花钱");
					}
					else if(chooseTypeString.equals("约会")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("约会");
						chooseTypeComboBox.addItem("吃饭");
						chooseTypeComboBox.addItem("RP掉钱");
						chooseTypeComboBox.addItem("购物");
						chooseTypeComboBox.addItem("其他花钱");
					}
					else if(chooseTypeString.equals("RP掉钱")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("RP掉钱");
						chooseTypeComboBox.addItem("吃饭");
						chooseTypeComboBox.addItem("约会");
						chooseTypeComboBox.addItem("购物");
						chooseTypeComboBox.addItem("其他花钱");
					}
					else if(chooseTypeString.equals("购物")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("购物");
						chooseTypeComboBox.addItem("吃饭");
						chooseTypeComboBox.addItem("约会");
						chooseTypeComboBox.addItem("RP掉钱");
						chooseTypeComboBox.addItem("其他花钱");														
					}
					else if(chooseTypeString.equals("其他花钱")){
						chooseTypeComboBox.removeAllItems();
						chooseTypeComboBox.addItem("其他花钱");
						chooseTypeComboBox.addItem("吃饭");
						chooseTypeComboBox.addItem("约会");
						chooseTypeComboBox.addItem("RP掉钱");
						chooseTypeComboBox.addItem("购物");
																				
					}

				}
				else{
					chooseTypeComboBox.removeAllItems();
					chooseTypeComboBox.addItem("吃饭");
					chooseTypeComboBox.addItem("约会");
					chooseTypeComboBox.addItem("RP掉钱");
					chooseTypeComboBox.addItem("购物");
					chooseTypeComboBox.addItem("其他花钱");
				}
				
			}
			incomeRadioButton.setText("\u6536\u5165");
			addMoneyDialogContentPane.add(incomeRadioButton);

			incomeRadioButton.setBounds(new Rectangle(new Point(100, 5), incomeRadioButton.getPreferredSize()));
			incomeRadioButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					chooseTypeComboBox.removeAllItems();
					chooseTypeComboBox.addItem("工资");
					chooseTypeComboBox.addItem("奖金");
					chooseTypeComboBox.addItem("RP捡钱");
					chooseTypeComboBox.addItem("其他赚钱");
				}
			});
			//---- outRadioButton ----
			outRadioButton.setText("\u652f\u51fa");
			addMoneyDialogContentPane.add(outRadioButton);
			outRadioButton.setBounds(new Rectangle(new Point(220, 5), outRadioButton.getPreferredSize()));

			outRadioButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					chooseTypeComboBox.removeAllItems();					
					chooseTypeComboBox.addItem("吃饭");
					chooseTypeComboBox.addItem("约会");
					chooseTypeComboBox.addItem("RP掉钱");
					chooseTypeComboBox.addItem("购物");
					chooseTypeComboBox.addItem("其他花钱");
				}
			});
			moneyTypeButtonGroup.add(incomeRadioButton);    //将按钮加入分组中
			moneyTypeButtonGroup.add(outRadioButton);
			

			//---- moneyNameLabel ----
			moneyNameLabel.setText("\u8d39\u7528\u540d\uff1a");
			addMoneyDialogContentPane.add(moneyNameLabel);
			moneyNameLabel.setBounds(90, 50, 110, 20);
			addMoneyDialogContentPane.add(moneyNameTextField);
			moneyNameTextField.setText(moneyNameString);
			moneyNameTextField.setBounds(145, 50, 105, moneyNameTextField.getPreferredSize().height);

			//---- timeLabel ----
			timeLabel.setText("\u65f6\u95f4\uff1a");
			addMoneyDialogContentPane.add(timeLabel);
			timeLabel.setBounds(95, 130, 95, 20);

			//---- moneyLabel ----
			moneyLabel.setText("\u91d1\u989d");
			addMoneyDialogContentPane.add(moneyLabel);
			moneyLabel.setBounds(95, 90, 110, 20);

			//---- chooseTypeLabel ----
			chooseTypeLabel.setText("\u7c7b\u522b");
			addMoneyDialogContentPane.add(chooseTypeLabel);
			chooseTypeLabel.setBounds(95, 175, 110, 20);
			addMoneyDialogContentPane.add(chooseTypeComboBox);
			chooseTypeComboBox.setBounds(145, 175, 105, chooseTypeComboBox.getPreferredSize().height);
			
			//---- remarkLabel ----
			remarkLabel.setText("\u5907\u6ce8");
			addMoneyDialogContentPane.add(remarkLabel);
			remarkLabel.setBounds(95, 220, 110, 20);
			addMoneyDialogContentPane.add(moneyTextField);
			moneyTextField.setBounds(145, 90, 105, 21);
			moneyTextField.setText(moneyString);
			addMoneyDialogContentPane.add(timeTextField);
			timeTextField.setBounds(145, 130, 105, 21);
			timeTextField.setText(timeString);
			addMoneyDialogContentPane.add(remarkTextField);
			remarkTextField.setText(remarkString);
			remarkTextField.setBounds(145, 220, 105, 21);


			
			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			addMoneyDialogContentPane.add(cancelButton);
			cancelButton.setCursor(hander);
			cancelButton.setBounds(new Rectangle(new Point(260, 280), cancelButton.getPreferredSize()));
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addMoneyDialog.dispose();
				}
			});
			//---- okButton ----
			okButton.setText("\u786e\u5b9a");
			okButton.setCursor(hander);
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!isNum(moneyTextField.getText())){
						JOptionPane.showMessageDialog(null, "挣钱花钱都要输入数字...");
						moneyTextField.setText(null);
						return ;
					}
					if(moneyNameString!=null){
						addMoneyDialog.dispose();
					}
					else{
						okButtonActionPerformed(e);
					}
					
				}
			});
			addMoneyDialogContentPane.add(okButton);
			okButton.setBounds(170, 280, 57, 23);


			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < addMoneyDialogContentPane.getComponentCount(); i++) {
					Rectangle bounds = addMoneyDialogContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = addMoneyDialogContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				addMoneyDialogContentPane.setMinimumSize(preferredSize);
				addMoneyDialogContentPane.setPreferredSize(preferredSize);
			}
			addMoneyDialog.pack();
			addMoneyDialog.setLocationRelativeTo(addMoneyDialog.getOwner());
			addMoneyDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog addMoneyDialog;
	private JLabel moneyNameLabel;
	private JTextField moneyNameTextField;
	private JLabel timeLabel;
	private JLabel moneyLabel;
	private JLabel chooseTypeLabel;
	private JComboBox chooseTypeComboBox;
	private JLabel remarkLabel;
	private JTextField moneyTextField;
	private JTextField timeTextField;
	private JTextField remarkTextField;
	private JButton cancelButton;
	private JButton okButton;
	private JRadioButton incomeRadioButton;
	private JRadioButton outRadioButton;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
	private String usernameString;
	private ButtonGroup moneyTypeButtonGroup;
	private String moneyNameString;
	private String moneyString;
	private String timeString;
	private String chooseTypeString;
	private String remarkString;
	private Boolean isSelectedBoolean;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
