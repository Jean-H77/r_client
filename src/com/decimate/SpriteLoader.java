package com.decimate;

import com.decimate.security.Decryption;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.READ;

public final class SpriteLoader implements Closeable {

    private Sprite[] cache;

    private static final Map<String, Sprite> spriteMap = new HashMap<>();

    private FileChannel dataChannel;
    private FileChannel metaChannel;

    private static BufferedImage convert(BufferedImage image, int type) {
        BufferedImage converted = new BufferedImage(image.getWidth(), image.getHeight(), type);
        converted.getGraphics().drawImage(image, 0, 0, null);
        return converted;
    }

    public void init(Path dataFile, Path metaFile) throws IOException {
        if (Configuration.localHost) {
            dataChannel = FileChannel.open(dataFile, READ);
            metaChannel = FileChannel.open(metaFile, READ);

            final int spriteCount = Math.toIntExact(metaChannel.size() / 10);
            cache = new Sprite[spriteCount];
            System.out.println("Loaded "+spriteCount+" sprites!");
            return;
        }
        byte[] data = Files.readAllBytes(dataFile);
        java.nio.ByteBuffer dataBuffer = java.nio.ByteBuffer.allocate(data.length);
        dataBuffer.put(data, 17, data.length - 17);

        byte[] meta = Files.readAllBytes(metaFile);
        java.nio.ByteBuffer metaBuffer = java.nio.ByteBuffer.allocate(meta.length);
        metaBuffer.put(meta, 17, meta.length - 17);

        Decryption.writeTempFile("sprites-data-file", dataBuffer.array());

        Decryption.writeTempFile("sprites-meta-file", metaBuffer.array());

        Path dataPath = Decryption.getTempFile("sprites-data-file").toPath();

        Path metaPath = Decryption.getTempFile("sprites-meta-file").toPath();

        dataChannel = FileChannel.open(dataPath, READ);
        metaChannel = FileChannel.open(metaPath, READ);

        final int spriteCount = Math.toIntExact(metaChannel.size() / 10);
        cache = new Sprite[spriteCount];
        System.out.println("Loaded "+spriteCount+" sprites!");
    }

    public Sprite lookup(int id) {
        try {
            if (contains(id)) {
                return cache[id];
            }

            if (!dataChannel.isOpen() || !metaChannel.isOpen()) {
                System.out.println("Sprite channels are closed!");
                return null;
            }

            final int entries = Math.toIntExact(metaChannel.size() / 10);

            if (id > entries) {
                System.out.printf("id=%d > size=%d%n", id, entries);
                return null;
            }

            metaChannel.position(id * 10);

            final ByteBuffer metaBuf = ByteBuffer.allocate(10);
            metaChannel.read(metaBuf);
            metaBuf.flip();

            final int pos = ((metaBuf.get() & 0xFF) << 16) | ((metaBuf.get() & 0xFF) << 8) | (metaBuf.get() & 0xFF);
            final int len = ((metaBuf.get() & 0xFF) << 16) | ((metaBuf.get() & 0xFF) << 8) | (metaBuf.get() & 0xFF);
            final int offsetX = metaBuf.getShort() & 0xFF;
            final int offsetY = metaBuf.getShort() & 0xFF;

            final ByteBuffer dataBuf = ByteBuffer.allocate(len);

            dataChannel.position(pos);
            dataChannel.read(dataBuf);
            dataBuf.flip();

            try (InputStream is = new ByteArrayInputStream(dataBuf.array())) {

                BufferedImage bimage = ImageIO.read(is);

                if (bimage == null) {
                    System.out.printf("Could not read image at %d%n", id);
                    return null;
                }

                if (bimage.getType() != BufferedImage.TYPE_INT_ARGB) {
                    bimage = convert(bimage, BufferedImage.TYPE_INT_ARGB);
                }

                final int[] pixels = ((DataBufferInt) bimage.getRaster().getDataBuffer()).getData();

                final Sprite sprite = new Sprite(bimage.getWidth(), bimage.getHeight(), offsetX, offsetY, pixels);

                // cache so we don't have to perform I/O calls again
                cache[id] = sprite;

                return sprite;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("No sprite found for id=%d%n", id);
        return null;
    }
    public static Sprite lookup(String id) {
        if(spriteMap.containsKey(id))
            return spriteMap.get(id);
        Sprite sprite = new Sprite(id);
        if(sprite == null) {
            System.out.println("Can't find sprite " + id);
            return null;
        }
        spriteMap.put(id, sprite);
        return sprite;
    }
    public static Sprite setHeight(String id, int height) {
        if(!spriteMap.containsKey(id)) {
            lookup(id);
            return setHeight(id, height);
        }
        Sprite sprite = lookup(id);
        sprite.myHeight = height;
        sprite.height = height;
        spriteMap.put(id, sprite);
        return sprite;
    }

    public boolean contains(int id) {
        return id < cache.length && cache[id] != null;
    }

    public void set(int id, Sprite sprite) {
        if (!contains(id)) {
            return;
        }

        cache[id] = sprite;
    }

    public void close() throws IOException {
        dataChannel.close();
        metaChannel.close();
    }
}