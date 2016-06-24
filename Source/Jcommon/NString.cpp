/* 
 * Файл:   NString.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 9 августа 2015 г., 11:23
 */

#include "NString.h"
#include "jcommon.h"

using std::string;
using std::u16string;

static jstring createFromUTF8(const string& str);
static jstring createFromUCS(const u16string& str);

NString::NString(string str){
	utf8 = str;
	jstr = createFromUTF8(str);
}
NString::NString(u16string str){
	ucs = str;
	jstr = createFromUCS(str);
}

NString::NString(const NString& orig) {
}

NString::~NString() {
	if (jstr != NULL){
		env_ptr->DeleteLocalRef(jstr);
	}
	jstr = NULL;
}

jstring NString::jString(){
	return jstr;
}

static jstring createFromUTF8(const string& str){
	jstring jstr = env_ptr->NewStringUTF( str.c_str() );
	NULL_CHECK_EXC(jstr)
	return jstr;
}

static jstring createFromUCS(const u16string& str){
	jstring jstr = env_ptr->NewString( (const jchar*) str.c_str(), str.size() );
	NULL_CHECK_EXC(jstr)
	return jstr;
}

