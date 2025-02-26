package com.decimate;

public class TextureAnimating {

    private static final int[] Animated_Textures = {17, 24, 34, 40, 51, 52, 53, 54, 55, 56, 57, 58, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 59, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 1};

    private static byte[] pixels = new byte[16384];
    private static int[] hdPixels = new int[16384];

    /**
     * Animates on screen textures with a certain id.
     */
    public static void animateTexture() {
        try {
            for (int textureId : Animated_Textures) {
                Background image = Rasterizer.aBackgroundArray1474s[textureId];
                int indexes = image.imgWidth * image.imgHeight - 1;
                int noise = image.imgWidth * Client.instance.cycleTimer * 2;
                if (image.rawPixels != null) {
                    int[] current = image.getRawPixels();
                    int[] next = hdPixels;
                    for (int i = 0; i <= indexes; i++) {
                        if (current != null) {
                            next[i] = current[i - noise & indexes];
                        }
                    }
                    image.rawPixels = next;
                    hdPixels = current;
                } else {
                    byte[] current = image.imgPixels;
                    byte[] next = pixels;
                    for (int i2 = 0; i2 <= indexes; i2++) {
                        next[i2] = current[i2 - noise & indexes];
                    }
                    image.imgPixels = next;
                    pixels = current;
                }
                Rasterizer.method370(textureId);
            }
        } catch (Exception e) {

        }
    }
}
