package ru.tesmio.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumPart implements IStringSerializable
{
    DEF("def"),
    DOWN("down"),
    MID("mid"),
    UP("up"),
    WEST("west"),
    EAST("east"),
    NORTH("north"),
    SOUTH("south"),
    FULL("full");
    private final String name;

    EnumPart(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return this.name;
    }
}
