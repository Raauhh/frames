package net.raauhh.frames.api;

import java.awt.image.BufferedImage;
import java.util.Collection;

public interface FrameManager {

  /**
   * Represents the maximum pixels that a map
   * in minecraft supports.
   */
  int PIXELS_PER_FRAME = 128;

  /**
   * Creates a new frame that contains the given
   * {@code image}.
   * <p>
   * The given {@code image} is scaled using the
   * given {@code columns} and {@code rows}.
   */
  Frame create(
      String id,
      BufferedImage image,
      int columns,
      int rows
  );

  /**
   * Same as {@link FrameManager#create(String, BufferedImage, int, int)}
   * but the columns and rows are being calculated using the
   * size of the image.
   */
  default Frame create(String id, BufferedImage image) {
    return create(
        id,
        image,
        image.getHeight() / PIXELS_PER_FRAME,
        image.getWidth() / PIXELS_PER_FRAME
    );
  }

  /**
   * Removes given {@code frame} from internal registry
   * for this frame manager.
   */
  void remove(Frame frame);

  /**
   * @return a collection containing all
   * loaded frames.
   */
  Collection<? extends Frame> getFrames();

  /**
   * Retrieves the frame that contains given
   * frame part id.
   *
   * @param id - the frame id
   * @return the frame who belongs given frame part id.
   */
  Frame getFrameByEntityId(int id);

}
