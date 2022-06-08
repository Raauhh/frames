package net.raauhh.frames.api.event;

import java.util.function.Consumer;

public interface FrameEventHandler {

  <T extends FrameEvent> void register(
      Class<T> type,
      Consumer<T> consumer
  );

  <T extends FrameEvent> void call(T event);

  void unregister(Consumer<FrameEvent> event);

  void unregisterAll(Class<? extends FrameEvent> type);

  void unregisterAll();

}
