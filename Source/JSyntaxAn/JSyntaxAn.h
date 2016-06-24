#ifndef _JSYNTAXAN_H_
#define _JSYNTAXAN_H_

#include <SyntaxHolder.h>
#include <JObject.h>
#include "JVariant.h"
#include "JSyntaxTree.h"

struct SGroup {
public:
	int m_W1;	
	int m_W2;	
	bool m_bIsGroup;
	string m_strDescr;
	bool operator < (const SGroup& gr) const {
		if( (m_W1 <= gr.m_W1) && (m_W2 >= gr.m_W2) )
			return true;
		if( m_W2 < gr.m_W1 )
			return true;
		return false;
	}
};

struct SSynVariant2Groups {
public:
	vector< CSynUnit> m_SynUnits;
	vector<SGroup> m_Groups;
};



class JSyntaxAn: public JObject {
public:
    JSyntaxAn(jobject);
	jstring processText(jobject, jstring);
private:
	CSyntaxHolder syntaxHolder;
	static jmethodID methId_fillList;
	static jclass jSyntaxClass;

	void AddGroup(vector<SSynVariant2Groups>& synVariants, const CGroup&  piGroup);
	void AddGroups(vector<SSynVariant2Groups>& synVariants, const CMorphVariant& piVar, const CClause& Clause);
	shared_ptr<JSentence> processSentence(const CSentence& csent);
	void fillVariants(vector<SSynVariant2Groups>& allSynVariants, const CClause& fragment,  const CSentence& piSent);
	void AddOneVariant(vector<SSynVariant2Groups>& allSynVariants, const CMorphVariant& piVar, const CSentence& csent, const CClause& Clause);
	void AddHomonym(vector<SSynVariant2Groups>& synVariants, const CSynUnit& Unit);
	void WriteVariant(SSynVariant2Groups& var, vector<JVariant>& jvariants );
};

#endif // _JSYNTAXAN_H_
