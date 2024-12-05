package io.github.singlerr.chiseledlens.client.textures;

import com.mojang.blaze3d.platform.NativeImage;
import java.io.Closeable;
import java.io.IOException;

public final class ChiseledRegionBuilder {

  private final int width;
  private final int height;
  private final NativeImage image;

  public ChiseledRegionBuilder(int width, int height, NativeImage image) {
    this.width = width;
    this.height = height;
    this.image = image;
  }

  public void putUV(float u, float v, int data) {
    int x = (int) ((width - 1) * u);
    int y = (int) ((height - 1) * v);

    this.image.setPixelRGBA(x, y, data);
  }

  public void clear() {
    this.image.fillRect(0, 0, width, height, 0);
  }

  public NativeImage getImage() {
    return this.image;
  }
}
