//
// Created by Long Luo
//

#ifndef HELLO_CONSTANTS_H
#define HELLO_CONSTANTS_H

#include <jni.h>


namespace HelloJNI{

    static jfloat JniVersion(JNIEnv *env, jobject object) {
        return 1.6;
    }

    static jstring JniDynamicRegister(JNIEnv *env, jobject object){
        return env->NewStringUTF("Hello Jni Dynamic Register");
    }

    static const char *constants = "me/longluo/hellojni/JniLibrary";

    static JNINativeMethod constants_methods[] = {
            {"nativeGetJniVersion", "()F", (void *) JniVersion},
            {"nativeGetJniDynamicRegister", "()Ljava/lang/String;", (void *) JniDynamicRegister}
    };

    static std::string jString2String(JNIEnv *env, jstring jStr) {
        if (!jStr) {
            return "";
        }

        const jclass stringClass = env->GetObjectClass(jStr);
        const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
        const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

        size_t length = (size_t) env->GetArrayLength(stringJbytes);
        jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

        std::string ret = std::string((char *)pBytes, length);
        env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

        env->DeleteLocalRef(stringJbytes);
        env->DeleteLocalRef(stringClass);

        return ret;
    }
}

#endif //HELLO_CONSTANTS_H
