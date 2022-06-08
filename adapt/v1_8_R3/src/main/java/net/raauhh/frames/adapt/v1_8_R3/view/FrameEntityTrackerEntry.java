package net.raauhh.frames.adapt.v1_8_R3.view;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityTrackerEntry;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.raauhh.frames.adapt.v1_8_R3.util.Chunks;
import net.raauhh.frames.api.render.FrameRenderer;
import net.raauhh.frames.api.view.FrameView;

public class FrameEntityTrackerEntry extends EntityTrackerEntry {

  private static final Entity DUMMY = new Entity(null) {
    @Override protected void h() {}
    @Override protected void a(NBTTagCompound nbtTagCompound) {}
    @Override protected void b(NBTTagCompound nbtTagCompound) {}
  };

  private final FrameView view;
  private final FrameRenderer renderer;

  public FrameEntityTrackerEntry(
      FrameView view,
      FrameRenderer renderer
  ) {
    super(DUMMY, -1, -1, false);
    this.view = view;
    this.renderer = renderer;
  }

  @Override
  public void updatePlayer(EntityPlayer entityplayer) {
    if (!Chunks.isLoaded(entityplayer, view.getLocation())) {
      trackedPlayers.remove(entityplayer);
      renderer.hide(
          entityplayer.getBukkitEntity(),
          view.getFrame()
      );
      return;
    }

    if (trackedPlayers.add(entityplayer)) {
      renderer.renderFrame(
          entityplayer.getBukkitEntity(),
          view.getFrame(),
          view.getLocation(),
          view.getFacing()
      );
    }
  }

  public FrameView getView() {
    return view;
  }
}
