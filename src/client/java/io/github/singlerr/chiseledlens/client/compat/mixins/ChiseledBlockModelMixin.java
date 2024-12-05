package io.github.singlerr.chiseledlens.client.compat.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.singlerr.chiseledlens.client.compat.extensions.FaceRegionHolder;
import mod.chiselsandbits.render.chiseledblock.ChiseledBlockBakedModel;
import mod.chiselsandbits.render.chiseledblock.FaceRegion;
import mod.chiselsandbits.render.chiseledblock.IFaceBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChiseledBlockBakedModel.class)
public abstract class ChiseledBlockModelMixin {

  @Redirect(method = "generateFaces", at = @At(value = "INVOKE", target = "Lmod/chiselsandbits/render/chiseledblock/IFaceBuilder;create(Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)Lnet/minecraft/client/renderer/block/model/BakedQuad;"), remap = false)
  private BakedQuad lens$injectFaceRegion(IFaceBuilder instance, TextureAtlasSprite textureAtlasSprite,
                                              @Local FaceRegion region){
    BakedQuad quad = instance.create(textureAtlasSprite);
    if(quad instanceof FaceRegionHolder holder){
      holder.setStateId(region.getStateId());
    }
    return quad;
  }
}
