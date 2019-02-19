package com.jhnuxer.game;

import java.util.*;

public class Vec2 {
  public float x,y;

  public Vec2(float x,float y) {
    this.x = x;
    this.y = y;
  }
  public Vec2(int x,int y) {
    this.x = x;
    this.y = y;
  }
  public Vec2(long l) {
    this.x = Float.intBitsToFloat((int)((l&0xFFFFFFFF00000000L)>>>32));
    this.y = Float.intBitsToFloat((int) (l&0xFFFFFFFF));
  }

  public long toLongBits() {
    return ((((long)x)<<32) | ((long)y));
  }

  public float r() { return (float)Math.atan2(y,x); }
  public Vec2 r(float r) {
    float m = m();
    this.x = (float) (Math.cos(r)*m);
    this.y = (float) (Math.sin(r)*m);
    return this;
  }
  public float d() { return (float)((r()/Math.PI)*180F); }
  public Vec2 d(float d) {
    return r((d/180F)*((float)Math.PI));
  }
  public float m() { return (float)Math.sqrt( x*x + y*y ); }
  public Vec2 m(float m) {
    float r = r();
    this.x = m;
    this.y = 0F;
    return this.r(r);
  }

  public int[] ints() { return new int[] { Math.round(x),Math.round(y) }; }

  public Vec2 copy() { return new Vec2(x,y); }

  public String toString() { return "Vec2<"+x+","+y+">"; }

  public Vec2 add(Vec2 b) {
    Vec2 c = copy();
    c.x += b.x;
    c.y += b.y;
    return c;
  }
  public Vec2 add(float b) { return copy().m(m()+b); }
  public Vec2 add(int b) { return add((float)b); }
  public Vec2 sub(Vec2 b) {
    Vec2 c = copy();
    c.x -= b.x;
    c.y -= b.y;
    return c;
  }
  public Vec2 sub(float b) { return copy().m(m()-b); }
  public Vec2 sub(int b) { return sub((float)b); }
  public Vec2 mul(float b) { return copy().m(m()*b); }
  public Vec2 mul(int b) { return mul((float)b); }
  public Vec2 div(float b) { return copy().m(m()/b); }
  public Vec2 div(int b) { return div((float)b); }

  public java.awt.Point round() {
    return new java.awt.Point(Math.round(x),Math.round(y));
  }

  public float dot(Vec2 b) {
    return this.x*b.x+this.y*b.y;
  }

  public float angleFrom(Vec2 b) {
    float num = this.dot(b);
    float den = this.m()*b.m();
    return (float)Math.acos(num/den);
  }

  public float distFrom(Vec2 b) {
    return sub(b).m();
  }
}
