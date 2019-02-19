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
    // TODO: Add entities for testing...
    JFrame frame = new JFrame();
    GamePanel gp = new GamePanel(level,800,600);
    frame.add(gp);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Title");
    frame.setVisible(true);
  }
}
