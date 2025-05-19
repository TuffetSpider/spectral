package net.tuffetspider.spectral.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.tuffetspider.spectral.attribute.ModAttributes;

import java.util.Objects;

public class SpectralDustItem extends Item {
    public SpectralDustItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (Objects.requireNonNull(entity.getAttributeInstance(ModAttributes.SPECTRAL)).getValue() == 0) {
            Objects.requireNonNull(entity.getAttributeInstance(ModAttributes.SPECTRAL)).setBaseValue(1);
        } else Objects.requireNonNull(entity.getAttributeInstance(ModAttributes.SPECTRAL)).setBaseValue(0);
        return super.useOnEntity(stack, user, entity, hand);

    }

}