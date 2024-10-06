package deque;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
   @Test
    public void maxTest() {
       Comparator<Integer> c = new IntComparator();
       MaxArrayDeque<Integer> l = new MaxArrayDeque<>(c);
       l.addLast(0);
       l.addLast(1);
       l.addLast(4);
       l.addLast(7);
       l.addLast(9);
       l.addLast(2);
       l.addLast(3);
       l.addLast(3);
       assertEquals(9, (int) l.max());
    }
    @Test
    public void MaxWithParameterTest() {
        Comparator<Integer> c = new IntComparator();
        MaxArrayDeque<Integer> l = new MaxArrayDeque<>(c);
        l.addLast(0);
        l.addLast(1);
        l.addLast(4);
        l.addLast(7);
        l.addLast(9);
        l.addLast(2);
        l.addLast(3);
        l.addLast(3);
        assertEquals(9, (int) l.max(c));
    }
}
