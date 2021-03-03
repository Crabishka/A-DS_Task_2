import java.util.Collection;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(5);
        list.addFirst(1);
        list.addFirst(4);
        for (Integer a : list){
            System.out.print(a + " ");
        }
        System.out.println();
        list.insertionSort();
        for (Integer a : list){
            System.out.print(a + " ");
        }


        // write your code here
    }


}
