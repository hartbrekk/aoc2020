package com.tj.day11;

public enum Status {
    E('L'),
    O('#'),
    F('.');

    public final Character label;

    Status(Character label) {
        this.label = label;

    }
}