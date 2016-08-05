#include "org_elliptica_ling_Morph.h"

#ifndef JNIEXPORT
#define JNIEXPORT
#endif

#ifndef JNIIMPORT
#define JNIIMPORT
#endif

#ifndef JNICALL
#define JNICALL
#endif

#ifndef __GNUC__
#pragma warning(disable:4786)
#pragma warning(disable:4530)
#endif

#include <stdio.h>
#include <stdlib.h>

#include <sys/types.h>
#include <sys/stat.h>

#include "../common/utilit.h"
#include "../AgramtabLib/RusGramTab.h"
#include "../AgramtabLib/EngGramTab.h"
#include "../AgramtabLib/GerGramTab.h"
#include "../LemmatizerLib/Lemmatizers.h"
#include "../LemmatizerLib/Paradigm.h"

#include <JByteArray.h>
#include <jcommon.h>

//jni
static jclass setClazz=0;
static jmethodID setConstructor=0;
static jmethodID method_convertFromCharsetCp1251ToJavaString=0;
static jmethodID method_grammemSetAddGrammem=0;
static jmethodID method_paradigmSetAddParadigm = 0;
static jmethodID method_paradigmsetAddNewParadigm=0;
static jmethodID method_paradigmAddWordform=0;
static jmethodID method_wordresult_new=0;

// java class and method names

#define CLASS_UTIL		PCKG_UTIL "Morph"
#define CLASS_WORDRES	PCKG_UTIL "РезультатСлова"
#define CLASS_PARADIGM	PCKG_UTIL "Парадигма"

#define SIG_UTIL		SIG( CLASS_UTIL )
#define SIG_WORDRES		SIG( CLASS_WORDRES )
#define SIG_PARADIGM	SIG( CLASS_PARADIGM )

#define concat(left, right)
#define FUN_UTIL_CLASS Java_org_elliptica_ling_Morph_

#define UTIL_CALL(meth) concat(FUN_UTIL_CLASS,meth)

#ifndef UTIL_METHOD
#define UTIL_METHOD(type, meth) JNIEXPORT type concat(FUN_UTIL_CLASS,meth)
#endif

// ----

enum jni_language {Russian,English,German};

enum rus_pos{
	noun,  // 0
	adjective, // 1
	verb, // 2
	mestoim_noun, // 3
	mestoim_adjective, // 4
	mestoim_predikativ, // 5
	chislitelnoeKolichestv, // 6
	chislitelnoePoryadkovoe, // 7
	narechie, // 8
	predikativ, //9
	predlog, // 10
	posl, // 11
	soyuz, // 12
	mejdometie, // 13
	vvodnoe_slovo,// 14
	fraz, // 15
	chastica, // 16
	kratkoePrilagat,  // 17
	prichastie, //18
	deeprichastie, //19
	kratkoePrichastie, // 20
	verbInfinitive  //21
};

enum rus_grammems{
	// 0..1
	plural, singular,
	// 2..8
	padejImen, padejRodit, padejDatel, padejVinit, padejTvor, padejPredl, padejZvateln,
	// род 9-12
	rodMuj, rodJen, rodSred, rodMujJen,
	// 13..15
	present, future, past,
	// 16..18
	lico1, lico2, lico3,
	// 19
	povelitelnFormaGlagola,
	// 20..21
	odush, neodush,
	// 22
	sravnitelnFormaPrilagat,
	// 23..24
	vidSov, vidNesov,
	// 25..26
	neperehodnyiGlagol, perehodnyiGlagol,
	// 27..28
	deistvitZalog, stradatZalog,
	// 29-31
	neizmenyaemoe, abbrev, otchestvo,
	// 32-33
	lokativnost, organizaciya,
	// 34-35
	kachestv, neImeetMnojestvChisla,
	// 36-37 (наречия)
	voprositNarech, ukazat,
	// 38..39
	firstName, lastName,
	// 40
	bezlichnGlagol,
	// 41,42
	jargon, opechatka,
	// 43,44,45
	razgovorn, posessive, archaic,
	// для второго родительного и второго предложного
	padej2,
	poet, professionalizm,
	prev, poloj
};

struct jni_dictionary{
	jni_language 		lang;
	MorphLanguageEnum	Language;
	CAgramtab*			pAgramtab;
	CLemmatizer*		pLemmatizer;
};

/* returns true if ok */
bool GetGrammems (jni_dictionary& dic, const char* tab_str, QWORD& grammems){
	return dic.pAgramtab->GetGrammems(tab_str, grammems);
}

