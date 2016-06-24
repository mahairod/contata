/* 
 * Файл:   JFragmentCollection.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 23 августа 2015 г., 1:50
 */

#include <iosfwd>

#include "JFragmentCollection.h"

JFragmentCollection::JFragmentCollection() {
}

JFragmentCollection::JFragmentCollection(const JFragmentCollection& orig) : JObjectSynt(orig) {
}

JFragmentCollection::~JFragmentCollection() {
}

JFragment& JFragmentCollection::addFragment(const JFragment& fr){
	return *fragments.insert(fragments.end(), fr);
}

JFragmentRelation& JFragmentCollection::addFragmentRelation(const JFragmentRelation& frRel){
	return *fragmentRelations.insert(fragmentRelations.end(), frRel);
}

std::string JFragmentCollection::className() const {
	return "коллекцияФрагментов";
}

void JFragmentCollection::internal_export(std::ostream& ss) const {
	JObjectSynt::internal_export(ss);
	//-- members here
	ss << openTag("списокСвязейФрагментов");
	for (std::vector<JFragmentRelation>::const_iterator it = fragmentRelations.begin(); it != fragmentRelations.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("списокСвязейФрагментов");

	ss << openTag("списокФрагментов");
	for (std::vector<JFragment>::const_iterator it = fragments.begin(); it != fragments.end(); ++it ){
		ss << *it;
	}
	ss << closeTag("списокФрагментов");
	//---------------
}
