#include <string>

#include "JSyntaxAn.h"
#include "org_elliptica_ling_Syntax.h"
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

jboolean JSyntaxAn::processText(jobject jlist, jstring textJstr){
	JString jstr(textJstr);
	try{
		if ( !syntaxHolder.GetSentencesFromSynAn( jstr.cp1251str(), false ) ){
			return JNI_FALSE;
		}
	} catch(std::exception& ex){
		throwException(ex.what());
		throw JNIException();
	}
	vector<std::string>& stringList = syntaxHolder.m_PlmLines.m_Items;
	for (vector<std::string>::iterator it = stringList.begin(); it != stringList.end(); ++it ){
		std::u16string ucsStr = convert_to_ucs(*it, cp1251);
		NString nString(ucsStr);
		env_ptr->CallVoidMethod(jthis, methId_fillList, jlist, nString.jString());
		SIMPLE_CHECK_EXC
	}
	return JNI_TRUE;
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

jboolean JNICALL Java_org_elliptica_ling_Syntax_parseRawText(JNIEnv * env, jobject jthis, jobject resultJlist, jstring textJstr) {
	env_ptr = env;
	try{
		JSyntaxAn* thisObj = getObjectById<JSyntaxAn>(jthis);
		return thisObj->processText(resultJlist, textJstr);
	} catch (JNIException& ex){
		return false;
	}
}
