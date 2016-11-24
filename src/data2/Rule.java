/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data2;

/**
 *
 * @author namso1902
 */
public class Rule {
    
     static int ruleSize = 7;
    byte[] condition = new byte[getRuleSize() - 1];
    byte output;
    byte fitness;
    
    //Constructor
    public Rule() {
    }

    //Getters and Setters.
    public byte getConditionByte(int i) {
        return condition[i];
    }

    public void setCondition(int i, byte b_condition) {
        condition[i] = b_condition;
    }

    public byte getOutput() {
        return output;
    }

    public void setOutput(byte output) {
        this.output = output;
    }

    public int getFitness() {
        return fitness;
    }

    //convert condition to string (2 = #)
    public String getStrCondition() {
        String s_condition = "";
        for (int i = 0; i < getRuleSize() - 1; i++) {
            //change to hash
            if (condition[i] == 2) {
                s_condition += "#";
            }
            else {
                s_condition += condition[i];   
            }
        }
        return s_condition;
    }

    //size of 1 rule
    public static int getRuleSize() {
        return ruleSize;
    }
    
    //return output as string
    public String getStrOutput() {
        String s_output = "";
        s_output = Byte.toString(output);
        return s_output;
    }

    //return rule as string
    @Override
    public String toString() {
        String s_rule = "";
        s_rule = getStrCondition().concat(getStrOutput());
        return s_rule;
    }
   
}
