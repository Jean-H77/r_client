package com.decimate;


public final class Tile extends Node {

    public final InteractableObject[] interactableObjects;
    final int tileX;
    final int tileY;
    final int plane;
    final int[] anIntArray1319;
    public PlainTile plainTile;
    public ShapedTile shapedTile;
    public WallObject wallObject;
    public WallDecoration wallDecoration;
    public GroundDecoration groundDecoration;
    public GroundItem groundItem;
    public Tile tileBelowThisTile;
    int tileZ;
    int entityCount;
    int anInt1320;
    int logicHeight;
    boolean aBoolean1322;
    boolean aBoolean1323;
    boolean aBoolean1324;
    int anInt1325;
    int anInt1326;
    int anInt1327;
    int anInt1328;
    public Tile(int i, int j, int k) {
        interactableObjects = new InteractableObject[5];
        anIntArray1319 = new int[5];
        plane = tileZ = i;
        tileX = j;
        tileY = k;
    }
}
