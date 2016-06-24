#include <string>

#include "JSyntaxAn.h"
#include "org_elliptica_ling_Syntax.h"
#include "JSyntaxTree.h"
#include <jcommon.h>
#include <JString.h>
#include <NString.h>
#include <utilit.h>
#include <memory>

JSyntaxAn::JSyntaxAn(jobject jthis):JObject(jthis) {
	bool res = syntaxHolder.LoadSyntax(morphRussian);
	CHECK_RESULT_EXC(!res, "Unable to load syntax materials")
	if (!methId_fillList){
		jSyntaxClass = env_ptr->FindClass( PCKG_UTIL "Syntax" );
		NULL_CHECK_EXC(jSyntaxClass)
		jSyntaxClass = (jclass) env_ptr->NewGlobalRef(jSyntaxClass);
		NULL_CHECK_EXC(jSyntaxClass)
		methId_fillList = env_ptr->GetMethodID(jSyntaxClass, "fillList", "(" SIG_LIST SIG_STRG SIG_VRES);
		SIMPLE_CHECK_EXC
	}

}

using std::vector;
jmethodID JSyntaxAn::methId_fillList = 0;
jclass JSyntaxAn::jSyntaxClass = 0;

void processSentence(const CSentence& csent);

jstring JSyntaxAn::processText(jobject jlist, jstring textJstr){
	JString jstr(textJstr);
	try{
		if ( !syntaxHolder.GetSentencesFromSynAn( jstr.cp1251str(), false ) ){
			return JNI_FALSE;
		}
	} catch(std::exception& ex){
		throwException(ex.what());
		throw JNIException();
	}
	
	JSyntaxTree tree;

    for (size_t sent = 0; sent < syntaxHolder.m_Synan.m_vectorSents.size(); sent++){
		tree.addSentence( processSentence( * syntaxHolder.m_Synan.m_vectorSents[sent] ) );
	}
	std::cout << "xml:\n" << tree << std::endl << std::flush;

	std::stringstream ss;
	ss << tree;
	string xmlStr = ss.str();
	NString nXml(xmlStr);
	jstring resultJStr = (jstring) env_ptr->NewLocalRef( nXml.jString() );
	
	return resultJStr;

	vector<std::string>& stringList = syntaxHolder.m_PlmLines.m_Items;
	for (vector<std::string>::iterator it = stringList.begin(); it != stringList.end(); ++it ){
		std::u16string ucsStr = convert_to_ucs(*it, cp1251);
		NString nString(ucsStr);
		env_ptr->CallVoidMethod(jthis, methId_fillList, jlist, nString.jString());
		SIMPLE_CHECK_EXC
	}
	return resultJStr;
}

void topFragments(const CSentence& piSent, vector<long>& topFragments){
	long clCount = piSent.m_Clauses.size();
	const CClause*  piNextClause = 0;

	for(long i = piSent.m_Clauses.size() - 1 ; i >= 0 ; i-- ) {
		const CClause* piClause = &piSent.m_Clauses[i];
		if( (piNextClause == NULL) || (piNextClause->m_iFirstWord == piClause->m_iLastWord + 1 ) ) {
			piNextClause = piClause;
			topFragments.push_back(i);
		}
	}
	std::reverse(topFragments.begin(), topFragments.end());
}

void fillWords(const CSentence& csent, JSentence& jsent, long startW, long endW) {
	for(int i = startW ; i <= endW ; i++ ) {
		const CSynWord& cword = csent.m_Words[i];
		JWord& jw = jsent.addWord(cword.m_strWord);

		long nHoms = cword.m_Homonyms.size();
		for(int j = 0 ; j < nHoms ; j++ ){			
			const CSynHomonym& H = cword.m_Homonyms[j];
			jw.addHomonym(H.m_strLemma);
		}
	}
}

void JSyntaxAn::AddHomonym(vector<SSynVariant2Groups>& synVariants, const CSynUnit& Unit){
	for(int i = 0 ; i < synVariants.size() ; i++ ){
		synVariants[i].m_SynUnits.push_back(Unit);
	}
}

