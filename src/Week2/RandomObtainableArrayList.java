/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week2;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Lee5
 */
public class RandomObtainableArrayList<E> extends ArrayList implements RandomObtainable{
    ArrayList<E> arrayList;
    
    public RandomObtainableArrayList(){
        arrayList = new ArrayList<>();
    }

    @Override
    public Object getRandom() throws NoSuchElementException {
        if (this.isEmpty()){
            throw new NoSuchElementException("The array is empty! Cannot get a random element"); 
        }
        return this.get(new Random().nextInt(this.size()));
    }

    @Override
    public boolean removeRandom() throws UnsupportedOperationException {
        if(this.isEmpty()){
            throw new UnsupportedOperationException("The array is empty! Cannot remove a random element"); 
        }
        this.remove(new Random().nextInt(this.size()));
        return true;
    }
    
    public static void main(String[] args) {
        RandomObtainableArrayList b = new RandomObtainableArrayList();
        try{
            //b.getRandom();
            //b.removeRandom();
            b.add(1);
            b.add(2);
            System.out.println(b);
            System.out.println("Getting random: " + b.getRandom());
            System.out.println("Remove random: " + b.removeRandom());
            System.out.println(b);
        }catch(NoSuchElementException | UnsupportedOperationException e ){
            System.out.println(e);
        }
    }
    
}
