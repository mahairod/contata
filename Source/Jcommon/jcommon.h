#ifndef _J_COMMON_H_
#define _J_COMMON_H_

#include <string>
#include <jni.h>
#include "JObject.h"
#include <iostream>

#define SIMPLE_CHECK_RETURN \
if(env_ptr->ExceptionOccurred()){\
	return NULL;\
};

#define NULL_CHECK_ONLY(var, msg) \
if(0 == var){\
	throwException(msg);\
	return NULL;\
};

#define NULL_CHECK_RETURN(var) \
SIMPLE_CHECK_RETURN \
NULL_CHECK_ONLY(var, NO_OBJ_MEM_ERROR)

#define CHECK_EXPR_RETURN(test,message) \
if( test ){\
	throwException(message); \
	return NULL;\
};

#define CHECK_EXPR_RETURN_VAL(test,message, val) \
if( test ){\
	throwException(message); \
	return val;\
};

#define SIMPLE_CHECK_EXC \
if(env_ptr->ExceptionOccurred()){\
	throw JNIException();\
};

#define NULL_CHECK_ONLY_EXC(var, msg) \
if(0 == var){\
	throwException(msg);\
	throw JNIException();\
};

#define CHECK_RESULT_EXC(test,message) \
SIMPLE_CHECK_EXC \
if( test ){\
	throwException(message); \
	throw JNIException();\
};

#define NULL_CHECK_EXC(var) \
SIMPLE_CHECK_EXC \
NULL_CHECK_ONLY_EXC(var, NO_OBJ_MEM_ERROR)

#define CHECKJAVAERROR(test, message ) \
		if (test){ \
		  throwException(message); \
		  return; \
		}

extern const char NO_OBJ_MEM_ERROR[];
extern const char NO_MEM_ERROR[];

extern jclass JNIAPIExceptionClass;
extern jclass jObjectClass;
extern jfieldID fldObjectRef;

extern __thread JNIEnv* env_ptr;

class JNIException {
};

extern void throwException(std::string message);

extern void setEnvPath(jstring path) throw (JNIException);

template <typename JType>
JType* getObjectById(jobject obj){
    jlong longPtr = env_ptr->GetLongField(obj, fldObjectRef);
	JObject* medPtr = reinterpret_cast<JObject*>( longPtr );
    return dynamic_cast<JType*>(medPtr);
}

jclass getGlobalClassRef(const char* const class_name);

#define PCKG_UTIL "org/elliptica/ling/"

#define CLASS_EXCP		PCKG_UTIL "ОтклонениеМорфологии"
#define CLASS_JOBJ		PCKG_UTIL "ОбъектЯва"
#define FLD_JOBJ_REF	"указатель"

#define CLASS_HSET		"java/util/HashSet"
#define CLASS_LIST		"java/util/List"
#define CLASS_STRG		"java/lang/String"
#define CLASS_OBJT		"java/lang/Object"

#define SIG_VRES		")V"

#define SIG(clss) "L" clss ";"

#define SIG_STRG		SIG( CLASS_STRG )
#define SIG_LIST		SIG( CLASS_LIST )
#define SIG_HSET		SIG( CLASS_HSET )
#define SIG_OBJT		SIG( CLASS_OBJT )

#endif
