#include "hello-jni.h"

jint JNI_OnLoad(JavaVM *vm, void *reserved){

    UnionJNIEnvToVoid uenv;
    uenv.venv = nullptr;
    jint result = -1;
    JNIEnv* env;

    ALOGI("JNI_OnLoad");

    if (vm->GetEnv(&uenv.venv, JNI_VERSION_1_6) != JNI_OK) {
        ALOGE("ERROR: GetEnv failed");
        goto bail;
    }

    env = uenv.env;
    if (registerNatives(env) != JNI_TRUE) {
        ALOGE("ERROR: registerNatives failed");
        goto bail;
    }

    result = JNI_VERSION_1_6;

    bail:
    return result;
}