jstring createJStringCp1251(JNIEnv *env, jclass clazz, string& baseForm){
	const char* chars = baseForm.c_str();
	jsize length = (jsize) baseForm.size();

	jbyteArray baseFormBytes = env->NewByteArray(length);
	NULL_CHECK_RETURN(baseFormBytes)

	env->SetByteArrayRegion(baseFormBytes, (jsize)0, length, (jbyte*)chars);
	SIMPLE_CHECK_RETURN

	//for Russian bytes
	jstring jbaseForm = (jstring) env->CallStaticObjectMethod(clazz, method_convertFromCharsetCp1251ToJavaString, baseFormBytes);
	NULL_CHECK_RETURN(jbaseForm)

	return jbaseForm;
}

jobject GenerateMorphForms(JNIEnv *env, jclass clazz, jni_dictionary& dic, vector<CFormInfo>& paradigms, jobject paradigmset, bool bCapital){
	for(int i=0; i < paradigms.size(); i++){
		const CFormInfo& formInfo = paradigms[i];
		bool found = formInfo.m_bFound;

		QWORD commonGrammems;
		{
			string CommonAncode = formInfo.GetCommonAncode();
			bool ok = GetGrammems(dic, CommonAncode.c_str(), commonGrammems);
			if(!ok){
				commonGrammems = 0;
			}
		}

		jobject paradigm = 0;

		for (int i=0; i<formInfo.GetCount(); i++){
			string form = formInfo.GetWordForm(i);
			{
				char firstChar = form[0];
				EngRusMakeLower(form);
				if (bCapital){
					form[0] = firstChar;
				}
			}
			jstring jform = createJStringCp1251(env, clazz, form);

			QWORD grammems=0;
			grammems |= commonGrammems;

			string grammemCodes = formInfo.GetAncode(i);
			BYTE  PartOfSpeech = dic.pAgramtab->GetPartOfSpeech(grammemCodes.c_str());
			//assert(dic.lang==Russian);
			rus_pos pos = (rus_pos) PartOfSpeech;

			for (long i=0; i < grammemCodes.length(); i+=2){
				QWORD grammems2;
				bool ok2 = GetGrammems(dic, grammemCodes.c_str()+i, grammems2);
				if(ok2){
					grammems |= grammems2;
				}
			}

			paradigm = env->CallStaticObjectMethod(clazz, method_paradigmAddWordform, paradigm, jform, grammems, (jint)pos);
		}

		env->CallStaticVoidMethod(clazz, method_paradigmSetAddParadigm, paradigmset, paradigm);
		SIMPLE_CHECK_RETURN

	}
	return paradigmset;
}

jobject GenerateMorphInfoIntern(JNIEnv *env, jclass clazz, jni_dictionary& dic, vector<CFormInfo>& paradigms, jobject paradigmset){
	for(int i=0; i < paradigms.size(); i++){
		const CFormInfo& formInfo = paradigms[i];
		bool found = formInfo.m_bFound;

		string baseForm = formInfo.GetWordForm(0);
		jstring jbaseForm = createJStringCp1251(env, clazz, baseForm);

		//baseFormBytes is a local ref, no need to release.
		string grammemCodes = formInfo.GetSrcAncode();
		BYTE  PartOfSpeech = dic.pAgramtab->GetPartOfSpeech(grammemCodes.c_str());
		//assert(dic.lang==Russian);
		rus_pos pos = (rus_pos) PartOfSpeech;

		string CommonAncode = formInfo.GetCommonAncode();
		QWORD grammems=0;
		QWORD grammems1;
		bool ok = GetGrammems(dic, CommonAncode.c_str(), grammems1);
		if(ok){
			grammems=grammems1;
		}
		for (long i=0; i < grammemCodes.length(); i+=2){
			QWORD grammems2;
			bool ok2=GetGrammems(dic, grammemCodes.c_str()+i, grammems2);
			if(ok2){
				grammems|=grammems2;
			}
		}
		//assert(dic.lang==Russian);
		size_t GrammemsCountInLanguage = dic.pAgramtab->GetGrammemsCount();
		jobject grammemset = env->NewObject(setClazz, setConstructor);
		NULL_CHECK_RETURN(grammemset)

		for (int i = GrammemsCountInLanguage-1; i>=0; i--){
			if ((((QWORD)1)<<i) & grammems){
				env->CallStaticVoidMethod(clazz, method_grammemSetAddGrammem, grammemset, (jint)(rus_grammems)i);
				SIMPLE_CHECK_RETURN
			}
		}
		env->CallStaticVoidMethod(clazz, method_paradigmsetAddNewParadigm,
				paradigmset, grammemset, jbaseForm, (jboolean)found, (jint)pos);
		//jbaseForm is a local ref, no need to release.
		//grammemset is a local ref, no need to release.
		SIMPLE_CHECK_RETURN

	}
	return paradigmset;
}

