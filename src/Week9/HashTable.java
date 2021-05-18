/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week9;

/**
 *
 * @author gjs5758
 */
public class HashTable<E> {
    private int numElements;
    private final int DEFAULT_CAPACITY = 7;
    private Node<E>[] hashTable;
    public final double DEFAULT_LOAD_FACTOR = 0.75;
    //protected double load_factor;   
    
    public HashTable(){
        this.hashTable =  new Node[DEFAULT_CAPACITY];
        numElements = 0;
    }
    
    public boolean contains(Student s){
        int i = Math.abs(s.hashCode()) % hashTable.length;
        Node newNode = new Node(s);
        Node tmp = hashTable[i];
        if (hashTable[i] == null){
            return false;
        }else{
            while(tmp!=null){
                if (tmp.element.equals(newNode.element)){
                    return true;
                }
                tmp = tmp.next;
            }
            return false;
        }
    }
    public void add(Student s){
        Node newNode = new Node(s);
        if(!this.contains(s)){
            if ((this.calc_load_factor()) >= DEFAULT_LOAD_FACTOR){
                System.out.println("Resizing hashtable...");
                this.resize();
            }
            int i = Math.abs(s.hashCode()) % hashTable.length;
            newNode.next = hashTable[i];
            hashTable[i] = newNode;
            numElements++;
    }
}

    public void resize(){
        // set up new hash table
        int new_table_size = (int) (this.hashTable.length * 1.5);
        Node<E>[] newHashTable = new Node[new_table_size];
        Node<E> curr;
        Node<E> tmp;
        int j;
        
        //insert old nodes into new table
        for (int i=0 ; i <numElements+1; i++){
            curr = hashTable[i];
            while(curr != null){
                j = Math.abs(curr.element.hashCode()) % newHashTable.length;
                tmp = new Node(curr.element);
                System.out.println("Adding: " + tmp.element + " at " + j);
                if(newHashTable[j] !=null){
                    tmp.next = newHashTable[j];
                    newHashTable[j] = tmp;
                    curr = curr.next;
                }else{
                    newHashTable[j] = tmp;
                    curr = curr.next;
                }
                
            }
            
        }
        this.hashTable = newHashTable;
        
    }    
    
    public double calc_load_factor(){
        double load_factor = (double)numElements / (double)hashTable.length;
        System.out.println(load_factor);
        return load_factor;
    }
    
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < this.hashTable.length; i++){
            s+= "At " + i + ": ";
            if (hashTable[i]!=null){
                Node<E> n = hashTable[i];
                while(n!=null){
                    s+=n.element + " ";
                    n = n.next;
                }
            }
            s+="\n";
        }
        return s;
    }
    private class Node<E>{
        public E element;
        public Node<E> next;
        
        public Node(E element){
            this.next = null;
            this.element = element;
        }
    }
    
    public static void main(String[] args) {
        HashTable hs = new HashTable();
        hs.add(new Student("Andrew", 21));
        System.out.println(hs);
        hs.add(new Student("Rhys", 21));
        System.out.println(hs);
        hs.add(new Student("Andrew", 21));
        System.out.println(hs);
        hs.add(new Student("Bndrdw", 21));
        System.out.println(hs);
        hs.add(new Student("Jambo", 21));
        System.out.println(hs);
        hs.add(new Student("Gerard", 21));
        System.out.println(hs);
        hs.add(new Student("Tyrell", 21));
        System.out.println(hs);
        hs.add(new Student("Ethan", 21));
        System.out.println(hs);
    }
}
