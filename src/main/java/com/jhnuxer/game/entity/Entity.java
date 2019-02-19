package com.jhnuxer.game.entity;

import java.awt.*;

import com.jhnuxer.game.*;
import com.jhnuxer.game.graphics.*;

public interface Entity extends HasPosition,Tickable,HasHealth {

  // ACTIONS:
  public void die();
  public void draw(Graphics g,int offsx,int offsy);
  public void draw3D(Graphics3D g);
  public void markForDeletion();

  // CHECKS:
  public boolean isDying();
  public boolean isTypeOf(String s);
  public boolean isMarkedForDeletion();

  // EVENTS:
  public void onDie();
  public void onDying();
  public void onReceiveDamage(DamageSource source);

  // GETTERS:
  public Collisionable getCollisionBoundary();
  public Team getTeam();
  public Level getLevel();
  public String[] getTypeOf();
  public int getSlotCount();
  public String getName();

  // SETTERS:
  public void setTeam(Team t);
  public void setLevel(Level l);
  public void setTypeOf(String s,boolean b);



  public default Entity getClosestEnemy() {
    return getLevel().getClosestEnemy(this);
  }

}
