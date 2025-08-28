package net.tuffetspider.spectral.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.tuffetspider.spectral.attribute.ModAttributes;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(World.class)
public abstract class WorldMixin implements WorldAccess, AutoCloseable {


    @Shadow @Final public boolean isClient;


    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @ModifyReturnValue(at = @At("RETURN"),method = "getBlockState")
    private BlockState disableClientSeeingSpectral(BlockState original, BlockPos pos){
        Chunk chunk = this.getChunk(ChunkSectionPos.getSectionCoord(pos.getX()), ChunkSectionPos.getSectionCoord(pos.getZ()));
        if(this.isClient&&chunk.getBlockState(pos).isOf(ModBlocks.SPECTRAL_BLOCK) &&
                MinecraftClient.getInstance().player!=null&&
                MinecraftClient.getInstance().player.getAttributeValue(ModAttributes.SPECTRAL)!=1){
            return Blocks.AIR.getDefaultState();
        }

        return original;
    }
}
