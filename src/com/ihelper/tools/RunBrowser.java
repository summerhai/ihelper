/**
 * Copyright  100 yearsAdministrator

 * All rights reserved.
 */
package com.ihelper.tools;

import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
/** *//**
*
* @author Anthrax
*此类负责检测系统的默认浏览器等程序，并负责启动它们
* @netSite 指定要显示的网址
*/
public class RunBrowser{
private Desktop desktop;
private URI uri;
private String netSite;
private Cursor hander;
/** *//** Creates a new instance of DesktopRuner */
public RunBrowser(){
    this.desktop = Desktop.getDesktop();
}
/**//*
*检测系统是否支持浏览器
*/
public boolean checkBroswer(){
    if(desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)){
        return true;
    }
    else{
        return false;
    }
}
/**//*
*运行默认浏览器，并在其中显示指定网址
*/
public void runBroswer(){
    netSite = "http://blog.csdn.net/minglaihan/article/details/8764415";
    try {
        uri = new URI(netSite);
    } catch (URISyntaxException ex){
        ex.printStackTrace();
    }
    try{
        desktop.browse(uri);
    } catch (IOException ex){
        ex.printStackTrace();
    }
}
/**//*
*改变鼠标形状
*/
public void changeMouse(JLabel label){
    hander = new Cursor(Cursor.HAND_CURSOR);
    label.setCursor(hander);
}
}


//假如有这样一个JLabel,就可以给这个JLabel加超链接了
//JLabel   jl=new JLabel("申请帐号");
//LLogon.setCursor(new Cursor(Cursor.HAND_CURSOR));//这样也可以改变鼠标形状
//    LLogon.addMouseListener(new   MouseAdapter(){   
//      public   void   mouseClicked(MouseEvent   e){   
//      try{     
//      new RunBrowser().runBroswer();
//      }catch(Exception   ex){   
//      ex.printStackTrace();   
//      }   
//      }   
//      }); 
//}
