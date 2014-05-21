package ch.fhnw.mada.bonusaufgabe.logic;

import java.util.HashMap;


/**
 * Date object for encoding data
 */
public class EncodeData {

    private byte[] inputByteArray;
    private String outputPath;
    private String decodedBitString = "";
    private String encodedText = "";
    private String inputTableString;
    private HashMap<String, Character> inputDecodeTable;

    public String getEncodedText()
    {
        return encodedText;
    }

    public void addEncodedText(String encodedText)
    {
        this.encodedText += encodedText;
    }

    public EncodeData()
    {
        inputDecodeTable = new HashMap<String, Character>();
    }

    public String getInputTableString()
    {
        return inputTableString;
    }

    public void setInputTableString(String inputTableString)
    {
        this.inputTableString = inputTableString;
    }

    public byte[] getInputByteArray()
    {
        return inputByteArray;
    }

    public void setInputByteArray(byte[] inputByteArray)
    {
        this.inputByteArray = inputByteArray;
    }

    public String getOutputPath()
    {
        return outputPath;
    }

    public void setOutputPath(String outputPath)
    {
        this.outputPath = outputPath;
    }

    public String getDecodedBitString()
    {
        return decodedBitString;
    }

    public void addDecodedBitString(String decodedBitString)
    {
        this.decodedBitString += decodedBitString;
    }

    public void setDecodedBitString(String decodedBitString)
    {
        this.decodedBitString = decodedBitString;
    }

    public HashMap<String, Character> getInputDecodeTable()
    {
        return inputDecodeTable;
    }

    public void setInputDecodeTable(HashMap<String, Character> inputDecodeTable) {
        this.inputDecodeTable = inputDecodeTable;
    }
}
