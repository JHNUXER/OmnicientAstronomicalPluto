package com.jhnuxer.game.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jhnuxer.game.*;
import com.jhnuxer.game.entity.*;

public class GamePanel extends JTextField implements Runnable,MouseMotionListener {
  Level level;
  int offsx,offsy;
  Point mp = new Point(0,0);
  Thread thread = new Thread(this);
  boolean running = true;
  long lt = 0;

  public GamePanel(Level l,int w,int h) {
    this.level = l;
    Dimension d = new Dimension(w,h);
    setMinimumSize(d);
    setPreferredSize(d);
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.clearRect(0,0,getWidth(),getHeight());
    for (Entity ent : level.scanEntities()) {
      ent.draw(g,offsx,offsy);
    }
  }

  @Override
  public void run() {
    while (running) {
      if (System.nanoTime() - lt >= 16666667) {
        repaint();
      }
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    mp = e.getPoint();
  }
  @Override
  public void mouseDragged(MouseEvent e) {
    Point p = e.getPoint();
    offsx += p.x-mp.x;
    offsy += p.y-mp.y;
    mp = p;
  }
}
