package Week6;

/**
   A class which includes common algorithms for sorting arrays
   of Comparable objects in place.Note the elements of the array are presumed to be non-null
 * @param <E>
*/

public class ArraySorter<E extends Comparable>
{
   public int swap_counter = 0;
   public int compare_counter = 0;
   
    public void selectionSort(E[] list) {
        this.swap_counter = 0;
        this.compare_counter = 0;
        //E[] tmpList = (E[]) new Object[list.length];
        //System.arraycopy(list, 0, tmpList, 0, list.length);
        int indexMin; // index of least element
        E temp; // temporary reference to an element for swapping
        for (int i = 0; i < list.length - 1; i++) {  // find the least element that has index>=i
            indexMin = i;
            for (int j = i + 1; j < list.length; j++) {
                this.compare_counter+=1;
                if (list[j].compareTo(list[indexMin]) < 0) {
                    indexMin = j;
                }
            }
            // swap the element at indexMin with the element at i
            temp = list[indexMin];
            list[indexMin] = list[i];
            list[i] = temp;
            this.swap_counter+=1;
        }
    }

    public void insertionSort(E[] list) {
        swap_counter = 0;
        compare_counter = 0;
        E elementInsert;
        for (int i = 1; i < list.length; i++) {  // get the element at index i to insert at some index<=i
            elementInsert = list[i];
            // find index where to insert element to maintain 0..i sorted
            int indexInsert = i;
            while (indexInsert > 0
                    && list[indexInsert - 1].compareTo(elementInsert) > 0) {  // shift element at insertIndex-1 along one to make space
                compare_counter+=1;
                list[indexInsert] = list[indexInsert - 1];
                indexInsert--;
            }
            // insert the element
            list[indexInsert] = elementInsert;
            swap_counter+=1;
            compare_counter+=1;
        }
    }

    public void bubbleSort(E[] list) {
        swap_counter = 0;
        compare_counter = 0;
        E temp; // temporary reference to an element for swapping
        for (int i = list.length - 1; i >= 0; i--) {  // pass through indices 0..i and bubble (swap) adjacent
            // elements if out of order
            for (int j = 0; j < i; j++) {
                compare_counter+=1;
                if (list[j].compareTo(list[j + 1]) > 0) {  // swap the elements at indices j and j+1
                    temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                    swap_counter+=1;
                }
            }
        }
    }

    public void quickSort(E[] list) {
        this.swap_counter = 0;
        this.compare_counter = 0;
        quickSortSegment(list, 0, list.length);
    }

    // recursive method which applies quick sort to the portion
    // of the array between start (inclusive) and end (exclusive)
    private void quickSortSegment(E[] list, int start, int end) {
        if (end - start > 1) // then more than one element to sort
        {  // partition the segment into two segments
            int indexPartition = partition(list, start, end);
            // sort the segment to the left of the partition element
            quickSortSegment(list, start, indexPartition);
            // sort the segment to the right of the partition element
            quickSortSegment(list, indexPartition + 1, end);
        }
    }

    // use the index start to partition the segment of the list
    // with the element at start as the partition element
    // separating the list segment into two parts, one less than
    // the partition, the other greater than the partition
    // returns the index where the partition element ends up
    private int partition(E[] list, int start, int end) {
        E temp; // temporary reference to an element for swapping
        E partitionElement = list[start];
        int leftIndex = start; // start at the left end
        int rightIndex = end - 1; // start at the right end
        // swap elements so elements at left part are less than
        // partition element and at right part are greater
        while (leftIndex < rightIndex) {  // find element starting from left greater than partition
            while (list[leftIndex].compareTo(partitionElement) <= 0
                    && leftIndex < rightIndex) {
                this.compare_counter+=1;
                leftIndex++; // this index is on correct side of partition
            }         // find element starting from right less than partition
            this.compare_counter+=1;
            while (list[rightIndex].compareTo(partitionElement) > 0) {
                this.compare_counter+=1;
                rightIndex--; // this index is on correct side of partition
            }
            this.compare_counter+=1;
            if (leftIndex < rightIndex) {  // swap these two elements
                temp = list[leftIndex];
                list[leftIndex] = list[rightIndex];
                list[rightIndex] = temp;
                this.swap_counter+=1;
            }
        }
        // put the partition element between the two parts at rightIndex
        list[start] = list[rightIndex];
        list[rightIndex] = partitionElement;
        return rightIndex;
    }

    public void mergeSort(E[] list) {
        this.swap_counter= 0;
        this.compare_counter = 0;
        mergeSortSegment(list, 0, list.length);
    }

