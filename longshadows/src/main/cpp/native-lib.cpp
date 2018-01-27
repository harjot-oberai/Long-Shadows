#include <jni.h>
#include <string>

using namespace std;

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sdsmdg_harjot_longshadows_LongShadowsWrapper_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
