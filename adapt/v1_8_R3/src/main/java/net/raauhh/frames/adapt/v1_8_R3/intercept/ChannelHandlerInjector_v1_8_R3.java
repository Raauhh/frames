package net.raauhh.frames.adapt.v1_8_R3.intercept;

import net.raauhh.frames.api.intercept.ChannelHandlerInjector;
import net.raauhh.frames.api.intercept.PacketInterceptorRegistry;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ChannelHandlerInjector_v1_8_R3 implements ChannelHandlerInjector {

  private final PacketInterceptorRegistry registry;

  public ChannelHandlerInjector_v1_8_R3(PacketInterceptorRegistry registry) {
    this.registry = registry;
  }

  @Override
  public void inject(Player player) {
    ((CraftPlayer) player).getHandle()
        .playerConnection
        .networkManager
        .channel
        .pipeline()
        .addBefore(
            "packet_handler",
            CHANNEL_NAME,
            new FrameChannelDuplexHandler(player, registry)
        );
  }
}
