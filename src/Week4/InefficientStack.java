/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week4;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author Lee5
 */
public class InefficientStack<E> implements StackADT<E>{
    private final int INITIAL_CAPACITY = 20;
   protected int numElements;
   protected E[] elements;
   
   // default constructor that creates a new stack 
   // that is initially empty
   public InefficientStack()
   {  numElements = 0;
      elements = (E[])(new Object[INITIAL_CAPACITY]); // unchecked
   }
   
   // constructor for creating a new stack which
   // initially holds the elements in the collection c
   public InefficientStack(Collection<? extends E> c)
   {  this();
      for (E element : c)
         push(element);
   }
   
   // Adds one element to the top of this stack
   public void push(E element)
   {  
    if (numElements >= elements.length){
         expandCapacity();
    }
         
    if ((numElements+1)>elements.length){
        expandCapacity();
    }  
    for (int i = numElements-1; i >= 0; i--){
        elements[i+1] = elements[i];
    }
    elements[0] = element;
    numElements++;
    
      
   }
   
   // removes and returns the top element from this stack
   public E pop() throws NoSuchElementException
   {  if (numElements > 0)
      {  
        E topElement = elements[0];
        for (int i = 1; i <= numElements; i++){
            elements[i] = elements[i+1];
        }
        numElements--;
        return topElement;
      }
      else
         throw new NoSuchElementException();
   }
   
   // returns without removing the top element of this stack
   public E peek() throws NoSuchElementException
   {  if (numElements > 0)
         return elements[numElements-1];
      else
         throw new NoSuchElementException();
   }
   
   // returns true if this stack contains no elements
   public boolean isEmpty()
   {  return (numElements==0);
   }
   
   // returns the number of elements in this stack
   public int size()
   {  return numElements;
   }
   
   // returns a string representation of the stack from top to bottom
    @Override
   public String toString()
   {  String output = "[";
      for (int i=numElements-1; i>=0; i--)
      {  output += elements[i];
         if (i>0)
            output += ",";
      }
      output += "]";
      return output;
   }
      
   // helper method which doubles the current size of the array
   private void expandCapacity()
   {  E[] largerArray =(E[])(new Object[elements.length*2]);//unchecked
      // copy the elements array to the largerArray
      for (int i=0; i<numElements; i++)
         largerArray[i] = elements[i];
      elements = largerArray;
   }
   
   public static void main(String[] args) {
        
        long start, end, elapsed;
        InefficientStack s1 = new InefficientStack();
        ArrayStack s2 = new ArrayStack();
        int n = 100;
        
        System.out.println("==PUSHING==");
        start = System.nanoTime();
        for (int i = 0; i < n; i++){
            s1.push(i);
        }
        end = System.nanoTime();
        elapsed = end-start;
        System.out.println("Inefficient Array Stack - Time: " + elapsed + "ns");
        long pushs1 = elapsed;
        
        start = System.nanoTime();
        for (int i = 0; i < n; i++){
            s2.push(i);
        }
        end = System.nanoTime();
        elapsed = end-start;
        System.out.println("Array Stack - Time: " + elapsed + "ns");
        System.out.println("Array Stack is " + ((double)pushs1/(double)elapsed)*100.0 + "% faster");
        System.out.println("");
        System.out.println("==POPPING==");
        start = System.nanoTime();
        for (int i = 0; i < n; i++){
            s1.pop();
        }
        end = System.nanoTime();
        elapsed = end-start;
        System.out.println("Inefficient Array Stack - Time: " + elapsed + "ns");
        pushs1 = elapsed;
        start = System.nanoTime();
        for (int i = 0; i < n; i++){
            s2.pop();
        }
        end = System.nanoTime();
        elapsed = end-start;
        System.out.println("Array Stack - Time: " + elapsed + "ns");
        System.out.println("Array Stack is " + ((double)pushs1/(double)elapsed)*100.0 + "% faster");
    }
   
}
