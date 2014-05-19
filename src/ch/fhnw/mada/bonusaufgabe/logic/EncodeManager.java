package ch.fhnw.mada.bonusaufgabe.logic;

import ch.fhnw.mada.bonusaufgabe.helpers.FileManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by raphi on 19.05.14.
 */
public class EncodeManager {


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

    public void convertByteArrayToBitRepresentationString(EncodeData encodeData)
    {
        for(byte b : encodeData.getInputByteArray())
            encodeData.addDecodedBitString(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
    }

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

    public void cutLastCharacters(EncodeData encodeData)
    {
        String inputBitRepresentation = encodeData.getDecodedBitString();
        inputBitRepresentation = inputBitRepresentation.substring(0, inputBitRepresentation.lastIndexOf("1"));
        encodeData.setDecodedBitString(inputBitRepresentation);
    }

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


    public void writeTextFile(EncodeData encodeData)
    {
        try {
            FileManager.writeFile("/Users/raphi/Desktop","blubber.txt",encodeData.getEncodedText().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
