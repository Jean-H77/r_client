package com.decimate.itemstats;

import com.decimate.Configuration;
import com.decimate.signlink;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ItemDescription {

    public static Map<Integer, String> itemDescriptions;

    public static void load() {
        if(Configuration.localHost) {
            itemDescriptions = new HashMap<>();
            return;
        }
        System.out.println("Loading");
        try {
            itemDescriptions = new HashMap<>();
            File file = new File(signlink.findcachedir() + "itemdescriptions.json");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("{") || line.contains("}"))
                    continue;
                line = line.replaceAll("\"", "").replaceAll("    ", "");
                String[] parts = line.split(":");
                int id = Integer.parseInt(parts[0]);
                String description = parts[1].substring(1);
                itemDescriptions.put(id, description);
            }
            System.out.println("Loaded " + itemDescriptions.size() + " Item Descriptions!");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        File file = new File(signlink.findcachedir() + "itemDescriptions.json");
//        try {
//
//            String json = new String(Files.readAllBytes(file.toPath()));
//            ObjectMapper objectMapper = new ObjectMapper();
//            Map<Integer, String> map = objectMapper.readValue(json, Map.class);
//            itemDescriptions = map;
//            System.out.println("Loaded " + itemDescriptions.size() + " Item Descriptions!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error loading Item Descriptions!");
//        }
    }

    public static String getItemDescription(int id) {
        if (itemDescriptions.containsKey(id))
            return itemDescriptions.get(id);
        return null;
    }

}
