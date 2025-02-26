package com.decimate;

public class Animable extends QueueNode {

    public int modelHeight;
    VertexNormal vertexNormals[];

    public Animable() {
        modelHeight = 1000;
    }

    public void renderAtPoint(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int newuid, int bufferOffset) {
        Model model = getRotatedModel();
        if (model != null) {
            modelHeight = model.modelHeight;

            if(this instanceof NPC) {
                NPC npc = (NPC) this;
                if(npc.desc != null && NPC.isImportantNpc(npc.desc.id)) {
                    System.out.println("Adding particles");
                    model.addParticleDefinition(7, 40);
                }
            }

            model.renderAtPoint(i, j, k, l, i1, j1, k1, l1, i2, newuid, bufferOffset);
        }
    }

    public Model getRotatedModel() {
        return null;
    }

    public Model getRotatedModelHD() {
        return null;
    }
}