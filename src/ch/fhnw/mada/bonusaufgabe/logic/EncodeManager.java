package ch.fhnw.mada.bonusaufgabe.logic;

import ch.fhnw.mada.bonusaufgabe.helpers.FileManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EncodeManager {

    /**
     * Read the two input files and save it to the data object
     * @param inputEncodeFile
     * @param inputEncodeTable
     * @param encodeData
     */
    public void readInputFiles(File inputEncodeFile, File inputEncodeTable, EncodeData encodeData)
    {

        FileManager fileManager = new FileManager();
        try {
            encodeData.setInputTableString(fileManager.readFile(inputEncodeTable));
            encodeData.setInputByteArray(fileManager.readByteArray(inputEncodeFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert the input byte array to a bitstring
     * @param encodeData
     */
    public void convertByteArrayToBitRepresentationString(EncodeData encodeData)
    {
        for(byte b : encodeData.getInputByteArray())
            encodeData.addDecodedBitString(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
    }

    /**
     * Convert the dec_table to a hasmap
     * @param encodeData
     */
    public void convertTableStringToHashMap(EncodeData encodeData)
    {
        String[] splittedDecodeTable = SplitDecodeTable(encodeData.getInputTableString());
        for (String splittedString : splittedDecodeTable)
        {
            String[] blubber =  splittedString.split(":");
            encodeData.getInputDecodeTable().put(blubber[1], blubber[0].charAt(0));
        }
    }

    private String[] SplitDecodeTable(String decodeTable)
    {
        return decodeTable.split("-");
    }

    /**
     * Cut the last bits
     * @param encodeData
     */
    public void cutLastCharacters(EncodeData encodeData)
    {
        String inputBitRepresentation = encodeData.getDecodedBitString();
        inputBitRepresentation = inputBitRepresentation.substring(0, inputBitRepresentation.lastIndexOf("1"));
        encodeData.setDecodedBitString(inputBitRepresentation);
    }

    /**
     * Write the decompressed string tho the data Object
     * @param encodeData
     */
    public void encodeText(EncodeData encodeData)
    {
        int pos = 0;
        String decodedBitString = encodeData.getDecodedBitString();
        for (int i = 0; i < decodedBitString.length(); i++)
        {
            for (String abc : encodeData.getInputDecodeTable().keySet())
            {
                if (decodedBitString.substring(pos, i).equals(abc))
                {
                    encodeData.addEncodedText(encodeData.getInputDecodeTable().get(abc).toString());
                    pos = i;
                }
            }

        }

    }

    /**
     * Write the decompressed textfile
     * @param encodeData
     * @param outputPath
     * @param openAfterDecode
     */
    public void writeTextFile(EncodeData encodeData, String outputPath, boolean openAfterDecode)
    {
        try {
            File temp = new File(outputPath+"/decompress.txt");
            if (temp.exists()) temp.delete();

            FileManager.writeFile(outputPath,"decompress.txt",encodeData.getEncodedText().getBytes());
            if (openAfterDecode)
                Desktop.getDesktop().open(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
