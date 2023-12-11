#include <jni.h>

#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_me_longluo_hellojni_HelloJniActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from JNI.";
    return env->NewStringUTF(hello.c_str());
}