package net.raauhh.frames.api.view;

import net.raauhh.frames.api.Frame;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public interface FrameView {

  Frame getFrame();

  Location getLocation();

  BlockFace getFacing();

}
