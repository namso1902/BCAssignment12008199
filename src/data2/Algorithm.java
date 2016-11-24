/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data2;

import java.io.FileNotFoundException;

/**
 *
 * @author namso1902
 */
public class Algorithm {

    /* GA parameters */
    //Crossover:
    //private int crossPoint = 0;
    //0.05 (def) - keep between 0 and 1
    private static final double mutationRate = 0.05;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
    //no of rules
    
    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) throws 
            FileNotFoundException {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual?
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population space for offset
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new population with
        // crossover and tournament selection
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
        byte test_gene;
        //crossover crosspoint (rulesize * noOfRules)
        int crosspoint = 0;
        crosspoint = (int) ((int) Rule.ruleSize * 
                Math.round(Math.random() * 10));   
       //cut off individual 1
        for (int i = 0; i < crosspoint; i++) {
            newSol.setGene(i, indiv1.getGene(i));
        }
        //load in remaining genes from individual 2
        for (int i = crosspoint; i < indiv2.size(); i++) {
            newSol.setGene(i, indiv2.getGene(i));
        }        
        //return new individual
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            //mutate if probability is met.
            if (Math.random() <= mutationRate) {
                //get mutated gene
                byte get_gene = indiv.getGene(i);
                byte test_gene, set_gene = 0;
                //if get gene = # (2), set to 0 or 1.
                if (get_gene == 2) {
                    if (Math.random() >= 0.5) {
                        set_gene = 1;
                    } else {
                        set_gene = 0;
                    }
                }
                //if get gene = 1, set to # (2) or 0.
                else if (get_gene == 1) {
                    if (Math.random() >= 0.5) {
                        set_gene = 2;
                    } else {
                        set_gene = 0;
                    }
                }
                //if get gene = 0, set to # or 1.
                else if (get_gene == 0) {
                    test_gene = (byte) Math.round(Math.random() * 1);
                    if (Math.random() >= 0.5) {
                        set_gene = 1;
                    } else {
                        set_gene = 2;
                    }
                }
                //check for output bit (can only be 0 or 1)
                if (((i + 1) % Rule.getRuleSize() == 0) && 
                        (indiv.getGene(i) == 2)) {
                  if (Math.random() >= 0.5) {
                        set_gene = 1;
                    } else {
                        set_gene = 0;
                    }
                }
                //set new mutated bit.
                indiv.setGene(i, set_gene);
            }
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) throws 
            FileNotFoundException {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest individual from the tournament and return it.
        Individual fittest = tournament.getFittest();
        return fittest;
    }
    
}