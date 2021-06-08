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
public class TermData {

    public TermData() {
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

    public void initializeData(File file) {
        try {
            Scanner sc = new Scanner(file);

            sc.useDelimiter(";");
            this.removeData();
            while (sc.hasNext()) {
                termList.add(new Term(sc.next(), sc.next(), 0));
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TermData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData(File file) throws FileNotFoundException, NumberFormatException {
//        try {
        Scanner sc = new Scanner(file);

//        sc.useDelimiter(";");
        this.removeData();
//            int i = 0;
        while (sc.hasNext()) {
//                System.out.printf("Definition: %s\nValue: %s\nRaw Number: %d\n", sc.next(), sc.next(), Integer.parseInt(sc.next()));
            sc.useDelimiter(";");
//            System.out.println(sc.next() + sc.next() + Integer.parseInt(sc.next()));
            termList.add(new Term(sc.next(), sc.next(), Integer.parseInt(sc.next())));
            sc.useDelimiter("\n");
            sc.nextLine();
//                System.out.println(termList.get(i).toString());
//                i++;
        }
        sc.close();
//        } catch (FileNotFoundException | NumberFormatException ex) {
//            System.err.println(ex);
//        }
    }

    public void saveData(File file) {
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
//            for (int i = 0; i < termList.size(); i++) { //use format data here 
//                pw.print(termList.get(i).getDefinition() + ";" + termList.get(i).getValue() + ";" + termList.get(i).getScore() + ";");
                pw.print(this.formatData());
//            }
            pw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(TermData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String formatData() {
        String str = new String();
        for (Term indx : this.getTermList()) {
            str += indx.getDefinition() + ";" + indx.getValue() + ";" + indx.getScore() + ";\n";
        }
        return str;
    }

    private void removeData() {
        termList.clear();
    }

}
