package io.github.singlerr.chiseledlens.client.compat.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import link.infra.indium.renderer.mesh.MutableQuadViewImpl;
import link.infra.indium.renderer.render.AbstractBlockRenderContext;
import link.infra.indium.renderer.render.TerrainRenderContext;
import me.jellysquid.mods.sodium.client.render.chunk.terrain.material.Material;
import me.jellysquid.mods.sodium.client.render.chunk.vertex.format.ChunkVertexEncoder;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TerrainRenderContext.class)
public abstract class TerrainRenderContextMixin extends AbstractBlockRenderContext {

  @Inject(method = "bufferQuad", at = @At(value = "INVOKE_ASSIGN", target = "Llink/infra/indium/renderer/mesh/MutableQuadViewImpl;lightmap(I)I"), remap = false)
  private void lens$injectFaceRegion(MutableQuadViewImpl quad, Material material, CallbackInfo ci, @Local
                                     ChunkVertexEncoder.Vertex vertex){
    if(quad instanceof FaceRegionHolder holder && vertex instanceof FaceRegionHolder vertexRegionHolder){
      vertexRegionHolder.setStateId(holder.getStateId());
    }
  }
}
