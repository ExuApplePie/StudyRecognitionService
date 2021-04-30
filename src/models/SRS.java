package models;

import controller.StudyFiles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
    Map<String, Integer> termMap = new HashMap<>();
    Map<String, Integer> definitionMap = new HashMap<>();// better way to tdo this than 2 maps?

    private boolean defaultMode; //mode switches beteween Terms or Definition, default is asking for definition (true) and non-default is terms (false)

    public final void setDictionary(String path) {
        //calls StudyFiles.readFile()
        String str = StudyFiles.readFile(path);
        if (str == null) {
            System.out.println("no dictionary values found");
            return;
        }
        Scanner sc = new Scanner(str);
        sc.useDelimiter(";");
        int currentIndx = 0;
        while (sc.hasNext()) {
            String term = sc.next();
            String definition = sc.next();
            String[] element = {term, definition};
            dictionary.add(element);
            termMap.put(term, currentIndx);
            definitionMap.put(definition, currentIndx);
            currentIndx++;
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

    public String displayTerm() {
        Random r = new Random();
        String s[] = dictionary.get(r.nextInt(dictionary.size()));
        this.setDefaultMode(true);
        return s[0];
    }

    public String displayDefinition() {
        Random r = new Random();
        String s[] = dictionary.get(r.nextInt(dictionary.size()));
        this.setDefaultMode(false);
        return s[1];
    }

    public boolean checkAns(String ans, String question) {
        for (int i = 0; i < this.dictionary.size(); i++) {
            System.out.println(this.dictionary.get(i)[0]);
            System.out.println(this.dictionary.get(i)[1]);
            System.out.println("");
        }
//        System.out.println("ans: " + ans + "\nquestion: " + question + "\nmode: " + this.getDefaultMode() + "\n");
        if (this.getDefaultMode()) { //when checking for the definition
            int indx = this.termMap.get(question);
            System.out.println(dictionary.get(indx)[1]);
            if (dictionary.get(indx)[1].equals(ans)) {
                return true;
            } else {
                return false;
            }
        } else {
            int indx = this.definitionMap.get(question);
            System.out.println(dictionary.get(indx)[0]);
            if (dictionary.get(indx)[0].equals(ans)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean getDefaultMode() {
        return this.defaultMode;
    }

    public void setDefaultMode(boolean b) {
        this.defaultMode = b;
    }
}
