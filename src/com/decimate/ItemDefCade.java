package com.decimate;

public class ItemDefCade {
    public static void doCustom(ItemDef itemDef) {
        switch (itemDef.id) {
            case 14557:
                itemDef.modelID = 100008;
                itemDef.maleEquip1 = 100009;
                itemDef.femaleEquip1 = 100009;
                itemDef.name = "Cade Wings";
                itemDef.modelZoom = 2680;
                itemDef.modelOffsetY = 4;
                itemDef.modelOffset1 = 5;
                itemDef.rotationX = 1052;
                itemDef.rotationY = 595;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
        }
    }
}
