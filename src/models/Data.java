/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 84038001
 */
public class Data {

    public Data() {
    }

    private final ObservableList<Term> TermList = FXCollections.observableArrayList(terms
            -> new Observable[]{terms.definitionProperty(), terms.valueProperty()});
    
    private final ObjectProperty<Term> currentTerm = new SimpleObjectProperty<>(null);
    
    public ObjectProperty<Term> currentTermProperty() {
        return currentTerm;
    }
}
