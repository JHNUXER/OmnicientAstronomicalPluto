package com.jhnuxer.game;

import java.awt.Color;
import java.util.*;

import com.jhnuxer.game.entity.*;

public class Team {
  private Level level;
  private String name;
  private Color color;
  private ArrayList<Entity> members = new ArrayList<Entity>();
  private HashMap<Team,Integer> relations = new HashMap<Team,Integer>();

  public Team(Level l,String s,Color c) {
    this.level = l;
    this.name = s;
    this.color = c;
  }

  public int getRelation(Team t) {
    return relations.containsKey(t) ? relations.get(t) : 0 ;
  }
  public void setRelation(Team t,int n) {
    relations.put(t,n);
  }
  public boolean isHostileTo(Team t) { return getRelation(t) < 0; }
  public boolean isNeutralTo(Team t) { return getRelation(t) == 0; }
  public boolean isFriendlyTo(Team t) { return getRelation(t) > 0; }

  public boolean isMember(Entity ent) {
    return members.contains(ent);
  }
  public void addMember(Entity ent) {
    members.add(ent);
    if (ent.getTeam() != this) ent.setTeam(this);
  }
  public void removeMember(Entity ent) {
    members.remove(ent);
    if (ent.getTeam() == this) ent.setTeam(null);
  }
  public Entity[] scanMembers() { return members.toArray(new Entity[0]); }

  public Level getLevel() {
    return level;
  }
  public void setLevel(Level L) {
    this.level = L;
  }
  public String getName() {
    return name;
  }
  public Color getColor() {
    return color;
  }
}
