package com.jhnuxer.game.entity;

import java.awt.*;

import com.jhnuxer.game.*;
import com.jhnuxer.game.graphics.*;

public class Plane extends AEntity implements Collisionable {
  public static final float SQRT3 = (float)Math.sqrt(3);

  static float fHeight = 100F;
  static float speed = 8F;

  VPoly2 poly;
  Vec2 bearing;
  Vec2 moveTarg;
  float gunRange = 240F;
  float maxRotPerTick = (float)((24F/180F)*Math.PI);
  int delayF = 0;
  int[] fireDelays = { 0,0,0,0 };
  int mgFireDelay = 0;
  float range = 400F;
  int currWeap = 0;
  int fallTimeFactor = 1;
  CollisionRadius colR = new CollisionRadius(this,22.5F);

  public Plane(Level l,Team f,float x,float y,float z) {
    //Level l,Team t,float x,float y,float z,String name,String kindOf,float h
    super(l,f,x,y,z,"PLANE","AIRCRAFT CAN_ATTACK",320F);
    bearing = new Vec2(-1,0).d(0F);
    poly = new VPoly2(new Vec2(0F,-22.5F),new Vec2(14F,22.5F),new Vec2(-14F,22.5F))/*.rotate(0.5F*(float)Math.PI)*/;
    moveTarg = getPos2().copy();
    locomotor = new ConstThrustFlightLocomotor(this,speed);
  }

  public VPoly2 setupGunSpreadPoly() {
    Vec2 ab = getPos().add(gunRange);
    Vec2 a = ab.copy().r(ab.d()-3+90);
    Vec2 b = ab.copy().r(ab.d()+3+90);
    Vec2 c = getPos().sub(22.5F);
    return new VPoly2(a,b,c);
  }

  public float calculateNeededRotation() {
    float dist = moveTarg.distFrom(getPos());
    Vec2 v = bearing.m(dist).add(getPos());
    return v.angleFrom(moveTarg);
  }
  public void doRotationAdjusment() {
    float f = calculateNeededRotation();
    if (f < 0) f = Math.max(-maxRotPerTick,f);
    else if (f > 0) f = Math.min(maxRotPerTick,f);
    bearing.r(bearing.r()+f);
  }

  public Vec2 getBearing() { return bearing.copy(); }
  public void setBearing(Vec2 b) { bearing = b.copy().m(1F); }
  public void fire(int weap,Entity e) {
    Vec2 q = e.getPos().sub(getPos()).m(1F);
    // Missile fb = new Missile(getLevel(),getTeam(),getX(),getY(),q,e);
    // getLevel().addEntity(fb);
    fireDelays[weap] = 150;
    delayF = 15;
  }
  public void fireMG(Entity e) {
    // Bullet b = new Bullet(getLevel(),getTeam(),getX(),getY(),e.getX(),e.getY(),"4d6 + d9");
    // getLevel().addEntity(b);
    mgFireDelay = 10;
  }
  public void fireNext(Entity e) {
    fire(currWeap++,e);
    if (currWeap == fireDelays.length) currWeap = 0;
  }
  public boolean canFireNext() {
    return canFire(currWeap);
  }
  public boolean canFire(int weap) {
    return fireDelays[weap] == 0 && delayF == 0;
  }
  public boolean canFireMG() { return mgFireDelay < 1; }
  public void tickFireDelays() {
    for (int i = 0; i < fireDelays.length; i++) {
      if (fireDelays[i] > 0) fireDelays[i]--;
    }
    if (delayF > 0) delayF--;
    if (mgFireDelay > 0) mgFireDelay--;
  }

  public float getDrawAlt() {
    // return (getZ()/SQRT3)*2F;
    return getZ();
  }

  public boolean overlaps(Vec2 v) { return poly.rotated(bearing.r()).toPoly(getPos()).contains(v.round()); }
  public boolean overlaps(Vec3 v) { return poly.rotated(bearing.r()).toPoly(getPos()).contains(Math.round(v.x),Math.round(v.y)) && Math.abs(v.z - getZ()) <= 5; }
  public Vec3 getPosition() { return getPos3(); }

  @Override
  public void draw3D(Graphics3D g) {
    VPoly3 p = new VPoly3(poly,getZ()).rotatedZ(bearing.r()).rotatedX((30F/180F)*((float)Math.PI)).translate(new Vec3(getX(),getY(),0F));
    g.setColor(Color.BLACK);
    g.fillPolygon(p);
    g.setColor(getTeam().getColor());
    g.fillPolygon(p.translate(new Vec3(0F,-getDrawAlt(),0F)));
  }
  @Override
  public void draw(Graphics g,int ox,int oy) {
    g.setColor(Color.BLACK);
    Polygon polyg = poly.rotated(bearing.r()).translate(new Vec2(ox,oy)).toPoly(getPos());
    g.fillPolygon(polyg.xpoints,polyg.ypoints,polyg.npoints);
    polyg = poly.rotated(bearing.r()).translate(new Vec2(0F,-getDrawAlt()).add(new Vec2(ox,oy))).toPoly(getPos());
    g.setColor(getTeam().getColor());
    g.fillPolygon(polyg.xpoints,polyg.ypoints,polyg.npoints);
  }
  @Override
  public Collisionable getCollisionBoundary() {
    return this;
  }

  @Override
  public void tick() {
    System.out.println("X: "+getX()+" Y: "+getY()+" Z: "+getZ());
    if (!isDying() && distFrom(moveTarg) <= 3F) {
      bearing.d(bearing.d()+16);
    } else if (!isDying()) {
      Entity entt = getClosestEnemy();
      if (entt != null) {
        moveTarg = entt.getPos();
      }
      if (distFrom(moveTarg) > 3F) {
        doRotationAdjusment();
      }
      if (entt != null && setupGunSpreadPoly().toPoly(getPos()).contains(entt.getPos().round())) {
        if (canFireMG()) fireMG(entt);
      }
    }
    // Vec2 vel = new Vec2(speed,0);
    // vel.d(bearing.d()-90);
    // move(vel);
    AEntityTick();
    // Combat
    Entity ent = getClosestEnemy();
    if (ent != null) {
      if (canFireNext() && ent.distFrom(this) <= range) {
        fireNext(ent);
      }
    }
    tickFireDelays();
    if (getHealth() < 1 && !isDying()) {
      dyingTime = Math.round(getZ()*fallTimeFactor+RNG.i32(10,70));
      Person person = new Person(getLevel(),getTeam(),getX(),getY(),getZ());
      person.weapon = new BrawlerWeapon();
      // Parachute para = new Parachute(l,f,x,y,person);
      getLevel().addEntity(person);
    }
    if (dyingTime > 0) {
      dyingTime--;
      if (dyingTime % fallTimeFactor == 0 && getZ() > 0) setZ(getZ()-1);
      // for (Entity ent0 : l.getEntitiesInVPoly2(poly.rotated(bearing.r()),x,y)) {
      //   ent0.takeDamage(DamageType.CRUSHING,200);
      //   ent0.takeDamage(DamageType.CUTTING,200);
      //   ent0.takeDamage(DamageType.BLUNT,200);
      // }
    }
    if (dyingTime == 1) {
      // Fireball.FireballExplosion exp = new Fireball.FireballExplosion(l,f,x,y);
      // exp.mtc = 60;
      // getLevel().addEntity(exp);
      getLevel().removeEntity(this);
    }
  }
}
