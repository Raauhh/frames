package net.raauhh.frames.api.impl;

import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FrameManager;
import net.raauhh.frames.api.FramePart;
import net.raauhh.frames.api.util.Images;
import net.raauhh.frames.api.util.Vector2D;
import org.bukkit.entity.Player;
import org.bukkit.map.MapPalette;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultFrameManager implements FrameManager {

  private final Map<String, Frame> frames = new ConcurrentHashMap<>();

  @Override
  public Frame create(String id, BufferedImage image, int columns, int rows) {
    image = Images.resizeImage(
        image,
        columns * PIXELS_PER_FRAME,
        rows * PIXELS_PER_FRAME
    );

    FramePart[] frames = new FramePart[rows * columns];
    int index = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int minX = PIXELS_PER_FRAME * j;
        int minY = PIXELS_PER_FRAME * i;

        BufferedImage part = image.getSubimage(
            minX,
            minY,
            PIXELS_PER_FRAME,
            PIXELS_PER_FRAME
        );

        frames[index++] = new DefaultFramePart(
            new Vector2D(j, i),
            MapPalette.imageToBytes(part)
        );
      }
    }

    return new DefaultFrame(
        id,
        frames
    );
  }

  @Override
  public void remove(Frame frame) {
    frames.remove(frame.getId());
  }

  @Override
  public Collection<? extends Frame> getFrames() {
    return frames.values();
  }

  @Override
  public Frame getFrameByEntityId(Player player, int id) {
    return null;
  }
}
