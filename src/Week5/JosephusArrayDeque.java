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
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class JosephusArrayDeque {
    
    public static void printOrder(int numPeople, int gap) {
        int counter;
        Deque<Integer> dq = new ArrayDeque<Integer>();
        for (int count=1; count<=numPeople; count++){
            dq.add(count);
        }
        counter = 1;
        
        while(!dq.isEmpty()){
            if (counter < gap){
                dq.addLast(dq.removeFirst());
                counter ++;
            }
            else{
                System.out.print(dq.removeFirst() + ", ");
                counter = 1;
            }
        }
                
    }
}
