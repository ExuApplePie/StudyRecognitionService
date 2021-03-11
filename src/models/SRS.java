package models;

import controller.StudyFiles;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 84038001
 */
public class SRS {

    public SRS(String path) {
        this.setDictionary(path);
    }

    ArrayList<String[]> dictionary = new ArrayList<>();


    public final void setDictionary(String path) {
        //calls StudyFiles.readFile()
        String str = StudyFiles.readFile(path);
        if (str == null) {
            System.out.println("no dictionary values found");
            return;
        }
        Scanner sc = new Scanner(str);
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            String key = sc.next();
            String value = sc.next();
            String[] element = {key, value};
            dictionary.add(element);
        }
        sc.close();

    }

    public int getScore() {
        return 0;
    }

    private void setScore() {
        //calls calculateScore
    }

    private void calculateScore() {

    }

    public String displayQuestion() {
        Random r = new Random();
        String s[] = dictionary.get(r.nextInt(dictionary.size()));
        return s[0];
    }

    public void checkAns() {

    }
}
