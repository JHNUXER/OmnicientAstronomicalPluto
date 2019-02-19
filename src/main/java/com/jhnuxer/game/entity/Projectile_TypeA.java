package com.jhnuxer.game.entity;

import java.awt.*;

import com.jhnuxer.game.*;
import com.jhnuxer.game.graphics.Graphics3D;

public abstract class Projectile_TypeA implements Entity {
  boolean marked = false;
  Vec3 pos,vel,start;
  Team team;
  Level level;
  float tDist;

  public Projectile_TypeA(Level l,Team t,Vec3 p,Vec3 tp,float speed) {
    this.level = l;
    this.team = t;
    this.pos = p.copy();
    this.start = pos.copy();
    this.vel = tp.sub(pos);
    this.tDist = vel.m();
    this.vel = this.vel.m(speed);
  }

  public abstract void onDistReached();

  public float getX() { return pos.x; }
  public float getY() { return pos.y; }
  public float getZ() { return pos.z; }
  public void setX(float n) { pos.x=n; }
  public void setY(float n) { pos.y=n; }
  public void setZ(float n) { pos.z=n; }
  public float getHealth() { return 1; }
  public void setHealth(float hp) { }
  public float getMaxHealth() { return 1; }
  public void die() { }
  public void draw(Graphics g,int offsx,int offsy) {
    g.setColor(team.getColor());
  }
  public void draw3D(Graphics3D g) { }
  public void markForDeletion() { marked = true; }
  public boolean isDying() { return false; }
  public boolean isTypeOf(String s) { return false; }
  public boolean isMarkedForDeletion() { return marked; }
  public void onDie() { }
  public void onDying() { }
  public void onReceiveDamage(DamageSource source) { }
  public Collisionable getCollisionBoundary() { return null; }
  public Team getTeam() { return team; }
  public Level getLevel() { return level; }
  public String[] getTypeOf() { return "PROJECTILE".split(" "); }
  public int getSlotCount() { return 0; }
  public String getName() { return "PROJECTILE"; }
  public void setTeam(Team t) { }
  public void setLevel(Level l) { }
  public void setTypeOf(String s,boolean b) { }
  public void tick() {
    pos = pos.add(vel);
    if (distFrom(start) >= tDist) {
      onDistReached();
      getLevel().removeEntity(this);
    }
  }
}
