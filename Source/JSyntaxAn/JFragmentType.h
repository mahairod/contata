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

	enum FragmentType {
		VERB_PERS_T=0,//ГЛ_ЛИЧН
		ADVERB_PARTICIPLE_T=1, //ДПР
		PARTICIPLE_SHORT_T=2,	//КР_ПРЧ
		ADJ_SHORT_T=3,//КР_ПРИЛ
		PREDK_T=4, //ПРЕДК
		PARTICIPLE_T=5,	//ПРЧ
		INFINITIVE_T=6,	//ИНФ
		INP_T=7, //ВВОД
		DASH_T=8,//ТИРЕ
		UNDETACHED_ADJ_PATIC=9,//НСО
		COMPARATIVE_T=10,//СРАВН
		COPUL_T=11//КОПУЛ
	};

	void setType(const FragmentType type);

protected:
	std::string className() const;
	void internal_export(std::ostream& ss) const;
private:
	FragmentType fragmentType;
};

#endif	/* _JFRAGMENTTYPE_H_ */

