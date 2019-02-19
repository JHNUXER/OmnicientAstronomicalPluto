package com.jhnuxer.game;

import com.jhnuxer.game.entity.*;

public class EntityFilter_Hostile {
  public static final byte MODE_ENT_TO_US = (byte)0,
                           MODE_US_TO_ENT = (byte)1,
                           MODE_BOTH_WAYS = (byte)2;

  Team team;
  byte mode;

  public EntityFilter_Hostile(Team t,byte m) {
    this.team = t;
    this.mode = m;
  }

  public boolean allow(Entity ent) {
    boolean b = true;
    if (mode == (byte)0 || mode == (byte)2) b &= ent.getTeam().isHostileTo(team);
    if (mode == (byte)1) b &= team.isHostileTo(ent.getTeam());
    return ent.getTeam().isHostileTo(team);
  }
}
