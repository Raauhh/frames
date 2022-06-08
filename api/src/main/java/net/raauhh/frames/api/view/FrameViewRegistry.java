package net.raauhh.frames.api.view;

import org.bukkit.entity.Player;

public interface FrameViewRegistry {

  boolean register(Player player, FrameView view);

  boolean unregister(Player player);

}
