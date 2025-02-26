package com.decimate.interfaces;

import com.decimate.Client;
import com.decimate.RSInterface;

public class MysteryBoxInterface extends RSInterface {

    public static int[][] rewards;

    public static int mysterySpeed = 0;
    public static int mysteryLength = 0;
    public static boolean isOpening = false;
    public static int[] stages = new int[9];
    public static int[] stageSpeed = { 16, 13, 10, 8, 5, 3, 2, 1, 1 };
    public static int stage = 0;
    public static int mysteryProgress = 0;
    public static int currentReward = 0;
    public static int currentRewardAmount = 0;

    public static void create() {
        RSInterface rsi = addInterface(50212);

        addBackground(50211, 487, 277, true);
        addHoverButtonWSpriteLoader(50210, 740, 21, 21, "Close", -1, 50209, 1);
        addHoveredImageWSpriteLoader(50209, 741, 21, 21, 50208);
        addText(50207, "Mystery Box Opening", fonts, 2, 0xFFA500, true, true);
        addRectangle(50206, 0, 0x3F372E, true, 473, 63);
        addSpriteLoader(50204, 1126);
        addHoverButtonWSpriteLoader(50203, 1129, 72, 32, "Spin", -1, 50202, 1);
        addHoveredImageWSpriteLoader(50202, 1128, 72, 32, 50201);
        addText(50200, "Spin", fonts, 2, 0xFFA500, true, true);

        itemGroup(50187, 1, 1, 0, 0);

        addRectangle(50184, 0, 0x3F372E, true, 200, 90);
        addRectangle(50183, 0, 0x000000, false, 200, 90);
        addRectangle(54088, 0, 0x3F372E, true, 170, 90);
        addRectangle(54089, 0, 0x000000, false, 170, 90);

        RSInterface line = addInterface(54090);
        line.type = 3;
        line.width = 473;
        line.height = 1;
        line.disabledColor = 0x000000;

        addText(50186, "", fonts, 0, 0xffffff, true, true);

        addText(50182, "Rewards", fonts, 2, 0xFFA500, true, true);
        addText(54091, "", fonts, 2, 0xFFA500, true, true);

        int x = (512 - 487) / 2;
        int y = (334 - 277) / 2;

        rsi.children(22);
        rsi.child(0, 50211, x, y);
        rsi.child(1, 50210, x + 458, y + 7);
        rsi.child(2, 50209, x + 458, y + 7);
        rsi.child(3, 50207, x + 243, y + 10);
        rsi.child(4, 50206, x + 7, y + 87);
        rsi.child(5, 50205, x + 7, y + 90);
        rsi.child(6, 50203, x + 207, y + 43);
        rsi.child(7, 50202, x + 207, y + 43);
        rsi.child(8, 50200, x + 243, y + 51);
        rsi.child(9, 50184, x + 40, y + 176);
        rsi.child(10, 50183, x + 40, y + 176);
        rsi.child(11, 50182, x + 138, y + 158);
        rsi.child(12, 54088, x + 278, y + 176);
        rsi.child(13, 54089, x + 278, y + 176);
        rsi.child(14, 54090, x + 7, y + 87);
        rsi.child(15, 54090, x + 7, y + 150);
        rsi.child(16, 50204, x + 237, y + 84);
        rsi.child(17, 50194, x + 340, y + 185);
        rsi.child(18, 50186, x + 366, y + 240);
        rsi.child(19, 54091, x + 363, y + 158);
        rsi.child(20, 50187, x + 349, y + 195);
        rsi.child(21, 54072, x + 50, y + 180);

        RSInterface rewards = addTabInterface(54072);

        rewards.width = 170;
        rewards.height = 82;
        rewards.scrollMax = 83;

        itemGroup(54092, 4, 300, 10, 3);

        rewards.children(1);
        rewards.child(0, 54092, 0, y - 25);

        RSInterface mysteryItems = addInterface(50205);
        addSpriteLoader(50194, 1125);
        itemGroup(50193, 100, 1, 20, 0);
        mysteryItems.width = 473;
        resetMystery(mysteryItems);
    }

    private static void resetMystery(RSInterface rsi) {
        rsi.children(101);
        int pos = -12;
        for (int t = 0; t < 100; t++) {
            rsi.child(t, 50194, pos, 4);
            pos += 52;
        }
        rsi.child(100, 50193, -2, 12);
        isOpening = false;
        stage = 0;
        mysteryLength = 0;
        mysterySpeed = 0;
        mysteryProgress = 0;
        RSInterface.interfaceCache.get(50187).inv[0] = -1;
        RSInterface.interfaceCache.get(50187).invStackSizes[0] = 0;
        RSInterface.interfaceCache.get(54091).message = "";
        RSInterface.interfaceCache.get(50186).message = "";
    }

    public static void openProcess() {
        if (isOpening) {
            for (int i = 0; i < 101; i++) {
                RSInterface.interfaceCache.get(50205).childX[i] -= mysterySpeed;
            }
            mysteryProgress += mysterySpeed;
            if (mysteryProgress >= stages[stage]) {
                mysterySpeed = stageSpeed[stage];
                stage++;
                if (stage == 9) {
                    isOpening = false;
                    complete();
                }
            }
        }
    }

    private static void complete() {
        Client.instance.sendMysteryBoxComplete();

        RSInterface.interfaceCache.get(50187).inv[0] = currentReward;
        RSInterface.interfaceCache.get(50187).invStackSizes[0] = currentRewardAmount;

        RSInterface.interfaceCache.get(54091).message = "Congratulations!";
    }

    public static void openMysteryBox(int reward, int rewardAmount) {
        resetMystery(RSInterface.interfaceCache.get(50205));
        int finish = (50 + Client.random(43));
        randomizeScroller();
        RSInterface.interfaceCache.get(50193).inv[finish] = currentReward = reward + 1;
        RSInterface.interfaceCache.get(50193).invStackSizes[finish] = currentRewardAmount = rewardAmount;

        //decreasing, pushes it more to right
        //increasing, pushes it more to left
        mysteryLength = (finish * 52) - 230;//236
        mysteryLength += Client.random(40);
        isOpening = true;
        mysterySpeed = stageSpeed[0];
        stages[0] = (int) ((double) mysteryLength * 0.6);
        stages[1] = (int) ((double) mysteryLength * 0.7);
        stages[2] = (int) ((double) mysteryLength * 0.8);
        stages[3] = (int) ((double) mysteryLength * 0.85);
        stages[4] = (int) ((double) mysteryLength * 0.9);
        stages[5] = (int) ((double) mysteryLength * 0.93);
        stages[6] = (int) ((double) mysteryLength * 0.96);
        stages[7] = (int) ((double) mysteryLength * 0.99);
        stages[8] = (int) ((double) mysteryLength * 0.997);
        RSInterface.interfaceCache.get(50186).message = "Spinning....";
    }

    public static void randomizeScroller() {
        for (int i = 0; i < 100; i++) {
            int index = Client.random(rewards.length - 1);
            RSInterface.interfaceCache.get(50193).inv[i] = rewards[index][0] + 1;
            RSInterface.interfaceCache.get(50193).invStackSizes[i] = rewards[index][1];
        }
    }
}
