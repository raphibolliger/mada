package ch.fhnw.mada.bonusaufgabe.logic;

import ch.fhnw.mada.bonusaufgabe.helpers.MyOwnHashMap;
import ch.fhnw.mada.bonusaufgabe.helpers.TreeObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Date object for decoding data
 */
public class DecodeData {

    private File inputFile;
    private String outputPath;
    private MyOwnHashMap caracterCountTable;
    private HashMap<String, Integer> decodingTable;
    private ArrayList<TreeObject> completeTree;

    public ArrayList<TreeObject> getCompleteTree() {
        return completeTree;
    }

    public void setCompleteTree(ArrayList<TreeObject> completeTree) {
        this.completeTree = completeTree;
    }

    public DecodeData(File inputFile, String outputPath)
    {
        this.inputFile = inputFile;
        this.outputPath = outputPath;
        caracterCountTable = new MyOwnHashMap();
        decodingTable = new HashMap<String, Integer>();
    }

    public File getInputFile()
    {
        return inputFile;
    }

    public String getOutputPath()
    {
        return outputPath;
    }

    public MyOwnHashMap getCaracterCountTable()
    {
        return caracterCountTable;
    }

    public void setCaracterCountTable(MyOwnHashMap caracterCountTable)
    {
        this.caracterCountTable = caracterCountTable;
    }
}
