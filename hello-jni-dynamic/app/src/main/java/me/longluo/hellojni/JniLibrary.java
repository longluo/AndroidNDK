package me.longluo.hellojni;

import androidx.annotation.Keep;


public final class JniLibrary {

    /*
     * this is used to load the 'hello-jni' library on application
     * startup. The library has already been unpacked into
     * /data/data/me.longluo.hellojni/lib/libhello-jni.so
     * at the installation time by the package manager.
     */
    static {
        try {
            System.loadLibrary("hello-jni");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    private static final class InstanceHolder {
        private static final JniLibrary instance = new JniLibrary();
    }

    public static JniLibrary getInstance() {
        return InstanceHolder.instance;
    }

    public static float getCoreJniVersion() {
        return nativeGetJniVersion();
    }

    public static String getJniDynamicRegister() {
        return nativeGetJniDynamicRegister();
    }

    /*
     * A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
    @Keep
    private static native float nativeGetJniVersion();

    // Native method to get the version (String)
    @Keep
    private static native String nativeGetJniDynamicRegister();

}
