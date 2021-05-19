/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week3;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author gjs5758
 */
public class ArraySetApp {
    
    public static ArraySet addTest(ArraySet as, int n){
        long start =  System.nanoTime();
        for (int i = 1; i <= n ; i++){
            as.add(i);
        }
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println("Adds - " + as.size() + " Time taken : " + TimeUnit.NANOSECONDS.toMicros(elapsedTime) + "micro s");
        return as;
    }
    
    public static ArraySet containsTest(ArraySet as ){
        long start =  System.nanoTime();
        int n = as.size();
        as.contains(n/2);
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println("Contains - " + n/2 + " Time taken : " + TimeUnit.NANOSECONDS.toMicros(elapsedTime) + "micro s");
        return as;
    }
    
    public static ArraySet removesTest(ArraySet as ){
        long start =  System.nanoTime();
        int n = as.size();
        as.remove(n/2);
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println("Removes - : " + n/2 + " Time taken : " + TimeUnit.NANOSECONDS.toMicros(elapsedTime) + "micro s");
        return as;
    }
    
    
    public static void main(String[] args) {
        System.out.println("==Small value test==");
        ArraySet as = new ArraySet();
        ArraySetApp.addTest(as, 5);
        ArraySetApp.containsTest(as);
        ArraySetApp.removesTest(as);
        
        System.out.println("\n==Medium value test==");
        as.clear();
        ArraySetApp.addTest(as, 100000);
        ArraySetApp.containsTest(as);
        ArraySetApp.removesTest(as);
        
        System.out.println("\n==Large value test==");
        as.clear();
        ArraySetApp.addTest(as, Integer.MAX_VALUE/10000);
        ArraySetApp.containsTest(as);
        ArraySetApp.removesTest(as);
        
    }
}
