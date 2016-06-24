/* 
 * File:   JPeriod.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:08
 */

#ifndef _JPERIOD_H_
#define	_JPERIOD_H_

#include "JObjectSynt.h"

class JPeriod: public JObjectSynt {
public:
	JPeriod();
	virtual ~JPeriod();
	void setStart(int pos);
	void setStop(int pos);
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	int start;
	int stop;
};

#endif	/* _JPERIOD_H_ */

