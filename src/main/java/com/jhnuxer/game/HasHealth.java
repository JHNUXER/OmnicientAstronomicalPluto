package com.jhnuxer.game;

public interface HasHealth {
  public float getHealth();
  public void setHealth(float hp);
  public float getMaxHealth();

  public default void takeDamage(float f) { setHealth(getHealth()-f); }
  public default void takeDamage(DamageType t,float f) { takeDamage(f); }
  public default void takeDamage(String s,float f) { takeDamage(Damage.type(s),f); }
  public default void takeDamage(Damage dmg) {
    for (DamageType t : dmg.types()) takeDamage(t,dmg.get(t));
  }
}
