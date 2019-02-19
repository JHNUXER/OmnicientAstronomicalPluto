package com.jhnuxer.game;

public interface HasPosition {
  public float getX();
  public float getY();
  public float getZ();
  public void setX(float n);
  public void setY(float n);
  public void setZ(float n);

  public default void addX(float x) { setX(getX()+x); }
  public default void addY(float y) { setY(getY()+y); }
  public default void addZ(float z) { setZ(getZ()+z); }
  public default void move(Vec2 v) { move(v.x,v.y); }
  public default void move(float x,float y) { addX(x); addY(y); }
  public default void move(int x,int y) { move((float)x,(float)y); }
  public default void move(float x,float y,float z) { move(x,y); addZ(z); }
  public default void move(Vec2 v,float z) { move(v); addZ(z); }
  public default void move(Vec3 v) { move(v.x,v.y,v.z); }
  public default void move(int x,int y,int z) { move(x,y); addZ((float)z); }
  public default Vec2 getPos2() { return new Vec2(getX(),getY()); }
  public default Vec3 getPos3() { return new Vec3(getX(),getY(),getZ()); }

  // DISTANCE:
  public default float distFrom(float x,float y,float z) {
    return dist(getX(),getY(),getZ(),x,y,z);
  }
  public default float distFrom(Vec3 v) {
    return distFrom(v.x,v.y,v.z);
  }
  public default float distFrom(HasPosition p) {
    return distFrom(p.getPos3());
  }

  public static float dist(float x1,float y1,float z1,float x2,float y2,float z2) {
    float dx = x2-x1;
    float dy = y2-y1;
    float dz = z2-z1;
    return (float)Math.sqrt(dx*dx+dy*dy+dz*dz);
  }
}
