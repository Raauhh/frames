package net.raauhh.frames.adapt.v1_8_R3.util;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Location;

public final class Chunks {

  private Chunks() {
    throw new IllegalStateException("Cannot instantiate utility class!");
  }

  public static boolean isLoaded(EntityPlayer entityplayer, int chunkX, int chunkZ) {
    return entityplayer.u()
        .getPlayerChunkMap()
        .a(entityplayer, chunkX, chunkZ);
  }

  public static boolean isLoaded(EntityPlayer entityplayer, Location location) {
    return isLoaded(
        entityplayer,
        location.getBlockX() >> 4,
        location.getBlockZ() >> 4
    );
  }
}
