package net.raauhh.frames.api.impl;

import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FramePart;

public class DefaultFrame implements Frame {

  private final String id;
  private final FramePart[] parts;

  public DefaultFrame(String id, FramePart[] parts) {
    this.id = id;
    this.parts = parts;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public FramePart[] getParts() {
    return parts;
  }
}
