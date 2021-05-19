/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Lee5
 */
public class CircularArrayQueue<E>{
    private final int INITIAL_CAPACITY = 5;
    protected int numElements;
    protected E[] array;
    protected int front;
    protected int rear;
    
    public CircularArrayQueue(){
        numElements = 0;
        array = (E[]) new Object[this.INITIAL_CAPACITY];
        front = -1;
        rear = -1;
    }


   
    public E dequeue() {
        E frontElement;
        if (this.isEmpty()){
            throw new NoSuchElementException("Array is empty! No element to remove!");
        }
        frontElement = array[this.front];
        array[this.front] = null;
        //change front pointer
        if (this.front == this.rear){ //if there is one element
            this.front = -1;
            this.rear = -1;
        }else{
            this.front = (this.front - 1) % this.array.length;
        }
        this.numElements--;
        return frontElement;
    }

    
    public E first() {
        if(this.isEmpty()){
            throw new NoSuchElementException("No elements in array!");
        }
        return this.array[this.front];
    }

    
    public E last() {
        if(this.isEmpty()){
            throw new NoSuchElementException("No elements in array!");
        }
        
        return this.array[this.rear];
    }

    
    public void enqueue(E element) throws NullPointerException{
        if (element == null){
            throw new NullPointerException("Cannot enqueue null element");
        }
        if (this.isEmpty()){ // when empty, rear and front = -1
            this.front = (this.front+1)%this.array.length;
            this.rear = this.front;
            this.array[this.front] = element;
        }else if(this.isFull()){
            this.expandCapacity();
            this.front = (this.front+1) % this.array.length;
            this.array[this.front] = element;
        }else{
//            if(this.front == this.array.length-1){ //front pointer needs to move to the last index
//                this.front = 0;
//                this.array[this.front] = element;
//            }else{ 
//                this.front = (this.front+1) % this.array.length;
//                this.array[this.front] = element;
//            }
            this.front = (this.front+1) % this.array.length;
            this.array[this.front] = element;
        }
        this.numElements++;
    }

    
    public boolean isEmpty() {
        if (numElements == 0){
            return true;
        }
        return false;
    }
    
    public boolean isFull(){
        if (numElements == this.array.length){
            return true;
        }
        return false;
    }
    
    public int size() {
        return this.numElements;
    }
    
    
    public Iterator<E> iterator() {
        return new ArrayIterator(this.array, numElements);
    }
    
    private void expandCapacity()
    {  
        E[] largerArray =(E[])(new Object[this.array.length*2]);                                             //Help keep track with index in largerArray for IF frontElement != 0
        int elementIndexToCopy = this.front;
        
        //Copying from frontElement onwards
        for (int i = 0; i < this.array.length; i++)
        {
            largerArray[i] = this.array[elementIndexToCopy];
            if (elementIndexToCopy == 0){
                elementIndexToCopy = this.array.length-1;
            }else{
                elementIndexToCopy--;
            }
        }
          
        this.front = 0;
        this.rear = this.numElements - 1;
        this.array = largerArray;
    }
    @Override
    public String toString(){
        String s = "[";
        for (int i = 0; i < this.array.length; i++){
            s+= array[i];
            if (i < this.array.length -1 ){
                s+=",";
            }
        }
        s += "]";
        return s;
    }
    
    public void clear() throws NoSuchElementException
    {
        if(this.numElements > 0)
        {
            array = (E[])(new Object[this.array.length]);
            this.front = -1;
            this.rear = -1;
            this.numElements = 0;
        }
        else
            throw new NoSuchElementException("No elements in array to clear!");
    }

    public class ArrayIterator implements Iterator<E> {
        
        private int IteratorNumElements;
        private E[] IteratorArray;
        private int nextElement;
        
        public ArrayIterator(E[] array, int numElements) {
            if(numElements>array.length){
                numElements = array.length;
            }
           
            this.IteratorArray = array;
            this.IteratorNumElements = numElements;
            this.nextElement = front;
        }

        @Override
        public boolean hasNext() {
            if (nextElement < 0){
                nextElement = IteratorArray.length-1;
            }
            return IteratorArray[nextElement] != null;
        }

        @Override
        public E next() {
            if (nextElement < 0){
                nextElement = IteratorArray.length-1;
            }
            return IteratorArray[nextElement--];
        }
    }
    
    public static void main(String[] args) {
        CircularArrayQueue c = new CircularArrayQueue();
        System.out.println("==Basic testing==");
        c.enqueue(1);
        System.out.println(c);
        c.enqueue(2);
        System.out.println(c);
        c.dequeue();
        System.out.println(c);
        c.dequeue();
        System.out.println(c);
        
        System.out.println("\n==Expand Capacity Test==");
        for (int i = 0; i < 5; i++){
            c.enqueue(i);
        }
        System.out.println(c + " System is now full");
        System.out.println("Enqueue: 5");
        c.enqueue(5);
        System.out.println(c);
    }
}
