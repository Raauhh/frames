package net.raauhh.frames.api.intercept;

public interface PacketInterceptorRegistry {

  void register(Class<?> packetType, PacketInterceptor<?> interceptor);

  void unregister(Class<PacketInterceptor<?>> packetType);

  PacketInterceptor<?> getInterceptorFor(Class<?> packetType);

}
