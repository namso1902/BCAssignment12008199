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
 * Purpose: One Ruleset
 */
public class Individual {

    //data1 = 60
    static int defaultGeneLength = 60;
    private byte[] genes = new byte[defaultGeneLength];
    //Fitness of indiv
    private int fitness = 0;
    boolean useWildcard = false;
    //public char hash = 2;

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            //set wilcard if random no = 2.
            byte gene = (byte) Math.round(Math.random() * 2);
            if (gene == 2) {
                //check if output bit (no hashes allowed)
                if (((i + 1) % 6) == 0) {
                    genes[i] = (byte) Math.round(Math.random() * 1);
                }
                else {
                    genes[i] = (byte) 2;
                }
            } else {
                genes[i] = gene;
            }
        }
    }

    /* Getters and setters */
    //Set gene length
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    //gene getter and setter
    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    //gene size
    public int size() {
        return genes.length;
    }

    //get fitness of individual
    public int getFitness() throws FileNotFoundException {
        
        FitnessCalc fc = new FitnessCalc();
        fitness = fc.getFitness(this);
        
        return fitness;
    }

    public byte[] getGenes() {
        return genes;
    }

    public void setGenes(byte[] genes) {
        this.genes = genes;
    }

    //Convert genes to a string
    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            //set has if gene = 2
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
