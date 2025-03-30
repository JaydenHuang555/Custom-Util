package jay.util.stack;

public class Stack<T> {

    private Node head, tail;

    public Stack() {

    }

    public void push(T item) {
        Node node = new Node(item);
        if(head == null) {
            head = node;
            tail = head;
        }
        else {
            tail.next = node;
            tail.next.prev = tail;
            tail = tail.next;
        }
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return (T)tail.val;
    }

    public T pop() {
        T ret = peek();
        if(tail != head) {
            tail = tail.prev;
            tail.next = null;
        } else {
            head = null;
            tail = null;
        }
        return ret;
    }

    public boolean isEmpty() {
        return head == null;
    }

}