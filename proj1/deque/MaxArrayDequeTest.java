package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    public class Compare_len implements Comparator<String>{
        @Override
        public int compare(String s1, String s2) {
            return s2.length() - s1.length();
        }
    }

    @Test
    public void LenComTest(){
        Comparator<String> s = new Compare_len();
        MaxArrayDeque<String> m1 = new MaxArrayDeque<>(s);
        m1.addLast("dsdf");
        m1.addLast("a");
        m1.addLast("df");
        assertEquals("dsdf", m1.max());
    }

}
