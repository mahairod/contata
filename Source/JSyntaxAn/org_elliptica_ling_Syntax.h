/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_elliptica_ling_Syntax */

#ifndef _Included_org_elliptica_ling_Syntax
#define _Included_org_elliptica_ling_Syntax
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_elliptica_ling_Syntax
 * Method:    init
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_elliptica_ling_Syntax_init
  (JNIEnv *, jobject, jstring);

/*
 * Class:     org_elliptica_ling_Syntax
 * Method:    finalize0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_elliptica_ling_Syntax_finalize0
  (JNIEnv *, jobject);

/*
 * Class:     org_elliptica_ling_Syntax
 * Method:    parseRawText
 * Signature: (Ljava/util/List;Ljava/lang/String;)V
 */
JNIEXPORT jstring JNICALL Java_org_elliptica_ling_Syntax_parseRawText
  (JNIEnv *, jobject, jobject, jstring);

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved);

#ifdef __cplusplus
}
#endif
#endif
