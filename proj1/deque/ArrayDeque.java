package deque;

import java.util.Iterator;
import java.util.Map;

public class ArrayDeque <T> implements Deque<T>, Iterable<T>{
    public int size;
    public T[] items;
    public int nextFirst;
    public int nextLast;
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
        nextLast += 1;
        size += 1;

    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
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