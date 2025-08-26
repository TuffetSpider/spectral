package net.tuffetspider.spectral.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.World;
import net.tuffetspider.spectral.Spectral;
import net.tuffetspider.spectral.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(World.class)
public class WorldMixin {
    @ModifyReturnValue(at=@At("RETURN"),method = "getBlockState")
    private BlockState testing(BlockState original){
        if(MinecraftClient.getInstance().player!= null){
        if(!Spectral.isSpectral(MinecraftClient.getInstance().player)&&original.isOf(ModBlocks.SPECTRAL_BLOCK)){
            return Blocks.AIR.getDefaultState();
        }
    }
        return original;
    }
}
