package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;

public class ItemUpgradeInterface extends RSInterface {

    public static void create(TextDrawingArea[] tda) {
        RSInterface rsi = addTabInterface(62890);

        addSprite(62891, Client.spriteLoader.lookup(1061));
        addText(62892, "Item Upgrade", tda, 2, 0xFF9900, true, true);

        addHDSprite(62893, Client.spriteLoader.lookup(1062));
        addHDSprite(62894, Client.spriteLoader.lookup(1051));
        addHDSprite(62895, Client.spriteLoader.lookup(1059));

        addConfigButtonWSpriteLoader(62904, 62890, 1054, 1055, 143, 23, "Select Weaponry", 0, 5, 1090);
        addConfigButtonWSpriteLoader(62905, 62890, 1054, 1055, 143, 23, "Select Armoury", 1, 5, 1090);
        addConfigButtonWSpriteLoader(62906, 62890, 1054, 1055, 143, 23, "Select Miscellaneous", 2, 5, 1090);

        addText(62896, "Weaponry", tda, 0, 0xFF9900, false, true);
        addText(62897, "Armoury", tda, 0, 0xFF9900, false, true);
        addText(62898, "Miscellaneous", tda, 0, 0xFF9900, false, true);

        addText(62899, "Upgradeables", tda, 2, 0xFF9900, true, true);

        addContainer(62900, 0, 1, 1, 1, 1, false, null, null, null, null, null);
        addContainer(62901, 0, 1, 1, 1, 1, false, null, null, null, null, null);
        addContainer(62902, 0, 1, 1, 1, 1, false, null, null, null, null, null);
        addContainer(62903, 0, 1, 1, 1, 1, false, null, null, null, null, null);

        addHDSprite(62907, Client.spriteLoader.lookup(1057));
        addHDSprite(62908, Client.spriteLoader.lookup(1060));

        addText(62909, "", tda, 0, 0x00FF00, true, true);
        addText(62910, "", tda, 0, 0x00FF00, true, true);

        addHoverButtonWSpriteLoader(62911, 1052, 90, 25, "Upgrade Item!", -1, 62912, 1);
        addHoveredImageWSpriteLoader(62912, 1053, 90, 25, 62913);

        addText(62914, "Upgrade Item!", tda, 0, 0xFF9900, true, true);

        int x = (512 - 319) / 2;
        int y = (334 - 286) / 2;

        RSInterface upgradeablesScroll = addTabInterface(62950);
        upgradeablesScroll.width = 124;
        upgradeablesScroll.height = 134;
        upgradeablesScroll.scrollMax = 139;
        upgradeablesScroll.children(1);

        addContainer(62951, 0, 3, 14, 10, 10, false, "Upgrade", null, null, null, null);
        upgradeablesScroll.child(0, 62951, 5, 2);

        rsi.children(26);

        rsi.child(0, 62891, x, y);
        rsi.child(1, 62892, x + 160, y + 10);
        rsi.child(2, 36502, x + 293, y + 9);
        rsi.child(3, 36503, x + 293, y + 9);

        rsi.child(4, 62904, x + 10, y + 39);
        rsi.child(5, 62905, x + 10, y + 62);
        rsi.child(6, 62906, x + 10, y + 85);

        rsi.child(7, 62893, x + 16, y + 42);
        rsi.child(8, 62894, x + 16, y + 65);
        rsi.child(9, 62895, x + 16, y + 88);

        rsi.child(10, 62896, x + 35, y + 45);
        rsi.child(11, 62897, x + 35, y + 68);
        rsi.child(12, 62898, x + 35, y + 91);

        rsi.child(13, 62899, x + 80, y + 115);

        rsi.child(14, 62950, x + 10, y + 135);

        rsi.child(15, 62900, x + 167, y + 90);
        rsi.child(16, 62901, x + 217, y + 90);
        rsi.child(17, 62902, x + 267, y + 90);
        rsi.child(18, 62903, x + 217, y + 200);

        rsi.child(19, 62907, x + 168, y + 49);
        rsi.child(20, 62908, x + 168, y + 68);

        rsi.child(21, 62909, x + 238, y + 50);
        rsi.child(22, 62910, x + 238, y + 69);

        rsi.child(23, 62911, x + 189, y + 242);
        rsi.child(24, 62912, x + 189, y + 242);
        rsi.child(25, 62914, x + 233, y + 249);
    }
}
