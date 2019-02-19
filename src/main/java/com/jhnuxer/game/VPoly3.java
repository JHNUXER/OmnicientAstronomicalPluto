package com.jhnuxer.game;

import java.awt.*;
import java.util.*;

public class VPoly3 {
  ArrayList<Vec3> verts = new ArrayList<Vec3>();

  public VPoly3(Vec3...verts) {
    this(Arrays.asList(verts));
  }
  public VPoly3(Collection<Vec3> verts) {
    this.verts.addAll(verts);
  }
  public VPoly3(float[][] verts) {
    for (float[] v : verts) {
      this.verts.add(new Vec3(v[0],v[1],v[2]));
    }
  }
  public VPoly3(VPoly2 poly,float z) {
    for (int i = 0; i < poly.vertCount(); i++) {
      Vec2 v0 = poly.getVert(i);
      addPoint(v0.x,v0.y,z);
    }
  }
  public VPoly3() { }

  public VPoly3 copy() {
    VPoly3 vp = new VPoly3();
    for (int i = 0; i < verts.size(); i++) {
      vp.verts.add(verts.get(i).copy());
    }
    return vp;
  }

  public VPoly3 rotateX(float r) {
    for (int i = 0; i < verts.size(); i++) {
      Vec3 v = verts.get(i);
      verts.set(i,v.pitch(v.pitch()+r));
    }
    return this;
  }
  public VPoly3 rotateY(float r) {
    for (int i = 0; i < verts.size(); i++) {
      Vec3 v = verts.get(i);
      verts.set(i,v.yaw(v.yaw()+r));
    }
    return this;
  }
  public VPoly3 rotateZ(float r) {
    for (int i = 0; i < verts.size(); i++) {
      Vec3 v = verts.get(i);
      verts.set(i,v.roll(v.roll()+r));
    }
    return this;
  }
  public VPoly3 rotatedX(float r) {
    return copy().rotateX(r);
  }
  public VPoly3 rotatedY(float r) {
    return copy().rotateY(r);
  }
  public VPoly3 rotatedZ(float r) {
    return copy().rotateZ(r);
  }
  public VPoly3 translate(Vec3 vo) {
    for (int i = 0; i < verts.size(); i++) {
      Vec3 v = verts.get(i);
      verts.set(i,v.add(vo));
    }
    return this;
  }
  public VPoly3 translated(Vec3 v) {
    return copy().translate(v);
  }
  // public Polygon toPoly(Vec3 offs) {
  //   Polygon poly = new Polygon();
  //   for (int i = 0; i < verts.size(); i++) {
  //     Point p = verts.get(i).add(offs).round();
  //     poly.addPoint(p.x,p.y);
  //   }
  //   return poly;
  // }

  public void addPoint(int x,int y,int z) {
    addPoint((float)x,(float)y,(float)z);
  }
  public void addPoint(float x,float y,float z) {
    addPoint(new Vec3(x,y,z));
  }
  public void addPoint(Vec3 v) {
    verts.add(v);
  }
  public int vertCount() { return verts.size(); }
  public Vec3 getVert(int n) { return verts.get(n); }
  public Vec3[] verts() { return verts.toArray(new Vec3[0]); }

  // public static VPoly3 gRegPoly(int sides,float r) {
  //   float q = ((float)(Math.PI*2))/(float)sides;
  //   Vec3 v = new Vec3(0F,r);
  //   VPoly3 poly = new VPoly3();
  //   System.out.println("GENERATING...");
  //   for (int i = 0; i < sides; i++) {
  //     Vec3 vi = v.r(q*i);
  //     System.out.println(vi);
  //     poly.addPoint(vi.copy());
  //   }
  //   System.out.println("CONFIRMING...");
  //   for (Vec3 vv : poly.verts()) {
  //     System.out.println(vv);
  //   }
  //   return poly;
  // }
}
