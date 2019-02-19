package com.jhnuxer.game.entity;

import com.jhnuxer.game.*;

public interface Entity extends HasPosition,Tickable {

  // ACTIONS:
  public void die();

  // CHECKS:
  public boolean isDying();

  // EVENTS:
  public void onDie();
  public void onDying();
  public void onReceiveDamage(DamageSource source);

  // GETTERS:
  public Collisionable getCollisionBoundary();

  // SETTERS:
  public void setLevel();

}
