package net.raauhh.frames.api.impl;

import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.view.FrameView;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public class DefaultFrameView implements FrameView {

  private final Frame frame;
  private final Location location;
  private final BlockFace facing;

  public DefaultFrameView(
      Frame frame,
      Location location,
      BlockFace facing
  ) {
    this.frame = frame;
    this.location = location;
    this.facing = facing;
  }

  @Override
  public Frame getFrame() {
    return frame;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public BlockFace getFacing() {
    return facing;
  }
}
