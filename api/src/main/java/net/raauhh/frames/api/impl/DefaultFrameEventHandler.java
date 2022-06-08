package net.raauhh.frames.api.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.raauhh.frames.api.event.FrameEventHandler;
import net.raauhh.frames.api.event.FrameEvent;

import java.util.function.Consumer;

public class DefaultFrameEventHandler implements FrameEventHandler {

  private final Multimap<Class<? extends FrameEvent>, Consumer<FrameEvent>> events = HashMultimap.create();

  @Override
  public <T extends FrameEvent> void register(Class<T> type, Consumer<T> consumer) {
    events.put(type, (Consumer<FrameEvent>) consumer);
  }

  @Override
  public <T extends FrameEvent> void call(T event) {
    for (Consumer<FrameEvent> consumer : events.get(event.getClass())) {
      consumer.accept(event);
    }
  }

  @Override
  public void unregister(Consumer<FrameEvent> event) {
    events.remove(event.getClass(), event);
  }

  @Override
  public void unregisterAll(Class<? extends FrameEvent> type) {
    events.removeAll(type);
  }

  @Override
  public void unregisterAll() {
    events.clear();
  }
}
