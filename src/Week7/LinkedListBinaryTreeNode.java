/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week7;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Andrew Lee 17983766
 * @param <E>
 */
public class LinkedListBinaryTreeNode<E> implements MutableTreeNode {

    private E element;
    private MutableTreeNode root;
    private MutableTreeNode parent;
    private MutableTreeNode leftChild;
    private MutableTreeNode rightChild;
    
    public LinkedListBinaryTreeNode(){
        this(null);
    }
    
    public LinkedListBinaryTreeNode(E element){
        this.element = element;
        root = null;
        parent = null;
        leftChild = null;
        rightChild = null;
    }
    
    @Override
    public void insert(MutableTreeNode child, int index) throws IllegalArgumentException, IndexOutOfBoundsException{
        if(child == null)
            throw new IllegalArgumentException("Null child");
        if(index != 0 && index != 1)
            throw new IndexOutOfBoundsException();
        
        if(index == 0)
        {
            child.removeFromParent();
            leftChild = child;
            leftChild.setParent(this.parent);
        }
        else
        {
            child.removeFromParent();
            rightChild = child;
            rightChild.setParent(this.parent);
        }
    }

    @Override
    public void remove(int index) {
        if(index == 0)
        {
            //this.leftChild.removeFromParent();
            this.leftChild.setParent(null);
            this.leftChild = null;
        }    
        else if(index == 1)
        {
            //this.rightChild.removeFromParent();
            this.rightChild.setParent(null);
            this.rightChild = null;
        }
        else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public void remove(MutableTreeNode node) {
         if(node == leftChild)
            remove(0);
        if(node == rightChild)
            remove(1);
        
    }

    @Override
    public void setUserObject(Object object) {
        this.element = (E) object;
    }

    @Override
    public void removeFromParent() {
        if (this.parent!=null)
        {  
            parent.remove(this);
            this.parent = null;
        }
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        this.parent = newParent;
    }

    @Override
    public TreeNode getChildAt(int childIndex) throws IndexOutOfBoundsException{
        if(childIndex == 0)
        {
            return this.leftChild;
        }
        else if(childIndex == 1)
        {
            return this.rightChild;
        }
        else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public int getChildCount() {
        int count = 0;
        if(leftChild != null){
            count++;
        }
        if (rightChild != null){
            count++;
        }
        return count;
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getIndex(TreeNode node) throws NoSuchElementException{
        if(node == this.leftChild){
            return 0;
        }else if (node == this.rightChild){
            return 1;
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return (leftChild == null && rightChild == null);
    }

    @Override
    public Enumeration children() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {  
        String output = "" + this.element;
        if (!isLeaf())
        {  
            output += "[ ";
            output += ((leftChild==null)?"_":leftChild) + ":"+((rightChild==null)?"_":rightChild);   
            output += " ] ";
        }
        return output;
    }
    
    public static void main(String[] args) {

      MutableTreeNode root = new LinkedListBinaryTreeNode<String>("A");
      MutableTreeNode nodeB = new LinkedListBinaryTreeNode<String>("B");
      MutableTreeNode nodeC = new LinkedListBinaryTreeNode<String>("C");
      MutableTreeNode nodeD = new LinkedListBinaryTreeNode<String>("D");
      MutableTreeNode nodeE = new LinkedListBinaryTreeNode<String>("E");
      MutableTreeNode nodeF = new LinkedListBinaryTreeNode<String>("F");
      // build the tree
      nodeB.insert(nodeD, 0);
      nodeB.insert(nodeE, 1);
      nodeC.insert(nodeF, 0);
      root.insert(nodeB, 0);
      root.insert(nodeC, 1);
      System.out.println("Original Tree: " + root);
      root.remove(nodeC);
      nodeB.insert(nodeC, 1);
      System.out.println("Modified Tree: " + root);
        
        
    }
    
}
