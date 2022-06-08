package net.raauhh.frames.api.event.types;

import net.raauhh.frames.api.view.FrameView;
import net.raauhh.frames.api.event.FrameEvent;
import org.bukkit.entity.Player;

public class SimpleFrameViewEvent implements FrameEvent {

  private final Player player;
  private final FrameView view;

  public SimpleFrameViewEvent(Player player, FrameView view) {
    this.player = player;
    this.view = view;
  }

  public Player getPlayer() {
    return player;
  }

  public FrameView getView() {
    return view;
  }
}
