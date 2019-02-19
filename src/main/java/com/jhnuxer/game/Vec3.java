package com.jhnuxer.game;

public class Vec3 {
  public float x,y,z;

  public float m() { return (float)Math.sqrt(x * x + y * y + z * z); }
  public Vec3 m(float f) {
    Vec3 r = r();
    this.x = f;
    this.y = 0;
    this.z = 0;
    return this.r(r);
  }
  public Vec3 r() { return new Vec3(pitch(),yaw(),roll()); }
  public Vec3 r(Vec3 v) {
    this.pitch(v.x);
    this.yaw(v.y);
    return this.roll(v.z);
  }

  // pitch = atan2(sqrt(y^2+z^2),x);
  // yaw = atan2(sqrt(z^2+x^2),y);
  // roll = atan2(sqrt(x^2+y^2),z);

  public float pitch() { return (float)Math.atan2(Math.sqrt(y*y+z*z),x); }
  public float yaw() { return (float)Math.atan2(Math.sqrt(z*z+x*x),y); }
  public float roll() { return (float)Math.atan2(Math.sqrt(x*x+y*y),z); }
  public Vec3 pitch(float p) {
    float m = m();
    this.y = m * (float)Math.cos(p);
    this.z = m * (float)Math.sin(p);
    return this;
  }
  public Vec3 yaw(float y) {
    float m = m();
    this.x = m * (float)Math.cos(y);
    this.z = m * (float)Math.sin(y);
    return this;
  }
  public Vec3 roll(float r) {
    float m = m();
    this.x = m * (float)Math.cos(r);
    this.y = m * (float)Math.sin(r);
    return this;
  }

  public Vec3() { this(0,0,0); }
  public Vec3(int x,int y,int z) { this((float)x,(float)y,(float)z); }
  public Vec3(float x,float y,float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vec3 copy() { return new Vec3(x,y,z); }

  public Vec3 add(Vec3 b) {
    Vec3 c = copy();
    c.x += b.x;
    c.y += b.y;
    c.z += b.z;
    return c;
  }
  public Vec3 add(float b) {
    return copy().m(m()+b);
  }
  public Vec3 sub(Vec3 b) {
    Vec3 c = copy();
    c.x -= b.x;
    c.y -= b.y;
    c.z -= b.z;
    return c;
  }
  public Vec3 sub(float b) {
    return copy().m(m()-b);
  }
  public Vec3 mul(float b) {
    return copy().m(m()*b);
  }
  public Vec3 div(float b) {
    return copy().m(m()/b);
  }
  public float dot(Vec3 b) {
    return x*b.x+y*b.y+z*b.z;
  }
  public Vec3 cross(Vec3 b) {
    return new Vec3(y*b.z-z*b.y,z*b.x-x*b.z,x*b.y-y*b.x);
  }

  public float distFrom(Vec3 b) {
    return (float)Math.abs(b.sub(this).m());
  }
  // public Point3 round() {
  //   return new Point3(
  //     Math.round(x),
  //     Math.round(y),
  //     Math.round(z)
  //   );
  // }
}
