package net.raauhh.frames.adapt.v1_8_R3;

import com.google.inject.Singleton;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.raauhh.frames.api.util.PacketSender;
import org.apache.commons.lang3.Validate;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@Singleton
public class PacketSender_v1_8_R3 implements PacketSender {

  @Override
  public void sendPackets(Player target, Object... packets) {
    CraftPlayer player = (CraftPlayer) target;
    PlayerConnection connection = player.getHandle().playerConnection;

    for (Object packet : packets) {
      Validate.isTrue(packet instanceof Packet, "Provided object isn't a packet.");
      connection.sendPacket((Packet<?>) packet);
    }
  }
}
