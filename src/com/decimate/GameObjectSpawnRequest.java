package com.decimate;


final class GameObjectSpawnRequest extends Node {

    public int currentIDRequested;
    public int currentFaceRequested;
    public int currentTypeRequested;
    public int removeTime;
    public int plane;
    public int objectType;
    public int tileX;
    public int tileY;
    public int objectId;
    public int face;
    public int type;
    public int spawnTime;
    GameObjectSpawnRequest() {
        removeTime = -1;
    }
}
