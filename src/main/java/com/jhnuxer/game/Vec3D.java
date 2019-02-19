package com.jhnuxer.game;

public class Vec3D {
  public double x,y,z;

  public double m() { return (double)Math.sqrt(x * x + y * y + z * z); }
  public Vec3D m(double f) {
    Vec3D r = r();
    this.x = f;
    this.y = 0;
    this.z = 0;
    return this.r(r);
  }
  public Vec3D r() { return new Vec3D(pitch(),yaw(),roll()); }
  public Vec3D r(Vec3D v) {
    this.pitch(v.x);
    this.yaw(v.y);
    return this.roll(v.z);
  }

  // pitch = atan2(sqrt(y^2+z^2),x);
  // yaw = atan2(sqrt(z^2+x^2),y);
  // roll = atan2(sqrt(x^2+y^2),z);

  public double pitch() { return Math.atan2(Math.sqrt(y*y+z*z),x); }
  public double yaw() { return Math.atan2(Math.sqrt(z*z+x*x),y); }
  public double roll() { return Math.atan2(Math.sqrt(x*x+y*y),z); }
  public Vec3D pitch(double p) {
    double m = m();
    this.y = m * Math.cos(p);
    this.z = m * Math.sin(p);
    return this;
  }
  public Vec3D yaw(double y) {
    double m = m();
    this.x = m * Math.cos(y);
    this.z = m * Math.sin(y);
    return this;
  }
  public Vec3D roll(double r) {
    double m = m();
    this.x = m * Math.cos(r);
    this.y = m * Math.sin(r);
    return this;
  }

  public Vec3D() { this(0,0,0); }
  public Vec3D(long x,long y,long z) { this((double)x,(double)y,(double)z); }
  public Vec3D(double x,double y,double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vec3D copy() { return new Vec3D(x,y,z); }

  public Vec3D setBearing(Vec3D b) {
    return this.r(b.r());
  }

  public Vec3D add(Vec3D b) {
    Vec3D c = copy();
    c.x += b.x;
    c.y += b.y;
    c.z += b.z;
    return c;
  }
  public Vec3D add(double b) {
    return copy().m(m()+b);
  }
  public Vec3D sub(Vec3D b) {
    Vec3D c = copy();
    c.x -= b.x;
    c.y -= b.y;
    c.z -= b.z;
    return c;
  }
  public Vec3D sub(double b) {
    return copy().m(m()-b);
  }
  public Vec3D mul(double b) {
    return copy().m(m()*b);
  }
  public Vec3D div(double b) {
    return copy().m(m()/b);
  }
  public double dot(Vec3D b) {
    return x*b.x+y*b.y+z*b.z;
  }
  public Vec3D cross(Vec3D q) {
    double a = y*q.z-z*q.y;
    double b = z*q.x-x*q.z;
    double c = x*q.y-y*q.x;
    return new Vec3D(a,b,c);
  }

  public Vec3 toVec3() {
    return new Vec3((float)x,(float)y,(float)z);
  }

  public double distFrom(Vec3D b) {
    return (double)Math.abs(b.sub(this).m());
  }
  // public Polong3 round() {
  //   return new Polong3(
  //     Math.round(x),
  //     Math.round(y),
  //     Math.round(z)
  //   );
  // }
}