/* cp1251 charset on input string - for Russian */
jobject GenerateMorphInfo(JNIEnv *env, jclass clazz, jni_dictionary& dic, string& form, bool norm){
//	try{

	jobject paradigmset = env->NewObject(setClazz, setConstructor);
	NULL_CHECK_RETURN(paradigmset)

	bool bCapital = is_upper_alpha((BYTE)form[0], dic.Language);
	vector<CFormInfo> paradigms;
	dic.pLemmatizer->CreateParadigmCollection(norm, form, bCapital, true, paradigms);

	if (norm){
		GenerateMorphForms(env, clazz, dic, paradigms, paradigmset, bCapital);
	} else {
		GenerateMorphInfoIntern(env, clazz, dic, paradigms, paradigmset);
	}

	jobject wordresult = env->CallStaticObjectMethod(clazz, method_wordresult_new, paradigmset);
	NULL_CHECK_RETURN(wordresult)
	return wordresult;

//	}catch(CExpc e){
//		throwException("C++ exception: CExpc: " + e.m_strCause);
//	}catch(int e){
//		string errstr("C++ exception: int: ");
//		errstr += e;
//		errstr += ".";
//		throwException( errstr);
//	}catch(...){
//		throwException("Unknown C++ exception.");
//	}
//	return NULL;

}

jobject GetMorphInfo(JNIEnv *env, jclass clazz, jni_dictionary& dic, string& form){
	return GenerateMorphInfo(env, clazz, dic, form, false);
}

jobject GetMorphForms(JNIEnv *env, jclass clazz, jni_dictionary& dic, string& norm){
	return GenerateMorphInfo(env, clazz, dic, norm, true);
}

template <class  T, class Y>
bool InitMorphologySystem(JNIEnv *env, jni_dictionary &dic){
	switch (dic.Language){
		case morphRussian:
			dic.lang=Russian;
			break;
		case morphEnglish :
		case morphGerman:
		default:
			throwException("assertion error: A1");
			return false;
	}

	string langua_str = GetStringByLanguage(dic.Language);
	dic.pLemmatizer = new T;
	string strError;
	if (!dic.pLemmatizer->LoadDictionariesRegistry(strError)){
		string err = "Cannot load " + langua_str + " morphological dictionary. Error details: " + strError;
		throwException(err);
		return false;
	}
	dic.pAgramtab = new Y;
	CHECK_EXPR_RETURN_VAL(!dic.pAgramtab->LoadFromRegistry(), "Cannot load " + langua_str + " gramtab.", false);
	return true;
}

static bool inited=0;
static jni_dictionary dic;

static jobject lookupWordIternal(bool normal, JNIEnv *env, jclass clazz, jint languageId, jbyteArray word);

//cp1251 charset for <code>word</code> - for Russian.
/*
 * Класс:	 ru_aot_morph_JavaMorphAPI
 * Метод:	lookupWordImpl
 * Сигнатура: (I[B)Lru/aot/morph/JavaMorphAPI/WordResult;
 */
UTIL_METHOD(jobject, lookupWordImpl) (JNIEnv *env, jclass clazz, jint languageId, jbyteArray word){
	  return lookupWordIternal(false, env, clazz, languageId, word);
}

//cp1251 charset for <code>word</code> - for Russian.
/*
 * Класс:	ru_aot_morph_JavaMorphAPI
 * Метод:	lookupFormImpl
 * Сигнатура: (I[B)Lru/aot/morph/JavaMorphAPI/WordResult;
 */
UTIL_METHOD(jobject, lookupFormImpl) (JNIEnv *env, jclass clazz, jint languageId, jbyteArray word){
	  return lookupWordIternal(true, env, clazz, languageId, word);
}

static jobject lookupWordIternal(bool normal, JNIEnv *env, jclass clazz, jint languageId, jbyteArray word){
	try{
		CHECK_EXPR_RETURN(!inited || dic.pAgramtab==0 || dic.pLemmatizer==0, "Словари не загружены. Сначала вызови Morph.приготовьСловари!");
		CHECK_EXPR_RETURN(languageId!=0, "Поддерживается только русский язык.");
		NULL_CHECK_ONLY(word, "word is null")

		try {
			ByteArray bArray(word);
			string s( (char*) bArray.b(), bArray.len() );
			Trim(s);
			CHECK_EXPR_RETURN(s.empty(), "Пустая либо пробельная строка вместо слова.");
			if (normal){
				return GetMorphForms(env, clazz, dic, s);
			} else {
				return GetMorphInfo(env, clazz, dic, s);
			}
		} catch(JNIException& ex){
			return NULL;
		}
	}catch(CExpc& e){
		string err = "C++ exception: CExpc: " + e.m_strCause;
		throwException(err);
	}catch(int e){
		string errstr("C++ exception: int: ");
		errstr += e;
		errstr += ".";
		throwException(errstr);
	}catch(...){
		throwException("Unknown C++ exception.");
	}
	return NULL;
}

