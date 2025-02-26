package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;

public class CosmeticOverridesInterface extends RSInterface {

    public static void create(TextDrawingArea[] tda) {
        RSInterface rsi = RSInterface.addTabInterface(57600);
        addSprite(57601, Client.spriteLoader.lookup(1137));
        RSInterface.addToItemGroup(57602, 3, 6, 9, 7, true, new String[]{"Remove-Cosmetic Override"});

        RSInterface itemEquipment = RSInterface.interfaceCache.get(57602);
        itemEquipment.spritesY[0] = itemEquipment.spritesY[0] - 39;
        itemEquipment.spritesX[0] = itemEquipment.spritesX[0] + 39;

        itemEquipment.spritesX[1] = itemEquipment.spritesX[1] - 39;
        itemEquipment.spritesX[2] = itemEquipment.spritesX[2] - 39;

        itemEquipment.spritesX[13] = itemEquipment.spritesX[12] + 42;
        itemEquipment.spritesY[13] = itemEquipment.spritesY[12] - 154;

        RSInterface.addChar(57603);
        RSInterface.addText(57604, "Cosmetic Overrides", tda, 2, 0xff981f, true, true);

        addHoverButtonWSpriteLoader(57609, 737, 16, 16, "Close", -1, 57610, 1);
        addHoveredImageWSpriteLoader(57610, 738, 16, 16, 57611);

        rsi.totalChildren(6);
        int child = 0;
        rsi.child(child++, 57601, 100, 10);
        rsi.child(child++, 57602, 315, 100);
        rsi.child(child++, 57603, 150, 200);
        rsi.child(child++, 57604, 285, 20);
        rsi.child(child++, 57609, 427, 19);
        rsi.child(child++, 57610, 427, 19);
    }
}

