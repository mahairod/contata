/* 
 * File:   JFragment.h
 * Author: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 *
 * Created on 23 августа 2015 г., 1:47
 */

#ifndef _JFRAGMENT_H_
#define	_JFRAGMENT_H_

#include "JPeriod.h"
#include "JVariant.h"
#include "JConjunction.h"
#include "JFragmentType.h"
#include "JHomonymRef.h"
#include <vector>

class JFragment : public JPeriod {
public:
	JFragment();
	virtual ~JFragment();

	JVariant& addVariant(const JVariant& v);
	JFragmentType& addType(const JFragmentType& t);
	JConjunction& addConjunction(const JConjunction& c);

	void setPuncMarkNum(int markNum);
	void setRelativeWord(const JHomonymRef homonym);
protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	std::vector<JVariant> variants;
	std::vector<JConjunction> conjunctions;
	std::vector<JFragmentType> types;
	JObjectSynt antecedent;
	int puncMarkNum;
	JHomonymRef relativeWord;
};

#endif	/* _JFRAGMENT_H_ */

