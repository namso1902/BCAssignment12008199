/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data1;

/**
 *
 * @author namso1902
 */
public class DataPoint {
    
    //size of data points for data set 1.
    static int size = 6;
    
    String condition; 
    String output;

    //constructor
    public DataPoint() {
    }

    //Getters and Setters for class variables.
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public static int getSize() {
        return size;
    } 
}
