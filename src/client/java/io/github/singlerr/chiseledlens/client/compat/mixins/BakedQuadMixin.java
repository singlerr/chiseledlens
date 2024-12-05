package io.github.singlerr.chiseledlens.client.compat.mixins;

import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BakedQuad.class)
public abstract class BakedQuadMixin implements FaceRegionHolder {

  @Unique
  private int stateId = -1;

  @Override
  public void setStateId(int stateId) {
    this.stateId = stateId;
  }

  @Override
  public int getStateId() {
    return stateId;
  }
}
