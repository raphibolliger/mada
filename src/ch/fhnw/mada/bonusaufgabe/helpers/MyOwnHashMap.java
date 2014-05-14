package ch.fhnw.mada.bonusaufgabe.helpers;

import java.util.HashMap;

public class MyOwnHashMap {

    private HashMap<Character, Integer> hashMap;

    public MyOwnHashMap()
    {
        hashMap = new HashMap<Character, Integer>();
    }

    public void add(char c)
    {
        if (hashMap.containsKey(c))
            hashMap.put(c, hashMap.get(c) + 1);
        else
            hashMap.put(c, 1);
    }

    public HashMap<Character, Integer> getHashMap()
    {
        return this.hashMap;
    }

}