void MultTwoVariants(SSynVariant2Groups& var1, SSynVariant2Groups& var2, SSynVariant2Groups& res){
	res = var1;
	res.m_SynUnits.insert(res.m_SynUnits.end(), var2.m_SynUnits.begin(), var2.m_SynUnits.end());
	res.m_Groups.insert(res.m_Groups.end(), var2.m_Groups.begin(), var2.m_Groups.end());
}

void MultVariants(vector<SSynVariant2Groups>& synVariants1, vector<SSynVariant2Groups>& synVariants2){
	vector<SSynVariant2Groups> resVars;
	for(int i = 0 ; i < synVariants1.size() ; i++){
		for(int j = 0 ; j < synVariants2.size() ; j++){
			SSynVariant2Groups res;
			MultTwoVariants(synVariants1[i], synVariants2[j], res);
			resVars.push_back(res);
		}
	}

	if( synVariants1.size() == 0)
		synVariants1 = synVariants2;
	else
		synVariants1 = resVars;
}

void JSyntaxAn::AddGroup(vector<SSynVariant2Groups>& synVariants, const CGroup&  piGroup){
	for(int i = 0 ; i < synVariants.size() ; i++ ){
		SGroup group;
		group.m_W1 = piGroup.m_iFirstWord;
		group.m_W2 = piGroup.m_iLastWord;
		group.m_bIsGroup = true;
		group.m_strDescr = syntaxHolder.m_Synan.GetOpt()->GetGroupNameByIndex(piGroup.m_GroupType);
		EngRusMakeLower(group.m_strDescr);
		synVariants[i].m_Groups.push_back(group);
	}
}

void JSyntaxAn::AddGroups(vector<SSynVariant2Groups>& synVariants, const CMorphVariant& piVar, const CClause& Clause){
	long i = 0;
	for(; i < piVar.m_vectorGroups.GetGroups().size() ; i++ ){
		const CGroup& G = 	 piVar.m_vectorGroups.GetGroups()[i];
		AddGroup(synVariants, G);
	}

	long iSubj = piVar.GetFirstSubject();
	long iPred = piVar.m_iPredk;
	if( (iSubj >= 0) && (iPred >= 0) ){
		iSubj = piVar.GetSentenceCoordinates(iSubj).m_iFirstWord;
		iPred = piVar.GetSentenceCoordinates(iPred).m_iFirstWord;
		for(i = 0 ; i < synVariants.size() ; i++ ){
			SGroup group;
			group.m_W1 = iSubj;
			group.m_W2 = iPred;			
			group.m_strDescr = "sp";			
			synVariants[i].m_Groups.push_back(group);
		}	
	}
}

void JSyntaxAn::AddOneVariant(vector<SSynVariant2Groups>& allSynVariants, const CMorphVariant& piVar, const CSentence& csent, const CClause& Clause){
	long unitsCount = piVar.m_SynUnits.size();
	SSynVariant2Groups var;
	allSynVariants.push_back(var);

	for(long i = 0 ; i < unitsCount ; i++ ) {
		if( piVar.m_SynUnits[i].m_Type == EWord){
			AddHomonym(allSynVariants, piVar.m_SynUnits[i]);
		} else {
			vector<SSynVariant2Groups> synVariants;
			int fragmentNo = csent.FindClauseIndexByPeriod(piVar.m_SynUnits[i].m_SentPeriod);
			fillVariants(synVariants, csent.m_Clauses[fragmentNo], csent);
			MultVariants(allSynVariants, synVariants);
		}
	}

	AddGroups(allSynVariants, piVar, Clause);
}

void JSyntaxAn::fillVariants(vector<SSynVariant2Groups>& allSynVariants, const CClause& fragment,  const CSentence& piSent){
	for(CSVI i =  fragment.m_SynVariants.begin() ; i != fragment.m_SynVariants.end(); i++ ) {
		vector<SSynVariant2Groups> synVariants;
		AddOneVariant(synVariants, *i, piSent, fragment);

		for( int j = 0 ; j < synVariants.size() ; j++ ) {
			SGroup group;
			group.m_W1 = fragment.m_iFirstWord;
			group.m_W2 = fragment.m_iLastWord;
			group.m_bIsGroup = false;
			group.m_strDescr = syntaxHolder.GetClauseTypeDescr(fragment,  i->m_ClauseTypeNo);
			EngRusMakeLower(group.m_strDescr);
			synVariants[j].m_Groups.push_back(group);
		}

		allSynVariants.insert(allSynVariants.end(), synVariants.begin(), synVariants.end());
	}
}

