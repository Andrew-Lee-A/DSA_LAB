package Week5;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * A class which demonstrates binary search of an array
 *
 * @author Andrew Ensor
 */
public class BinarySearchListIterator<E extends Comparable> {

    private ListIterator<E> it;
    private List<E> elements;

    public BinarySearchListIterator(List<E> elements) {
        this.elements = elements;
    }

    public int search(E target) {
        if (target == null) {
            throw new NullPointerException("search target is null");
        }
        return search(target, 0, elements.size());
    }

    // recursive method which searches through the elements array
    // between start index (inclusive) and end index (exclusive) for the
    // index of specified target, or returns -(insertion)-1 if not found
    private int search(E target, int start, int end) {
        if (start >= end) {
            return -start - 1; // negative value
        } else {
            int midpoint = (start + end) / 2; // midpoint of search region
            it = elements.listIterator(midpoint);
            E item = it.next();
            item = it.previous();
            int comparison = target.compareTo(item);
            if (comparison == 0) {
                return midpoint;
            } else if (comparison < 0) {
                return search(target, start, midpoint);
            } else { // comparison > 0

                return search(target, midpoint + 1, end);
            }
        }
    }

    public static void main(String[] args) {
        String[] list = {"ant", "bat", "cat", "cow", "dog", "eel",
            "fly", "fox", "owl", "pig", "rat"};
        List<String> slist = Arrays.asList(list);
        BinarySearchListIterator<String> bin = new BinarySearchListIterator<String>(slist);
        String target = "dog";
        int index = bin.search(target);
        if (index >= 0) {
            System.out.println(target + " found at index " + index);
        } else {
            System.out.println(target + " not at index " + (-index - 1));
        }
    }
}
