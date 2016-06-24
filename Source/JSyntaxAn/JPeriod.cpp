/* 
 * Файл:   JPeriod.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:08
 */

#include <iosfwd>

#include "JPeriod.h"

JPeriod::JPeriod() {
	start = 0;
	stop = 0;
}

JPeriod::~JPeriod() {
}

void JPeriod::setStart(int pos){
	start = pos;
}

void JPeriod::setStop(int pos){
	stop = pos;
}

std::string JPeriod::className() const {
	return "диапазон";
}

void JPeriod::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	tag(ss, "начало", start);
	tag(ss, "конец", stop);
	//---------------
}
