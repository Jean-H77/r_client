package com.decimate;

import com.google.common.collect.ImmutableSet;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.decimate.Client.*;

public final class ItemDef {

    private static final Set<Integer> MELEE_GEAR_HIGHLIGHT = ImmutableSet.of(
    15042, 15043, 15032
    );

    private static final Set<Integer> MAGIC_GEAR_HIGHLIGHT = ImmutableSet.of(
    15031, 15041
    );

    private static final Set<Integer> RANGED_GEAR_HIGHLIGHT = ImmutableSet.of(
    15033, 15036, 15034, 15037, 15040, 15035, 15039
    );

    public static final int[] UNTRADEABLE_ITEMS
            = {13661, 13262,
            6529, 6950, 1464, 2996, 2677, 2678, 2679, 2680, 2682,
            2683, 2684, 2685, 2686, 2687, 2688, 2689, 2690,
            6570, 12158, 12159, 12160, 12163, 12161, 12162,
            19143, 19149, 19146, 19157, 19162, 19152, 4155,
            8850, 10551, 8839, 8840, 8842, 11663, 11664,
            11665, 3842, 3844, 3840, 8844, 8845, 8846, 8847,
            8848, 8849, 8850, 10551, 7462, 7461, 7460,
            7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839,
            8840, 8842, 11663, 11664, 11665, 10499, 9748,
            9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808,
            9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772,
            9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770,
            9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806,
            9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812,
            9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762,
            9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792,
            9774, 9771, 9777, 9786, 9810, 9765, 9948, 9949,
            9950, 12169, 12170, 12171, 20671, 14641, 14642,
            6188, 10954, 10956, 10958,
            3057, 3058, 3059, 3060, 3061,
            7594, 7592, 7593, 7595, 7596,
            14076, 14077, 14081,
            10840, 10836, 6858, 6859, 10837, 10838, 10839,
            9925, 9924, 9923, 9922, 9921,
            4084, 4565, 20046, 20044, 20045,
            1050, 14595, 14603, 14602, 14605, 11789,
            19708, 19706, 19707,
            4860, 4866, 4872, 4878, 4884, 4896, 4890, 4896, 4902,
            4932, 4938, 4944, 4950, 4908, 4914, 4920, 4926, 4956,
            4926, 4968, 4994, 4980, 4986, 4992, 4998,
            18778, 18779, 18780, 18781,
            13450, 13444, 13405, 15502,
            10548, 10549, 10550, 10551, 10555, 10552, 10553, 2412, 2413, 2414,
            20747,
            18365, 18373, 18371, 15246, 12964, 12971, 12978, 14017,
            757, 8851,
            13855, 13848, 13857, 13856, 13854, 13853, 13852, 13851, 13850, 5509, 13653, 14021, 14020, 19111, 14019, 14022,
            19785, 19786, 18782, 18351, 18349, 18353, 18357, 18355, 18359, 18335
    };
    public static MemCache spriteCache = new MemCache(100);
    public static MemCache modelCache = new MemCache(50);
    public static ItemDef[] cache;
    public static int cacheIndex;
    public static Stream stream;
    public static int[] streamIndices;
    public static int totalItems;
    private static int[] prices;
    private static List<Integer> untradeableItems = new ArrayList<Integer>();
    public byte femaleYOffset;
    public int value;
    public int[] editedModelColor;
    public int id;
    public int[] newModelColor;
    public boolean membersObject;
    public boolean animateInventory;
    public int femaleEquip3;
    public int certTemplateID;
    public int femaleEquip2;
    public int maleEquip1;
    public int maleDialogueModel;
    public int sizeX;
    public String groundActions[];
    public int modelOffset1;
    public String name;
    public int femaleDialogueModel;
    public int modelID;
    public int maleDialogue;
    public boolean stackable;
    public String description;
    public int certID;
    public int modelZoom;
    public int lightness;
    public int maleEquip3;
    public int maleEquip2;
    public String actions[];
    public int rotationY;
    public int sizeZ;
    public int sizeY;
    public int[] stackIDs;
    public int modelOffsetY;
    public int shadow;
    public int femaleDialogue;
    public int rotationX;
    public int femaleEquip1;
    public int[] stackAmounts;
    public int team;
    public int modelOffsetX;
    public byte maleYOffset;
    public byte maleXOffset;
    public int lendID;
    public int lentItemID;
    public boolean untradeable;
    public int[] originalModelColor;
    public ItemDef() {
        id = -1;
    }

