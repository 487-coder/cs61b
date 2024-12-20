package deque;

import java.util.Iterator;
import java.util.Map;

public class ArrayDeque <T> implements Deque<T>, Iterable<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }


    @Override
    public void addFirst(T item) {
        if (size == items.length - 2){
            resize(size*2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length ;
        size += 1;
    }
    private void resize(int capicity){
        T[] a = (T[]) new Object[capicity];
        if (nextFirst < nextLast){
            System.arraycopy(items, nextFirst,a,0,size+2);
            nextFirst = 0;
            nextLast = size + 1;
        } else if (nextFirst > nextLast) {
            System.arraycopy(items, nextFirst, a, 0,(items.length - nextFirst));
            System.arraycopy(items, 0, a,(items.length - nextFirst) ,(size+2-items.length+nextFirst));
            nextFirst = 0;
            nextLast = size + 1;
        }
        items = a;

    }

    @Override
    public void addLast(T item) {
        if (size == items.length - 2){
            resize(size*2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;

    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuilder printStr = new StringBuilder();
        int index = (nextFirst + 1) % (items.length);
        while (index < nextLast){
            printStr.append(items[index].toString());
            printStr.append(" ");
            index = (index + 1) % (items.length);
        }
        System.out.println(printStr);
    }

    @Override
    public T removeFirst() {
        if (size != 0){
        T a = items[(nextFirst + 1) % items.length];
        nextFirst = (nextFirst + 1) % items.length;
        size -= 1;
        if (size < 0.25*items.length && items.length > 8){
            resize(items.length / 2);
        }
        return a;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size != 0){
            T a = items[(nextLast + items.length- 1)% items.length];
            nextLast = (nextLast + items.length- 1)% items.length;
            size -= 1;
            if (size < 0.25*items.length && items.length > 8){
                resize(items.length / 2);
            }
            return a;}
        return null;
    }

    @Override
    public T get(int index) {
        return items[(nextFirst+1+index)% items.length];
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof ArrayDeque)){
            return false;
        }

        int index1 = this.nextFirst + 1;
        int index2 = ((ArrayDeque<T>) o).nextFirst + 1;
        while (index1 != this.nextLast && index2 !=((ArrayDeque<T>) o).nextLast){
            if (this.items[index1] != ((ArrayDeque<?>) o).items[index2]){
            return false;}
            else {
                index1 += 1;
                index2 += 1;
            }
        }

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDueueIterator();
    }


    private class ArrayDueueIterator implements Iterator <T>{
        public int wizPos;
        @Override
        public boolean hasNext() {
            if (wizPos >= nextLast){
                return false;
            }
            return true;
        }
        @Override
         public T next(){
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
         }


    }
}