/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.StudyRecognitionService;
import java.util.Timer;
import java.util.TimerTask;
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
        this.timer = new Timer();
    }
    private Timer timer;
    
    public void startTimer(double min, Window window) {
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                StudyDisplayController.showTimerEndWindow(window);
                this.cancel();
            }
        }, (long) (min * 60 * 1000)); //*60 for minutes
    }
    
    public void playNoise(String url) {
        try {
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void setNoise() {
        
    }
    
    public static double calculateTime(int startTime) {
        return 0.0;
    }
}