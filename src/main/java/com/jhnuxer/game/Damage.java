package com.jhnuxer.game;

import java.util.*;

public class Damage {

  HashMap<DamageType,Integer> values = new HashMap<DamageType,Integer>();

  public Damage() { }
  public Damage(String t,int v) {
    this(DamageType.valueOf(t),v);
  }
  public Damage(DamageType t,int v) {
    this();
    this.set(t,v);
  }

  public int get(DamageType t) { return values.containsKey(t) ? values.get(t) : 0 ; }
  public void set(DamageType t,int n) { values.put(t,n); }
  public DamageType[] types() {
    return new ArrayList<DamageType>(values.keySet()).toArray(new DamageType[0]);
  }

  public static DamageType type(String s) {
    return DamageType.valueOf(s);
  }
  public Damage copy() {
    Damage c = new Damage();
    for (DamageType t : types()) c.set(t,get(t));
    return c;
  }

  public Damage add(Damage b) {
    Damage c = new Damage();
    for (DamageType t : types()) c.set(t,get(t)+b.get(t));
    return c;
  }
  public Damage sub(Damage b) {
    Damage c = new Damage();
    for (DamageType t : types()) c.set(t,get(t)-b.get(t));
    return c;
  }

}
