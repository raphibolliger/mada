package ch.fhnw.mada.bonusaufgabe.helpers;

import java.util.HashMap;

public class MyOwnHashMap {

    private HashMap<String, Integer> hashMap;

    public MyOwnHashMap()
    {
        hashMap = new HashMap<String, Integer>();
    }

    public void add(String c)
    {
        if (hashMap.containsKey(c))
            hashMap.put(c, hashMap.get(c) + 1);
        else
            hashMap.put(c, 1);
    }

    public HashMap<String, Integer> getHashMap()
    {
        return (HashMap<String, Integer>) hashMap.clone();
    }

    public int getSize()
    {
       return hashMap.size();
    }

}
