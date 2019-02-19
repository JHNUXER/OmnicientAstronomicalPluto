package com.jhnuxer.game;

import java.util.*;

import com.jhnuxer.game.entity.*;

public interface EntityFilter {
  public boolean allow(Entity ent);

  public default Entity[] filter(Entity[] ents) {
    ArrayList<Entity> entz = new ArrayList<Entity>();
    for (int i = 0; i < ents.length; i++) {
      if (allow(ents[i])) entz.add(ents[i]);
    }
    return entz.toArray(new Entity[0]);
  }
}
