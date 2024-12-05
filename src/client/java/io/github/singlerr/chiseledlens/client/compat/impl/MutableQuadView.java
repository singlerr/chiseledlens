package io.github.singlerr.chiseledlens.client.compat.impl;

import io.github.singlerr.chiseledlens.client.compat.mixins.AbstractBlockRenderContextAccessor;
import link.infra.indium.renderer.mesh.MutableQuadViewImpl;
import link.infra.indium.renderer.render.AbstractBlockRenderContext;

public class MutableQuadView extends MutableQuadViewImpl {

  private final AbstractBlockRenderContext context;
  private final boolean isVanilla;

  public MutableQuadView(AbstractBlockRenderContext context, boolean isVanilla){
    this.context = context;
    this.isVanilla = isVanilla;
  }

  @Override
  public void emitDirectly() {
    ((AbstractBlockRenderContextAccessor) context).renderQuad(this, isVanilla);
  }
}
