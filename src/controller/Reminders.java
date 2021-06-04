/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author niclo
 */
public class Reminders {

    public Reminders() {
       
    }
    Scheduler scheduler;
    

    public void displayNotification() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            scheduler.start();
            
//            JobDetail job = newJob(TimerRunner.class)
//                    .withIdentity("job1", "group1")
//                    .build();
        } catch (SchedulerException ex) {
            System.err.println(ex);
        }

    }

    public void setReminder(String content, int date) {

    }
}
