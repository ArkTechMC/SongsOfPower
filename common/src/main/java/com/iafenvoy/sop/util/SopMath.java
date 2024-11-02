package com.iafenvoy.sop.util;

import net.minecraft.util.math.Vec3d;

public class SopMath {
    public static Vec3d getRotationVector(float pitch, float yaw) {
        double f = Math.toRadians(pitch);
        double g = -Math.toRadians(yaw);
        double h = Math.cos(g);
        double i = Math.sin(g);
        double j = Math.cos(f);
        double k = Math.sin(f);
        return new Vec3d(i * j, -k, h * j);
    }

    public static Vec3d getRotationXYZ(float pitch, float yaw) {
        double p = Math.toRadians(pitch);
        double y = Math.toRadians(yaw);
        double h = Math.sin(p);
        double d = Math.cos(p);
        double xd = d * Math.cos(y);
        double zd = d * Math.sin(y);
        return new Vec3d(Math.atan(h / xd), y, Math.atan(h / zd));
    }

    public static Vec3d getRotationVectorUnit(float pitch, float yaw) {
        return toUnit(getRotationVector(pitch, yaw));
    }

    public static Vec3d toUnit(double x, double y, double z) {
        return toUnit(new Vec3d(x, y, z));
    }

    public static Vec3d toUnit(Vec3d origin) {
        return origin.length() == 0 ? origin : origin.multiply(1 / origin.length());
    }

    public static Vec3d reverse(Vec3d origin, double distance) {
        double newLength = distance - origin.length();
        return toUnit(origin).multiply(newLength > 0 ? newLength : 0);
    }
}
