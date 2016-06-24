/* 
 * Файл:   JGroup.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 6:33
 */

#include <iosfwd>

#include "JGroup.h"

JGroup::JGroup(int pos1, int pos2, string descr) {
	setStart(pos1);
	setStop(pos2);
	description = descr;
}

JGroup::~JGroup() {
}

void JGroup::setType(string t){
	type = t;
}

std::string JGroup::className() const {
	return "группа";
}

void JGroup::internal_export(std::ostream& ss) const {
	JPeriod::internal_export(ss);
	//-- members here
	tag(ss, "описание", description);
	tag(ss, "тип", type);
	//---------------
}
