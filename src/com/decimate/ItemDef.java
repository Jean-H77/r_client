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
            case 20100:
            case 20101:
            case 20106:
            case 20109:
            case 20107:
            case 20104:
            case 20108:
                model.setTexture(121, new int[] {97, 96});
                break;
            case 592:
            case 13147:
                model.setTexture(127);
                break;
            case 1949:
                model.setTexture(128);
                break;
            case 1523:
                model.setTexture(128);
                break;
            case 8000:
            case 20113:
            case 20110:
            case 20115:
            case 20114:
            case 20112:
            case 20111:
                model.setTexture(122, new int[] {97, 96});
                break;
            case 8008:
            case 8009:
            case 8010:
            case 8011:
            case 8012:
            case 8013:
            case 8014:
                model.setTexture(124, new int[] {97, 96});
                break;
            case 8007:
            case 8006:
            case 8005:
            case 8004:
            case 8003:
            case 8002:
            case 8001:
                model.setTexture(123, new int[] {97, 96});
                break;

            case 8015:
            case 8016:
            case 8017:
            case 8018:
            case 8019:
            case 8020:
                model.setTexture(125, new int[] {97,96});
                break;

            case 8021:
            case 8022:
            case 8023:
            case 8024:
            case 8025:
            case 8026:
                model.setTexture(126, new int[] {97, 96});
                break;


            case 18898:
                model.setTexture(126, new int[] {3008, 5056});
                model.setTexture(62, new int[] {43045, 43053});
                break;


            case 9104:
                model.setTexture(119, new int[] {6573});
                break;
            case 7927:
                model.setTexture(60, new int[] {6862, 6854});
                model.setTexture(60, new int[] {939, 935});
                model.setTexture(59, new int[] {43992, 43988, 43856});
                break;

            case 4202:
                model.setTexture(73, new int[] {6829});
                break;

            case 20054:
                model.setTexture(103);
                break;

            case 8038:
                model.setTexture(116);
                break;

            case 3062:
                model.setTexture(52, new int[] {57});
                model.setTexture(62, new int[] {28, 37});
                break;

            case 10993:
                model.setTexture(128, new int[] {5910, 5662, 5786, 5392});
                break;

            case 7959:
                model.setTexture(119);
                break;

            case 6183:
                model.setTexture(88, new int[] {60325});
                break;

            case 15372:
                model.setTexture(103, new int[] {22410});
                break;


            case 1139:
                model.setTexture(73, new int[] {4526, 4516, 4530, 4511});
                break;

            case 693:
                model.setTexture(58, new int[] {10351});
                break;

            case 691:
                model.setTexture(62, new int[] {10351});
                break;

            case 18768:
                model.setTexture(73);
               // model.printModelColors();
                break;

            case 15258:
                model.setTexture(57, new int[] {6});
                break;

            case 15257:
                model.setTexture(119, new int[] {550});
                break;

            case 4119:
                model.setTexture(59);
                break;


                case 15541:
                case 15540:
                case 15542:
                case 15543:
                model.setTexture(111, new int[] {570});
                break;

                case 15539:
                case 15538:
                model.setTexture(111);
                break;

            case 3849:
                model.setTexture(128, new int[] {34507, 34371, 33099, 32956});
                break;

            case 10350:
                model.setTexture(58, new int[] {33030});
                break;

            case 10346:
                model.setTexture(58, new int[] {33030, 74, 57});
                break;

            case 10348:
                model.setTexture(58, new int[] {33030, 57, 10262, 74});
                break;

            case 10352:
                model.setTexture(58, new int[] {43150, 43146, 21766, 43270, 3230, 3226, 26, 37, 18, 20, 28});
                break;

            case 1585:
                model.setTexture(128);
                break;

            case 7053:
                model.setTexture(128, new int[] {8111, 8107});
                break;

            case 15501:
                model.setTexture(114, new int[] {2998, 3007, 64404, 64427});
                break;

            case 3954:
                model.setTexture(119, new int[] {22431, 56255, 800, 799, 12223, 807});
                break;

            case 16022:
                model.setTexture(100, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;

            case 19526:
                model.setTexture(53, new int[] {32161, 32173, 32326, 32703, 32694});
                break;

                case 16020:
                model.setTexture(101, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;

            case 3947:
                model.setTexture(54, new int[] {3502, 3490, 3488, 3483, 3492, 3479, 3485, 3489, 3511, 3495, 3472, 3482, 3498, 3493, 3512, 3508, 3504, 3476, 3477, 3501, 3513, 3505, 3506, 3499, 3509, 4547, 3760, 3748, 3753, 3757, 3736, 3754, 3747, 3768, 3730, 3739, 3730, 3739, 3755, 3742, 3740, 3752, 3751, 3765, 3762, 3745, 3775, 3766, 3763, 3750, 3759, 3764, 3767, 33313, 33306, 33312, 33318, 33308, 33301, 33305, 333295, 33302, 33323, 33316});
                break;

            case 6833:
                model.setTexture(87, new int[] {22410});
                model.setTexture(62, new int[] {2999});
                break;

            case 16012:
                model.setTexture(86, new int[] {6430, 6798, 7223, 7467});
                break;

            case 20075:
                model.setTexture(127);
                break;

            case 15924:
                model.setTexture(105, new int[] {7731, 7757, 7500, 7712, 4110, 7275, 7694, 7718, 7753, 7726, 7713, 7721, 7684, 7716, 7680, 7743, 7711, 7706, 7728, 7733, 7738, 7698, 7693});
                break;

                case 16023:
                    model.setTexture(105, new int[] {7731, 7757, 7500, 7712, 4110, 7275, 7694, 7718, 7753, 7726, 7713, 7721, 7684, 7716, 7680, 7743, 7711, 7706, 7728, 7733, 7738, 7698, 7693, 33093, 33117, 7703, 7710, 7717, 105});
                    break;

                    case 15935:
                        model.setTexture(105, new int[] {7731, 7757, 7500, 7712, 4110, 7275, 7694, 7718, 7753, 7726, 7713, 7721, 7684, 7716, 7680, 7743, 7711, 7706, 7728, 7733, 7738, 7698, 7693, 33093, 33117, 7703, 7710, 7717, 105, 7700, 7724, 7715, 7735, 7714, 7740, 7719});
                        break;
            case 15818:
                model.setTexture(105, new int[] {6694, 6704, 6729, 6719, 5403, 5398, 5388, 5393, 5380, 5385, 5424, 5408, 5378, 5376, 5383, 33117, 33129, 33107, 33119, 5411, 5406, 5429, 5413, 5414, 5396, 5401, 5419});
                break;
            case 15946:
            case 16272:
                model.setTexture(105);
                break;

            case 16184:
            case 16045:
                model.setTexture(104);
                break;

            case 6199:
                model.setTexture(54, new int[] {5056});
                model.setTexture(76, new int[] {2999});
                break;
            case 6200:
                model.setTexture(79, new int[] {22410});
                break;
            case 15420:
                model.setTexture(58, new int[] {1712, 1718, 1707, 1702});
                model.setTexture(97, new int[] {20917, 20912, 20909, 20908, 20905});
                break;
            case 19600:
                model.setTexture(83, new int[] {7388, 7387, 10312, 10313});
                break;
            case 19602:
                model.setTexture(83, new int[] {120, 105, 119, 97, 94, 92, 85, 81, 80, 6570, 77, 73, 71, 64, 13, 59, 18});//120
                break;
            case 19590:
                model.setTexture(83, new int[] {10046, 8127});
                break;
            case 19592:
                model.setTexture(83, new int[] {10825, 9151, 8886});
                break;
            case 19594:
                model.setTexture(83, new int[] {10046, 9151});
                break;
            case 19598:
                model.setTexture(83, new int[] {10046});
                break;
            case 19596:
                model.setTexture(83, new int[] {9151});
                break;
                case 6201:
                model.setTexture(99, new int[] {22410});
                model.setTexture(76, new int[] {2999});
                //model.printModelColors();
                break;
            case 19776:
            case 19778:
                model.setTexture(114, new int[] {33727});
                break;
            case 19899:
            case 19897:
            case 19901:
                model.setTexture(57, new int[] {63395});
                break;
            case 19921:
            case 19923:
                model.setTexture(57, new int[] {527, 676, 557});
                break;
            case 19873:
            case 19875:
            case 19877:
                model.setTexture(114, new int[] {38746, 38614});
                break;
            case 19780:
                model.setTexture(115, new int[] {48446});
                break;
            case 19784:
                model.setTexture(105, new int[] {39972});
                break;
            case 19786:
                model.setTexture(105, new int[] {60});
                break;
            case 19788:
            case 19790:
                model.setTexture(57, new int[] {1983, 63374});
                break;
            case 19796:
            case 19798:
                model.setTexture(84, new int[] {932});
                break;
            case 19889:
            case 19895:
            case 19885:
            case 19891:
            case 19893:
                model.setTexture(84, new int[] {10035});
                break;






            case 19808:
            case 19810:
                model.setTexture(117, new int[] {9151});
                break;
            case 19881:
            case 19879:
            case 19883:
                model.setTexture(117, new int[] {50628, 50765});
                //model.printModelColors();
                break;
            case 19915:
            case 19913:
                model.setTexture(117, new int[] {48583, 48852, 48715});
                //model.printModelColors();
                break;

            case 19651:
            case 19657:
            case 19659:
            case 19655:
            case 19653:
                model.setTexture(103, new int[] {33570, 33584, 33555});
                break;
            case 19640:
                model.setTexture(101, new int[] {5});
                break;
            case 19642:
                model.setTexture(101, new int[] {6});
                break;
            case 19528:
            case 19530:
            case 19532:
            case 19534:
            case 19536:
            case 19538:
            case 19540:
            case 19542:
            case 19544:
            case 19546:
                model.setTexture(62, new int[] {18820});
                //model.printModelColors();
                break;
            case 6203:
                model.setTexture(93, new int[] {22410});
                model.setTexture(62, new int[] {2999});
                //model.printModelColors();
                break;
            case 6204:
                model.setTexture(75, new int[] {22410});
                model.setTexture(62, new int[] {2999});
                //model.printModelColors();
                break;
            case 6205:
                model.setTexture(67, new int[] {22410, 926});
                model.setTexture(62, new int[] {2999});
                break;
                case 15050:
                 model.setTexture(54, new int[] {39852, 39871});
                //model.printModelColors();
                break;
            case 8:
                model.setTexture(54, new int[] {127, 64040, 64026, 64046});
                break;
            case 3948:
                model.setTexture(54, new int[] {32323, 32174, 32190, 32310, 32307, 32311, 32312, 32328, 32184, 12866, 10933, 10969, 10945});
                break;
            case 3949:
                model.setTexture(54, new int[] {10675, 10687, 10711, 32328, 32187, 32186, 32184, 1461, 1433, 1431, 1441, 1425, 1420, 1447, 1435, 1422, 1423, 10804, 10816, 1462, 1434, 1432, 1442, 10840, 1426, 1421, 1448, 1436, 30409, 30268, 30267, 30265});
                break;
            case 3951:
                model.setTexture(54, new int[] {35781, 32322, 35514, 9912});
                break;
            case 20142:
                model.setTexture(54, new int[] {16, 8334});
                break;
            case 15001:
                model.setTexture(79, new int[] {13, 11, 19, 46, 22, 8, 6, 9});
                break;
            case 18636:
                //model.setTexture(79);
                model.setTexture(79, new int[] {10302, 10442, 10453, 10308, 10448, 10291, 10297, 10301, 10441, 10452, 10307, 10447, 10290, 10296});
                //model.printModelColors();
                break;
            case 19944:
                model.setTexture(79, new int[] {48407, 48397, 48259, 47365, 48392, 51475, 50308, 50438, 51465, 51480, 51470, 10442, 10302, 10297, 10308, 10448, 10453, 10441, 10301, 10296, 10307, 10452, 10447});
                //model.printModelColors();
                break;
            case 19940:
                //model.setTexture(79);
                model.setTexture(79, new int[] {50438, 43138, 51470, 10448, 51465, 10453, 10442, 10308, 10297, 10302, 50308, 10291, 10285, 51268, 51475, 51480, 47365, 41090, 48397, 10447, 48392, 10452, 10441, 10307, 10296, 10301, 48259, 10290, 10284, 48195, 48402, 48407});
                //model.printModelColors();
                break;
            case 19941:
                //model.setTexture(79);
                model.setTexture(79, new int[] {47365, 48259, 10307, 10447, 10452, 10441, 47365, 48259, 10307, 10447, 10452, 10441, 41090, 50438, 50308, 43138, 10308, 10448, 10453, 10442});
                //model.printModelColors();
                break;
            case 19945:
                //model.setTexture(79);
                model.setTexture(79, new int[] {10296, 10447, 10284, 10441, 48397, 48259, 10452, 47365, 48392, 48402, 48407, 10297, 10448, 10285, 10442, 51470, 50308, 10453, 50438, 51465, 51475, 51480});
                //model.printModelColors();
                break;
            case 18447:
                model.setTexture(99, new int[] {17826, 14768, 17819, 17824, 18846, 35200, 24986, 15773, 18731, 64143, 22817, 17570});
                break;
            case 18453:
                model.setTexture(99, new int[] {14768, 23962, 17570, 17680, 17682, 17685, 18729, 18745});
                break;
            case 18461:
                model.setTexture(99, new int[] {15669});
                model.setTexture(76, new int[] {17600});
                break;
            case 15052:
                model.setTexture(99, new int[] {46514, 8660, 8504});
                break;
            case 3666:
                model.setTexture(99, new int[] {40});
                break;
            case 17550:
                model.setTexture(73);
                break;
            case 19671:
                model.setTexture(83, new int[] {5025, 8127, 7103});
                break;
            case 15214:
                model.setTexture(77, new int[] {6430, 6798});
                break;
                case 15228:
                case 15226:
                case 15224:
                case 15222:
                case 15220:
                case 15218:
                model.setTexture(62, new int[] {5542});
                break;
                case 14998:
                model.setTexture(54, new int[] {43220});
                //model.printModelColors();
                break;

                case 17552:
                model.setTexture(77, new int[] {5395, 5404, 5411, 5400});
                //model.printModelColors();
                break;
                case 15065:
            	model.setTexture(73, new int[] {2710, 8414, 8404, 8394, 8384, 8344, 8374, 8364, 7300, 36977, 89, 108});
                //model.printModelColors();
                break;
            case 19020:
            case 6499:

                model.setTexture(92);
                break;
            case 3956:
                model.setTexture(92, new int[] {659, 655, 663, 667, 674, 1691, 1685, 1679});
                break;
            case 15006:
                model.setTexture(92, new int[] {51995, 51991, 51988, 51984, 52002, 50165, 8741, 8733, 8730});
                break;
            case 3958:
                model.setTexture(92, new int[] {1681, 1677, 1687, 1694, 1691, 1687, 1697, 1420, 1033, 1693, 1672, 1426, 1686, 407, 417, 411});
                break;
            case 3957:
                model.setTexture(92, new int[] {2055, 401, 407, 1679, 1685, 2055, 2053, 2572, 2577, 2591, 3228, 3469, 3083, 3092, 3088, 2053, 3080, 3084, 2584});
                break;
            case 3955:
                model.setTexture(92, new int[] {1685, 1679, 1691, 1671});
                break;
            case 11613:
                model.setTexture(92, new int[] {1818});
                break;
            case 17867:
                model.setTexture(93, new int[] {41});
                break;
            case 20182:
                model.setTexture(93, new int[] {46395, 46369, 46398, 46366, 46367, 46368, 46392, 46388, 48568, 46363, 46375, 46377, 46396, 46399, 46372, 46348, 8005});
                break;
            case 11607:
                model.setTexture(93, new int[] {49422, 49452, 65184, 49470, 49428, 49408, 49443, 65175, 65178, 49423, 49427, 49419, 65182, 49433, 49436, 49439, 49448, 49431, 49420, 49446, 49434, 49430, 49438, 49453, 49449, 49425, 49447, 49458, 49463, 49459});
                break;
            case 11608:
                model.setTexture(93, new int[] {49422, 49452, 65184, 49470, 49428, 49408, 49443, 65175, 65178, 49423, 49427, 49419, 65182, 49433, 49436, 49439, 49448, 49431, 49420, 49446, 49434, 49430, 49438, 49453, 49449, 49425, 49447, 49458, 49463, 49459, 12236, 20287, 33604, 31548, 49178, 49168, 49158, 49506, 49429, 49421, 49435, 49426, 49412, 49417, 49441, 49444, 49418, 49445, 49411, 49442, 49440, 49415, 49416, 49413, 49450, 49437, 49473, 49469, 49467, 49456, 49410, 49451, 49462, 49457, 49432, 49472, 926, 7876, 7088});
                break;
            case 11609:
                model.setTexture(93, new int[] {49422, 49452, 65184, 49470, 49428, 49408, 49443, 65175, 65178, 49423, 49427, 49419, 65182, 49433, 49436, 49439, 49448, 49431, 49420, 49446, 49434, 49430, 49438, 49453, 49449, 49425, 49447, 49458, 49463, 49459, 49424, 49412, 49435, 49417});
                break;
            case 11610:
                model.setTexture(93, new int[] {49422, 49452, 65184, 49470, 49428, 49408, 49443, 65175, 65178, 49423, 49427, 49419, 65182, 49433, 49436, 49439, 49448, 49431, 49420, 49446, 49434, 49430, 49438, 49453, 49449, 49425, 49447, 49458, 49463, 49459});
                break;
            case 11611:
                model.setTexture(93, new int[] {49422, 49452, 65184, 49470, 49428, 49408, 49443, 65175, 65178, 49423, 49427, 49419, 65182, 49433, 49436, 49439, 49448, 49431, 49420, 49446, 49434, 49430, 49438, 49453, 49449, 49425, 49447, 49458, 49463, 49459, 12236, 20287, 33604, 31548, 49178, 49168, 49158, 49506, 49429, 49421, 49435, 49426, 49412, 49417, 49441, 49444, 49418, 49445, 49411, 49442, 49440, 49415, 49416, 49413, 49450, 49437, 49473, 49469, 49467, 49456, 49410, 49451, 49462, 49457, 49432, 49472, 926, 7876, 7088});
                break;
            case 19576:
                model.setTexture(67, new int[] {37245, 32216, 31996, 39422, 30169, 31977, 29886, 31997});
                model.setTexture(62, new int[] {7588, 7577, 7579, 7578, 7571, 7572, 7574, 7580, 7589, 7573, 7575});
                break;
            case 11623:
                model.setTexture(62, new int[] {20});
                break;
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
                case 2399:
                model.setTexture(80);
                //model.printModelColors();
                break;
            case 20249:
                //model.printModelColors();
                break;
            case 15356:
            case 3851:
                //model.printModelColors();
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
            case 20002:
                model.setTexture(72);
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
