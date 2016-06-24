/* 
 * File:   NString.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 9 августа 2015 г., 11:23
 */

#ifndef _NSTRING_H_
#define	_NSTRING_H_

#include "StringCommon.h"

class NString {
public:
	NString(std::string);
	NString(std::u16string);
	NString(const NString& orig);
	jstring jString();
	virtual ~NString();
private:
	std::string utf8;
	std::u16string ucs;
	jstring jstr;
};

#endif	/* _NSTRING_H_ */

