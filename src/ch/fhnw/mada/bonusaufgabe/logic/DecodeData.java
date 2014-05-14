package ch.fhnw.mada.bonusaufgabe.logic;

import ch.fhnw.mada.bonusaufgabe.helpers.MyOwnHashMap;

import java.io.File;
import java.util.HashMap;

public class DecodeData {

    private File inputFile;
    private String outputPath;
    private MyOwnHashMap caracterCountTable;
    private HashMap<String, Integer> decodingTable;

    public DecodeData(File inputFile, String outputPath)
    {
        this.inputFile = inputFile;
        this.outputPath = outputPath;
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
