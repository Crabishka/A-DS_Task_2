import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileWriter out = new FileWriter(new File("output.txt"), false);
        FileReader fr = new FileReader(new File("input.txt"));
        BufferedReader in = new BufferedReader(fr); // пусть будет так
        String str = in.readLine();
        long time;
        while (str != null) {

            out.write(str);
            String[] words = str.split(" ");


            ArrayList<Integer> arrayList = stringArrayToIntArrayList(words);
            time = System.nanoTime();
            Collections.sort(arrayList);
            time = System.nanoTime() - time;
            out.write("\n" + "Сортировка динамического массива стандартным методом   - " + time + " наносекунд");

            LinkedList<Integer> linkedList = stringArrayToIntLinkedList(words);
            time = System.nanoTime();
            Collections.sort(linkedList);
            time = System.nanoTime() - time;
            out.write("\n" + "Сортировка двусвязного списка стандартным методом      - " + time + " наносекунд");

            SimpleLinkedList<Integer> list = stringArrayToIntSimpleLinkedList(words);
            time = System.nanoTime();
            list = sortLinkedListWithArray(list);
            time = System.nanoTime() - time;
            out.write("\n" + "Сортировка моего односвязного списка с помощью массива - " + time + " наносекунд");
            out.write("\n" + simpleLinkedListToString(list));

            list = stringArrayToIntSimpleLinkedList(words);
            time = System.nanoTime();
            list.insertionSort();
            time = System.nanoTime() - time;
            out.write("\n" + "Сортировка моего односвязного списка моим методом      - " + time + " наносекунд");
            out.write("\n" + simpleLinkedListToString(list) + "\n" + "\n");

            /*
            list = stringArrayToIntSimpleLinkedList(words);
            time = System.nanoTime();
            list.sort();
            time = System.nanoTime() - time;
            out.write("\n" + "Сортировка моего односвязного с вызовом от объекта     - " + time + " наносекунд");
            out.write("\n" + simpleLinkedListToString(list));
            */


            str = in.readLine();
        }
        out.close();
        // 1 тест - full sorted
        // 2 тест - swaps array
        // 3 тест - chaotic
        // 4 тест - the worst case
        // 5 тест - full of const
        // 6 тест - (almost) full of const
        // 7 тест - Big Data
    }

    public static ArrayList<Integer> stringArrayToIntArrayList(String[] words) {
        ArrayList<Integer> list = new ArrayList<>();
        for (String word : words) {
            list.add(Integer.parseInt(word));
        }
        return list;
    }

    public static SimpleLinkedList<Integer> stringArrayToIntSimpleLinkedList(String[] words) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
        for (String word : words) {
            list.addLast(Integer.parseInt(word));
        }
        return list;
    }

    public static LinkedList<Integer> stringArrayToIntLinkedList(String[] words) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (String word : words) {
            list.addLast(Integer.parseInt(word));
        }
        return list;
    }

    public static SimpleLinkedList<Integer> arrayToIntLinkedList(int[] a) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();
        for (int number : a) {
            list.addLast(number);
        }
        return list;
    }

    public static int[] simpleLinkedListToArray(SimpleLinkedList<Integer> linkedList) {
        int count = linkedList.getCount();
        int[] a = new int[count];
        int i = 0;
        for (int number : linkedList) {
            a[i] = number;
            i++;
        }
        return a;
    }

    public static SimpleLinkedList<Integer> sortLinkedListWithArray(SimpleLinkedList<Integer> list) {
        int[] a = simpleLinkedListToArray(list);
        Arrays.sort(a);
        return arrayToIntLinkedList(a);
    }

    public static String simpleLinkedListToString(SimpleLinkedList<Integer> list) {
        StringBuilder str = new StringBuilder();
        for (int number : list) {
            str.append(number + " ");
        }
        return str.toString();
    }

}

/*
  list.addFirst(2);
        list.addFirst(3);
        list.addFirst(5);
        list.addFirst(1);
        list.addFirst(4);
 */
