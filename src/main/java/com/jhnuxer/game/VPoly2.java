package com.jhnuxer.game;

import java.awt.*;
import java.util.*;

public class VPoly2 {
  ArrayList<Vec2> verts = new ArrayList<Vec2>();

  public VPoly2(Vec2...verts) {
    this(Arrays.asList(verts));
  }
  public VPoly2(Collection<Vec2> verts) {
    this.verts.addAll(verts);
  }
  public VPoly2(float[][] verts) {
    for (float[] v : verts) {
      this.verts.add(new Vec2(v[0],v[1]));
    }
  }
  public VPoly2() { }

  public VPoly2 copy() {
    VPoly2 vp = new VPoly2();
    for (int i = 0; i < verts.size(); i++) {
      vp.verts.add(verts.get(i).copy());
    }
    return vp;
  }

  public VPoly2 rotate(float r) {
    for (int i = 0; i < verts.size(); i++) {
      Vec2 v = verts.get(i);
      verts.set(i,v.r(v.r()+r));
    }
    return this;
  }
  public VPoly2 rotated(float r) {
    return copy().rotate(r);
  }
  public VPoly2 translate(Vec2 vo) {
    for (int i = 0; i < verts.size(); i++) {
      Vec2 v = verts.get(i);
      verts.set(i,v.add(vo));
    }
    return this;
  }
  public VPoly2 translated(Vec2 v) {
    return copy().translate(v);
  }
  public Polygon toPoly(Vec2 offs) {
    Polygon poly = new Polygon();
    for (int i = 0; i < verts.size(); i++) {
      Point p = verts.get(i).add(offs).round();
      poly.addPoint(p.x,p.y);
    }
    return poly;
  }

  public void addPoint(int x,int y) {
    addPoint((float)x,(float)y);
  }
  public void addPoint(float x,float y) {
    addPoint(new Vec2(x,y));
  }
  public void addPoint(Vec2 v) {
    verts.add(v);
  }
  public int vertCount() { return verts.size(); }
  public Vec2 getVert(int n) { return verts.get(n); }
  public Vec2[] verts() { return verts.toArray(new Vec2[0]); }

  public static VPoly2 gRegPoly(int sides,float r) {
    float q = ((float)(Math.PI*2))/(float)sides;
    Vec2 v = new Vec2(0F,r);
    VPoly2 poly = new VPoly2();
    System.out.println("GENERATING...");
    for (int i = 0; i < sides; i++) {
      Vec2 vi = v.r(q*i);
      System.out.println(vi);
      poly.addPoint(vi.copy());
    }
    System.out.println("CONFIRMING...");
    for (Vec2 vv : poly.verts()) {
      System.out.println(vv);
    }
    return poly;
  }
}
