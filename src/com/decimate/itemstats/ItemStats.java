package com.decimate.itemstats;

import com.decimate.ItemDef;
import com.decimate.signlink;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ItemStats {

    public static final int STAB = 0;
    public static final int SLASH = 1;
    public static final int CRUSH = 2;
    public static final int MAGIC = 3;
    public static final int RANGED = 4;

    public static ItemStats[] itemstats = new ItemStats[ItemDef.totalItems > 0 ? ItemDef.totalItems : 25000];
    private static int readType = 0;
    public int itemId;
    public int[] attackBonus;
    public int[] defenceBonus;
    public double prayerBonus;
    public double critChance;
    public double strengthBonus;
    public double critDamage;
    public double dropRateBonus;
    public double doubleDropRateBonus;
    public int healAmount;
    public int type;
    public int[][] rewards;
    public String information;

    public ItemStats(int id, int typeOfStat) {
        this.itemId = id;
        this.attackBonus = new int[]{0, 0, 0, 0, 0};
        this.defenceBonus = new int[]{0, 0, 0, 0, 0};
        this.prayerBonus = 0;
        this.strengthBonus = 0;
        this.dropRateBonus = 0;
        this.critChance = 0;
        this.critDamage = 0;
        this.doubleDropRateBonus = 0;
        this.healAmount = 0;
        this.type = typeOfStat;
    }

    public static void readDefinitions() {
        try {
            File file = new File(signlink.findcachedir() + "itemstats.dat");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("[STATS]")) {
                    readType = 1;
                    continue;
                }
                if (readType == 1) {
                    String[] data = line.split(" ");
                    int slot = 0;
                    int id = Integer.parseInt(data[slot++]);
                    itemstats[id] = new ItemStats(id, readType);
                    for (int i = 0; i < 5; ++i) {
                        itemstats[id].attackBonus[i] = Integer.parseInt(data[slot++]);
                    }
                    for (int i = 0; i < 5; ++i) {
                        itemstats[id].defenceBonus[i] = Integer.parseInt(data[slot++]);
                    }
                    itemstats[id].strengthBonus = Double.parseDouble(data[slot++]);
                    itemstats[id].critChance = Double.parseDouble(data[slot++]);
                    itemstats[id].prayerBonus = Double.parseDouble(data[slot++]);
                    itemstats[id].critDamage = Double.parseDouble(data[slot++]);
                    itemstats[id].dropRateBonus = Double.parseDouble(data[slot++]);
                    itemstats[id].doubleDropRateBonus = Double.parseDouble(data[slot++]);
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
