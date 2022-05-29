package net.raauhh.frames.api;

import net.raauhh.frames.api.util.Vector2D;

public interface FramePart {

  /**
   * The id of the respective item frame.
   * <p>
   * If the current frame part was not spawned yet
   * the frame part id will be -1
   */
  int getId();

  /**
   * Sets the current frame part id, it is usually used
   * when the frame part is spawned then the same item
   * frame id is assigned to the frame part.
   */
  void setId(int id);

  /**
   * @return the vector of the frame part, it is usually
   * used to locate a frame part in a 2D environment.
   */
  Vector2D getVector();

  /**
   * @return the pixels of the image of this current frame part.
   */
  byte[] getPixels();

}
