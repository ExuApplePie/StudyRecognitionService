package models;

import controller.StudyFiles;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javafx.collections.ObservableList;

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

    Data data = new Data();
//    ArrayList<Term> dictionary = new ArrayList<>();
//    Map<String, Integer> termMap = new HashMap<>();
//    Map<String, Integer> definitionMap = new HashMap<>();// better way to tdo this than 2 maps?
//    Map<String[], Integer> scoreMap = new HashMap<>();

    private boolean defaultMode; //mode switches beteween Definitions or values, default is asking for value (true) and non-default is definition (false)

    private final void setDictionary(String path) {
        this.data.initializeData(new File(path));
    }

    public final void updateDictionary(String path) {
        //same as setDictionary but just check if the term already exists
    }

    //scoreMap is linked to the ArrayList  index in dictionary
    //Need to get the index and then it can return the score
    //get the question, use termMap or definitionMap to find the index in arrayList
    public int getScore(String question) {
        if (this.getDefaultMode()) { //asking for value - so check definition
//            for (Term currTerm : this.data.getTermList()) {
//                if (currTerm.getValue().equals(question)) {
//                    return currTerm.getScore();
//                }
//            }
            try {
                return this.findElement(this.data.getTermList(), question).getScore();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("the definition does not exist");
            }

        } else {
//            for (Term currTerm : this.data.getTermList()) {
//                if (currTerm.getDefinition().equals(question)) {
//                    return currTerm.getScore();
//                }
//            }
            try {
                return this.findElement(this.data.getTermList(), question).getScore();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("the value does not exist");
            }
        }
    }

    private void setScore(boolean b, Term t) {
        if (b) {
            t.setScore(t.getScore() + 1);
        } else {
            t.setScore(t.getScore() - 1);
        }
    }

    public String displayDefinition() {
        Random r = new Random();
        this.setDefaultMode(false);
        int size = this.data.getTermList().size();
        return this.data.getTermList().get(r.nextInt(size)).getDefinition();
    }

    public String displayValue() {
        Random r = new Random();
        this.setDefaultMode(true);
        int size = this.data.getTermList().size();
        return this.data.getTermList().get(r.nextInt(size)).getValue();
    }

    public boolean checkAns(String ans, String question) {
        Term currTerm = this.findElement(this.data.getTermList(), question);
        boolean returnVal;
        if (this.getDefaultMode()) { //when checking for the value
//            for (Term currTerm : this.data.getTermList()) {
//                if (currTerm.getValue().equals(question)) { //check the question to find the correct element in the list
//                    this.setScore(ans.equalsIgnoreCase(currTerm.getDefinition()), currTerm);
//                    return ans.equalsIgnoreCase(currTerm.getDefinition());
//                }
//            }
            returnVal = currTerm.getValue().equalsIgnoreCase(ans);
            this.setScore(returnVal, currTerm);
            return returnVal;
        } else {
//            for (Term currTerm : this.data.getTermList()) {
//                if (currTerm.getDefinition().equals(question)) {
//                    this.setScore(ans.equalsIgnoreCase(currTerm.getValue()), currTerm);
//                    return ans.equalsIgnoreCase(currTerm.getValue());
//                }
//            }
            returnVal = currTerm.getDefinition().equalsIgnoreCase(ans);
            this.setScore(returnVal, currTerm);
            return returnVal;
        }
    }

    public Term findElement(ObservableList<Term> list, String question) {
        for (Term currTerm : list) {
            if (currTerm.getDefinition().equalsIgnoreCase(question) || currTerm.getValue().equalsIgnoreCase(question)) {
                return currTerm;
            }
        }
        throw new IllegalArgumentException("element not found");
    }

    public boolean getDefaultMode() {
        return this.defaultMode;
    }

    private void setDefaultMode(boolean b) {
        this.defaultMode = b;
    }
}
