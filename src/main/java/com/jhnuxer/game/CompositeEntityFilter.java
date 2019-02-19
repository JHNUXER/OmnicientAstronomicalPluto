package com.jhnuxer.game;

import com.jhnuxer.game.entity.*;

public class CompositeEntityFilter implements EntityFilter {
  EntityFilter[] filters;

  public CompositeEntityFilter(EntityFilter[] filters) {
    this.filters = filters;
  }

  public boolean allow(Entity ent) {
    boolean b = true;
    for (EntityFilter f : filters) b &= f.allow(ent);
    return b;
  }
}
