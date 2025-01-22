package jay.util.stack;

public class Stack<T extends Object> {

    private Node head = null, tail = null;

    public Stack(){

    }

    public void push(T item){
        if(head == null){
            head = new Node(item);
            tail = head;
        }
        else {
            tail.next = new Node(item);
            tail.next.prev = tail;
            tail = tail.next;
        }
    }
    @SuppressWarnings("unchecked")
    public T peek(){
        return (T)tail.val;
    }

    public T pop(){
        T ret = peek();
        if(tail == head){
            tail = null;
            head = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return ret;
    }

    public boolean isEmpty(){
        return head == null;
    }

}
