/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author namso1902
 */
public class GA {
    
    //data file 1 location
    static String data1_txt = "C:\\Users\\namso1902\\Documents\\NetBeansProjects"
            + "\\BCAssignment12008199\\src\\data1\\data1.txt";
    //Point new scanner to data file 1
    static File data_file = new File(data1_txt); 
    static Scanner scan;

    public static void main(String[] args) throws FileNotFoundException {      
        // Create an initial population
        Population myPop = new Population(100, true);
        int maxFitness = 32;
        int dp_count = 0;
        //Headers
        System.out.println("Generation,\t,Fittest \t,Mean");
        //create new scanner and set to first data point.
        scan = new Scanner(data_file);
        scan.nextLine();
        DataPoint[] training = new DataPoint[32];
        //store all 32 data points in array
        while (dp_count < 32)  {
            training[dp_count] = new DataPoint();
            //store condition and output
            training[dp_count].setCondition(scan.next());
            training[dp_count].setOutput(scan.next());
            dp_count++;
        }
        //close scanner and pass datapoints to fitnessCalc
        scan.close();
        FitnessCalc.setDataPoints(training);
        //Evolve our population until we reach an optimum solution
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
        //display results
        System.out.println("Generation: " + generationCount);
        System.out.println("Best Genes:");
        System.out.println(myPop.getFittest().toString());
    }
}
