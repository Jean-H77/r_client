package com.decimate;

import java.util.*;

public class ParticleAttachment {

	private static final Map<Integer, int[][]> attachments = new HashMap<>();

	public static int[][] getAttachments(int model) {
        return attachments.get(model);
    }

	static {
        // Completionist cape
        attachments.put(65297, new int[][] { { 494, 0 }, { 488, 0 }, { 485, 0 }, { 476, 0 }, { 482, 0 }, { 479, 0 }, { 491, 0 } });
        attachments.put(65316, new int[][] { { 494, 0 }, { 488, 0 }, { 485, 0 }, { 476, 0 }, { 482, 0 }, { 479, 0 }, { 491, 0 } });

        // Gandalf Set
        attachments.put(90247, new int[][] { { 52, 1 }, { 19, 1 }, { 10, 1 }, { 8, 1 }, { 14, 1 }, { 27, 1 }, { 60, 1 } }); // Mask
        attachments.put(90258, new int[][] { { 56, 1 }, { 127, 1 }, { 42, 1 }, { 45, 1 } }); // Robe top
        attachments.put(90256, new int[][] { { 83, 1 }, { 80, 1 }, { 78, 1 } }); // Robe bottoms
        attachments.put(90249, new int[][] { { 805, 1 }, { 829, 1 }, { 815, 1 }, { 53, 1 }, { 764, 1 }, { 766, 1 } }); // Staff
        attachments.put(90251, new int[][] { { 278, 1 }, { 279, 1 }, { 1, 1 }, { 0, 1 } }); // Book
        attachments.put(90252, new int[][] { { 85, 1 }, { 86, 1 }, { 110, 1 }, { 29, 1 }, { 30, 1 }, { 54, 1 } }); // Boots
        attachments.put(90254, new int[][] { { 95, 1 }, { 96, 1 }, { 98, 1 }, { 40, 1 }, { 37, 1 }, { 38, 1 } }); // Gloves

        // Gimili Set
        attachments.put(90260, new int[][] { { 116, 2 }, { 113, 2 }, { 162, 2 }, { 175, 2 }, { 36, 2 } }); // Body
        attachments.put(90261, new int[][] { { 135, 2 }, { 194, 2 }, { 219, 2 }, { 109, 2 }, { 84, 2 }, { 25, 2 } }); // Boots
        attachments.put(90263, new int[][] { { 95, 2 }, { 96, 2 }, { 37, 2 }, { 40, 2 } }); // Gloves
        attachments.put(90265, new int[][] { { 1044, 2 }, { 348, 2 }, { 366, 2 }, { 1062, 2 } }); // Hammers
        attachments.put(90269, new int[][] { { 173, 2 }, { 70, 2 }, { 160, 2 }, { 57, 2 }, { 71, 2 }, { 174, 2 } }); // Platelegs

        // Legolas Set
        attachments.put(90271, new int[][] { { 47, 3 }, { 53, 3 }, { 125, 3 }, { 119, 3 } }); // Bow
        attachments.put(90273, new int[][] { { 77, 3 }, { 78, 3 }, { 95, 3 }, { 21, 3 }, { 22, 3 }, { 37, 3 } }); // Gloves
        attachments.put(90275, new int[][] { { 1021, 3 }, { 1034, 3 }, { 1042, 3 }, { 1045, 3 } }); // Platebody
        attachments.put(90276, new int[][] { { 1, 3 }, { 57, 3 }, { 110, 3 }, { 54, 3 } }); // Boots
        attachments.put(90278, new int[][] { { 57, 3 }, { 58, 3 }, { 43, 3 } }); // Helmet
        attachments.put(90280, new int[][] { { 96, 3 }, { 86, 3 }, { 51, 3 } }); // Platelegs

        // Owner Set
        attachments.put(90194, new int[][] { { 223, 4 }, { 224, 4 }, { 231, 4 }, { 230, 4 }, { 236, 4 }, { 233, 4 }, { 21, 4 }, { 23, 4 }, { 7, 4 }, { 6, 4 }, { 5, 4 } }); // Cape
        attachments.put(90192, new int[][] { { 510, 4 }, { 506, 4 }, { 505, 4 }, { 49, 4 }, { 50, 4 }, { 52, 4 }, { 23, 4 }, { 19, 4 }, { 24, 4 }, { 32, 4 }, { 33, 4 }, { 39, 4 }, { 311, 4 }, { 309, 4 }, { 307, 4 }, { 18, 4 }, { 86, 4 }, { 87, 4 }, { 88, 4 }, { 89, 4 } }); // Bow
        attachments.put(90206, new int[][] { { 104, 4 }, { 190, 4 }, { 94, 4 }, { 61, 4 }, { 66, 4 }, { 243, 4 }, { 196, 4 }, { 231, 4 }, { 236, 4 }, { 227, 4 }, { 164, 4 }, { 165, 4 }, { 168, 4 }, { 175, 4 }, { 169, 4 }, { 114, 4 }, { 56, 4 } }); // Shield
        attachments.put(90210, new int[][] { { 227, 4 }, { 200, 4 }, { 363, 4 }, { 336, 4 }, { 497, 4 }, { 555, 4 }, { 558, 4 }, { 381, 4 }, { 375, 4 } }); // Staff
        attachments.put(90212, new int[][] { { 100, 4 }, { 101, 4 }, { 102, 4 }, { 103, 4 }, { 104, 4 }, { 105, 4 }, { 106, 4 }, { 107, 4 }, { 108, 4 }, { 45, 4 }, { 270, 4 }, { 271, 4 } }); // Sword


        //Master Attack Cape
        attachments.put(90294, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Attack Cape
        //Master Defence Cape
        attachments.put(90297, new int[][] { { 151, 0 }, { 149, 0 }, { 148, 0 }, { 147, 0 }, { 146, 0 }, { 170, 0 }, { 169, 0 }, { 168, 0 }, { 167, 0 }, { 218, 0 }, { 121, 0 } }); // Master Defence Cape
        //Master Strength Cape
        attachments.put(90299, new int[][] { { 151, 6 }, { 149, 6 }, { 148, 6 }, { 147, 6 }, { 146, 6 }, { 170, 6 }, { 169, 6 }, { 168, 6 }, { 167, 6 }, { 218, 6 }, { 121, 6 } }); // Master Strength Cape
        //Master Hitpoints Cape
        attachments.put(90301, new int[][] { { 151, 6 }, { 149, 6 }, { 148, 6 }, { 147, 6 }, { 146, 6 }, { 170, 6 }, { 169, 6 }, { 168, 6 }, { 167, 6 }, { 218, 6 }, { 121, 6 } }); // Master Hitpoints Cape
        //Master Ranged Cape
        attachments.put(90303, new int[][] { { 151, 7 }, { 149, 7 }, { 148, 7 }, { 147, 7 }, { 146, 7 }, { 170, 7 }, { 169, 7 }, { 168, 7 }, { 167, 7 }, { 218, 7 }, { 121, 7 } }); // Master Ranged Cape
        //Master Prayer Cape
        attachments.put(90305, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Prayer Cape
        //Master Magic Cape
        attachments.put(90307, new int[][] { { 151, 8 }, { 149, 8 }, { 148, 8 }, { 147, 8 }, { 146, 8 }, { 170, 8 }, { 169, 8 }, { 168, 8 }, { 167, 8 }, { 218, 8 }, { 121, 8 } }); // Master Magic Cape
        //Master Runecrafting Cape
        attachments.put(90340, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Runecrafting Cape
        //Master Herblore Cape
        attachments.put(90330, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Herblore Cape
        //Master Firemaking Cape
        attachments.put(90317, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Firemaking Cape
        //Master Crafting Cape
        attachments.put(90324, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Crafting Cape
        //Master Smithing Cape
        attachments.put(90326, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Smithing Cape
        //Master Fletching Cape
        attachments.put(90313, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Fletching Cape
        //Master Fishing Cape
        attachments.put(90315, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Fishing Cape
        //Master Construction Cape
        attachments.put(90342, new int[][] { { 151, 5 }, { 149, 5 }, { 148, 5 }, { 147, 5 }, { 146, 5 }, { 170, 5 }, { 169, 5 }, { 168, 5 }, { 167, 5 }, { 218, 5 }, { 121, 5 } }); // Master Construction Cape
        //Master Agility Cape
        attachments.put(90332, new int[][] { { 151, 6 }, { 149, 6 }, { 148, 6 }, { 147, 6 }, { 146, 6 }, { 170, 6 }, { 169, 6 }, { 168, 6 }, { 167, 6 }, { 218, 6 }, { 121, 6 } }); // Master Agility Cape
        //Master Cooking Cape
        attachments.put(90309, new int[][] { { 151, 6 }, { 149, 6 }, { 148, 6 }, { 147, 6 }, { 146, 6 }, { 170, 6 }, { 169, 6 }, { 168, 6 }, { 167, 6 }, { 218, 6 }, { 121, 6 } }); // Master Cooking Cape
        //Master Slayer Cape
        attachments.put(90336, new int[][] { { 151, 6 }, { 149, 6 }, { 148, 6 }, { 147, 6 }, { 146, 6 }, { 170, 6 }, { 169, 6 }, { 168, 6 }, { 167, 6 }, { 218, 6 }, { 121, 6 } }); // Master Slayer Cape
        //Master Mining Cape
        attachments.put(90328, new int[][] { { 151, 9 }, { 149, 9 }, { 148, 9 }, { 147, 9 }, { 146, 9 }, { 170, 9 }, { 169, 9 }, { 168, 9 }, { 167, 9 }, { 218, 9 }, { 121, 9 } }); // Master Mining Cape
        //Master Hunter Cape
        attachments.put(90344, new int[][] { { 151, 10 }, { 149, 10 }, { 148, 10 }, { 147, 10 }, { 146, 10 }, { 170, 10 }, { 169, 10 }, { 168, 10 }, { 167, 10 }, { 218, 10 }, { 121, 10 } }); // Master Hunter Cape
        //Master Farming Cape
        attachments.put(90338, new int[][] { { 151, 11 }, { 149, 11 }, { 148, 11 }, { 147, 11 }, { 146, 11 }, { 170, 11 }, { 169, 11 }, { 168, 11 }, { 167, 11 }, { 218, 11 }, { 121, 11 } }); // Master Farming Cape
        //Master Thieving Cape
        attachments.put(90334, new int[][] { { 151, 12 }, { 149, 12 }, { 148, 12 }, { 147, 12 }, { 146, 12 }, { 170, 12 }, { 169, 12 }, { 168, 12 }, { 167, 12 }, { 218, 12 }, { 121, 12 } }); // Master Thieving Cape
        //Master Woodcutting Cape
        attachments.put(90311, new int[][] { { 151, 13 }, { 149, 13 }, { 148, 13 }, { 147, 13 }, { 146, 13 }, { 170, 13 }, { 169, 13 }, { 168, 13 }, { 167, 13 }, { 218, 13 }, { 121, 13 } }); // Master Woodcutting Cape

        //Reaper Helmet
        attachments.put(3188, new int[][] { { 57, 14 }, { 45, 14 }, { 129, 14 } }); // Reaper Helmet
        //Reaper Body
        attachments.put(43500, new int[][] { { 524, 14 }, { 214, 14 }, { 510, 14 }, { 199, 14 }, { 508, 14 }, { 189, 14 }, { 591, 14 }, { 412, 14 } }); // Reaper Body
        //Reaper Bottoms
        attachments.put(44762, new int[][] { { 93, 14 }, { 94, 14 }, { 95, 14 }, { 150, 14 }, { 143, 14 }, { 148, 14 }, { 154, 14 }, { 114, 14 }, { 158, 14 }, { 341, 14 }, { 342, 14 }, { 343, 14 }, { 196, 14 }, { 194, 14 }, { 362, 14 }, { 363, 14 } }); // Reaper Bottoms

        //Malevolence Shield
        attachments.put(1052, new int[][] { { 53, 15 }, { 52, 15 }, { 55, 15 }, { 80, 15 }, { 75, 15 }, { 73, 15 }, { 206, 15 }, { 207, 15 }, { 208, 15 }, { 231, 15 }, { 232, 15 }, { 252, 15 }, { 216, 0 }, { 69, 15 }, { 64, 0 }, { 135, 15 }, { 11, 0 }, { 9, 15 }, { 88, 0 } }); // Malevolence Shield
        //Malevolence Boots
        attachments.put(32797, new int[][] { { 20, 15 }, { 37, 15 }, { 95, 15 }, { 78, 15 } }); // Malevolence Boots
        //Malevolence Crossbow
        attachments.put(32793, new int[][] { { 49, 0 }, { 478, 0 }, { 146, 0 }, { 130, 0 } }); // Malevolence Crossbow
        //Malevolence Platebody
        attachments.put(97856, new int[][] { { 2004, 15 }, { 2014, 15 }, { 1469, 15 }, { 1448, 15 } }); // Malevolence Platebody
        //Malevolence Helmet
        attachments.put(32678, new int[][] { { 162, 15 }, { 163, 15 } }); // Malevolence Helmet

        //Noctorious Staff
        attachments.put(29250, new int[][] { { 20, 16 }, { 36, 16 }, { 30, 16 }, { 60, 16 }, { 261, 16 }, { 262, 16 }, { 37, 16 }, { 22, 16 }, { 44, 16 }, { 56, 16 } }); // Noctorious Staff

        //Space Intruder
        attachments.put(23994, new int[][] { { 185, 17 }, { 186, 17 }, { 187, 17 }, { 191, 17 }, { 192, 17 }, { 0, 17 }, { 795, 17 } }); // Space Intruder

        //Dragonmaster Cape
        attachments.put(9347, new int[][] { { 88, 17 }, { 76, 17 }, { 66, 17 }, { 152, 17 }, { 160, 17 }, { 37, 17 }, { 287, 12 }, { 288, 12 }, { 305, 12 }, { 302, 12 }, { 303, 12 }, { 293, 12 }, { 289, 12 }, { 290, 12 }, { 298, 12 }, { 299, 12 }, { 297, 12 } }); // Dragonmaster Cape

        //Grand staff of the enlightened
        attachments.put(91453, new int[][] { { 0, 18 }, { 1, 18 }, { 2, 18 }, { 3, 18 }, { 4, 18 }, { 5, 18 }, { 550, 18 }, { 551, 18 }, { 552, 18 }, { 553, 18 }, { 554, 18 }, { 555, 18 } }); // Grand staff of the enlightened

	}
}