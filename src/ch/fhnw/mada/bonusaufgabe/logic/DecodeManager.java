package ch.fhnw.mada.bonusaufgabe.logic;

import ch.fhnw.mada.bonusaufgabe.helpers.FileManager;
import ch.fhnw.mada.bonusaufgabe.helpers.MyOwnHashMap;
import ch.fhnw.mada.bonusaufgabe.helpers.TreeObject;

import java.io.IOException;
import java.util.*;

public class DecodeManager {

    public void decode(DecodeData decodeData) throws IOException
    {
        generateDecodeTable(decodeData);
        generateDecodeTree(decodeData);
        generateDecodedFile(decodeData);
    }

    private void generateDecodeTree(DecodeData decodeData)
    {
        ArrayList<TreeObject> intialTree = createInitialTree(decodeData.getCaracterCountTable());
        TreeMap<String,Integer> abc = new TreeMap<String, Integer>();
        abc.putAll(decodeData.getCaracterCountTable().getHashMap());
        makeTree(intialTree, abc);
    }

    private ArrayList<TreeObject> createInitialTree(MyOwnHashMap caracterCountTable)
    {
        ArrayList<TreeObject> initialTree = new ArrayList<TreeObject>();
        for(String c : caracterCountTable.getHashMap().keySet())
        {
            TreeObject initialTreeObject = new TreeObject();
            initialTreeObject.setText(c);
            initialTree.add(initialTreeObject);
        }
        return initialTree;
    }

    private void makeTree(ArrayList<TreeObject> treeObjectsArray, TreeMap<String,Integer> decodeTreeMap)
    {
        if(decodeTreeMap.size() == 1)
            return;

        TreeMap<String,Integer> sortedDecodeTreeMap = sortDecodeTreeMap(decodeTreeMap);

        Map.Entry<String,Integer> firstEntry = sortedDecodeTreeMap.firstEntry();
        sortedDecodeTreeMap.pollFirstEntry();

        Map.Entry<String,Integer> secondEntry = sortedDecodeTreeMap.firstEntry();
        sortedDecodeTreeMap.pollFirstEntry();

        TreeObject newTreeObject = new TreeObject();
        newTreeObject.setText(firstEntry.getKey()+ secondEntry.getKey());

        HashMap<String,Integer> hashMap = new HashMap<String, Integer>(sortedDecodeTreeMap);
        hashMap.put(newTreeObject.getText(), firstEntry.getValue()+ secondEntry.getValue());

        TreeMap<String, Integer> newNotSortedTreeMap = new TreeMap<String, Integer>(hashMap);

        for(int i = 0; i < treeObjectsArray.size();i++)
        {
            TreeObject treeObject = treeObjectsArray.get(i);
            if(treeObject.getText() == firstEntry.getKey())
            {
                newTreeObject.setChild0(treeObject);
                SetParentAndAddToTreeObjectArray(treeObjectsArray, newTreeObject, treeObject);
            }
            if(treeObject.getText() == secondEntry.getKey())
            {
                newTreeObject.setChild1(treeObject);
                SetParentAndAddToTreeObjectArray(treeObjectsArray, newTreeObject, treeObject);
            }
        }

        makeTree(treeObjectsArray, newNotSortedTreeMap);
    }

    private void SetParentAndAddToTreeObjectArray(ArrayList<TreeObject> treeObjectsArray, TreeObject newTreeObject, TreeObject treeObject)
    {
        treeObject.setParent(newTreeObject);
        treeObjectsArray.add(treeObjectsArray.size(), newTreeObject);
    }

    private TreeMap<String,Integer> sortDecodeTreeMap(TreeMap<String,Integer> decodeTreeMap)
    {
        ValueComparator bvc =  new ValueComparator(decodeTreeMap);
        TreeMap<String,Integer> outPutTreeMap = new TreeMap<String,Integer>(bvc);
        outPutTreeMap.putAll(decodeTreeMap);
        return outPutTreeMap;
    }


    private void generateDecodeTable(DecodeData decodeData) throws IOException
    {
        String inputText = FileManager.readFile(decodeData.getInputFile());

        char[] inputArray = inputText.toCharArray();

        for(char c : inputArray)
        {
            decodeData.getCaracterCountTable().add(String.valueOf(c));
        }
    }

    private void generateDecodedFile(DecodeData decodeData)
    {

    }

    class ValueComparator implements Comparator<String> {

        Map<String,Integer> base;
        public ValueComparator(Map<String,Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return 1;
            } else {
                return -1;
            } // returning 0 would merge keys
        }
    }

}
