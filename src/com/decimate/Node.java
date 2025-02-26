package com.decimate;

public class Node {

    public long hash;
    public Node next;
    public Node prev;
    public Node() {
    }

    public void unlink() {
        if (prev == null) {
            return;
        } else {
            prev.next = next;
            next.prev = prev;
            next = null;
            prev = null;
            return;
        }
    }
}
