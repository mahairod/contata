/* 
 * File:   JHomonym.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:55
 */

#ifndef _JHOMONYM_H_
#define	_JHOMONYM_H_

#include "JObjectSynt.h"

class JHomonym : public JObjectSynt {
public:
	JHomonym(std::string val);
	virtual ~JHomonym();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	std::string value;
};

#endif	/* _JHOMONYM_H_ */

