#include "JByteArray.h"
#include "jcommon.h"

ByteArray::ByteArray(JNIEnv* _env, jbyteArray _array):env(_env),array(_array){
	length = env->GetArrayLength(array);
	SIMPLE_CHECK_EXC
	bytes = env->GetByteArrayElements(array, NULL);
	NULL_CHECK_EXC(bytes)
}

jsize ByteArray::len(){
	return length;
}

jbyte* ByteArray::b(){
	return bytes;
}

ByteArray::~ByteArray(){
	if(bytes!=NULL){
		env->ReleaseByteArrayElements(array,bytes,JNI_ABORT);
	}
}

