package net.raauhh.frames.api.view;

import net.raauhh.frames.api.Frame;

import java.util.Collection;
import java.util.UUID;

public interface FrameView {

  Frame getFrame();

  Collection<UUID> getViewers();

}
