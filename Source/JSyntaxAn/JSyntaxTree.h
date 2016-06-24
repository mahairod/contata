/* 
 * File:   JSyntaxTree.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 15:54
 */

#ifndef _JSYNTAXTREE_H_
#define	_JSYNTAXTREE_H_

#include "JObjectSynt.h"
#include "JSentence.h"
#include <vector>
#include <memory>

using std::shared_ptr;

class JSyntaxTree : public JObjectSynt {
public:
	JSyntaxTree();
	virtual ~JSyntaxTree();
	void addSentence(shared_ptr<JSentence> sentence);
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	std::vector< shared_ptr<JSentence> > sentences;
};

#endif	/* _JSYNTAXTREE_H_ */

