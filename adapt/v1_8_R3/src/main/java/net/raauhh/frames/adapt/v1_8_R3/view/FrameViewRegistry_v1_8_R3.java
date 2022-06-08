package net.raauhh.frames.adapt.v1_8_R3.view;

import net.raauhh.frames.api.render.FrameRenderer;
import net.raauhh.frames.api.view.FrameView;
import net.raauhh.frames.api.view.FrameViewRegistry;
import org.bukkit.World;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static net.raauhh.frames.adapt.v1_8_R3.util.TrackerEntries.addEntryToWorld;
import static net.raauhh.frames.adapt.v1_8_R3.util.TrackerEntries.removeEntryFromWorld;

public class FrameViewRegistry_v1_8_R3 implements FrameViewRegistry {

  private final FrameRenderer frameRenderer;
  private final Map<String, FrameEntityTrackerEntry> entries = new ConcurrentHashMap<>();

  public FrameViewRegistry_v1_8_R3(FrameRenderer frameRenderer) {
    this.frameRenderer = frameRenderer;
  }

  @Override
  public boolean register(String id, FrameView view) {
    FrameEntityTrackerEntry entry = new FrameEntityTrackerEntry(view, frameRenderer);
    boolean added = addEntryToWorld(entry, view.getLocation().getWorld());
    if (added) {
      entries.put(id, entry);
    }
    return added;
  }

  @Override
  public boolean unregister(String id) {
    FrameEntityTrackerEntry entry = entries.get(id);
    if (entry == null) {
      return false;
    }

    FrameView view = entry.getView();
    World world = view.getLocation().getWorld();

    boolean removed = removeEntryFromWorld(entry, world);
    if (removed) {
      entries.remove(id);
    }
    return removed;
  }

  @Override
  public FrameView getView(String id) {
    FrameEntityTrackerEntry entry = entries.get(id);
    return entry != null ? entry.getView() : null;
  }

  @Override
  public Collection<FrameView> getViews() {
    return entries.values()
        .stream()
        .map(FrameEntityTrackerEntry::getView)
        .collect(Collectors.toSet());
  }
}
