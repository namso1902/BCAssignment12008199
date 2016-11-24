/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data1;

import java.io.FileNotFoundException;

/**
 *
 * @author namso1902
 */
public class Population {

    //array of indivs
    Individual[] individuals;

    //Constructor
    // Create a population
    public Population(int populationSize, boolean initialise) throws FileNotFoundException {
        individuals = new Individual[populationSize];
        // Initialise population
        if (initialise) {
            //store rules
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    //Getter
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    //Get fittest individual of population
    public Individual getFittest() throws FileNotFoundException {
        Individual fittest = individuals[0];
        for (int i = 0; i < size(); i++) {
            //Save new individual if it is fitter and return it,
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    // Get population size
    public int size() {
        return individuals.length;
    }

    //Save a individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
    
    //get fitness of each individual
    public int[] getFitnesses() throws FileNotFoundException {
        int[] fitnesses = new int[size()];
        for (int i = 0; i < size(); i++) {
            fitnesses[i] = individuals[i].getFitness();
        }
        return fitnesses;
    }
    
    //Get the mean fitness at current generation
    public int getMeanFitness() throws FileNotFoundException {
        int sum = 0, mean = 0;
        sum = getTotalFitness();
        mean = sum / size();
        return mean;
    }
    
    //Get total fitness at current generation
    public int getTotalFitness() throws FileNotFoundException {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            sum = sum + getIndividual(i).getFitness();
        }
        return sum;
    }

    //To string
    @Override
    public String toString() {
        return "Population{" + "individuals=" + individuals + '}';
    }
}

