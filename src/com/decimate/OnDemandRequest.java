package com.decimate;


public final class OnDemandRequest extends QueueNode {

    int dataType;
    byte buffer[];
    int id;
    boolean isNotExtraFile;
    int loopCycle;
    public OnDemandRequest() {
        isNotExtraFile = true;
    }

    @Override
    public String toString() {
        return "OnDemandData [dataType=" + dataType + ", ID=" + id
                + ", incomplete=" + isNotExtraFile + ", loopCycle=" + loopCycle
                + "]";
    }

}
