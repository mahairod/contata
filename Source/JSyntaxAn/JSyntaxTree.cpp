/* 
 * Файл:   JSyntaxTree.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 15:54
 */

#include <iosfwd>

#include "JSyntaxTree.h"

JSyntaxTree::JSyntaxTree() {
}

JSyntaxTree::~JSyntaxTree() {
}

void JSyntaxTree::addSentence(shared_ptr<JSentence> sentence){
	sentences.push_back(sentence);
}

std::string JSyntaxTree::className() const {
	return "текст";
}

void JSyntaxTree::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	ss << openTag("предложения");
	for (std::vector<shared_ptr<JSentence>>::const_iterator it = sentences.begin(); it != sentences.end(); ++it ){
		ss << **it;
	}
	ss << closeTag("предложения");
	//---------------
}
