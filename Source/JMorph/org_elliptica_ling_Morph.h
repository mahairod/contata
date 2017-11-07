/* НЕ РЕДАКТИРУЙТЕ ЭТОТ ФАЙЛ - он генерируется автоматически */
/*     DO NOT EDIT THIS FILE - it is machine generated       */
#include <jni.h>
/* Заголовок для класса ru_aot_morph_JavaMorphAPI */

#define UTIL_METHOD(type, meth) JNIEXPORT type Java_org_elliptica_ling_Morph_ ## meth


#ifndef _Included_org_elliptica_ling_Morph
#define _Included_org_elliptica_ling_Morph
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_elliptica_ling_Morph
 * Method:    lookupWordImpl
 * Signature: (I[B)Lru/aot/morph/JavaMorphAPI/WordResult;
 */
UTIL_METHOD(jobject, lookupWordImpl)
  (JNIEnv *, jclass, jint, jbyteArray);

/*
 * Class:     org_elliptica_ling_Morph
 * Method:    lookupFormImpl
 * Signature: (I[B)Lru/aot/morph/JavaMorphAPI/WordResult;
 */
UTIL_METHOD(jobject, lookupFormImpl)
  (JNIEnv *, jclass, jint, jbyteArray);

/*
 * Class:     org_elliptica_ling_Morph
 * Method:    initImpl
 * Signature: (ILjava/lang/String;)V
 */
UTIL_METHOD(void, initImpl)
  (JNIEnv *, jclass, jint, jstring work_dir);

/*
 * Class:     org_elliptica_ling_Morph
 * Method:    closeImpl
 * Signature: ()V
 */
UTIL_METHOD(void, closeImpl)
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif