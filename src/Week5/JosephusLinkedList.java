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

import java.util.LinkedList;

public class JosephusLinkedList {
        public static void printOrder(int numPeople, int gap) {
            int counter;
            LinkedList<Integer> list=new LinkedList<>();
            for (int count=1; count<=numPeople; count++){
                list.add(count);
            }

            counter = 1;
            
            while(!list.isEmpty()){
                if (counter < gap){
                    list.addLast(list.removeFirst());
                    counter++;
                }else{
                    System.out.print(list.removeFirst() + ", ");
                    counter = 1;
                }
            }
    }

}
