package flik;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilkTest {
    @Test
    public void TestEqual() {
        for (int i = 0; i < 400; i++) {
            System.out.println(i);
            assertTrue(Flik.isSameNumber(i, i));
        }
    }
}
