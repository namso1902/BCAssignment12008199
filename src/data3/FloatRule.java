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
public class FloatRule {
    
    //(6 x 2) + 1
    int noOfRules;
    //data3 = 13.
    static int ruleSize = 13;
    //6 x 2 float array
    float floatRanges[][] = new float[6][2];
    float output;
    byte fitness;

    //constructor
    public FloatRule() {
    }
    
    //Getters and Setters
    public float getOutput() {
        return output;
    }

    public void setOutput(float output) {
        this.output = output;
    }
    
    public int getNoOfRules() {
        return noOfRules;
    }

    public void setNoOfRules(int noOfRules) {
        this.noOfRules = noOfRules;
    }

    public static int getRuleSize() {
        return ruleSize;
    }

    public static void setRuleSize(int ruleSize) {
        FloatRule.ruleSize = ruleSize;
    }

    public float[][] getFloatRanges() {
        return floatRanges;
    }
    
    public float getFloat(int row, int column) {
        return floatRanges[row][column];
    }

    public void setFloatRanges(float[][] floatRanges) {
        this.floatRanges = floatRanges;
    }

    @Override
    public String toString() {
        return "FloatRule{" + "noOfRules=" + noOfRules + ", floatRanges=" + floatRanges + ", output=" + output + ", fitness=" + fitness + '}';
    }
}
