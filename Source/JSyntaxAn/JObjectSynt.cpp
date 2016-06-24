/* 
 * Файл:   JObjectSynt.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 0:32
 */

#include "JObjectSynt.h"
#include <typeinfo>
#include "StringCommon.h"

JObjectSynt::JObjectSynt() {
}

JObjectSynt::JObjectSynt(const JObjectSynt& orig) {
}

JObjectSynt::~JObjectSynt() {
}

std::string JObjectSynt::className() const {
	std::string name = typeid(this).name();
//	std::u16string result(name.begin(), name.end());
	return name;
}

void JObjectSynt::internal_export (std::ostream& ss) const {
	
}

template<>
std::ostream& tag<string>(std::ostream& ss, string tag, string value){
	ss << openTag(tag) << convert_to_utf8(value, cp1251) << closeTag(tag);
}

std::string openTag(std::string tag) {
	return "<" + tag + ">";
}

std::string closeTag(std::string tag) {
	return "</" + tag + ">";
}

std::ostream& JObjectSynt::operator>>(std::ostream& ss) const {
	ss << '<' << className() << '>';
	this->internal_export(ss);
	ss << "</" << className() << '>';
	return ss;
}

std::ostream& operator<<(std::ostream& ss, const JObjectSynt& obj) {
	obj >> ss;
	return ss;
}
