package jay.util.chainlist;

class Node<T> {

    T item;
    Node<T> next, prev;

    Node(T item){
        this.item = item;
    }
}
