package ru.tesmio.items.protection_suit;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class Suit extends ArmorItem {
    public Suit(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        SuitModel model = new SuitModel(armorSlot);
        model.isChild = _default.isChild;
        model.isSitting = _default.isSitting;
        model.isSneak = _default.isSneak;
        return (A) model;
    }
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        String path = "";
        switch (slot) {
            case HEAD:
                path = "soviet:textures/suit/gasmask.png";
                break;
            case CHEST:
                path = "soviet:textures/suit/jacket_texture.png";
                break;
            case LEGS:
                path = "soviet:textures/suit/leggins_texture.png";
                break;
            case FEET:
                path = "soviet:textures/suit/boots_texture.png";
                break;
        }
        return path;
    }
}
