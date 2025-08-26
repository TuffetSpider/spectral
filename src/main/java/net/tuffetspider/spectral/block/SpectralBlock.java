package net.tuffetspider.spectral.block;


import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.tuffetspider.spectral.attribute.ModAttributes;

public class SpectralBlock extends Block {
    public SpectralBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext entityShapeContext && entityShapeContext.getEntity() instanceof LivingEntity livingEntity && livingEntity.getAttributeValue(ModAttributes.SPECTRAL) == 0) {
            return VoxelShapes.empty();
        } else return super.getCollisionShape(state, world, pos, context);
    }


    @Override
    protected VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }


    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected boolean isCullingShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        if(player.getAttributeValue(ModAttributes.SPECTRAL)==1){
            return super.calcBlockBreakingDelta(state, player, world, pos);
        }
        else return 0.0F;
    }

}
