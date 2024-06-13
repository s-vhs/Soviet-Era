package ru.tesmio.reg;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.core.Core;
import ru.tesmio.world.modifiers.SovietLootStructureModifier;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Core.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegModifiers {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new SovietLootStructureModifier.Serializer().setRegistryName
                        (new ResourceLocation(Core.MODID,"suit_in_igloo"))
        );
    }
}
