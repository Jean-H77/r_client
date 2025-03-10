package com.decimate;


import java.awt.*;

public class RSFontSystem extends DrawingArea {

    public static String aRSString_4135;
    public static String startTransparency;
    public static String startDefaultShadow;
    public static String endShadow = "/shad";
    public static String endEffect;
    public static String aRSString_4143;
    public static String endStrikethrough = "/str";
    public static String aRSString_4147;
    public static String startColor;
    public static String lineBreak;
    public static String startStrikethrough;
    public static String endColor;
    public static String startImage;
    public static String endUnderline;
    public static String defaultStrikethrough;
    public static String startShadow;
    public static String startEffect;
    public static String aRSString_4162;
    public static String aRSString_4163;
    public static String endTransparency;
    public static String aRSString_4165;
    public static String startUnderline;
    public static String startDefaultUnderline;
    public static String aRSString_4169;
    public static String[] splitTextStrings;
    public static int defaultColor;
    public static int textShadowColor;
    public static int strikethroughColor;
    public static int defaultTransparency;
    public static int anInt4175;
    public static int underlineColor;
    public static int defaultShadow;
    public static int anInt4178;
    public static int transparency;
    public static int textColor;

    static {
        startTransparency = "trans=";
        startStrikethrough = "str=";
        startDefaultShadow = "shad";
        startColor = "col=";
        lineBreak = "br";
        defaultStrikethrough = "str";
        endUnderline = "/u";
        startImage = "img=";
        startShadow = "shad=";
        startUnderline = "u=";
        endColor = "/col";
        startDefaultUnderline = "u";
        endTransparency = "/trans";

        aRSString_4143 = Integer.toString(100);
        aRSString_4135 = "nbsp";
        aRSString_4169 = "reg";
        aRSString_4165 = "times";
        aRSString_4162 = "shy";
        aRSString_4163 = "copy";
        endEffect = "gt";
        aRSString_4147 = "euro";
        startEffect = "lt";
        defaultTransparency = 256;
        defaultShadow = -1;
        anInt4175 = 0;
        textShadowColor = -1;
        textColor = 0;
        defaultColor = 0;
        strikethroughColor = -1;
        splitTextStrings = new String[100];
        underlineColor = -1;
        anInt4178 = 0;
        transparency = 256;
    }

    public int baseCharacterHeight = 0;
    public int anInt4142;
    public int anInt4144;
    public int[] characterDrawYOffsets;
    public int[] characterHeights;
    public int[] characterDrawXOffsets;
    public int[] characterWidths;
    public int[] iconWidths;
    public byte[] aByteArray4151;
    public byte[][] fontPixels;
    public int[] characterScreenWidths;

    public RSFontSystem(boolean TypeFont, String s, CacheArchive archive) {
        try {
            int length = (s.equals("regularhit") || s.equals("bighit")) ? 58 : 256;
            fontPixels = new byte[length][];
            characterWidths = new int[length];
            characterHeights = new int[length];
            characterDrawXOffsets = new int[length];
            characterDrawYOffsets = new int[length];
            characterScreenWidths = new int[length];
            Stream stream = new Stream(archive.getDataForName(s + ".dat"));
            Stream stream_1 = new Stream(archive.getDataForName("index.dat"));
            stream_1.currentOffset = stream.readShort() + 4;
            int k = stream_1.readUByte();
            if (k > 0) {
                stream_1.currentOffset += 3 * (k - 1);
            }
            for (int l = 0; l < length; l++) {
                characterDrawXOffsets[l] = stream_1.readUByte();
                characterDrawYOffsets[l] = stream_1.readUByte();
                int i1 = characterWidths[l] = stream_1.readShort();
                int j1 = characterHeights[l] = stream_1.readShort();
                int k1 = stream_1.readUByte();
                int l1 = i1 * j1;
                fontPixels[l] = new byte[l1];
                if (k1 == 0) {
                    for (int i2 = 0; i2 < l1; i2++) {
                        fontPixels[l][i2] = stream.readByte();
                    }

                } else if (k1 == 1) {
                    for (int j2 = 0; j2 < i1; j2++) {
                        for (int l2 = 0; l2 < j1; l2++) {
                            fontPixels[l][j2 + l2 * i1] = stream.readByte();
                        }

                    }

                }
                if (j1 > baseCharacterHeight && l < 128) {
                    baseCharacterHeight = j1;
                }
                characterDrawXOffsets[l] = 1;
                characterScreenWidths[l] = i1 + 2;
                int k2 = 0;
                for (int i3 = j1 / 7; i3 < j1; i3++) {
                    k2 += fontPixels[l][i3 * i1];
                }

                if (k2 <= j1 / 7) {
                    characterScreenWidths[l]--;
                    characterDrawXOffsets[l] = 0;
                }
                k2 = 0;
                for (int j3 = j1 / 7; j3 < j1; j3++) {
                    k2 += fontPixels[l][(i1 - 1) + j3 * i1];
                }

                if (k2 <= j1 / 7) {
                    characterScreenWidths[l]--;
                }
            }

            if (TypeFont) {
                characterScreenWidths[32] = characterScreenWidths[73];
            } else {
                characterScreenWidths[32] = characterScreenWidths[105];
            }
        } catch (Exception e) {
        }
    }

