package com.decimate.security;

import com.decimate.Configuration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class Decryption {

    public static final String TEMP = System.getProperty("user.home") + "/AppData/Local/Decimate/Temp/";

    public static void init() {
        File tempDir = new File(TEMP);
        if (!tempDir.exists())
            tempDir.mkdirs();
        else {
            for (File file : tempDir.listFiles())
                file.delete();
        }
    }

    public static RandomAccessFile decryptRAF(RandomAccessFile file, String name) {
        if (Configuration.localHost)
            return file;
        try {
            byte[] data = new byte[(int) file.length()];
            file.read(data);

            java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(data.length);
            buffer.put(data, 17, data.length - 17);

            File temp = new File(TEMP+(long)(data.length*2*Math.random()*1234));

            try (FileOutputStream os = new FileOutputStream(temp)) {
                os.write(buffer.array());
            }
            RandomAccessFile toReturn = new RandomAccessFile(temp, "rw");
            temp.delete();
            return toReturn;
        } catch (Exception e) {
            System.err.println("Error decrypting file: "+name);
            e.printStackTrace();
            return null;
        }
    }


    public static void writeTempFile(String name, byte[] data) {
        try {
            Files.write(new File(TEMP+name).toPath(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getTempFile(String name) {
        File temp = new File(TEMP+name);
        if (!temp.exists())
            return null;
        return temp;
    }
}
