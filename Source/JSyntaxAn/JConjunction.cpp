/* 
 * Файл:   JConjunction.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 2:30
 */

#include <iosfwd>

#include "JConjunction.h"

JConjunction::JConjunction() {
}

JConjunction::JConjunction(const JConjunction& orig) : JPeriod(orig) {
}

JConjunction::~JConjunction() {
}

std::string JConjunction::className() const {
	return "позицияСоюза";
}

void JConjunction::internal_export(std::ostream& ss) const {
	JPeriod::internal_export(ss);
	//-- members here
	//---------------
}
