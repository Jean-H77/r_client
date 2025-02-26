package com.decimate.dtos;

import com.decimate.Stream;
import lombok.Getter;

public class ItemDto {
    @Getter
    private final int id;
    @Getter
    private final int amount;
    @Getter
    private final int customValue;

    public ItemDto(Stream stream) {
        int itemAmt = stream.readUnsignedByte();
        if (itemAmt == 255) {
            itemAmt = stream.method440();
        }

        this.id = stream.readWordBigEndian();
        this.amount = itemAmt;
        this.customValue = stream.readInt();
    }

    public ItemDto() {
        this.id = 0;
        this.amount = 0;
        this.customValue = 0;
    }
}
