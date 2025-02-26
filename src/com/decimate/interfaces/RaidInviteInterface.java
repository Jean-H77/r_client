package com.decimate.interfaces;

import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;

public class RaidInviteInterface extends RSInterface {

    public static void create(TextDrawingArea[] tda) {
        RSInterface tab = RSInterface.addTabInterface(91055);
        tab.height = 334;
        tab.totalChildren(6);
        int totalChildren = 0;

        addRectangle(91061, 50, 0x3F372E, true, 350, 90);
        tab.child(totalChildren++, 91061, 90, 180);

        hoverButton(91056,  tda, 1087, 1087, "Accept", 2, 0xFFA500, "");
        tab.child(totalChildren++, 91056, 145, 226);

        addText(91059, "Accept", tda, 1, 0xff9e09, false, true);
        tab.child(totalChildren++, 91059, 175, 230);

        hoverButton(91057, tda, 1078, 1078, "Decline", 2, 0xFFA500, "");
        tab.child(totalChildren++, 91057, 275, 226);

        addText(91060, "Decline", tda, 1, 0xff9e09, false, true);
        tab.child(totalChildren++, 91060, 305, 230);

        // Player Name + Message
        addText(91058, "A very very long message for testing very long message for testing", tda, 0, 16750899, false, true);
        tab.child(totalChildren++, 91058, 93, 195);

    }
}