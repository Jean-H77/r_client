package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.ClientConstants;
import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;
import com.decimate.util.ColorConstants;

public class TeleportInterface extends RSInterface{

    public static void create(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addInterface(13400);
        setChildren(15, tab);

        addBackground(13401, 487, 277, true);
        tab.child(0, 13401, 12, 40);

        addConfigButtonWSpriteLoader(13402, 13400, 506, 507, 68, 20, "Select Slayer", 0, 5, 1130);
        tab.child(1, 13402, 125, 73);

        addConfigButtonWSpriteLoader(13403, 13400, 506, 507, 68, 20, "Select Minigames", 1, 5, 1130);
        tab.child(2, 13403, 193, 73);

        addConfigButtonWSpriteLoader(13404, 13400, 506, 507, 68, 20, "Select Minigames", 2, 5, 1130);
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
        tab.child(12, 13414, 27, 94);

        addRectangle(13415, 256, 0x080202, false, 457, 210);
        tab.child(13, 13415, 27, 94);

        RSInterface scroll = addTabInterface(13416);
        scroll.width = 85;
        scroll.height = 206;
        scroll.scrollMax = 500;
        tab.child(14, 13416, 28, 96);

        scroll.totalChildren(60);
        addRectangle(13417, 256, 0x484034, true, 85, 20);
        addRectangle(13418, 256, 0x6b614a, true, 85, 20);

        for(int i = 0; i < 30; i++) {
            scroll.child(i, i % 2 == 0 ? 13417 : 13418, 0, i * 20);

            addClickableText(13419 + i, "Teleport Name", "Select", tda, 1, 0xeb981f, false, true, 130);
            scroll.child(i+30, 13419 + i, 2, i * 20 + 2);
        }
    }
}
