/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data3;

/**
 *
 * @author namso1902
 */
public class DataPoint {
    
    //size of data points for data set 3.
    static int size = 7;
    
    float[] floats = new float[6];
    int output;
    
    //constructor
    public DataPoint() {
    }

    //Getters and Setters
    public float[] getFloats() {
        return floats;
    }

    public void setFloats(float[] floats) {
        this.floats = floats;
    }
    
    public float getFloat(int i) {
        return floats[i];
    }
    
    public void setFloat(int i, float f) {
        floats[i] = f;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public static int getSize() {
        return size;
    } 
}
