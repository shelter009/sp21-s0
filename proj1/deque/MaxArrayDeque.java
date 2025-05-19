package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }

    public T max(){
        int l = (nextFirst + 1) % items.length;
        int r = (nextLast - 1 + items.length) % items.length;
        T max = items[l];
        T cur;
        if (size == 0)
            return null;
        else{
            while ( l != r) {
                l++;
                cur = items[l];
                if (comparator.compare(cur, max) < 0)
                    max = cur;
            }
        }
        return max;
    }

    public T max(Comparator<T> c){
        int l = (nextFirst + 1) % items.length;
        int r = (nextLast - 1 + items.length) % items.length;
        T max = items[l];
        T cur;
        if (size == 0)
            return null;
        else{
            while ( l != r) {
                l++;
                cur = items[l];
                if (c.compare(cur, max) < 0)
                    max = cur;
            }
        }
        return max;
    }
}
