package com.decimate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public final class RSFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private final RSApplet applet;
    private final Insets insets;
    public Toolkit toolkit = Toolkit.getDefaultToolkit();
    public Dimension screenSize = toolkit.getScreenSize();

    public RSFrame(RSApplet applet, int width, int height, boolean resizable, boolean fullscreen) {
        this.applet = applet;
        setTitle(Configuration.CLIENT_NAME);
        try {
            final Image icon = new ImageIcon(loadImageResource(getClass(), "icon.png")).getImage();
            if (icon != null) {
                setIconImage(icon);
            }
        } catch (IllegalArgumentException ignored) {
        }

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(resizable);
        setUndecorated(fullscreen);
        setVisible(true);
        insets = getInsets();
        if (resizable) {
            setMinimumSize(new Dimension(900 + insets.left + insets.right, 600 + insets.top + insets.bottom));
        }
        setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        toFront();
    }

    public void repairFrameSize(int width, int height) {
        setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
    }
    public static void openURL(String url) {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Graphics getGraphics() {
        final Graphics graphics = super.getGraphics();
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.translate(insets != null ? insets.left : 0, insets != null ? insets.top : 0);
        return graphics;
    }

    public int getFrameWidth() {
        return getWidth() - (insets.left + insets.right);
    }

    public int getFrameHeight() {
        return getHeight() - (insets.top + insets.bottom);
    }

    public void update(Graphics graphics) {
        applet.update(graphics);
    }

    public void paint(Graphics graphics) {
        applet.paint(graphics);
    }

    private BufferedImage loadImageResource(final Class<?> c, final String path) {
        try (InputStream in = c.getResourceAsStream(path)) {
            synchronized (ImageIO.class) {
                return ImageIO.read(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}