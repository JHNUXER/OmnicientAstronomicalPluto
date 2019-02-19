package com.jhnuxer.game;

import java.util.*;

import com.jhnuxer.game.entity.*;

public class Level implements Runnable {
  public static final java.text.DecimalFormat PFORMAT = new java.text.DecimalFormat("0.00");

  final Team neutralTeam;
  ArrayList<Team> teams = new ArrayList<Team>();
  ArrayList<Entity> entities = new ArrayList<Entity>();
  Thread thread = new Thread(this);
  boolean running = true;
  long lt = 0;
  long lo = 0;
  long ltt = 0;
  long delay = 1000000000/100;

  public Level() {
    neutralTeam = new Team(this,"NEUTRAL",java.awt.Color.GRAY);
    thread.start();
  }

  public double performance() {
    return (1000000000D/(double)ltt);
  }

  @Override
  public void run() {
    while (running) {
      long lttc = System.nanoTime() - lt;
      if (lttc >= delay) {
        tick();
        lt = System.nanoTime();
        ltt = lttc;
      }
      if (System.nanoTime() - lo >= 1000000000) {
        System.out.println("PERFORMANCE: "+PFORMAT.format(performance())+"tps");
        lo = System.nanoTime();
      }
    }
  }

  public Entity[] scanEntities() {
    return entities.toArray(new Entity[0]);
  }
  public Entity[] scanEntities(EntityFilter[] filters) {
    Entity[] ents = scanEntities();
    for (EntityFilter fil : filters) {
      ents = fil.filter(ents);
    }
    return ents;
  }

  public void addEntity(Entity ent) {
    entities.add(ent);
    if (ent.getLevel() != this) ent.setLevel(this);
  }
  public void removeEntity(Entity ent) {
    entities.remove(ent);
    if (ent.getLevel() == this) ent.setLevel(null);
  }

  public Entity getClosestEnemy(Entity entt) {
    Team t = entt.getTeam();
    float x = entt.getX();
    float y = entt.getY();
    float z = entt.getZ();
    Entity cl = null;
    float cld = 0F;
    for (Entity ent : scanEntities()) {
      if (ent != null && ent != entt) {
        if (cl == null || (cld > ent.distFrom(x,y,z) && ent.getTeam().isHostileTo(t))) {
          cl = ent;
          cld = ent.distFrom(x,y,z);
        }
      }
    }
    if (cl == this) System.out.println("CL IS THIS");
    return cl;
  }
  public Entity getClosestEnemy(Team t,float x,float y,float z) {
    Entity cl = null;
    float cld = 0F;
    for (Entity ent : scanEntities()) {
      if (ent != null) {
        if (cl == null || (cld > ent.distFrom(x,y,z) && ent.getTeam().isHostileTo(t))) {
          cl = ent;
          cld = ent.distFrom(x,y,z);
        }
      }
    }
    return cl;
  }

  public boolean shouldDelete(Entity ent) {
    if (ent.isMarkedForDeletion()) return true;
    return ent.getHealth() < 1 && !ent.isDying();
  }

  /**
   * Iterates over a scan of the entities list, ticks each entity,
   * then checks if the entity should be deleted, and deletes it
   * if so.
   */
  public void tickEntities() {
    for (Entity ent : scanEntities()) {
      // FIXME: I Put this here because sometimes when you scan the
      //        entities list, there are null values.
      if (ent != null) {
        ent.tick();
        if (shouldDelete(ent) && entities.contains(ent)) entities.remove(ent);
      }
    }
  }
  public void tick() {
    tickEntities();
  }
}
