package com.jhnuxer.game;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import com.jhnuxer.game.entity.*;
import com.jhnuxer.game.graphics.*;

public class Main {
  public static void main(String[] args) {
    Level level = new Level();
    Team tRed = new Team(level,"RED",Color.RED);
    Team tBlue = new Team(level,"BLUE",Color.BLUE);
    tRed.setRelation(tBlue,-10);
    tBlue.setRelation(tRed,-10);
    Person p = new Person(level,tRed,10,300,0);
    Person p1 = new Person(level,tBlue,790,300,0);
    Plane plane = new Plane(level,tRed,10,300,30);
    p.weapon = new BrawlerWeapon();
    p1.weapon = new BrawlerWeapon();
    level.addEntity(p);
    level.addEntity(p1);
    level.addEntity(plane);
    JFrame frame = new JFrame();
    GamePanel gp = new GamePanel(level,800,600);
    // gp.followTarget = plane;
    frame.setUndecorated(true);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.add(gp);
    // frame.pack();

    // frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setTitle("Title");
    frame.setVisible(true);
  }
}
