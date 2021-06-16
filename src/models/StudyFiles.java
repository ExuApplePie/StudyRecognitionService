package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author 84038001
 */
public class StudyFiles {

    public StudyFiles() {

    }

    public static void saveFile(String path, String content) {
        File file = new File(path);
        try {
            Scanner sc = new Scanner(content);
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            boolean firstPass = true;
            while (sc.hasNext()) {
                if (!firstPass) {
//                    pw.write("\n");
                    pw.println();
                }
                pw.write(sc.nextLine());
                firstPass = false;
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            Logger.getLogger(StudyFiles.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static String readFile(String path) {
        File fl = new File(path);
        String str = "";
        try {
            Scanner sc = new Scanner(fl);
            while (sc.hasNext()) {
                str += sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudyFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

    public static void saveUserData(String content) {
        StudyFiles.saveFile(System.getProperty("user.dir") + "/UserData.txt", content);
    }

    public static void saveUserReminders(String content) {
        StudyFiles.saveFile(System.getProperty("user.dir") + "/UserReminders.txt", content); //save daily reminder time and any that have not run
    }

    public static void loadUserData(TermData data) {
        try {
            data.loadData(new File(System.getProperty("user.dir") + "/UserData.txt"));
        } catch (FileNotFoundException ex) {
            StudyFiles.saveUserData(data.formatData());
        } catch (NumberFormatException ex) {
            System.err.println(ex); //this most likely shouldn't ever occur unless user modifies file
        }
    }

    public static void exportFile(String path) {

    }

}
