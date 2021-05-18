/*
*The selection sort algorithm works by first finding the smallest value in a 
*list and swapping the first value with it, then finding the second smallest 
*value and swapping the second value with it, continuing until all the values 
*are in order. Implement this algorithm, then determine its growth function,
*and hence the order of the algorith
 */
package Week1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Andrew Lee 17983766
 */
public class Exercises_1_2 {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList <Integer> intList = new ArrayList<>();
        
        System.out.println("Enter the integers seperated by a space: ");
        String[] strNums = null;
        if (s.hasNextLine()){
            strNums = s.nextLine().split(" ");
        }
        if (strNums!= null){
            for (String strNumber: strNums){
                try{
                    intList.add(Integer.parseInt(strNumber.trim()));
                } catch (Exception e){
                    System.out.println("Invalid input");
                    break;
                }
            }
        }
        System.out.println("This is what the user has input");
        System.out.println(Arrays.toString(strNums));
        System.out.println("\nThis is the list to be sorted");
        System.out.println(intList.toString());
        
        //Algorithm has a growth function of 0.5n(n-1)
        //which is a Big O of O(n^2)
        
        int tmpVal = 0;
        int minVal = 0;
        
        for (int i = 0; i<intList.size(); i++){
            int minIndex = i;
            
            //Search for smallest value and retrieve it's index
            for (int j = i+1; j < intList.size(); j++){
                if (intList.get(j) < intList.get(minIndex)){
                    minIndex = j;
                }
            }
            
            //swap the values into the index
            tmpVal = intList.get(i);
            minVal = intList.get(minIndex);
            intList.set(i, minVal);
            intList.set(minIndex, tmpVal);
        }
        
        //print out the sorted List
        System.out.println(intList.toString());
    }
}
