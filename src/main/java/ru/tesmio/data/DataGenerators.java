package ru.tesmio.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import ru.tesmio.core.Core;
import ru.tesmio.data.providers.SovietBlockStateProvider;
import ru.tesmio.data.providers.SovietItemModelProvider;
import ru.tesmio.data.providers.SovietLootProvider;
import ru.tesmio.data.providers.SovietRecipeProvider;
import ru.tesmio.data.providers.advancements.SovietAdvancementsProvider;

@Mod.EventBusSubscriber(modid = Core.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new SovietBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new SovietItemModelProvider(gen, existingFileHelper));
        gen.addProvider(new SovietLootProvider(gen));
        gen.addProvider(new SovietRecipeProvider(gen));
        gen.addProvider(new SovietAdvancementsProvider(gen));
    }
}
