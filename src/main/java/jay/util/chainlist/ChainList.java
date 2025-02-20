package jay.util.chainlist;

import jay.util.math.ParaToken;

import java.util.Iterator;

public class ChainList<T> implements Iterable{

    private Node<T> head, tail;

    public ChainList(){

    }

    public void pushBack(T item){
        Node<T> next = new Node(item);
        if(head == null){
            head = next;
            tail = next;
            tail.prev = head;
        }
        else {
            tail.next = next;
            next.prev = tail;
        }
    }

    @Override
    public Iterator iterator() {
        return new ChainListIterator(this);
    }

    private final class ChainListIterator implements Iterator<T> {

        private final ChainList<T> chainList;
        private Node next = head;
        boolean passedHead = false;


        private ChainListIterator(ChainList<T> chainlist){
            this.chainList = chainlist;
        }

        @Override
        public boolean hasNext() {
            return next == head && passedHead;
        }

        @Override @SuppressWarnings("unchecked")
        public T next() {
            Node ret = next;
            if(!passedHead && next == head) passedHead = true;
            next = next.next;
            return (T)ret.item;
        }
    }

}
