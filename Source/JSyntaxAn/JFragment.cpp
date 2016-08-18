/* 
 * Файл:   JFragment.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:47
 */

#include <iosfwd>

#include "JFragment.h"

JFragment::JFragment() {
    puncMarkNum = 0;
}

JFragment::~JFragment() {
}

JVariant& JFragment::addVariant(const JVariant& v){
	return *variants.insert(variants.end(), v);
}

JFragmentType& JFragment::addType(const JFragmentType& t){
	return *types.insert(types.end(), t);
}

JConjunction& JFragment::addConjunction(const JConjunction& c){
	return *conjunctions.insert(conjunctions.end(), c);
}

void JFragment::setPuncMarkNum(int markNum){
	puncMarkNum = markNum;
}

void JFragment::setRelativeWord(const JHomonymRef homonym){
	this->relativeWord = homonym;
}

std::string JFragment::className() const {
	return "фрагмент";
}

void JFragment::internal_export(std::ostream& ss) const {
	JPeriod::internal_export(ss);
	//-- members here
	ss << openTag("варианты");
	for (std::vector<JVariant>::const_iterator it = variants.begin(); it != variants.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("варианты");

	ss << openTag("союзы");
	for (std::vector<JConjunction>::const_iterator it = conjunctions.begin(); it != conjunctions.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("союзы");

	ss << openTag("типы");
	for (std::vector<JFragmentType>::const_iterator it = types.begin(); it != types.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("типы");

	tag(ss, "предшествующий", antecedent);
	tag(ss, "числоПунктЗнаков", puncMarkNum);
	tag(ss, "родственноеСлово", relativeWord);

	//---------------
}
