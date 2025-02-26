package com.decimate;

public final class FrameReader {

    private static FrameReader[][] sequenceHeaders;
    public SkinList skinList;
    public int transformationCount;
    public int displayLength;
    public int[] transformationIndices;
    public int[] transformX;
    public int[] transformY;
    public int[] transformZ;

    public static void clear() {
        sequenceHeaders = null;
    }

    public static void init() {
        sequenceHeaders = new FrameReader[5000][];
    }

    public static FrameReader get(int frame) {
        try {
            int file = frame >> 16;
            int frameIdx = frame & 65535;
            FrameReader[][] header = sequenceHeaders;
            if (header == null || file > header.length) {
                return null;
            }

            FrameReader[] seqHeader = header[file];
            if (seqHeader == null) {
                Client.instance.onDemandFetcher.requestFileData(1, file);
                return null;
            }
            return header[file][frameIdx];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isFrameNull(int frame) {
        return frame == -1;
    }

    public static void load(int file, byte[] data) {
        try {
            Stream buffer = new Stream(data);
            SkinList base = new SkinList(buffer);
            final int frameCount = buffer.getUnsignedShort();

            FrameReader[][] frames = sequenceHeaders;
            frames[file] = new FrameReader[frameCount];

            for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
                int id = buffer.getUnsignedShort();
                FrameReader frame = frames[file][id] = new FrameReader();
                frame.skinList = base;

                int transformations = buffer.readUnsignedByte();
                int transformation = 0;
                int lastIndex = -1;

                frame.transformationCount = transformations;
                frame.transformationIndices = new int[transformations];
                frame.transformX = new int[transformations];
                frame.transformY = new int[transformations];
                frame.transformZ = new int[transformations];

                for (int i = 0; i < transformations; i++) {
                    int transformationType = base.opcodes[i];

                    int attribute = buffer.readUnsignedByte();
                    if (attribute > 0) {

                        if (transformationType != 0) {
                            for (int next = i - 1; next > lastIndex; next--) {
                                if (base.opcodes[next] != 0) {
                                    continue;
                                }

                                frame.transformationIndices[transformation] = next;
                                frame.transformX[transformation] = 0;
                                frame.transformY[transformation] = 0;
                                frame.transformZ[transformation] = 0;
                                transformation++;
                                break;
                            }
                        }

                        frame.transformationIndices[transformation] = i;
                        short standard = transformationType == 3 ? (short) 128 : 0;

                        frame.transformX[transformation] = (attribute & 1) != 0 ? buffer.readShort2() : standard;
                        frame.transformY[transformation] = (attribute & 2) != 0 ? buffer.readShort2() : standard;
                        frame.transformZ[transformation] = (attribute & 4) != 0 ? buffer.readShort2() : standard;

                        if (transformationType == 2) {
                            frame.transformX[transformation] =
                                    ((frame.transformX[transformation] & 0xff) << 3) + (frame.transformX[transformation] >> 8 & 0x7);
                            frame.transformY[transformation] =
                                    ((frame.transformY[transformation] & 0xff) << 3) + (frame.transformY[transformation] >> 8 & 0x7);
                            frame.transformZ[transformation] =
                                    ((frame.transformZ[transformation] & 0xff) << 3) + (frame.transformZ[transformation] >> 8 & 0x7);
                        }

                        lastIndex = i;
                        transformation++;
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}