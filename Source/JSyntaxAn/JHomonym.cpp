/* 
 * Файл:   JHomonym.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:55
 */

#include <iosfwd>

#include "JHomonym.h"

JHomonym::JHomonym(std::string val) {
	value = val;
}

JHomonym::~JHomonym() {
}

std::string JHomonym::className() const {
	return "омоним";
}

void JHomonym::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	tag(ss, "значение",	value);
	//---------------
}
