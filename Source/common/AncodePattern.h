#ifndef ancode_pattern_h
#define ancode_pattern_h

class CAgramtab;
class CSyntaxOpt;
#include "utilit.h"

class CAncodePattern 
{
    const	CAgramtab* m_pGramTab;
    void    ResetFlags();
	string			m_GramCodes;

public:
	QWORD			m_TypeGrammems;
	QWORD			m_iGrammems;
	char			m_LemSign;
	string			m_CommonGramCode;
	poses_mask_t    m_iPoses;
    bool            m_bUnkGramcodes;
			
	// all single preposition interpretations
	vector<int>		m_SimplePrepNos;

	CAncodePattern(const	CAgramtab* pGramTab);
    const	CAgramtab* GetGramTab() const;
	void	CopyAncodePattern(const CAncodePattern& X);
	bool	HasGrammem(BYTE gram) const;
	bool	HasPos(BYTE pos) const;
	bool	HasAnyPos(poses_mask_t poses) const;
	string	GetGrammemsByAncodes() const;
	bool	ModifyGrammems(QWORD Grammems, poses_mask_t Poses=0xffffffff);
	bool	InitAncodePattern();
    void    SetMorphUnknown();
    bool    DeleteAncodesByGrammemIfCan(BYTE Grammem);
    string	GetPartOfSpeechStr() const;
    const string& GetGramCodes() const;
    void  SetGramCodes(const string&);
    void  SetGramCodes(const char*);

};


#endif
