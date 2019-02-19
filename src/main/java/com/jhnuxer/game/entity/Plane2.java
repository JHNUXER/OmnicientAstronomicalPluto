package com.jhnuxer.game.entity;

public class Plane2 extends AEntity {
  public Collisionable getCollisionBoundary() { return new CollisionRadius(this,0F); }
}
