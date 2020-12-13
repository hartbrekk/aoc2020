package com.tj.day12;

public enum Dir {
    N('N'),
    S('S'),
    E('E'),
    W('W'),
    L('L'),
    R('R'),
    F('F');

    public final Character label;

    Dir(Character label) {
        this.label = label;

    }
}

/**
 * Action N means to move north by the given value.
 * Action S means to move south by the given value.
 * Action E means to move east by the given value.
 * Action W means to move west by the given value.
 * Action L means to turn left the given number of degrees.
 * Action R means to turn right the given number of degrees.
 * Action F means to move forward by the given value in the direction the ship is currently facing.
 */
