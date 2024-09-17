package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

        @Test
        /** Adds a few things to the list, checking isEmpty() and size() are correct,
         * finally printing the results.
         *
         * && is the "and" operation. */
        public void addIsEmptySizeTest() {

            System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

            ArrayDeque<String> lld1 = new ArrayDeque<String>();

            assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
            lld1.addFirst("front");

            // The && operator is the same as "and" in Python.
            // It's a binary operator that returns true if both arguments true, and false otherwise.
            assertEquals(1, lld1.size());
            //最后提交的时候把这个注释掉，把nextFirst 改成private
            assertEquals(7, lld1.nextFirst);
            assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

            lld1.addLast("middle");
            assertEquals(2, lld1.size());

            lld1.addLast("back");
            assertEquals(3, lld1.size());

            System.out.println("Printing out deque: ");
            lld1.printDeque();

        }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }


}
