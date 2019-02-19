package com.jhnuxer.game;

import com.jhnuxer.game.entity.*;

public class EntityFilter_Radius3 implements EntityFilter {
  Vec3 pos;
  float r;

  public EntityFilter_Radius3(Vec3 pos,float r) {
    this.pos = pos;
    this.r = r;
  }

  public boolean allow(Entity ent) {
    return ent.distFrom(pos) <= r;
  }
}
