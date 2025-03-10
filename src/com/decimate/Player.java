package com.decimate;

@SuppressWarnings("all")
public final class Player extends Entity {

    static MemCache modelCache = new MemCache(260);
    public final int[] equipment;
    final int[] anIntArray1700;
    public int frontLight = 68;
    public int backLight = 820;
    public int rightLight = 0;
    public int middleLight = -1; // Cannot be 0
    public int leftLight = 0;
    public int[][] modifiedColors = new int[13][];
    public long constitution, maxConstitution;
    public int playerRights;
    public String playerTitle;
    public NPCDef desc;
    public int team;
    public int myGender;
    public String name;
    public int combatLevel;
    public int headIcon;
    public int bountyHunterIcon;
    public int hintIcon;
    public boolean skulled;
    public int summonLevel;
    public int SummonLevel;
    public int startTimeTransform;
    boolean aBoolean1699;
    int transformedTimer;
    int z;
    boolean visible;
    int resizeX;
    int resizeZ;
    int resizeY;
    Model tranformIntoModel;
    int extendedXMin;
    int extendedYMin;
    int extendedXMax;
    int extendedYMax;
    public NPC shadowPet = null;
    private long aLong1697;
    private int anInt1715;
    private long aLong1718;
    Player() {
        aLong1697 = -1L;
        aBoolean1699 = false;
        anIntArray1700 = new int[5];
        visible = false;
        anInt1715 = 9;
        equipment = new int[13];
    }

