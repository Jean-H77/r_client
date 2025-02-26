package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;

public class ReferralsTrackingInterface extends RSInterface {

    public static void create() {
        RSInterface rsi = addInterface(62000);
        rsi.totalChildren(2);

        int child = 0;
        int x = (512 - 481) / 2;
        int y = (334 - 321) / 2;
        addSprite(62001, Client.spriteLoader.lookup(1018));
        rsi.child(child++, 62001, x, y);
        rsi.child(child++, 62002, x + 20, y + 30);

        RSInterface rsText = addInterface(62002);
        rsText.totalChildren(10);
        rsText.width = 430;
        rsText.height = 250;
        rsText.scrollMax = 500;

        int rsTextChild = 0;

        for (int i =0; i < 10; i++) {
            RSInterface.addText(62003 + i, "", fonts, 2, 0xff981f, false, true);
            rsText.child(rsTextChild++, 62003 + i, x, y + (i * 25));
        }
    }
}
