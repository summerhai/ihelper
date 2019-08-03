/**
 * Copyright  100 yearsAdministrator

 * All rights reserved.
 */
package com.ihelper.studymanage;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ihelper.accountmanage.AccountManageFrame;
import com.ihelper.tools.Coder;
import com.ihelper.tools.DateChooser;
import com.ihelper.tools.PBECoder;

/**
 * @author Administrator
 *
 */
public class StudyTargetDialog {

	public StudyTargetDialog(JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String username) {
		usernameString=username;
		mainFrame1=mainFrame;
		mainContainer1=mainContainer;
		mainJPanel1=mainJPanel;
		initComponents();
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Han LaiMing
		studyTargetDialog = new JDialog();
		studyTargetLabel = new JLabel();
		studyTargetTextField = new JTextField();
		endTimeLabel = new JLabel();
		startTimeLabel = new JLabel();
		remarkLabel = new JLabel();
		startTimeTextField = new JTextField();
		endTimeTextField = new JTextField();
		remarkTextField = new JTextField();
		cancelButton = new JButton();
		okButton = new JButton();
		DateChooser timeChooser = DateChooser.getInstance("yyyy-MM-dd");
		timeChooser.register(startTimeTextField);
		DateChooser timeChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		timeChooser1.register(endTimeTextField);
//		timeChooser.register(endTimeTextField);
		Cursor hander = new Cursor(Cursor.HAND_CURSOR);

		//======== addAccountDialog ========
		{
			studyTargetDialog.setTitle("学习目标，你准备好了吗");
			studyTargetDialog.setVisible(true);
			Container studyTargetDialogContentPane = studyTargetDialog.getContentPane();
			studyTargetDialogContentPane.setLayout(null);

			//---- networkNameLabel ----
			studyTargetLabel.setText("近期学习目标：");
			studyTargetDialogContentPane.add(studyTargetLabel);
			studyTargetLabel.setBounds(30, 25, 110, 20);
			studyTargetDialogContentPane.add(studyTargetTextField);
			studyTargetTextField.setBounds(120, 25, 105, studyTargetTextField.getPreferredSize().height);

			//---- passwordLabel ----
			endTimeLabel.setText("结束时间：");
			studyTargetDialogContentPane.add(endTimeLabel);
			endTimeLabel.setBounds(30, 125, 95, 20);

			//---- accountLabel ----
			startTimeLabel.setText("开始时间：");
			studyTargetDialogContentPane.add(startTimeLabel);
			startTimeLabel.setBounds(30, 75, 110, 20);

			//---- remarkLabel ----
			remarkLabel.setText("备注");
			studyTargetDialogContentPane.add(remarkLabel);
			remarkLabel.setBounds(30, 225, 110, 20);
			
			studyTargetDialogContentPane.add(startTimeTextField);
			startTimeTextField.setBounds(120, 75, 105, 21);
			
			studyTargetDialogContentPane.add(endTimeTextField);
			endTimeTextField.setBounds(120, 125, 105, 21);
			
			studyTargetDialogContentPane.add(remarkTextField);
			remarkTextField.setBounds(120, 225, 105, 21);

			//---- cancelButton ----
			cancelButton.setText("\u53d6\u6d88");
			studyTargetDialogContentPane.add(cancelButton);
			cancelButton.setBounds(new Rectangle(new Point(260, 285), cancelButton.getPreferredSize()));
			cancelButton.setCursor(hander);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					studyTargetDialog.dispose();
				}
			});
			//---- okButton ----
			okButton.setText("\u786e\u5b9a");
			okButton.setCursor(hander);
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						File file1=new File("./Save/"+usernameString+"/"+usernameString+"_studyNotesTarget.ih");
						FileOutputStream temp = new FileOutputStream(file1);
						temp.close();
						File file=new File("./Save/"+usernameString+"/"+usernameString+"_studyNotesTarget.ih");
						FileWriter outputFile = new FileWriter(file, true);
						byte[] salt = PBECoder.initSalt();
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(studyTargetTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(startTimeTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(endTimeTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.write(Coder.parseByte2HexStr(PBECoder.encrypt(Coder.encryptBASE64(remarkTextField.getText().getBytes()).getBytes(), "ihelperforyou", salt))+"\r\n");
						outputFile.close();
						System.out.println("学习目标数据写入完毕");
						studyTargetDialog.dispose();
						new StudyNoteFrame(mainFrame1, mainContainer1, mainJPanel1, usernameString);
						System.out.println("add ok之后执行showtable");
						
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
			studyTargetDialogContentPane.add(okButton);
			okButton.setBounds(175, 285, 57, 23);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < studyTargetDialogContentPane.getComponentCount(); i++) {
					Rectangle bounds = studyTargetDialogContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = studyTargetDialogContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				studyTargetDialogContentPane.setMinimumSize(preferredSize);
				studyTargetDialogContentPane.setPreferredSize(preferredSize);
			}
			studyTargetDialog.pack();
			studyTargetDialog.setLocationRelativeTo(studyTargetDialog.getOwner());
			studyTargetDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Han LaiMing
	private JDialog studyTargetDialog;
	private JLabel studyTargetLabel;
	private JTextField studyTargetTextField;
	private JLabel endTimeLabel;
	private JLabel startTimeLabel;
	private JLabel remarkLabel;
	private JTextField startTimeTextField;
	private JTextField endTimeTextField;
	private JTextField remarkTextField;
	private JButton cancelButton;
	private JButton okButton;
	private String usernameString;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

}
