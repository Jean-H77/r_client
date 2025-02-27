package com.decimate;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("all")
public class RSApplet extends Applet implements Runnable, MouseListener,
        MouseMotionListener, MouseWheelListener, KeyListener, FocusListener,
        WindowListener {


    public static int hotKey = 508;
    public static int anInt34;
    public static boolean shiftDown = false;
    final int keyArray[] = new int[128];
    private final long aLongArray7[] = new long[10];
    private final int charQueue[] = new int[128];
    public boolean resizing;
    public int mouseX;
    public int mouseY;
    public int clickMode1;
    public int clickX;
    public int clickY;
    public int clickMode3;
    public int saveClickX;
    public int saveClickY;
    public boolean isLoading = true;
    public boolean isApplet;
    public int resizeChatStartY;
    public int mouseWheelRotation;
    public int releasedX;
    public int releasedY;
    public boolean mouseWheelDown;
    public boolean leftMouseDown;
    public int mouseWheelX;
    public int mouseWheelY;
    int minDelay;
    int fps;
    boolean shouldDebug;
    int myWidth;
    int myHeight;
    Graphics graphics;
    RSFrame mainFrame;
    boolean awtFocus;
    int idleTime;
    int clickMode2;
    long aLong29;
    int lastB = -1;
    private int anInt4;
    private int delayTime;
    private boolean shouldClearScreen;
    private long clickTime;
    private int readIndex;
    private int writeIndex;
    private Image loadingBuffer;
    private long startTime = 0L;
    private long colorStart = 0L;
    RSApplet() {
        resizing = false;
        delayTime = 20;
        minDelay = 1;
        shouldDebug = false;
        shouldClearScreen = true;
        awtFocus = true;
    }

    public void setCursor(byte[] data) {

        Image image = getGameComponent().getToolkit().createImage(data);
        getGameComponent().setCursor(
                getGameComponent().getToolkit().createCustomCursor(image,
                        new Point(0, 0), null));
    }

    public Component getMainFrame() {
        if (isApplet)
            return this;
        return mainFrame;
    }

    public void rebuildFrame(boolean undecorated, int width, int height,
                             boolean resizable, boolean full) {
        myWidth = width;
        myHeight = height;
        if (mainFrame != null) {
            mainFrame.dispose();
        }
        mainFrame = new RSFrame(this, width, height, resizable, undecorated);
        mainFrame.addWindowListener(this);
        graphics = mainFrame.getGraphics();
        if (getGameComponent().getMouseListeners().length == 0) {
            getGameComponent().addMouseListener(this);
            getGameComponent().addMouseMotionListener(this);
            getGameComponent().addKeyListener(this);
            getGameComponent().addFocusListener(this);
            getGameComponent().addMouseWheelListener(this);
        }
        getGameComponent().setFocusTraversalKeysEnabled(false);
    }

    final void createClientFrame(int w, int h) {
        isApplet = false;
        myWidth = w;
        myHeight = h;
        mainFrame = new RSFrame(this, myWidth, myHeight, false, Client.clientSize == 2);
        graphics = getGameComponent().getGraphics();
        startRunnable(this, 1);
    }

    @Override
    public int getHeight() {
        return (Client.instance.loggedIn && Client.clientSize == 1 && Client.paneClosed > 0) ? super.getHeight() - 55 : super.getHeight();
    }

    @Override
    public int getWidth() {
        return (Client.instance.loggedIn && Client.clientSize == 1 && Client.paneClosed > 0) ? super.getWidth() - 5 : super.getWidth();
    }

    final void initClientFrame(int w, int h) {
        isApplet = true;
        myWidth = w;
        myHeight = h;
        graphics = getGameComponent().getGraphics();
        startRunnable(this, 1);
    }

    public void run() {
        getGameComponent().addMouseListener(this);
        getGameComponent().addMouseMotionListener(this);
        getGameComponent().addKeyListener(this);
        getGameComponent().addFocusListener(this);
        getGameComponent().addMouseWheelListener(this);
        getGameComponent().setFocusTraversalKeysEnabled(false);
        if (mainFrame != null) {
            mainFrame.addWindowListener(this);
        }

        colorStart = startTime = 0L;
        startUp();
        int i = 0;
        int j = 256;
        int k = 1;
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < 10; j1++) {
            aLongArray7[j1] = System.currentTimeMillis();
        }

        long l1 = System.currentTimeMillis();
        do {
            if (anInt4 < 0) {
                break;
            }
            if (anInt4 > 0) {
                anInt4--;
                if (anInt4 == 0) {
                    exit();
                    return;
                }
            }
            int k1 = j;
            int i2 = k;
            j = 300;
            k = 1;
            long l2 = System.currentTimeMillis();
            if (aLongArray7[i] == 0L) {
                j = k1;
                k = i2;
            } else if (l2 > aLongArray7[i]) {
                j = (int) ((long) (2560 * delayTime) / (l2 - aLongArray7[i]));
            }
            if (j < 25) {
                j = 25;
            }
            if (j > 256) {
                j = 256;
                k = (int) ((long) delayTime - (l2 - aLongArray7[i]) / 10L);
            }
            if (k > delayTime) {
                k = delayTime;
            }
            aLongArray7[i] = l2;
            i = (i + 1) % 10;
            if (k > 1) {
                for (int j2 = 0; j2 < 10; j2++) {
                    if (aLongArray7[j2] != 0L) {
                        aLongArray7[j2] += k;
                    }
                }

            }
            if (k < minDelay) {
                k = minDelay;
            }
            try {
                Thread.sleep(k);
            } catch (InterruptedException interruptedexception) {
                i1++;
            }
            for (; l < 256; l += j) {
                clickMode3 = clickMode1;
                saveClickX = clickX;
                saveClickY = clickY;
                aLong29 = clickTime;
                clickMode1 = 0;
                processGameLoop();
                readIndex = writeIndex;
            }

            l &= 0xff;
            if (delayTime > 0) {
                fps = (1000 * j) / (delayTime * 256);
            }
            processDrawing();
            if (shouldDebug) {
                System.out.println((new StringBuilder()).append("ntime:")
                        .append(l2).toString());
                for (int k2 = 0; k2 < 10; k2++) {
                    int i3 = ((i - k2 - 1) + 20) % 10;
                    System.out.println((new StringBuilder()).append("otim")
                            .append(i3).append(":").append(aLongArray7[i3])
                            .toString());
                }

                System.out.println((new StringBuilder()).append("fps:")
                        .append(fps).append(" ratio:").append(j)
                        .append(" count:").append(l).toString());
                System.out.println((new StringBuilder()).append("del:")
                        .append(k).append(" deltime:").append(delayTime)
                        .append(" mindel:").append(minDelay).toString());
                System.out.println((new StringBuilder()).append("intex:")
                        .append(i1).append(" opos:").append(i).toString());
                shouldDebug = false;
                i1 = 0;
            }
        } while (true);
        if (anInt4 == -1) {
            exit();
        }
    }

    private void exit() {
        anInt4 = -2;
        cleanUpForQuit();
        if (mainFrame != null) {
            try {
                Thread.sleep(1000L);
            } catch (Exception exception) {
            }
            try {
                Runtime.getRuntime().halt(0);
            } catch (Throwable throwable) {
            }
        }
    }

    final void setFPS(int targetFPS) {
        delayTime = 1000 / targetFPS;
    }

    public final void start() {
        if (anInt4 >= 0) {
            anInt4 = 0;
        }
    }

    public final void stop() {
        if (anInt4 >= 0) {
            anInt4 = 4000 / delayTime;
        }
    }

    public final void destroy() {
        anInt4 = -1;
        try {
            Thread.sleep(5);
        } catch (Exception exception) {
        }
        if (anInt4 == -1) {
            exit();
        }
    }

    public final void update(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    public final void paint(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    public void mouseWheelMoved(MouseWheelEvent event) {
        int rotation = event.getWheelRotation();
        mouseWheelRotation = rotation;
        if (mouseX > 0 && mouseX < 512
                && mouseY > Client.getClient().clientHeight - 165
                && mouseY < Client.getClient().clientHeight - 25) {
            int scrollPos = Client.anInt1089;
            scrollPos -= rotation * 30;
            if (scrollPos < 0) {
                scrollPos = 0;
            }
            if (scrollPos > Client.anInt1211 - 110) {
                scrollPos = Client.anInt1211 - 110;
            }
            if (Client.anInt1089 != scrollPos) {
                Client.anInt1089 = scrollPos;
                Client.inputTaken = true;
            }
        } else if (Client.loggedIn) {
            boolean zoom = Client.clientSize == 0 ? (mouseX < 512) : (mouseX < Client.clientWidth - 200);
            if (zoom && Client.openInterfaceID == -1) {
                Client.clientZoom -= rotation * 45;
                boolean fixed = Client.clientSize == 0;
                if (Client.clientZoom > (fixed ? 1100 : 2200)) {
                    Client.clientZoom = (fixed ? 1100 : 2200);
                } else if (Client.clientZoom < (fixed ? 180 : 240)) {
                    Client.clientZoom = (fixed ? 180 : 240);
                }
            }
            Client.needDrawTabArea = true;
            Client.inputTaken = true;
        }
    }

    public final void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        int type = event.getButton();
        if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }
        resizeChatStartY = -1;
        if (Client.clientSize != 0) {
            int offsetY = Client.clientHeight - 165 - Client.chatAreaHeight;
            if (y >= offsetY - 10 && y <= offsetY + 10) {
                if (x <= 500) {
                    resizeChatStartY = y;
                }
            }
        }
        if (SwingUtilities.isMiddleMouseButton(event)) {
            mouseWheelDown = true;
            mouseWheelX = x;
            mouseWheelY = y;
            return;
        }
        idleTime = 0;
        clickX = x;
        clickY = y;
        clickTime = System.currentTimeMillis();
        if (event.getButton() == MouseEvent.BUTTON3) {
            clickMode1 = 2;
            clickMode2 = 2;
        } else if (event.getButton() == MouseEvent.BUTTON1) {
            leftMouseDown = true;
            clickMode1 = 1;
            clickMode2 = 1;
        }
    }

    public final void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }
        releasedX = x;
        releasedY = y;
        idleTime = 0;
        clickMode2 = 0;
        mouseWheelDown = false;
        leftMouseDown = false;

        if (Client.clientSize != 0 && resizeChatStartY != -1) {
            int offsetY = Client.clientHeight - 165 - Client.chatAreaHeight;
            int difference = y - resizeChatStartY;
            Client.chatAreaHeight = offsetY - y;
        }
    }

    public final void mouseClicked(MouseEvent mouseevent) {

    }

    public final void mouseEntered(MouseEvent mouseevent) {
    }

    public final void mouseExited(MouseEvent mouseevent) {
        idleTime = 0;
        mouseX = -1;
        mouseY = -1;
    }

    public final void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (mouseWheelDown) {
            y = mouseWheelX - e.getX();
            int k = mouseWheelY - e.getY();
            mouseWheelDragged(y, -k);
            mouseWheelX = e.getX();
            mouseWheelY = e.getY();
            return;
        }
        if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }
        idleTime = 0;
        mouseX = x;
        mouseY = y;

        if (Client.clientSize != 0 && resizeChatStartY != -1) {
            int offsetY = Client.clientHeight - 165 - Client.chatAreaHeight;
            int difference = y - resizeChatStartY;
            Client.chatAreaHeight = offsetY - y;
        }
    }

    void mouseWheelDragged(int param1, int param2) {

    }

    public final void mouseMoved(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();
        if (mainFrame != null) {
            Insets insets = mainFrame.getInsets();
            x -= insets.left;// 4
            y -= insets.top;// 22
        }
        idleTime = 0;
        mouseX = x;
        mouseY = y;
    }

    public final void keyPressed(KeyEvent keyevent) {

        idleTime = 0;
        int i = keyevent.getKeyCode();
        int j = keyevent.getKeyChar();
        if (dialogueSkipKeys(i)) {
            return;
        }
        if (i == KeyEvent.VK_SHIFT) {
            shiftDown = true;
        }
        if (i == KeyEvent.VK_ESCAPE && Client.openInterfaceID != -1 && Client.openInterfaceID != 33300) {
            Client.instance.clearTopInterfaces();
            return;
        }
        if (i == 17)
            lastB = 1;
        else if (i != 83)
            lastB = 0;
        if (i == 83 && lastB > 0) {
            if (Client.loggedIn) {
                if (Client.openInterfaceID == -1) {
                    Client.stream.createFrame(185);
                    Client.stream.writeWord(10000);
                }
            }
        }

        if (hotKey == 508) {
            if (Client.shiftDown) {
                if (i == KeyEvent.VK_B) {
                    Client.sendCommand("openbank");
                    return;
                }
            }
            if (i == KeyEvent.VK_CONTROL) {
                Client.controlIsDown = true;
            }
            if (Client.controlIsDown) {
                if (i == KeyEvent.VK_H) {
                    Client.sendCommand("home");
                } else if (i == KeyEvent.VK_C) {
                    Client.sendCommand("skillteleport");
                } else if (i == KeyEvent.VK_T) {
                    Client.sendCommand("monsterteleport");
                } else if (i == KeyEvent.VK_B) {
                    Client.sendCommand("bank");
                } else if (i == KeyEvent.VK_M) {
                    Client.sendCommand("minigameteleport");
                } else if (i == KeyEvent.VK_G) {
                    Client.sendCommand("globalbossteleport");
                } else if (i == KeyEvent.VK_U) {
                    Client.sendCommand("ultrabossteleport");
                }
            }
            if (i == KeyEvent.VK_ESCAPE) {
                Client.setTab(3);
            } else if (i == KeyEvent.VK_F1) {
                Client.setTab(3);
            } else if (i == KeyEvent.VK_F2) {
                Client.setTab(4);
            } else if (i == KeyEvent.VK_F3) {
                Client.setTab(5);
            } else if (i == KeyEvent.VK_F4) {
                Client.setTab(6);
            } else if (i == KeyEvent.VK_F5) {
                Client.setTab(0);
            }
        } else {
            if (i == KeyEvent.VK_ESCAPE) {
                Client.setTab(0);
            } else if (i == KeyEvent.VK_F1) {
                Client.setTab(3);
            } else if (i == KeyEvent.VK_F2) {
                Client.setTab(4);
            } else if (i == KeyEvent.VK_F3) {
                Client.setTab(5);
            } else if (i == KeyEvent.VK_F4) {
                Client.setTab(6);
            } else if (i == KeyEvent.VK_F5) {
                Client.setTab(0);
            }
        }
        if (j < 30)
            j = 0;
        if (i == 37)
            j = 1;
        if (i == 39)
            j = 2;
        if (i == 38)
            j = 3;
        if (i == 40)
            j = 4;
        if (i == 17)
            j = 5;
        if (i == 8)
            j = 8;
        if (i == 127)
            j = 8;
        if (i == 9)
            j = 9;
        if (i == 10)
            j = 10;
        if (i >= 112 && i <= 123)
            j = (1008 + i) - 112;
        if (i == 36)
            j = 1000;
        if (i == 35)
            j = 1001;
        if (i == 33)
            j = 1002;
        if (i == 34)
            j = 1003;
        if (j > 0 && j < 128)
            keyArray[j] = 1;
        if (j > 4) {
            charQueue[writeIndex] = j;
            writeIndex = writeIndex + 1 & 0x7f;
        }
    }

    public void replyLastPM() {

    }

    public final void keyReleased(KeyEvent keyevent) {

        idleTime = 0;
        int i = keyevent.getKeyCode();
        char c = keyevent.getKeyChar();
        if (i == KeyEvent.VK_SHIFT) {
            shiftDown = false;
        }
        if (i == KeyEvent.VK_CONTROL) {
            Client.controlIsDown = false;
        }
        if (i == 17) {
            resizing = false;
        }
        if (c < '\036') {
            c = '\0';
        }
        if (i == 37) {
            c = '\001';
        }
        if (i == 39) {
            c = '\002';
        }
        if (i == 38) {
            c = '\003';
        }
        if (i == 40) {
            c = '\004';
        }
        if (i == 17) {
            c = '\005';
        }
        if (i == 8) {
            c = '\b';
        }
        if (i == 127) {
            c = '\b';
        }
        if (i == 9) {
            c = '\t';
        }
        if (i == 10) {
            c = '\n';
        }
        if (c > 0 && c < '\200') {
            keyArray[c] = 0;
        }
    }

    public final void keyTyped(KeyEvent keyevent) {
    }

    public final int readChar(int i) {
        while (i >= 0) {
            int j = 1;
            while (j > 0) {
                j++;
            }
        }
        int k = -1;
        if (writeIndex != readIndex) {
            k = charQueue[readIndex];
            readIndex = readIndex + 1 & 0x7f;
        }
        return k;
    }

    public final void focusGained(FocusEvent focusevent) {
        awtFocus = true;
        shiftDown = false;
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    public final void focusLost(FocusEvent focusevent) {
        awtFocus = false;
        shiftDown = false;
        for (int i = 0; i < 128; i++) {
            keyArray[i] = 0;
        }

    }

    public final void windowActivated(WindowEvent windowevent) {
    }

    public final void windowClosed(WindowEvent windowevent) {
    }

    public final void windowClosing(WindowEvent windowevent) {
        try {
           int result = JOptionPane.showConfirmDialog(
                   this,
                   "Are you sure you want to exit?", "Exit",
                   JOptionPane.OK_CANCEL_OPTION,
                   JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void windowDeactivated(WindowEvent windowevent) {
    }

    public final void windowDeiconified(WindowEvent windowevent) {
    }

    public final void windowIconified(WindowEvent windowevent) {
    }

    public final void windowOpened(WindowEvent windowevent) {
    }

    void startUp() {
    }

    void processGameLoop() {
    }

    void cleanUpForQuit() {
    }

    void processDrawing() {
    }

    void raiseWelcomeScreen() {
    }

    Component getGameComponent() {
        if (mainFrame != null && !isApplet) {
            return mainFrame;
        } else {
            return this;
        }
    }

    public void startRunnable(Runnable runnable, int i) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(i);
    }

    void prepareGraphics() {
        while (graphics == null) {
            graphics = (isApplet ? this : mainFrame).getGraphics();
            try {
                getGameComponent().repaint();
            } catch (Exception _ex) {
            }

            try {
                // Thread.sleep(1000L);
            } catch (Exception _ex) {
            }
        }
        Font font = new Font("Serif", 1, 16);
        graphics.setFont(font);
        graphics.setColor(Color.white);
    }

    void resetGraphic() {
        graphics = (isApplet ? this : mainFrame).getGraphics();
        try {
            getGameComponent().repaint();
        } catch (Exception _ex) {
        }
    }

    private boolean dialogueSkipKeys(int key) {
        final Client client = Client.getClient();
        if (Client.consoleOpen || client.backDialogID == -1 || client.continuedDialogue || client.inputDialogState > 0
                || client.showInput) {
            return false;
        }

        int select = -1;
        int maxOptions = 5;
        if (key >= 49 && key <= 49 + maxOptions) {
            final int index = key - 49;
            switch (client.backDialogID) {
                case 2459:
                    select = 2461 + index;
                    break;
                case 2469:
                    select = 2471 + index;
                    break;
                case 2480:
                    select = 2482 + index;
                    break;
                case 2492:
                    select = 2494 + index;
                    break;
                default:
                    select = -1;
                    break;
            }
        }
        if (select != -1) {
            client.stream.createFrame(185);
            client.stream.writeWord(select);
            return true;
        }
        return false;
    }
}
