package net.raauhh.frames.api.util;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public class Vector2D {

  private final int x;
  private final int y;

  public Vector2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void apply(Location location, BlockFace facing) {
    location.setY(location.getY() - y + 0.5);
    if (facing.name().startsWith("NORTH")) {
      location.setX(location.getX() - x + 0.5);
    } else if (facing.name().startsWith("SOUTH")) {
      location.setX(location.getX() + x + 0.5);
      location.setZ(location.getZ() + 0.5);
    } else if (facing.name().startsWith("EAST")) {
      location.setZ(location.getZ() - x + 0.5);
      location.setX(location.getX() + 0.5);
    } else if (facing.name().startsWith("WEST")) {
      location.setZ(location.getZ() + x + 0.5);
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
