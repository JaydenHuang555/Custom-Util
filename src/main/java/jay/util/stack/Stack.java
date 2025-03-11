package jay.util.stack;

import java.util.function.Consumer;

public class Stack<T extends Object> {

    private Node head, tail;

    public Stack(){
        head = tail = null;
    }

    public void push(T item){
        Node next = new Node(item);
        if(head == null){
            head = next;
            tail = head;
        }
        else {
            tail.next = next;
            tail.next.prev = tail;
            tail = tail.next;
        }
    }

    @SuppressWarnings("unchecked")
    public T peek(){
        return (T)tail.val;
    }

    @SuppressWarnings("unchecked")
    public T pop(){
        T peek = peek();
        if(tail == head){
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return peek;
    }

    @SuppressWarnings("unchecked")
    public void foreach(Consumer<T> f){
        int cap = 0x400, i = 0;
        for(Node next = head; next != null;){
            if(i++ == cap)
                throw new RuntimeException("for each has reached cap iteration");
            f.accept((T)next.val);
            next = next.next;
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

}
