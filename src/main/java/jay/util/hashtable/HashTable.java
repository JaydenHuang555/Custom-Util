package jay.util.hashtable;


import jay.util.ForEachFunc;
import jay.util.OrderedList;
import sun.java2d.opengl.OGLContext;

import java.util.Enumeration;
import java.util.Iterator;

public class HashTable<K extends Object, V extends Object> implements Iterable<HashTable.Entry> {

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

    private final Node[] table;

    private int hash(final K o){
        return (o.hashCode() << 5) % MAX;
    }

    @SuppressWarnings("unchecked")
    private int hash(Node node) {
        return hash((K)node.key);
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
        Node theta = new Node(key, val);
        if(table[hash(theta)] == null)
            table[hash(theta)] = theta;
        else {
            Node next = table[hash(theta)];
            while(next.next != null) next = next.next;
            next.next = theta;
        }
    }

    public void put(K[] keys, V[] vals){
        if(keys.length != vals.length) throw new RuntimeException("arrays are not of same size");
        for(int i = 0; i < vals.length; i++)
            put(keys[i], vals[i]);
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
        for(Entry entry : this)
            if(entry.key().hashCode() == key.hashCode()) return true;
        return false;
    }

    public boolean containsVal(V val){
        for(Entry entry : this)
            if(entry.val().hashCode() == val.hashCode()) return true;
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

    public OrderedList<Entry> getAsList(){
        OrderedList<Entry> entries = new OrderedList<>();
        for(Entry entry : this) entries.add(entry);
        return entries;
    }

    public Enumeration<Entry> enumeration(){
        return new Enumeration<Entry>(){
            private int i = 0;
            private final OrderedList<Entry> hhh = getAsList();

            @Override
            public boolean hasMoreElements() {
                return i < hhh.size();
            }

            @Override
            public Entry nextElement() {
                return hhh.get(i++);
            }
        };
    }

    @Override
    public Iterator<HashTable.Entry> iterator(){
        return new HashTableIterator(this);
    }

    private final class HashTableIterator implements Iterator<HashTable.Entry> {

        private final OrderedList<Entry> entries = new OrderedList<>();
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
        public Entry next() {
            return entries.get(offset++);
        }
    }

}
