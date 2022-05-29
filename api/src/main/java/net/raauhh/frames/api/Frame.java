package net.raauhh.frames.api;

public interface Frame {

  /**
   * @return the frame id.
   */
  String getId();

  /**
   * @return the parts that make up this frame.
   */
  FramePart[] getParts();

}
