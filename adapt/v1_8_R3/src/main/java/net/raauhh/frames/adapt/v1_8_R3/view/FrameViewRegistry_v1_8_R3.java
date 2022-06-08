package net.raauhh.frames.adapt.v1_8_R3.view;

import net.raauhh.frames.api.render.FrameRenderer;
import net.raauhh.frames.api.view.FrameView;
import net.raauhh.frames.api.view.FrameViewRegistry;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static net.raauhh.frames.adapt.v1_8_R3.util.TrackerEntries.addEntryToWorld;
import static net.raauhh.frames.adapt.v1_8_R3.util.TrackerEntries.removeEntryFromWorld;

public class FrameViewRegistry_v1_8_R3 implements FrameViewRegistry {

  private final FrameRenderer frameRenderer;
  private final Map<UUID, FrameEntityTrackerEntry> entries = new ConcurrentHashMap<>();

  public FrameViewRegistry_v1_8_R3(FrameRenderer frameRenderer) {
    this.frameRenderer = frameRenderer;
  }

  @Override
  public boolean register(Player player, FrameView view) {
    FrameEntityTrackerEntry entry = new FrameEntityTrackerEntry(view, frameRenderer);
    boolean added = addEntryToWorld(entry, view.getLocation().getWorld());
    if (added) {
      entries.put(player.getUniqueId(), entry);
    }
    return added;
  }


  @Override
  public boolean unregister(Player player) {
    FrameEntityTrackerEntry entry = entries.get(player.getUniqueId());
    if (entry == null) {
      return false;
    }

    FrameView view = entry.getView();
    World world = view.getLocation().getWorld();

    boolean removed = removeEntryFromWorld(entry, world);
    if (removed) {
      entries.remove(player.getUniqueId());
    }
    return removed;
  }
}
