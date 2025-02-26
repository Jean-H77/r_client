package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;

public class NpcDropsInterface extends RSInterface {

    public static void create(TextDrawingArea[] tda) {
        RSInterface rsi = addInterface(57499);

        // Main Interface Sprites
        addBackground(57500, 503, 293, true);
        addSprite(57501, Client.spriteLoader.lookup(1045));

        addText(57502, "Drop Table Viewer", tda, 2, 0xFF9900, true, true);

        addText(57503, "Information", tda, 1, 0xFF9900, true, true);
        addText(57504, "Item", tda, 1, 0xFF9900, true, true);
        addText(57505, "Name", tda, 1, 0xFF9900, true, true);
        addText(57506, "Amount", tda, 1, 0xFF9900, true, true);
        addText(57507, "Rarity", tda, 1, 0xFF9900, true, true);

        drawNpcOnInterface(57508, 50, 1400);
        addText(57509, "", tda, 1, 0xFF9900, true, true);

        int x = (512 - 503) / 2;
        int y = (334 - 293) / 2;

        // Scroll bar for drops
        RSInterface dropsScroll = addInterface(61119);
        dropsScroll.width = 319;
        dropsScroll.height = 222;
        dropsScroll.scrollMax = 250;

        final int maxSlots = 50;
        dropsScroll.children((maxSlots * 5));

        int id = 61200;
        int childId = 0;
        for (int i = 0, yOffset = 0; i < maxSlots; i++, yOffset += 35) {
            addSprite(id, i % 2 == 0 ? Client.spriteLoader.lookup(1063) : Client.spriteLoader.lookup(1064));
            addContainer(id + 1, 0, 1, 1, 0, 0, false, null, null, null, null, null);
            addText(id + 2, "", tda, 0, 0xFF9900, true, true);
            addText(id + 3, "", tda, 0, 0xFF9900, true, true);
            addText(id + 4, "", tda, 0, 0xFF9900, true, true);

            dropsScroll.child(childId++, id, x - 3, yOffset);
            dropsScroll.child(childId++, id + 1, x + 5, 4 + yOffset);
            dropsScroll.child(childId++, id + 2, x + 97, 10 + yOffset);
            dropsScroll.child(childId++, id + 3, x + 192, 10 + yOffset);
            dropsScroll.child(childId++, id + 4, x + 275, 10 + yOffset);
            id += 5;
        }

        setChildren(13, rsi);
        int child = 0;
        rsi.child(child++, 57500, x, y);
        rsi.child(child++, 57501, x + 9, y + 38);
        rsi.child(child++, 57502, x + 252, y + 10);
        rsi.child(child++, 36502, x + 477, y + 9);
        rsi.child(child++, 36503, x + 477, y + 9);
        rsi.child(child++, 57503, x + 80, y + 40);
        rsi.child(child++, 57504, x + 185, y + 40);
        rsi.child(child++, 57505, x + 260, y + 40);
        rsi.child(child++, 57506, x + 355, y + 40);
        rsi.child(child++, 57507, x + 440, y + 40);
        rsi.child(child++, 57509, x + 80, y + 155);
        rsi.child(child++, 61119, x + 158, y + 59);
        rsi.child(child++, 57508, x + 20, y + 55);
    }
}
