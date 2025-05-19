package net.tuffetspider.spectral.block;


import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tuffetspider.spectral.attribute.ModAttributes;
import org.jetbrains.annotations.Nullable;

public class SpectralBlock extends Block {
    public SpectralBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext entityShapeContext && entityShapeContext.getEntity() instanceof LivingEntity livingEntity && livingEntity.getAttributeValue(ModAttributes.SPECTRAL) == 1) {
            return super.getCollisionShape(state, world, pos, context);
        } else return VoxelShapes.empty();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.getChunk(pos);
        super.onPlaced(world, pos, state, placer, itemStack);
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

    @Override
    protected void spawnBreakParticles(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        if (player.getAttributeValue(ModAttributes.SPECTRAL) == 1) {
            super.spawnBreakParticles(world, player, pos, state);
        } else super.spawnBreakParticles(world, player, pos, Blocks.AIR.getDefaultState());
    }
}
