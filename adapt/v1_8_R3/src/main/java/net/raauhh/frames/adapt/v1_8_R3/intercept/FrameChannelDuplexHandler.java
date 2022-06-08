package net.raauhh.frames.adapt.v1_8_R3.intercept;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.raauhh.frames.api.intercept.PacketInterceptor;
import net.raauhh.frames.api.intercept.PacketInterceptorRegistry;
import org.bukkit.entity.Player;

public class FrameChannelDuplexHandler extends ChannelDuplexHandler {

  private final Player player;
  private final PacketInterceptorRegistry registry;

  public FrameChannelDuplexHandler(
      Player player,
      PacketInterceptorRegistry registry
  ) {
    this.player = player;
    this.registry = registry;
  }

  private <T> void handleRead(
      ChannelHandlerContext ctx,
      T packet
  ) throws Exception {
    PacketInterceptor<T> interceptor = (PacketInterceptor<T>) registry.getInterceptorFor(packet.getClass());
    if (interceptor == null) {
      super.channelRead(ctx, packet);
    } else {
      T in = interceptor.in(player, packet);
      if (in != null) super.channelRead(ctx, in);
    }
  }

  @Override
  public void channelRead(
      ChannelHandlerContext ctx,
      Object packet
  ) throws Exception {
    handleRead(ctx, packet);
  }

  private <T> void handleWrite(
      ChannelHandlerContext ctx,
      T packet,
      ChannelPromise promise
  ) throws Exception {
    PacketInterceptor<T> interceptor = (PacketInterceptor<T>) registry.getInterceptorFor(packet.getClass());
    if (interceptor == null) {
      super.write(ctx, packet, promise);
    } else {
      T out = interceptor.out(player, packet);
      if (out != null) super.write(ctx, out, promise);
    }
  }

  @Override
  public void write(
      ChannelHandlerContext ctx,
      Object packet,
      ChannelPromise promise
  ) throws Exception {
    handleWrite(ctx, packet, promise);
  }
}
