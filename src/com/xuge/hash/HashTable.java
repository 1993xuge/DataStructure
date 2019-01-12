package com.xuge.hash;

import java.util.TreeMap;

public class HashTable<K, V> {

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    private TreeMap<K, V>[] treeMaps;
    private int M;
    private int size;

    public HashTable() {
        this(initCapacity);
    }

    public HashTable(int M) {
        this.M = M;
        size = 0;
        treeMaps = new TreeMap[M];

        for (int i = 0; i < M; i++) {
            treeMaps[i] = new TreeMap<>();
        }
    }

    private void add(K key, V value) {
        TreeMap<K, V> map = treeMaps[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }
    }

    private V remove(K key) {
        TreeMap<K, V> map = treeMaps[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && M / 2 >= initCapacity) {
                resize(M / 2);
            }
        }
        return ret;
    }

    private void set(K key, V value) {
        TreeMap<K, V> map = treeMaps[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " does not exist!");
        }

        map.put(key, value);
    }

    public boolean contains(K key) {
        return treeMaps[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return treeMaps[hash(key)].get(key);
    }

    // 哈希函数
    private int hash(K key) {
        // 先将hashCode转化成正数，然后取模
        return (key.hashCode() & 0x7fff_ffff) % M;
    }

    public int getSize() {
        return size;
    }

    private void resize(int newCapacity) {
        TreeMap<K, V>[] newTreeMaps = new TreeMap[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newTreeMaps[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newCapacity;
        for(int i = 0 ; i < oldM ; i ++){
            TreeMap<K, V> map = treeMaps[i];
            for(K key: map.keySet())
                newTreeMaps[hash(key)].put(key, map.get(key));
        }

        this.treeMaps = newTreeMaps;
    }
}
