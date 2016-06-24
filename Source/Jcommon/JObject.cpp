/* 
 * Файл:   JObject.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 7 августа 2015 г., 20:17
 */

#include "JObject.h"
#include "jcommon.h"

//#define TRACE

#ifdef TRACE
#include <iostream>
#include <signal.h>
#include <execinfo.h>
#include <unistd.h>
#include <stdlib.h>
#endif

JObject::JObject(jobject jthis_) {
	jthis = env_ptr->NewWeakGlobalRef(jthis_);
	SIMPLE_CHECK_EXC
    jlong refVal = (jlong)this;
//    std::cerr << "Object address:" << this << ", refVal = " << refVal << "\n";
    env_ptr->SetLongField(jthis, fldObjectRef, refVal);
	SIMPLE_CHECK_EXC
}

JObject::~JObject() {
    env_ptr->DeleteWeakGlobalRef(jthis);
#ifdef TRACE
    std::cerr << "Destroying object, address:" << this << "\n";
    void *array[10];
    size_t size = backtrace(array, 10);
    backtrace_symbols_fd(array, size, STDERR_FILENO);
#endif
}
