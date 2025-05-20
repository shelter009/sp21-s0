package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing L1 = new AListNoResizing();
        BuggyAList L2 = new BuggyAList();
        L1.addLast(3);
        L2.addLast(3);
        L1.addLast(4);
        L2.addLast(4);
        L1.addLast(5);
        L2.addLast(5);
        assertEquals(L1.removeLast(), L2.removeLast());
        assertEquals(L1.removeLast(), L2.removeLast());
        assertEquals(L1.removeLast(), L2.removeLast());
    }

    @Test
    public void RandomTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                //assertEquals(B.);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size1 = B.size();
                //System.out.println("size: " + size);
                assertEquals(size, size1);
            }
            else if (operationNumber == 2){
                //getLast
                int j = 0 , j1 = 0;
                if (L.size() > 0) {
                    j = L.getLast();
                    j1 = B.getLast();
                    assertEquals(j, j1);
                }
                    //System.out.println("getLast: " + j);
            }
            else if (operationNumber == 3){
                //removeLast
                int k = 0, k1 = 0;
                if (L.size() > 0){
                    k = L.removeLast();
                    k1 = B.removeLast();
                    assertEquals(k, k1);
                }
                //System.out.println("removeLast: " + k);
            }
        }
    }

}
