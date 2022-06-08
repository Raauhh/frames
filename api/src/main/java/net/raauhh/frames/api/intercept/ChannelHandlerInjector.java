package net.raauhh.frames.api.intercept;

import org.bukkit.entity.Player;

public interface ChannelHandlerInjector {

  String CHANNEL_NAME = "frames_injector";

  void inject(Player player);

}
