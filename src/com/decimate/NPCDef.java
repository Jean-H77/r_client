package com.decimate;

public final class NPCDef {

    public static int NPCAMOUNT = 11599;
    public static int cacheIndex;
    public static Stream stream;
    public static int[] streamIndices;
    public static NPCDef[] cache;
    public static Client clientInstance;
    public static MemCache modelCache = new MemCache(30);
    public int frontLight = 68;
    public int backLight = 820;
    public int rightLight = 0;
    public int middleLight = -1; // Cannot be 0
    public int leftLight = 0;
    public int turn90CCWAnimIndex;
    public int varbitId;
    public int turn180AnimIndex;
    public int varSettingsId;
    public int combatLevel;
    public String name;
    public String actions[];
    public int walkAnim;
    public int runAnim;
    public byte squaresNeeded;
    public int[] destColours;
    public int[] npcHeadModels;
    public int headIcon;
    public int[] originalColours;
    public int standAnim;
    public long type;
    public int degreesToTurn;
    public int turn90CWAnimIndex;
    public boolean clickable;
    public int lightning;
    public int sizeY;
    public boolean drawMinimapDot;
    public int childrenIDs[];
    public String description;
    public int sizeXZ;
    public int shadow;
    public boolean hasRenderPriority;
    public int[] models;
    public int id;
    public NPCDef() {
        turn90CCWAnimIndex = -1;
        varbitId = -1;
        turn180AnimIndex = -1;
        varSettingsId = -1;
        combatLevel = -1;
        walkAnim = -1;
        squaresNeeded = 1;
        headIcon = -1;
        standAnim = -1;
        type = -1L;
        degreesToTurn = 32;
        turn90CWAnimIndex = -1;
        clickable = true;
        sizeY = 128;
        drawMinimapDot = true;
        sizeXZ = 128;
        hasRenderPriority = false;
    }

    public boolean isPet() {
        switch ((int) type) {
            case 1313:// Shadow pet
            case 507: // MewTwo
            case 535: // Squirtle
            case 399: // Mustachio
            case 565: // Sonic
            case 480: // Bandicoot
            case 481: // Donkey Kong
            case 483: // Silent Whisper
            case 484: // Spikerz
            case 485: // Hollow
            case 486: // Exodia
            case 487: // Magician
            case 489: // Poseidon
            case 490: // Hades
            case 488: // Ichigo
            case 491: // Dracula
            case 432: // Thor
            case 429: // Zeus
            case 482: // Greater Diablo
            case 426: // Nymora
            case 382: // Avaryss
            case 3047: // Frost Dragon
            case 3052: // Solaris (firemaking pet)
            case 3053: // Bubbles (fishing pet)
            case 3054: // Brains (farming pet)
            case 3055: // Woody (woodcutting pet)
            case 3033: // Con-Mario (construction pet)
            case 3030: // Sly Cooper (thieving pet)
            case 3031: // Boo (prayer pet)
            case 3032: // Gordo (cooking pet)
            case 3034: // Old Man-miner (mining pet)
            case 3035: // Zunto (hunter pet)
            case 3036: // Ivy (herblore pet)
            case 379: // Sapphire dragon pet
            case 1011: // Emerald dragon pet
            case 560: // Icy Vorago pet
            case 561: // Solak pet
            case 562: // Witch Doctor pet
            case 563: // Seren pet
            case 4006: // Umber Pet
                return true;
        }
        return false;
    }

