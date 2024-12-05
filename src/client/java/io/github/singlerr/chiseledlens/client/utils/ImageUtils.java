package io.github.singlerr.chiseledlens.client.utils;

import java.security.SecureRandom;

public final class ImageUtils {

  private static final SecureRandom RANDOM = new SecureRandom();

  public static int generateRandomColor(){
    int r = RANDOM.nextInt(255);
    int g = RANDOM.nextInt(255);
    int b = RANDOM.nextInt(255);
    int a = 255;
    return r << 24 | g << 16 | b << 8 | a;
  }

  public static byte[] generateTexture(int width, int height){
    byte[] bytes = new byte[4 * width * height];
    RANDOM.nextBytes(bytes);
    return bytes;
  }
}
