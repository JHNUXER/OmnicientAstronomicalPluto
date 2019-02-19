package com.jhnuxer.game.graphics;

import java.awt.*;

import com.jhnuxer.game.*;

public class Graphics3D extends GraphicsWrapper {
  Vec3 cameraPos = new Vec3(0,0,0);
  Vec3 cameraRot = new Vec3(1,0,0).r(new Vec3(0,0,0));

  public Graphics3D(Graphics g) {
    super(g);
  }

  public void setCameraLocation(float x,float y,float z) {
    setCameraLocation(new Vec3(x,y,z));
  }
  public void setCameraLocation(Vec3 v) {
    cameraPos = v;
  }

  public Vec2 to2D(Vec3 v) {
    double costhx = Math.cos(cameraRot.x);
    double costhy = Math.cos(cameraRot.y);
    double costhz = Math.cos(cameraRot.z);
    double sinthx = Math.sin(cameraRot.x);
    double sinthy = Math.sin(cameraRot.y);
    double sinthz = Math.sin(cameraRot.z);
    Vec3 e = new Vec3(0F,0F,0.1F);
    Matrix3x3D x = new Matrix3x3D(1,0,0,0,costhx,sinthx,0,-sinthx,costhx);
    Matrix3x3D y = new Matrix3x3D(costhy,0,-sinthy,0,1,0,sinthy,0,costhy);
    Matrix3x3D z = new Matrix3x3D(costhz,sinthz,0,-sinthz,costhz,0,0,0,1);
    Matrix3x1D a = new Matrix3x1D(v);
    Matrix3x1D c = new Matrix3x1D(cameraPos);
    Vec3 d = x.mul(y).mul(z).mul(a.sub(c)).toVec3();
    float ezdz = e.z/d.z;
    return new Vec2(ezdz*d.x+e.x,ezdz*d.y+e.y);
  }
  public void drawLine(int x0,int y0,int z0,int x1,int y1,int z1) {
    Vec2 a = to2D(new Vec3(x0,y0,z0));
    Vec2 b = to2D(new Vec3(x1,y1,z1));
    drawLine(a,b);
  }
  public Polygon genPoly(VPoly3 poly) {
    VPoly2 polyg = new VPoly2();
    for (int i = 0; i < poly.vertCount(); i++) {
      Vec2 v = to2D(poly.getVert(i));
      polyg.addPoint(v);
    }
    return polyg.toPoly(new Vec2(0,0));
  }
  public void fillPolygon(VPoly2 v) {
    fillPolygon(v.toPoly(new Vec2(0,0)));
  }
  public void fillPolygon(VPoly3 v) {
    fillPolygon(genPoly(v));
  }
  // public void drawRect(int x,int y,int z,int w,int h) {
  //   int x2 = x + w;
  //   int y2 = y + h;
  //   int z2 = z + d;
  // }
}
