#include "JByteArray.h"
#include "jcommon.h"

ByteArray::ByteArray(jbyteArray _array):array(_array){
	length = env_ptr->GetArrayLength(array);
	SIMPLE_CHECK_EXC
	bytes = env_ptr->GetByteArrayElements(array, NULL);
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
		env_ptr->ReleaseByteArrayElements(array,bytes,JNI_ABORT);
	}
}

