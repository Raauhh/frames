package net.raauhh.frames.api.util;

import org.bukkit.entity.Player;

public interface PacketSender {

  /**
   * Sends the specified packets to the
   * specified target player
   *
   * @param target  The packet receiver
   * @param packets The packets
   */
  void sendPackets(Player target, Object... packets);

}