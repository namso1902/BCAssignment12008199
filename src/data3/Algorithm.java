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
public class Algorithm {

    /* GA parameters */
    private static final double mutationRate = 0.05;
    private static final int tournamentSize = 10;
    private static final boolean elitism = true;
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) throws FileNotFoundException {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population space
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        //crossover crosspoint
        int crosspoint = 0;
        crosspoint = (int) ((int) 13 * Math.round(Math.random() * 10));   
        for (int i = 0; i < crosspoint; i++) {
            newSol.setGene(i, indiv1.getGene(i));
        }
        for (int i = crosspoint; i < indiv2.size(); i++) {
            newSol.setGene(i, indiv2.getGene(i));
        }    
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                //if output bit, set to 0 or 1.
                if ((i + 1) % 13 == 0) {
                   if (indiv.getGene(i) == 0.0) {
                       indiv.setGene(i, (float) 1.0);
                   } else {
                       indiv.setGene(i, (float) 0.0);
                   }
                }
                else {
                    //get mutated gene
                    float add_sub_float =  (float) ((float) Math.random() * 0.1);
                    //set to minus or keep it positive
                    if (Math.random() < 0.5) {
                        add_sub_float = add_sub_float * -1;
                    } 
                    indiv.setGene(i, indiv.getGene(i) + add_sub_float);
                }
            }
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) throws FileNotFoundException {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
    
}