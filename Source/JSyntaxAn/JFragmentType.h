/* 
 * File:   JFragmentType.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 2:34
 */

#ifndef _JFRAGMENTTYPE_H_
#define	_JFRAGMENTTYPE_H_

#include "JObjectSynt.h"

class JFragmentType : public JObjectSynt {
public:
	JFragmentType();
	JFragmentType(const JFragmentType& orig);
	virtual ~JFragmentType();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	enum FragmentType {
		
	};
	FragmentType fragmentType;
};

#endif	/* _JFRAGMENTTYPE_H_ */

