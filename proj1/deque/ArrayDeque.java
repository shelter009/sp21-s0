package deque;

public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;

    public ArrayDeque(){
        items = (T[])new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(T x){
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % (items.length);
        size++;
        IncLen();
    }

    public void addLast(T x){
        items[nextLast] = x;
        nextLast = (nextLast + 1) % (items.length);
        size++;
        IncLen();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque()
    {
        if(isEmpty())
            return;
        int l = (nextFirst + 1) % items.length;
        int r = (nextLast + items.length - 1) % items.length;
        while(l != r){
            System.out.print(items[l] + " ");
            l = (l + 1) % items.length;
        }
        System.out.println(items[l]);
    }

    public T removeFirst(){
        if (isEmpty())
            return null;
        else{
            DecLen();
            size--;
            nextFirst = (nextFirst + 1) % items.length;
            return items[nextFirst];
        }
    }

    public T removeLast(){
        if (isEmpty())
            return null;
        else{
            DecLen();
            nextLast = (nextLast - 1 + items.length) % items.length;
            size--;
            return items[nextLast];
        }
    }

    public T get(int x){
        if (x > size - 1)
            return null;
        return items[(nextFirst + x + 1) % items.length];
    }

    public T getRecursive(){
        return null;
    };

    private void IncLen(){
        if(size == items.length){
            ResizeArray(2);
        }
    }
    private void DecLen(){
        if ((double)size/items.length <= 0.25)
            ResizeArray(0.5);
    }

    private void ResizeArray(double factor){
        T[] temps;
        temps = items;
        items = (T[])new Object[(int) (temps.length * factor)];
        int l = (nextFirst + 1) % temps.length;
        int r = (nextLast + temps.length - 1) % temps.length;
        int i = 0;
        while(l != r){
            items[i] = temps[l];
            l = (l + 1) % temps.length;
            i++;
        }
        items[i] = temps[l];
        nextFirst = items.length - 1;
        nextLast = size ;
    }

    /*public static void main(String[] args){
        ArrayDeque A = new ArrayDeque();
        A.addFirst(12);
        A.addFirst("er");
        A.addLast(1);
        A.addLast(3);
        A.addLast(5);
        A.addLast(2);
        A.addLast(7);
        A.addLast(6);
        A.addLast(4);
        A.removeLast();

        System.out.println(A.get(3));
        A.printDeque();
    }*/
}
