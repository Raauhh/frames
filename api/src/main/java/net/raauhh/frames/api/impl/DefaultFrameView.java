package net.raauhh.frames.api.impl;

import com.google.common.collect.Sets;
import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FrameView;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class DefaultFrameView implements FrameView {

  private final Frame frame;
  private final Set<UUID> viewers = Sets.newHashSet();

  public DefaultFrameView(Frame frame) {
    this.frame = frame;
  }

  @Override
  public Frame getFrame() {
    return frame;
  }

  @Override
  public Collection<UUID> getViewers() {
    return viewers;
  }
}
