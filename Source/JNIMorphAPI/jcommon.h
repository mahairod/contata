#ifndef _J_COMMON_H_
#define _J_COMMON_H_

#include <string>
#include <jni.h>

#define SIMPLE_CHECK_RETURN \
if(env->ExceptionOccurred()){\
	return NULL;\
};

#define NULL_CHECK_ONLY(var, msg) \
if(0 == var){\
	throwException(env, msg);\
	return NULL;\
};

#define NULL_CHECK_RETURN(var) \
SIMPLE_CHECK_RETURN \
NULL_CHECK_ONLY(var, NO_OBJ_MEM_ERROR)


#define SIMPLE_CHECK_EXC \
if(env->ExceptionOccurred()){\
	throw JNIException();\
};

#define NULL_CHECK_ONLY_EXC(var, msg) \
if(0 == var){\
	throwException(env, msg);\
	throw JNIException();\
};

#define NULL_CHECK_EXC(var) \
SIMPLE_CHECK_EXC \
NULL_CHECK_ONLY_EXC(var, NO_OBJ_MEM_ERROR)

extern const char NO_OBJ_MEM_ERROR[];
extern const char NO_MEM_ERROR[];

extern jclass JNIAPIExceptionClass;

extern void throwException(JNIEnv* env, std::string message);

class JNIException {
};

#endif
