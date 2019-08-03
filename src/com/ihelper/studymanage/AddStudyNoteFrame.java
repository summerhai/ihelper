package com.ihelper.studymanage;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.hg.xdoc.XDocBuilder;
import com.hg.xdoc.XDocIO;

/**
 * XDocBuilderǶ�����
 * @author xdoc
 */
public class AddStudyNoteFrame{
    private static XDocBuilder builder;
    private String usernameString;
	private JFrame mainFrame1;
	private Container mainContainer1;
	private JPanel mainJPanel1;
    /**
     * @param args
     */
    public AddStudyNoteFrame(String username,JFrame mainFrame,Container mainContainer,JPanel mainJPanel,String filename) {

    	usernameString=username;
    	mainContainer1=mainContainer;
    	mainFrame1=mainFrame;
    	mainJPanel1=mainJPanel;
    	if(filename == null){
    		  try {
    	            
    	            //��ȡXDocBuilderʵ��
    	            builder = new XDocBuilder();
    	            JFrame frame = new JFrame("��¼ѧϰ����ĵ��");
    	            //��XDocBuilder���������
    	            frame.getContentPane().add(builder, BorderLayout.CENTER);
    	            JToolBar bar = new JToolBar("������");
    	            JButton btn = new JButton("�½�");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //�½�
    	                    builder.create();
    	                }
    	            });
    	            btn = new JButton("��");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //��
    	                    builder.open();
    	                    //builder.open("http://www.hgsql.com/down/XDocIntro.xdoc", XDocIO.READ_FORMAT_XDOC);
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("�鿴XML");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //��ʾXML����
    	                    try {
    	                        JOptionPane.showMessageDialog(null, builder.getXml());
    	                    } catch (Exception e1) {
    	                        JOptionPane.showMessageDialog(null, e1.getMessage());
    	                    }
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("����");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //���浱ǰ�ļ�
    	                    builder.save();
    	                    
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("ȫ������");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    for (int i = 0; i < builder.getXDocCount(); i++) {
    	                        //ѡ���i���ļ�
    	                        builder.activeXDoc(i);
    	                        //����
    	                        builder.save();
    	                        //builder.save("xdox" + i + ".rtf", XDocIO.WRITE_FORMAT_PDF);
    	                    }
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("�ر�");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //�رյ�ǰ�ļ�
    	                    builder.close();
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("��ӡ");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    builder.print();
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("ֱ�Ӵ�ӡ");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //��ӡ��ָ����ӡ��
    	                    builder.print("HG JPD �����ӡ��");
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("����");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    builder.about();
    	                }
    	            });
    	            bar.add(btn);
    	            Container content = frame.getContentPane();
    	            content.add(bar, BorderLayout.NORTH);
    	            frame.setSize(800, 600);
    	            frame.setVisible(true);
    	            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	            
    	            frame.addWindowListener(new WindowListener() {
    	                public void windowOpened(WindowEvent e) {}
    	                public void windowClosing(WindowEvent e) {
    	                    //frame�ر�ʱ������
    	                    for (int i = 0; i < builder.getXDocCount(); i++) {
    	                        try {
    	                            XDocIO.write(builder.getXDoc(i), new File("c:/xdoc" + i + ".xdoc"));
    	                        } catch (Exception e1) {
    	                            JOptionPane.showMessageDialog(null, e1.getMessage());
    	                        }
    	                    }
    	                    
    	                }
    	                public void windowClosed(WindowEvent e) {
    	                	
    	                }
    	                public void windowIconified(WindowEvent e) {}
    	                public void windowDeiconified(WindowEvent e) {}
    	                public void windowActivated(WindowEvent e) {}
    	                public void windowDeactivated(WindowEvent e) {}
    	            });
    	            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	        } catch (Exception e) {
    	            JOptionPane.showMessageDialog(null, e.getMessage());
    	        }
    	}
		  else {
			  try {
  	           
  	            //��ȡXDocBuilderʵ��
  	            builder = new XDocBuilder();
  	            JFrame frame = new JFrame("��¼ѧϰ����ĵ��");
  	            //��XDocBuilder���������
  	            frame.getContentPane().add(builder, BorderLayout.CENTER);
  	            JToolBar bar = new JToolBar("������");
  	            JButton btn = new JButton("�½�");
  	            
  	            builder.open(".\\Save\\"+usernameString+"\\"+usernameString+"_StudyNotes\\"+filename);
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //�½�
  	                    builder.create();
  	                }
  	            });
  	            btn = new JButton("��");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //��
  	                    builder.open();
  	                    //builder.open("http://www.hgsql.com/down/XDocIntro.xdoc", XDocIO.READ_FORMAT_XDOC);
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("�鿴XML");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //��ʾXML����
  	                    try {
  	                        JOptionPane.showMessageDialog(null, builder.getXml());
  	                    } catch (Exception e1) {
  	                        JOptionPane.showMessageDialog(null, e1.getMessage());
  	                    }
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("����");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //���浱ǰ�ļ�
  	                    builder.save();
  	                    
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("ȫ������");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    for (int i = 0; i < builder.getXDocCount(); i++) {
  	                        //ѡ���i���ļ�
  	                        builder.activeXDoc(i);
  	                        //����
  	                        builder.save();
  	                        //builder.save("xdox" + i + ".rtf", XDocIO.WRITE_FORMAT_PDF);
  	                    }
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("�ر�");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //�رյ�ǰ�ļ�
  	                    builder.close();
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("��ӡ");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    builder.print();
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("ֱ�Ӵ�ӡ");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //��ӡ��ָ����ӡ��
  	                    builder.print("HG JPD �����ӡ��");
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("����");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    builder.about();
  	                }
  	            });
  	            bar.add(btn);
  	            Container content = frame.getContentPane();
  	            content.add(bar, BorderLayout.NORTH);
  	            frame.setSize(800, 600);
  	            frame.setVisible(true);
  	            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  	            
  	            frame.addWindowListener(new WindowListener() {
  	                public void windowOpened(WindowEvent e) {}
  	                public void windowClosing(WindowEvent e) {
  	                    //frame�ر�ʱ������
  	                    for (int i = 0; i < builder.getXDocCount(); i++) {
  	                        try {
  	                            XDocIO.write(builder.getXDoc(i), new File("c:/xdoc" + i + ".xdoc"));
  	                        } catch (Exception e1) {
  	                            JOptionPane.showMessageDialog(null, e1.getMessage());
  	                        }
  	                    }
  	                    
  	                }
  	                public void windowClosed(WindowEvent e) {
  	                	
  	                }
  	                public void windowIconified(WindowEvent e) {}
  	                public void windowDeiconified(WindowEvent e) {}
  	                public void windowActivated(WindowEvent e) {}
  	                public void windowDeactivated(WindowEvent e) {}
  	            });
  	            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  	        } catch (Exception e) {
  	            JOptionPane.showMessageDialog(null, e.getMessage());
  	        }
				
		}
      
    }
}
