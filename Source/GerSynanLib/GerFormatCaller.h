// ==========  This file is under  LGPL, the GNU Lesser General Public Licence
// ==========  Dialing Syntax Analysis (www.aot.ru)
// ==========  Copyright by Dmitry Pankratov, Igor Nozhov, Alexey Sokirko

#ifndef ger_format_caller
 #define ger_format_caller

#include "../SynCommonLib/Group.h"
#include "../SynCommonLib/FormatCaller.h"
#include "GerGroup.h"
#include "GerSyntaxOpt.h"


class CGerFormatCaller;

//typedef bool(CGerFormatCaller::*simple_word_check_t)(const CSynPlmLine&)const;


class CGerFormatCaller : public CFormatCaller
{
public:
	CGerFormatCaller(const CSyntaxOpt* Opt);
	const CGerSyntaxOpt* GetOpt() const {  return (const CGerSyntaxOpt*)CFormatCaller::GetOpt(); };
	int		GetRuleByGroupTypeForThesaurus(int GroupType) const;
	void	AddAllRules();
	void	AddSimpleSimilarRules();
	void	BuildOborotGroups( ) {};
	bool	format_for_rank_fam(CGroup&);
    bool	format_for_genit_NP(CGroup&);
    bool	format_for_genit_in_preposition(CGroup& G);
    bool	format_for_verb_modif(CGroup& G);

};


bool IstZahlWort(CSynPlmLine& l){
    return l.HasFlag(fl_digit);
}


#endif

