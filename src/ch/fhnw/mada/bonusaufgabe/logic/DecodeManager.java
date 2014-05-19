package ch.fhnw.mada.bonusaufgabe.logic;

import ch.fhnw.mada.bonusaufgabe.helpers.FileManager;
import ch.fhnw.mada.bonusaufgabe.helpers.MyOwnHashMap;
import ch.fhnw.mada.bonusaufgabe.helpers.TreeObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DecodeManager {

    public void decode(DecodeData decodeData, boolean openAfterEncode) throws IOException
    {
        generateDecodeTable(decodeData);
        generateDecodeTree(decodeData);
        generateDecodedFile(decodeData, openAfterEncode);
    }

    private void generateDecodeTree(DecodeData decodeData)
    {
        ArrayList<TreeObject> intialTree = createInitialTree(decodeData.getCaracterCountTable());
        TreeMap<String,Integer> copiedCharacterCountTable = new TreeMap<String, Integer>();
        copiedCharacterCountTable.putAll(decodeData.getCaracterCountTable().getHashMap());
        makeTree(intialTree, copiedCharacterCountTable, decodeData);
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

    private void makeTree(ArrayList<TreeObject> treeObjectsArray, TreeMap<String, Integer> decodeTreeMap, DecodeData decodeData)
    {
        if(decodeTreeMap.size() == 1) {
            decodeData.setCompleteTree(treeObjectsArray);
            return;
        }

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

        makeTree(treeObjectsArray, newNotSortedTreeMap, decodeData);
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

    private void generateDecodedFile(DecodeData decodeData, boolean openAfterEncode) throws IOException {
        ArrayList<TreeObject> completeTree = decodeData.getCompleteTree();
        ArrayList<ArrayList<Integer>> codesForEachCharacter = new ArrayList<ArrayList<Integer>>();
        for (int i= 0; i < decodeData.getCaracterCountTable().getSize(); i++)
        {
            TreeObject tempTreeObject = completeTree.get(i);
            ArrayList<Integer> abc = new ArrayList<Integer>();
            while (tempTreeObject.getParent() != null)
            {
                if (tempTreeObject.getParent().getChild0().equals(tempTreeObject))
                    abc.add(0);
                else
                    abc.add(1);
                tempTreeObject = tempTreeObject.getParent();
            }
            Collections.reverse(abc);
            codesForEachCharacter.add(abc);
        }

        String completeCode = "";
        HashMap<Character, String> tempMap = new HashMap<Character, String>();
        for (int i = 0; i < codesForEachCharacter.size(); i++)
        {
            completeCode += completeTree.get(i).getText();
            completeCode += ":";
            String tempCode = "";
            for (int j = 0; j < codesForEachCharacter.get(i).size(); j++)
            {
                completeCode += codesForEachCharacter.get(i).get(j).toString();
                tempCode += codesForEachCharacter.get(i).get(j).toString();
            }
            tempMap.put(completeTree.get(i).getText().charAt(0), tempCode);
            completeCode += "-";
        }
        completeCode = completeCode.substring(0, completeCode.length()-1);
        System.out.println(completeCode);

        // generate complete bit string
        String codedBitStream = "";
        String inputFileString = FileManager.readFile(decodeData.getInputFile());
        for (int i = 0; i < inputFileString.length(); i++)
        {
            codedBitStream += tempMap.get(inputFileString.charAt(i));
        }

        int bitStringLength = codedBitStream.length();
        if (bitStringLength%8 == 0)
        {
            codedBitStream += "10000000";
        }
        else
        {
            int rest = 8 - bitStringLength%8;
            for (int i = 0; i < rest; i++)
            {
                if (i == 0) codedBitStream += "1";
                else codedBitStream += "0";
            }
        }

        int moduloFromString = codedBitStream.length()/8;
        byte[] outputByteArray = new byte[moduloFromString];
        for (int i = 0; i < moduloFromString; i++)
        {
            outputByteArray[i] = (byte)Short.parseShort(codedBitStream.substring(i*8, (i*8)+8), 2);
        }



        // write the dec_tab.txt
        File file = new File(decodeData.getOutputPath()+"/dec_tab.txt");
        if (file.exists()) file.delete();
        FileManager.writeFile(decodeData.getOutputPath(),"dec_tab.txt", completeCode.getBytes());

        if (openAfterEncode) Desktop.getDesktop().open(file);

        // write the output.dat
        File fileOutput = new File(decodeData.getOutputPath()+"/output.dat");
        if (fileOutput.exists()) fileOutput.delete();
        FileManager.writeFile(decodeData.getOutputPath(),"output.dat", outputByteArray);


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