    public static void nullLoader() {
        modelCache = null;
        spriteCache = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public static void unpackConfig(CacheArchive streamLoader) {
        /*
         * stream = new Stream(FileOperations.ReadFile("./Cache/obj.dat"));
         * Stream stream = new
         * Stream(FileOperations.ReadFile("./Cache/obj.idx"));
         */
        stream = new Stream(streamLoader.getDataForName("obj.dat"));
        Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
        totalItems = stream.readUnsignedWord() + 3000;
        streamIndices = new int[totalItems];
        int i = 2;
        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += stream.getUnsignedShort();
        }
        cache = new ItemDef[10];
        for (int k = 0; k < 10; k++) {
            cache[k] = new ItemDef();
        }
        setSettings();
    }

    public static ItemDef copyRotations(ItemDef itemDef, int id) {
        ItemDef itemDef2 = ItemDef.forID(id);
        itemDef.modelOffsetY = itemDef2.modelOffsetY;
        itemDef.modelOffsetX = itemDef2.modelOffsetX;
        itemDef.modelOffsetY = itemDef2.modelOffsetY;
        itemDef.modelOffset1 = itemDef2.modelOffset1;
        itemDef.modelZoom = itemDef2.modelZoom;
        itemDef.rotationX = itemDef2.rotationX;
        itemDef.rotationY = itemDef2.rotationY;
        return itemDef;
    }

