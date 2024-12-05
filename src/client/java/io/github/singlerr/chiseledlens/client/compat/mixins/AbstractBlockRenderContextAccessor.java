package io.github.singlerr.chiseledlens.client.compat.mixins;

import link.infra.indium.renderer.mesh.MutableQuadViewImpl;
import link.infra.indium.renderer.render.AbstractBlockRenderContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractBlockRenderContext.class)
public interface AbstractBlockRenderContextAccessor {

  @Invoker(value = "renderQuad", remap = false)
  void renderQuad(MutableQuadViewImpl quad, boolean isVanilla);
}
