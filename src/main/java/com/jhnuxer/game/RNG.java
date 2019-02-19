package com.jhnuxer.game;

public class RNG {
  public static final Random RAND = new Random();

  public static int i32() { return RAND.nextInt(); }
  public static int i32(int max) { return RAND.nextInt(max); }
  public static int i32(int min,int max) { return i32(max-min)+min; }

  public static float f32() { return RAND.nextFloat(); }
  public static float f32(float scale) { return f32() * scale; }
  public static float f32(float min,float max) { return f32(max-min)+min; }

  public static Vec2 vec2() { return new Vec2(f32(-1,1),f32(-1,1)).m(1F); }
  public static Vec2 vec2(float m) { return vec2().m(m); }
  public static Vec2 vec2(float min,float max) { return vec2(f32(min,max)); }

  public static Vec3 vec3() { return new Vec3(f32(-1,1),f32(-1,1),f32(-1,1)).m(1f); }
  public static Vec3 vec3(float m) { return vec3().m(m); }
  public static Vec3 vec3(float min,float max) { return vec3().m(f32(min,max)); }
}