    private static String convertSyntax(String text, String regex, String replacement) {
        if (text == null) {
            return null;
        }

        if (text.contains(regex)) {
            return text.replaceAll(regex, replacement);
        }
        return text;
    }

    public static String convertSyntax(String text) {
        text = convertSyntax(text, "@red@", "<col=ff0000>");
        text = convertSyntax(text, "@gre@", "<col=65280>");
        text = convertSyntax(text, "@blu@", "<col=255>");
        text = convertSyntax(text, "@yel@", "<col=ffff00>");
        text = convertSyntax(text, "@cya@", "<col=65535>");
        text = convertSyntax(text, "@mag@", "<col=ff00ff>");
        text = convertSyntax(text, "@whi@", "<col=ffffff>");
        text = convertSyntax(text, "@lre@", "<col=ff9040>");
        text = convertSyntax(text, "@dre@", "<col=800000>");
        text = convertSyntax(text, "@bla@", "<col=0>");
        text = convertSyntax(text, "@or1@", "<col=ffb000>");
        text = convertSyntax(text, "@or2@", "<col=ff7000>");
        text = convertSyntax(text, "@or3@", "<col=ff3000>");
        text = convertSyntax(text, "@or4@", "<col=FF981F>");
        text = convertSyntax(text, "@gr1@", "<col=c0ff00>");
        text = convertSyntax(text, "@gr2@", "<col=80ff00>");
        text = convertSyntax(text, "@gr3@", "<col=40ff00>");
        text = convertSyntax(text, "@cr1@", "<img=1>");
        text = convertSyntax(text, "@cr2@", "<img=2>");
        text = convertSyntax(text, "@cr3@", "<img=3>");
        text = convertSyntax(text, "@dev@", "<img=3>");
        text = convertSyntax(text, "@des@", "<img=4>");
        text = convertSyntax(text, "@vet@", "<img=5>");
        text = convertSyntax(text, "@don@", "<img=6>");
        text = convertSyntax(text, "@or2@", "<col=ff7000>");
        text = convertSyntax(text, "@ad1@", "<col=fac923>");
        text = convertSyntax(text, "@ad2@", "<col=ed8c25>");
        text = convertSyntax(text, "@ad3@", "<col=55e665>");
        text = convertSyntax(text, "@ad4@", "<col=fbe789>");

        //55e665
        //fbe789
        return text;
    }

