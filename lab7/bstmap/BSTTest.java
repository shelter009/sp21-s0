package bstmap;

import org.junit.Test;

public class BSTTest {
    @Test
    public void TestPrint(){
        BSTMap<String, Integer> Map = new BSTMap<>();
        Map.put("dsfdf", 122);
        Map.put("wd", 45);
        Map.put("ad", 56);
        Map.put("hgh", 83);
        Map.put("ty", 34);
        Map.printInOrder();
        System.out.println(Map.size());
    }
}
