package com.ihelper.tools;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	
	
	public ImageLabel(String imgURL){
		ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource(imgURL));
		setSize(icon.getImage().getWidth(null),icon.getImage().getHeight(null));
		setIcon(icon);
		setIconTextGap(0);
		setBorder(null);
		setText(null);
		setOpaque(false);
		
	}
	
	public ImageLabel(ImageIcon icon){
		setSize(icon.getImage().getWidth(null),icon.getImage().getHeight(null));
		setIcon(icon);
		setIconTextGap(0);
		setBorder(null);
		setText(null);
		setOpaque(false);
	}


}
