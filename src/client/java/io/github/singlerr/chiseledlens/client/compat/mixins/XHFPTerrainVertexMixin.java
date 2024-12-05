package io.github.singlerr.chiseledlens.client.compat.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import me.jellysquid.mods.sodium.client.render.chunk.vertex.format.ChunkVertexEncoder;
import mod.chiselsandbits.helpers.ModUtil;
import net.irisshaders.iris.compat.sodium.impl.vertex_format.terrain_xhfp.XHFPTerrainVertex;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(XHFPTerrainVertex.class)
public abstract class XHFPTerrainVertexMixin {

  @ModifyArg(method = "write", at = @At(value = "INVOKE", target = "Lorg/lwjgl/system/MemoryUtil;memPutShort(JS)V", ordinal = 3), remap = false, index = 1)
  private short lens$markVertex(short value,
                                @Local(argsOnly = true) ChunkVertexEncoder.Vertex vertex){
    if(vertex instanceof FaceRegionHolder regionHolder){
      int stateId = regionHolder.getStateId();
      if(stateId == -1){
        return value;
      }

      BlockState state = ModUtil.getStateById(stateId);
      if(ItemBlockRenderTypes.getChunkRenderType(state) == RenderType.translucent()){
        return 333;
      }
      if(state.getLightEmission() > 0){
        return 332;
      }
    }
    return value;
  }
}
