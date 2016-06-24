/* 
 * Файл:   JUnit.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 6:13
 */

#include <iosfwd>

#include "JUnit.h"

JUnit::JUnit(int num, std::string pos, long long gr) {
	partOfSpeech = pos;
	grammems = gr;
	number = num;
}

JUnit::~JUnit() {
}

std::string JUnit::className() const {
	return "юнит";
}

void JUnit::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	tag(ss, "номер",	number);
	tag(ss, "граммемы",	grammems);
	tag(ss, "частьРечи",partOfSpeech);
	//---------------
}
