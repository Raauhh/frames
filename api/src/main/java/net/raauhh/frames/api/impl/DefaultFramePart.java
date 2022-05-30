package net.raauhh.frames.api.impl;

import net.raauhh.frames.api.FramePart;
import net.raauhh.frames.api.util.Vector2D;

public class DefaultFramePart implements FramePart {

  private final Vector2D vector2D;
  private final byte[] pixels;

  private int id = -1;

  public DefaultFramePart(Vector2D vector2D, byte[] pixels) {
    this.vector2D = vector2D;
    this.pixels = pixels;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public Vector2D getVector() {
    return vector2D;
  }

  @Override
  public byte[] getPixels() {
    return pixels;
  }
}
