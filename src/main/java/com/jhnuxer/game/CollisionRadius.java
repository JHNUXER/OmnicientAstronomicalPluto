package com.jhnuxer.game;

public class CollisionRadius implements Collisionable {
  Entity ent;
  float r;

  public boolean overlaps(Vec3 f) { return ent.getPos3().distFrom(f) <= r; }
}
