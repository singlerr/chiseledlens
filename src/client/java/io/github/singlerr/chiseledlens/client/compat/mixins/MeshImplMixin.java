package io.github.singlerr.chiseledlens.client.compat.mixins;

import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import link.infra.indium.renderer.mesh.MeshImpl;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MeshImpl.class)
@Pseudo
public abstract class MeshImplMixin implements FaceRegionHolder {

  @Unique
  private int stateId;

  @Override
  public int getStateId() {
    return stateId;
  }

  @Override
  public void setStateId(int stateId) {
    this.stateId = stateId;
  }

  @Inject(method = "outputTo", at = @At(value = "HEAD"), remap = false)
  private void lens$injectFaceRegion(QuadEmitter emitter, CallbackInfo ci) {
    if (emitter instanceof FaceRegionHolder holder) {
      holder.setStateId(stateId);
    }
  }
}
