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
 * XDocBuilder嵌入测试
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
    	            
    	            //获取XDocBuilder实例
    	            builder = new XDocBuilder();
    	            JFrame frame = new JFrame("记录学习生活的点滴");
    	            //将XDocBuilder放入面板中
    	            frame.getContentPane().add(builder, BorderLayout.CENTER);
    	            JToolBar bar = new JToolBar("工具条");
    	            JButton btn = new JButton("新建");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //新建
    	                    builder.create();
    	                }
    	            });
    	            btn = new JButton("打开");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //打开
    	                    builder.open();
    	                    //builder.open("http://www.hgsql.com/down/XDocIntro.xdoc", XDocIO.READ_FORMAT_XDOC);
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("查看XML");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //显示XML内容
    	                    try {
    	                        JOptionPane.showMessageDialog(null, builder.getXml());
    	                    } catch (Exception e1) {
    	                        JOptionPane.showMessageDialog(null, e1.getMessage());
    	                    }
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("保存");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //保存当前文件
    	                    builder.save();
    	                    
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("全部保存");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    for (int i = 0; i < builder.getXDocCount(); i++) {
    	                        //选择第i个文件
    	                        builder.activeXDoc(i);
    	                        //保存
    	                        builder.save();
    	                        //builder.save("xdox" + i + ".rtf", XDocIO.WRITE_FORMAT_PDF);
    	                    }
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("关闭");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //关闭当前文件
    	                    builder.close();
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("打印");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    builder.print();
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("直接打印");
    	            btn.addActionListener(new ActionListener() {
    	                public void actionPerformed(ActionEvent e) {
    	                    //打印到指定打印机
    	                    builder.print("HG JPD 虚拟打印机");
    	                }
    	            });
    	            bar.add(btn);
    	            btn = new JButton("关于");
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
    	                    //frame关闭时做处理
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
  	           
  	            //获取XDocBuilder实例
  	            builder = new XDocBuilder();
  	            JFrame frame = new JFrame("记录学习生活的点滴");
  	            //将XDocBuilder放入面板中
  	            frame.getContentPane().add(builder, BorderLayout.CENTER);
  	            JToolBar bar = new JToolBar("工具条");
  	            JButton btn = new JButton("新建");
  	            
  	            builder.open(".\\Save\\"+usernameString+"\\"+usernameString+"_StudyNotes\\"+filename);
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //新建
  	                    builder.create();
  	                }
  	            });
  	            btn = new JButton("打开");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //打开
  	                    builder.open();
  	                    //builder.open("http://www.hgsql.com/down/XDocIntro.xdoc", XDocIO.READ_FORMAT_XDOC);
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("查看XML");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //显示XML内容
  	                    try {
  	                        JOptionPane.showMessageDialog(null, builder.getXml());
  	                    } catch (Exception e1) {
  	                        JOptionPane.showMessageDialog(null, e1.getMessage());
  	                    }
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("保存");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //保存当前文件
  	                    builder.save();
  	                    
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("全部保存");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    for (int i = 0; i < builder.getXDocCount(); i++) {
  	                        //选择第i个文件
  	                        builder.activeXDoc(i);
  	                        //保存
  	                        builder.save();
  	                        //builder.save("xdox" + i + ".rtf", XDocIO.WRITE_FORMAT_PDF);
  	                    }
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("关闭");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //关闭当前文件
  	                    builder.close();
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("打印");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    builder.print();
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("直接打印");
  	            btn.addActionListener(new ActionListener() {
  	                public void actionPerformed(ActionEvent e) {
  	                    //打印到指定打印机
  	                    builder.print("HG JPD 虚拟打印机");
  	                }
  	            });
  	            bar.add(btn);
  	            btn = new JButton("关于");
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
  	                    //frame关闭时做处理
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
