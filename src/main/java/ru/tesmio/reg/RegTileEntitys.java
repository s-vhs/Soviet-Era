package ru.tesmio.reg;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.blocks.affinage_factory.AffinageTileEntity;
import ru.tesmio.blocks.crusher.CrusherTileEntity;
import ru.tesmio.blocks.decorative.props.stillage.StillageTileEntity;
import ru.tesmio.blocks.diesel_generator.TileDieselGenerator;
import ru.tesmio.blocks.rasty_frame.RastyFrameTile;
import ru.tesmio.blocks.storage.desc_drawers.LinearTableDrawersTE;
import ru.tesmio.blocks.storage.dsp_tump.DspTumbTE;
import ru.tesmio.blocks.storage.kitchen_table.KitchenTableTE;
import ru.tesmio.blocks.storage.safe.TileEntitySafe;
import ru.tesmio.core.Core;

import java.util.function.Supplier;


public class RegTileEntitys {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Core.MODID);
    public static final RegistryObject<TileEntityType<CrusherTileEntity>> CRUSHER_TE = TILE_ENTITY_TYPES
            .register("crusher", () -> TileEntityType.Builder.create(CrusherTileEntity::new, RegBlocks.CRUSHER.get()).build(null));
    public static final RegistryObject<TileEntityType<AffinageTileEntity>> AFFINAGE_TE = TILE_ENTITY_TYPES
            .register("affinage", () -> TileEntityType.Builder.create(AffinageTileEntity::new, RegBlocks.AFFINAGE_FACTORY.get()).build(null));
    public static final RegistryObject<TileEntityType<TileDieselGenerator>> DIESEL_TILE =
            TILE_ENTITY_TYPES.register("diesel_tile",
                    () -> TileEntityType.Builder.create(TileDieselGenerator::new, RegBlocks.ENERGY_GENERATOR.get()).build(null));
    public static final RegistryObject<TileEntityType<LinearTableDrawersTE>> DRAWERS_STORAGE_TE = register("drawers_te", LinearTableDrawersTE::new, () -> new Block[]{RegBlocks.BIOLAB_TABLE4.get(), RegBlocks.BIOLAB_TABLE.get(), RegBlocks.CHEMLAB_TABLE.get()});
    public static final RegistryObject<TileEntityType<TileEntitySafe>> SAFE_TE =
            TILE_ENTITY_TYPES.register("safe_te",
                    () -> TileEntityType.Builder.create(TileEntitySafe::new, RegBlocks.SAFE.get()).build(null));

    public static final RegistryObject<TileEntityType<DspTumbTE>> DSP_TUMB_TE =
            TILE_ENTITY_TYPES.register("dsp_tumb_te",
                    () -> TileEntityType.Builder.create(DspTumbTE::new, RegBlocks.DSP_TUMB.get()).build(null));

    public static final RegistryObject<TileEntityType<KitchenTableTE>> KITCHEN_TABLE_TE =
            TILE_ENTITY_TYPES.register("kitchen_table_te",
                    () -> TileEntityType.Builder.create(KitchenTableTE::new, RegBlocks.KITCHEN_TABLE.get()).build(null));

    public static final RegistryObject<TileEntityType<StillageTileEntity>> STILLAGE_TE = TILE_ENTITY_TYPES
            .register("stillage_te",
                    () -> TileEntityType.Builder.create(StillageTileEntity::new, RegBlocks.STILLAGE.get()).build(null));

    public static final RegistryObject<TileEntityType<RastyFrameTile>> FRAMEBLOCK_TILE = TILE_ENTITY_TYPES
            .register("rasty_frame_te",
                    () -> TileEntityType.Builder.create(RastyFrameTile::new, RegBlocks.RASTY_FRAME.get()).build(null));
//    public static final RegistryObject<TileEntityType<TileEntityLightSource>> LIGHT_SOURCE = TILE_ENTITY_TYPES
//            .register("light_source_se",
//                    () -> TileEntityType.Builder.create(TileEntityLightSource::new, RegBlocks.BLOCK_LIGHT_SOURCE.get()).build(null));


    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factoryIn, Supplier<Block[]> validBlocksSupplier) {
        return TILE_ENTITY_TYPES.register(name, () -> TileEntityType.Builder.create(factoryIn, validBlocksSupplier.get()).build(null)); //Null until someone can explain data fixers
    }
}