    public Model getRotatedModel() {
        if (!visible)
            return null;
        Model model = method452();
        if (model == null)
            return null;
        super.height = model.modelHeight;
        model.rendersWithinOneTile = true;
        if (aBoolean1699)
            return model;
        if (super.anInt1520 != -1 && super.currentAnim != -1) {
            SpotAnim spotAnim = SpotAnim.cache[super.anInt1520];
            Model model_2 = spotAnim.getModel();

            if (model_2 != null) {
                Model model_3 = new Model(true, FrameReader.isFrameNull(super.currentAnim), false, model_2);
                model_3.translate(0, -super.graphicHeight, 0);
                model_3.createBones();
                model_3.scaleT(132, 132, 132);
                model_3.applyTransform(spotAnim.animation.frameIDs[super.currentAnim]);
                model_3.triangleSkin = null;
                model_3.vertexSkin = null;
                if (spotAnim.sizeXY != 128 || spotAnim.sizeZ != 128)
                    model_3.scaleT(spotAnim.sizeXY, spotAnim.sizeXY, spotAnim.sizeZ);
                model_3.light(64 + spotAnim.shadow, 850 + spotAnim.lightness, -30, -50, -30, true);
                Model aclass30_sub2_sub4_sub6_1s[] = {model, model_3};
                model = new Model(aclass30_sub2_sub4_sub6_1s);
            }
        }
        if (tranformIntoModel != null) {
            if (Client.loopCycle >= transformedTimer)
                tranformIntoModel = null;
            if (Client.loopCycle >= startTimeTransform && Client.loopCycle < transformedTimer) {
                Model model_1 = tranformIntoModel;
                model_1.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
                if (super.turnDirection == 512) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                } else if (super.turnDirection == 1024) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                } else if (super.turnDirection == 1536)
                    model_1.rotateBy90();
                Model models[] = {model, model_1};
                model = new Model(models);
                if (super.turnDirection == 512)
                    model_1.rotateBy90();
                else if (super.turnDirection == 1024) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                } else if (super.turnDirection == 1536) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                }
                model_1.translate(super.x - resizeX, z - resizeZ, super.y - resizeY);
            }
        }
        model.rendersWithinOneTile = true;
        return model;
    }

    public void updatePlayerAppearance(Stream stream) {
        stream.currentOffset = 0;
        myGender = stream.readUnsignedByte();
        headIcon = stream.readUnsignedByte();
        bountyHunterIcon = stream.readUnsignedByte();
        skulled = stream.readUnsignedWord() == 1;
        if (bountyHunterIcon > 4 && bountyHunterIcon != 255)
            bountyHunterIcon = 4;
        desc = null;
        team = 0;
        for (int partId = 0; partId < 13; partId++) {
            int firstByte = stream.readUnsignedByte();
            if (firstByte == 0) {
                equipment[partId] = 0;
                continue;
            }
            int secondByte = stream.readUnsignedByte();
            equipment[partId] = (firstByte << 8) + secondByte;
            if (partId == 0 && equipment[0] == 65535) {
                desc = NPCDef.forID(stream.readUnsignedWord());
                break;
            }
            if (partId == 1) {
                int length = stream.readUnsignedByte();
                if (length > 0) {
                    int[] colors = new int[length];
                    for (int i = 0; i < length; i++) {
                        colors[i] = stream.readInt();
                    }
                    modifiedColors[partId] = colors;
                } else {
                    modifiedColors[partId] = null;
                }
              //  modelCache.clear();
                if (this == Client.myPlayer) {
                    Client.getClient().updateMaxCapeColors(modifiedColors[partId]);
                }
            }
            if (partId == 8)
                Client.myHeadAndJaw[0] = equipment[partId] - 256;
            if (partId == 11)
                Client.myHeadAndJaw[1] = equipment[partId] - 256;
            if (equipment[partId] >= 512 && equipment[partId] - 512 < ItemDef.totalItems) {
                int l1 = ItemDef.forID(equipment[partId] - 512).team;
                if (l1 != 0)
                    team = l1;
            }
        }

        for (int l = 0; l < 5; l++) {
            int j1 = stream.readUnsignedByte();
            if (j1 < 0 || j1 >= Client.anIntArrayArray1003[l].length)
                j1 = 0;
            anIntArray1700[l] = j1;
        }

        super.standAnim = stream.readUnsignedWord();
        if (super.standAnim == 65535)
            super.standAnim = -1;
        super.anInt1512 = stream.readUnsignedWord();
        if (super.anInt1512 == 65535)
            super.anInt1512 = -1;
        super.anInt1554 = stream.readUnsignedWord();
        if (super.anInt1554 == 65535)
            super.anInt1554 = -1;
        super.anInt1555 = stream.readUnsignedWord();
        if (super.anInt1555 == 65535)
            super.anInt1555 = -1;
        super.anInt1556 = stream.readUnsignedWord();
        if (super.anInt1556 == 65535)
            super.anInt1556 = -1;
        super.anInt1557 = stream.readUnsignedWord();
        if (super.anInt1557 == 65535)
            super.anInt1557 = -1;
        super.runAnimation = stream.readUnsignedWord();
        if (super.runAnimation == 65535)
            super.runAnimation = -1;

        name = TextClass.fixName(TextClass.nameForLong(stream.readQWord()));
        combatLevel = stream.readUnsignedByte();
        playerRights = stream.readUnsignedWord();
        playerTitle = stream.readString();

        visible = true;
        aLong1718 = 0L;

        if (desc != null) {
            combatLevel = desc.combatLevel;
            super.standAnim = desc.standAnim;
            super.anInt1512 = desc.standAnim;
            super.anInt1554 = desc.walkAnim;
            super.anInt1555 = desc.standAnim;
            super.anInt1556 = desc.walkAnim;
            super.anInt1557 = desc.walkAnim;
            super.runAnimation = desc.walkAnim;
        }


        for (int k1 = 0; k1 < 13; k1++) {
            aLong1718 <<= 4;
            if (equipment[k1] >= 256)
                aLong1718 += equipment[k1] - 256;
        }

        if (equipment[0] >= 256)
            aLong1718 += equipment[0] - 256 >> 4;
        if (equipment[1] >= 256)
            aLong1718 += equipment[1] - 256 >> 8;
        for (int i2 = 0; i2 < 5; i2++) {
            aLong1718 <<= 3;
            aLong1718 += anIntArray1700[i2];
        }

        aLong1718 <<= 1;
        aLong1718 += myGender;
    }

    public Model method452() {
		/*if (desc != null) {
			int j = -1;
			if (super.anim >= 0 && super.animationDelay == 0) {
				j = Animation.anims[super.anim].frameIDs[super.currentAnimFrame];
			} else if (super.anInt1517 >= 0) {
				j = Animation.anims[super.anInt1517].frameIDs[super.currentForcedAnimFrame];
			}
			Model model = desc.getAnimatedModel(-1, j, null);
			return model;
		}
		 */
        if (desc != null) {
            int currentFrame = -1;
            int nextFrame = -1;
            int cycle1 = 0;
            int cycle2 = 0;
            if (super.anim >= 0 && super.animationDelay == 0) {
                Animation animation = Animation.anims[super.anim];
                currentFrame = animation.frameIDs[super.currentAnimFrame];
                nextFrame = animation.frameIDs[super.nextAnimationFrame];
                cycle1 = animation.delays[super.currentAnimFrame];
                cycle2 = super.anInt1528;
            } else if (super.anInt1517 >= 0) {
                Animation animation = Animation.anims[super.anInt1517];
                currentFrame = animation.frameIDs[super.currentForcedAnimFrame];
                nextFrame = animation.frameIDs[super.nextIdleAnimationFrame];
                cycle1 = animation.delays[super.currentForcedAnimFrame];
                cycle2 = super.anInt1519;
            }
            Model model = desc.method164(this, -1, currentFrame, null, nextFrame, cycle1, cycle2);
            return model;
        }


        long l = aLong1718;
        int currentFrame = -1;
        int nextFrame = -1;
        int cycle1 = 0;
        int cycle2 = 0;
        int i1 = -1;
        int j1 = -1;
        int k1 = -1;
        if (super.anim >= 0 && super.animationDelay == 0) {
            Animation animation = Animation.anims[super.anim];
            currentFrame = animation.frameIDs[super.currentAnimFrame];
            if (super.nextAnimationFrame < animation.frameIDs.length)
                nextFrame = animation.frameIDs[super.nextAnimationFrame];
            cycle1 = animation.delays[super.currentAnimFrame];
            cycle2 = super.anInt1528;
            if (super.anInt1517 >= 0 && super.anInt1517 != super.standAnim)
                i1 = Animation.anims[super.anInt1517].frameIDs[super.currentForcedAnimFrame];
            if (animation.leftHandItem >= 0) {
                j1 = animation.leftHandItem;
                l += j1 - equipment[5] << 40;
            }
            if (animation.rightHandItem >= 0) {
                k1 = animation.rightHandItem;
                l += k1 - equipment[3] << 48;
            }
        } else if (super.anInt1517 >= 0) {
            Animation animation = Animation.anims[super.anInt1517];
            currentFrame = animation.frameIDs[super.currentForcedAnimFrame];
            nextFrame = animation.frameIDs[super.nextIdleAnimationFrame];
            cycle1 = animation.delays[super.currentForcedAnimFrame];
            cycle2 = super.anInt1519;
        }


        Model model_1 = (Model) modelCache.get(l);
        if (model_1 == null) {
            boolean fetchModels = false;
            for (int bodyPartId = 0; bodyPartId < 13; bodyPartId++) {
                int partId = equipment[bodyPartId];
                if (k1 >= 0 && bodyPartId == 3)
                    partId = k1;
                if (j1 >= 0 && bodyPartId == 5)
                    partId = j1;
                if ((partId - 256) != 846) {

                    if (partId >= 256 && partId < 512 && !IDK.cache[partId - 256].bodyModelIsFetched())
                        fetchModels = true;
                    if (partId >= 512 && !ItemDef.forID(partId - 512).equipModelFetched(myGender))
                        fetchModels = true;

                }
            }

            if (fetchModels) {
                if (aLong1697 != -1L)
                    model_1 = (Model) modelCache.get(aLong1697);
                if (model_1 == null)
                    return null;
            }
        }
        if (model_1 == null) {
            Model bodyPartModels[] = new Model[13];
            int j2 = 0;
            for (int currentPart = 0; currentPart < 13; currentPart++) {
                int i3 = equipment[currentPart];
                if (k1 >= 0 && currentPart == 3)
                    i3 = k1;
                if (j1 >= 0 && currentPart == 5)
                    i3 = j1;
                if (i3 >= 256 && i3 < 512) {
                    Model model_3 = null;
                    if ((i3 - 256) < IDK.cache.length)
                        model_3 = IDK.cache[i3 - 256].fetchBodyModel();
                    if (model_3 != null)
                        bodyPartModels[j2++] = model_3;
                }
                if (i3 >= 512) {
                    ItemDef def = ItemDef.forID(i3 - 512);
                    Model model_4 = def.getEquipModel(myGender);
					/*if(def.id == 14019) {
						if(name.equalsIgnoreCase("Apache Ah64")) {
							System.out.println("changing color");
							int[] originalModelColor = new int[4];
							originalModelColor[0] = 926;//cape
							originalModelColor[1] = 350770;//cape
							originalModelColor[2] = 926;//outline
							originalModelColor[3] = 130770;//cape
							for (int i11 = 0; i11 < def.editedModelColor.length; i11++)
								model_4.recolour(def.editedModelColor[i11], originalModelColor[i11]);
						} else if(name.equalsIgnoreCase("Apache Ah66")) {
							System.out.println("changing color2");
							int[] originalModelColor = new int[4];
							originalModelColor[0] = 926;//cape
							originalModelColor[1] = 302770;//cape
							originalModelColor[2] = 926;//outline
							originalModelColor[3] = 302770;//cape
							for (int i11 = 0; i11 < def.editedModelColor.length; i11++)
								model_4.recolour(def.editedModelColor[i11], originalModelColor[i11]);
						}
					}*/
                    //System.out.println(name+" - "+currentPart+": "+Arrays.toString(modifiedColors[currentPart]));
                    if (modifiedColors[currentPart] != null) {
                        for (int i11 = 0; i11 < def.editedModelColor.length; i11++)
                            model_4.recolour(def.editedModelColor[i11], modifiedColors[currentPart][i11]);
                    }
                    if (model_4 != null)
                        bodyPartModels[j2++] = model_4;
                }
            }
            model_1 = new Model(j2, bodyPartModels);
            for (int j3 = 0; j3 < 5; j3++)
                if (anIntArray1700[j3] != 0) {
                    model_1.recolour(Client.anIntArrayArray1003[j3][0], Client.anIntArrayArray1003[j3][anIntArray1700[j3]]);
                    if (j3 == 1)
                        model_1.recolour(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j3]]);
                }

            model_1.createBones();
            model_1.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
            modelCache.put(model_1, l);
            aLong1697 = l;
        }
        if (aBoolean1699)
            return model_1;
        Model model_2 = Model.entityModelDesc;
        model_2.method464(model_1, FrameReader.isFrameNull(currentFrame) & FrameReader.isFrameNull(i1));
        if (currentFrame != -1 && i1 != -1)
            model_2.method471(Animation.anims[super.anim].animationFlowControl, i1, currentFrame);
        else if (currentFrame != -1 && nextFrame != -1)
            model_2.applyTransform(currentFrame, nextFrame, cycle1, cycle2);
        else
            model_2.applyTransform(currentFrame);
        model_2.calculateDiagonals();
        model_2.triangleSkin = null;
        model_2.vertexSkin = null;
        return model_2;
    }

    public boolean isVisible() {
        return visible;
    }

    public Model getPlayerModel() {
        if (!visible)
            return null;
        if (desc != null)
            return desc.getHeadModel();
        boolean flag = false;
        for (int i = 0; i < 13; i++) {
            int j = equipment[i];
            try {
                if (j >= 256 && j < 512 && !IDK.cache[j - 256].headModelFetched())
                    flag = true;
                if (j >= 512 && !ItemDef.forID(j - 512).dialogueModelFetched(myGender)) {
                    flag = true;
                }
            } catch (Exception e) {
                flag = true;
            }
        }

        if (flag)
            return null;
        Model models[] = new Model[13];
        int k = 0;
        for (int l = 0; l < 13; l++) {
            int i1 = equipment[l];
            if (i1 >= 256 && i1 < 512) {
                Model model_1 = IDK.cache[i1 - 256].fetchHeadModel();
                if (model_1 != null)
                    models[k++] = model_1;
            }
            if (i1 >= 512) {
                Model model_2 = ItemDef.forID(i1 - 512).getDialogueModel(myGender);
                if (model_2 != null)
                    models[k++] = model_2;
            }
        }

        Model model = new Model(k, models);
        for (int j1 = 0; j1 < 5; j1++)
            if (anIntArray1700[j1] != 0) {
                model.recolour(Client.anIntArrayArray1003[j1][0], Client.anIntArrayArray1003[j1][anIntArray1700[j1]]);
                if (j1 == 1)
                    model.recolour(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j1]]);
            }

        return model;
    }

}

