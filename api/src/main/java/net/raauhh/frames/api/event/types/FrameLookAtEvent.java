package net.raauhh.frames.api.event.types;

import net.raauhh.frames.api.view.FrameView;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FrameLookAtEvent extends SimpleFrameEvent {

  private final Location lookingAt;

  public FrameLookAtEvent(
      Player player,
      FrameView view,
      Location lookingAt) {
    super(player, view);
    this.lookingAt = lookingAt;
  }

  public Location getLookingAt() {
    return lookingAt;
  }
}
