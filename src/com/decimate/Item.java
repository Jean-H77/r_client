package com.decimate;


public final class Item extends Animable {

    public int ID;
    public int x;
    public int y;
    public int amount;
    public Item() {
    }

    public final Model getRotatedModel() {
        ItemDef itemDef = ItemDef.forID(ID);
        return itemDef.getItemModelFinalised(amount);
    }
}
