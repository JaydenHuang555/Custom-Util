package jay.util.hashtable;

import jay.util.ForEachFunc;

public class HashTable<K extends Object, V extends Object> {

    public final class Entry {
        private final Node node;

        private Entry(Node node){
            this.node = node;
        }
        @SuppressWarnings("unchecked")
        public K key(){
            return (K)node.key;
        }

        @SuppressWarnings("unchecked")
        public V val(){
            return (V)node.val;
        }

    }

    private final int MAX = 1 << 9;

    private int hash(Object o){
        return (o.hashCode() << 5) % MAX;
    }

    private final Node table[];

    public HashTable(){
        table = new Node[MAX];
    }

    public void put(K key, V val){
        int index = hash(key);
        if(table[index] == null) table[index] = new Node(key, val);
        else {
            Node next = table[index];
            while(next.next != null) next = next.next;
            next.next = new Node(key, val);
        }
    }

    @SuppressWarnings("unchecked")
    public V get(K key){
        if(!contains(key)) throw new RuntimeException("invalid key");
        Node node = table[hash(key)];
        while(node != null){
            if(node.key.hashCode() == key.hashCode()) return (V)node.val;
            node = node.next;
        }
        throw new RuntimeException("error when checking if table contains key");
    }

    public boolean contains(K key){
        Node node = table[hash(key)];
        while(node != null){
            if(node.key.hashCode() == key.hashCode()) return true;
            node = node.next;
        }
        return false;
    }

    public void foreach(ForEachFunc<Entry> f){
        for(int i = 0; i < MAX; i++)
            if(table[i] != null) f.f(new Entry(table[i]));
    }

}
