/* 
 * File:   NString.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 9 августа 2015 г., 11:23
 */

#ifndef _STRING_COMMON_H_
#define	_STRING_COMMON_H_

#include <string>
#include <jni.h>

enum encoding_id {
	cp1251,
	cp1252,
	cp1253,
	cp1254,
	cp1255,
	cp1256,
	cp1257,
	cp1258
};

std::u16string convert_to_ucs(std::string str, const encoding_id enc_id);
std::string convert_to_chars(std::u16string str, const encoding_id enc_id);

#endif // _STRING_COMMON_H_