package net.raauhh.frames.api.event.types;

import net.raauhh.frames.api.view.FrameView;
import org.bukkit.entity.Player;

public class FrameClickEvent extends SimpleFrameEvent {

  private final Action action;

  public FrameClickEvent(
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
