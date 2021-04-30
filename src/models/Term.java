/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 84038001
 */
public class Term {
    public Term (String definition, String value, int score) {
        setDefinition(definition);
        setValue(value);
        setScore(0);
    }
    
    private final StringProperty definition = new SimpleStringProperty();
    
    public final StringProperty definitionProperty() {
        return this.definition;
    }
    
    public final String getDefinition() {
        return this.definitionProperty().get();
    }
    
    public final void setDefinition(final String definition) {
        this.definitionProperty().set(definition);
    }
    
    private final StringProperty value = new SimpleStringProperty();
    
    public final StringProperty valueProperty() {
        return this.value;
    }
    
    public final String getValue() {
        return this.valueProperty().get();
    }
    
    public final void setValue(final String value) {
        this.valueProperty().set(value);
    }
    
    private int score;
    
    public final int getScore() {
        return this.score;
    }
    
    public final void setScore(int i) {
        this.score = i;
    }
    
    
    @Override
    public String toString() {
        return getDefinition() + " " + getValue() + " " + getScore();
    }
        
    
}
