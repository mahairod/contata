/* 
 * File:   JSentence.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:52
 */

#ifndef _JSENTENCE_H_
#define	_JSENTENCE_H_

#include "JFragmentCollection.h"
#include "JWord.h"


class JSentence : public JFragmentCollection {
public:
	JSentence();
	JSentence(const JSentence& orig);
	JWord& addWord(std::string word);
	virtual ~JSentence();
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	std::vector<JWord> words;
};

#endif	/* _JSENTENCE_H_ */

