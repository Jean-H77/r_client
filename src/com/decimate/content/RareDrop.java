package com.decimate.content;

import com.decimate.*;
import com.decimate.util.ColorConstants;
import com.decimate.util.Task;
import com.decimate.util.TaskManager;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RareDrop extends RSInterface {

    private static boolean adding = false;
    private static int currentComponent = 73661;
    public static boolean showing = false;

    private static final List<Integer> COLORS = ImmutableList.of(
            ColorConstants.CYAN, ColorConstants.LIME, ColorConstants.SKY_BLUE,
            ColorConstants.SNOW_WHITE, ColorConstants.VIOLET, ColorConstants.ORANGE,
            ColorConstants.PINK, ColorConstants.YELLOW, ColorConstants.TEAL
    );

    public final int id;
    public final String playerName;
    public final int kills;
    public final int amount;
    private final int color;
    private int yPos;

    public RareDrop(int id, String playerName, int kills, int amount) {
        this.id = id;
        this.playerName = playerName;
        this.kills = kills;
        this.amount = amount;
        this.color = Misc.getRandomElement(COLORS);
        yPos = 0;
    }

    private static final LinkedList<RareDrop> queuedDrops = new LinkedList<>();
    private static final List<RareDrop> displayedDrops = new ArrayList<>();
    public static void addDrop(RareDrop drop) {
        queuedDrops.add(drop);
        if (!adding) {
            updateDisplayedDrops();
        }
    }

    public static void create() {
        RSInterface rsi = addInterface(73650);
        rsi.width = 139;
        rsi.height = 196;
        rsi.childToIntersect = 73654;
        addSpriteLoader(73651, 1167);
        addSpriteToggleButton(73654, 73650, 1168, 1169, 16, 16, "Toggle", 4, 1827, 73655);
        addText(73657, "Global Rare Drops", 0xffffff, true, true, 52, fonts, 0);
        rsi.totalChildren(3);
        rsi.child(0, 73651, 0, 0);
        rsi.child(1, 73654, 118, 4);
        rsi.child(2, 73657, 60, 6);

    }

    public static void display() {
        Client.spriteLoader.lookup(1171).drawAdvancedSprite(3, 42);
        int origTopX = DrawingArea.topX;
        int origTopY = DrawingArea.topY;
        int origBottomX = DrawingArea.bottomX;
        int origBottomY = DrawingArea.bottomY;
        DrawingArea.setDrawingArea(212, 5, 140,
                42);
        for (RareDrop drop : displayedDrops) {
            int fY = drop.yPos;
            Client.spriteLoader.lookup(1170).drawAdvancedSprite(5, fY);
            TextDrawingArea font = RSInterface.fonts[0];
            font.drawRegularText(false, 12, drop.color, "Killer: "+drop.playerName, fY+6+font.anInt1497);
            font.drawRegularText(false, 12, drop.color, "Kill-count: "+drop.kills, fY+26+font.anInt1497);
            Sprite item = ItemDef.getSprite(drop.id-1, drop.amount,
                    0);
            if (item != null)
                item.drawSprite(105, 5+fY);
        }
        DrawingArea.setDrawingArea(origBottomY, origTopX, origBottomX, origTopY);

    }
    public static void updateDisplayedDrops() {
        RareDrop newDrop = queuedDrops.getFirst();
        displayedDrops.add(newDrop);
        if (displayedDrops.size() == 6)
            displayedDrops.remove(0);
        adding = true;
        TaskManager.submit(new Task(1, true) {
            private int y;

            @Override
            protected void execute() {
                for (RareDrop drop : displayedDrops)
                    drop.yPos++;
                if ((++y) >= 42) {
                    adding = false;
                    if (!queuedDrops.isEmpty()) {
                        updateDisplayedDrops();
                    }
                    stop();
                }
            }
        });

        queuedDrops.removeFirst();
    }

    public static void toggleButton() {
        showing = !showing;
    }
}
