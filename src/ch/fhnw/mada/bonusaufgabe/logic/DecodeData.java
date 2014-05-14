package ch.fhnw.mada.bonusaufgabe.logic;

import java.io.File;
import java.util.Map;

public class DecodeData {

    private File inputFile;
    private String outputPath;
    private Map decodeTable;

    public DecodeData(File inputFile, String outputPath)
    {
        this.inputFile = inputFile;
        this.outputPath = outputPath;
    }
}
