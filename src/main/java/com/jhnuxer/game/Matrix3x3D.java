package com.jhnuxer.game;

public class Matrix3x3D {
  double[] values;

  public Matrix3x3D(double[] values) {
    this.values = values;
  }
  public Matrix3x3D(double a,double b,double c,double d,double e,double f,double g,double h,double i) {
    this.values = new double[] { a,b,c,d,e,f,g,h,i };
  }

  public double get(int r,int c) {
    return values[c*3+r];
  }
  public double[] gc(int n) {
    double[] dd = new double[3];
    for (int i = 0; i < 3; i++) dd[i] = get(i,n);
    return dd;
  }
  public Vec3D gcv(int n) {
    double[] q = gc(n);
    return new Vec3D(q[0],q[1],q[2]);
  }
  public Vec3D grv(int n) {
    double[] q = gr(n);
    return new Vec3D(q[0],q[1],q[2]);
  }
  public double[] gr(int n) {
    double[] dd = new double[3];
    for (int i = 0; i < 3; i++) dd[i] = get(n,i);
    return dd;
  }
  public void set(int r,int c,double d) {
    values[c*3+r] = d;
  }

  public Matrix3x3D mul(Matrix3x3D b) {
    double[] dots = new double[9];
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        dots[y*3+x] = grv(x).dot(b.gcv(y));
      }
    }
    return new Matrix3x3D(dots);
  }
  public Matrix3x1D mul(Matrix3x1D b) {
    double[] d = new double[3];
    for (int i = 0; i < 3; i++) {
      d[i] = grv(i).dot(b.toVec3D());
    }
    return new Matrix3x1D(d);
  }
}
