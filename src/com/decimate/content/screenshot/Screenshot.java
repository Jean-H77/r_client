package com.decimate.content.screenshot;

import com.decimate.Client;
import com.decimate.RSImageProducer;
import com.decimate.signlink;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    private static final File CACHE_DIR = new File(signlink.findcachedir());
    private static final File SCREENSHOT_DIR = new File(CACHE_DIR, "screenshots");
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    private static String format(Date date) {
        synchronized (TIME_FORMAT) {
            return TIME_FORMAT.format(date);
        }
    }

    public static void buildScreenshot() {
        final Client client = Client.getClient();
        final BufferedImage screenshot = new BufferedImage(Client.clientWidth, Client.clientHeight, BufferedImage.TYPE_INT_ARGB);
        final Graphics graphics = screenshot.getGraphics();

        if (Client.clientSize == 0) {
            final RSImageProducer[] buffers = {
                    client.topFrame, client.leftFrame, client.rightFrame,
                    client.chatAreaIP, client.gameScreenIP, client.mapAreaIP, client.tabAreaIP
            };
            for (RSImageProducer buffer : buffers) {
                graphics.drawImage(buffer.image, (int) buffer.point.getX(), (int) buffer.point.getY(), null);
            }
        } else {
            final RSImageProducer buffer = client.gameScreenIP;
            graphics.drawImage(buffer.image, (int) buffer.point.getX(), (int) buffer.point.getY(), null);
        }

        takeScreenshot(client, screenshot);
        graphics.dispose();
    }

    private static void takeScreenshot(Client client, BufferedImage screenshot) {
        if (!Client.loggedIn) {
            return;
        }

        File playerFolder;
        if (Client.myPlayer != null && Client.myPlayer.name != null) {
            String playerDir = Client.myPlayer.name;
            playerFolder = new File(SCREENSHOT_DIR, playerDir);
        } else {
            playerFolder = SCREENSHOT_DIR;
        }

        playerFolder.mkdirs();

        String fileName = format(new Date());
        try {
            File screenshotFile = new File(playerFolder, fileName + ".png");

            // To make sure that screenshots don't get overwritten, check if file exists,
            // and if it does create file with same name and suffix.
            int i = 1;
            while (screenshotFile.exists()) {
                screenshotFile = new File(playerFolder, fileName + String.format("(%d)", i++) + ".png");
            }

            ImageIO.write(screenshot, "PNG", screenshotFile);
            client.pushMessage("A screenshot was saved to @red@" + screenshotFile, 0, "");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
