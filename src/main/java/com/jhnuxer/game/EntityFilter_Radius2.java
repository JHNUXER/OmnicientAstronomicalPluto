package com.jhnuxer.game;

import com.jhnuxer.game.entity.*;

public class EntityFilter_Radius2 implements EntityFilter {
  Vec2 pos;
  float r;

  public EntityFilter_Radius2(Vec2 p,float r) {
    this.pos = p;
    this.r = r;
  }

  public boolean allow(Entity ent) {
    return ent.distFrom(pos) <= r;
  }
}
