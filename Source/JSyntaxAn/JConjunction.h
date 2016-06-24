/* 
 * File:   JConjunction.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 2:30
 */

#ifndef _JCONJUNCTION_H_
#define	_JCONJUNCTION_H_

#include "JPeriod.h"

class JConjunction : public JPeriod {
public:
	JConjunction();
	JConjunction(const JConjunction& orig);
	virtual ~JConjunction();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:

};

#endif	/* _JCONJUNCTION_H_ */

