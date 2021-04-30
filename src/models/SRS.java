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
    Map<String[], Integer> scoreMap = new HashMap<>();

    private boolean defaultMode; //mode switches beteween Terms or Definition, default is asking for definition (true) and non-default is terms (false)

    private final void setDictionary(String path) {
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
            scoreMap.put(element, 0);
            termMap.put(term, currentIndx);
            definitionMap.put(definition, currentIndx);
            currentIndx++;
        }
        sc.close();

    }

    public final void updateDictionary(String path) {
        //same as setDictionary but just check if the term already exists
    }

    //scoreMap is linked to the ArrayList  index in dictionary
    //Need to get the index and then it can return the score
    //get the question, use termMap or definitionMap to find the index in arrayList
    public int getScore(String question) {
        int indx = -1;
        if (this.getDefaultMode()) { //asking for definition - so check term
            indx = this.termMap.get(question);
        } else {
            indx = this.definitionMap.get(question);
        }
        return this.scoreMap.get(this.dictionary.get(indx));
    }

    private void setScore(boolean b) {
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
        if (this.getDefaultMode()) { //when checking for the definition
            int indx = this.termMap.get(question);
            if (this.dictionary.get(indx)[1].equals(ans)) {
                this.scoreMap.put(this.dictionary.get(indx), this.scoreMap.get(this.dictionary.get(indx)) + 1);
                return true;
            } else {
                this.scoreMap.put(this.dictionary.get(indx), this.scoreMap.get(this.dictionary.get(indx)) - 1);
                return false;
            }
        } else {
            int indx = this.definitionMap.get(question);
            if (this.dictionary.get(indx)[0].equals(ans)) {
                this.scoreMap.put(this.dictionary.get(indx), this.scoreMap.get(this.dictionary.get(indx)) + 1);
                return true;
            } else {
                this.scoreMap.put(this.dictionary.get(indx), this.scoreMap.get(this.dictionary.get(indx)) - 1);
                return false;
            }
        }
    }

    public boolean getDefaultMode() {
        return this.defaultMode;
    }

    private void setDefaultMode(boolean b) {
        this.defaultMode = b;
    }
}
