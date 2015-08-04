#include "jcommon.h"

const char NO_OBJ_MEM_ERROR[] = "Object was not allocated";
const char NO_MEM_ERROR[] = "Not enough memory";

jclass JNIAPIExceptionClass = 0;

void throwException(JNIEnv* env, std::string message){
	if (env->ExceptionOccurred()){
		return;
	}
	if(message.empty()){
		env->ThrowNew(JNIAPIExceptionClass, "null message");
	} else {
		env->ThrowNew(JNIAPIExceptionClass, message.c_str());
	}
}

