package com.decimate.interfaces;

import com.decimate.*;

public class Townboard extends RSInterface {

    public static void create(TextDrawingArea[] tda) {
        RSInterface rsi = addInterface(90000);
        rsi.totalChildren(18);

        addSpriteLoader(90001, 1342);
        rsi.child(90001, 6,1);

        addText(90002, "Town Board", 0xff981f, false, true, 52, 2);
        rsi.child(90002, 222,11);

        addCloseButtonSmall(90003,90004,90005);
        rsi.child(90003, 482,10);
        rsi.child(90004, 482,10);

        addConfigButtonWSpriteLoader(90006, 90000, 1344, 1340, 100, 15, "Toggle", 0, 5, 899);
        rsi.child(90006, 16, 10);

        addText(90008, "Challenge Mode", 0xffa900, false, true, 52, 0);
        rsi.child(90008, 34, 13);

        addPercentageBar(90009, 239, 200, 0x373128, 0x962e00, 23, false, false, false);
        RSInterface.interfaceCache.get(90009).percentageCompleted = 10;
        rsi.child(90009, 19, 41);

        addText(90010, "0", 0xffffff, true, true, 52, 0);
        rsi.child(90010, 30, 48);

        addText(90011, "1", 0xffffff, true, true, 52, 0);
        rsi.child(90011, 245, 48);

        addText(90012, "10/200", 0xffa900, true, true, 52, 2);
        rsi.child(90012, 143, 46);

        addText(90013, "25/25,000", 0xffa900, false, true, 52, 0);
        rsi.child(90013, 285, 46);

        addHoverButtonWSpriteLoader(90014, 1345, 58, 18, "View Levels", -1, 90015, 1);
        addHoveredImageWSpriteLoader(90015, 1346, 58, 18, 90016);
        rsi.child(90014, 369, 45);
        rsi.child(90015, 369, 45);

        addText(90017, "Levels", 0xffa900, false, true, 52, 0);
        rsi.child(90017, 382, 48);

        addHoverButtonWSpriteLoader(90018, 1332, 67, 18, "Exchange", -1, 90019, 1);
        addHoveredImageWSpriteLoader(90019, 1334, 67, 18, 90020);
        rsi.child(90018, 431, 45);
        rsi.child(90019, 431, 45);

        addText(90021, "Exchange", 0xffa900, false, true, 52, 0);
        rsi.child(90021, 441, 48);

        RSInterface scroll = addTabInterface(90022);
        scroll.width = 477;
        scroll.height = 254;
        scroll.scrollMax = 475;
        rsi.child(90022, 5, 72);

        scroll.totalChildren(48);

        int y = 4;
        int x = 10;
        for(int i = 0; i < 6; i++) {
            addHdButton(90023+i, 1339, "Select");

            if(i==3) {
                y += 237;
                x = 10;
            }
            scroll.child(90023+i,x,y);

            addText(90029+i, "Unavailable", 0xf5f6f5, true, true, 52, 0);
            scroll.child(90029+i,x+75,y+4);

            addWrappingText(90035+i, "", tda, 0, 0xFF9900, true, true, 110);
            scroll.child(90035+i,x+26,y+115);

            addTimer(90041+i, "H:m:ss", tda, 1, 0xf5f6f5, true, true);
            scroll.child(90041+i,x+80,y+135);

            addText(90047+i, "569", 0xFF9900, false, true, 52, 1);
            scroll.child(90047+i,x+31,y+211);

            addText(90053+i, "569", 0xFF9900, true, true, 52, 1);
            scroll.child(90053+i,x+115,y+211);

            addTimer(90059+i, "H:m:ss", tda, 0, 0xf5f6f5, true, true);
            scroll.child(90059+i,x+75,y+4);

            drawNpcOnInterface(90065+i, 75,2500);
            scroll.child(90065+i, x+15,y+2);

            x+= 156;

        }
    }
}