    public static void nullLoader() {
        startEffect = null;
        endEffect = null;
        aRSString_4135 = null;
        aRSString_4162 = null;
        aRSString_4165 = null;
        aRSString_4147 = null;
        aRSString_4163 = null;
        aRSString_4169 = null;
        startImage = null;
        lineBreak = null;
        startColor = null;
        endColor = null;
        startTransparency = null;
        endTransparency = null;
        startUnderline = null;
        startDefaultUnderline = null;
        endUnderline = null;
        startShadow = null;
        startDefaultShadow = null;
        endShadow = null;
        startStrikethrough = null;
        defaultStrikethrough = null;
        endStrikethrough = null;
        aRSString_4143 = null;
        splitTextStrings = null;
    }

    public static void createTransparentCharacterPixels(int[] is, byte[] is_0_, int i, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_) {
        i = ((i & 0xff00ff) * i_7_ & ~0xff00ff) + ((i & 0xff00) * i_7_ & 0xff0000) >> 8;
        i_7_ = 256 - i_7_;
        for (int i_8_ = -i_4_; i_8_ < 0; i_8_++) {
            for (int i_9_ = -i_3_; i_9_ < 0; i_9_++) {
                if (is_0_[i_1_++] != 0) {
                    int i_10_ = is[i_2_];
                    is[i_2_++] = ((((i_10_ & 0xff00ff) * i_7_ & ~0xff00ff) + ((i_10_ & 0xff00) * i_7_ & 0xff0000)) >> 8) + i;
                } else {
                    i_2_++;
                }
            }
            i_2_ += i_5_;
            i_1_ += i_6_;
        }
    }

    public static void createCharacterPixels(int[] is, byte[] is_24_, int i, int i_25_, int i_26_, int i_27_, int i_28_, int i_29_, int i_30_) {
        int i_31_ = -(i_27_ >> 2);
        i_27_ = -(i_27_ & 0x3);
        for (int i_32_ = -i_28_; i_32_ < 0; i_32_++) {
            for (int i_33_ = i_31_; i_33_ < 0; i_33_++) {
                if (is_24_[i_25_++] != 0) {
                    is[i_26_++] = i;
                } else {
                    i_26_++;
                }
                if (is_24_[i_25_++] != 0) {
                    is[i_26_++] = i;
                } else {
                    i_26_++;
                }
                if (is_24_[i_25_++] != 0) {
                    is[i_26_++] = i;
                } else {
                    i_26_++;
                }
                if (is_24_[i_25_++] != 0) {
                    is[i_26_++] = i;
                } else {
                    i_26_++;
                }
            }
            for (int i_34_ = i_27_; i_34_ < 0; i_34_++) {
                if (is_24_[i_25_++] != 0) {
                    is[i_26_++] = i;
                } else {
                    i_26_++;
                }
            }
            i_26_ += i_29_;
            i_25_ += i_30_;
        }
    }

    public static String removeColors(String text) {
        //System.out.println(text);
        text = convertSyntax(text, "@red@", "");
        text = convertSyntax(text, "@gre@", "");
        text = convertSyntax(text, "@blu@", "");
        text = convertSyntax(text, "@yel@", "");
        text = convertSyntax(text, "@cya@", "");
        text = convertSyntax(text, "@mag@", "");
        text = convertSyntax(text, "@whi@", "");
        text = convertSyntax(text, "@lre@", "");
        text = convertSyntax(text, "@dre@", "");
        text = convertSyntax(text, "@bla@", "");
        text = convertSyntax(text, "@or1@", "");
        text = convertSyntax(text, "@or2@", "");
        text = convertSyntax(text, "@ad1@", "");
        text = convertSyntax(text, "@ad2@", "");
        text = convertSyntax(text, "@ad3@", "");
        text = convertSyntax(text, "@ad4@", "");
        text = convertSyntax(text, "@pr2@", "");
        text = convertSyntax(text, "", "");
        text = convertSyntax(text, "@or4@", "");
        text = convertSyntax(text, "@gr1@", "");
        text = convertSyntax(text, "@gr2@", "");
        text = convertSyntax(text, "@gr3@", "");
        text = convertSyntax(text, "@cr1@", "");
        text = convertSyntax(text, "@cr2@", "");
        text = convertSyntax(text, "@cr3@", "");
        text = convertSyntax(text, "@dev@", "");
        text = convertSyntax(text, "@des@", "");
        text = convertSyntax(text, "@vet@", "");
        text = convertSyntax(text, "@don@", "");
        text = convertSyntax(text, "@or2@", "");
        text = convertSyntax(text, "@ad1@", "");
        text = convertSyntax(text, "@ad2@", "");
        text = convertSyntax(text, "@purp@", "");

        text = convertSyntax(text, "@vea@", "");
        text = convertSyntax(text, "@eas@", "");
        text = convertSyntax(text, "@med@", "");
        text = convertSyntax(text, "@har@", "");
        text = convertSyntax(text, "@vha@", "");
        text = convertSyntax(text, "@bl2@", "");
        text = convertSyntax(text, "@gry@", "");
        text = convertSyntax(text, "@pnk@", "");
        text = convertSyntax(text, "@pr3@", "");
        text = convertSyntax(text, "@skb@", "");
        if (text != null) {
            while (text.contains("<")) {
                if (text.contains("<")) {
                    int index = text.indexOf("<");
                    int index1 = text.indexOf(">");
                    text = text.substring(0, index) + text.substring(index1 + 1);
                }
            }
        }

        return text;
    }

