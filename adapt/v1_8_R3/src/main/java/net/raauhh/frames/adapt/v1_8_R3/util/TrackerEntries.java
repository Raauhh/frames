package net.raauhh.frames.adapt.v1_8_R3.util;

import net.minecraft.server.v1_8_R3.EntityTracker;
import net.minecraft.server.v1_8_R3.EntityTrackerEntry;
import net.minecraft.server.v1_8_R3.WorldServer;
import net.raauhh.frames.adapt.v1_8_R3.view.FrameEntityTrackerEntry;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import java.lang.reflect.Field;
import java.util.Set;

public final class TrackerEntries {

  private static final Field ENTRIES_FIELD;

  static {
    try {
      ENTRIES_FIELD = EntityTracker.class.getDeclaredField("c");
      ENTRIES_FIELD.setAccessible(true);
    } catch (NoSuchFieldException e) {
      throw new IllegalStateException("Cannot find field 'c' from EntityTracker class", e);
    }
  }


  private TrackerEntries() {
    throw new IllegalStateException("Cannot instantiate utility class!");
  }

  public static boolean addEntryToWorld(FrameEntityTrackerEntry entry, World world) {
    WorldServer worldServer = ((CraftWorld) world).getHandle();
    EntityTracker tracker = worldServer.tracker;

    try {
      Set<EntityTrackerEntry> entries = (Set<EntityTrackerEntry>) ENTRIES_FIELD.get(tracker);
      entry.scanPlayers(worldServer.players);
      return entries.add(entry);
    } catch (IllegalAccessException e) {
      return false;
    }
  }

  public static boolean removeEntryFromWorld(FrameEntityTrackerEntry entry, World world) {
    WorldServer worldServer = ((CraftWorld) world).getHandle();
    EntityTracker tracker = worldServer.tracker;

    try {
      Set<EntityTrackerEntry> entries = (Set<EntityTrackerEntry>) ENTRIES_FIELD.get(tracker);
      return entries.remove(entry);
    } catch (IllegalAccessException e) {
      return false;
    }
  }

}
