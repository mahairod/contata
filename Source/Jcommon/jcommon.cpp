#include "jcommon.h"
#include "JString.h"
#include <stdlib.h>

const char NO_OBJ_MEM_ERROR[] = "Object was not allocated";
const char NO_MEM_ERROR[] = "Not enough memory";

jclass JNIAPIExceptionClass = 0;
jclass jObjectClass = 0;
jfieldID fldObjectRef = 0;

__thread JNIEnv* env_ptr;

void throwException(std::string message){
	if (env_ptr->ExceptionOccurred()){
		return;
	}
	if(message.empty()){
		env_ptr->ThrowNew(JNIAPIExceptionClass, "null message");
	} else {
		env_ptr->ThrowNew(JNIAPIExceptionClass, message.c_str());
	}
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved){
	jint result = vm->GetEnv( (void**)&env_ptr, JNI_VERSION_1_6);
	if (result != JNI_OK){
		return -1;
	}
	try{
		JNIAPIExceptionClass	= getGlobalClassRef(CLASS_EXCP);
		jObjectClass			= getGlobalClassRef(CLASS_JOBJ);
		fldObjectRef			= env_ptr->GetFieldID(jObjectClass, FLD_JOBJ_REF, "J");
		SIMPLE_CHECK_EXC
	} catch(JNIException& ex){
		return -1;
	}
	return JNI_VERSION_1_6;
}

jclass getGlobalClassRef(const char* const class_name){
	jclass klass = env_ptr->FindClass(class_name);
	if(env_ptr->ExceptionOccurred()){
		throw JNIException();
	}
	if (klass==NULL){
		env_ptr->FatalError("JNI: Cannot resolve class");
		throw JNIException();
	}
	klass = (jclass)env_ptr->NewGlobalRef(klass);
	CHECK_RESULT_EXC(klass==NULL, "Global reference error")
	return klass;
}

void setEnvPath(jstring path) throw (JNIException) {
	if (0!=path){
		JString rmlPathStr(path);
		int res = setenv("RML", rmlPathStr.cp1251str().c_str(), 0);
	}
}