    public void drawStringMoveY(String string, int drawX, int drawY, int color, int shadow, int randomMod, int randomMod2) {
        if (string != null) {
            setColorAndShadow(color, shadow);
            double d = 7.0 - (double) randomMod2 / 8.0;
            if (d < 0.0) {
                d = 0.0;
            }
            int[] yOffset = new int[string.length()];
            for (int index = 0; index < string.length(); index++) {
                yOffset[index] = (int) (Math.sin((double) index / 1.5 + (double) randomMod) * d);
            }
            drawBaseStringMoveXY(string, drawX - getTextWidth(string) / 2, drawY, null, yOffset);
        }
    }

    public int getCharacterWidth(int i) {
        return characterScreenWidths[i & 0xff];
    }

    public void setDefaultTextEffectValues(int color, int shadow, int trans) {
        strikethroughColor = -1;
        underlineColor = -1;
        textShadowColor = defaultShadow = shadow;
        textColor = defaultColor = color;
        transparency = defaultTransparency = trans;
        anInt4178 = 0;
        anInt4175 = 0;
    }

    public void drawCenteredStringMoveXY(String string, int drawX, int drawY, int color, int shadow, int randomMod) {
        if (string != null) {
            setColorAndShadow(color, shadow);
            int[] xMods = new int[string.length()];
            int[] yMods = new int[string.length()];
            for (int index = 0; index < string.length(); index++) {
                xMods[index] = (int) (Math.sin((double) index / 5.0 + (double) randomMod / 5.0) * 5.0);
                yMods[index] = (int) (Math.sin((double) index / 3.0 + (double) randomMod / 5.0) * 5.0);
            }
            drawBaseStringMoveXY(string, drawX - getTextWidth(string) / 2, drawY, xMods, yMods);
        }
    }

    public void drawCenteredStringMoveY(String class100, int drawX, int drawY, int color, int shadow, int i_54_) {
        if (class100 != null) {
            setColorAndShadow(color, shadow);
            int[] yOffset = new int[class100.length()];
            for (int index = 0; index < class100.length(); index++) {
                yOffset[index] = (int) (Math.sin((double) index / 2.0 + (double) i_54_ / 5.0) * 5.0);
            }
            drawBaseStringMoveXY(class100, drawX - getTextWidth(class100) / 2, drawY, null, yOffset);
        }
    }

