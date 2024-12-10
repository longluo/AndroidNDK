#ifndef HELLO_JNI_H
#define HELLO_JNI_H

#include <jni.h>
#include <string>
#include "logger.h"
#include "constants.h"

#define SIZE(X) sizeof(X) / sizeof(X[0])


typedef union {
    JNIEnv *env;
    void *venv;
} UnionJNIEnvToVoid;


/*
 * Register several native methods for one class.
 */
static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods,
                                 int numMethods) {
    jclass clazz;
    clazz = env->FindClass(className);

    if (clazz == NULL) {
        ALOGE("Native registration unable to find class '%s'", className);
        return JNI_FALSE;
    }

    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        ALOGE("RegisterNatives failed for '%s'", className);
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


/*
 * Register native methods for all classes we know about.
 *
 * returns JNI_TRUE on success.
 */
static int registerNatives(JNIEnv *env) {
    if (!registerNativeMethods(env, HelloJNI::constants,
                               HelloJNI::constants_methods,
                               SIZE(HelloJNI::constants_methods))
            ) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


/*
 * This is called by the VM when the shared library is first loaded.
 */
jint JNI_OnLoad(JavaVM *vm, void *reserved);

#endif //HELLO_JNI_H
