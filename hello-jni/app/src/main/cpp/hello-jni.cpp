#include <jni.h>
#include <string>

#include "hello_jni.h"


JNIEXPORT jstring JNICALL
Java_me_longluo_hellojni_HelloJniActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from JNI.";
    return env->NewStringUTF(hello.c_str());
}


JNIEXPORT jint JNICALL
Java_me_longluo_hellojni_HelloJniActivity_add(JNIEnv *env, jobject /* this */, jint a, jint b) {
    int result = a + b;
    return result;
}

