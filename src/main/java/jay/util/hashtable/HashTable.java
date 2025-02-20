package jay.util.hashtable;
import jay.util.ForEachFunc;
import jay.util.OrderedList;
import jdk.nashorn.internal.runtime.arrays.IteratorAction;

import java.util.Iterator;

public class HashTable<K extends Object, V extends Object> implements Iterable {

    public final class Entry {
        private final Node node;

        public Entry(final Node node){
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

    private final int MAX;

    private final Node table[];

    private int hash(final Object o){
        return (o.hashCode() << 5) % MAX;
    }

    public HashTable(){
        this.MAX = 0x400;
        table = new Node[MAX];
    }

    public HashTable(final int MAX){
        this.MAX = MAX;
        table = new Node[MAX];
    }

    public void put(K key, V val){
        Node next = table[hash(key)];
        if(next == null)
            next = new Node(key, val);
         int i = 0, cap = 0x400;
         while(next.next != null){
             if(i++ == cap) throw new RuntimeException("iteration reached cap");
             next = next.next;
         }
         next.next = new Node(key, val);
    }

    @SuppressWarnings("unchecked")
    public V get(K key){
        Node next = table[hash(key)];
        int i = 0, cap = 0x400;
        while(next != null){
            if(i++ == cap) throw new RuntimeException("iteration reached cap");
            if(next.key.hashCode() == key.hashCode()) return (V)next.val;
            next = next.next;
        }
        throw new RuntimeException("table does not contain key");
    }

    public boolean contains(K key){
        Node next = table[hash(key)];
        int i = 0, cap = 0x400;
        while(next != null){
            if(i++ == cap) throw new RuntimeException("iteration reached cap");
            if(next.key.hashCode() == key.hashCode()) return true;
            next = next.next;
        }
        return false;
    }

    public void foreach(ForEachFunc<Entry> f){
        for(int i = 0; i < MAX; i++)
            if(table[i] != null){
                Node next = table[i];
                while(next != null){
                    f.f(new Entry(next));
                    next = next.next;
                }
            }
    }

    @Override
    public Iterator<Entry> iterator(){
        return new HashTableIterator(this);
    }

    private final class HashTableIterator implements Iterator {

        private OrderedList<Entry> entries;
        private int offset = 0;

        private HashTableIterator(final HashTable<K, V> hashtable){
            hashtable.foreach(
                    (Entry o) -> entries.add(o)
            );
        }

        @Override
        public boolean hasNext() {
            return offset < entries.size();
        }

        @Override
        public Object next() {
            return entries.get(offset++);
        }
    }

}
