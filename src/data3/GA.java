/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author namso1902
 */
public class GA {

    //dataset 3 location
    static final String data3_txt = "C:\\Users\\namso1902\\Documents\\NetBeansProjects"
            + "\\BCAssignment12008199\\src\\data3\\data3.txt";
    //datset 3 file object
    static File data_file = new File(data3_txt); 
    static Scanner scan; 
    
    public static void main(String[] args) throws FileNotFoundException {    
        //Set max fitness
        int maxFitness = 2000;
        //Set datapoints
        int dp_count = 0;
        //intialise scanner pointer for dataset 3
        scan = new Scanner(data_file);
        scan.nextLine();
        int all_dpPoints = 2000;
        DataPoint[] training = new DataPoint[2000];
        //load in training data
        while (dp_count < all_dpPoints) {
            //read in floats
            training[dp_count] = new DataPoint();
            for (int i = 0; i < 6; i++) {
                training[dp_count].setFloat(i, scan.nextFloat());
                //System.out.print(training[dp_count].getFloat(i) + " ");
            }
            //read in output
            training[dp_count].setOutput(scan.nextInt());
            dp_count++;
        }
        //close external file and pass training data array
        scan.close();
        FitnessCalc.setDataPoints(training);
        //scan = new Scanner(data_file);
        // Create an initial population
        Population myPop = new Population(100, true);
        //Headers
        System.out.println("Generation\t,Fittest \t,Mean");
        // Evolve our population until we reach an optimum solution or
        //set number of generations
        int generationCount = 0;
        int fittestIndividual = 0;
        //Evolve GA for set number of generations
        while (generationCount < 200) { 
            generationCount++;
            fittestIndividual = myPop.getFittest().
                    getFitness();
            System.out.println(generationCount + ",\t\t" + fittestIndividual + 
                    ",\t\t" + myPop.getMeanFitness());
            myPop = Algorithm.evolvePopulation(myPop);
            //System.out.println(myPop.getFittest());
        }
        //Display best solution
        System.out.println("Generation: " + generationCount);
        System.out.println("Best Genes " + "fitness: " + fittestIndividual);
        System.out.println(myPop.getFittest().toString());
    }

}
