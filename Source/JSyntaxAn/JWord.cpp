/* 
 * Файл:   JWord.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:56
 */

#include <iosfwd>

#include "JWord.h"

JWord::JWord(std::string val, int _id) {
	value = val;
	id = _id;
}

JWord::~JWord() {
}

std::string JWord::className() const {
	return "слово";
}

void JWord::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	tag(ss, "значение", value);

	ss << openTag("омонимы");
	for (std::vector<JHomonym>::const_iterator it = homonyms.begin(); it != homonyms.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("омонимы");
	//---------------
}

JHomonym& JWord::addHomonym(std::string hom){
	int homInd = homonyms.size();
	return *homonyms.insert(homonyms.end(), JHomonym(hom, homInd, id));
}
