package net.raauhh.frames.api.impl;

import net.raauhh.frames.api.intercept.PacketInterceptor;
import net.raauhh.frames.api.intercept.PacketInterceptorRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultPacketInterceptorRegistry implements PacketInterceptorRegistry {

  private final Map<Class<?>, PacketInterceptor<?>> interceptors = new ConcurrentHashMap<>();

  @Override
  public void register(Class<?> packetType, PacketInterceptor<?> interceptor) {
    interceptors.put(packetType, interceptor);
  }

  @Override
  public void unregister(Class<PacketInterceptor<?>> packetType) {
    interceptors.remove(packetType);
  }

  @Override
  public PacketInterceptor<?> getInterceptorFor(Class<?> packetType) {
    return interceptors.get(packetType);
  }
}
