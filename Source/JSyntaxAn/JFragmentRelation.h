/* 
 * File:   JFragmentRelation.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:45
 */

#ifndef _JFRAGMENTRELATION_H_
#define	_JFRAGMENTRELATION_H_

#include "JPeriod.h"

class JFragmentRelation : public JPeriod {
public:
	JFragmentRelation();
	JFragmentRelation(const JFragmentRelation& orig);
	virtual ~JFragmentRelation();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:

};

#endif	/* _JFRAGMENTRELATION_H_ */