void JSyntaxAn::WriteVariant(SSynVariant2Groups& var, vector<JVariant>& jvariants ){
	JVariant& jvariant = *jvariants.insert(jvariants.end(), JVariant());
	int i = 0;
	for(; i < var.m_SynUnits.size(); i++ ){
		const CSynUnit& U  = var.m_SynUnits[i];
		int homNo = U.m_iHomonymNum;
		std::string grammems = U.GetGrammemsByAncodes();
		if (grammems.empty())
			grammems = syntaxHolder.m_pGramTab->GrammemsToStr(U.m_iGrammems);
		string pos = var.m_SynUnits[i].GetPartOfSpeechStr();
		JUnit unit(homNo, pos, U.m_iGrammems);
		jvariant.addUnit(unit);
	}

	for( i = 0 ; i < var.m_Groups.size(); i++ ){
		SGroup group = var.m_Groups[i];
		JGroup jgroup(group.m_W1, group.m_W2, group.m_strDescr);
		if( group.m_strDescr == "sp"){
			jgroup.setType("sp");
		} else {
			if( group.m_bIsGroup )
				jgroup.setType("gr");
			else
				jgroup.setType("cl");
		}
		jvariant.addGroup(jgroup);
	}
}

std::shared_ptr<JSentence> JSyntaxAn::processSentence(const CSentence& csent) {
	std::shared_ptr<JSentence> sentencePtr( new JSentence() );
	JSentence& sentence = *sentencePtr;
	try {
		vector<long> topClauses;
		topFragments(csent, topClauses);
		for(long i = 0; i < topClauses.size() ; i++ ) {
			const CClause& fragment = csent.m_Clauses[topClauses[i]];
			fillWords(csent, sentence, fragment.m_iFirstWord, fragment.m_iLastWord);

			long variantsCount = fragment.GetSynVariantsCount();
			JFragment& jfragment = sentence.addFragment(JFragment());
			jfragment.setStart(fragment.m_iFirstWord);
			jfragment.setStop(fragment.m_iLastWord);

			// writing groups
			vector<SSynVariant2Groups> synVariants;
			vector<JVariant> jvariants;
			fillVariants(synVariants, fragment, csent);
			for(long k = 0 ; k < synVariants.size() ; k++ ) {
				vector<SGroup>& G =  synVariants[k].m_Groups;;
				for(long j = 0 ; j < G.size(); j++) {
					G[j].m_W1 -= fragment.m_iFirstWord;
					G[j].m_W2 -= fragment.m_iFirstWord;
				};
				sort(synVariants[k].m_Groups.begin(), synVariants[k].m_Groups.end());
				WriteVariant(synVariants[k], jvariants);
			}
			for (vector<JVariant>::iterator it = jvariants.begin(); it != jvariants.end(); ++it ){
				jfragment.addVariant(*it);
			}
		}
        return sentencePtr;
	} catch (...) {
		throwException("an exception in JVisualSynAnParamBuilder occurred!");
		throw JNIException();
	};
}

void JNICALL Java_org_elliptica_ling_Syntax_init(JNIEnv * env, jobject jthis, jstring rmlPathJstr) {
	env_ptr = env;
	try{
		setEnvPath(rmlPathJstr);

		new JSyntaxAn(jthis);

	} catch (JNIException& ex){
		return;
	}
}

void JNICALL Java_org_elliptica_ling_Syntax_finalize0(JNIEnv * env, jobject jthis) {
	env_ptr = env;
	try{
		JSyntaxAn* jobj = getObjectById<JSyntaxAn>(jthis);
		if (jobj){
			delete jobj;
		}
	} catch (JNIException& ex){
		return;
	}
}

jstring JNICALL Java_org_elliptica_ling_Syntax_parseRawText(JNIEnv * env, jobject jthis, jobject resultJlist, jstring textJstr) {
	env_ptr = env;
	try{
		JSyntaxAn* thisObj = getObjectById<JSyntaxAn>(jthis);
		return thisObj->processText(resultJlist, textJstr);
	} catch (JNIException& ex){
		return 0;
	}
}
