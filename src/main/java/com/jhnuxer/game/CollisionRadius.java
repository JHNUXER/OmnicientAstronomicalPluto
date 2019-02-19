package com.jhnuxer.game;

import com.jhnuxer.game.entity.Entity;

public class CollisionRadius implements Collisionable {
  Entity ent;
  float r;

  public CollisionRadius(Entity ent,float r) {
    this.ent = ent;
    this.r = r;
  }

  public boolean overlaps(Vec2 f) { return ent.getPos2().distFrom(f) <= r; }
  public boolean overlaps(Vec3 f) { return ent.getPos3().distFrom(f) <= r; }
  public Vec3 getPosition() { return ent.getPos3(); }
}
