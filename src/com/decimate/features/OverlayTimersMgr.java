package com.decimate.features;

import com.decimate.Client;
import com.decimate.DrawingArea;
import com.decimate.ItemDef;
import com.decimate.Sprite;
import utils.Misc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class OverlayTimersMgr {

    public List<OverlayTimers> overlayTimers = new LinkedList<>();

    public void clearAllTimers() {
        overlayTimers.clear();
    }

    public void draw() {
        int xOffset = 0;
        for (int index = 0; index < overlayTimers.size(); index++) {
            OverlayTimers timer = overlayTimers.get(index);
            if (timer.getIdentifier() == 0) {
                int x = Client.clientSize == 0 ? 463 : Client.clientWidth - 535;
                int y = Client.clientSize == 0 ? 303 : Client.clientHeight - 197;
                DrawingArea.drawAlphaBox(x + xOffset + 10, y - 15, 40, 45, 0x686868, 100);
                if (timer.isItem()) {
                    Sprite sprite = ItemDef.getSprite(timer.getSpriteId(), 1, 0);
                    if (sprite != null) {
                        sprite.drawSprite(x + xOffset + 15, y - 10);
                    }
                } else {
                    Objects.requireNonNull(Client.spriteLoader.lookup(timer.getSpriteId())).drawSprite(x + xOffset + 15, y - 10);
                }
                Client.instance.newSmallFont.drawBasicString(Integer.toString(timer.getValue()), x + 19 + xOffset, y + 28, 0xffffff, -1);
            } else if (timer.getIdentifier() == 1) {
                long currentTime = System.currentTimeMillis();
                int duration = (int) ((timer.getTimeToEnd() - timer.getTimeStarted()) / 1000);
                if (overlayTimers.removeIf(x -> (x.getIdentifier() == timer.getIdentifier() && currentTime > (x.getTimeToEnd())))) {
                    continue;
                }

                int seconds = (int) (duration - ((currentTime - timer.getTimeStarted()) / 1000));
                int x = Client.clientSize == 0 ? 463 : Client.clientWidth - 535;
                int y = Client.clientSize == 0 ? 303 : Client.clientHeight - 197;
                DrawingArea.drawAlphaBox(x + xOffset + 10, y - 15, 40, 45, 0x686868, 100);
                if (timer.isItem()) {
                    Sprite sprite = ItemDef.getSprite(timer.getSpriteId(), 1, 0);
                    if (sprite != null) {
                        sprite.drawSprite(x + xOffset + 15, y - 10);
                    }
                } else {
                    Objects.requireNonNull(Client.spriteLoader.lookup(timer.getSpriteId())).drawSprite(x + xOffset + 15, y - 10);
                }
                Client.instance.newSmallFont.drawBasicString(Misc.getTimeLeft(seconds), x + 19 + xOffset, y + 28, 0xffffff, -1);
            }
            xOffset -= 39;
        }
    }

    public void addNewOverlay(int identifier, int spriteId, int secondsDuration, int value, boolean isItem) {
        OverlayTimers overlayTimer = new OverlayTimers(identifier, spriteId, System.currentTimeMillis(), System.currentTimeMillis() + (secondsDuration * 1000L), value, isItem);
        for (int index = 0; index < overlayTimers.size(); index++) {
            OverlayTimers data = overlayTimers.get(index);
            if (data == null) {
                 continue;
            }

            if (data.getSpriteId() == spriteId) {
                overlayTimers.set(index, overlayTimer);

                // remove old one if value has changed
                if (data.getIdentifier() == 0 && (data.getValue() <= 0 || value == data.getValue() || value != data.getValue())) {
                    remove(identifier, spriteId);
                }
            }
        }

        this.overlayTimers.add(new OverlayTimers(identifier, spriteId, System.currentTimeMillis(), System.currentTimeMillis() + (secondsDuration * 1000L), value, isItem));
    }

    public void remove(int identifier, int spriteIdToRemove) {
        OverlayTimers timerToRemove = null;
        for (OverlayTimers data : overlayTimers) {
            if (data == null) {
                continue;
            }

            if (data.getIdentifier() == identifier && data.getSpriteId() == spriteIdToRemove) {
                timerToRemove = data;
                break;
            }
        }

        overlayTimers.remove(timerToRemove);
    }
}