    public void drawBasicString(String string, int drawX, int drawY) {
        string = convertSyntax(string);
        drawY -= baseCharacterHeight;
        int startIndex = -1;
        //string = handleOldSyntax(string);
        for (int currentCharacter = 0; currentCharacter < string.length(); currentCharacter++) {
            int character = string.charAt(currentCharacter);
            if (character > 255) {
                character = 32;
            }
            if (character == 60) {
                startIndex = currentCharacter;
            } else {
                if (character == 62 && startIndex != -1) {
                    String effectString = string.substring(startIndex + 1, currentCharacter);
                    startIndex = -1;
                    if (effectString.equals(startEffect)) {
                        character = 60;
                    } else if (effectString.equals(endEffect)) {
                        character = 62;
                    } else if (effectString.equals(aRSString_4135)) {
                        character = 160;
                    } else if (effectString.equals(aRSString_4162)) {
                        character = 173;
                    } else if (effectString.equals(aRSString_4165)) {
                        character = 215;
                    } else if (effectString.equals(aRSString_4147)) {
                        character = 128;
                    } else if (effectString.equals(aRSString_4163)) {
                        character = 169;
                    } else if (effectString.equals(aRSString_4169)) {
                        character = 174;
                    } else {
                        if (effectString.startsWith(startImage)) {
                            try {
                                int imageId = Integer.parseInt(effectString.substring(4));
                                final int animatedRank = Client.getAnimatedIconId(imageId);
                              //  if (animatedRank != -1) {
                                 //   Sprite icon = Client.animatedRankIcon[animatedRank];
                                  //  if (icon != null) {
                                  //      icon.drawAdvancedSprite(drawX, (drawY + baseCharacterHeight - icon.myHeight));
                                  //      drawX += icon.myWidth;
                                 //   }
                              //  } else {
                                    Sprite icon = Client.spriteLoader.lookup(679 + imageId);
                                    if (icon != null) {
                                        int iconModY = icon.myHeight;
                                        if (imageId >= 8) {
                                            iconModY -= 2;
                                        }
                                        if (transparency == 256) {
                                            icon.drawSprite(drawX, (drawY + baseCharacterHeight - iconModY));
                                        } else {
                                            icon.drawSprite(drawX, (drawY + baseCharacterHeight - iconModY), transparency);
                                        }
                                        drawX += icon.myWidth;
                                    }
                               // }
                            } catch (Exception exception) {
                                /* empty */
                            }
                        } else {
                            setTextEffects(effectString);
                        }
                        continue;
                    }
                }
                if (startIndex == -1) {
                    int width = characterWidths[character];
                    int height = characterHeights[character];
                    if (character != 32) {
                        if (transparency == 256) {
                            if (textShadowColor != -1) {
                                drawCharacter(character, drawX + characterDrawXOffsets[character] + 1, drawY + characterDrawYOffsets[character] + 1, width, height, textShadowColor, true);
                            }
                            drawCharacter(character, drawX + characterDrawXOffsets[character], drawY + characterDrawYOffsets[character], width, height, textColor, false);
                        } else {
                            if (textShadowColor != -1) {
                                drawTransparentCharacter(character, drawX + characterDrawXOffsets[character] + 1, drawY + characterDrawYOffsets[character] + 1, width, height, textShadowColor, transparency, true);
                            }
                            drawTransparentCharacter(character, drawX + characterDrawXOffsets[character], drawY + characterDrawYOffsets[character], width, height, textColor, transparency, false);
                        }
                    } else if (anInt4178 > 0) {
                        anInt4175 += anInt4178;
                        drawX += anInt4175 >> 8;
                        anInt4175 &= 0xff;
                    }
                    int lineWidth = characterScreenWidths[character];
                    if (strikethroughColor != -1) {
                        DrawingArea474.drawHorizontalLine(drawX, drawY + (int) ((double) baseCharacterHeight * 0.69999999999999996D), lineWidth, strikethroughColor);
                    }
                    if (underlineColor != -1) {
                        DrawingArea474.drawHorizontalLine(drawX, drawY + baseCharacterHeight, lineWidth, underlineColor);
                    }
                    drawX += lineWidth;
                }
            }
        }
    }

    public void drawRAString(String string, int drawX, int drawY, int color, int shadow) {
        if (string != null) {
            setColorAndShadow(color, shadow);
            drawBasicString(string, drawX - getTextWidth(string), drawY);
        }
    }

