package net.raauhh.frames;

import net.raauhh.frames.api.Frame;
import net.raauhh.frames.api.FramePart;
import net.raauhh.frames.api.event.FrameEventHandler;
import net.raauhh.frames.api.event.types.FrameLookAtEvent;
import net.raauhh.frames.api.util.Locations;
import net.raauhh.frames.api.view.FrameView;
import net.raauhh.frames.api.view.FrameViewRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FrameLookAtTask extends BukkitRunnable {

  private final FrameViewRegistry viewRegistry;
  private final FrameEventHandler eventHandler;

  public FrameLookAtTask(
      FrameViewRegistry viewRegistry,
      FrameEventHandler eventHandler
  ) {
    this.viewRegistry = viewRegistry;
    this.eventHandler = eventHandler;
  }

  @Override
  public void run() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      for (FrameView view : viewRegistry.getViews()) {
        Frame frame = view.getFrame();
        Location original = view.getLocation();
        if (original.distance(player.getLocation()) > frame.getParts().length) {
          continue;
        }

        for (FramePart part : frame.getParts()) {
          Location partLocation = original.clone();
          part.getVector().apply(partLocation, view.getFacing());

          if (partLocation.distance(player.getLocation()) > 4
              || !Locations.isLookingAt(player, partLocation)) {
            continue;
          }

          eventHandler.call(
              new FrameLookAtEvent(
                  player,
                  view,
                  partLocation
              )
          );
        }
      }
    }
  }
}
