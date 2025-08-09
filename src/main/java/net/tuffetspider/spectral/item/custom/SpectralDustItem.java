package net.tuffetspider.spectral.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.effect.ModEffects;

import java.util.Objects;

public class SpectralDustItem extends Item {
    public SpectralDustItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity.hasStatusEffect(ModEffects.SPECTRAL)){
            entity.removeStatusEffect(ModEffects.SPECTRAL);
        } else entity.addStatusEffect(new StatusEffectInstance(ModEffects.SPECTRAL, StatusEffectInstance.INFINITE,0,true,false));
        if(user.getWorld() instanceof ServerWorld serverWorld){
            serverWorld.spawnParticles(ParticleTypes.POOF,entity.getX(),entity.getY()+0.5,entity.getZ(),5,0,0,0,0);
            serverWorld.playSound(entity,entity.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS,1.0f,1.0f);
        }
        if(!user.isCreative()){
            stack.decrement(1);
        }
        return ActionResult.SUCCESS;

    }


}