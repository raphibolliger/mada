package ch.fhnw.mada.bonusaufgabe.logic;

import ch.fhnw.mada.bonusaufgabe.helpers.FileManager;
import ch.fhnw.mada.bonusaufgabe.helpers.TreeObject;

import java.io.IOException;
import java.util.ArrayList;

public class DecodeManager {

    public void decode(DecodeData decodeData) throws IOException
    {
        generateDecodeTable(decodeData);
        generateDecodeTree(decodeData);
        generateDecodedFile(decodeData);
    }

    private void generateDecodeTree(DecodeData decodeData)
    {

    }

    private void makeTree(ArrayList<TreeObject> treeObjectsArray, )

    private void generateDecodeTable(DecodeData decodeData) throws IOException
    {
        String inputText = FileManager.readFile(decodeData.getInputFile());

        char[] inputArray = inputText.toCharArray();

        for(char c: inputArray)
        {
            decodeData.getCaracterCountTable().add(c);
        }
    }

    private void generateDecodedFile(DecodeData decodeData)
    {

    }

}
