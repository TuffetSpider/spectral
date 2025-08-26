package net.tuffetspider.spectral.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PersistentProjectileEntity.class)
public abstract class SpectralPersistentProjectileEntityMixin extends ProjectileEntity {


    public SpectralPersistentProjectileEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyExpressionValue(at= @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/shape/VoxelShape;"),method = "tick")
    private VoxelShape stopArrowsGettingStuckInSpectralBlocks(VoxelShape original){
        BlockPos blockPos = this.getBlockPos();
        BlockState blockState = this.getWorld().getBlockState(blockPos);

        if(this.getOwner() instanceof LivingEntity livingOwner && !Spectral.isSpectral(livingOwner)&&blockState.isOf(ModBlocks.SPECTRAL_BLOCK)){
            return VoxelShapes.empty();
        }
        else return original;



    }
}
