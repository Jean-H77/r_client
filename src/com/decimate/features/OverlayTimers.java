package com.decimate.features;

import lombok.Getter;

public class OverlayTimers {

    @Getter private final int identifier;
    @Getter private final int spriteId;
    @Getter private final long timeStarted;
    @Getter private final long timeToEnd;
    @Getter private final int value;
    @Getter private final boolean isItem;

    public OverlayTimers(int identifier, int spriteId, long timeStarted, long timeToEnd, int value, boolean isItem) {
        this.identifier = identifier;
        this.spriteId = spriteId;
        this.timeStarted = timeStarted;
        this.timeToEnd = timeToEnd;
        this.value = value;
        this.isItem = isItem;
    }
}
