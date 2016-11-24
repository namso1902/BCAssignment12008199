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
 * Purpose: One Ruleset
 */
public class Individual {

    //Set gene length
    static int defaultGeneLength = 70;
    private byte[] genes = new byte[defaultGeneLength];
    // Cache
    private int fitness = 0;

    // Create a random individual
    public void generateIndividual() {
        byte gene;
        for (int i = 0; i < size(); i++) {
            //make sure output bit is only set to 0 or 1.
            if ((i + 1) % 7 == 0) {
                gene = (byte) Math.round(Math.random() * 1);
            } 
            //set gene to 0, 1 or 2 (#)
            else {
                gene = (byte) Math.round(Math.random() * 2);
            }
            //set gene in individual
            genes[i] = gene;
        }
    }

    /* Getters and setters */
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    //Gene size (length)
    public int size() {
        return genes.length;
    }

    public int getFitness() throws FileNotFoundException {
        
        FitnessCalc fc = new FitnessCalc();
        fitness = FitnessCalc.getFitness(this);
        
        return fitness;
    }

    public byte[] getGenes() {
        return genes;
    }

    public void setGenes(byte[] genes) {
        this.genes = genes;
    }

    //return individual as string
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
