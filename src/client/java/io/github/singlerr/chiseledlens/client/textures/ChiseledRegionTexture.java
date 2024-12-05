package io.github.singlerr.chiseledlens.client.textures;

import net.irisshaders.iris.gl.state.ValueUpdateNotifier;
import net.irisshaders.iris.gl.texture.GlTexture;
import net.irisshaders.iris.gl.texture.InternalTextureFormat;
import net.irisshaders.iris.gl.texture.PixelType;
import net.irisshaders.iris.gl.texture.TextureType;
import net.irisshaders.iris.shaderpack.texture.TextureFilteringData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChiseledRegionTexture extends GlTexture {

  private static final Logger log = LoggerFactory.getLogger(ChiseledRegionTexture.class);
  private static ChiseledRegionTexture singleton;
  public ChiseledRegionTexture() {
    super(TextureType.TEXTURE_2D, 256, 256, 0, InternalTextureFormat.RGBA.getGlFormat(), InternalTextureFormat.RGBA.getPixelFormat().getGlFormat(), PixelType.UNSIGNED_BYTE.getGlFormat(),
        new byte[4 * 256 * 256], new TextureFilteringData(false, false));
  }

  public static ChiseledRegionTexture getSingleton() {
    return singleton;
  }

  public static void setSingleton(ChiseledRegionTexture singleton) {
    ChiseledRegionTexture.singleton = singleton;
  }


  public static class Notifier implements ValueUpdateNotifier{

    @Override
    public void setListener(Runnable runnable) {
      if(runnable != null)
        runnable.run();
    }
  }

}
