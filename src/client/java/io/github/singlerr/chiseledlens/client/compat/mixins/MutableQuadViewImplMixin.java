package io.github.singlerr.chiseledlens.client.compat.mixins;

import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import link.infra.indium.renderer.mesh.MutableQuadViewImpl;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MutableQuadViewImpl.class)
public abstract class MutableQuadViewImplMixin implements FaceRegionHolder {

  @Unique
  private int stateId;

  @Override
  public void setStateId(int stateId) {
    this.stateId = stateId;
  }

  @Override
  public int getStateId() {
    return stateId;
  }

  @Inject(method = "fromVanilla(Lnet/minecraft/client/renderer/block/model/BakedQuad;Lnet/fabricmc/fabric/api/renderer/v1/material/RenderMaterial;Lnet/minecraft/core/Direction;)Llink/infra/indium/renderer/mesh/MutableQuadViewImpl;", at = @At("HEAD"), remap = false)
  private void lens$injectFaceRegion(BakedQuad quad, RenderMaterial material, Direction cullFace,
                                     CallbackInfoReturnable<MutableQuadViewImpl> cir){
    if(quad instanceof FaceRegionHolder holder){
      stateId = holder.getStateId();
    }
  }
}
