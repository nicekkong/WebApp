package com.je.webapp.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapBuilder<K, V> {
    
    private Map<K, V> map = new ConcurrentHashMap<K, V>();
    
    @SafeVarargs
    public MapBuilder(Tuples.Tuple2<K, V>... keyValues) {
        for (Tuples.Tuple2<K, V> keyValue : keyValues)
            put(keyValue);
    }
    
    @SafeVarargs
    public static <K, V> MapBuilder<K, V> builder(Tuples.Tuple2<K, V>... keyValues)
    {
        return new MapBuilder<K, V>(keyValues);
    }
    
    @SafeVarargs
    public static <K, V> Map<K, V> map(Tuples.Tuple2<K, V>... keyValues)
    {
        return builder(keyValues).build();
    }
    
    public MapBuilder<K, V> put(Tuples.Tuple2<K, V> keyValue)
    {
        return put(keyValue.v1, keyValue.v2);
    }
    
    public MapBuilder<K, V> put(K key, V value)
    {
        this.map.put(key, value);
        return this;
    }
    
    public Map<K, V> build()
    {
        return map;
    }
}
