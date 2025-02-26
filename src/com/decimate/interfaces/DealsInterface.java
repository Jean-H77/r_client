package com.decimate.interfaces;

import com.decimate.RSInterface;
import com.decimate.util.ColorConstants;

public class DealsInterface extends RSInterface {

    public static void create() {
        RSInterface mainInterface = RSInterface.addInterface(85400);
        mainInterface.totalChildren(12);

        int totalChildren = 0;

        int x = (512 - 487) / 2;
        int y = (334 - 306) / 2;

        RSInterface.addSpriteLoader(85401, 1305);
        mainInterface.child(totalChildren++, 85401, x, y);

        hoverButton(85402, fonts, 1303, 1304, "Daily Deals", 2, 0xFFA500, "");
        mainInterface.child(totalChildren++, 85402, 21, 51);

        addText(85403, "Daily Deals", fonts, 2, 0xff9e09, false, true);
        mainInterface.child(totalChildren++, 85403, 43, 56);

        hoverButton(85404, fonts, 1303, 1304, "Weekly Deals", 2, 0xFFA500, "");
        mainInterface.child(totalChildren++, 85404, 138, 51);

        addText(85405, "Weekly Deals", fonts, 2, 0xff9e09, false, true);
        mainInterface.child(totalChildren++, 85405, 155, 56);

        hoverButton(85406, fonts, 1303, 1304, "Monthly Deals", 2, 0xFFA500, "");
        mainInterface.child(totalChildren++, 85406, 255, 51);

        addText(85407, "Monthly Deals", fonts, 2, 0xff9e09, false, true);
        mainInterface.child(totalChildren++, 85407, 268, 56);

        addText(85408, "Donation Deals", fonts, 2, 0xff9e09, false, true);
        mainInterface.child(totalChildren++, 85408, 205, 22);

        addText(85409, "Expires: ", fonts, 2, 0xff9e09, false, true);
        mainInterface.child(totalChildren++, 85409, 37, 93);

        addText(85410, "00:00 server time", fonts, 1, ColorConstants.WHITE, false, true);
        mainInterface.child(totalChildren++, 85410, 92, 93);

        hoverButton(85399, fonts, 1089, 1090, "Close", 2, 0xFFA500, "");
        mainInterface.child(totalChildren++, 85399, 475, 21);

        mainInterface.child(totalChildren++, 85500, 37, 117);

        RSInterface dealsInterface = RSInterface.addInterface(85500);
        dealsInterface.height = 190;
        dealsInterface.width = 435;
        dealsInterface.scrollMax = 380;

        dealsInterface.totalChildren(25);
        int dealsInterfaceTotalChidlren = 0;

        int totalAmountOfDeals = 5;

        for (int i =0; i < totalAmountOfDeals; i++) {
            RSInterface.addSpriteLoader(85501 + (i * 10), 1306);
            dealsInterface.child(dealsInterfaceTotalChidlren++, 85501 + (i * 10), 3, 16 + (i * 75));

            addPercentageBar(85502 + (i * 10), 280, 100, 0x7f7f7f, 0x47960b, 14, false, true, true);
            dealsInterface.child(dealsInterfaceTotalChidlren++, 85502 + (i * 10), 5, 40 + (i * 75));

            RSInterface.addText(85503 + (i * 10), "Reach 50$", fonts, 1, ColorConstants.WHITE, false, true);
            dealsInterface.child(dealsInterfaceTotalChidlren++, 85503 + (i * 10), 5, 19 + (i * 75));

            RSInterface.addToItemGroup(85504 + (i * 10), 3, 1, 5, 5, false, null);
            dealsInterface.child(dealsInterfaceTotalChidlren++, 85504 + (i * 10), 295, 27 + (i * 75));

            RSInterface.addText(85505 + (i * 10), "Rewards", fonts, 1, ColorConstants.WHITE, false, true);
            dealsInterface.child(dealsInterfaceTotalChidlren++, 85505 + (i * 10), 312, 19 + (i * 75));
        }
    }
}
