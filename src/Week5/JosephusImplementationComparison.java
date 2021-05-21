/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lee5
 */
public class JosephusImplementationComparison {
    public static void main(String[] args) {
        long start, end, elapsed;
        int numPeople, gap;
        
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of soldiers: ");
        numPeople = in.nextInt();
        in.nextLine();
        System.out.println("Enter the gap between soldiers: ");
        gap = in.nextInt();
        
        System.out.println("==ArrayList implementation==");
        start = System.nanoTime();
        JosephusArrayList.printOrder(numPeople, gap);
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("Array List - Time: " + elapsed/1000 + "micro seconds");
        
        System.out.println("\n==Queue implementation==");
        start = System.nanoTime();
        JosephusArrayDeque.printOrder(numPeople, gap);
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("\nArrayDeque - Time: " + elapsed/1000 + "micro seconds");
        
        
        System.out.println("\n==Queue implementation==");
        start = System.nanoTime();
        JosephusLinkedList.printOrder(numPeople, gap);
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("\nLinkedList - Time: " + elapsed/1000 + "micro seconds");
        
        System.out.println("We can see that queue implementation is alot faster");
    }
}
