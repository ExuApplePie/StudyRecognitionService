package controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Logger;
import java.util.logging.Level;
import controller.models.StudyDisplay;
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
    
    public static void saveFile(File file) {
        if (file != null) {
            try {
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.print("hi");
                pw.close();
                fw.close();
                
            } catch (IOException e) {
                Logger.getLogger(StudyFiles.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
    }
    
    public static void saveFile(String path) {
        
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
    
    public static String saveUserData() {
        saveFile("path to user data");
        return "successfully saved data";
    }
    
    public static void exportFile(String path) {
        
    }
    
}
