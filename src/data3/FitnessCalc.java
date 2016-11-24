/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data3;

import java.io.FileNotFoundException;

/**
 *
 * @author namso1902
 */
public class FitnessCalc {

    static DataPoint[] training = new DataPoint[2000];

    //Load in training data
    public static void setDataPoints(DataPoint[] dataPoints) {
        training = dataPoints;
    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    static int getFitness(Individual individual) throws FileNotFoundException {
        //fitness
        int fitness = 0;
        //rules params
        int noOfRules = 10;
        //data1 = 6, data2 = 7.
        int ruleSize = FloatRule.getRuleSize();
        //data point float values
        float[] floatValues;
        //data point output bit
        int output;
        //No of float matches
        int floatMatch = 0;
        //Float ranges rules
        FloatRule[] rules = new FloatRule[noOfRules];
        int geneCount = 0;
        //A set of ranges
        float[][] floatRanges = new float[6][2];
        //one float range
        float[] range = new float[2];
        //load indivs into rule class
        for (int i = 0; i < noOfRules; i++) {
            //init rules
            rules[i] = new FloatRule();
            //load in condition
            for (int k = 0; k < 6; k++) {
                for (int l = 0; l < 2; l++) {
                    floatRanges[k][l] = individual.getGene(geneCount);
                    //System.out.print("fr: " + floatRanges[k][l]);
                    geneCount++;
                    //System.out.println("g: " + geneCount);
                }
                //System.out.println("");
            }
            rules[i].setFloatRanges(floatRanges);
            rules[i].setOutput(individual.getGene(geneCount));
            //System.out.println("ri: " + rules[i].getOutput() + " gc: " + 
            //        geneCount);
            geneCount++;
        }
        
        for (int i = 0; i < training.length; i++) {
            //get data from training array
            floatValues = training[i].getFloats();
            output = training[i].getOutput();
            for (int k = 0; k < rules.length; k++) {
                //check that training data is within the range and matches
                //the output of the rule
                floatMatch = 0;
                for (int l = 0; l < 6; l++) {
                    for (int m = 0; m < 2; m++) {
                       range[m] = rules[k].getFloat(l, m);
                    }
                    //swap floats around if needed
                    if (range[0] > range[1]) {
                        float temp = range[1];
                        range[1] = range[0];
                        range[0] = temp; 
                    }
                    //check range
                    if ((floatValues[l] > range[0]) && 
                            (floatValues[l] < range[1]))  {
                        floatMatch++;
                    }
                }
                if (floatMatch == 6) {
                    //check that the outputs match. Increment fitness if it does.
                    if (rules[k].getOutput() == (float) training[i].getOutput()) {
                        fitness++;
                    }
                    break;
                }
            }
        }
        return fitness;
    }
    
}
