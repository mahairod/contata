/* 
 * Файл:   JFragmentType.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 2:34
 */

#include <iosfwd>

#include "JFragmentType.h"

JFragmentType::JFragmentType() {
}

JFragmentType::JFragmentType(const JFragmentType& orig) : JObjectSynt(orig) {
}

JFragmentType::~JFragmentType() {
}

void JFragmentType::setType(const FragmentType type){
	fragmentType = type;
}

std::string JFragmentType::className() const {
	return "типФрагмента";
}

void JFragmentType::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	tag(ss, "номер", (int) fragmentType);
	//---------------
}
