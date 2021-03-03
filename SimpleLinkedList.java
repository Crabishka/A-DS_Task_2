import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SimpleLinkedList<T> implements Iterable<T> {


    private class ListNode<T> {
        public T value;
        public ListNode<T> next;

        public ListNode(T value, ListNode<T> next) {
            this.value = value;
            this.next = next;
        }

        public ListNode(T value) {
            this(value, null);
        }

        public ListNode() {
            this(null);
        }
    }

    private ListNode<T> head = null;
    private ListNode<T> tail = null;

    private int count = 0;

    public void addFirst(T value) {
        head = new ListNode<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (count > 0) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        count++;
    }

    private void emptyError() throws Exception {
        if (count == 0) {
            throw new Exception("List is empty");
        }
    }

    public T getFirst() throws Exception {
        emptyError();
        return head.value;
    }

    public T getLast() throws Exception {
        emptyError();
        return tail.value;
    }

    private ListNode<T> getNode(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Wrong index");
        }
        ListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T get(int index) throws Exception {
        return getNode(index).value;
    }

    public void removeFirst() throws Exception {
        emptyError();
        head = head.next;
        count--;
        if (count == 0) {
            tail = null;
        }
    }

    public void removeLast() throws Exception {
        emptyError();
        count--;
        if (count == 0) {
            head = tail = null;
        } else {
            tail = getNode(count - 1);
            tail.next = null;
        }
    }

    public void clear() {
        head = tail = null;
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void insertionSort() {

        class SortByValue implements Comparator<ListNode<T>> {  // ладно
            public int compare(ListNode<T> o1, ListNode<T> o2) {
                return Integer.parseInt(o1.value.toString()) - Integer.parseInt(o2.value.toString());
            }
        }

        /*
        берем число
        сразу сохраняем следующее число

         */

        SortByValue sort = new SortByValue();

        // реализация через количество элементов
        for (int j = count - 2; j > 0; j--) { // просто количество элементов, которое мы переносим
            ListNode<T> main = head;
            ListNode<T> curr = main;
            ListNode<T> temp = main;
            for (int i = count - 2; i > 0; i--) {  // минимальное количество шагов
                curr = curr.next;
            }
            while (sort.compare(main, curr) > 0 && curr != tail){
                curr = curr.next;
            }
            main.next = curr.next;
            curr.next = head;
            head.next = main.next;

        }

      /*  while (main.next != null) {
            nextMain = main.next;
            curr = main.next;
            while (true) {
                if (sort.compare(main, curr) > 0 && curr != tail) { // curr < main
                    prev = curr;
                    curr = curr.next;
                } else {
                    main.next = curr;
                    prev.next = main;

                    break;
                }

            }


        }
*/
    }


    @Override
    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            ListNode<T> curr;

            public LinkedListIterator(ListNode<T> head) {
                curr = head;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T result = curr.value;
                curr = curr.next;
                return result;
            }
        }

        return new LinkedListIterator(head);
    }


}


