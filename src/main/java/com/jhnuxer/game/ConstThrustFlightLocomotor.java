package com.jhnuxer.game;

import com.jhnuxer.game.entity.Entity;

/**
 * Used for airplanes and missiles
 *
 * @author JHNuxer
 */
public class ConstThrustFlightLocomotor extends Locomotor {

  float speed;
  float targetHeight;

  public ConstThrustFlightLocomotor(Entity ent,float speed) {
    super(ent);
    this.speed = speed;
  }

  public void tick() {
    if (ent.getBearing().x == Float.NaN || ent.getBearing().y == Float.NaN) {
      ent.setBearing(new Vec2(0,1).d(0));
    }
    Vec2 vel = new Vec2(speed,0);
    vel.d(ent.getBearing().d()-90);
    if (vel.x == Float.NaN || vel.y == Float.NaN) {
      System.out.println("ADJUSTING...");
      ent.setBearing(new Vec2(0,1).d(0));
      vel = new Vec2(0,1).d(0).m(speed);
    }
    System.out.println(vel);
    ent.move(vel);
    // ent.move(ent.getBearing().m(speed));
  }

}
