package net.talisman.talismanjackiechan.compat;

import java.lang.reflect.Method;

public final class IMBlockerCompat {

    private static Method captureTickMethod = null;
    private static boolean initialized = false;
    private static boolean available = false;

    private IMBlockerCompat() {}

    private static synchronized void init() {
        if (initialized) return;
        initialized = true;
        try {
            Class<?> cls = Class.forName("io.github.reserveword.imblocker.IMCheckState");
            captureTickMethod = cls.getMethod("captureTick", Object.class, boolean.class);
            available = true;
        } catch (Throwable t) {
            available = false;
        }
    }

    public static void captureTick(Object input, boolean canWrite) {
        if (!initialized) init();
        if (!available || captureTickMethod == null) return;
        try {
            captureTickMethod.invoke(null, input, canWrite);
        } catch (Throwable t) {
            available = false;
        }
    }
}