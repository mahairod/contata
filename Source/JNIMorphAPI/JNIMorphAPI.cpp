#include "ru_aot_morph_JavaMorphAPI.h"

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
#include <string.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <locale.h>
#include <malloc.h>

#include "../common/utilit.h"
#include "../AgramtabLib/RusGramTab.h"
#include "../AgramtabLib/EngGramTab.h"
#include "../AgramtabLib/GerGramTab.h"
#include "../LemmatizerLib/Lemmatizers.h"
#include "../LemmatizerLib/Paradigm.h"

//jni infrastructure stuff
static jclass setClazz=0;
static jmethodID setConstructor=0;
static jclass JNIAPIExceptionClass=0;
static jmethodID method_convertFromCharsetCp1251ToJavaString=0;
static jmethodID method_grammemSetAddGrammem=0;
static jmethodID method_paradigmSetAddParadigm = 0;
static jmethodID method_paradigmsetAddNewParadigm=0;
static jmethodID method_paradigmAddWordform=0;
static jmethodID method_wordresult_new=0;

void throwEx(JNIEnv* env, char* message){
	if(env->ExceptionOccurred())return;
	jint retcode;//Returns 0 on success; a negative value on failure.
	if(message == 0){
		retcode = env->ThrowNew(JNIAPIExceptionClass,"null message");
	} else {
		retcode = env->ThrowNew(JNIAPIExceptionClass,message);
		free(message);
	}
}
//jni infrastructure stuff end


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

char* str_compose(const char *fmt, ...){
   /* Guess we need no more than 100 bytes. */
   int n, size = 100;
   char *p, *np;
   va_list ap;

  if ((p = (char*)malloc (size)) == NULL){
	  //out_of_memory();
	  return 0;
  }

  while (1) {
	  /* Try to print in the allocated space. */
	  va_start(ap, fmt);
	  n = vsnprintf (p, size, fmt, ap);
	  va_end(ap);
	  /* If that worked, return the string. */
	  if (n > -1 && n < size){
		 //return p;
		  break;
	  }
	  /* Else try again with more space. */
	  if (n > -1)	/* glibc 2.1 */
		 size = n+1; /* precisely what is needed */
	  else		   /* glibc 2.0 */
		 size *= 2;  /* twice the old size */
	  if ((np = (char*)realloc (p, size)) == NULL) {
		 //out_of_memory();
		 //free(p);
		 return p;//partial string
	  } else {
		 p = np;
	  }
   }

   return p;
}

const static char NO_OBJ_MEM_ERROR[] = "Object was not allocated";
const static char NO_MEM_ERROR[] = "Not enough memory";

#define SIMPLE_CHECK_RETURN \
if(env->ExceptionOccurred()){\
	return NULL;\
};


#define NULL_CHECK_ONLY(var, msg) \
if(0 == var){\
	throwEx(env, strdup(msg));\
	return NULL;\
};


#define NULL_CHECK_RETURN(var) \
SIMPLE_CHECK_RETURN \
NULL_CHECK_ONLY(var, NO_OBJ_MEM_ERROR)


/* returns true if ok */
bool GetGrammems (jni_dictionary& dic, const char* tab_str, QWORD& grammems){
	return dic.pAgramtab->GetGrammems(tab_str, grammems);
}

jstring createJStringCp1251(JNIEnv *env, jclass clazz, string& baseForm){
	const char* chars = baseForm.c_str();
	jsize length = (jsize) strlen(chars);
	//baseFormBytes is a local ref, no need to release.
	jbyteArray baseFormBytes = env->NewByteArray(length);
	NULL_CHECK_RETURN(baseFormBytes)

	env->SetByteArrayRegion(baseFormBytes, (jsize)0, length, (jbyte*)chars);
	SIMPLE_CHECK_RETURN

	//for Russian bytes
	jstring jbaseForm = (jstring) env->CallStaticObjectMethod(clazz, method_convertFromCharsetCp1251ToJavaString, baseFormBytes);
	NULL_CHECK_RETURN(jbaseForm)

	return jbaseForm;
}

