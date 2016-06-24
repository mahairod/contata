/* 
 * File:   JFragmentCollection.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:50
 */

#ifndef _JFRAGMENTCOLLECTION_H_
#define	_JFRAGMENTCOLLECTION_H_

#include "JObjectSynt.h"
#include "JFragment.h"
#include "JFragmentRelation.h"
#include <vector>

class JFragmentCollection : public JObjectSynt {
public:
	JFragmentCollection();
	JFragmentCollection(const JFragmentCollection& orig);
	virtual ~JFragmentCollection();
	JFragment& addFragment(const JFragment& fr);
	JFragmentRelation& addFragmentRelation(const JFragmentRelation& frRel);
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	std::vector<JFragment> fragments;
	std::vector<JFragmentRelation> fragmentRelations;
};

#endif	/* _JFRAGMENTCOLLECTION_H_ */

