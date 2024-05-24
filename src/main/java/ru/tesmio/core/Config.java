package ru.tesmio.core;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> canDisassembleDeviceHand;
    public static final ForgeConfigSpec.ConfigValue<Integer> heightGenLabs;
    public static final ForgeConfigSpec.ConfigValue<Integer> freqGenLabs;
    static {
        BUILDER.push("Config Soviet Era 2");

        canDisassembleDeviceHand = BUILDER.comment("Can the player dismantle the devices with a right mouse click.").define("Boolean Value", true);
        heightGenLabs = BUILDER.comment("Sets the height of the generation of procedural laboratories. The closer to 0, the higher.").define("Integer Value", -170);
        freqGenLabs = BUILDER.comment("The frequency of laboratory generation in the world. The higher the value, the less frequent the generation.").define("Integer Value", 1);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
