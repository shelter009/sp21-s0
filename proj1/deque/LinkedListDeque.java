package deque;

public class LinkedListDeque<T> implements Deque<T>{
    LinkNode head;
    //LinkNode last;
    int size = 0;

    public class LinkNode{
        T items;
        LinkNode next;
        LinkNode prev;
        public LinkNode(T i, LinkNode n){
            items = i;
            next = n;
        }
    }

    public LinkedListDeque(T x){
        head = new LinkNode(null, null);
        LinkNode first = new LinkNode(x, null);
        head.next = first;
        first.prev = head;
        first.next = head;
        head.prev = first;
        size++;
    }

    public LinkedListDeque(){
        head = new LinkNode(null, null);
        head.next = head;
        head.prev = head;
    }
    public LinkedListDeque(LinkedListDeque other){
        LinkedListDeque p = other;
        LinkNode s = p.head.next;
        head = new LinkNode(null, null);
        head.next = head;
        head.prev = head;

        while(s != p.head)
        {
            addLast(s.items);
            s = s.next;
        }
    }
    public void addFirst(T x){
        LinkNode L = new LinkNode(x,null);
        L.next = head.next;
        L.prev = head;
        head.next.prev = L;
        head.next = L;
        size++;
    }

    public void addLast(T x){
        LinkNode L = new LinkNode(x, null);
        head.prev.next = L;
        L.prev = head.prev;
        head.prev = L;
        L.next = head;
        size++;
    }

    public boolean isEmpty(){
        return head.next == head;
    }

    public int size(){
        return size;
    }

    public void printDeque()
    {
        if(isEmpty())
            return;
        LinkNode p = head.next;
        while (p != head){
            System.out.print(p.items + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty())
            return null;
        T t = head.next.items;
        head.next.next.prev = head;
        head.next = head.next.next;
        size--;
        return t;
    }

    public T removeLast(){
        if(isEmpty())
            return null;
        T t = head.prev.items;
        head.prev.prev.next = head;
        head.prev = head.prev.prev;
        size--;
        return t;
    }
    public T get(int x){
        LinkNode p = head.next;
        for(int i = 0; i < x; i++)
        {
            if(p == head)
                return null;
            p = p.next;
        }
        return p.items;
    }


    /*public static void main(String[] args){
        LinkedListDeque L = new LinkedListDeque();
        L.addFirst("fdfdf");
        L.addLast(12);
        int s = L.size;
        System.out.println(L.get(4));
        L.printDeque();
        //L.removeLast();
    }*/
}
