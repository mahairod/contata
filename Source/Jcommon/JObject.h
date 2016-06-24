/* 
 * Файл:   JObject.h
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 7 августа 2015 г., 20:17
 */

#ifndef _JOBJECT_H_
#define	_JOBJECT_H_

#include <jni.h>

class JObject {
public:
	JObject(jobject);
	virtual ~JObject();
protected:
	jobject jthis;
private:

};

#endif	/* _JOBJECT_H_ */

