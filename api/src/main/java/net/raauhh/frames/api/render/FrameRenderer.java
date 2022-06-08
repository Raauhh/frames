package net.raauhh.frames.api.render;

import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FramePart;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public interface FrameRenderer {

  /**
   * Renders the complete frame using {@link FrameRenderer#renderPart(Player, FramePart, Location, BlockFace)}
   * on every part of given {@link Frame} using the given {@link Location} as a reference
   * and every frame part applies its own vector using {@link net.raauhh.frames.api.util.Vector2D#apply(Location, BlockFace)}
   * on given location.
   */
  default void renderFrame(Player player, Frame frame, Location location, BlockFace facing) {
    for (FramePart part : frame.getParts()) {
      Location partLocation = location.clone();
      part.getVector().apply(partLocation, facing);

      renderPart(
          player,
          part,
          partLocation,
          facing
      );
    }
  }

  /**
   * Renders the given {@link FramePart} to a
   * given {@link Player} at given {@link Location}
   */
  void renderPart(Player player, FramePart part, Location location, BlockFace facing);

  default void hide(Player player, Frame frame) {
    for (FramePart part : frame.getParts()) {
      hide(player, part);
    }
  }

  void hide(Player player, FramePart part);
}
