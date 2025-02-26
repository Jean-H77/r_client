package com.decimate.interfaces;

import com.decimate.RSInterface;
import com.decimate.TextDrawingArea;

public class PerkTreeInterface extends RSInterface {
    private static void widget(TextDrawingArea[] fonts) {
        RSInterface widget = addInterface(5500);
        widget.totalChildren(4);

        addSpriteLoader(5501, 1309);
        widget.child(5501, 5, 20);

        addText(5502, "Decimate Perks", fonts, 2, 16751360, true, true);
        widget.child(5502, 265, 25);
        hoverButton(5503, fonts, 1089, 1090, "Close", 2, 0xFFA500, "");
        widget.child(5503, 479, 24);

        RSInterface e2 = addInterface(5600, 475, 268);
        e2.totalChildren(42);
        e2.scrollMax = 600;

        addSpriteLoader(5601, 1313);
        RSInterface.interfaceCache.get(5601).advancedSprite = true;
        e2.child(5601, 3, 5);

        widget.child(5600, 4, 44);

        byte b1 = 0;
        byte b2 = 0;
        addButton(5602, 1312, "Karma I");//Gives a % chance to recoil 50% damage received
        RSInterface.interfaceCache.get(5602).advancedSprite = true;
            RSInterface.interfaceCache.get(5602).hoverDescription = "Gives a 10% chance_to recoil half of_damage received.";
        e2.child(1, 5602, 243 + b1, 546 + b2);


        addButton(5603, 1312, "Karma II");
        RSInterface.interfaceCache.get(5603).advancedSprite = true;
            RSInterface.interfaceCache.get(5603).hoverDescription = "Gives an additional 15% chance_to recoil half of_damage received.";
        e2.child(2, 5603, 243 + b1, 485 + b2);

        addButton(5604, 1312, "Treasure hunter I"); //Better chance to receive misc drops/task bottles
        RSInterface.interfaceCache.get(5604).advancedSprite = true;
            RSInterface.interfaceCache.get(5604).hoverDescription = "Gives a 5% better chance_to receive misc drops_and task bottles.";
        e2.child(3, 5604, 361 + b1, 506 + b2);

        addButton(5605, 1312, "Treasure hunter II");
            RSInterface.interfaceCache.get(5605).hoverDescription = "Gives an additional 10% better_chance to receive misc drops_and task bottles.";
        RSInterface.interfaceCache.get(5605).advancedSprite = true;
        e2.child(4, 5605, 427 + b1, 506 + b2);

        addButton(5606, 1310, "Pet Power");
        RSInterface.interfaceCache.get(5606).hoverDescription = "Pet damage bonus effects get_doubled(Excludes Doppel)";
        RSInterface.interfaceCache.get(5606).advancedSprite = true;
        e2.child(5, 5606, 362 + b1, 433 + b2);

        addButton(5607, 1310, "Melee Power");
        RSInterface.interfaceCache.get(5607).hoverDescription = "";
        RSInterface.interfaceCache.get(5607).hoverDescription = "Melee Damage will be increased by 10-25% at random";
        RSInterface.interfaceCache.get(5607).advancedSprite = true;
        e2.child(6, 5607, 420 + b1, 398 + b2);

        addButton(5608, 1310, "AOE Victimize I");
        RSInterface.interfaceCache.get(5608).hoverDescription = "Allows AOE Attacks to hit up to 1 more victim";
        RSInterface.interfaceCache.get(5608).advancedSprite = true;
        e2.child(7, 5608, 303 + b1, 398 + b2);

        addButton(5609, 1312, "Magic Power");
        RSInterface.interfaceCache.get(5609).hoverDescription = "Magic Damage will be increased by 10-25% at random";
        RSInterface.interfaceCache.get(5609).advancedSprite = true;
        e2.child(8, 5609, 362 + b1, 369 + b2);

        addButton(5610, 1312, "Chargaholic I");//% Chance to save AOE charge
        RSInterface.interfaceCache.get(5610).advancedSprite = true;
            RSInterface.interfaceCache.get(5610).hoverDescription = "Gives a 5% chance to save an AOE charge.";
        e2.child(9, 5610, 119 + b1, 505 + b2);

        addButton(5611, 1312, "Chargaholic II");//% Chance to save AOE charge
            RSInterface.interfaceCache.get(5611).hoverDescription = "Gives an addition 10% chance_to save an AOE charge.";
        RSInterface.interfaceCache.get(5611).advancedSprite = true;
        e2.child(10, 5611, 54 + b1, 469 + b2);

        addButton(5612, 1312, "Casket Hoarder");
        RSInterface.interfaceCache.get(5612).hoverDescription = "Gives a 10% chance for all casket drops to be doubled.";
        RSInterface.interfaceCache.get(5612).advancedSprite = true;
        e2.child(11, 5612, 119 + b1, 428 + b2);

        addButton(5613, 1310, "Casket Saver");
        RSInterface.interfaceCache.get(5613).hoverDescription = "Gives a 10% chance to_save your casket_upon opening.";
        RSInterface.interfaceCache.get(5613).advancedSprite = true;
        e2.child(12, 5613, 54 + b1, 397 + b2);

        addButton(5614, 1310, "");
        RSInterface.interfaceCache.get(5614).advancedSprite = true;
        e2.child(13, 5614, 119 + b1, 359 + b2);

        addButton(5615, 1312, "Range Power");
        RSInterface.interfaceCache.get(5615).hoverDescription = "Range Damage will be increased by 10-25% at random";
        RSInterface.interfaceCache.get(5615).advancedSprite = true;
        e2.child(14, 5615, 303 + b1, 467 + b2);

        addButton(5616, 1312, "AOE Power");
        RSInterface.interfaceCache.get(5616).hoverDescription = "AOE damage gets increased by 10%";
        RSInterface.interfaceCache.get(5616).advancedSprite = true;
        e2.child(15, 5616, 420 + b1, 467 + b2);

        addButton(5617, 1312, "Treasure buddy");//Having a pet out gives a 15% Chance at an extra drop roll
            RSInterface.interfaceCache.get(5617).hoverDescription = "Gives a 15% chance to get_an addition drop roll when_you have a pet out.";
        RSInterface.interfaceCache.get(5617).advancedSprite = true;
        e2.child(16, 5617, 427 + b1, 552 + b2);

        addButton(5618, 1312, "Easy Scape I");
        RSInterface.interfaceCache.get(5618).hoverDescription = "Grants a bonus 10% experience.";
        RSInterface.interfaceCache.get(5618).advancedSprite = true;
        e2.child(17, 5618, 177 + b1, 397 + b2);

        addButton(5619, 1312, "Easy Scape II");
        RSInterface.interfaceCache.get(5619).hoverDescription = "Grants an additional 5%(15% total) experience.";
        RSInterface.interfaceCache.get(5619).advancedSprite = true;
        e2.child(18, 5619, 216 + b1, 434 + b2);

        addButton(5620, 1310, "AOE Victimize II");
        RSInterface.interfaceCache.get(5620).hoverDescription = "Allows AOE Attacks to hit_up to 1(Total 2) more victim";
        RSInterface.interfaceCache.get(5620).advancedSprite = true;
        e2.child(19, 5620, 270 + b1, 434 + b2);

        addButton(5621, 1312, "AOE God");//AOE hits have a chance to instakill victim
            RSInterface.interfaceCache.get(5621).hoverDescription = "AOE Hits have a chance to_insta-kill your opponent.";
        RSInterface.interfaceCache.get(5621).advancedSprite = true;
        e2.child(20, 5621, 13 + b1, 519 + b2);

        addButton(5622, 1312, "Perk Addiction");
        RSInterface.interfaceCache.get(5622).hoverDescription = "Gives an addition 10%_perk dust when exchanging items.";
        RSInterface.interfaceCache.get(5622).advancedSprite = true;
        e2.child(21, 5622, 176 + b1, 475 + b2);

        addButton(5623, 1312, "Chargaholic III");//% Chance to save AOE charge
            RSInterface.interfaceCache.get(5623).hoverDescription = "Gives an addition 15% chance_to save an AOE charge.";
        RSInterface.interfaceCache.get(5623).advancedSprite = true;
        e2.child(22, 5623, 10 + b1, 430 + b2);

        addButton(5624, 1310, "");
        RSInterface.interfaceCache.get(5624).advancedSprite = true;
        e2.child(23, 5624, 242 + b1, 396 + b2);

        addButton(5625, 1310, "");
        RSInterface.interfaceCache.get(5625).advancedSprite = true;
        e2.child(24, 5625, 198 + b1, 354 + b2);

        addButton(5626, 1310, "");
        RSInterface.interfaceCache.get(5626).advancedSprite = true;
        e2.child(25, 5626, 285 + b1, 354 + b2);

        addButton(5627, 1310, "");
        RSInterface.interfaceCache.get(5627).advancedSprite = true;
        e2.child(26, 5627, 59 + b1, 330 + b2);

        addButton(5628, 1310, "");
        RSInterface.interfaceCache.get(5628).advancedSprite = true;
        e2.child(27, 5628, 421 + b1, 331 + b2);

        addButton(5629, 1310, "");
        RSInterface.interfaceCache.get(5629).advancedSprite = true;
        e2.child(28, 5629, 59 + b1, 277 + b2);

        addButton(5630, 1310, "");
        RSInterface.interfaceCache.get(5630).advancedSprite = true;
        e2.child(29, 5630, 321 + b1, 330 + b2);

        addButton(5631, 1310, "");
        RSInterface.interfaceCache.get(5631).advancedSprite = true;
        e2.child(30, 5631, 321 + b1, 277 + b2);

        addButton(5632, 1310, "");
        RSInterface.interfaceCache.get(5632).advancedSprite = true;
        e2.child(31, 5632, 362 + b1, 307 + b2);

        addButton(5633, 1310, "");
        RSInterface.interfaceCache.get(5633).advancedSprite = true;
        e2.child(32, 5633, 156 + b1, 324 + b2);

        addButton(5634, 1310, "");
        RSInterface.interfaceCache.get(5634).advancedSprite = true;
        e2.child(33, 5634, 238 + b1, 324 + b2);

        addButton(5635, 1310, "");
        RSInterface.interfaceCache.get(5635).advancedSprite = true;
        e2.child(34, 5635, 198 + b1, 304 + b2);

        addButton(5636, 1310, "");
        RSInterface.interfaceCache.get(5636).advancedSprite = true;
        e2.child(35, 5636, 96 + b1, 242 + b2);

        addButton(5637, 1310, "");
        RSInterface.interfaceCache.get(5637).advancedSprite = true;
        e2.child(36, 5637, 421 + b1, 281 + b2);

        addButton(5638, 1310, "");
        RSInterface.interfaceCache.get(5638).advancedSprite = true;
        e2.child(37, 5638, 119 + b1, 305 + b2);

        addButton(5639, 1310, "");
        RSInterface.interfaceCache.get(5639).advancedSprite = true;
        e2.child(38, 5639, 150 + b1, 275 + b2);

        addButton(5640, 1312, "Grand treasure");
        RSInterface.interfaceCache.get(5640).hoverDescription = "Reduces drop rates by 15% on_boxes that open the_spinning interface.";
        RSInterface.interfaceCache.get(5640).advancedSprite = true;
        e2.child(39, 5640, 361 + b1, 506 + b2 + 46);

        addButton(5641, 1310, "");
        RSInterface.interfaceCache.get(5641).advancedSprite = true;
        e2.child(40, 5641, 150 + b1 + 32, 275 + b2 - 33);

        addButton(5642, 1310, "");
        RSInterface.interfaceCache.get(5642).advancedSprite = true;
        e2.child(41, 5642, 18 + b1, 365 + b2);

        e2 = addInterface(5700);
        e2.totalChildren(6);
        hoverButton(5308, fonts, 1314, 1315, "Purchase Perk", 1, 1, "");
        RSInterface.interfaceCache.get(5308).tooltip = "Purchase Blood Perk";
        addText(5311, "Purchase", fonts, 0, 16751360, false, true); //430 25

        addText(5312, "Perk Name", fonts, 1, 0x0000FF, true, false); //263 15
        addText(5313, "Required Perk(s): Perk, Perk", fonts, 1, 0xff0000, true, false); //263 38
        addText(5314, "Cost: 5000 Perk Dust", fonts, 1, 0xff0000, true, false); //263 54
        addText(5315, "This perk does funky stuff lol", fonts, 1, 0, true, false); //263 70
        e2.child(5308, 370, 2);
        e2.child(5311, 423, 13);
        e2.child(5312, 205, 7);
        e2.child(5313, 220, 38);
        e2.child(5314, 220, 57);
        e2.child(5315, 225, 76);
    }
    public static void unpack(TextDrawingArea[] font) {
        widget(font);
    }
}
