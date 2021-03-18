/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 840380010
 */
public class Data {

    public Data() {
    }

    private final ObservableList<Term> termList = FXCollections.observableArrayList(terms
            -> new Observable[]{terms.definitionProperty(), terms.valueProperty()});

    private final ObjectProperty<Term> currentTerm = new SimpleObjectProperty<>(null);

    public final ObjectProperty<Term> currentTermProperty() {
        return currentTerm;
    }

    public final Term getCurrentTerm() {
        return currentTermProperty().get();
    }

    public final void setCurrentTerm(Term term) {
        currentTermProperty().set(term);
    }

    public ObservableList<Term> getTermList() {
        return termList;
    }

    public void loadData(File file) {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                termList.set(0, new Term(sc.next(), sc.next(), Integer.parseInt(sc.next())));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveData(File file) {
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < termList.size(); i++) {
                pw.println(termList.get(i).toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
