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
        if (ent.isMarkedForDeletion() && entities.contains(ent)) entities.remove(ent);
      }
    }
  }
  public void tick() {
    tickEntities();
  }
}
