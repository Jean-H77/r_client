package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;

public class RandomEventInterface extends RSInterface {

    public static void create() {
        RSInterface widget = addInterface(33300);
        addSprite(33301, Client.spriteLoader.lookup(1190));
        addText(33302, "Click the 'Abyssal Whip'", fonts, 2, 0xFF981F, true, true);
        addText(33303, "1:00", fonts, 0, 0xFF981F, true, true);
        addText(33304,
                "If you click the wrong item or the time depletes to 0,\\nYou will be teleported to a new location.",
                fonts, 0, 0xFF981F, true, true);
        setChildren(7, widget);
        int totalChildren = 0;
        setBounds(33301, 115, 96, totalChildren++, widget);
        setBounds(33302, 253, 105, totalChildren++, widget);
        setBounds(33303, 375, 105, totalChildren++, widget);
        setBounds(33304, 255, 190, totalChildren++, widget);
        setBounds(33310, 180, 140, totalChildren++, widget);
        setBounds(33313, 240, 140, totalChildren++, widget);
        setBounds(33316, 300, 140, totalChildren, widget);

        for (int i = 0; i < 9; i += 3) {
            RSInterface option = addInterface(33310 + i);
            addToItemGroup(33311 + i, 1, 1, 0, 0, false, "", "", "");
            hoverButton(33312 + + i, fonts, 1189, 1189, "Select", 2, 0xFFA500, "");
            //addButton(33312 + i, 1, "interfaces/antibot/image", "Select");
            setChildren(2, option);
            setBounds(33311 + i, 0, 0, 0, option);
            setBounds(33312 + i, 0, 0, 1, option);
        }
    }
}