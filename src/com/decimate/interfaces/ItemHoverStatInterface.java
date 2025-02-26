package com.decimate.interfaces;

import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;

public class ItemHoverStatInterface extends RSInterface {

    public static void create(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(94000);
        addOutlinedColorBox(94001, 0x534a40, 145, 145, 200);
        addText(94002, "Item name", tda, 0, 0xff9040, true, true);
        addText(94003, "Attack", tda, 0, 0xff9040, false, true);
        addText(94004, "Defence", tda, 0, 0xff9040, false, true);
        addText(94005, "Stab:\\nSlash:\\nCrush:\\nMagic:\\nRange:", tda, 0, 0xff9040, false, true);
        addText(94006, "0\\n0\\n0\\n0\\n0", tda, 0, 0xffffff, true, true);
        addText(94007, "0\\n0\\n0\\n0\\n0", tda, 0, 0xffffff, true, true);
        addText(94008, "Other Bonuses", tda, 0, 0xff9040, false, true);
        addText(94009, "Strength Bonus:\\nPrayer Bonus:", tda, 0, 0xff9040, false, true);
        addText(94010, "0\\n0", tda, 0, 0xffffff, true, true);
        addLine(94011, 0xB7B7B7, 32);
        addLine(94012, 0xB7B7B7, 41);
        addLine(94013, 0xffffff, 74);
        int x = 8, y = 8;
        tab.totalChildren(13);
        tab.child(0, 94001, x, y);
        tab.child(1, 94002, 72 + x, 2 + y);
        tab.child(2, 94003, 48 + x, 16 + y);
        tab.child(3, 94004, 93 + x, 16 + y);
        tab.child(4, 94005, 2 + x, 30 + y);
        tab.child(5, 94006, 64 + x, 30 + y);
        tab.child(6, 94007, 114 + x, 30 + y);
        tab.child(7, 94008, 2 + x, 97 + y);
        tab.child(8, 94009, 2 + x, 110 + y);
        tab.child(9, 94010, 110 + x, 110 + y);
        tab.child(10, 94011, 48 + x, 27 + y);
        tab.child(11, 94012, 93 + x, 27 + y);
        tab.child(12, 94013, 2 + x, 109 + y);

        tab = addInterface(94020);
        addOutlinedColorBox(94021, 0x534a40, 145, 16, 200);
        addText(94022, "Press @gre@CTRL @whi@to view stats", tda, 0, 0xB7B7B7, false, true);
        tab.totalChildren(3);
        tab.child(0, 94021, x, y);
        tab.child(1, 94002, 72 + x, 2 + y);
        tab.child(2, 94022, 2 + x, 14 + y);

        tab = addInterface(94085);
        addOutlinedColorBox(94086, 0x534a40, 145, 100, 200);
        addText(94087, "Should list info about the item", tda, 0, 0xffffff, false, true);
        x = 8;
        y = 8;
        tab.totalChildren(4);
        tab.child(0, 94086, x, y);
        tab.child(1, 94002, 72 + x, 2 + y);
        tab.child(2, 94087, 4 + x, 17 + y);
        tab.child(3, 94088, x, 75);

        RSInterface items = addInterface(94088);
        itemGroup(94089, 40, 1, 10, 1, false, false);
        RSInterface.interfaceCache.get(94089).parentID = 94088;
        items.totalChildren(1);
        items.child(0, 94089, 0, 0);
        items.width = 145;
        items.height = 50;
    }

    public static void addOutlinedColorBox(int id, int color, int width, int height, int transparency) {
        RSInterface tab = addInterface(id);
        tab.width = width;
        tab.height = height;
        tab.color = color;
        tab.type = 19;
        tab.opacity = (byte) transparency;
        tab.contentType = 0;
    }
}
