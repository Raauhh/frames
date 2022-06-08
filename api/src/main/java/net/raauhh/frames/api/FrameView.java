package net.raauhh.frames.api;

import java.util.Collection;
import java.util.UUID;

public interface FrameView {

  Frame getFrame();

  Collection<UUID> getViewers();

}
