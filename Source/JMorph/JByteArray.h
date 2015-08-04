#ifndef _J_BYTE_ARRAY_H_
#define _J_BYTE_ARRAY_H_

#include <jni.h>

class ByteArray {
public:
	ByteArray(JNIEnv* _env, jbyteArray _array);
	jsize len();
	jbyte* b();
	~ByteArray();
private:
	jbyte* bytes;
	jsize length;
	JNIEnv* env;
	const jbyteArray array;
};

#endif

