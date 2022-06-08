package net.raauhh.frames.api.view;

import org.bukkit.Location;

public interface FrameViewRegistry {

  boolean register(FrameView view, Location location);

  boolean unregister(FrameView view);

}
