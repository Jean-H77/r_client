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
            case 14558://free to 14569
                itemDef.modelID = 100021;
                itemDef.maleEquip1 = 100020;
                itemDef.femaleEquip1 = 100020;
                itemDef.name = "Gim aura";
                itemDef.modelZoom = 2680;
                itemDef.modelOffsetY = 4;
                itemDef.modelOffset1 = 5;
                itemDef.rotationX = 1052;
                itemDef.rotationY = 595;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 14559://free to 14569
                itemDef.modelID = 100023;
                itemDef.maleEquip1 = 100022;
                itemDef.femaleEquip1 = 100022;
                itemDef.name = "Uim aura";
                itemDef.modelZoom = 2680;
                itemDef.modelOffsetY = 4;
                itemDef.modelOffset1 = 5;
                itemDef.rotationX = 1052;
                itemDef.rotationY = 595;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 14560://free to 14569
                itemDef.modelID = 100025;
                itemDef.maleEquip1 = 100024;
                itemDef.femaleEquip1 = 100024;
                itemDef.name = "Ironman aura";
                itemDef.modelZoom = 2680;
                itemDef.modelOffsetY = 4;
                itemDef.modelOffset1 = 5;
                itemDef.rotationX = 1052;
                itemDef.rotationY = 595;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 14561:
                itemDef.modelZoom = 2440;
                itemDef.modelOffsetY = 2;
                itemDef.modelOffset1 = 4;
                itemDef.rotationX = 43;
                itemDef.rotationY = 565;
                itemDef.modelID = 91356;
                itemDef.name = "Premium Pass";
                itemDef.stackable = false;
                itemDef.stackIDs = null;
                itemDef.stackAmounts = null;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{"Activate!", null, null, null, "Drop"};
                break;
        }
    }
}
