/* 
 * Файл:   JSentence.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:52
 */

#include <iosfwd>

#include "JSentence.h"

JSentence::JSentence() {
}

JSentence::JSentence(const JSentence& orig) : JFragmentCollection(orig) {
}

JSentence::~JSentence() {
}

std::string JSentence::className() const {
	return "предложение";
}

void JSentence::internal_export(std::ostream& ss) const {
	JFragmentCollection::internal_export(ss);
	//-- members here
	ss << openTag("списокСлов");
	for (std::vector<JWord>::const_iterator it = words.begin(); it != words.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("списокСлов");
	//---------------
}

JWord& JSentence::addWord(std::string word){
	return *words.insert(words.end(), JWord(word));
}