/*
 * Класс:	ru_aot_morph_JavaMorphAPI
 * Метод:	initImpl
 * Сигнатура: (I)V
 */
UTIL_METHOD(void, initImpl)(JNIEnv *env, jclass clazz, jint languagesBitSet, jstring work_dir){
	dic.pAgramtab=0;
	dic.pLemmatizer=0;
	setClazz=NULL;
	setConstructor=NULL;
	JNIAPIExceptionClass=NULL;
	method_convertFromCharsetCp1251ToJavaString=NULL;
	method_grammemSetAddGrammem=NULL;
	method_paradigmsetAddNewParadigm=NULL;
	method_paradigmAddWordform = NULL;
	method_wordresult_new=NULL;

	try{
		setEnvPath(work_dir);
	} catch (JNIException& ex){
		return;
	}

	setClazz = env->FindClass(CLASS_HSET);
	CHECKJAVAERROR( setClazz==NULL || env->ExceptionOccurred(), "Out of memory")

	setClazz = (jclass)env->NewGlobalRef(setClazz);
	CHECKJAVAERROR( setClazz==NULL || env->ExceptionOccurred(), "global ref error")

	setConstructor = env->GetMethodID(setClazz, "<init>", "()V");
	CHECKJAVAERROR( setConstructor==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_convertFromCharsetCp1251ToJavaString = env->GetStaticMethodID(clazz, "преобразованиеРегионКодировки", "([B)" SIG_STRG );
	CHECKJAVAERROR( method_convertFromCharsetCp1251ToJavaString==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_grammemSetAddGrammem = env->GetStaticMethodID(clazz, "добавьГраммемуКМножеству", "(" SIG_HSET "I)V");
	CHECKJAVAERROR( method_grammemSetAddGrammem==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_paradigmsetAddNewParadigm = env->GetStaticMethodID(clazz, "добавьПарадигмуКМножеству", "(" SIG_HSET SIG_HSET SIG_STRG "ZI)V");
	CHECKJAVAERROR( method_paradigmsetAddNewParadigm==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_paradigmSetAddParadigm = env->GetStaticMethodID(clazz, "добавьПарадигму", "(" SIG_HSET SIG_PARADIGM ")V");
	CHECKJAVAERROR( method_paradigmSetAddParadigm==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

    method_paradigmAddWordform = env->GetStaticMethodID(clazz, "добавьСловоформуКПарадигме", "(" SIG_PARADIGM SIG_STRG "JI)" SIG_PARADIGM);
	CHECKJAVAERROR( method_paradigmAddWordform==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR);

	method_wordresult_new = env->GetStaticMethodID(clazz, "создайРезультатСлова", "(" SIG_HSET ")" SIG_WORDRES);
	CHECKJAVAERROR( method_wordresult_new==NULL || env->ExceptionOccurred(), "метод создайРезультатСлова не получен")

	inited=false;
	CHECK_EXPR_RETURN_VAL(languagesBitSet==0, "Набор языков пуст.", );
	CHECK_EXPR_RETURN_VAL(languagesBitSet!=1, "JMorph поддерживает только русский язык.", );
	dic.Language=morphRussian;
	try{
		//LOADING DICTS
		bool bResult = false;
		switch (dic.Language){
			case morphRussian:
				bResult = InitMorphologySystem<CLemmatizerRussian, CRusGramTab>(env,dic);
				break;
/*			case morphEnglish :
				bResult = InitMorphologySystem<CLemmatizerEnglish, CEngGramTab>(env,dic);
				break;
			case morphGerman:
				bResult = InitMorphologySystem<CLemmatizerGerman, CGerGramTab>(env,dic);
				break; */
			default:
				throwException("assertion error: A2.");
				return;
		};
		if (!bResult){
			UTIL_CALL(closeImpl)(env,clazz);
			return;//exception was thrown by InitMorphologySystem
		}

		inited=true;
		return;//ok
	}catch(CExpc& e){
			CHECKJAVAERROR(true, "C++ exception: CExpc: " + e.m_strCause)
	}catch(int e){
			string errstr("C++ exception: int: ");
			errstr += e;
			errstr += ".";
			CHECKJAVAERROR(true, errstr)
	}catch(...){
			CHECKJAVAERROR(true, "Unknown C++ exception.")
	}
}

/*
 * Класс:	ru_aot_morph_JavaMorphAPI
 * Метод:	closeImpl
 * Сигнатура: ()V
 */
UTIL_METHOD(void, closeImpl) (JNIEnv *env, jclass clazz){
	try{
		//dispose of dics
		if(dic.pLemmatizer!=0){
			delete dic.pLemmatizer;
			dic.pLemmatizer=0;
		}

		if(dic.pAgramtab!=0){
			delete dic.pAgramtab;
			dic.pAgramtab=0;
		}

		if(setClazz!=NULL){
			env->DeleteGlobalRef(setClazz);
			setClazz=NULL;
		}
		//if(setConstructor!=NULL){env->DeleteGlobalRef(setConstructor);setConstructor=NULL;}
		if(JNIAPIExceptionClass!=NULL){
			env->DeleteGlobalRef(JNIAPIExceptionClass);
			JNIAPIExceptionClass=NULL;
		}
		inited=false;
		return;//ok
	}catch(CExpc& e){
		string err = string ("C++ exception: CExpc: ") + e.m_strCause;
		throwException(err);
		return;
	}catch(int e){
		string errstr("C++ exception: int: ");
		errstr += e;
		errstr += ".";
		throwException(errstr);
		return;
	}catch(...){
		throwException("Unknown C++ exception.");
		return;
	}
}

/*
#include <iostream>
using namespace std;

JNIEXPORT void JNICALL testParadigms(char* word){
try{
	//initialization

	if (true){
		const char* wdirPar = "/mnt/f16/mahairod/Develop/eclipse-4.3/wsp/dialing/svn-native/sf";
		int res = setenv("RML", wdirPar, 0);
		cerr << "Error " << res << ", code: " << errno << endl;
	}

	dic.Language=morphRussian;
	dic.lang=Russian;

	string langua_str = GetStringByLanguage(dic.Language);
	dic.pLemmatizer = new CLemmatizerRussian();
	string strError;
	if (!dic.pLemmatizer->LoadDictionariesRegistry(strError)){
   		char* err=str_compose("Cannot load %s morphological dictionary. Error details: %s", langua_str.c_str(), strError.c_str());
		cerr << err << endl;
		return;
	}
	dic.pAgramtab = new CRusGramTab();
	if (!dic.pAgramtab->LoadFromRegistry()){
   		char* err=str_compose("Cannot load %s gramtab.", langua_str.c_str());
		cerr << err << endl;
		return;
	}

	// processing
	vector<CFormInfo> Paradigms;
	bool norm = false;
	string form = word;
	dic.pLemmatizer->CreateParadigmCollection(norm, form, false, true, Paradigms);
	
	for(int i=0; i < Paradigms.size(); i++){
		const CFormInfo& formInfo = Paradigms[i];
		bool found = formInfo.m_bFound;
		cout << "Forms:=========\n";
		int cnt = formInfo.GetCount();
		for (int i=0; i<cnt; i++){
			string form = formInfo.GetWordForm(i);
			cout << form << "\n";
		}
		cout << "+++++++=========\n";

		string baseForm = formInfo.GetWordForm(0);
		const char* chars = baseForm.c_str();
		jsize length = (jsize) strlen(chars);


		string grammemCodes = formInfo.GetSrcAncode();
		cout << "grammemCodes:\t" << grammemCodes << "\n";
		BYTE  PartOfSpeech = dic.pAgramtab->GetPartOfSpeech(grammemCodes.c_str());
		//assert(dic.lang==Russian);
		rus_pos pos = (rus_pos) PartOfSpeech;

		string CommonAncode = formInfo.GetCommonAncode();
		QWORD grammems=0;
		QWORD grammems1;
		bool ok = GetGrammems(dic, CommonAncode.c_str(), grammems1);
		if(ok){
			grammems=grammems1;
		}
		for (long i=0; i < grammemCodes.length(); i+=2){
			QWORD grammems2;
			bool ok2=GetGrammems(dic, grammemCodes.c_str()+i, grammems2);
			if(ok2){
				grammems|=grammems2;
			}
		}
		//assert(dic.lang==Russian);
		size_t GrammemsCountInLanguage = dic.pAgramtab->GetGrammemsCount();

	}

	
}catch(CExpc e){
		char* err=str_compose("C++ exception: CExpc: %s",e.m_strCause.c_str());
		cerr << err << endl;
		return;
}

}
*/