    public static String ucFirst(String str) {
        str = str.toLowerCase().replaceAll("_", " ");
        if (str.length() > 1) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str.toUpperCase();
        }
        return str;
    }

    public static void printDef(int id) {
        ItemDef toPrint = forID(id);
        if(toPrint== null) {
            System.out.println("ItemDef is null");
        } else {
            System.out.println("MaleEquip1: " + toPrint.maleEquip1);
            System.out.println("MaleEquip2: " + toPrint.maleEquip2);
            System.out.println("MaleEquip3: " + toPrint.maleEquip3);
            System.out.println("femaleEquip1: " + toPrint.femaleEquip1);
            System.out.println("MaleEquip2: " + toPrint.femaleEquip2);
            System.out.println("MaleEquip3: " + toPrint.femaleEquip3);
            System.out.println("ModelId: " + toPrint.modelID);

        }
    }

    public static ItemDef forID(int i) {
        for (int j = 0; j < 10; j++) {
            if (cache[j].id == i) {
                return cache[j];
            }
        }
        cacheIndex = (cacheIndex + 1) % 10;
        ItemDef itemDef = cache[cacheIndex];
        if (i < 0 || i >= streamIndices.length) {
            itemDef.id = 1;
            itemDef.setDefaults();
            return itemDef;
        }
        stream.currentOffset = streamIndices[i];
        itemDef.id = i;
        itemDef.setDefaults();
        itemDef.readValues(stream);
        if (itemDef.certTemplateID != -1) {
            itemDef.toNote();
        }
        if (itemDef.lentItemID != -1) {
            itemDef.toLend();
        }
        if (itemDef.id == i && itemDef.editedModelColor == null) {
            itemDef.editedModelColor = new int[1];
            itemDef.newModelColor = new int[1];
            itemDef.editedModelColor[0] = 0;
            itemDef.newModelColor[0] = 1;
        }
        if (untradeableItems.contains(itemDef.id)) {
            itemDef.untradeable = true;
        }
        itemDef.value = prices[itemDef.id];
        int custom_start = 18888;
        //System.out.println("Custom items: "+CustomItems.values().length);
        for (CustomItems custom : CustomItems.values()) {
            if (i == custom_start + custom.ordinal()) {
                itemDef = copyRotations(itemDef, custom.getCopy());
                itemDef.name = ucFirst(custom.name());
                if (custom.isCopyDef()) {
                    ItemDef def2 = ItemDef.forID(custom.getCopy());
                    itemDef.modelID = def2.modelID;
                    itemDef.maleEquip1 = def2.maleEquip1;
                    itemDef.femaleEquip1 = def2.femaleEquip1;
                    itemDef.editedModelColor = custom.editedModelColor;
                    itemDef.newModelColor = custom.originalModelColor;
                } else {
                    itemDef.modelID = custom.getInventory();
                    itemDef.maleEquip1 = custom.getMale();
                    itemDef.femaleEquip1 = custom.getFemale();
                }
                itemDef.actions = new String[5];
                itemDef.actions[1] = custom.isWeapon() ? "Wield" : "Wear";
            }
        }
        ItemDefCade.doCustom(itemDef);
        ItemDefCustom1.doCustom(itemDef);
        ItemDefCustom2.doCustom(itemDef);
        ItemDefCustom3.doCustom(itemDef);
        return itemDef;
    }

    public static void setSettings() {
        try {
            prices = new int[22694];
            int index = 0;
            for (String line : Files.readAllLines(Paths.get(signlink.findcachedir() + "data/data.txt"), Charset.defaultCharset())) {
                prices[index] = Integer.parseInt(line);
                index++;
            }
            for (int i : UNTRADEABLE_ITEMS) {
                untradeableItems.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyTexturing(Model model, int id) {
        switch (id) {
            case 15257://devote coin
                model.setTexture(119, new int[] {550});
                //model.printModelColors();
                break;
            case 1139://ref kit
                model.setTexture(73, new int[] {4526, 4516, 4530, 4511});
                break;

        ///Booster Packs / Somewhat like elixirs / Boxes
            case 15228:
            case 15226:
            case 15224:
            case 15222:
            case 15220:
            case 15218:
                model.setTexture(62, new int[] {5542});
                break;
            case 15420://elixir box
                model.setTexture(58, new int[] {1712, 1718, 1707, 1702});
                model.setTexture(97, new int[] {20917, 20912, 20909, 20908, 20905});
                break;
            case 3954://box
                model.setTexture(119, new int[] {22431, 56255, 800, 799, 12223, 807});
                break;
            case 15501://box
                model.setTexture(114, new int[] {2998, 3007, 64404, 64427});
                break;
            case 6833://box
                model.setTexture(87, new int[] {22410});
                model.setTexture(62, new int[] {2999});
                break;
            case 3849://bond casket
                model.setTexture(128, new int[] {34507, 34371, 33099, 32956});
                break;
            case 18768://box
                model.setTexture(73);
                // model.printModelColors();
                break;
            case 10993://scroll box op
                model.setTexture(128, new int[] {5910, 5662, 5786, 5392});
                break;
            case 7959://box of boxes
                model.setTexture(101);
                break;

         /// Misc
            case 1949://skilling tools
            case 1523:
            case 1585:
                model.setTexture(128);
                break;
            case 18898://ring
                model.setTexture(126, new int[] {3008, 5056});
                model.setTexture(62, new int[] {43045, 43053});
                break;
            case 9104://ring
                model.setTexture(119, new int[] {6573});
                break;
            case 4202://ring
                model.setTexture(73, new int[] {6829});
                break;
            case 20054://ring
                model.setTexture(103);
                break;
            case 691://Battlepass
                model.setTexture(62, new int[] {10351});
                break;
            case 693://Battlepass GIM
                model.setTexture(58, new int[] {10351});
                break;
            case 7053://mining item
                model.setTexture(128, new int[] {8111, 8107});
                break;
            case 16022://dragon pet
                model.setTexture(100, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;
            case 16020://dragon pet
                model.setTexture(101, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;
            case 16012://upgrade token sack
                model.setTexture(86, new int[] {6430, 6798, 7223, 7467});
                break;
            case 20075://partyhat
                model.setTexture(127);
                break;
            case 4119://partyhat
                model.setTexture(59);
                break;
            case 592://perk points
            case 13147:
                model.setTexture(127);
                break;
            case 19873://Mage armor
            case 19875:
            case 19877:
                model.setTexture(114, new int[] {38746, 38614});
                break;
            case 19776://Matches mage armor
            case 19778://Staff and offhand
                model.setTexture(114, new int[] {33727});
                break;
            case 19784://Placeholder mage wep
                model.setTexture(105, new int[] {39972});
                break;
            case 19786://Placeholder mage wep
                model.setTexture(105, new int[] {60});
                break;
            case 19788://Big ass swords
            case 19790://Change tex color, mad ugly
                model.setTexture(57, new int[] {1983, 63374});
                break;
            case 19671://Shield
                model.setTexture(83, new int[] {5025, 8127, 7103});
                break;
            case 19899://Igris
            case 19897:
            case 19901:
                model.setTexture(83, new int[] {63395});
                break;
            case 19921://Igris
            case 19923:
                model.setTexture(83, new int[] {527, 676, 557});
                break;
            case 15214://Looting bag
                model.setTexture(77, new int[] {6430, 6798});
                break;
            case 3666://Occult Staff
                model.setTexture(99, new int[] {40});
                break;
            case 14998://Magic watering can
                model.setTexture(54, new int[] {43220});
                break;
            case 17552://bonecrusher (e)
                model.setTexture(77, new int[] {5395, 5404, 5411, 5400});
                break;
            case 15065://Fused slayer helmet
            	model.setTexture(73, new int[] {2710, 8414, 8404, 8394, 8384, 8344, 8374, 8364, 7300, 36977, 89, 108});
                break;
            case 6499://donation gem
                model.setTexture(92);
                break;
            case 11613://diablo shield
                model.setTexture(92, new int[] {1818});
                break;
            case 2399://starter key
                model.setTexture(80);
                break;

        /// Purple Knights Armor
            case 19808:
            case 19810:
                model.setTexture(117, new int[] {9151});
                break;
            case 19881:
            case 19879:
            case 19883:
                model.setTexture(117, new int[] {50628, 50765});
                break;
            case 19915:
            case 19913:
                model.setTexture(117, new int[] {48583, 48852, 48715});
                break;

            ///hollow set
            case 18003:
                model.setTexture(67, new int[] {63155, 21344});
                break;
            case 18009:
                model.setTexture(67, new int[] {63155});
                break;
            case 18005:
                model.setTexture(67, new int[] {63155});
                break;
            case 18011:
                model.setTexture(67, new int[] {63155});
                break;
            case 18007:
                model.setTexture(67, new int[] {35985, 35987, 8489, 63155});
                break;
            case 19576:
                model.setTexture(67, new int[] {37245, 32216, 31996, 39422, 30169, 31977, 29886, 31997});
                model.setTexture(62, new int[] {7588, 7577, 7579, 7578, 7571, 7572, 7574, 7580, 7589, 7573, 7575});
                break;
            case 11623:
                model.setTexture(62, new int[] {20});
                break;

                /// bonds
            case 15356:
            case 3851:
                model.setTexture(0, 85);
                break;

            case 15355:
                model.setTexture(0, 87);
                break;
            case 3852:
                model.setTexture(1, 87);
                break;


            case 15359:
            case 3853:
                model.setTexture(0, 90);
                break;

            case 15358:
                model.setTexture(0, 88);
                break;
            case 3854:
                model.setTexture(1, 88);
                break;

            case 15360:
            case 3855:
                model.setTexture(0, 86);
                break;

            case 15361:
                model.setTexture(1, 83);
                break;

            case 15362:
                model.setTexture(1, 84);
                break;

            case 15363:
                model.setTexture(1, 89);
                break;
            case 3850:
                model.setTexture(0, 89);
                break;
        }
    }

    public static Sprite getSprite(int i, int j, int k, int zoom) {
        if (k == 0 && zoom != -1) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDef itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }

            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = itemDef.getItemModelFinalised(1);
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lendID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.modelZoom;
        if (zoom != -1 && zoom != 0) {
            k3 = (itemDef.modelZoom * 100) / zoom;
        }
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);

        if (MELEE_GEAR_HIGHLIGHT.contains(itemDef.id)) {
            sprite2.outline(0xff0000);
        } else if (MAGIC_GEAR_HIGHLIGHT.contains(itemDef.id)) {
            sprite2.outline(0x0000ff);
        } else if (RANGED_GEAR_HIGHLIGHT.contains(itemDef.id)) {
            sprite2.outline(0x00ff00);
        } else {
            sprite2.outline(1);
        }

        if (k > 0) {
            sprite2.outline(16777215);
        }
        if (k == 0) {
            sprite2.shadow(3153952);
        }
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lendID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        sprite2.maxWidth = itemDef.stackable ? 33 : 32;
        sprite2.maxHeight = j;
        return sprite2;
    }

    public static Sprite getSprite(int i, int j, int k) {
        if (k == 0) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDef itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }
            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = itemDef.getItemModelFinalised(1);
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lentItemID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.modelZoom;
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
        if (MELEE_GEAR_HIGHLIGHT.contains(itemDef.id)) {
            sprite2.outline(0xff0000);
        } else if (MAGIC_GEAR_HIGHLIGHT.contains(itemDef.id)) {
            sprite2.outline(0x0000ff);
        } else if (RANGED_GEAR_HIGHLIGHT.contains(itemDef.id)) {
            sprite2.outline(0x00ff00);
        } else {
            sprite2.outline(1);
        }
        if (k > 0) {
            sprite2.outline(16777215);
        }
        if (k == 0) {
            sprite2.shadow(3153952);
        }
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lentItemID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0 && !itemDef.animateInventory) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        if (itemDef.stackable) {
            sprite2.maxWidth = 33;
        } else {
            sprite2.maxWidth = 32;
        }
        sprite2.maxHeight = j;
        return sprite2;
    }

    public boolean dialogueModelFetched(int j) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (j == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(k)) {
            flag = false;
        }
        if (l != -1 && !Model.modelIsFetched(l)) {
            flag = false;
        }
        return flag;
    }

    public Model getDialogueModel(int gender) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (gender == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return null;
        }
        Model model = Model.fetchModel(k);
        if (l != -1) {
            Model model_1 = Model.fetchModel(l);
            Model models[] = {model, model_1};
            model = new Model(2, models);
        }
        if (editedModelColor != null) {
            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
                model.recolour(editedModelColor[i1], newModelColor[i1]);
            }
        }
        applyTexturing(model, id);
        return model;
    }

    public boolean equipModelFetched(int gender) {
        int fistModel = maleEquip1;
        int secondModel = maleEquip2;
        int thirdModel = maleEquip3;
        if (gender == 1) {
            fistModel = femaleEquip1;
            secondModel = femaleEquip2;
            thirdModel = femaleEquip3;
        }
        if (fistModel == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(fistModel)) {
            flag = false;
        }
        if (secondModel != -1 && !Model.modelIsFetched(secondModel)) {
            flag = false;
        }
        if (thirdModel != -1 && !Model.modelIsFetched(thirdModel)) {
            flag = false;
        }
        return flag;
    }

    public Model getEquipModel(int gender) {
        int j = maleEquip1;
        int k = maleEquip2;
        int l = maleEquip3;
        if (gender == 1) {
            j = femaleEquip1;
            k = femaleEquip2;
            l = femaleEquip3;
        }
        if (j == -1) {
            return null;
        }
        Model model = Model.fetchModel(j);
        if (k != -1) {
            if (l != -1) {
                Model model_1 = Model.fetchModel(k);
                Model model_3 = Model.fetchModel(l);
                Model model_1s[] = {model, model_1, model_3};
                model = new Model(3, model_1s);
            } else {
                Model model_2 = Model.fetchModel(k);
                Model models[] = {model, model_2};
                model = new Model(2, models);
            }
        }
        //if (j == 62367)
        //model.translate(68, 7, -8);
        if (gender == 0 && maleYOffset != 0) {
            model.translate(0, maleYOffset, 0);
        } else if (gender == 1 && femaleYOffset != 0) {
            model.translate(0, femaleYOffset, 0);
        }
        if (editedModelColor != null) {
            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
                model.recolour(editedModelColor[i1], newModelColor[i1]);
            }
        }
        applyTexturing(model, id);
        return model;
    }

    public void setDefaults() {
        untradeable = false;
        modelID = 0;
        name = null;
        description = null;
        editedModelColor = null;
        newModelColor = null;
        modelZoom = 2000;
        rotationY = 0;
        rotationX = 0;
        modelOffsetX = 0;
        modelOffset1 = 0;
        modelOffsetY = 0;
        stackable = false;
        value = 0;
        membersObject = false;
        groundActions = null;
        actions = null;
        maleEquip1 = -1;
        maleEquip2 = -1;
        maleYOffset = 0;
        maleXOffset = 0;
        femaleEquip1 = -1;
        femaleEquip2 = -1;
        femaleYOffset = 0;
        maleEquip3 = -1;
        femaleEquip3 = -1;
        maleDialogue = -1;
        maleDialogueModel = -1;
        femaleDialogue = -1;
        femaleDialogueModel = -1;
        stackIDs = null;
        stackAmounts = null;
        certID = -1;
        certTemplateID = -1;
        sizeX = 128;
        sizeY = 128;
        sizeZ = 128;
        shadow = 0;
        lightness = 0;
        team = 0;
        lendID = -1;
        lentItemID = -1;
    }

    private void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                modelID = stream.readUnsignedWord();
            } else if (i == 2) {
                name = stream.readString();
            } else if (i == 3) {
                description = stream.readString();
            } else if (i == 4) {
                modelZoom = stream.readUnsignedWord();
            } else if (i == 5) {
                rotationY = stream.readUnsignedWord();
            } else if (i == 6) {
                rotationX = stream.readUnsignedWord();
            } else if (i == 7) {
                modelOffset1 = stream.readUnsignedWord();
                if (modelOffset1 > 32767) {
                    modelOffset1 -= 0x10000;
                }
            } else if (i == 8) {
                modelOffsetY = stream.readUnsignedWord();
                if (modelOffsetY > 32767) {
                    modelOffsetY -= 0x10000;
                }
            } else if (i == 10) {
                stream.readUnsignedWord();
            } else if (i == 11) {
                stackable = true;
            } else if (i == 12) {
                value = stream.readUnsignedWord();
            } else if (i == 16) {
                membersObject = true;
            } else if (i == 23) {
                maleEquip1 = stream.readUnsignedWord();
                maleYOffset = stream.readSignedByte();
            } else if (i == 24) {
                maleEquip2 = stream.readUnsignedWord();
            } else if (i == 25) {
                femaleEquip1 = stream.readUnsignedWord();
                femaleYOffset = stream.readSignedByte();
            } else if (i == 26) {
                femaleEquip2 = stream.readUnsignedWord();
            } else if (i >= 30 && i < 35) {
                if (groundActions == null) {
                    groundActions = new String[5];
                }
                groundActions[i - 30] = stream.readString();
                if (groundActions[i - 30].equalsIgnoreCase("hidden")) {
                    groundActions[i - 30] = null;
                }
            } else if (i >= 35 && i < 40) {
                if (actions == null) {
                    actions = new String[5];
                }
                actions[i - 35] = stream.readString();
                if (actions[i - 35].equalsIgnoreCase("null")) {
                    actions[i - 35] = null;
                }
            } else if (i == 40) {
                int j = stream.readUnsignedByte();
                editedModelColor = new int[j];
                newModelColor = new int[j];
                for (int k = 0; k < j; k++) {
                    editedModelColor[k] = stream.readUnsignedWord();
                    newModelColor[k] = stream.readUnsignedWord();
                }
            } else if (i == 78) {
                maleEquip3 = stream.readUnsignedWord();
            } else if (i == 79) {
                femaleEquip3 = stream.readUnsignedWord();
            } else if (i == 90) {
                maleDialogue = stream.readUnsignedWord();
            } else if (i == 91) {
                femaleDialogue = stream.readUnsignedWord();
            } else if (i == 92) {
                maleDialogueModel = stream.readUnsignedWord();
            } else if (i == 93) {
                femaleDialogueModel = stream.readUnsignedWord();
            } else if (i == 95) {
                modelOffsetX = stream.readUnsignedWord();
            } else if (i == 97) {
                certID = stream.readUnsignedWord();
            } else if (i == 98) {
                certTemplateID = stream.readUnsignedWord();
            } else if (i >= 100 && i < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[i - 100] = stream.readUnsignedWord();
                stackAmounts[i - 100] = stream.readUnsignedWord();
            } else if (i == 110) {
                sizeX = stream.readUnsignedWord();
            } else if (i == 111) {
                sizeY = stream.readUnsignedWord();
            } else if (i == 112) {
                sizeZ = stream.readUnsignedWord();
            } else if (i == 113) {
                shadow = stream.readSignedByte();
            } else if (i == 114) {
                lightness = stream.readSignedByte() * 5;
            } else if (i == 115) {
                team = stream.readUnsignedByte();
            } else if (i == 116) {
                lendID = stream.readUnsignedWord();
            } else if (i == 117) {
                lentItemID = stream.readUnsignedWord();
            }
        } while (true);
    }

    public void toNote() {
        ItemDef itemDef = forID(certTemplateID);
        modelID = itemDef.modelID;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        rotationX = itemDef.rotationX;
        modelOffsetX = itemDef.modelOffsetX;
        modelOffset1 = itemDef.modelOffset1;
        modelOffsetY = itemDef.modelOffsetY;
        editedModelColor = itemDef.editedModelColor;
        newModelColor = itemDef.newModelColor;
        ItemDef itemDef_1 = forID(certID);
        name = itemDef_1.name;
        membersObject = itemDef_1.membersObject;
        value = itemDef_1.value;
        String s = "a";
        char c = itemDef_1.name.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
        stackable = true;
    }

    private void toLend() {
        ItemDef itemDef = forID(lentItemID);
        actions = new String[5];
        modelID = itemDef.modelID;
        modelOffset1 = itemDef.modelOffset1;
        rotationX = itemDef.rotationX;
        modelOffsetY = itemDef.modelOffsetY;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        modelOffsetX = itemDef.modelOffsetX;
        value = 0;
        ItemDef itemDef_1 = forID(lendID);
        maleDialogueModel = itemDef_1.maleDialogueModel;
        editedModelColor = itemDef_1.editedModelColor;
        maleEquip3 = itemDef_1.maleEquip3;
        maleEquip2 = itemDef_1.maleEquip2;
        femaleDialogueModel = itemDef_1.femaleDialogueModel;
        maleDialogue = itemDef_1.maleDialogue;
        groundActions = itemDef_1.groundActions;
        maleEquip1 = itemDef_1.maleEquip1;
        name = itemDef_1.name;
        femaleEquip1 = itemDef_1.femaleEquip1;
        membersObject = itemDef_1.membersObject;
        femaleDialogue = itemDef_1.femaleDialogue;
        femaleEquip2 = itemDef_1.femaleEquip2;
        femaleEquip3 = itemDef_1.femaleEquip3;
        newModelColor = itemDef_1.newModelColor;
        team = itemDef_1.team;
        if (itemDef_1.actions != null) {
            for (int i_33_ = 0; i_33_ < 4; i_33_++) {
                actions[i_33_] = itemDef_1.actions[i_33_];
            }
        }
        actions[4] = "Discard";
    }

    public Model getItemModelFinalised(int amount) {
        if (stackIDs != null && amount > 1) {
            int stackId = -1;
            for (int k = 0; k < 10; k++) {
                if (amount >= stackAmounts[k] && stackAmounts[k] != 0) {
                    stackId = stackIDs[k];
                }
            }
            if (stackId != -1) {
                return forID(stackId).getItemModelFinalised(1);
            }
        }

        Model model = (Model) modelCache.get(id);
        if (model != null && id != 3930) {
            return model;
        }
        model = Model.fetchModel(modelID);
        if(id == 3930) {
            model = myPlayer.getRotatedModel();

            if (Client.instance.aBoolean1031) {
                Model characterDisplay = myPlayer.getRotatedModel();
                for (int l2 = 0; l2 < 5; l2++) {
                    if (Client.instance.myAppearanceColors[l2] != 0) {
                        characterDisplay.recolour(anIntArrayArray1003[l2][0],
                                anIntArrayArray1003[l2][Client.instance.myAppearanceColors[l2]]);
                        if (l2 == 1) {
                            characterDisplay.recolour(anIntArray1204[0], anIntArray1204[Client.instance.myAppearanceColors[l2]]);
                        }
                    }
                }
                int staticFrame = myPlayer.standAnim;
                if(characterDisplay != null) {
                    characterDisplay.createBones();
                    characterDisplay.applyTransform(Animation.anims[staticFrame].frameIDs[0]);
                    return characterDisplay;
                }
            }
        }
        if (model == null) {
            return null;
        }
        if (sizeX != 128 || sizeY != 128 || sizeZ != 128) {
            model.scaleT(sizeX, sizeZ, sizeY);
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        applyTexturing(model, id);
        model.light(64 + shadow, 768 + lightness, -50, -10, -50, true);
        model.rendersWithinOneTile = true;
        modelCache.put(model, id);
        return model;
    }

    public Model getItemModel(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++) {
                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
                    j = stackIDs[k];
                }
            }
            if (j != -1) {
                return forID(j).getItemModel(1);
            }
        }
        Model model = Model.fetchModel(modelID);
        if (model == null) {
            return null;
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        applyTexturing(model, id);
        return model;
    }
    public enum CustomItems {
        //PINK_DILDO(18351, 20, 20, 20, true), // 18983
        ;

        private int copy;
        private int inventory;
        private int female;
        private int male;
        private boolean weapon;
        private int[] editedModelColor;
        private int[] originalModelColor;
        private boolean copyDef;

        CustomItems(int copy, int model, boolean weapon) {
            this.setCopy(copy);
            this.setInventory(model);
            this.setFemale(model);
            this.setMale(model);
            this.setWeapon(weapon);
        }

        CustomItems(int copy, int inventory, int wield, boolean weapon) {
            this.setCopy(copy);
            this.setInventory(inventory);
            this.setFemale(wield);
            this.setMale(wield);
            this.setWeapon(weapon);
        }

        CustomItems(int copy, int inventory, int female, int male, boolean weapon) {
            this.setCopy(copy);
            this.setInventory(inventory);
            this.setFemale(female);
            this.setMale(male);
            this.setWeapon(weapon);
        }

        CustomItems(int copy, int[] editedModelColor, int[] originalModelColor) {
            setCopyDef(true);
            this.setCopy(copy);
            this.editedModelColor = editedModelColor;
            this.originalModelColor = originalModelColor;
        }

        public int getCopy() {
            return copy;
        }

        public void setCopy(int copy) {
            this.copy = copy;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public int getFemale() {
            return female;
        }

        public void setFemale(int female) {
            this.female = female;
        }

        public int getMale() {
            return male;
        }

        public void setMale(int male) {
            this.male = male;
        }

        public boolean isWeapon() {
            return weapon;
        }

        public void setWeapon(boolean weapon) {
            this.weapon = weapon;
        }

        public int[] getEditedModelColor() {
            return editedModelColor;
        }

        public void setEditedModelColor(int[] editedModelColor) {
            this.editedModelColor = editedModelColor;
        }

        public int[] getOriginalModelColor() {
            return originalModelColor;
        }

        public void setOriginalModelColor(int[] originalModelColor) {
            this.originalModelColor = originalModelColor;
        }

        public boolean isCopyDef() {
            return copyDef;
        }

        public void setCopyDef(boolean copyDef) {
            this.copyDef = copyDef;
        }
    }
}