    public void drawBaseStringMoveXY(String string, int drawX, int drawY, int[] xModifier, int[] yModifier) {
        drawY -= baseCharacterHeight;
        int startIndex = -1;
        int modifierOffset = 0;
        for (int currentCharacter = 0; currentCharacter < string.length(); currentCharacter++) {
            int character = string.charAt(currentCharacter);
            if (character == 60) {
                startIndex = currentCharacter;
            } else {
                if (character == 62 && startIndex != -1) {
                    String effectString = string.substring(startIndex + 1, currentCharacter);
                    startIndex = -1;
                    if (effectString.equals(startEffect)) {
                        character = 60;
                    } else if (effectString.equals(endEffect)) {
                        character = 62;
                    } else if (effectString.equals(aRSString_4135)) {
                        character = 160;
                    } else if (effectString.equals(aRSString_4162)) {
                        character = 173;
                    } else if (effectString.equals(aRSString_4165)) {
                        character = 215;
                    } else if (effectString.equals(aRSString_4147)) {
                        character = 128;
                    } else if (effectString.equals(aRSString_4163)) {
                        character = 169;
                    } else if (effectString.equals(aRSString_4169)) {
                        character = 174;
                    } else {
                        if (effectString.startsWith(startImage)) {
                            try {
                                int xModI;
                                if (xModifier != null) {
                                    xModI = xModifier[modifierOffset];
                                } else {
                                    xModI = 0;
                                }
                                int yMod;
                                if (yModifier != null) {
                                    yMod = yModifier[modifierOffset];
                                } else {
                                    yMod = 0;
                                }
                                modifierOffset++;
                                int iconId = Integer.valueOf(effectString.substring(4));
                                Sprite icon = Client.spriteLoader.lookup(679 + iconId);
                                int iconOffsetY = icon.myHeight;
                                if (transparency == 256) {
                                    icon.drawSprite(drawX + xModI, (drawY + baseCharacterHeight - iconOffsetY + yMod));
                                } else {
                                    icon.drawSprite(drawX + xModI, (drawY + baseCharacterHeight - iconOffsetY + yMod), transparency);
                                }
                                drawX += icon.myWidth;
                            } catch (Exception exception) {
                                /* empty */
                            }
                        } else {
                            setTextEffects(effectString);
                        }
                        continue;
                    }
                }
                if (startIndex == -1) {
                    int width = characterWidths[character];
                    int height = characterHeights[character];
                    int xOff;
                    if (xModifier != null) {
                        xOff = xModifier[modifierOffset];
                    } else {
                        xOff = 0;
                    }
                    int yOff;
                    if (yModifier != null) {
                        yOff = yModifier[modifierOffset];
                    } else {
                        yOff = 0;
                    }
                    modifierOffset++;
                    if (character != 32) {
                        if (transparency == 256) {
                            if (textShadowColor != -1) {
                                drawCharacter(character, (drawX + characterDrawXOffsets[character] + 1 + xOff), (drawY + characterDrawYOffsets[character] + 1 + yOff), width, height, textShadowColor, true);
                            }
                            drawCharacter(character, drawX + characterDrawXOffsets[character] + xOff, drawY + characterDrawYOffsets[character] + yOff, width, height, textColor, false);
                        } else {
                            if (textShadowColor != -1) {
                                drawTransparentCharacter(character, (drawX + characterDrawXOffsets[character] + 1 + xOff), (drawY + characterDrawYOffsets[character] + 1 + yOff), width, height, textShadowColor, transparency, true);
                            }
                            drawTransparentCharacter(character, drawX + characterDrawXOffsets[character] + xOff, drawY + characterDrawYOffsets[character] + yOff, width, height, textColor, transparency, false);
                        }
                    } else if (anInt4178 > 0) {
                        anInt4175 += anInt4178;
                        drawX += anInt4175 >> 8;
                        anInt4175 &= 0xff;
                    }
                    int i_109_ = characterScreenWidths[character];
                    if (strikethroughColor != -1) {
                        DrawingArea474.drawHorizontalLine(drawX, drawY + (int) ((double) baseCharacterHeight * 0.7), i_109_, strikethroughColor);
                    }
                    if (underlineColor != -1) {
                        DrawingArea474.drawHorizontalLine(drawX, drawY + baseCharacterHeight, i_109_, underlineColor);
                    }
                    drawX += i_109_;
                }
            }
        }
    }

