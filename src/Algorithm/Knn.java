/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author AFRownak
 */
public class Knn {
    
    
     /**
 * Returns the majority value in an array of strings
 * majority value is the most frequent value (the mode)
 * handles multiple majority values (ties broken at random)
 *                    
 * @param  array an array of strings
 * @return  the most frequent string in the array
 */ 
    private static String findMajorityClass(String[] array)
    {
    //add the String array to a HashSet to get unique String values
        Set<String> h = new HashSet<String>(Arrays.asList(array));
        //convert the HashSet back to array
        String[] uniqueValues = h.toArray(new String[0]);
        //counts for unique strings
        int[] counts = new int[uniqueValues.length];
        // loop thru unique strings and count how many times they appear in origianl array   
        for (int i = 0; i < uniqueValues.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[j].equals(uniqueValues[i])){
                   counts[i]++;
                }
            }        
        }

        for (int i = 0; i < uniqueValues.length; i++)
           System.out.println(uniqueValues[i]);
        for (int i = 0; i < counts.length; i++)
           System.out.println(counts[i]);


        int max = counts[0];
        for (int counter = 1; counter < counts.length; counter++) {
            if (counts[counter] > max) {
                max = counts[counter];
            }
        }
        System.out.println("max # of occurences: "+max);

        // how many times max appears
        //we know that max will appear at least once in counts
        //so the value of freq will be 1 at minimum after this loop
        int freq = 0;
        for (int counter = 0; counter < counts.length; counter++) {
            if (counts[counter] == max) {
                freq++;
            }
        }

        //index of most freq value if we have only one mode
        int index = -1;
        if(freq==1){
           for (int counter = 0; counter < counts.length; counter++) {
             if (counts[counter] == max) {
                index = counter;
                break;
             }
           }
           //System.out.println("one majority class, index is: "+index);
           return uniqueValues[index];
        }  else{//we have multiple modes
           int[] ix = new int[freq];//array of indices of modes
           System.out.println("multiple majority classes: "+freq+" classes");
           int ixi = 0;
           for (int counter = 0; counter < counts.length; counter++) {
             if (counts[counter] == max) {
                ix[ixi] = counter;//save index of each max count value
                ixi++; // increase index of ix array
             }
           }

           for (int counter = 0; counter < ix.length; counter++)         
                System.out.println("class index: "+ix[counter]);       

           //now choose one at random
           Random generator = new Random();        
           //get random number 0 <= rIndex < size of ix
           int rIndex = generator.nextInt(ix.length);
           System.out.println("random index: "+rIndex);
           int nIndex = ix[rIndex];
           //return unique value at that index 
           return uniqueValues[nIndex];
        }

    }
    
}
