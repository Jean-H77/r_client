package com.decimate;


public final class NPC extends Entity {

    public Player cachedShadowPetOwner;
    public NPCDef desc;

    NPC() {
    }

    private Model getAnimatedModel() {

        if (super.anim >= 0 && super.animationDelay == 0) {
            Animation animation = Animation.anims[super.anim];
            int currentFrame = animation.frameIDs[super.currentAnimFrame];
            int nextFrame = animation.frameIDs[super.nextAnimationFrame];
            int cycle1 = animation.delays[super.currentAnimFrame];
            int cycle2 = super.anInt1528;
            int i1 = -1;
            if (super.anInt1517 >= 0 && super.anInt1517 != super.standAnim)
                i1 = Animation.anims[super.anInt1517].frameIDs[super.currentForcedAnimFrame];
            return desc.method164(this, i1, currentFrame, Animation.anims[super.anim].animationFlowControl, nextFrame, cycle1, cycle2);
        }

        int currentFrame = -1;
        int nextFrame = -1;
        int cycle1 = 0;
        int cycle2 = 0;
        if (super.anInt1517 >= 0) {
            Animation animation = Animation.anims[super.anInt1517];
            currentFrame = animation.frameIDs[super.currentForcedAnimFrame];
            nextFrame = animation.frameIDs[super.nextIdleAnimationFrame];
            cycle1 = animation.delays[super.currentForcedAnimFrame];
            cycle2 = super.anInt1519;
        }
        return desc.method164(this, -1, currentFrame, null, nextFrame, cycle1, cycle2);
    }

    public Model getRotatedModel() {
        if (desc == null)
            return null;
        Model model = getAnimatedModel();

        if (desc.type == 1313) {
            final Player owner = cachedShadowPetOwner;
            if (owner != null) {
                Model playerModel = owner.getRotatedModel();
                if (playerModel != null) {
                    playerModel.scale2(90, 90, 90);
                    playerModel.setGlobalAlpha(135);
                    model = playerModel;
                }
            }
        }

        if (model == null)
            return null;
        super.height = model.modelHeight;
        if (super.anInt1520 != -1 && super.currentAnim != -1) {
            SpotAnim spotAnim = SpotAnim.cache[super.anInt1520];
            Model model_1 = spotAnim.getModel();
            if (model_1 != null) {
                int j = spotAnim.animation.frameIDs[super.currentAnim];
                Model model_2 = new Model(true, FrameReader.isFrameNull(j), false, model_1);
                model_2.translate(0, -super.graphicHeight, 0);
                model_2.createBones();
                model_2.applyTransform(j);
                model_2.triangleSkin = null;
                model_2.vertexSkin = null;
                if (spotAnim.sizeXY != 128 || spotAnim.sizeZ != 128)
                    model_2.scaleT(spotAnim.sizeXY, spotAnim.sizeXY, spotAnim.sizeZ);
                model_2.light(64 + spotAnim.shadow, 850 + spotAnim.lightness, -30, -50, -30, true);
                Model aModel[] = {
                        model, model_2
                };
                model = new Model(aModel);
            }
        }
        if (desc.squaresNeeded == 1)
            model.rendersWithinOneTile = true;
        return model;
    }

    public boolean isVisible() {
        return desc != null;
    }

    public static int[] importantNpcs = new int[] {506, 534, 289, 564, 479, 478, 472, 477, 476, 475, 470, 469, 471, 468, 467, 466, 465, 464, 463, 462};

    public static boolean isImportantNpc(int id) {
        for(int i : importantNpcs) {
            if(i == id)
                return true;
        }
        return false;
    }
}