    public void setTextEffects(String string) {
        do {
            try {
                if (string.startsWith(startColor)) {
                    String color = string.substring(4);
                    textColor = color.length() < 6 ? Color.decode(color).getRGB() : Integer.parseInt(color, 16);
                } else if (string.equals(endColor)) {
                    textColor = defaultColor;
                } else if (string.startsWith(startTransparency)) {
                    transparency = Integer.valueOf(string.substring(6));
                } else if (string.equals(endTransparency)) {
                    transparency = defaultTransparency;
                } else if (string.startsWith(startStrikethrough)) {
                    String color = string.substring(4);
                    strikethroughColor = color.length() < 6 ? Color.decode(color).getRGB() : Integer.parseInt(color, 16);
                } else if (string.equals(defaultStrikethrough)) {
                    strikethroughColor = 8388608;
                } else if (string.equals(endStrikethrough)) {
                    strikethroughColor = -1;
                } else if (string.startsWith(startUnderline)) {
                    String color = string.substring(2);
                    underlineColor = color.length() < 6 ? Color.decode(color).getRGB() : Integer.parseInt(color, 16);
                } else if (string.equals(startDefaultUnderline)) {
                    underlineColor = 0;
                } else if (string.equals(endUnderline)) {
                    underlineColor = -1;
                } else if (string.startsWith(startShadow)) {
                    String color = string.substring(5);
                    textShadowColor = color.length() < 6 ? Color.decode(color).getRGB() : Integer.parseInt(color, 16);
                } else if (string.equals(startDefaultShadow)) {
                    textShadowColor = 0;
                } else if (string.equals(endShadow)) {
                    textShadowColor = defaultShadow;
                } else {
                    if (!string.equals(lineBreak)) {
                        break;
                    }
                    setDefaultTextEffectValues(defaultColor, defaultShadow, defaultTransparency);
                }
            } catch (Exception exception) {
                break;
            }
            break;
        } while (false);
    }

    public void setColorAndShadow(int color, int shadow) {
        strikethroughColor = -1;
        underlineColor = -1;
        textShadowColor = defaultShadow = shadow;
        textColor = defaultColor = color;
        transparency = defaultTransparency = 256;
        anInt4178 = 0;
        anInt4175 = 0;
    }

    public int getTextWidth(String string) {
        if (string == null) {
            return 0;
        }
        string = convertSyntax(string);
        int startIndex = -1;
        int finalWidth = 0;
        for (int currentCharacter = 0; currentCharacter < string.length(); currentCharacter++) {
            int character = string.charAt(currentCharacter);
            if (character > 255) {
                character = 32;
            }
            if (character == 60) {
                startIndex = currentCharacter;
            } else {
                if (character == 62 && startIndex != -1) {
                    String effectString = string.substring(startIndex + 1, currentCharacter);
                    startIndex = -1;
                    if (effectString.equals(startEffect)) {
                        character = 60;
                    } else if (effectString.equals(endEffect)) {
                        character = 62;
                    } else if (effectString.equals(aRSString_4135)) {
                        character = 160;
                    } else if (effectString.equals(aRSString_4162)) {
                        character = 173;
                    } else if (effectString.equals(aRSString_4165)) {
                        character = 215;
                    } else if (effectString.equals(aRSString_4147)) {
                        character = 128;
                    } else if (effectString.equals(aRSString_4163)) {
                        character = 169;
                    } else if (effectString.equals(aRSString_4169)) {
                        character = 174;
                    } else {
                        if (effectString.startsWith(startImage)) {
                            try {
                                int iconId = Integer.parseInt(effectString.substring(4));
                                final int animatedRank = Client.getAnimatedIconId(iconId);
                                Sprite icon = null;
                              //  if (animatedRank != -1) {
                                //    icon = Client.animatedRankIcon[animatedRank];
                               // } else {
                                    icon = Client.spriteLoader.lookup(679 + iconId);
                                //}
                                if (icon != null) {
                                    finalWidth += icon.myWidth;
                                }
                            } catch (Exception exception) {
                                /* empty */
                            }
                        }
                        continue;
                    }
                }
                if (startIndex == -1) {
                    finalWidth += characterScreenWidths[character];
                }
            }
        }
        return finalWidth;
    }

