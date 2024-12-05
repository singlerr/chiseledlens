//package io.github.singlerr.chiseledlens.client.compat.mixins;
//
//import com.llamalad7.mixinextras.sugar.Local;
//import io.github.singlerr.chiseledlens.client.textures.ChiseledRegionTexture;
//import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
//import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.function.Supplier;
//import net.irisshaders.iris.gl.program.ProgramSamplers;
//import net.irisshaders.iris.gl.texture.TextureAccess;
//import net.irisshaders.iris.gl.texture.TextureType;
//import net.irisshaders.iris.pipeline.IrisRenderingPipeline;
//import net.irisshaders.iris.samplers.IrisSamplers;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Pseudo;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.Redirect;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(value = IrisRenderingPipeline.class, remap = false)
//@Pseudo
//public abstract class IrisRenderingPipelineMixin {
//
//  @Inject(method = "lambda$new$6", at = @At("TAIL"), remap = false)
//  private void lens$registerTexture(Supplier flipped, int programId,
//                                    CallbackInfoReturnable<ProgramSamplers> cir, @Local ProgramSamplers.Builder builder, @Local ProgramSamplers.CustomTextureSamplerInterceptor interceptor) {
//
//    ChiseledRegionTexture texture = new ChiseledRegionTexture();
//    ChiseledRegionTexture.setSingleton(texture);
//    interceptor.addDynamicSampler(texture.getTextureId(), new ChiseledRegionTexture.Notifier(), "chiseledRegionTex");
//  }
//}
