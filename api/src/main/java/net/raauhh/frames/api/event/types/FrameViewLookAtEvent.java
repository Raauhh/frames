package net.raauhh.frames.api.event.types;

import net.raauhh.frames.api.view.FrameView;
import org.bukkit.entity.Player;

public class FrameViewLookAtEvent extends SimpleFrameViewEvent {

  public FrameViewLookAtEvent(
      Player player,
      FrameView view
  ) {
    super(player, view);
  }
}
