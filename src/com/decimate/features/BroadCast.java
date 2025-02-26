package com.decimate.features;

import com.decimate.Client;
import lombok.Getter;

import java.util.LinkedList;

public class BroadCast {
    @Getter
    private final LinkedList<String> messages;
    private static final int MAX_MESSAGES = 3;

    private int firstMessageCycles;
    private static final int MAX_CYCLES_FOR_FIRST_MESSSAGE = 2880;

    public BroadCast() {
        this.messages = new LinkedList<>();
    }

    public void reset() {
        this.messages.clear();
    }

    public void addMessage(String message) {
        if (this.messages.size() >= MAX_MESSAGES) {
            this.messages.removeLast();
        }

        this.messages.offerFirst(message);
    }

    public void removeMessage(int index) {
        this.messages.remove(index);
    }

    public void drawBroadCastMessage() {
        if (!this.messages.isEmpty()) {
            if (firstMessageCycles == 0) {
                firstMessageCycles = Client.loopCycle;
            }

            for (int i = 0; i < this.getMessages().size(); i++) {
                int yPosition = Client.clientSize == 0 ? 329 - (i * 14) : (Client.instance.getClientHeight() - 169) - (i * 14);
                String message = this.getMessages().get(i);

                if (message == null) {
                    System.out.print("[BroadCasts] Broadcast msg is null.");
                    return;
                }

                // Remove last message every maxCycles + firstMessage
                if ((firstMessageCycles + MAX_CYCLES_FOR_FIRST_MESSSAGE) - Client.loopCycle <= 0) {
                    this.messages.removeFirst();
                    this.firstMessageCycles = 0;
                    return;
                }

                if (Client.instance.getUpdateMinutes() != 0) {
                    yPosition -= 14;
                }
                Client.instance.newRegularFont.drawBasicString(message, 5, yPosition, 16776960, 0);
            }
        }
    }
}

