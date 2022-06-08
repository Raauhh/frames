package net.raauhh.frames.adapt.v1_8_R3;

import net.minecraft.server.v1_8_R3.*;
import net.raauhh.frames.api.FramePart;
import net.raauhh.frames.api.render.FrameRenderer;
import net.raauhh.frames.api.util.PacketSender;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class FrameRenderer_v1_8_R3 implements FrameRenderer {

  private final PacketSender packetSender;

  public FrameRenderer_v1_8_R3(PacketSender packetSender) {
    this.packetSender = packetSender;
  }

  @Override
  public void renderPart(
      Player player,
      FramePart part,
      Location location,
      BlockFace facing
  ) {
    EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();

    ItemStack item = new ItemStack(Items.FILLED_MAP);
    EntityItemFrame itemFrame = new EntityItemFrame(nmsPlayer.world);

    item.setData(itemFrame.getId()); // assign the same id of item frame to the map.
    itemFrame.setItem(item);
    itemFrame.setPosition(location.getX(), location.getY(), location.getZ());
    itemFrame.setDirection(CraftBlock.blockFaceToNotch(facing));

    part.setId(itemFrame.getId());

    Packet<?> spawn = new PacketPlayOutSpawnEntity(
        itemFrame,
        71 // respective id of item frame
    );

    Packet<?> metadata = new PacketPlayOutEntityMetadata(
        itemFrame.getId(),
        itemFrame.getDataWatcher(),
        true // clear data watcher
    );

    Packet<?> map = new PacketPlayOutMap(
        item.getData(), // the id of the item map.
        (byte) 0, // map scale
        Collections.emptyList(), // icons
        part.getPixels(), // pixels
        0,
        0,
        128,
        128
    );

    packetSender.sendPackets(
        player,
        spawn,
        metadata,
        map
    );
  }

  @Override
  public void hide(Player player, FramePart part) {
    packetSender.sendPackets(
        player,
        new PacketPlayOutEntityDestroy(part.getId())
    );
  }
}
