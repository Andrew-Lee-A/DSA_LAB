/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week3;



/**
 *
 * @author gjs5758 Andrew Lee 17983766
 * @param <E>
 */
public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E>{
   
    
    @Override
    public boolean add(E o) {
        Node<E> newNode = new Node(o);
        if(this.contains(o)){
            return false;
        }else{
            if (firstNode == null || o.compareTo(firstNode.element) < 0){
            newNode.next = firstNode;
            firstNode = newNode;
            numElements++;
            return true;

            }else{
                Node<E> currentNode = firstNode;
                while(currentNode.next != null && o.compareTo(currentNode.element) > 0){
                    currentNode = currentNode.next;
                }
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                numElements++;
                return true;
            }
        }


    }
    
    public static void main(String[] args) {
        LinkedSortedSet lss = new LinkedSortedSet();
        lss.add(10);
        lss.add(1);
        lss.add(10);
        lss.add(1);
        System.out.println(lss);
        
        LinkedSortedSet<String> test = new LinkedSortedSet<>();
        test.add("Tyrell");
        test.add("Jambo");
        test.add("Gerard");
        test.add("Andrew");
        test.add("Andrew");
        System.out.println(test);
        System.out.println("Size currently: " + test.size());
        
        test.remove("Tyrell");
        System.out.println(test);
        System.out.println("Size currently: " + test.size());
        
        test.add("Andrew");
        test.add("Lynnx");
        System.out.println(test);
        System.out.println("Size currently: " + test.size());
    }
}
