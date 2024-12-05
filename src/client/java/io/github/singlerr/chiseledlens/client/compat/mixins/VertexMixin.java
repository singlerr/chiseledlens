package io.github.singlerr.chiseledlens.client.compat.mixins;

import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import me.jellysquid.mods.sodium.client.render.chunk.vertex.format.ChunkVertexEncoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChunkVertexEncoder.Vertex.class)
public abstract class VertexMixin implements FaceRegionHolder {

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
}