    // recursive method which applies merge sort to the portion
    // of the array between start (inclusive) and end (exclusive)
    private void mergeSortSegment(E[] list, int start, int end) {
        int numElements = end - start;
        if (numElements > 1) {
            int middle = (start + end) / 2;
            // sort the part to the left of middle
            mergeSortSegment(list, start, middle);
            // sort the part to the right of middle
            mergeSortSegment(list, middle, end);
            // copy the two parts elements into a temporary array
            E[] tempList = (E[]) (new Comparable[numElements]); //unchecked
            for (int i = 0; i < numElements; i++) {
                tempList[i] = list[start + i];
            }
            // merge the two sorted parts from tempList back into list
            int indexLeft = 0; // current index of left part
            int indexRight = middle - start; // current index of right part
            for (int i = 0; i < numElements; i++) {  // determine which element to next put in list
                if (indexLeft < (middle - start))//left part still has elements
                {
                    if (indexRight < (end - start))// right part also has elem
                    {
                        this.compare_counter+=1;
                        if (tempList[indexLeft].compareTo(tempList[indexRight]) < 0) // left element smaller 
                        {
                            this.swap_counter+=1;
                            list[start + i] = tempList[indexLeft++];
                        } else // right element smaller
                        {
                            this.swap_counter+=1;
                            list[start + i] = tempList[indexRight++];
                        }
                    } else // take element from left part
                    {
                        this.swap_counter+=1;
                        list[start + i] = tempList[indexLeft++];
                    }
                } else // take element from right part
                {
                    this.swap_counter+=1;
                    list[start + i] = tempList[indexRight++];
                }
            }
        }
    }
    
    public String[] setupList(){
        String[] s = {"cow", "fly", "dog", "bat", "fox", "cat", "eel",
            "ant", "beetle", "hyena", "elephant", "zebra"};
        return s;
    }
    
    public String[] setupAscList(){
        String[] s = {"ant", "bat", "beetle", "cat", "cow", "dog", "eel", "elephant", "fly", "fox", "hyena", "zebra"};
        return s;
    }
    
    public String[] setupDscList(){
        String[] s = {"zebra", "hyena" , "fox", "fly", "elephant", "eel", "dog", "cow", "cat","beetle", "bat", "ant"};
        return s;
    }
    
    public void print(String[] list, ArraySorter sorter){
        System.out.print("[");
        for (int i = 0; i < list.length; i++) {
            System.out.print(((i > 0) ? ", " : "") + list[i]);
        }
        System.out.print("]");
        System.out.println(" Swaps: " + sorter.swap_counter + " Comparisons: " + sorter.compare_counter);

    }
    
    
//    public E[] setupDescendingList(int n){
//        int[] list = new int[n];
//        for (int i = n; i >= 1; i--){
//            list[i] = i;
//        }
//        return (E[]);
//    }

    // driver main method to test one of the algorithms
    public static void main(String[] args) {
        ArraySorter<String> sorter = new ArraySorter<>();
        String[] list = sorter.setupList();
        
        
        System.out.println("== Randomized list ==");
        System.out.print("Selection : ");
        long startTime = System.nanoTime();
        sorter.selectionSort(list);
        long endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Insertion : ");
        list = sorter.setupList();
        startTime = System.nanoTime();
        sorter.insertionSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Bubble : ");
        list = sorter.setupList();
        startTime = System.nanoTime();
        sorter.bubbleSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Quick : ");
        list = sorter.setupList();
        startTime = System.nanoTime();
        sorter.quickSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime) / 1000.0 + "micro seconds\n");
        System.out.println("");
        
        System.out.print("Merge : ");
        list = sorter.setupList();
        startTime = System.nanoTime();
        sorter.mergeSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime) / 1000.0 + "micro seconds\n");
        System.out.println("");
        
        System.out.println("== Ascending list ==");
        System.out.print("Selection : ");
        list = sorter.setupAscList();
        startTime = System.nanoTime();
        sorter.selectionSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Insertion : ");
        list = sorter.setupAscList();
        startTime = System.nanoTime();
        sorter.insertionSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Bubble : ");
        list = sorter.setupAscList();
        startTime = System.nanoTime();
        sorter.bubbleSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Quick : ");
        list = sorter.setupAscList();
        startTime = System.nanoTime();
        sorter.quickSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Merge : ");
        list = sorter.setupAscList();
        startTime = System.nanoTime();
        sorter.mergeSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.println("== Descending list ==");
        list = sorter.setupDscList();
        System.out.print("Selection : ");
        startTime = System.nanoTime();
        sorter.selectionSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Insertion : ");
        list = sorter.setupDscList();
        startTime = System.nanoTime();
        sorter.insertionSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Bubble : ");
        list = sorter.setupDscList();
        startTime = System.nanoTime();
        sorter.bubbleSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Quick : ");
        list = sorter.setupDscList();
        startTime = System.nanoTime();
        sorter.quickSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        System.out.print("Merge : ");
        list = sorter.setupDscList();
        startTime = System.nanoTime();
        sorter.mergeSort(list);
        endTime = System.nanoTime();
        sorter.print(list, sorter);
        sorter.print(list, sorter);System.out.print("Time taken: " + (endTime - startTime)/1000.0+ "micro seconds\n");
        System.out.println("");
        
        
    }



}
