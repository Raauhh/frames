package net.raauhh.frames;

import net.raauhh.frames.adapt.v1_8_R3.FrameRenderer_v1_8_R3;
import net.raauhh.frames.adapt.v1_8_R3.PacketSender_v1_8_R3;
import net.raauhh.frames.adapt.v1_8_R3.view.FrameViewRegistry_v1_8_R3;
import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FrameManager;
import net.raauhh.frames.api.impl.DefaultFrameManager;
import net.raauhh.frames.api.impl.DefaultFrameView;
import net.raauhh.frames.api.render.FrameRenderer;
import net.raauhh.frames.api.view.FrameView;
import net.raauhh.frames.api.view.FrameViewRegistry;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class FramesPlugin extends JavaPlugin implements Listener {

  private FrameViewRegistry frameViewRegistry;
  private Frame frame;

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(
        this,
        this
    );

    try {
      URL url = new URL("https://i.imgur.com/PodlzEd.png");
      BufferedImage bufferedImage = ImageIO.read(url);

      FrameRenderer frameRenderer = new FrameRenderer_v1_8_R3(
          new PacketSender_v1_8_R3()
      );
      frameViewRegistry = new FrameViewRegistry_v1_8_R3(frameRenderer);

      FrameManager frameManager = new DefaultFrameManager();
      frame = frameManager.create(
          "test",
          bufferedImage,
          3,
          3
      );
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @EventHandler
  public void onPlayerInteractEvent(PlayerInteractEvent event) {
    if (!event.hasBlock() || !event.hasItem()) {
      return;
    }

    if (event.getItem().getType() != Material.FEATHER) {
      return;
    }

    Player player = event.getPlayer();
    Block block = event.getClickedBlock();
    BlockFace face = getBlockFace(player);
    if (face == null) {
      return;
    }

    if (face == BlockFace.UP || face == BlockFace.DOWN) {
      return;
    }

    FrameView view = new DefaultFrameView(
        frame,
        block.getLocation(),
        face
    );

    frameViewRegistry.register(player, view);
  }

  public BlockFace getBlockFace(Player player) {
    List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks((Set<Material>) null, 100);
    if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) {
      return null;
    }

    Block targetBlock = lastTwoTargetBlocks.get(1);
    Block adjacentBlock = lastTwoTargetBlocks.get(0);
    return targetBlock.getFace(adjacentBlock);
  }
}
