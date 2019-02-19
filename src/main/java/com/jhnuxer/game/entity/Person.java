package com.jhnuxer.game.entity;

import java.awt.*;
import java.util.*;

import com.jhnuxer.game.*;
import com.jhnuxer.game.graphics.Graphics3D;

public class Person extends AEntity {
  public static final String KIND_OF = "INFANTRY CAN_MOVE";

  CollisionRadius cr;

  public Person(Level l,Team t,int x,int y) {
    // Level l,Team t,float x,float y,float z,String name,String kindOf,float h
    super(l,t,x,y,0F,"HUMAN",KIND_OF,100F);
    cr = new CollisionRadius(this,3.5F);
  }

  public Collisionable getCollisionBoundary() {
    return cr;
  }

  public void tick() {
    move(RNG.vec2(1.2F));
  }
}
