package com.jhnuxer.game;

public class Matrix3x1D {
  public double[] values = new double[3];

  public double get(int r,int c) { return values[r]; }
  public void set(int r,int c,double d) { values[r] = d; }

  public Vec3D toVec3D() { return new Vec3D(values[0],values[1],values[2]); }
  public Vec3 toVec3() { return toVec3D().toVec3(); }

  public Matrix3x1D(double[] v) {
    this.values = v;
  }
  public Matrix3x1D(double a,double b,double c) {
    this(new double[] { a,b,c });
  }
  public Matrix3x1D(Vec3D v) {
    this(v.x,v.y,v.z);
  }
  public Matrix3x1D(Vec3 v) {
    this((double)v.x,(double)v.y,(double)v.z);
  }

  public Matrix3x1D sub(Matrix3x1D b) {
    return new Matrix3x1D(toVec3D().sub(b.toVec3D()));
  }
}
