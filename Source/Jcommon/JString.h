/* 
 * Файл:   JString.h
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 7 августа 2015 г.
 */
#ifndef _JSTRING_H_
#define _JSTRING_H_

#include <jni.h>
#include "StringCommon.h"

class JString {
public:
	JString(jstring);
	int length() const;
//	std::wstring content() const;
	std::string cp1251str() const;
	std::string cp1252str() const;
	std::string utf8() const;
private:
	std::u16string native;
	std::string cp1251_str;
	std::string cp1252_str;
	std::string utf8_str;
};

#endif // _JSTRING_H_
