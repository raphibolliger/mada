package ch.fhnw.mada.bonusaufgabe.helpers;

import java.io.*;

public class FileManager {

    public static String readFile(File file) throws IOException
    {
        byte[] bytes = readByteArray(file);

        return new String(bytes);
    }

    public static void writeFile(String outputPath, String fileName, byte[] bFile) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputPath, fileName));
        fileOutputStream.write(bFile);
        fileOutputStream.close();
    }


    public static byte[] readByteArray(File inputEncodeFile) throws IOException
    {
        byte[] bFile = new byte[(int)inputEncodeFile.length()];
        FileInputStream fileInputStream = new FileInputStream(inputEncodeFile);
        fileInputStream.read(bFile);
        fileInputStream.close();

        return bFile;
    }
}
