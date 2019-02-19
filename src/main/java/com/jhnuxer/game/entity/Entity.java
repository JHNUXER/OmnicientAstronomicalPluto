package com.jhnuxer.game.entity;

import com.jhnuxer.game.*;

public interface Entity extends HasPosition,Tickable {

  // ACTIONS:
  public void die();

  // CHECKS:
  public boolean isDying();
  public boolean isTypeOf(String s);

  // EVENTS:
  public void onDie();
  public void onDying();
  public void onReceiveDamage(DamageSource source);

  // GETTERS:
  public Collisionable getCollisionBoundary();
  public Team getTeam();
  public Level getLevel();
  public String[] getTypeOf();

  // SETTERS:
  public void setTeam(Team t);
  public void setLevel(Level l);
  public void setTypeOf(String s,boolean b);

}
