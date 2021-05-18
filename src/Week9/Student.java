package Week9;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gjs5758
 */
public class Student {
    String name;
    private int age;
//    public final double DEFULT_LOAD_FACTOR = 75.0;
//    protected double load_factor;
//    protected LinkedList<Student>[] hashTable;
    
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int hashCode(){
        int code = 0;
        char[] charArray = name.toCharArray();
        for (char c : charArray){
            code += c;
        }
        return code;
        //return name.hashCode();    
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Student)){
            return false;
        }
        if (this == o){
            return true;
        }
        
        Student s = (Student) o;
        return (this.name.compareTo(s.name)==0) && (this.age == s.age);
    }
//    public int add(Student s){
//        if(s == null){
//            return 0;
//        }else{
//            if (this.load_factor >= 75.0){
//                this.resize();
//            }
//            hashTable[this.hashCode(s.name)].add(s);
//            return 1;
//        }
//    }
//    
//    public void resize(){
//        // set up new hash table
//        int new_table_size = (int) (this.hashTable.length * 1.5);
//        LinkedList<Student>[] new_hashTable = new LinkedList[new_table_size];
//        
//        
//    }
}
