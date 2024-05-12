package ru.tesmio.world.structure;

import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.core.Core;
import ru.tesmio.reg.RegStructures;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Core.MODID)
public class WorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        StructureGeneration.generateStructures(event);
    }
    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld) {

            ServerWorld serverWorld = (ServerWorld) event.getWorld();
            ChunkGenerator scp = serverWorld.getChunkProvider().generator;
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(scp.func_235957_b_().func_236195_a_());
            tempMap.putIfAbsent(RegStructures.PROCEDURAL_LABORATORY.get(), DimensionStructuresSettings.field_236191_b_.get(RegStructures.PROCEDURAL_LABORATORY.get()));
            scp.func_235957_b_().field_236193_d_ = tempMap;

        }
    }
}
