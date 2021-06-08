/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.StudyRecognitionService;
import java.awt.AWTException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Window;

/**
 *
 * @author 84038001
 */
public class TimerRunner {

    public TimerRunner() {
    }

    public void startTimer(double min) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                StudyDisplayController.showTimerEndWindow();
                playNoise("alarm.wav");
                this.cancel();
            }
        }, (long) (min * 60 * 1000)); //*60 for minutes
    }

    public void playNoise(String url) {
        MusicPlayer mp = new MusicPlayer();
        mp.playMusic(url, 0.2, true);
    }

    public void setNoise() {

    }

    public void scheduleDailyReminders(String time) {
        Scanner sc = new Scanner(time);
        sc.useDelimiter(":");
        int hour = Integer.parseInt(sc.next());
        int min = Integer.parseInt(sc.next());
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, min);
        today.set(Calendar.SECOND, 0);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Reminders.displayNotification("Daily Reminder", "Time to Study!");
                } catch (AWTException ex) {
                    System.err.println(ex);
                }
            }
        }, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }
    

    public static double calculateTime(int startTime) {
        return 0.0;
    }
}
