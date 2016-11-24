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
 * Purpose: One Ruleset
 */
public class Individual {

    //data3 = 130.
    static int defaultGeneLength = 130;
    private float[] genes = new float[defaultGeneLength];
    private int fitness = 0;
    //public char hash = 2;

    // Create a random individual
    public void generateIndividual() {
        float gene;
        for (int i = 0; i < size(); i++) {
            //set output bit to 0 or 1
            if ((i + 1) % 13 == 0) {
                gene = (float) Math.round(Math.random() * 1);
            } 
            else {
                //random 8.dp float between 0 and 1.
                gene = (float) Math.random();
            }
            genes[i] = gene;
        }
    }

    /* Getters and setters */
    //Set gene length
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public float getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, float value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() throws FileNotFoundException {
        
        FitnessCalc fc = new FitnessCalc();
        fitness = FitnessCalc.getFitness(this);
        
        return fitness;
    }

    public float[] getGenes() {
        return genes;
    }

    public void setGenes(float[] genes) {
        this.genes = genes;
    }

    //IMPORTANT!!!
    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            if (getGene(i) == 2) {
                geneString += "#";
            }
            else {
                geneString += getGene(i);
            }
        }
        
        return geneString;
    }
}
