package net.raauhh.frames.adapt.v1_8_R3.intercept.types;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FrameManager;
import net.raauhh.frames.api.event.FrameEventHandler;
import net.raauhh.frames.api.event.types.FrameClickEvent;
import net.raauhh.frames.api.intercept.PacketInterceptor;
import net.raauhh.frames.api.view.FrameView;
import net.raauhh.frames.api.view.FrameViewRegistry;
import org.bukkit.entity.Player;

import java.io.IOException;

public class FrameClickInterceptor implements PacketInterceptor<PacketPlayInUseEntity> {

  private final FrameManager frameManager;
  private final FrameViewRegistry viewRegistry;
  private final FrameEventHandler eventHandler;

  public FrameClickInterceptor(
      FrameManager frameManager,
      FrameViewRegistry viewRegistry,
      FrameEventHandler eventHandler
  ) {
    this.frameManager = frameManager;
    this.viewRegistry = viewRegistry;
    this.eventHandler = eventHandler;
  }

  @Override
  public PacketPlayInUseEntity in(Player player, PacketPlayInUseEntity packet) {
    PacketDataScanner scanner = new PacketDataScanner();
    try {
      packet.b(scanner);
    } catch (IOException ignored) {
      return packet;
    }

    int entityId = scanner.entityId;
    FrameClickEvent.Action action = scanner.action;

    Frame frame = frameManager.getFrameByEntityId(entityId);
    if (frame == null) {
      return packet;
    }

    FrameView view = viewRegistry.getView(frame.getId());
    if (view == null) {
      return packet;
    }

    eventHandler.call(
        new FrameClickEvent(
            player,
            view,
            action
        )
    );
    return packet;
  }

  private static class PacketDataScanner extends PacketDataSerializer {

    private int entityId;
    private FrameClickEvent.Action action;

    private PacketDataScanner() {
      super(null);
    }

    @Override
    public void b(int i) {
      this.entityId = i;
    }

    @Override
    public void a(Enum<?> action) {
      this.action = FrameClickEvent.Action.values()[action.ordinal() % FrameClickEvent.Action.values().length];
    }

    @Override
    public ByteBuf writeFloat(float f) {
      return null;
    }
  }

}
