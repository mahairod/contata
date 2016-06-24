/* 
 * Файл:   JFragmentRelation.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:45
 */

#include <iosfwd>

#include "JFragmentRelation.h"

JFragmentRelation::JFragmentRelation() {
}

JFragmentRelation::JFragmentRelation(const JFragmentRelation& orig) : JPeriod(orig) {
}

JFragmentRelation::~JFragmentRelation() {
}

std::string JFragmentRelation::className() const {
	return "связьФрагментов";
}

void JFragmentRelation::internal_export(std::ostream& ss) const {
	JPeriod::internal_export(ss);
	//-- members here
	//---------------
}
