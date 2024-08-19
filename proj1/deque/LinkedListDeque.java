package deque;

import java.util.Deque;
import java.util.Iterator;

public class LinkedListDeque<T>{

    private class IntNode<T> {
        public T item;
        public IntNode next;
        public IntNode prev;

        public IntNode(T i, IntNode n, IntNode m){
            this.item = i ;
            this.next = n;
            this.prev = m;
        }
    }
    /**一个sentinel node，循环法*/
    private IntNode sentinel;
    private int size;

    /**空Deque*/
    public LinkedListDeque() {
        sentinel = new IntNode<T>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
            IntNode p = new IntNode(item, sentinel.next, sentinel);
            sentinel.next.prev =p;
            sentinel.next = p;
            size += 1;

    }
    /**注意这里要有恒定的时间复杂度，也就是说所有查找地址的操作从sentinel开始*/
    public void addLast(T item) {
        IntNode p = new IntNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size() {
       return size;
    }

    public void printDeque()  {
        StringBuilder printStr = new StringBuilder();
        for (IntNode i = sentinel.next; i != sentinel; i = i.next) {
            printStr.append(i.item.toString());
            printStr.append(" ");
        }
        System.out.println(printStr);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        IntNode p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size -= 1;
        T removed = (T) p.item;
        p.item = null;
        return removed;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        IntNode p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size -= 1;
        T removed = (T) p.item;
        p.item = null;
        return removed;
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        }
        int num = 0;
        IntNode p = sentinel.next;
        while (num < index) {
            p = p.next;
            num += 1;
        }
        return (T) p.item;
    }

    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {
        private int wizPos;

        public LinkedIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (! (o instanceof LinkedListDeque)) {
            return false;
        }
        if (((LinkedListDeque<?>) o).size != this.size) {
            return false;
        }
        int i = 0;
        while (i < this.size) {
            if (! this.get(i).equals(((LinkedListDeque<?>) o).get(i))) {
                return false;
            }
            i += 1;
        }
        return true;
    }
/**这里不会写，抄了一段*/
    public T getRecursive(int index) {
        IntNode p = sentinel.next;
        if (index != 0 && p == sentinel) {
            return null;
        }
        if (index == 0){
            return (T) p.item;
        }
        LinkedListDeque<T> l = new LinkedListDeque<>();
        l.sentinel.next = p.next;
        return l.getRecursive(index - 1);
    }


}
