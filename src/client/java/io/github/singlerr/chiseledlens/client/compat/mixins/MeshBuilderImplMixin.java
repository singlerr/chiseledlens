package io.github.singlerr.chiseledlens.client.compat.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import link.infra.indium.renderer.mesh.MeshBuilderImpl;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MeshBuilderImpl.class)
public abstract class MeshBuilderImplMixin {

  @Shadow
  public abstract QuadEmitter getEmitter();

  @ModifyReturnValue(method = "build", at = @At(value = "RETURN"), remap = false)
  private Mesh lens$transmitState(Mesh original) {
    QuadEmitter emitter = getEmitter();
    if (emitter instanceof FaceRegionHolder holder &&
        original instanceof FaceRegionHolder meshHolder) {
      meshHolder.setStateId(holder.getStateId());
    }
    return original;
  }
}
