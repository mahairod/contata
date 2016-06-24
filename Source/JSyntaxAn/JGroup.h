/* 
 * File:   JGroup.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 6:33
 */

#ifndef _JGROUP_H_
#define	_JGROUP_H_

#include "JObjectSynt.h"
#include "JPeriod.h"

using std::string;

class JGroup : public JPeriod {
public:
	JGroup(int pos1, int pos2, string description);
	virtual ~JGroup();
	void setType(string t);
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	string description;
	string type;
};

#endif	/* _JGROUP_H_ */