    public void drawBasicString(String string, int drawX, int drawY, int color, int shadow) {
        if (string != null) {
            setColorAndShadow(color, shadow);
            drawBasicString(string, drawX, drawY);
        }
    }

    public void drawCenteredString(String string, int drawX, int drawY, int color, int shadow) {
        if (string != null) {
            setColorAndShadow(color, shadow);
            //string = handleOldSyntax(string);
            drawBasicString(string, drawX - getTextWidth(string) / 2, drawY);
        }
    }

    public void drawTransparentCharacter(int i, int i_11_, int i_12_, int i_13_, int i_14_, int i_15_, int i_16_, boolean bool) {
        int i_17_ = i_11_ + i_12_ * width;
        int i_18_ = width - i_13_;
        int i_19_ = 0;
        int i_20_ = 0;
        if (i_12_ < topY) {
            int i_21_ = topY - i_12_;
            i_14_ -= i_21_;
            i_12_ = topY;
            i_20_ += i_21_ * i_13_;
            i_17_ += i_21_ * width;
        }
        if (i_12_ + i_14_ > bottomY) {
            i_14_ -= i_12_ + i_14_ - bottomY;
        }
        if (i_11_ < topX) {
            int i_22_ = topX - i_11_;
            i_13_ -= i_22_;
            i_11_ = topX;
            i_20_ += i_22_;
            i_17_ += i_22_;
            i_19_ += i_22_;
            i_18_ += i_22_;
        }
        if (i_11_ + i_13_ > bottomX) {
            int i_23_ = i_11_ + i_13_ - bottomX;
            i_13_ -= i_23_;
            i_19_ += i_23_;
            i_18_ += i_23_;
        }
        if (i_13_ > 0 && i_14_ > 0) {
            createTransparentCharacterPixels(pixels, fontPixels[i], i_15_, i_20_, i_17_, i_13_, i_14_, i_18_, i_19_, i_16_);
        }
    }

    public void drawCharacter(int character, int i_35_, int i_36_, int i_37_, int i_38_, int i_39_, boolean bool) {
        int i_40_ = i_35_ + i_36_ * width;
        int i_41_ = width - i_37_;
        int i_42_ = 0;
        int i_43_ = 0;
        if (i_36_ < topY) {
            int i_44_ = topY - i_36_;
            i_38_ -= i_44_;
            i_36_ = topY;
            i_43_ += i_44_ * i_37_;
            i_40_ += i_44_ * width;
        }
        if (i_36_ + i_38_ > bottomY) {
            i_38_ -= i_36_ + i_38_ - bottomY;
        }
        if (i_35_ < topX) {
            int i_45_ = topX - i_35_;
            i_37_ -= i_45_;
            i_35_ = topX;
            i_43_ += i_45_;
            i_40_ += i_45_;
            i_42_ += i_45_;
            i_41_ += i_45_;
        }
        if (i_35_ + i_37_ > bottomX) {
            int i_46_ = i_35_ + i_37_ - bottomX;
            i_37_ -= i_46_;
            i_42_ += i_46_;
            i_41_ += i_46_;
        }
        if (i_37_ > 0 && i_38_ > 0) {
            try {
                createCharacterPixels(pixels, fontPixels[character], i_39_, i_43_, i_40_, i_37_, i_38_, i_41_, i_42_);
            } catch (Exception e) {

            }

        }
    }
}