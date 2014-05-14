package ch.fhnw.mada.bonusaufgabe.logic;

import java.io.*;

public class FileManager {

    public String readFile(File file) throws IOException
    {
        byte[] bFile = new byte[(int)file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();

        return new String(bFile);
    }

    public void writeFile(String outputPath, String fileName, byte[] bFile) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputPath, fileName));
        fileOutputStream.write(bFile);
        fileOutputStream.close();
    }


}