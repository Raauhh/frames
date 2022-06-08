package net.raauhh.frames;

import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.raauhh.frames.adapt.v1_8_R3.FrameRenderer_v1_8_R3;
import net.raauhh.frames.adapt.v1_8_R3.PacketSender_v1_8_R3;
import net.raauhh.frames.adapt.v1_8_R3.intercept.ChannelHandlerInjector_v1_8_R3;
import net.raauhh.frames.adapt.v1_8_R3.intercept.types.FrameClickInterceptor;
import net.raauhh.frames.adapt.v1_8_R3.view.FrameViewRegistry_v1_8_R3;
import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FrameManager;
import net.raauhh.frames.api.event.FrameEventHandler;
import net.raauhh.frames.api.event.types.FrameClickEvent;
import net.raauhh.frames.api.event.types.FrameLookAtEvent;
import net.raauhh.frames.api.impl.DefaultFrameEventHandler;
import net.raauhh.frames.api.impl.DefaultFrameManager;
import net.raauhh.frames.api.impl.DefaultFrameView;
import net.raauhh.frames.api.impl.DefaultPacketInterceptorRegistry;
import net.raauhh.frames.api.intercept.ChannelHandlerInjector;
import net.raauhh.frames.api.intercept.PacketInterceptorRegistry;
import net.raauhh.frames.api.view.FrameView;
import net.raauhh.frames.api.view.FrameViewRegistry;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FramesPlugin extends JavaPlugin implements Listener {

  private FrameManager frameManager;
  private FrameViewRegistry frameViewRegistry;
  private FrameEventHandler frameEventHandler;

  private ChannelHandlerInjector injector;
  private Frame frame;

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(
        this,
        this
    );

    frameManager = new DefaultFrameManager();
    frameEventHandler = new DefaultFrameEventHandler();
    frameViewRegistry = new FrameViewRegistry_v1_8_R3(
        new FrameRenderer_v1_8_R3(
            new PacketSender_v1_8_R3()
        )
    );

    PacketInterceptorRegistry interceptorRegistry = new DefaultPacketInterceptorRegistry();
    interceptorRegistry.register(
        PacketPlayInUseEntity.class,
        new FrameClickInterceptor(
            frameManager,
            frameViewRegistry,
            frameEventHandler
        )
    );

    injector = new ChannelHandlerInjector_v1_8_R3(interceptorRegistry);
    frame = loadFrameFromURL(
        "https://i.imgur.com/PodlzEd.png",
        3,
        3
    );

    new FrameLookAtTask(
        frameViewRegistry,
        frameEventHandler
    ).runTaskTimer(this, 0, 5L);

    this.registerEvents();
  }

  public Frame loadFrameFromURL(
      String imageURL,
      int columns,
      int rows
  ) {
    BufferedImage bufferedImage = null;
    try {
      URL url = new URL(imageURL);
      bufferedImage = ImageIO.read(url);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (bufferedImage == null) {
      return null;
    }

    return frameManager.create(
        UUID.randomUUID().toString(),
        bufferedImage,
        columns,
        rows
    );
  }

  public void registerEvents() {
    frameEventHandler.register(
        FrameClickEvent.class,
        event -> {
          Player player = event.getPlayer();
          FrameView view = event.getView();
          player.sendMessage("clicked on frame with id = " + view.getFrame().getId());
        }
    );
    frameEventHandler.register(
        FrameLookAtEvent.class,
        event -> {
          Player player = event.getPlayer();
          player.playEffect(event.getLookingAt(), Effect.COLOURED_DUST, 1);
        }
    );
  }

  @EventHandler
  public void onPlayerJoinEvent(PlayerJoinEvent event) {
    injector.inject(event.getPlayer());
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

    frameViewRegistry.register(
        frame.getId(),
        view
    );
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
