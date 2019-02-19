package com.jhnuxer.game.entity;

import java.awt.*;
import java.util.*;

import com.jhnuxer.game.*;
import com.jhnuxer.game.graphics.Graphics3D;

public class Person extends AEntity {
  public static final String KIND_OF = "INFANTRY CAN_MOVE";

  CollisionRadius cr;
  public Weapon weapon;

  public Person(Level l,Team t,int x,int y) {
    // Level l,Team t,float x,float y,float z,String name,String kindOf,float h
    super(l,t,x,y,0F,"HUMAN",KIND_OF,100F);
    cr = new CollisionRadius(this,3.5F);
  }

  public Collisionable getCollisionBoundary() {
    return cr;
  }

  public void tick() {
    if (weapon != null) {
      Entity ent = getClosestEnemy();
      if (ent != null) {
        float dist = ent.distFrom(this);
        if (weapon.isTargetInRange(getPos3(),ent.getPos3())) {
          if (weapon.canFire()) weapon.fire(getPos3(),ent);
        } else {
          move(ent.getPos2().sub(getPos2()).m(Math.min(2.4F,Math.max(0,dist-weapon.getMaxRange()))));
        }
      }
      weapon.tick();
    } else {
      move(RNG.vec2(1.2F));
    }
    final Person p = this;
    CompositeEntityFilter filter = new CompositeEntityFilter(new EntityFilter[] {
      new EntityFilter_Radius3(getPos3(),cr.r),
      new EntityFilter() {
        public boolean allow(Entity ent) { return ent != p; }
      }
    });
    for (Entity ent : level.scanEntities(new EntityFilter[] {filter})) {
      Vec2 vv = getPos2().sub(ent.getPos2());
      ent.move(vv);
    }
  }
}
