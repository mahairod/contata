/* 
 * File:   JObjectSynt.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 0:32
 */

#ifndef _JOBJECTSYNT_H_
#define	_JOBJECTSYNT_H_

#include <sstream>

using std::string;

class JObjectSynt {
public:
	JObjectSynt();
	JObjectSynt(const JObjectSynt& orig);
	virtual ~JObjectSynt();
	virtual std::string className() const;
	virtual std::ostream& operator>>(std::ostream& ss) const;
protected:
	virtual void internal_export (std::ostream& ss) const;
private:

};

string openTag(string tag);
string closeTag(string tag);

template<typename ValueType>
std::ostream& tag(std::ostream& ss, string tag, ValueType value){
	ss << openTag(tag) << value << closeTag(tag);
}

template<>
std::ostream& tag<string>(std::ostream& ss, string tag, string value);

std::ostream& operator<<(std::ostream& ss, const JObjectSynt& obj);


#endif	/* _JOBJECTSYNT_H_ */

