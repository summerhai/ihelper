/*
 * Created by JFormDesigner on Sun Dec 16 12:43:40 CST 2012
 */

package com.ihelper.schedulemanage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import com.ihelper.tools.Coder;
import com.ihelper.tools.DateChooser;
import com.ihelper.tools.MyAlarm;
import com.ihelper.tools.PBECoder;

/**
 * @author Han LaiMing
 */
public class AddScheduleFrame {
	
	public AddScheduleFrame(JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String username) {
		usernameString=username;
		mainContainer1=mainContainer;
		mainFrame1=mainFrame;
		mainJPanel1=mainJPanel;
		initComponents();
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		addScheduleDialog = new JDialog();
		undoLabel = new JLabel();
		undoTextField = new JTextField();
		remarkLabel = new JLabel();
		timeLabel = new JLabel();
		chooseTypeLabel = new JLabel();
		comboBox = new JComboBox();
		timeTextField = new JTextField();
		remarkTextField = new JTextField();
		okButton = new JButton();
		cancelButton = new JButton();
		remindLabel = new JLabel();
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);

		DateChooser timeChooser = DateChooser.getInstance("yyyy-MM-dd");
		timeTextField = new JTextField();
		timeChooser.register(timeTextField);
		//======== addScheduleDialog ========
		{
			addScheduleDialog.setTitle("Add Schedule");
			addScheduleDialog.setVisible(true);
			Container addScheduleDialogContentPane = addScheduleDialog.getContentPane();
			addScheduleDialogContentPane.setLayout(null);

			//---- undoLabel ----
			undoLabel.setText("\u5f85\u529e\u4e8b\u9879\uff1a");
			addScheduleDialogContentPane.add(undoLabel);
			undoLabel.setBounds(30, 25, 110, 20);
			addScheduleDialogContentPane.add(undoTextField);
			undoTextField.setBounds(120, 25, 140, undoTextField.getPreferredSize().height);

			//---- remarkLabel ----
			remarkLabel.setText("\u5907\u6ce8\uff1a");
			addScheduleDialogContentPane.add(remarkLabel);
			remarkLabel.setBounds(30, 125, 95, 20);

			//---- timeLabel ----
			timeLabel.setText("\u65f6\u95f4\uff1a");
			addScheduleDialogContentPane.add(timeLabel);
			timeLabel.setBounds(30, 75, 110, 20);

			//---- chooseTypeLabel ----
			chooseTypeLabel.setText("\u9009\u62e9\u7c7b\u522b");
			addScheduleDialogContentPane.add(chooseTypeLabel);
			chooseTypeLabel.setBounds(30, 175, 110, 20);
			addScheduleDialogContentPane.add(comboBox);
			comboBox.setBounds(120, 175, 110, comboBox.getPreferredSize().height);
			comboBox.addItem("开会");
			comboBox.addItem("考试");
			comboBox.addItem("打球");
			comboBox.addItem("洗澡");
			comboBox.addItem("其他");
			
			addScheduleDialogContentPane.add(timeTextField);
			timeTextField.setBounds(120, 75, 140, 21);
			addScheduleDialogContentPane.add(remarkTextField);
			remarkTextField.setBounds(120, 125, 140, 21);

			//---- okButton ----
			okButton.setText("\u786e\u5b9a");
			addScheduleDialogContentPane.add(okButton);
			okButton.setCursor(hander);
			okButton.setBounds(new Rectangle(new Point(175, 285), okButton.getPreferredSize()));
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//写入数据到文件username_schedule.ih
					try {
						byte[] salt = PBECoder.initSalt();
						File file=new File("./Save/"+usernameString+"/"+usernameString+"_schedule.ih");
						FileWriter outputFile = new FileWriter(file, true);
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(undoTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(timeTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remarkTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(comboBox.getSelectedItem().toString().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");

						outputFile.close();
						System.out.println("日程数据写入完毕");
						new ScheduleManageFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
						addScheduleDialog.dispose();
						//添加的数据同步显示到account的主界面，表格里面..怎么实现呢
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			addScheduleDialogContentPane.add(cancelButton);
			cancelButton.setBounds(260, 285, 57, 23);
			cancelButton.setCursor(hander);

			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addScheduleDialog.dispose();
				}
			});
			
			remindLabel.setText("如需提醒请点击");
			addScheduleDialogContentPane.add(remindLabel);
			remindLabel.setBounds(130, 220, 100, 50);
			remindLabel.setForeground(Color.red);
			
			remindLabel.setCursor(hander);
			remindLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					System.out.println("ssss");
					new MyAlarm(usernameString+"的小闹钟");
				}
			});
			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < addScheduleDialogContentPane.getComponentCount(); i++) {
					Rectangle bounds = addScheduleDialogContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = addScheduleDialogContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				addScheduleDialogContentPane.setMinimumSize(preferredSize);
				addScheduleDialogContentPane.setPreferredSize(preferredSize);
			}
			addScheduleDialog.pack();
			addScheduleDialog.setLocationRelativeTo(addScheduleDialog.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog addScheduleDialog;
	private JLabel undoLabel;
	private JTextField undoTextField;
	private JLabel remarkLabel;
	private JLabel timeLabel;
	private JLabel chooseTypeLabel;
	private JComboBox comboBox;
	private JLabel remindLabel;
	private JTextField timeTextField;
	private JTextField remarkTextField;
	private JButton okButton;
	private JButton cancelButton;
	private String usernameString;
	private JPanel mainJPanel1;
	private JFrame mainFrame1;
	private Container mainContainer1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
