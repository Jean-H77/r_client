package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.ClientConstants;
import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;
import com.decimate.util.ColorConstants;

public class TeleportInterface extends RSInterface{

    public static void create(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(13400);
        setChildren(26, tab);

        addBackground(13401, 487, 277, true);
        tab.child(0, 13401, 12, 40);

        addConfigButtonWSpriteLoader(13402, 13400, 506, 507, 68, 20, "Select Slayer", 0, 5, 1130);
        tab.child(1, 13402, 125, 73);

        addConfigButtonWSpriteLoader(13403, 13400, 506, 507, 68, 20, "Select Minigames", 1, 5, 1130);
        tab.child(2, 13403, 193, 73);

        addConfigButtonWSpriteLoader(13404, 13400, 506, 507, 68, 20, "Select Bosses", 2, 5, 1130);
        tab.child(3, 13404, 261, 73);

        addConfigButtonWSpriteLoader(13405, 13400, 506, 507, 68, 20, "Select Miscellaneous", 3, 5, 1130);
        tab.child(4, 13405, 329, 73);

        addText(13406, "Teleport Menu", 0xff981f, false, true, 52, 2);
        tab.child(5, 13406, 215, 50);

        addCloseButton(13407, 13408, 13409);
        tab.child(6, 13407, 472, 47);
        tab.child(7, 13408, 472, 47);

        addText(13410, "Slayer", 0xff981f, false, true, 52, 1);
        tab.child(8, 13410, 142, 75);

        addText(13411, "Minigames", 0xff981f, false, true, 52, 1);
        tab.child(9, 13411, 197, 75);

        addText(13412, "Bosses", 0xff981f, false, true, 52, 1);
        tab.child(10, 13412, 274, 75);

        addText(13413, "Misc", 0xff981f, false, true, 52, 1);
        tab.child(11, 13413, 349, 75);

        addRectangle(13414, 256, 0x554b41, true, 457, 210);
        addRectangle(13415, 256, 0x080202, false, 457, 210);
        tab.child(12, 13414, 27, 94);
        tab.child(13, 13415, 27, 94);

        addRectangle(13416, 256, 0x484034, true, 149, 24);
        addRectangle(13417, 256, 0x080202, false, 149, 24);
        tab.child(14, 13416, 29, 96);
        tab.child(15, 13417, 29, 96);

        addRectangle(13418, 256, 0x484034, true, 149, 145);
        addRectangle(13419, 256, 0x080202, false, 149, 145);
        tab.child(16, 13418, 29, 121);
        tab.child(17, 13419, 29, 121);

        addRectangle(13420, 256, 0x484034, true, 302, 60);
        addRectangle(13421, 256, 0x080202, false, 302, 60);
        tab.child(18, 13420, 180, 242);
        tab.child(19, 13421, 180, 242);

        addHoverButtonWSpriteLoader(13422, 726, 150, 44, "Teleport", 0, 13423, 1);
        addHoveredImageWSpriteLoader(13423, 727, 150, 44, 13424);
        tab.child(20, 13422, 29, 267);
        tab.child(21, 13423, 29, 267);

        addText(13425, "Selections", 0xff981f, false, true, 52, 2);
        tab.child(22, 13425, 71, 101);

        addText(13426, "Teleport!", 0x7cf344, false, true, 52, 2);
        tab.child(23, 13426, 75, 277);

        RSInterface teleScroll = addTabInterface(13427);
        teleScroll.width = 130;
        teleScroll.height = 141;
        teleScroll.scrollMax = 500;
        tab.child(24, 13427, 30, 123);

        teleScroll.totalChildren(60);
        addRectangle(13428, 256, 0x484034, true, 130, 20);
        addRectangle(13429, 256, 0x6b614a, true, 130, 20);

        for(int i = 0; i < 30; i++) {
            teleScroll.child(i, i % 2 == 0 ? 13428 : 13429, 0, i * 20);

            addClickableText(13430 + i, "Teleport Name", "Select", tda, 1, 0xeb981f, false, true, 130);
            teleScroll.child(i+30, 13430 + i, 2, i * 20 + 2);
        }

        RSInterface lootScroll = addTabInterface(13460);
        lootScroll.width = 273;
        lootScroll.height = 56;
        lootScroll.scrollMax = 500;
        lootScroll.totalChildren(1);
        tab.child(25, 13460, 191, 244);

        addToItemGroup(13461, 6, 6, 15, 5, false, null, null, null);
        lootScroll.child(0, 13461, 0, 0);

        for(int i = 0; i < 36; i++) {
            RSInterface.interfaceCache.get(13461).inv[i] = 4152;
            RSInterface.interfaceCache.get(13461).invStackSizes[i] = 1;
        }
    }
}
