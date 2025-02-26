package com.decimate;


// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Entity extends Animable {

    public int index = -1;
    public final int[] pathX;
    public final int[] pathY;
    final long[] hitArray;
    final int[] hitMarkTypes;
    final int[] hitsLoopCycle;
    final boolean[] aBooleanArray1553;
    public int[] hitmarkMove = new int[4];
    public int[] moveTimer = new int[4];
    public int[] hitmarkTrans = new int[4];
    public int[] hitIcon = new int[4];
    public long[] soakDamage = new long[4];
    public int[] hitMarkPos = new int[4];
    public int entScreenX;
    public int entScreenY;
    public int interactingEntity;
    public String textSpoken;
    public int height;
    public int turnDirection;
    public int anim;
    public int loopCycleStatus;
    public long currentHealth;
    public long maxHealth;
    public int x;
    public int y;
    public int nextAnimationFrame;
    public int nextGraphicsAnimationFrame;
    public int nextIdleAnimationFrame;
    int anInt1503;
    int anInt1504;
    int runAnimation;
    int standAnim;
    int anInt1512;
    int anInt1513;
    int anInt1517;
    int currentForcedAnimFrame;
    int anInt1519;
    int anInt1520;
    int currentAnim;
    int animCycle;
    int graphicDelay;
    int graphicHeight;
    int pathLength;
    int currentAnimFrame;
    int anInt1528;
    int animationDelay;
    int anInt1530;
    int anInt1531;
    int textCycle;
    int loopCycle;
    int anInt1538;
    int anInt1539;
    int boundDim;
    boolean aBoolean1541;
    int anInt1542;
    int anInt1543;
    int anInt1544;
    int anInt1545;
    int anInt1546;
    int anInt1547;
    int anInt1548;
    int turnInfo;
    int anInt1552;
    int anInt1554;
    int anInt1555;
    int anInt1556;
    int anInt1557;
    Entity() {
        pathX = new int[10];
        pathY = new int[10];
        interactingEntity = -1;
        anInt1504 = 32;
        runAnimation = -1;
        height = 200;
        standAnim = -1;
        anInt1512 = -1;
        hitArray = new long[4];
        hitMarkTypes = new int[4];
        hitsLoopCycle = new int[4];
        anInt1517 = -1;
        anInt1520 = -1;
        anim = -1;
        loopCycleStatus = -1000;
        textCycle = 100;
        boundDim = 1;
        aBoolean1541 = false;
        aBooleanArray1553 = new boolean[10];
        anInt1554 = -1;
        anInt1555 = -1;
        anInt1556 = -1;
        anInt1557 = -1;
    }
    public Entity getInteractingEntity()
    {
        int index = interactingEntity;
        if (index == -1 || index == 65535)
        {
            return null;
        }
        Client client = Client.instance;

        if (index < 32768)
        {
            NPC[] npcs = client.npcArray;
            return npcs[index];
        }

        index -= 32768;
        if (index == client.playerId) {
            index = client.myPlayerIndex;
        }
        Player[] players = client.playerArray;
        return players[index];
    }
    public final void setPos(int i, int j, boolean flag) {
        if (anim != -1 && Animation.anims[anim].priority == 1)
            anim = -1;
        if (!flag) {
            int k = i - pathX[0];
            int l = j - pathY[0];
            if (k >= -8 && k <= 8 && l >= -8 && l <= 8) {
                if (pathLength < 9)
                    pathLength++;
                for (int i1 = pathLength; i1 > 0; i1--) {
                    pathX[i1] = pathX[i1 - 1];
                    pathY[i1] = pathY[i1 - 1];
                    aBooleanArray1553[i1] = aBooleanArray1553[i1 - 1];
                }

                pathX[0] = i;
                pathY[0] = j;
                aBooleanArray1553[0] = false;
                return;
            }
        }
        pathLength = 0;
        anInt1542 = 0;
        anInt1503 = 0;
        pathX[0] = i;
        pathY[0] = j;
        x = pathX[0] * 128 + boundDim * 64;
        y = pathY[0] * 128 + boundDim * 64;
    }

    public final void resetWalk() {
        pathLength = 0;
        anInt1542 = 0;
    }

    public final void updateHitData(int markType, long damage, int l, int icon, long soak) {
        for (int i1 = 0; i1 < 4; i1++)
            if (hitsLoopCycle[i1] <= l) {
                hitIcon[i1] = icon;
                hitmarkMove[i1] = 5;
                moveTimer[i1] = 2;
                hitmarkTrans[i1] = 255;
                soakDamage[i1] = soak;
                hitArray[i1] = damage;
                hitMarkTypes[i1] = markType;
                hitsLoopCycle[i1] = l + 70;
                hitMarkPos[i1] = 0;
                return;
            }
    }

    public final void moveInDir(boolean flag, int dir) {
        int j = pathX[0];
        int k = pathY[0];
        if (dir == 0) {
            j--;
            k++;
        }
        if (dir == 1)
            k++;
        if (dir == 2) {
            j++;
            k++;
        }
        if (dir == 3)
            j--;
        if (dir == 4)
            j++;
        if (dir == 5) {
            j--;
            k--;
        }
        if (dir == 6)
            k--;
        if (dir == 7) {
            j++;
            k--;
        }
        if (anim != -1 && Animation.anims[anim].priority == 1)
            anim = -1;
        if (pathLength < 9)
            pathLength++;
        for (int l = pathLength; l > 0; l--) {
            pathX[l] = pathX[l - 1];
            pathY[l] = pathY[l - 1];
            aBooleanArray1553[l] = aBooleanArray1553[l - 1];
        }
        pathX[0] = j;
        pathY[0] = k;
        aBooleanArray1553[0] = flag;
    }

    public boolean isVisible() {
        return false;
    }
}