jobject GenerateMorphForms(JNIEnv *env, jclass clazz, jni_dictionary& dic, vector<CFormInfo>& Paradigms, jobject paradigmset, bool bCapital){
	for(int i=0; i < Paradigms.size(); i++){
		const CFormInfo& F = Paradigms[i];
		bool found = F.m_bFound;

		QWORD commonGrammems;
		{
			string CommonAncode = F.GetCommonAncode();
			bool ok = GetGrammems(dic, CommonAncode.c_str(), commonGrammems);
			if(!ok){
				commonGrammems = 0;
			}
		}

		jobject paradigm = 0;

		for (int i=0; i<F.GetCount(); i++){
			string form = F.GetWordForm(i);
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

			string GramCodes = F.GetAncode(i);
			BYTE  PartOfSpeech = dic.pAgramtab->GetPartOfSpeech(GramCodes.c_str());
			//assert(dic.lang==Russian);
			rus_pos pos = (rus_pos) PartOfSpeech;

			for (long i=0; i < GramCodes.length(); i+=2){
				QWORD grammems2;
				bool ok2 = GetGrammems(dic, GramCodes.c_str()+i, grammems2);
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

jobject GenerateMorphInfoIntern(JNIEnv *env, jclass clazz, jni_dictionary& dic, vector<CFormInfo>& Paradigms, jobject paradigmset){
	for(int i=0; i < Paradigms.size(); i++){
		const CFormInfo& F = Paradigms[i];
		bool found = F.m_bFound;

		string baseForm = F.GetWordForm(0);
		jstring jbaseForm = createJStringCp1251(env, clazz, baseForm);

		//baseFormBytes is a local ref, no need to release.
		string GramCodes = F.GetSrcAncode();
		BYTE  PartOfSpeech = dic.pAgramtab->GetPartOfSpeech(GramCodes.c_str());
		//assert(dic.lang==Russian);
		rus_pos pos = (rus_pos) PartOfSpeech;

		string CommonAncode = F.GetCommonAncode();
		QWORD grammems=0;
		QWORD grammems1;
		bool ok = GetGrammems(dic, CommonAncode.c_str(), grammems1);
		if(ok){
			grammems=grammems1;
		}
		for (long i=0; i < GramCodes.length(); i+=2){
			QWORD grammems2;
			bool ok2=GetGrammems(dic, GramCodes.c_str()+i, grammems2);
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
jobject GenerateMorphInfo(JNIEnv *env, jclass clazz, jni_dictionary& dic, string& Form, bool norm){
//	try{

	jobject paradigmset = env->NewObject(setClazz, setConstructor);
	NULL_CHECK_RETURN(paradigmset)

	bool bCapital = is_upper_alpha((BYTE)Form[0], dic.Language);
	vector<CFormInfo> Paradigms;
	dic.pLemmatizer->CreateParadigmCollection(norm, Form, bCapital, true, Paradigms);

	if (norm){
		GenerateMorphForms(env, clazz, dic, Paradigms, paradigmset, bCapital);
	} else {
		GenerateMorphInfoIntern(env, clazz, dic, Paradigms, paradigmset);
	}

	jobject wordresult = env->CallStaticObjectMethod(clazz, method_wordresult_new, paradigmset);
	//paradigmset is a local ref, no need to release.
	NULL_CHECK_RETURN(wordresult)
	return wordresult;

//	}catch(CExpc e){
//		char* err=str_compose("C++ exception: CExpc: %s",e.m_strCause.c_str());
//		throwEx(env, err);
//	}catch(int e){
//		string errstr("C++ exception: int: ");
//		errstr+=e;
//		errstr+=".";
//		throwEx(env, strdup(errstr.c_str()));
//	}catch(...){
//		throwEx(env, strdup("Unknown C++ exception."));
//	}
//	return NULL;

}

jobject GetMorphInfo(JNIEnv *env, jclass clazz, jni_dictionary& dic, string& Form){
	return GenerateMorphInfo(env, clazz, dic, Form, false);
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
			throwEx(env, strdup("assertion error: A1"));
			return false;
	}

	string langua_str = GetStringByLanguage(dic.Language);
	dic.pLemmatizer = new T;
	string strError;
	if (!dic.pLemmatizer->LoadDictionariesRegistry(strError)){
   		char* err=str_compose("Cannot load %s morphological dictionary. Error details: %s", langua_str.c_str(), strError.c_str());
		throwEx(env, err);
		return false;
	}
	dic.pAgramtab = new Y;
	if (!dic.pAgramtab->LoadFromRegistry()){
   		char* err=str_compose("Cannot load %s gramtab.", langua_str.c_str());
		throwEx(env, err);
		return false;
	}
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
JNIEXPORT jobject JNICALL Java_ru_aot_morph_JavaMorphAPI_lookupWordImpl
  (JNIEnv *env, jclass clazz, jint languageId, jbyteArray word){
	  return lookupWordIternal(false, env, clazz, languageId, word);
}

//cp1251 charset for <code>word</code> - for Russian.
/*
 * Класс:	 ru_aot_morph_JavaMorphAPI
 * Метод:	lookupFormImpl
 * Сигнатура: (I[B)Lru/aot/morph/JavaMorphAPI/WordResult;
 */
JNIEXPORT jobject JNICALL Java_ru_aot_morph_JavaMorphAPI_lookupFormImpl
  (JNIEnv *env, jclass clazz, jint languageId, jbyteArray word){
	  return lookupWordIternal(true, env, clazz, languageId, word);
}

static jobject lookupWordIternal(bool normal, JNIEnv *env, jclass clazz, jint languageId, jbyteArray word){
	jbyte* bytes=NULL;
	char* chars=NULL;
	try{
		if(!inited||dic.pAgramtab==0||dic.pLemmatizer==0){
			throwEx(env, strdup("Dictionaries are not loaded. Call JavaMorphAPI.initDictionaries() first!"));
			return NULL;
		}
		if(languageId!=0){
			throwEx(env, strdup("The only language implemented is Russian."));
			return NULL;
		}
		NULL_CHECK_ONLY(word, "word is null")

		jsize len = env->GetArrayLength(word);
		bytes = env->GetByteArrayElements(word, NULL);
		NULL_CHECK_RETURN(bytes)

		chars=(char*)malloc(len+1);
		NULL_CHECK_ONLY(chars, NO_MEM_ERROR)

		for(jsize i=0;i<len;i++){
			chars[i] = (char)bytes[i];
		}
		chars[len]=(char)0;
		string s = chars;
		free(chars);chars=0;
		env->ReleaseByteArrayElements(word,bytes,JNI_ABORT);
		bytes=NULL;
		Trim(s);
		if (s.empty()){
			throwEx(env, strdup("Empty or whitespace-only string instead of a word."));
			return NULL;
		}
		if (normal){
			return GetMorphForms(env, clazz, dic, s);
		} else {
			return GetMorphInfo(env, clazz, dic, s);
		}
	}catch(CExpc& e){
		char* err=str_compose("C++ exception: CExpc: %s",e.m_strCause.c_str());
		throwEx(env, err);
		if(chars!=0){free(chars);chars=0;}
		if(bytes!=NULL){env->ReleaseByteArrayElements(word,bytes,JNI_ABORT);bytes=NULL;}
	}catch(int e){
		string errstr("C++ exception: int: ");
		errstr+=e;
		errstr+=".";
		throwEx(env, strdup(errstr.c_str()));
		if(chars!=0){free(chars);chars=0;}
		if(bytes!=NULL){env->ReleaseByteArrayElements(word,bytes,JNI_ABORT);bytes=NULL;}
	}catch(...){
		throwEx(env, strdup("Unknown C++ exception."));
		if(chars!=0){free(chars);chars=0;}
		if(bytes!=NULL){env->ReleaseByteArrayElements(word,bytes,JNI_ABORT);bytes=NULL;}
	}
	return NULL;
}

#define CHECKJAVAERROR(test, message ) \
		if (test){ \
		  throwEx(env,strdup(message)); \
		  Java_ru_aot_morph_JavaMorphAPI_closeImpl(env,clazz); \
		  return; \
		}


/*
 * Класс:	ru_aot_morph_JavaMorphAPI
 * Метод:	initImpl
 * Сигнатура: (I)V
 */
JNIEXPORT void JNICALL Java_ru_aot_morph_JavaMorphAPI_initImpl(JNIEnv *env, jclass clazz, jint languagesBitSet, jstring work_dir){
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

	if (0!=work_dir){
		const char* wdirPar = env->GetStringUTFChars(work_dir, JNI_FALSE);
		int res = setenv("RML", wdirPar, 0);
		env->ReleaseStringUTFChars(work_dir, wdirPar);
		CHECKJAVAERROR(res!=0, "RML not set")
	}

	JNIAPIExceptionClass = env->FindClass("ru/aot/morph/JavaMorphAPI$ИсключениеЯваСопряженияМорфологии");
	if (JNIAPIExceptionClass==NULL || env->ExceptionOccurred()){
	if(!env->ExceptionOccurred()){
		env->FatalError("JNIMorphAPI JNI: Cannot resolve exception class");
		return;
	}
	}
	JNIAPIExceptionClass = (jclass)env->NewGlobalRef(JNIAPIExceptionClass);
	CHECKJAVAERROR(JNIAPIExceptionClass==NULL || env->ExceptionOccurred(), "global ref error")

	setClazz = env->FindClass("java/util/HashSet");
	CHECKJAVAERROR( setClazz==NULL || env->ExceptionOccurred(), "Out of memory")

	setClazz = (jclass)env->NewGlobalRef(setClazz);
	CHECKJAVAERROR( setClazz==NULL || env->ExceptionOccurred(), "global ref error")

	setConstructor = env->GetMethodID(setClazz, "<init>", "()V");
	CHECKJAVAERROR( setConstructor==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_convertFromCharsetCp1251ToJavaString = env->GetStaticMethodID(clazz, "convertFromCP1251", "([B)Ljava/lang/String;");
	CHECKJAVAERROR( method_convertFromCharsetCp1251ToJavaString==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_grammemSetAddGrammem = env->GetStaticMethodID(clazz, "добавьГраммемуКМножеству", "(Ljava/util/HashSet;I)V");
	CHECKJAVAERROR( method_grammemSetAddGrammem==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_paradigmsetAddNewParadigm = env->GetStaticMethodID(clazz, "добавьПарадигмуКМножеству", "(Ljava/util/HashSet;Ljava/util/HashSet;Ljava/lang/String;ZI)V");
	CHECKJAVAERROR( method_paradigmsetAddNewParadigm==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_paradigmSetAddParadigm = env->GetStaticMethodID(clazz, "добавьПарадигму", "(Ljava/util/HashSet;Lru/aot/morph/JavaMorphAPI$РезультатСлова$Парадигма;)V");
	CHECKJAVAERROR( method_paradigmSetAddParadigm==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR)

	method_paradigmAddWordform = env->GetStaticMethodID(clazz, "добавьСловоформуКПарадигме", "(Lru/aot/morph/JavaMorphAPI$РезультатСлова$Парадигма;Ljava/lang/String;JI)Lru/aot/morph/JavaMorphAPI$РезультатСлова$Парадигма;");
	CHECKJAVAERROR( method_paradigmAddWordform==NULL || env->ExceptionOccurred(), NO_OBJ_MEM_ERROR);

	method_wordresult_new = env->GetStaticMethodID(clazz, "создайРезультатСлова", "(Ljava/util/HashSet;)Lru/aot/morph/JavaMorphAPI$РезультатСлова;");
	CHECKJAVAERROR( method_wordresult_new==NULL || env->ExceptionOccurred(), "method_wordresult_new is null")


	//setConstructor=(jmethodID)env->NewGlobalRef(setConstructor);if(setConstructor==NULL || env->ExceptionOccurred()){throwEx(env,strdup("global ref error"));Java_ru_aot_morph_JavaMorphAPI_closeImpl(env,clazz);return;}
	//method_convertFromCharsetCp1251ToJavaString=(jmethodID)env->NewGlobalRef(method_convertFromCharsetCp1251ToJavaString);if(method_convertFromCharsetCp1251ToJavaString==NULL || env->ExceptionOccurred()){throwEx(env,strdup("global ref error"));Java_ru_aot_morph_JavaMorphAPI_closeImpl(env,clazz);return;}
	//method_grammemSetAddGrammem=(jmethodID)env->NewGlobalRef(method_grammemSetAddGrammem);if(method_grammemSetAddGrammem==NULL || env->ExceptionOccurred()){throwEx(env,strdup("global ref error"));Java_ru_aot_morph_JavaMorphAPI_closeImpl(env,clazz);return;}
	//method_paradigmsetAddNewParadigm=(jmethodID)env->NewGlobalRef(method_paradigmsetAddNewParadigm);if(method_paradigmsetAddNewParadigm==NULL || env->ExceptionOccurred()){throwEx(env,strdup("global ref error"));Java_ru_aot_morph_JavaMorphAPI_closeImpl(env,clazz);return;}
	//method_wordresult_new=(jmethodID)env->NewGlobalRef(method_wordresult_new);if(method_wordresult_new==NULL || env->ExceptionOccurred()){throwEx(env,strdup("global ref error"));Java_ru_aot_morph_JavaMorphAPI_closeImpl(env,clazz);return;}

	inited=false;
	if(languagesBitSet==0){
		throwEx(env, strdup("The set of languages is empty."));
		return;
	}
	if(languagesBitSet!=1){
	throwEx(env, strdup("Russian is the only language supported by JavaMorphAPI."));
	return;
	}
	dic.Language=morphRussian;
	try{
		//dic.Language=morphEnglish;
		//dic.Language=morphGerman;

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
				throwEx(env,strdup("assertion error: A2."));
								return;
				};
				if (!bResult){
						Java_ru_aot_morph_JavaMorphAPI_closeImpl(env,clazz);
						return;//exception was thrown by InitMorphologySystem
				}

		inited=true;
		return;//ok
		}catch(CExpc& e){
				const char* ca=e.m_strCause.c_str();
				char* err=str_compose("C++ exception: CExpc: %s",ca);
				CHECKJAVAERROR(true, err)
		}catch(int e){
				string errstr("C++ exception: int: ");
				errstr+=e;
				errstr+=".";
				CHECKJAVAERROR(true, errstr.c_str())
		}catch(...){
				CHECKJAVAERROR(true, "Unknown C++ exception.")
		}
}

/*
 * Класс:	ru_aot_morph_JavaMorphAPI
 * Метод:	closeImpl
 * Сигнатура: ()V
 */
JNIEXPORT void JNICALL Java_ru_aot_morph_JavaMorphAPI_closeImpl
  (JNIEnv *env, jclass clazz){
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
		//if(method_convertFromCharsetCp1251ToJavaString!=NULL){env->DeleteGlobalRef(method_convertFromCharsetCp1251ToJavaString);method_convertFromCharsetCp1251ToJavaString=NULL;}
		//if(method_grammemSetAddGrammem!=NULL){env->DeleteGlobalRef(method_grammemSetAddGrammem);method_grammemSetAddGrammem=NULL;}
		//if(method_paradigmsetAddNewParadigm!=NULL){env->DeleteGlobalRef(method_paradigmsetAddNewParadigm);method_paradigmsetAddNewParadigm=NULL;}
		//if(method_wordresult_new!=NULL){env->DeleteGlobalRef(method_wordresult_new);method_wordresult_new=NULL;}

		inited=false;
		return;//ok
	}catch(CExpc& e){
		char* err=str_compose("C++ exception: CExpc: %s",e.m_strCause.c_str());
		throwEx(env, err);
		return;
	}catch(int e){
		string errstr("C++ exception: int: ");
		errstr+=e;
		errstr+=".";
		throwEx(env, strdup(errstr.c_str()));
		return;
	}catch(...){
		throwEx(env, strdup("Unknown C++ exception."));
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
	string Form = word;
	dic.pLemmatizer->CreateParadigmCollection(norm, Form, false, true, Paradigms);
	
	for(int i=0; i < Paradigms.size(); i++){
		const CFormInfo& F = Paradigms[i];
		bool found = F.m_bFound;
		cout << "Forms:=========\n";
		int cnt = F.GetCount();
		for (int i=0; i<cnt; i++){
			string form = F.GetWordForm(i);
			cout << form << "\n";
		}
		cout << "+++++++=========\n";

		string baseForm = F.GetWordForm(0);
		const char* chars = baseForm.c_str();
		jsize length = (jsize) strlen(chars);


		string GramCodes = F.GetSrcAncode();
		cout << "GramCodes:\t" << GramCodes << "\n";
		BYTE  PartOfSpeech = dic.pAgramtab->GetPartOfSpeech(GramCodes.c_str());
		//assert(dic.lang==Russian);
		rus_pos pos = (rus_pos) PartOfSpeech;

		string CommonAncode = F.GetCommonAncode();
		QWORD grammems=0;
		QWORD grammems1;
		bool ok = GetGrammems(dic, CommonAncode.c_str(), grammems1);
		if(ok){
			grammems=grammems1;
		}
		for (long i=0; i < GramCodes.length(); i+=2){
			QWORD grammems2;
			bool ok2=GetGrammems(dic, GramCodes.c_str()+i, grammems2);
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
