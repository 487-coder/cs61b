package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {

        AList<Integer> L = new AList<>();
        AList<Integer> Test = new AList<>();
        L.addLast(4);
        L.addLast(5);
        L.addLast(6);
        Test.addLast(4);
        Test.addLast(5);
        Test.addLast(6);
        for (int i = 0; i < 3; i++) {
            Integer expected = Test.removeLast();
            Integer actual = L.removeLast();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> T = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                T.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int testsize = T.size();
                assertEquals(size, testsize);
                System.out.println("size: " + size);
            } else if (operationNumber == 2 && L.size() > 0) {
                // size
                int last = L.getLast();
                int testlast = T.getLast();
                assertEquals(last, testlast);
                System.out.println("last: " + last);
            } else if (operationNumber == 3 && L.size() > 0) {
                // size
                int removed = L.removeLast();
                int testremoved = T.removeLast();
                assertEquals(removed, testremoved);
                System.out.println("removed: " + removed);
            }
        }
    }
}
