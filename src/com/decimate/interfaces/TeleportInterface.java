package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;
import com.decimate.util.ColorConstants;

public class TeleportInterface {

    public static void create(TextDrawingArea[] tda) {
        // Main Interface ID, and calling the addinterface method
        RSInterface tab = RSInterface.addInterface(13400);

        // Scroll bar Interface ID.
        RSInterface scrollTableft = RSInterface.addInterface(13430);
        int xOffset = 4;
        int yOffset = -8;

        // Scroll bar for drops
        RSInterface scrollTabright = RSInterface.addInterface(13498);

        // Main Interface Sprites
        RSInterface.addSprite(13401, Client.spriteLoader.lookup(1034));

        // Interface Text
        RSInterface.addText(13402, "Preview", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(13403, "Description", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(13404, "Drops", tda, 2, 0xFF9900, true, true);
        RSInterface.addText(13405, "Boss Names", tda, 2, 0xFF9900, true, true);

        // Category Buttons
        RSInterface.addButtonWSpriteLoader(13406, 1035, 1036, "Select", 71, 21);
        RSInterface.addButtonWSpriteLoader(13407, 1035, 1036, "Select", 71, 21);
        RSInterface.addButtonWSpriteLoader(13408, 1035, 1036, "Select", 71, 21);
        RSInterface.addButtonWSpriteLoader(13409, 1035, 1036, "Select", 71, 21);
        RSInterface.addButtonWSpriteLoader(13410, 1035, 1036, "Select", 71, 21);
        RSInterface.addButtonWSpriteLoader(13411, 1035, 1036, "Select", 71, 21);

        // Category Text
        RSInterface.addText(13412, "Monsters", tda, 0, 0xFF9900, true, true);
        RSInterface.addText(13413, "Bosses", tda, 0, 0xFF9900, true, true);
        RSInterface.addText(13414, "Global Bosses", tda, 0, 0xFF9900, true, true);
        RSInterface.addText(13415, "Skilling", tda, 0, 0xFF9900, true, true);
        RSInterface.addText(13416, "Minigames", tda, 0, 0xFF9900, true, true);
        RSInterface.addText(13417, "Ultra Bosses", tda, 0, 0xFF9900, true, true);

        // Description Text
        RSInterface.addText(13418, "Name:", tda, 1, 0xFF9900, false, true);
        RSInterface.addText(13419, "Hitpoints:", tda, 1, 0xFF9900, false, true);
        RSInterface.addText(13420, "Weakness:", tda, 1, 0xFF9900, false, true);
        RSInterface.addText(13421, "Attack Styles:", tda, 1, 0xFF9900, false, true);
        RSInterface.addText(13422, "Difficulty:", tda, 1, 0xFF9900, false, true);
        RSInterface.addText(13491, "Requirements:", tda, 1, 0xFF9900, false, true);

        // Teleport Button
        RSInterface.hoverButton(13423, tda, 1037, 1038, "Teleport2", 0, 16750623, "");

        // skip button
        RSInterface.hoverButton(13501, tda, 1037, 1038, "bypass", 0, 16750623, "");

        // Description Text
        RSInterface.addText(13424, "1:", tda, 1, 0xffff01, false, true);
        RSInterface.addText(13425, "2:", tda, 1, 0xffff01, false, true);
        RSInterface.addText(13426, "3:", tda, 1, 0xffff01, false, true);
        RSInterface.addText(13427, "4:", tda, 1, 0xffff01, false, true);
        RSInterface.addText(13428, "5:", tda, 1, 0xffff01, false, true);
        RSInterface.addText(13492, "6:", tda, 1, 0xffff01, false, true);

        RSInterface.addText(13429, "Teleport", tda, 2, 0xffff01, true, true);

        RSInterface.addText(13502, "Bypass Requirement", tda, 1, ColorConstants.SLATE_BLUE, true, true);


        RSInterface.addToItemGroup(13499, 8, 8, 5, 5, false, null);

        RSInterface.drawNpcOnInterface(13500, 50, 1400);

        // Positioning and Interface child
        RSInterface.setChildren(36, tab);
        int totalChildren = 0;
        tab.child(totalChildren++, 13401, 5, 5);
        tab.child(totalChildren++, 13402, 213, 63);
        tab.child(totalChildren++, 13403, 403, 63);
        tab.child(totalChildren++, 13404, 208, 239);
        tab.child(totalChildren++, 13405, 83, 45);

        tab.child(totalChildren++, 13406, 21, 12);
        tab.child(totalChildren++, 13407, 100, 12);
        tab.child(totalChildren++, 13408, 179, 12);
        tab.child(totalChildren++, 13409, 258, 12);
        tab.child(totalChildren++, 13410, 337, 12);
        tab.child(totalChildren++, 13411, 417, 12);

        tab.child(totalChildren++, 13412, 54, 18);
        tab.child(totalChildren++, 13413, 132, 18);
        tab.child(totalChildren++, 13414, 213, 18);
        tab.child(totalChildren++, 13415, 293, 18);
        tab.child(totalChildren++, 13416, 372, 18);
        tab.child(totalChildren++, 13417, 452, 18);

        // Scrollbar positioning
        tab.child(totalChildren++, 13430, 5 + xOffset, 77 + yOffset);

        // Description Text Positioning
        tab.child(totalChildren++, 13418, 334, 85);
        tab.child(totalChildren++, 13419, 334, 100);
        tab.child(totalChildren++, 13420, 334, 115);
        tab.child(totalChildren++, 13421, 334, 130);
        tab.child(totalChildren++, 13422, 334, 145);
        tab.child(totalChildren++, 13491, 334, 160);

        // Teleport Button Positioning
        tab.child(totalChildren++, 13423, 153, 203);

        // Text Input fields Positioning
        tab.child(totalChildren++, 13424, 372, 85);
        tab.child(totalChildren++, 13425, 389, 100);
        tab.child(totalChildren++, 13426, 396, 115);
        tab.child(totalChildren++, 13427, 416, 130);
        tab.child(totalChildren++, 13428, 394, 145);
        tab.child(totalChildren++, 13492, 416, 160);
        tab.child(totalChildren++, 13429, 218, 208);
        tab.child(totalChildren++, 13498, 148, 260);

        tab.child(totalChildren++, 13500, 148, 75);

        tab.child(totalChildren++, 13501, 350, 203);
        tab.child(totalChildren++, 13502, 416, 206);

        // Scoll bar size, witdh and scrolling size.
        scrollTableft.width = 113;
        scrollTableft.height = 247;
        scrollTableft.scrollMax = 450;

        // Scroll bar size, width and scrolling size.
        scrollTabright.width = 334;
        scrollTabright.height = 60;
        scrollTabright.scrollMax = 200;

        int npcList = 50;
        int y = 1;

        for (int i = 0; i < npcList; i++) {
            RSInterface.hoverButton(13431 + i, tda, 1039, 1040, "Teleport Name", 0, 16750623, "Teleport");
        }

        RSInterface.setChildren(npcList, scrollTableft);

        for (int i = 0; i < npcList; i++) {
            scrollTableft.child(i, 13431 + i, 0, y);
            y += 23;
        }

        RSInterface.setChildren(1, scrollTabright);

        scrollTabright.child(0, 13499, 18, 5);
    }
}
