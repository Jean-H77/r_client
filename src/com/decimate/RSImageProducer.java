package com.decimate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RSImageProducer {

    public final int[] pixels;
    public final int width;
    public final int height;
    public final BufferedImage image;
    public Point point;

    public RSImageProducer(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
        this.point = new Point(x, y);
        initDrawingArea();
    }

    public void initDrawingArea() {
        DrawingArea.initDrawingArea(height, width, pixels);
    }

    public void drawGraphics(Graphics g) {
        g.drawImage(image, (int) point.getX(), (int) point.getY(), null);
    }
}