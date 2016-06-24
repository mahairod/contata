/* 
 * File:   JUnit.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 6:13
 */

#ifndef _JUNIT_H_
#define	_JUNIT_H_

#include "JObjectSynt.h"

class JUnit : public JObjectSynt {
public:
	JUnit(int number, std::string pos, long long gr);
	virtual ~JUnit();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	std::string partOfSpeech;
	long long grammems;
	int number;
};

#endif	/* _JUNIT_H_ */

