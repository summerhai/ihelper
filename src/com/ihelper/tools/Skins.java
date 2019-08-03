/**
 * Copyright  100 yearsAdministrator

 * All rights reserved.
 */
package com.ihelper.tools;

import java.awt.EventQueue;
import java.awt.SystemTray;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;
import org.jvnet.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;
import org.jvnet.substance.skin.BusinessSkin;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.EmeraldDuskSkin;
import org.jvnet.substance.skin.NebulaBrickWallSkin;
import org.jvnet.substance.skin.NebulaSkin;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.RavenSkin;
import org.jvnet.substance.skin.SaharaSkin;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenLookAndFeel;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

import com.ihelper.mainframe.MainFrame;


/**
 * @author liang
 * @date 2012/4/13
 * @version 1.0
 * @describe 为GUI提供皮肤，共提供11种皮肤，可根据要求，自定义水印。
 * */

public class Skins {

	public static SystemTray st;

	public static void setThemeSahara()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己该，使用自己喜欢的图片来做应用程序的水印图片
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/6.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_ANCHOR);
            //透明度调节
            watermark.setOpacity((float) 0.35);
            UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());
            SubstanceSkin skin = new SaharaSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void setThemeOfficeSilver()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己该，使用自己喜欢的图片来做应用程序的水印图片
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/8.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_ANCHOR);
            //透明度调节
            watermark.setOpacity((float) 0.35);
            UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());
            SubstanceSkin skin = new SaharaSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void setThemeNebula()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/9.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.55);
            UIManager.setLookAndFeel(new SubstanceNebulaLookAndFeel());
            SubstanceSkin skin = new NebulaSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void setThemeNebulaBrickWall()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/10.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceNebulaBrickWallLookAndFeel());
            SubstanceSkin skin = new NebulaBrickWallSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	public static void setThemeAutumn()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/11.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel());
            SubstanceSkin skin = new AutumnSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	public static void setThemeBusiness()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/2.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
            SubstanceSkin skin = new BusinessSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeCreme()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/4.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());
            SubstanceSkin skin = new CremeSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeBusinessBlackSteel()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/3.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.55);
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            SubstanceSkin skin = new BusinessBlackSteelSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeRaven()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/7.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
            SubstanceSkin skin = new RavenSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeEmeraldDusk()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/5.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceEmeraldDuskLookAndFeel());
            SubstanceSkin skin = new EmeraldDuskSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	
	public static void setThemeOfficeBlue()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //新建一个图片水印，路径可以自己改，使用自己喜欢的图片来做应用程序的水印图片、
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainFrame.class.getResourceAsStream("/image/1.jpg"));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            //透明度调节
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceEmeraldDuskLookAndFeel());
            SubstanceSkin skin = new EmeraldDuskSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	
}
