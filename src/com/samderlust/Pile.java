/*
    Ngoc Sang Nguyen
    313
    Howard Rosenblum
    Assignment 2
    This Class to define a pile
 */

package com.samderlust;

public class Pile {
    private int size;

    public Pile() {
        this.size = 10;
    }

    public Pile(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void remove(int amount) {
        this.size -= amount;
    }
}

