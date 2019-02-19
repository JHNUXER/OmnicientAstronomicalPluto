package com.jhnuxer.game;

import com.jhnuxer.game.entity.Entity;

public interface Collisionable {
  public boolean overlaps(Vec2 v);
  public boolean overlaps(Vec3 v);
  public Vec3 getPosition();

  public default boolean collidesWith(Entity e) {
    return overlaps(e.getPos3());
  }
}
