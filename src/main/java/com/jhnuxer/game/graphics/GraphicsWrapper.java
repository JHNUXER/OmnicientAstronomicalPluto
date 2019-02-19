package com.jhnuxer.game.graphics;

import java.awt.*;

import com.jhnuxer.game.Vec2;
import com.jhnuxer.graphics.*;

public class GraphicsWrapper extends JGraphics {
  public GraphicsWrapper(Graphics g) { super(g); }

  public void drawLine(Vec2 a,Vec2 b) {
    drawLine(a.round(),b.round());
  }
}
