package ch.fhnw.mada.bonusaufgabe.helpers;

import java.io.*;

public class FileManager {

    /**
     * Read a file and returns the byte array as a string
     * @param file inputFile
     * @return String with content of file
     * @throws IOException
     */
    public static String readFile(File file) throws IOException
    {
        byte[] bytes = readByteArray(file);
        return new String(bytes);
    }

    /**
     * Write a file whit outputpath and name
     * @param outputPath outputPath
     * @param fileName outputFile name
     * @param bFile byte array
     * @throws IOException
     */
    public static void writeFile(String outputPath, String fileName, byte[] bFile) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputPath, fileName));
        fileOutputStream.write(bFile);
        fileOutputStream.close();
    }

    /**
     * Read a file and returns the byte array
     * @param inputEncodeFile
     * @return
     * @throws IOException
     */
    public static byte[] readByteArray(File inputEncodeFile) throws IOException
    {
        byte[] bFile = new byte[(int)inputEncodeFile.length()];
        FileInputStream fileInputStream = new FileInputStream(inputEncodeFile);
        fileInputStream.read(bFile);
        fileInputStream.close();

        return bFile;
    }
}
