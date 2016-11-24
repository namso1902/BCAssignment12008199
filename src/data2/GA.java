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
public class GA {

    //Get data2 file location
    static final String data2_txt = "C:\\Users\\namso1902\\Documents\\NetBeansProjects"
            + "\\BCAssignment12008199\\src\\data2\\data2.txt";
    //data1_txt or data2_txt
    static File data_file = new File(data2_txt); 
    static Scanner scan; 
    
    public static void main(String[] args) throws FileNotFoundException {    
        //Set max fitness
        int maxFitness = 64;
        //Set datapoints
        int dp_count = 0;
        //intialise scanner and skip first line
        scan = new Scanner(data_file);
        scan.nextLine();
        int trainingNo = 64;
        DataPoint[] training = new DataPoint[64];
        //load in training data
        while (dp_count < trainingNo)  {
            training[dp_count] = new DataPoint();
            //store condition and output
            training[dp_count].setCondition(scan.next());
            training[dp_count].setOutput(scan.next());
            dp_count++;
        }      
        //close external file and set pass in training data array
        scan.close();
        FitnessCalc.setDataPoints(training);
        // Create an initial population
        Population myPop = new Population(50, true);
        //Headers
        System.out.println("Generation\t,Fittest \t,Mean");
        //Evolve our population until we reach an optimum solution or
        //reach a set number of generations
        int generationCount = 0;
        int fittestIndividual = 0;
        while (generationCount < 150) { 
            generationCount++;
            fittestIndividual = myPop.getFittest().
                    getFitness();
            //print generatino no, mean and count.
            System.out.println(generationCount + ",\t\t" + fittestIndividual + 
                    ",\t\t" + myPop.getMeanFitness());
            //evolve and return new population
            myPop = Algorithm.evolvePopulation(myPop);
        }
        //Display results
        System.out.println("Generation: " + generationCount);
        System.out.println("Best Genes " + "fitness: " + fittestIndividual);
        System.out.println(myPop.getFittest().toString());
        
    }

}
