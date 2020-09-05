package org.benti.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Work around for Java 8 missing map builder (available in Java 9)
 *
 * @param <T> Key for pair
 * @param <R> Value for pair
 */
public class MapBuilder<T, R> {

    private Map<T, R> map;

    public MapBuilder() {
        map = new HashMap<T, R>(0);
    }

    public MapBuilder<T, R> addRecord(T t, R r) {
        map.put(t, r);
        return this;
    }

    public Map<T, R> build() {
        return map;
    }

}
