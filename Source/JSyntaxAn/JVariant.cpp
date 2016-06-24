/* 
 * Файл:   JVariant.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:49
 */

#include <iosfwd>
#include <vector>

#include "JVariant.h"

JVariant::JVariant() {
}

JVariant::~JVariant() {
}

std::string JVariant::className() const {
	return "вариант";
}

void JVariant::addUnit(const JUnit& u){
	units.push_back(u);
}

void JVariant::addGroup(const JGroup& g){
	groups.push_back(g);
}

void JVariant::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	ss << openTag("юниты");
	for (std::vector<JUnit>::const_iterator it = units.begin(); it != units.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("юниты");

	ss << openTag("группы");
	for (std::vector<JGroup>::const_iterator it = groups.begin(); it != groups.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("группы");

	//---------------
}
