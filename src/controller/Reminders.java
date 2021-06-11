/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.util.Date;



/**
 *
 * @author niclo
 */
public class Reminders {

    public Reminders() {
       
    }
    

    public static void displayNotification(String title, String content) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("/images/icon.png");
        
        TrayIcon trayIcon = new TrayIcon(image, "Reminder");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Reminder Icon");
        tray.add(trayIcon);
        
        trayIcon.displayMessage(title, content, TrayIcon.MessageType.INFO);
    }

}
