/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author namso1902
 */
public class FitnessCalc {

    static Rule[] rules; //hold rule from the individual split
    int all_dpPoints = 64; //no of data points from file
    static DataPoint[] training = new DataPoint[64];
    //file location
    String data2_txt = "C:\\Users\\namso1902\\Documents\\NetBeansProjects\\"
            + "GA\\build\\classes\\data2\\data2.txt";
    //Point scanner to data file 2
    File data_file = new File(data2_txt); 
    Scanner scan;

    //set data points
    public static void setDataPoints(DataPoint[] dataPoints) {
        training = dataPoints;
    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    static int getFitness(Individual individual) throws FileNotFoundException {
        //intialise fitness
        int fitness = 0;
        //No of rules
        int noOfRules = 10; 
        //data point info
        String condition, output;
        //break loop boolean
        Byte b_condition;
        int conditionCounter = 0;
        //split individual into 5 seperate
        rules = new Rule[noOfRules];
        int geneCounter = 0;
        //load indivs into rule class
        //Increment gene counter after each bit is read in
        for (int i = 0; i < noOfRules; i++) {
            //init rules
            rules[i] = new Rule();
            //load condition bits
            for (int j = 0; j < 6; j++) {
                b_condition = individual.getGene(geneCounter);
                rules[i].setCondition(j, b_condition);
                geneCounter++;
            }
            //load output bit
            rules[i].output = individual.getGene(geneCounter);
            geneCounter++;
        }
        
        //Run every datapoint over each rule
        for (int i = 0; i < training.length; i++) {
            //load in data points
            condition = training[i].getCondition();
            output = training[i].getOutput();
            //iterate over rules
            for (int j = 0; j < rules.length; j++) {
                conditionCounter = 0;
                //iterate through condition.
                //increment condition counter for every bit that matches
                for (int k = 0; k < condition.length(); k++) {
                    if ((condition.charAt(k) == rules[j].getStrCondition()
                            .charAt(k)) || (rules[j].getConditionByte(k) == 2)) {
                        conditionCounter++;
                    } 
                }
                //if every condition bit matches, check the output
                if (conditionCounter == condition.length()) {
                    //increment the fitness if output matches
                    if (rules[j].getStrOutput().equals(output)) {
                        fitness++;                   
                    }
                    //go to next data point regardless of if output matches
                    break;
                } 
            }
        } 
        //return fitness of individual
        return fitness;
    }

    //return all rules
    public static Rule[] getRules() {
        return rules;
    }
    
}
