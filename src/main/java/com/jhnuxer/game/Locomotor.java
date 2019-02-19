package com.jhnuxer.game;

import com.jhnuxer.game.entity.*;

public abstract class Locomotor {
  Entity ent;

  public Locomotor(Entity ent) {
    this.ent = ent;
  }

  public abstract void tick();
}
