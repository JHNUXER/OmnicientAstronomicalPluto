package com.jhnuxer.game;

public interface Weapon {

  /**
   * Determines the maximum distance that there can be between
   * the entity wielding this weapon and the target location for
   * the user to be able to "fire" it.
   *
   * @return the maximum range of this weapon
   */
  public float getMaxRange();

  /**
   * Determines the minimum distance that there can be between
   * the entity wielding this weapon and the target location for
   * the user to be able to "fire" it.
   *
   * @return the minimum range of this weapon
   */
  public float getMinRange();

  /**
   * The number of milliseconds it takes for this weapon
   * to reload
   *
   * @return the reload time of this weapon (ms)
   */
  public int getReloadTime();

  /**
   * The amount of time (ms) between shots when firing
   * this weapon
   *
   * @return the amount of time (ms) between shots when firing
   *         this weapon
   */
  public int getDelayBetweenShots();

  /**
   * The number of shots this weapon can fire before reloading
   *
   * @return The number of shots this weapon can fire before
   *         reloading
   */
  public int getClipSize();
  public Damage getPrimaryDamage();
  public Damage getSecondaryDamage();
  public float getPrimaryDamageRadius();
  public float getSecondaryDamageRadius();
  public boolean canAttackKindOf(String s);

}
