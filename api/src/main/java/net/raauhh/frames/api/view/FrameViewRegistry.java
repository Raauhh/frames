package net.raauhh.frames.api.view;

import java.util.Collection;

public interface FrameViewRegistry {

  boolean register(String id, FrameView view);

  boolean unregister(String id);

  FrameView getView(String id);

  Collection<FrameView> getViews();
}