    public static NPCDef forID(int i) {
        for (int j = 0; j < 20; j++)
            if (cache[j].type == (long) i)
                return cache[j];
        cacheIndex = (cacheIndex + 1) % 20;
        NPCDef npc = cache[cacheIndex] = new NPCDef();
        if (i >= streamIndices.length)
            return null;
        stream.currentOffset = streamIndices[i];
        npc.type = i;
        npc.readValues(stream);
        if (npc.name != null && npc.name.toLowerCase().contains("bank")) {
            if (npc.actions != null) {
                for (int l = 0; l < npc.actions.length; l++) {
                    if (npc.actions[l] != null && npc.actions[l].equalsIgnoreCase("Collect"))
                        npc.actions[l] = null;
                }
            }
        }
        npc.id = i;
        switch (i) {
            case 4006:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91369;
                npc.name = "Umber Pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 4005:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91382;
                npc.name = "Dagda";
                npc.combatLevel = 1000;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                break;
            case 4004:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91381;
                npc.name = "Kanzi";
                npc.combatLevel = 1000;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                break;
            case 4003:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91369;
                npc.name = "Umber";
                npc.combatLevel = 100;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 4002:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91362;
                npc.name = "Enormous Scorpion";
                npc.combatLevel = 150;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 100;
                npc.sizeY = 100;
                break;
            case 4001:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91361;
                npc.name = "Mummy";
                npc.combatLevel = 150;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                break;
            case 4000:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91360;
                npc.name = "Evil Mystery Box";
                npc.combatLevel = 500;
                npc.description = "";
                npc.standAnim = 5022;
                npc.walkAnim = 5022;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 2026:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94628;
                npc.name = "Fharok";
                npc.combatLevel = 500;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 2027:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94629;
                npc.name = "Muthan";
                npc.combatLevel = 500;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 2028:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94630;
                npc.name = "Zaril";
                npc.combatLevel = 500;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 2025:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94631;
                npc.name = "Shrim";
                npc.combatLevel = 500;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 2029:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94632;
                npc.name = "Rorag";
                npc.combatLevel = 500;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 2030:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94633;
                npc.name = "Derac";
                npc.combatLevel = 500;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;

            //start of raid monsters
            case 515:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 64364;
                npc.name = "Frieza";
                npc.combatLevel = 1000;
                npc.description = "";
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.squaresNeeded = 2;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                npc.headIcon = 19;
                break;
            case 517:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 64383;
                npc.name = "Super Buu";
                npc.combatLevel = 1000;
                npc.description = "";
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.squaresNeeded = 2;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                npc.headIcon = 18;
                break;
            case 574:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 68;
                npc.name = "Cell";
                npc.combatLevel = 1000;
                npc.description = "";
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.squaresNeeded = 2;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                npc.headIcon = 17;
                break;
            //end of raid monsters

            case 555:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 50945;
                npc.name = "Dragonbone Dragon";
                npc.description = "";
                npc.standAnim = 10056;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                break;
            case 559:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90322;
                npc.name = "Icy vorago";
                npc.combatLevel = 2000;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 560:
                npc.actions = new String[]{"Pick up", null, "Interact", null, null};
                npc.models = new int[1];
                npc.models[0] = 90322;
                npc.name = "Icy Vorago pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 20;
                npc.sizeY = 20;
                break;
            case 561:
                npc.actions = new String[]{"Pick up", null, "Interact", null, null};
                npc.models = new int[1];
                npc.models[0] = 86;
                npc.name = "Solak pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 20;
                npc.sizeY = 20;
                break;
            case 562:
                npc.actions = new String[]{"Pick up", null, "Interact", null, null};
                npc.models = new int[1];
                npc.models[0] = 89;
                npc.name = "Witch Doctor pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 563:
                npc.actions = new String[]{"Pick up", null, "Interact", null, null};
                npc.models = new int[1];
                npc.models[0] = 87;
                npc.name = "Seren pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 50;
                break;
            case 1340:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91151;
                npc.name = "Invention pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 50;
                break;
            case 750:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90770;
                npc.name = "Corrupted Slayer";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                npc.headIcon = 19;
                break;
            case 752:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90771;
                npc.name = "Corrupted Wyvern";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 5021;
                npc.walkAnim = 5022;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                npc.headIcon = 19;
                break;
            case 754:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90772;
                npc.name = "Corrupted Bear";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 4920;
                npc.walkAnim = 4923;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                break;
            case 756:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90859;
                npc.name = "Yi";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 250;
                npc.sizeY = 250;
                break;
            case 758:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90860;
                npc.name = "Veigar";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 760:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90861;
                npc.name = "Gnar";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 160;
                npc.sizeY = 160;
                break;
            case 762:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90862;
                npc.name = "Shyv";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 220;
                npc.sizeY = 220;
                break;
            case 764:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90873;
                npc.name = "Syndra";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 766:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90874;
                npc.name = "Chogath";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                break;
            case 767:
                npc.actions = new String[]{null, null, "Siphon", null, null};
                npc.models = new int[1];
                npc.models[0] = 62883;
                npc.name = "Red Nexus";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 2;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                npc.degreesToTurn = 0;
                break;
            case 10:
                npc.actions = new String[]{"Open-Shop", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90896;
                npc.name = "Raid-Point Shop";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 2;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                break;
            case 11:
                npc.actions = new String[]{"Open-Shop", null, "Skilling Tasks", null, null};
                npc.models = new int[1];
                npc.models[0] = 90897;
                npc.name = "Skilling Shard Shop";
                npc.combatLevel = 0;
                npc.description = "";
                npc.squaresNeeded = 1;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                break;
            case 655:
                npc.actions = new String[]{"Open-Shop", null, null, null, null};
                npc.name = "Evil Kindling Shop";
                npc.combatLevel = 0;
                npc.description = "";
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                break;
            case 50:
                npc.name = "Dragon";
                npc.actions = new String[]{"Steal-hide", null, null, null, null};
                npc.combatLevel = 0;
                break;
                case 736:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90243;
                npc.name = "Gandalf";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                npc.headIcon = 19;
                break;
            case 730:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90244;
                npc.name = "Gimli";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                npc.headIcon = 18;
                npc.degreesToTurn = 32;
                break;
            case 732:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90245;
                npc.name = "Legolas";
                npc.combatLevel = 900;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                npc.headIcon = 17;
                break;
            //end of global monsters
            //::train monster's
            case 1011:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91013;
                npc.name = "@gre@Emerald Dragon Pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = NPCDef.forID(52).standAnim;
                npc.walkAnim = NPCDef.forID(52).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 60;
                npc.sizeY = 60;
                break;
            case 1010:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91013;
                npc.name = "Sapphire Dragon";
                npc.combatLevel = 1000;
                npc.standAnim = NPCDef.forID(52).standAnim;
                npc.walkAnim = NPCDef.forID(52).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 1009:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91013;
                npc.name = "Emerald Dragon";
                npc.combatLevel = 1000;
                npc.standAnim = NPCDef.forID(52).standAnim;
                npc.walkAnim = NPCDef.forID(52).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 1008:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91013;
                npc.name = "Diamond Dragon";
                npc.combatLevel = 1000;
                npc.standAnim = NPCDef.forID(52).standAnim;
                npc.walkAnim = NPCDef.forID(52).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 508:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 58467;
                npc.name = "Mewtwo";
                npc.combatLevel = 50;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 75;
                break;
            case 506:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 58467;
                npc.name = "Immortal Mewtwo";
                npc.combatLevel = 160;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 75;
                break;
            case 507:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 58467;
                npc.name = "Mewtwo";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 35;
                npc.sizeY = 45;
                break;
            case 1313:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.name = "Shadow Pet";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 88;
                break;
            case 519:
                npc.models = new int[1];
                npc.models[0] = 28233;
                npc.name = "Squirtle";
                npc.description = "It's squirtle.";
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.combatLevel = 50;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 75;
                break;
            case 534:
                npc.models = new int[2];
                npc.models[0] = 28233;
                npc.name = "Immortal Squirtle";
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.combatLevel = 160;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 60;
                npc.sizeY = 85;
                break;
            case 535:
                npc.models = new int[1];
                npc.models[0] = 28233;
                npc.name = "Squirtle";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 35;
                npc.sizeY = 45;
                break;
            //End of ::train monster's
            //Tier 1 monster's
            case 518:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90585;
                npc.name = "Mustachio";
                npc.combatLevel = 70;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 289:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 90585;
                npc.name = "Immortal Mustachio";
                npc.combatLevel = 200;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                break;
            case 399:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90585;
                npc.name = "Mustachio Pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 80;
                npc.sizeY = 80;
                break;
                case 571:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94216;
                npc.name = "Sonic";
                npc.combatLevel = 100;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                break;
            case 564:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 94216;
                npc.name = "Immortal Sonic";
                npc.combatLevel = 200;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 160;
                npc.sizeY = 160;
                break;
            case 565:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94216;
                npc.name = "Sonic";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 90;
                npc.sizeY = 90;
                break;
            case 573:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94218;
                npc.name = "Crash bandicoot";
                npc.combatLevel = 85;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                break;
            case 479:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 94218;
                npc.name = "Immortal bandicoot";
                npc.combatLevel = 200;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 200;
                npc.sizeY = 200;
                break;
            case 480:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94218;
                npc.name = "Crash bandicoot pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 120;
                npc.sizeY = 120;
                break;

            case 575:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94219;
                npc.name = "Donkey kong";
                npc.combatLevel = 100;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                break;
            case 478:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 94219;
                npc.name = "Immortal Donkey kong";
                npc.combatLevel = 240;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                break;
            case 481:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 94219;
                npc.name = "Donkey kong pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 50;
                break;

            case 556:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 66317;
                npc.name = "Hollow";
                npc.description = "";
                npc.combatLevel = 275;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 80;
                npc.sizeY = 80;
                break;
            case 472:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 66317;
                npc.name = "Immortal Hollow";
                npc.combatLevel = 300;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 80;
                npc.sizeY = 80;
                break;
            case 485:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 66317;
                npc.name = "Hollow pet";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 50;
                break;
            case 557:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 96115;
                npc.name = "Diablo";
                npc.combatLevel = 200;
                npc.description = "";
                npc.standAnim = NPCDef.forID(8350).standAnim;
                npc.walkAnim = NPCDef.forID(8350).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 70;
                npc.sizeY = 70;
                break;
            case 477:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 96115;
                npc.name = "Immortal diablo";
                npc.combatLevel = 800;
                npc.standAnim = NPCDef.forID(8350).standAnim;
                npc.walkAnim = NPCDef.forID(8350).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 70;
                npc.sizeY = 70;
                break;
            case 482:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 96115;
                npc.name = "Greater diablo pet";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = NPCDef.forID(8350).standAnim;
                npc.walkAnim = NPCDef.forID(8350).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 25;
                npc.sizeY = 25;
                break;
            case 511:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 98283;
                npc.name = "Silent whisper";
                npc.combatLevel = 225;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 476:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 98283;
                npc.name = "Immortal Silent whisper";
                npc.combatLevel = 160;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 483:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 98283;
                npc.name = "Silent whisper pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 70;
                npc.sizeY = 70;
                break;
            case 512:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 13558;
                npc.name = "Spikerz";
                npc.combatLevel = 250;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 110;
                npc.sizeY = 110;
                break;
            case 475:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 13558;
                npc.name = "Immortal Spikerz";
                npc.combatLevel = 160;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 110;
                npc.sizeY = 110;
                break;
            case 484:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 13558;
                npc.name = "Spikerz pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 2;
                npc.sizeXZ = 65;
                npc.sizeY = 65;
                break;
            case 1598:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 36040;
                npc.name = "Magician";
                npc.combatLevel = 350;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                npc.headIcon = 19;
                break;
            case 470:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 36040;
                npc.name = "Immortal Magician";
                npc.combatLevel = 350;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                npc.headIcon = 19;
                break;
            case 487:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 36040;
                npc.name = "Magician pet";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 666:
                npc.models = new int[1];
                npc.models[0] = 12354;
                npc.name = "Ichigo";
                npc.description = "";
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.combatLevel = 400;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                npc.headIcon = 18;
                break;
            case 469:
                npc.models = new int[2];
                npc.models[0] = 12354;
                npc.name = "Immortal Ichigo";
                npc.description = "";
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.combatLevel = 400;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                npc.headIcon = 18;
                break;
            case 488:
                npc.models = new int[1];
                npc.models[0] = 12354;
                npc.name = "Ichigo pet";
                npc.description = "";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.combatLevel = 0;
                npc.standAnim = 12021;
                npc.walkAnim = 12023;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 509:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 9980;
                npc.name = "Exodia";
                npc.combatLevel = 300;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                npc.headIcon = 17;
                break;
            case 471:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 9980;
                npc.name = "Immortal Exodia";
                npc.combatLevel = 400;
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                break;
            case 486:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 9980;
                npc.name = "Exodia pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 700:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 75;
                npc.name = "Poseidon";
                npc.description = "";
                npc.combatLevel = 450;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                npc.headIcon = 19;
                break;
            case 468:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 75;
                npc.name = "Immortal Poseidon";
                npc.description = "";
                npc.combatLevel = 450;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                npc.headIcon = 19;
                break;
            case 489:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 75;
                npc.name = "Poseidon pet";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 701:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 76;
                npc.name = "Dracula";
                npc.description = "";
                npc.combatLevel = 500;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                npc.headIcon = 18;
                break;
            case 467:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 76;
                npc.name = "Immortal Dracula";
                npc.description = "";
                npc.combatLevel = 500;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                npc.headIcon = 18;
                break;
            case 491:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 76;
                npc.name = "Dracula pet";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                break;
            case 702:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[4];
                npc.models[0] = 77;
                npc.models[1] = 90228;
                npc.models[2] = 90230;
                npc.models[3] = 90216;
                npc.combatLevel = 550;
                npc.name = "Hades";
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                npc.headIcon = 17;
                break;
            case 466:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[5];
                npc.models[0] = 77;
                npc.models[1] = 90228;
                npc.models[2] = 90230;
                npc.models[3] = 90216;
                npc.combatLevel = 550;
                npc.name = "Immortal Hades";
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                npc.headIcon = 17;
                break;
            case 490:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 77;
                npc.name = "Hades pet";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 90;
                npc.sizeY = 90;
                break;
            case 703:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 78;
                npc.name = "Thor";
                npc.combatLevel = 600;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                npc.headIcon = 18;
                break;
            case 465:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 78;
                npc.name = "Immortal Thor";
                npc.combatLevel = 600;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 175;
                npc.sizeY = 175;
                npc.headIcon = 18;
                break;
            case 432:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 78;
                npc.name = "Thor pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 100;
                npc.sizeY = 100;
                break;
            case 704:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 79;
                npc.name = "Zeus";
                npc.combatLevel = 650;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                npc.headIcon = 19;
                break;
            case 586:
                npc.actions = new String[]{"Talk-to", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91264;
                npc.name = "John";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 2305:
                npc.name = "@gre@Green Hornet";
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[]{90850, 90851, 91806, 91808, 91810, 91812, 91814, 91816};
                npc.combatLevel = 10000;
                npc.standAnim = 1501;
                npc.walkAnim = 1851;
                npc.sizeY = 200;
                npc.sizeXZ = 200;
                npc.squaresNeeded = 1;
                break;
            case 464:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 79;
                npc.name = "Immortal Zeus";
                npc.combatLevel = 650;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 75;
                npc.sizeY = 75;
                npc.headIcon = 19;
                break;
            case 429:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 79;
                npc.name = "Zeus pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 35;
                npc.sizeY = 35;
                break;
            case 426:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 85;
                npc.name = "Nymora pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 50;
                break;
            case 382:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 80;
                npc.name = "Avaryss pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 50;
                npc.sizeY = 50;
                break;
            case 379:
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91013;
                npc.name = "@blu@Sapphire Dragon Pet";
                npc.combatLevel = 0;
                npc.description = "";
                npc.standAnim = NPCDef.forID(52).standAnim;
                npc.walkAnim = NPCDef.forID(52).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 60;
                npc.sizeY = 60;
                break;
            case 706:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.combatLevel = 800;
                npc.models[0] = 80;
                npc.name = "Avaryss";
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 90;
                npc.sizeY = 90;
                npc.headIcon = 18;
                break;
            case 463:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.combatLevel = 800;
                npc.models[0] = 80;
                npc.name = "Immortal Avaryss";
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 90;
                npc.sizeY = 90;
                npc.headIcon = 18;
                break;
            case 710:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 85;
                npc.name = "Nymora";
                npc.combatLevel = 800;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 90;
                npc.sizeY = 90;
                npc.headIcon = 17;
                break;
            case 462:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[2];
                npc.models[0] = 85;
                npc.name = "Immortal Nymora";
                npc.combatLevel = 800;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 90;
                npc.sizeY = 90;
                npc.headIcon = 17;
                break;
            case 708:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 82;
                npc.name = "Conjoined Nymavaryss";
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 80;
                npc.sizeY = 80;
                break;
            case 711:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 86;
                npc.name = "Solak";
                npc.combatLevel = 2000;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 100;
                npc.sizeY = 100;
                break;
            case 712:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 87;
                npc.name = "Seren Vote Boss";
                npc.combatLevel = 1000;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 80;
                npc.sizeY = 80;
                break;
            case 713:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 88;
                npc.name = "Golden flying coin";
                npc.combatLevel = 25;
                npc.description = "";
                npc.standAnim = NPCDef.forID(78).standAnim;
                npc.walkAnim = NPCDef.forID(78).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 225;
                npc.sizeY = 225;
                break;
            case 716:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90135;
                npc.name = "Silver flying coin";
                npc.combatLevel = 50;
                npc.description = "";
                npc.standAnim = NPCDef.forID(78).standAnim;
                npc.walkAnim = NPCDef.forID(78).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 225;
                npc.sizeY = 225;
                break;
            case 717:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90136;
                npc.name = "Red flying coin";
                npc.combatLevel = 75;
                npc.description = "";
                npc.standAnim = NPCDef.forID(78).standAnim;
                npc.walkAnim = NPCDef.forID(78).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 225;
                npc.sizeY = 225;
                break;
            case 718:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90137;
                npc.name = "Purple flying coin";
                npc.combatLevel = 200;
                npc.description = "";
                npc.standAnim = NPCDef.forID(78).standAnim;
                npc.walkAnim = NPCDef.forID(78).walkAnim;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 225;
                npc.sizeY = 225;
                break;
            case 714:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 89;
                npc.name = "Witch Doctor";
                npc.combatLevel = 2000;
                npc.description = "";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 220;
                npc.sizeY = 220;
                break;
            case 291:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.originalColours = new int[]{40};
                npc.destColours = new int[]{34};
                npc.models = new int[]{64062, 82363, 82416, 82368, 13544, 15018, 15016};
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.name = "Tetsu";
                npc.combatLevel = 3000;
                break;
            case 3334:
                npc.name = "WildyWyrm";
                npc.models = new int[]{63604};
                //npc.boundDim = 1;
                npc.standAnim = 12790;
                npc.walkAnim = 12790;
                npc.combatLevel = 382;
                npc.actions = new String[5];
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.sizeXZ = 225;
                npc.sizeY = 200;
                //npc.sizeXZ = 35;
                //npc.sizeY = 75;
                break;

            case 1:
                npc.name = "Poison";
                npc.actions = new String[]{null, null, null, null, null};
                npc.sizeXZ = 1;
                npc.sizeY = 1;
                npc.drawMinimapDot = false;
                break;
            case 0:
                npc.name = " ";
                npc.actions = new String[]{null, null, null, null, null};
                npc.sizeXZ = 1;
                npc.sizeY = 1;
                npc.drawMinimapDot = false;
                break;
            case 273:
                npc.name = "Boss Point Shop";
                npc.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 3000:
                npc.name = "Cosmetically Awesome";
                npc.actions = new String[]{"Cosmetic Override", null, null, null, null};
                break;
            case 263:
                npc.name = "Extreme Donator Shop";
                npc.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 543:
                npc.name = "Decanter";
                break;
            case 4902:
                npc.name = "Expert Miner";
                npc.actions = new String[]{"Talk-To", null, "Trade", null, null};
                break;
            case 212:
                npc.name = "Donator Shop";
                npc.actions = new String[]{"View Shop 1", null, "View Shop 2", null, null};
                break;
            case 741:
                npc.name = "Donator Shop 2";
                break;
            case 2998:
                npc.name = "Gambler";
                npc.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 2633:
                npc.name = "Trivia Point Shop";
                break;
            case 1674:
                npc.name = "Afk Shop";
                npc.actions = new String[]{"Open Shop", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 90351;
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 220;
                npc.sizeY = 220;
                break;
            case 947:
                npc.name = "Player Owned Shop Manager";
                npc.actions = new String[]{"Talk-to", null, "View Shops", "My Shop", "Claim Earnings"};
                break;
            case 9939:
                npc.combatLevel = 607;
                break;
            case 688:
                npc.name = "Archer";
                break;
            case 4540:
                npc.combatLevel = 299;
                break;
            case 3101:
                npc.sizeY = npc.sizeXZ = 80;
                npc.squaresNeeded = 1;
                npc.actions = new String[]{"Talk-to", null, "Start", "Rewards", null};
                break;
            case 1610:
            case 10216:
                npc.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 7969:
                npc.actions = new String[]{"Talk-to", null, "Trade", null, null};
                break;
            case 1382:
                npc.name = "Glacor";
                npc.models = new int[]{58940};
                npc.squaresNeeded = 3;
                //	npc.anInt86 = 475;
                npc.sizeXZ = npc.sizeY = 180;
                npc.standAnim = 10869;
                npc.walkAnim = 10867;
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.combatLevel = 123;
                npc.drawMinimapDot = true;
                npc.combatLevel = 188;
                break;
            case 4249:
                npc.name = "Gambler";
                break;
            case 6970:
                npc.actions = new String[]{"Trade", null, "Exchange Shards", null, null};
                break;
            case 4657:
                npc.actions = new String[]{"Talk-to", null, "Check Total", "Teleport", null};
                break;
            case 364:
                npc.actions = new String[]{"Talk-to", null, "Vote Rewards", "Loyalty Titles", null};
                break;
            case 8591:
                npc.actions = new String[]{"Talk-to", null, "Trade", null, null};
                break;
            case 316:
            case 315:
            case 309:
            case 310:
            case 314:
            case 312:
            case 313:
                npc.sizeXZ = 30;
                break;
            case 318:
                npc.sizeXZ = 30;
                npc.actions = new String[]{"Net", null, "Lure", null, null};
                break;
            case 805:
                npc.actions = new String[]{"Trade", null, "Tan hide", null, null};
                break;
            case 461:
            case 844:
            case 650:
            case 5112:
            case 3789:
            case 802:
            case 520:
            case 521:
            case 11226:
                npc.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 8022:
            case 8028:
                String color = i == 8022 ? "Yellow" : "Green";
                npc.name = "" + color + " energy source";
                npc.actions = new String[]{"Siphon", null, null, null, null};
                break;
            case 8444:
                npc.actions = new String[5];
                npc.actions[0] = "Trade";
                break;
            case 2579:
                npc.name = "Max";
                npc.description = "One of Decimate's veterans.";
                npc.combatLevel = 200;
                npc.actions = new String[5];
                npc.actions[0] = "Talk-to";
//			npc.actions[2] = "Trade";
                npc.models = new int[8];
                npc.models[0] = 65289;
                npc.models[1] = 62746;
                npc.models[2] = 62743;
                npc.models[3] = 65305;
                npc.models[4] = 13307;
                npc.models[5] = 27738;
                npc.models[6] = 20147;
                npc.models[7] = 252;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.npcHeadModels = NPCDef.forID(517).npcHeadModels;
                break;
            case 6830:
            case 6841:
            case 6796:
            case 7331:
            case 6831:
            case 7361:
            case 6847:
            case 6872:
            case 7353:
            case 6835:
            case 6845:
            case 6808:
            case 7370:
            case 7333:
            case 7351:
            case 7367:
            case 6853:
            case 6855:
            case 6857:
            case 6859:
            case 6861:
            case 6863:
            case 9481:
            case 6827:
            case 6889:
            case 6813:
            case 6817:
            case 7372:
            case 6839:
            case 8575:
            case 7345:
            case 6799:
            case 7335:
            case 7347:
            case 6800:
            case 9488:
            case 6804:
            case 6822:
            case 6849:
            case 7355:
            case 7357:
            case 7359:
            case 7341:
            case 7329:
            case 7339:
            case 7349:
            case 7375:
            case 7343:
            case 6820:
            case 6865:
            case 6809:
            case 7363:
            case 7337:
            case 7365:
            case 6991:
            case 6992:
            case 6869:
            case 6818:
            case 6843:
            case 6823:
            case 7377:
            case 6887:
            case 6885:
            case 6883:
            case 6881:
            case 6879:
            case 6877:
            case 6875:
            case 6833:
            case 6851:
            case 5079:
            case 5080:
            case 6824:
                npc.actions = new String[]{null, null, null, null, null};
                break;
            case 6806: // thorny snail
            case 6807:
            case 6994: // spirit kalphite
            case 6995:
            case 6867: // bull ant
            case 6868:
            case 6794: // spirit terrorbird
            case 6795:
            case 6815: // war tortoise
            case 6816:
            case 6874:// pack yak
            case 6873: // pack yak
            case 3594: // yak
            case 3590: // war tortoise
            case 3596: // terrorbird
                npc.actions = new String[]{"Store", null, null, null, null};
                break;
            case 548:
                npc.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 3299:
            case 437:
                npc.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 1265:
            case 8459:
                npc.drawMinimapDot = true;
                break;
            case 961:
                npc.actions = new String[]{null, null, "Buy Consumables", "Restore Stats", null};
                npc.name = "Healer";
                break;
            case 705:
                npc.actions = new String[]{null, null, "Buy Armour", "Buy Weapons", "Buy Jewelries"};
                npc.name = "Warrior";
                break;
            case 1861:
                npc.actions = new String[]{null, null, "Buy Equipment", "Buy Ammunition", null};
                npc.name = "Archer";
                break;
            case 946:
                npc.actions = new String[]{null, null, "Buy Equipment", "Buy Runes", null};
                npc.name = "Mage";
                break;
            case 2253:
                npc.actions = new String[]{null, null, "Buy Skillcapes", "Buy Master Skillcapes", "Buy Hoods"};
                break;
            case 2292:
                npc.actions = new String[]{"Trade", null, null, null, null};
                npc.name = "Merchant";
                break;
            case 2676:
                npc.actions = new String[]{"Makeover", null, null, null, null};
                break;
            case 494:
            case 1360:
                npc.actions = new String[]{"Talk-to", null, null, null, null};
                break;
            case 659:
                npc.actions = new String[]{"Talk-to", null, null, null, null};
                break;
            case 1685:
                npc.name = "Pure";
                npc.actions = new String[]{"Trade", null, null, null, null};
                break;
            case 1266:
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.name = "Gim Pet 1";
                npc.models = new int[]{28057};
                npc.walkAnim = 808;
                npc.standAnim = 819;
                npc.sizeXZ = 130;
                npc.sizeY = 130;
                npc.squaresNeeded = 1;
                npc.drawMinimapDot = false;
                break;
            case 1267:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.name = "Gim Boss 1";
                npc.combatLevel = 5000;
                npc.models = new int[]{28057};
                npc.walkAnim = 808;
                npc.standAnim = 819;
                npc.sizeXZ = 325;
                npc.sizeY = 325;
                npc.squaresNeeded = 1;
                npc.drawMinimapDot = true;
                break;
            case 2006:
                npc.models = new int[1];
                npc.models[0] = 9941;
                npc.name = "Infernal minion";
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.combatLevel = 5000;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 325;
                npc.sizeXZ = 325;
                npc.squaresNeeded = 1;
                npc.drawMinimapDot = true;
                break;
            case 2007:
                npc.models = new int[1];
                npc.models[0] = 9941;
                npc.name = "Gim Pet 2";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 130;
                npc.sizeXZ = 130;
                npc.squaresNeeded = 1;
                npc.drawMinimapDot = false;
                break;
            case 2008:
                npc.models = new int[1];
                npc.models[0] = 26253;
                npc.combatLevel = 0;
                npc.name = "Clue Pup";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.standAnim = 15240;
                npc.walkAnim = 6583;
                npc.sizeY = 130;
                npc.sizeXZ = 130;
                npc.squaresNeeded = 1;
                npc.drawMinimapDot = false;
                break;
            case 2031:
                npc.models = new int[1];
                npc.models[0] = 9941;
                npc.name = "Infernal King";
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.combatLevel = 5000;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 425;
                npc.sizeXZ = 425;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = true;
                break;
            case 649:
            case 652:
                npc.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 989:
                npc.name = "Warrior";
                npc.actions = new String[]{null, "Attack", null, null, null};
                break;
            case 3022:
                npc.models = new int[]{28300};
                npc.name = "Vet'ion";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 464;
                npc.standAnim = 5483;
                npc.walkAnim = 5481;
                npc.sizeXZ = 60;
                npc.sizeY = 60;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3023:
                npc.models = new int[]{29270};
                npc.name = "Cerberus";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 464;
                npc.standAnim = 4484;
                npc.walkAnim = 4488;
                npc.sizeXZ = 45;
                npc.sizeY = 45;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3024:
                npc.models = new int[]{28293};
                npc.name = "Scorpia";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 464;
                NPCDef scor2 = forID(107);
                npc.standAnim = scor2.standAnim;
                npc.walkAnim = scor2.walkAnim;
                npc.sizeY = 60;
                npc.sizeXZ = 60;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3025:
                npc.models = new int[]{31653};
                npc.name = "Skotizo";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 321;
                npc.standAnim = 55;
                npc.walkAnim = 55;
                npc.sizeY = 35;
                npc.sizeXZ = 35;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3026:
                npc.models = new int[]{28294, 28295};
                npc.name = "Venenatis";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 464;
                npc.standAnim = 5326;
                npc.walkAnim = 5325;
                npc.sizeY = 55;
                npc.sizeXZ = 55;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3027:
                npc.models = new int[]{28298};
                npc.name = "Dragonball Z Pet";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.combatLevel = 0;
                npc.standAnim = 55;
                npc.walkAnim = 55;
                npc.sizeY = 60;
                npc.sizeXZ = 60;
                npc.squaresNeeded = 1;
                npc.drawMinimapDot = false;
                break;
            case 3028:
                npc.models = new int[]{14409};
                npc.name = "Snakeling";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 725;
                npc.standAnim = 5070;
                npc.walkAnim = 5070;
                npc.sizeY = 45;
                npc.sizeXZ = 45;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3029:
                npc.models = new int[]{14408};
                npc.name = "Snakeling";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 725;
                npc.standAnim = 5070;
                npc.walkAnim = 5070;
                npc.sizeY = 45;
                npc.sizeXZ = 45;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;

            case 3041:
                npc.models = new int[]{14407};
                npc.name = "Snakeling";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 725;
                npc.standAnim = 5070;
                npc.walkAnim = 5070;
                npc.sizeY = 45;
                npc.sizeXZ = 45;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3042:
                npc.models = new int[]{4039};
                npc.name = "Lizardman Shaman";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 150;
                npc.standAnim = 7191;
                npc.walkAnim = 7195;
                npc.sizeY = 40;
                npc.sizeXZ = 40;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3043:
                npc.models = new int[]{63604};
                npc.name = "Wildywyrm";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 382;
                npc.standAnim = 12790;
                npc.walkAnim = 12790;
                npc.sizeY = 60;
                npc.sizeXZ = 60;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3044:
                npc.models = new int[]{28442};
                npc.name = "Thermonuclear Smoke Devil";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 301;
                npc.standAnim = 1829;
                npc.walkAnim = 1828;
                npc.sizeY = 80;
                npc.sizeXZ = 80;
                npc.squaresNeeded = 2;
                npc.drawMinimapDot = false;
                break;
            case 3045:
                npc.models = new int[]{29477};
                npc.name = "Abyssal Sire";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 350;
                npc.standAnim = 4533;
                npc.walkAnim = 4534;
                npc.sizeY = 35;
                npc.sizeXZ = 35;
                npc.squaresNeeded = 3;
                npc.drawMinimapDot = false;
                break;
            case 3030:
                npc.name = "Sly Cooper";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[]{91799};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 120;
                npc.sizeXZ = 120;
                break;

            case 3031:
                npc.name = "Boo";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[]{27789};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 120;
                npc.sizeXZ = 120;
                break;

            case 3032:
                npc.name = "Gordo";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[]{34131};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 120;
                npc.sizeXZ = 120;
                npc.squaresNeeded = 1;
                break;

            case 3033:
                npc.name = "Con-Mario";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[]{91798};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeY = 120;
                npc.sizeXZ = 120;
                break;

            case 3034:
                npc.name = "The Old Man-Miner";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[]{40955};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 120;
                npc.sizeXZ = 120;
                npc.squaresNeeded = 1;
                break;

            case 3035:
                npc.name = "Zunto";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[]{28003};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 120;
                npc.sizeXZ = 120;
                npc.squaresNeeded = 1;
                break;

            case 3036:
                npc.name = "Ivy";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.models = new int[]{27768};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 120;
                npc.sizeXZ = 120;
                npc.squaresNeeded = 1;
                break;
            case 3037:
                npc.name = "Commander zilyana";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.models = new int[]{28057, 28071, 28078, 28056};
                npc.combatLevel = 596;
                npc.standAnim = 6963;
                npc.walkAnim = 6962;
                npc.sizeY = 103;
                npc.sizeXZ = 103;
                npc.squaresNeeded = 2;
                break;
            case 3038:
                npc.name = "Dagannoth supreme";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.models = new int[]{9941, 9943};
                npc.combatLevel = 303;
                npc.standAnim = 2850;
                npc.walkAnim = 2849;
                npc.sizeY = 105;
                npc.sizeXZ = 105;
                npc.squaresNeeded = 2;
                break;

            case 3039:
                npc.name = "Dagannoth prime"; //9940, 9943, 9942
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.models = new int[]{9940, 9943, 9942};
                npc.originalColours = new int[]{11930, 27144, 16536, 16540};
                npc.destColours = new int[]{5931, 1688, 21530, 21534};
                npc.combatLevel = 303;
                npc.standAnim = 2850;
                npc.walkAnim = 2849;
                npc.sizeY = 105;
                npc.sizeXZ = 105;
                npc.squaresNeeded = 2;
                break;

            case 3040:
                npc.name = "Dagannoth rex";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.models = new int[]{9941};
                npc.originalColours = new int[]{16536, 16540, 27144, 2477};
                npc.destColours = new int[]{7322, 7326, 10403, 2595};
                npc.combatLevel = 303;
                npc.standAnim = 2850;
                npc.walkAnim = 2849;
                npc.sizeY = 105;
                npc.sizeXZ = 105;
                npc.squaresNeeded = 2;
                break;
            case 3047:
                npc.name = "Frost dragon";
                npc.combatLevel = 0;
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.standAnim = 13156;
                npc.walkAnim = 13157;
                npc.turn180AnimIndex = -1;
                npc.turn90CCWAnimIndex = -1;
                npc.turn90CWAnimIndex = -1;
                //npc.type = 51;
                npc.degreesToTurn = 32;
                npc.models = new int[]{56767, 55294};
                npc.sizeY = 50;
                npc.sizeXZ = 50;
                npc.squaresNeeded = 1;
                break;

            case 3048:
                npc.models = new int[]{44733};
                npc.name = "Tormented demon";
                npc.combatLevel = 450;
                npc.standAnim = 10921;
                npc.walkAnim = 10920;
                npc.turn180AnimIndex = -1;
                npc.turn90CCWAnimIndex = -1;
                npc.turn90CWAnimIndex = -1;
                //	npc.type = 8349;
                npc.degreesToTurn = 32;
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.sizeY = 60;
                npc.sizeXZ = 60;
                npc.squaresNeeded = 2;
                break;
            case 3050:
                npc.models = new int[]{24602, 24605, 24606};
                npc.name = "Kalphite queen";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 333;
                npc.standAnim = 6236;
                npc.walkAnim = 6236;
                npc.sizeY = 70;
                npc.sizeXZ = 70;
                npc.squaresNeeded = 2;
                break;
            case 3051:
                npc.models = new int[]{46141};
                npc.name = "Slash bash";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 111;
                npc.standAnim = 11460;
                npc.walkAnim = 11461;
                npc.sizeY = 65;
                npc.sizeXZ = 65;
                npc.squaresNeeded = 2;
                break;
            case 3052:
                npc.models = new int[]{45412};
                npc.name = "Solaris";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.combatLevel = 0;
                npc.standAnim = 11074;
                npc.walkAnim = 11075;
                npc.sizeY = 70;
                npc.sizeXZ = 70;
                npc.squaresNeeded = 2;
                break;
            case 3053:
                npc.models = new int[]{46058};
                npc.name = "Bubbles";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 100;
                npc.sizeXZ = 100;
                npc.squaresNeeded = 1;
                break;
            case 3054:
                npc.models = new int[]{62717};
                npc.name = "Brains";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 125;
                npc.sizeXZ = 125;
                npc.squaresNeeded = 1;
                break;
            case 3055:
                npc.models = new int[]{51852};
                npc.name = "Woody";
                npc.actions = new String[]{"Pick up", null, null, null, null};
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.sizeY = 160;
                npc.sizeXZ = 160;
                npc.squaresNeeded = 1;
                break;
            case 3056:
                npc.models = new int[]{51848, 51850};
                npc.name = "Desert strykewyrm";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 130;
                npc.standAnim = 12790;
                npc.walkAnim = 12790;
                npc.sizeY = 60;
                npc.sizeXZ = 60;
                npc.squaresNeeded = 1;
                break;
            case 3057:
                npc.models = new int[]{51847, 51849};
                npc.name = "Ice strykewyrm";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 210;
                npc.standAnim = 12790;
                npc.walkAnim = 12790;
                npc.sizeY = 65;
                npc.sizeXZ = 65;
                npc.squaresNeeded = 1;
                break;
            case 3058:
                npc.models = new int[]{49142, 49144};
                npc.name = "Green dragon";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 79;
                npc.standAnim = 12248;
                npc.walkAnim = 12246;
                npc.sizeY = 70;
                npc.sizeXZ = 70;
                npc.squaresNeeded = 2;
                break;
            case 3059:
                npc.models = new int[]{57937};
                npc.name = "Baby blue dragon";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 48;
                npc.standAnim = 14267;
                npc.walkAnim = 14268;
                npc.sizeY = 85;
                npc.sizeXZ = 85;
                npc.squaresNeeded = 1;
                break;
            case 3060:
                npc.models = new int[]{49137, 49144};
                npc.name = "Blue dragon";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 111;
                npc.standAnim = 12248;
                npc.walkAnim = 12246;
                npc.sizeY = 70;
                npc.sizeXZ = 70;
                npc.squaresNeeded = 2;
                break;
            case 3061:
                npc.models = new int[]{14294, 49144};
                npc.name = "Black dragon";
                npc.actions = new String[5];
                npc.actions[0] = "Pick-up";
                npc.combatLevel = 227;
                npc.standAnim = 12248;
                npc.walkAnim = 12246;
                npc.sizeY = 70;
                npc.sizeXZ = 70;
                npc.squaresNeeded = 2;
                break;
            case 7780:
                npc.models = new int[]{24521, 90336};
                npc.name = "Reaper Master";
                break;
            case 4100:
                npc.actions = new String[]{null, "Attack", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91118;
                npc.name = "Reaper of Souls";
                npc.description = "";
                npc.combatLevel = 9999;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 4101:
                npc.actions = new String[]{null, "Talk-To", null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91150;
                npc.name = "Invention Tutor";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 125;
                npc.sizeY = 125;
                break;
            case 4102:
                npc.actions = new String[]{"Open-Shop", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91231;
                npc.name = "Sapphire Donator Shop";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 160;
                npc.sizeY = 160;
                break;
            case 4103:
                npc.actions = new String[]{"Open-Shop", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91232;
                npc.name = "Emerald Donator Shop";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 180;
                npc.sizeY = 180;
                break;
            case 4104:
                npc.actions = new String[]{"Open-Shop", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91233;
                npc.name = "Ruby Donator Shop";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 180;
                npc.sizeY = 180;
                break;
            case 4105:
                npc.actions = new String[]{"Open-Shop", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91234;
                npc.name = "Diamond Donator Shop";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 180;
                npc.sizeY = 180;
                break;
            case 4106:
                npc.actions = new String[]{"Open-Shop", null, null, null, null};
                npc.models = new int[1];
                npc.models[0] = 91235;
                npc.name = "Onyx Donator Shop";
                npc.description = "";
                npc.combatLevel = 0;
                npc.standAnim = 808;
                npc.walkAnim = 819;
                npc.squaresNeeded = 1;
                npc.sizeXZ = 180;
                npc.sizeY = 180;
                break;
        }
        return npc;
    }

    public static void unpackConfig(CacheArchive streamLoader) {
        stream = new Stream(streamLoader.getDataForName("npc.dat"));
        Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
        int totalNPCs = stream2.readUnsignedWord();
        streamIndices = new int[totalNPCs];
        int i = 2;
        for (int j = 0; j < totalNPCs; j++) {
            streamIndices[j] = i;
            i += stream2.readUnsignedWord();
        }
        cache = new NPCDef[20];
        for (int k = 0; k < 20; k++)
            cache[k] = new NPCDef();
        //NPCDefThing2.initialize();

        //printDefinitionsForId(98);
    }

    public static void printDefinitionsForId(int id) {
        NPCDef dump = NPCDef.forID(id);
        if (dump.name != null) {
            System.out.println("Dumping: "+dump.name);
        } else {
            System.out.println("NpcDefition.get("+id+").name == null");
        }
        System.out.println("combatlevel: "+dump.combatLevel);
        System.out.println("id: "+dump.id);
        if (dump.models != null) {
            for (int i = 0; i < dump.models.length; i++) {
                System.out.println("npcModels[" + i + "]: " + dump.models[i]);
            }
        }
        if (dump.actions != null) {
            for (int i = 0; i < dump.actions.length; i++) {
                System.out.println("Action[" + i + "]: " + dump.actions[i]);
            }
        }
        System.out.println("degreesToTurn: "+dump.degreesToTurn);
        System.out.println("headIcon: "+dump.headIcon);
        System.out.println("npcSizeInSquares: "+dump.squaresNeeded);
        System.out.println("standAnimation: "+dump.standAnim);
        System.out.println("walkAnimation: "+dump.walkAnim);
    }

    public static void nullLoader() {
        modelCache = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    private static void applyTexturing(Model model, int npcId) {
        switch (npcId) {
            case 2026:
                model.setTexture(121, new int[] {97});
                break;
            case 2027:
                model.setTexture(122, new int[] {97});
                break;
            case 2028:
                model.setTexture(123, new int[] {97});
                break;
            case 2025:
                model.setTexture(124, new int[] {97});
                break;
            case 2029:
                model.setTexture(125, new int[] {97});
                break;
            case 2030:
                model.setTexture(126, new int[] {97});
                break;
            case 1011:
                model.setTexture(100, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;
            case 1009:
                model.setTexture(100, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;
            case 1008:
                model.setTexture(120, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;
            case 379:
                model.setTexture(101, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;
            case 1010:
                model.setTexture(101, new int[] {927, 921, 0, 926, 105, 684, 804, 923, 924, 1951});
                break;
            case 506:
                model.setTexture(62, new int[] {57782});
                model.setTexture(86, new int[] {101});
                break;
            case 534:
                model.setTexture(86, new int[] {36160});
                break;
            case 289:
                model.setTexture(86, new int[] {4412, 4415, 4406, 4398, 4410, 4532, 4539, 4549, 4553, 4550});
                break;
            case 564:
                model.setTexture(86, new int[] {40862, 40850, 64418});
                break;
            case 479:
                model.setTexture(86, new int[] {42798, 42773, 1425, 104});
                break;
            case 478:
                model.setTexture(86, new int[] {5532, 4501});
                break;
            case 477:
                model.setTexture(86, new int[] {2, 8, 5, 4, 3, 6, 18, 11});
                break;
            case 476:
                model.setTexture(86, new int[] {85, 78, 65, 19, 28, 38, 33, 74, 64, 35, 23});
                break;
            case 475:
                model.setTexture(86, new int[] {62});
                break;
            case 472:
                model.setTexture(86, new int[] {43910, 44936, 7103, 45962, 43919});
                break;
            case 471:
                model.setTexture(86, new int[] {6811, 6694, 7362});
                break;
            case 470:
                model.setTexture(86, new int[] {48653, 48688});
                break;
            case 469:
                model.setTexture(86, new int[] {108, 120, 77});
                break;
            case 468:
                model.setTexture(86, new int[] {37317, 37321, 37307, 37301, 31527, 31515, 38934, 35907, 32703});
                break;
            case 467:
                model.setTexture(86, new int[] {925, 935, 924, 944, 939, 946, 926, 929, 921, 943, 936, 937, 923, 918, 928, 931, 932, 938, 919, 922, 917, 920, 1563, 1558, 1566, 1546, 1544, 1549, 1552, 1575, 1577});
                break;
            case 466:
                model.setTexture(86, new int[] {60576, 60574, 60453, 60450, 60573, 60451, 60580, 60578, 60577, 60454, 60457, 60440, 60452, 60459, 60460, 60461, 60443, 60448, 60449, 60445, 60446, 60458, 60462, 61478, 61476, 61474, 61600, 61601, 60570, 61488, 61484, 61603, 60575, 60579, 61605, 61481, 61487, 61483, 60447, 61475, 61485, 60455});
                break;
            case 465:
                model.setTexture(86, new int[] {32703, 9012, 115, 914});
                break;
            case 464:
                model.setTexture(86, new int[] {64179, 64038, 64043, 64184, 64046, 62538, 62504, 62526, 62514, 62507, 62499, 62492, 62498, 62506, 62520, 62512, 62489, 35284, 32791, 33812, 32804, 32820, 32807, 32814, 42079, 44223, 34332, 34340, 34325, 34322, 34462, 34468, 33989, 34491, 35021, 34239, 34436, 34471, 34492, 35261, 35254, 34243, 35267, 35249, 35241, 35236, 60490, 33806, 33824, 32801, 32796, 34223, 33222, 36312, 36303, 36287, 35279, 35271, 35253, 35268, 35248, 35266, 35276, 35286, 35272, 35278, 35257});
                break;
            case 2006:
            case 2031:
                model.setTexture(103, new int[] {2988, 2977});
                break;

        }
    }

    public Model getHeadModel() {
        if (childrenIDs != null) {
            NPCDef altered = getAlteredNPCDef();
            if (altered == null)
                return null;
            else
                return altered.getHeadModel();
        }
        if (npcHeadModels == null)
            return null;
        boolean everyFetched = false;
        for (int i = 0; i < npcHeadModels.length; i++)
            if (!Model.modelIsFetched(npcHeadModels[i]))
                everyFetched = true;
        if (everyFetched)
            return null;
        Model parts[] = new Model[npcHeadModels.length];
        for (int j = 0; j < npcHeadModels.length; j++)
            parts[j] = Model.fetchModel(npcHeadModels[j]);
        Model completeModel;
        if (parts.length == 1)
            completeModel = parts[0];
        else
            completeModel = new Model(parts.length, parts);
        if (originalColours != null) {
            for (int k = 0; k < originalColours.length; k++)
                completeModel.recolour(originalColours[k], destColours[k]);
        }
        applyTexturing(completeModel, id);
        return completeModel;
    }

    public NPCDef getAlteredNPCDef() {
        try {
            int j = -1;
            if (varbitId != -1) {
                VarBit varBit = VarBit.cache[varbitId];
                int k = varBit.configId;
                int l = varBit.leastSignificantBit;
                int i1 = varBit.mostSignificantBit;
                int j1 = Client.anIntArray1232[i1 - l];
                j = clientInstance.variousSettings[k] >> l & j1;
            } else if (varSettingsId != -1) {
                j = clientInstance.variousSettings[varSettingsId];
            }
            if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
                return null;
            } else {
                return forID(childrenIDs[j]);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Model getAnimatedModel(int j, int k, int ai[]) {
        if (childrenIDs != null) {
            NPCDef npc = getAlteredNPCDef();
            if (npc == null)
                return null;
            else
                return npc.getAnimatedModel(j, k, ai);
        }
        Model completedModel = (Model) modelCache.get(type);
        if (completedModel == null) {
            boolean everyModelFetched = false;
            for (int ptr = 0; ptr < models.length; ptr++)
                if (!Model.modelIsFetched(models[ptr]))
                    everyModelFetched = true;

            if (everyModelFetched)
                return null;
            Model parts[] = new Model[models.length];
            for (int j1 = 0; j1 < models.length; j1++)
                parts[j1] = Model.fetchModel(models[j1]);
            if (parts.length == 1)
                completedModel = parts[0];
            else
                completedModel = new Model(parts.length, parts);
            if (originalColours != null) {
                for (int k1 = 0; k1 < originalColours.length; k1++)
                    completedModel.recolour(originalColours[k1], destColours[k1]);
            }
            applyTexturing(completedModel, id);
            completedModel.createBones();
            completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
            modelCache.put(completedModel, type);
        }
        Model animatedModel = Model.entityModelDesc;
        animatedModel.method464(completedModel, FrameReader.isFrameNull(k) & FrameReader.isFrameNull(j));
        if (k != -1 && j != -1)
            animatedModel.method471(ai, j, k);
        else if (k != -1)
            animatedModel.applyTransform(k);
        if (sizeXZ != 128 || sizeY != 128)
            animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
        animatedModel.calculateDiagonals();
        animatedModel.triangleSkin = null;
        animatedModel.vertexSkin = null;
        if (squaresNeeded == 1)
            animatedModel.rendersWithinOneTile = true;
        return animatedModel;
    }

    public Model method164(Entity entity, int j, int frame, int ai[], int nextFrame, int idk, int idk2) {
        if (childrenIDs != null) {
            NPCDef npc = getAlteredNPCDef();
            if (npc == null)
                return null;
            else
                return npc.method164(entity, j, frame, ai, nextFrame, idk, idk2);
        }
        Model completedModel = (Model) modelCache.get(type);
        if (completedModel == null) {
            boolean everyModelFetched = false;
            for (int ptr = 0; ptr < models.length; ptr++)
                if (!Model.modelIsFetched(models[ptr]))
                    everyModelFetched = true;

            if (everyModelFetched)
                return null;
            Model parts[] = new Model[models.length];
            for (int j1 = 0; j1 < models.length; j1++)
                parts[j1] = Model.fetchModel(models[j1]);
            if (parts.length == 1)
                completedModel = parts[0];
            else
                completedModel = new Model(parts.length, parts);
            if (originalColours != null) {
                for (int k1 = 0; k1 < originalColours.length; k1++)
                    completedModel.recolour(originalColours[k1], destColours[k1]);
            }
            applyTexturing(completedModel, id);
            completedModel.createBones();
            completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
            modelCache.put(completedModel, type);
        }
        Model animatedModel = Model.entityModelDesc;
        animatedModel.method464(completedModel, FrameReader.isFrameNull(frame) & FrameReader.isFrameNull(j));

        if (frame != -1 && j != -1)
            animatedModel.method471(ai, j, frame);
        else if (frame != -1 && nextFrame != -1)
            animatedModel.applyTransform(frame, nextFrame, idk, idk2);
        else if (frame != -1)
            animatedModel.applyTransform(frame);
        if (sizeXZ != 128 || sizeY != 128)
            animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
        animatedModel.calculateDiagonals();
        animatedModel.triangleSkin = null;
        animatedModel.vertexSkin = null;
        if (squaresNeeded == 1)
            animatedModel.rendersWithinOneTile = true;
        return animatedModel;
    }

    public void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0)
                return;
            if (i == 1) {
                int j = stream.readUnsignedByte();
                models = new int[j];
                for (int j1 = 0; j1 < j; j1++)
                    models[j1] = stream.readUnsignedWord();
            } else if (i == 2)
                name = stream.readNewString();
            else if (i == 3) {
                description = stream.readNewString();
            } else if (i == 12)
                squaresNeeded = stream.readSignedByte();
            else if (i == 13)
                standAnim = stream.readUnsignedWord();
            else if (i == 14) {
                walkAnim = stream.readUnsignedWord();
                runAnim = walkAnim;
            } else if (i == 17) {
                walkAnim = stream.readUnsignedWord();
                turn180AnimIndex = stream.readUnsignedWord();
                turn90CWAnimIndex = stream.readUnsignedWord();
                turn90CCWAnimIndex = stream.readUnsignedWord();
                if (walkAnim == 65535)
                    walkAnim = -1;
                if (turn180AnimIndex == 65535)
                    turn180AnimIndex = -1;
                if (turn90CWAnimIndex == 65535)
                    turn90CWAnimIndex = -1;
                if (turn90CCWAnimIndex == 65535)
                    turn90CCWAnimIndex = -1;
            } else if (i >= 30 && i < 40) {
                if (actions == null)
                    actions = new String[5];
                actions[i - 30] = stream.readNewString();
                if (actions[i - 30].equalsIgnoreCase("hidden"))
                    actions[i - 30] = null;
            } else if (i == 40) {
                int k = stream.readUnsignedByte();
                destColours = new int[k];
                originalColours = new int[k];
                for (int k1 = 0; k1 < k; k1++) {
                    originalColours[k1] = stream.readUnsignedWord();
                    destColours[k1] = stream.readUnsignedWord();
                }
            } else if (i == 60) {
                int l = stream.readUnsignedByte();
                npcHeadModels = new int[l];
                for (int l1 = 0; l1 < l; l1++)
                    npcHeadModels[l1] = stream.readUnsignedWord();
            } else if (i == 90)
                stream.readUnsignedWord();
            else if (i == 91)
                stream.readUnsignedWord();
            else if (i == 92)
                stream.readUnsignedWord();
            else if (i == 93)
                drawMinimapDot = false;
            else if (i == 95)
                combatLevel = stream.readUnsignedWord();
            else if (i == 97)
                sizeXZ = stream.readUnsignedWord();
            else if (i == 98)
                sizeY = stream.readUnsignedWord();
            else if (i == 99)
                hasRenderPriority = true;
            else if (i == 100)
                lightning = stream.readSignedByte();
            else if (i == 101)
                shadow = stream.readSignedByte() * 5;
            else if (i == 102)
                headIcon = stream.readUnsignedWord();
            else if (i == 103)
                degreesToTurn = stream.readUnsignedWord();
            else if (i == 106) {
                varbitId = stream.readUnsignedWord();
                if (varbitId == 65535)
                    varbitId = -1;
                varSettingsId = stream.readUnsignedWord();
                if (varSettingsId == 65535)
                    varSettingsId = -1;
                int i1 = stream.readUnsignedByte();
                childrenIDs = new int[i1 + 1];
                for (int i2 = 0; i2 <= i1; i2++) {
                    childrenIDs[i2] = stream.readUnsignedWord();
                    if (childrenIDs[i2] == 65535)
                        childrenIDs[i2] = -1;
                }
            } else if (i == 107)
                clickable = false;
        } while (true);
    }
}