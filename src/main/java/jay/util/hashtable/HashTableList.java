package jay.util.hashtable;

import jay.util.OrderedList;
import jay.util.Pair;

public class HashTableList<K, V> extends HashTable<K, V> {
    private final OrderedList<Pair<K, V>> pairs = new OrderedList<>();

    public HashTableList() {
        super();
    }

    @Override
    public void put(K key, V val) {
        super.put(key, val);
        pairs.add(new Pair<K, V>(key, val));
    }

    @Override
    public void remove(K key) {
        pairs.pruneFirst(new Pair<>(key, get(key)));
        super.remove(key);
    }

    public K getKeyAtIndex(int index) {
        return pairs.get(index).getFirst();
    }

    public V getValAtIndex(int index){
        return pairs.get(index).getSecond();
    }

}
