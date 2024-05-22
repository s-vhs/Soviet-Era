package ru.tesmio.world.structure;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import ru.tesmio.core.Config;
import ru.tesmio.reg.RegStructures;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class StructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        ThreadLocalRandom tr = ThreadLocalRandom.current();
      //  if(types.contains(BiomeDictionary.Type.PLAINS)) {
            List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();
         if(tr.nextInt(0, Config.freqGenLabs.get()) == 1) {
            structures.add(() -> RegStructures.PROCEDURAL_LABORATORY.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
       }
     //   }
    }
}
