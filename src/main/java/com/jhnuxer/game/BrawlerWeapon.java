package com.jhnuxer.game;

import com.jhnuxer.game.entity.*;

public class BrawlerWeapon implements Weapon {

  int fd = 0;

  public float getMaxRange() { return 6F; }
  public float getMinRange() { return 0F; }
  public int getReloadTime() { return 0; }
  public int getDelayBetweenShots() { return 10; }
  public int getClipSize() { return 1; }
  public Damage getPrimaryDamage() { return new Damage(); }
  public Damage getSecondaryDamage() { return new Damage(); }
  public float getPrimaryDamageRadius() { return 0F; }
  public float getSecondaryDamageRadius() { return 0F; }
  public boolean canAttackKindOf(String s) { return !s.equals("AIRCRAFT"); }
  public boolean canFire() { return fd == 0; }
  public void fire(Vec3 p,Entity targ) {
    targ.takeDamage(3);
    fd = 10;
  }
  public void tick() {
    if (fd > 0) fd--;
  }

}
