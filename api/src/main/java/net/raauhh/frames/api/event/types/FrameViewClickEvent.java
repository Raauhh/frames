package net.raauhh.frames.api.event.types;

import net.raauhh.frames.api.view.FrameView;
import org.bukkit.entity.Player;

public class FrameViewClickEvent extends SimpleFrameViewEvent {

  private final Action action;

  public FrameViewClickEvent(
      Player player,
      FrameView view,
      Action action
  ) {
    super(player, view);

    this.action = action;
  }

  public Action getAction() {
    return action;
  }

  public enum Action {
    RIGHT_CLICK,
    LEFT_CLICK
  }

}
