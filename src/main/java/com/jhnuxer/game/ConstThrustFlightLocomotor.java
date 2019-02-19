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
    ent.move(ent.getBearing().m(speed));
  }

}
