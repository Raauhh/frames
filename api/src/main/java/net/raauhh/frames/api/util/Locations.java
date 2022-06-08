package net.raauhh.frames.api.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public final class Locations {

  public static boolean isLookingAt(Player player, Location location) {
    Location eyeLocation = player.getEyeLocation();
    Vector toEntity = location.toVector().subtract(eyeLocation.toVector());
    double dot = toEntity.normalize().dot(eyeLocation.getDirection());
    return dot > 0.98D;
  }

}
