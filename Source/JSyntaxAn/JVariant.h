/* 
 * File:   JVariant.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:49
 */

#ifndef _JVARIANT_H_
#define	_JVARIANT_H_

#include "JObjectSynt.h"
#include "JUnit.h"
#include "JGroup.h"
#include <vector>

class JVariant : public JObjectSynt {
public:
	JVariant();
	void addUnit(const JUnit& u);
	void addGroup(const JGroup& g);
	virtual ~JVariant();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	std::vector<JUnit> units;
	std::vector<JGroup> groups;
};

#endif	/* _JVARIANT_H_ */

