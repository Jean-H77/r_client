package com.decimate;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneratedValues {

    /**
     * Banning by @Mikey96 from Rune-Server.
     */
    /**
     * Modified directory so linuxMasterRace doesn't ??? when they have AppData.
     */
    public static String FilePath() {
        if (Misc.isWindows()) {
            return System.getProperty("user.home") + "\\AppData\\Roaming\\Packages\\data";
        }
        String path = System.getProperty("user.home") + "\\Decimate\\data";
        File file = new File(path + "/saved-data.dat");
            file.mkdirs();
        return path;
    }

    public static String FilePath2() {
        if (Misc.isWindows()) {
            return System.getProperty("user.home") + "\\AppData\\Local\\Packages\\data";
        }
        return System.getProperty("user.home") + "\\Decimate\\data";
    }

    public static String ValueString = "\\saved-data.dat";
    public static String generatedValue = "";

    public static void createValue() {
        File folder = new File(FilePath());
        File folder2 = new File(FilePath2());

        if (folder.exists()) {
            File data = new File(FilePath() + ValueString);
            if (data.exists()) {
                readData(1);
            } else {
                generateValue();
                saveData(1);
            }
            if (!folder2.exists()) {
                folder2.mkdirs();
                saveData(2);
            }
        } else if (folder2.exists()) {
            File data = new File(FilePath2() + ValueString);
            if (data.exists()) {
                readData(2);
            } else {
                generateValue();
                saveData(2);
            }
            if (!folder.exists()) {
                folder.mkdirs();
                saveData(1);
            }
        } else {

            folder.mkdirs();
            folder2.mkdirs();
            generateValue();
            saveData(1);
            saveData(2);
        }
    }

    public static void generateValue() {
        generatedValue = UUID.randomUUID().toString();
    }

    public static void saveData(int loc) {
        try {
            @SuppressWarnings("resource")
            BufferedWriter data = new BufferedWriter(
                    new FileWriter((loc == 1 ? FilePath() : FilePath2()) + ValueString));
            data.write(generatedValue);
            data.newLine();
            data.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(loc);
        }
    }

    public static void readData(int loc) {

        try {

            BufferedReader data = new BufferedReader(
                    new FileReader((loc == 1 ? FilePath() : FilePath2()) + ValueString));
            generatedValue = data.readLine();
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    public static String getHardwareAddress() {
        String firstInterface = null;
        Map<String, String> addressByNetwork = new HashMap<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while(networkInterfaces.hasMoreElements()){
            NetworkInterface network = networkInterfaces.nextElement();

            byte[] bmac = network.getHardwareAddress();
            if(bmac != null){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bmac.length; i++){
                    sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));
                }

                if(sb.toString().isEmpty()==false){
                    addressByNetwork.put(network.getName(), sb.toString());
                    System.out.println("Address = "+sb.toString()+" @ ["+network.getName()+"] "+network.getDisplayName());
                }

                if(sb.toString().isEmpty()==false && firstInterface == null){
                    firstInterface = network.getName();
                }
            }
        }

        if(firstInterface != null){
            return addressByNetwork.get(firstInterface);
        }

        return null;
    }

    public static String getValue() {
        InetAddress ip;
        String value = null;
        try {

            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] value1 = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < value1.length; i++) {
                sb.append(String.format("%02X%s", value1[i], (i < value1.length - 1) ? "-" : ""));
            }
            value = sb.toString();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();

        }
        return value;
    }

}
