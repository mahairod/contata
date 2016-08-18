/* 
 * File:   JWord.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:56
 */

#ifndef _JWORD_H_
#define	_JWORD_H_

#include "JObjectSynt.h"
#include "JHomonym.h"
#include <vector>

class JWord : public JObjectSynt {
public:
	JWord(std::string val, int _id);
	JHomonym& addHomonym(std::string word);
	virtual ~JWord();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	int id;
	std::string value;
	std::vector<JHomonym> homonyms;
};

#endif	/* _JWORD_H_ */

