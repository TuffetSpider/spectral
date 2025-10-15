package net.tuffetspider.spectral.block;


import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.attribute.ModAttributes;

public class ColdIronBlock extends Block {
    public ColdIronBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext entityShapeContext) {
            if (entityShapeContext.getEntity() instanceof LivingEntity livingEntity && livingEntity.getAttributeValue(ModAttributes.SPECTRAL) == 1) {
                return VoxelShapes.empty();
            }

            if(entityShapeContext.getEntity() instanceof Ownable ownable && ownable.getOwner() instanceof LivingEntity livingOwner){
                if(!Spectral.isSpectral(livingOwner)){
                    return super.getCollisionShape(state,world,pos,context);
                } else return VoxelShapes.empty();
            }
        }
        return super.getCollisionShape(state, world, pos, context);
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



}
