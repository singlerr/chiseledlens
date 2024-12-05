//package io.github.singlerr.chiseledlens.client.compat.mixins;
//
//import com.llamalad7.mixinextras.sugar.Local;
//import com.llamalad7.mixinextras.sugar.Share;
//import com.llamalad7.mixinextras.sugar.ref.LocalRef;
//import com.mojang.blaze3d.platform.NativeImage;
//import com.mojang.blaze3d.systems.RenderSystem;
//import io.github.singlerr.chiseledlens.client.textures.ChiseledRegionBuilder;
//import io.github.singlerr.chiseledlens.client.textures.ChiseledRegionTexture;
//import java.io.IOException;
//import mod.chiselsandbits.chiseledblock.data.VoxelBlob;
//import mod.chiselsandbits.helpers.ModUtil;
//import mod.chiselsandbits.render.chiseledblock.ChiseledBlockBakedModel;
//import mod.chiselsandbits.render.chiseledblock.ChiseledModelBuilder;
//import mod.chiselsandbits.render.chiseledblock.FaceRegion;
//import mod.chiselsandbits.render.chiseledblock.IFaceBuilder;
//import net.irisshaders.iris.shaderpack.materialmap.WorldRenderingSettings;
//import net.minecraft.util.RandomSource;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Pseudo;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.Redirect;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(value = ChiseledBlockBakedModel.class, remap = false)
//@Pseudo
//public abstract class ChiseledBlockBakedModelMixin {
//
//  @Inject(method = "generateFaces", at = @At("HEAD"), remap = false)
//  private void lens$beginBuilder(ChiseledModelBuilder builder, VoxelBlob blob, RandomSource weight,
//                                 CallbackInfo ci, @Share("regionBuilder")
//                                 LocalRef<ChiseledRegionBuilder> builderRef) {
//    if(ChiseledRegionTexture.getSingleton() != null)
//      builderRef.set(ChiseledRegionTexture.getSingleton().newBuilder());
//  }
//
//  @Redirect(method = "generateFaces", at = @At(value = "INVOKE", target = "Lmod/chiselsandbits/render/chiseledblock/IFaceBuilder;put(II[F)V", ordinal = 4), remap = false)
//  private void lens$fillBuilder(IFaceBuilder instance, int vertexIndex, int element, float[] args,
//                                @Share("regionBuilder")
//                                LocalRef<ChiseledRegionBuilder> builderRef, @Local FaceRegion region) {
//    assert args.length == 2;
//    float u = args[0];
//    float v = args[1];
//
//    int id = WorldRenderingSettings.INSTANCE.getBlockStateIds().getInt(ModUtil.getStateById(region.getStateId()));
//    id &= 0xff;
//
//    int encoded = id << 24 | id << 16 | id << 8 | id & 0xff;
//
//    instance.put(vertexIndex, element, args);
//
//    if(builderRef.get() != null){
//      builderRef.get().putUV(u, v, encoded);
//    }
//  }
//
//  @Inject(method = "generateFaces", at = @At("TAIL"), remap = false)
//  private void lens$uploadBuilder(ChiseledModelBuilder builder, VoxelBlob blob, RandomSource weight,
//                                  CallbackInfo ci, @Share("regionBuilder")
//                                  LocalRef<ChiseledRegionBuilder> builderRef) {
//
//    if(builderRef.get() != null) {
//      RenderSystem.recordRenderCall(() -> {
//        ChiseledRegionTexture.getSingleton().upload();
//      });
//    }
//  }
//
//}
//
