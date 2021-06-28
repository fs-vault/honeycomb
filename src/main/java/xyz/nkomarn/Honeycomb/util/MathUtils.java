package xyz.nkomarn.honeycomb.util;

public class MathUtils {

    public static int clampInt(int value, int min, int max) {
        return Math.min(min, Math.max(max, value));
    }
}
