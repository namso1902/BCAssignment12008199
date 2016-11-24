/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author namso1902
 */
public class FitnessCalc {

    int noOfRules = 10;
    int trainingNo = 25, testingNo = 7;
    static DataPoint[] training = new DataPoint[32];
    
    //set datapoints from main class
    public static void setDataPoints(DataPoint[] dataPoints) {
        training = dataPoints;
    }

    
    // Calculate inidividuals fittness by comparing it to our candidate solution
    int getFitness(Individual individual) throws FileNotFoundException {
         //fitness conuter
        int fitness = 0;
        //data1 = conditionSize
        int ruleSize = Rule.getRuleSize();
        int conditionSize = Rule.getRuleSize() - 1;
        //data point variables
        String file_condition, output;
        //byte to hold each condition bit (gene)
        Byte b_condition;
        //No of times condition bit matches
        int conditionCounter = 0;
        // Loop through our individuals genes and compare them to our cadidates
        //training, testing sets:
        
        
        //split indiv into 10 seperate
        Rule[] rules = new Rule[noOfRules];
        int j = 0;
        //index for individual gene
        int geneCounter = 0;
        //split individuals into rules of 10.
        for (int i = 0; i < noOfRules; i++) {
            //initialise rule object
            rules[i] = new Rule();
            //load in condition genes
            for (j = 0; j < conditionSize; j++) {
                b_condition = individual.getGene(geneCounter);
                rules[i].setCondition(j, b_condition);
                geneCounter++;
            }
            //load in output gene
            rules[i].output = individual.getGene(geneCounter);
            geneCounter++;
        }
        
        //loop over every data point
        for (int i = 0; i < training.length; i++) {
            //get data point condition and output
            file_condition = training[i].getCondition();
            output = training[i].getOutput();
            for (j = 0; j < rules.length; j++) {
                int k;
                conditionCounter = 0;
                //loop through condition bits
                for (k = 0; k < conditionSize; k++) {
                    //inc condition counter if there is a match
                    if ((file_condition.charAt(k) == rules[j].getStrCondition()
                            .charAt(k)) || (rules[j].getConditionByte(k) == 2)) {
                        conditionCounter++;
                    } 
                }
                //if condition and output is matched, inc fitness
                if (conditionCounter == conditionSize) {
                    if (rules[j].getStrOutput().equals(output)) {
                        fitness++;                   
                    }
                    //return to next datapoint regardless of if output is right.
                    break;
                } 
            }
        } 
        //return fitness
        return fitness;
    }
    
}
