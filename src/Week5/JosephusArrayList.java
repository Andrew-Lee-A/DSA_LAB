/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week5;

/**
 *
 * @author gjs5758
 */

import java.util.ArrayList;
import java.util.List;
        
public class JosephusArrayList {

    public static void printOrder(int numPeople, int gap){
        List<Integer> list = new ArrayList<>();
        int counter;
        for (int count=1; count<=numPeople; count++){
            list.add(count);
        }
        counter = gap-1;
        System.out.println("The order is: ");
        while(!list.isEmpty()){
            System.out.print(list.remove(counter));
            numPeople--;
            if (numPeople>0){
                counter = (counter - 1 + gap ) % numPeople;
                System.out.print(", ");
            }
        }
        System.out.println("");
    }
    
}
