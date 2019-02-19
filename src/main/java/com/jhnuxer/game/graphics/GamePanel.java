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
  public Entity followTarget = null;
  long lt = 0;

  public GamePanel(Level l,int w,int h) {
    this.level = l;
    Dimension d = new Dimension(w,h);
    setMinimumSize(d);
    setPreferredSize(d);
    addMouseMotionListener(this);
    thread.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.clearRect(0,0,getWidth(),getHeight());
    int xq = offsx;
    int yq = offsy;
    if (followTarget != null) {
      xq = Math.round(followTarget.getX());
      yq = Math.round(followTarget.getY());
    }
    for (Entity ent : level.scanEntities()) {
      ent.draw(g,xq,yq);
    }
    Graphics3D g3d = new Graphics3D(g);
    g3d.fillPolygon(new VPoly3(new float[][] {
      {0,0,-2},
      {10,0,-2},
      {10,10,-2},
      {0,10,-2}
    }));
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
