package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;

public class RaidsCreationInterface extends RSInterface {

    public static void create(TextDrawingArea[] tda) {
        RSInterface main = addInterface(91000);

        addSprite(91001, Client.spriteLoader.lookup(1065));
        addText(91002, "Raiding party setup", tda, 2, 0xff9e09, true, true);
        addText(91003, "Description and requirements", tda, 2, 0xff9e09, true, true);
        addText(91004, "Possible drops and rewards", tda, 2, 0xff9e09, true, true);
        addText(91005, "Raid type and requirements", tda, 2, 0xff9e09, false, true);
        addText(91006, "Your raiding party", tda, 2, 0xff9e09, true, true);

        hoverButton(91008, tda, 1087, 1088, "Start Raid", 2, 0xFFA500, "");
        addText(91030, "Start Raid", tda, 2, 0xff9e09, false, true);
        hoverButton(91009, tda, 1077, 1078, "Leave party", 2, 0xFFA500, "");
        addText(91031, "Leave party", tda, 2, 0xff9e09, false, true);

        addWrappingText(91029, "A very very long message for testing very long message for testing", tda, 0, 16750899, false, true, 257);
        hoverButton(91028, tda, 1089, 1090, "Close", 2, 0xFFA500, "");
        // Leader
        addText(91010, "Leader name", tda, 0, 0xff9e09, false, true);
        addText(91011, "Leader", tda, 0, 0xff9e09, false, true);

        // Member 1
        RSInterface member1 = addInterface(91012);
        addText(91013, "Name", tda, 0, 0xff9e09, true, true);
        hoverButton(91014, tda, 1079, 1080, "Promote to leader", 2, 0xFFA500, "");
        hoverButton(91015, tda, 1085, 1086, "Remove from group", 2, 0xFFA500, "");
        int member1ChildCount = 0;
        member1.totalChildren(3);
        member1.child(member1ChildCount++, 91013, 36, 0);
        member1.child(member1ChildCount++, 91014, 20, 15);
        member1.child(member1ChildCount, 91015, 40, 15);

        // Member 2
        RSInterface member2 = addInterface(91016);
        addText(91017, "Name", tda, 0, 0xff9e09, true, true);
        hoverButton(91018, tda, 1079, 1080, "Promote to leader", 2, 0xFFA500, "");
        hoverButton(91019, tda, 1085, 1086, "Remove from group", 2, 0xFFA500, "");
        int member2ChildCount = 0;
        member2.totalChildren(3);
        member2.child(member2ChildCount++, 91017, 36, 0);
        member2.child(member2ChildCount++, 91018, 20, 15);
        member2.child(member2ChildCount, 91019, 40, 15);

        // Member 3
        RSInterface member3 = addInterface(91020);
        addText(91021, "Name", tda, 0, 0xff9e09, true, true);
        hoverButton(91022, tda, 1079, 1080, "Promote to leader", 2, 0xFFA500, "");
        hoverButton(91023, tda, 1085, 1086, "Remove from group", 2, 0xFFA500, "");
        int member3ChildCount = 0;
        member3.totalChildren(3);
        member3.child(member3ChildCount++, 91021, 36, 0);
        member3.child(member3ChildCount++, 91022, 20, 15);
        member3.child(member3ChildCount, 91023, 40, 15);

        // Member 4
        RSInterface member4 = addInterface(91024);
        addText(91025, "Name", tda, 0, 0xff9e09, true, true);
        RSInterface.hoverButton(91026, tda, 1079, 1080, "Promote to leader", 2, 0xFFA500, "");
        RSInterface.hoverButton(91027, tda, 1085, 1086, "Remove from group", 2, 0xFFA500, "");
        int member4ChildCount = 0;
        member4.totalChildren(3);
        member4.child(member4ChildCount++, 91025, 36, 0);
        member4.child(member4ChildCount++, 91026, 20, 15);
        member4.child(member4ChildCount, 91027, 40, 15);

        int x = (512 - 510) / 2;
        int y = (334 - 325) / 2;

        RSInterface scroll = addInterface(57875);
        scroll.totalChildren(1);
        int scrollTotalChidlren = 0;
        addContainer(57876, 0, 7, 5, 0, 0, false, null, null, null, null, null);
        scroll.child(scrollTotalChidlren++, 57876, 0, 0);
        scroll.width = 240;
        scroll.height = 40;
        scroll.scrollMax = 200;

        RSInterface raidNames = addInterface(91032);
        raidNames.totalChildren(1);
        int raidNamesTotalChildren = 0;
        addClickableText(91033, "", "Select", tda, 1, 0xeb981f, false, true, 150);
        raidNames.child(raidNamesTotalChildren++, 91033, 0, 0);
        raidNames.width = 160;
        raidNames.height = 55;
        raidNames.scrollMax = 60;


        RSInterface requirements = addInterface(91034);
        requirements.totalChildren(2);
        int requirementsTotalChildren = 0;
        addText(91035, "Requirements", tda, 1, 0xff9e09, false, true);
        addText(91036, "Requirement", tda, 0, 0xff9e09, false, true);
        requirements.child(requirementsTotalChildren++, 91035, 0, 0);
        requirements.child(requirementsTotalChildren++, 91036, 0, 20);
        requirements.width = 160;
        requirements.height = 65;
        requirements.scrollMax = 70;

        main.totalChildren(21);
        int child = 0;

        main.child(child++, 91001, x, y);
        main.child(child++, 91002, 260, 13);
        main.child(child++, 91003, 351, 45);
        main.child(child++, 91004, 341, 148);
        main.child(child++, 91005, 29, 45);
        main.child(child++, 91006, 105, 224);
        main.child(child++, 91008, 135, 292);
        main.child(child++, 91030, 162, 298);
        main.child(child++, 91009, 260, 292);
        main.child(child++, 91031, 286, 298);
        main.child(child++, 91010, 42, 253);
        main.child(child++, 91011, 42, 264);
        main.child(child++, 91012, 131, 253);
        main.child(child++, 91016, 221, 253);
        main.child(child++, 91020, 311, 253);
        main.child(child++, 91024, 401, 253);
        main.child(child++, 57875, 233, 170);
        main.child(child++, 91029, 233, 68);
        main.child(child++, 91032, 35, 72);
        main.child(child++, 91028, 483, 12);
        main.child(child++, 91034, 35, 140);
    }
}
