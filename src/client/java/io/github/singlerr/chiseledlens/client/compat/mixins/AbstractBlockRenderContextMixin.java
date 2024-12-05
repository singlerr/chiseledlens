package io.github.singlerr.chiseledlens.client.compat.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import link.infra.indium.renderer.helper.ColorHelper;
import link.infra.indium.renderer.mesh.MutableQuadViewImpl;
import link.infra.indium.renderer.render.AbstractBlockRenderContext;
import link.infra.indium.renderer.render.BlockRenderInfo;
import mod.chiselsandbits.chiseledblock.BlockChiseled;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlockRenderContext.class)
public abstract class AbstractBlockRenderContextMixin {

  @Shadow
  protected BlockRenderInfo blockInfo;

  @Redirect(method = "colorizeQuad", at = @At(value = "INVOKE", target = "Llink/infra/indium/renderer/mesh/MutableQuadViewImpl;color(II)Llink/infra/indium/renderer/mesh/MutableQuadViewImpl;"), remap = false)
  private MutableQuadViewImpl lens$skipAlpha(MutableQuadViewImpl instance, int vertexIndex,
                                             int color, @Local(ordinal = 1) int blockColor) {
    if (blockInfo.blockState.getBlock() instanceof BlockChiseled) {
      int c = instance.color(vertexIndex);
      return instance.color(vertexIndex, ColorHelper.multiplyRGB(c, blockColor));
    }

    return instance.color(vertexIndex, color);
  }

  @Inject(method = "colorizeQuad", at = @At("HEAD"), cancellable = true, remap = false)
  private void lens$skipAlpha(MutableQuadViewImpl quad, int colorIndex,
                              CallbackInfo ci) {
    if (blockInfo.blockState.getBlock() instanceof BlockChiseled) {
      ci.cancel();
    }
  }

  @Inject(method = "shadeQuad", at = @At("HEAD"), cancellable = true, remap = false)
  private void lens$skipShadeQuad(MutableQuadViewImpl quad, boolean isVanilla, boolean ao,
                                  boolean emissive, CallbackInfo ci) {
    if (blockInfo.blockState.getBlock() instanceof BlockChiseled) {
      ci.cancel();
    }
  }
}
