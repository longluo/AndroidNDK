#ifndef HELLO_JNI_H
#define HELLO_JNI_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL
Java_me_longluo_hellojni_HelloJniActivity_stringFromJNI(JNIEnv *env, jobject /* this */);

JNIEXPORT jint JNICALL
Java_me_longluo_hellojni_HelloJniActivity_add(JNIEnv *env, jobject /* this */, jint a, jint b);


#ifdef __cplusplus
}
#endif

#endif  // HELLO_JNI_H
