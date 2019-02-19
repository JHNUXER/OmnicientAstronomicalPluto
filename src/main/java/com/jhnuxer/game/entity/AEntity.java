package com.jhnuxer.game.entity;

import java.awt.*;
import java.util.*;

import com.jhnuxer.game.*;
import com.jhnuxer.game.graphics.Graphics3D;

public abstract class AEntity implements Entity {
  Vec3 pos;
  Team team;
  Level level;
  String name;
  int slotSize = 1;
  float hp,mhp;
  ArrayList<String> kindOf = new ArrayList<String>();
  boolean marked = false;
  int dyingTime = 0;
  int dyingTimerLength = 0;

  public AEntity(Level l,Team t,float x,float y,float z,String name,String kindOf,float h) {
    this.level = l;
    this.team = t;
    this.pos = new Vec3(x,y,z);
    this.name = name;
    this.hp = (this.mhp = h);
    this.kindOf.addAll(Arrays.asList(kindOf.split(" ")));
  }

  // <HasPosition>
  public float getX() { return pos.x; }
  public float getY() { return pos.y; }
  public float getZ() { return pos.z; }
  public void setX(float x) { pos.x=x; }
  public void setY(float y) { pos.y=y; }
  // </HasPosition>
  // <HasHealth>
  public float getHealth() { return hp; }
  public void setHealth(float f) { hp = f; }
  public float getMaxHealth() { return mhp; }
  // </HasHealth>
  // <Entity>
  public void die() {
    hp = 0;
    dyingTime = dyingTimerLength;
    onDying();
  }
  public void draw(Graphics g,int offsx,int offsy) { }
  public void draw3D(Graphics3D g) { }
  public void markForDeletion() { marked = true; }
  public boolean isDying() { return dyingTime > 0; }
  public boolean isTypeOf(String s) { return kindOf.contains(s); }
  public boolean isMarkedForDeletion() { return marked; }
  public void onDie() {  }
  public void onDying() {  }
  public void onReceiveDamage(DamageSource source) { }
  // public Collisionable getCollisionBoundary() { }
  public Team getTeam() { return team; }
  public Level getLevel() { return level; }
  public String[] getTypeOf() { return kindOf.toArray(new String[0]); }
  public int getSlotCount() { return slotSize; }
  public String getName() { return name; }
  public void setTeam(Team t) { this.team = t; }
  public void setLevel(Level l) { this.level = l; }
  public void setTypeOf(String s,boolean b) {
    if (b) {
      kindOf.add(s);
    } else if (kindOf.contains(s)) {
      kindOf.remove(s);
    }
  }
  // </Entity>
}
