package net.raauhh.frames.api.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Images {

  private Images() {
    throw new IllegalStateException("Cannot instantiate utility class!");
  }

  public static BufferedImage resizeImage(BufferedImage bufferedImage, int width, int height) {
    Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics2D graphics = resized.createGraphics();
    graphics.drawImage(image, 0, 0, null);
    graphics.dispose();
    return resized;
  }

}
