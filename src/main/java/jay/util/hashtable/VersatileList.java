package jay.util.hashtable;

import jay.util.EmptyListException;
import jay.util.OrderedList;
import jay.util.Pair;

public class VersatileList<K, V> extends HashTable<K, V> {
    private final OrderedList<Pair<K, V>> pairs = new OrderedList<>();

    public VersatileList() {
        super();
    }

    @Override
    public void put(K key, V val) {
        super.put(key, val);
        pairs.add(new Pair<>(key, val));
    }

    @Override
    public void remove(K key) {
        pairs.pruneFirst(new Pair<>(key, get(key)));
        super.remove(key);
    }

    public void removeFirst() {
        if(pairs.isEmpty()) throw new EmptyListException();
        super.remove(pairs.get(0).getFirst());
        pairs.pruneFirst(pairs.get(0));
    }

    public boolean isValidIndex(int index) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Pair<K, V> getPairAtIndex(int index) {
        return pairs.get(index);
    }

    public K getKeyAtIndex(int index) {
        return pairs.get(index).getFirst();
    }

    public V getValAtIndex(int index){
        return pairs.get(index).getSecond();
    }

}
