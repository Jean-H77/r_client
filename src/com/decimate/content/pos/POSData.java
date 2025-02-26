package com.decimate.content.pos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class POSData {

    public static List<ShopItem> currentData = new ArrayList<>();

    public static class ShopItem {
        @Getter
        private int id;

        @Getter
        @Setter
        private int amount;

        @Getter
        @Setter
        private int maxAmount;

        @Getter
        private long price;

        @Getter
        private String owner;

        @Getter
        private long timeStamp;


        public ShopItem(String owner, int id, int amount, long price, int maxAmount, long timeStamp) {
            this.id = id;
            this.amount = amount;
            this.maxAmount = maxAmount;
            this.owner = owner;
            this.price = price;
            this.timeStamp = timeStamp;
        }
    }

}
