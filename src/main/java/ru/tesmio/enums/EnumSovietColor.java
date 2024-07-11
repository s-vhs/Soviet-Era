package ru.tesmio.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumSovietColor implements IStringSerializable
{
    EMPTY("empty"),
    WATER("water"),
    //-----//
    ORANGE("orange"),
    BLUE("blue"),
    RED("red"),
    YELLOW("yellow"),
    WHITE("white"),
    BEIGE("beige"),
    GREEN("green"),
    GRAY("gray"),
    BEIGE2("beige2");

    private final String name;

    EnumSovietColor(String name)
    {
        this.name = name;
    }

    public String getString()
    {
        return this.name;
    }



}
