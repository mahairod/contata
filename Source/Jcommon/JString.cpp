/* 
 * Файл:   JString.cpp
 * Автор: Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * 
 * Создан 7 августа 2015 г.
 */
#include "JString.h"
#include "jcommon.h"
#include <iconv.h>
#include <string.h>
#include <iostream>

using std::string;
using std::u16string;

JString::JString(jstring str){
	const jchar* jchars = env_ptr->GetStringChars(str, JNI_FALSE);
	NULL_CHECK_EXC(jchars)
	native = (char16_t*) jchars;
	env_ptr->ReleaseStringChars(str, jchars);
	SIMPLE_CHECK_EXC
}

int JString::length() const{
	return native.length();
}
/*
wstring JString::content() const{
	return native;

}
*/
string JString::cp1251str() const{
	if (cp1251_str.empty()){
		string& str = const_cast<string&>(cp1251_str);
        str = convert_to_chars(native, cp1251);
    }
	return cp1251_str;
}

string JString::cp1252str() const{
	if (cp1252_str.empty()){
		string& str = const_cast<string&>(cp1252_str);
        str = convert_to_chars(native, cp1252);
	}
	return cp1252_str;
}

static const char* const encodings [] = {
	"CP1251",
	"CP1252",
	"CP1253",
	"CP1254",
	"CP1255",
	"CP1256",
	"CP1257",
	"CP1258"
};

const char UTF16_LITER[] = "UTF16";

static void reportError();

u16string convert_to_ucs(string str, const encoding_id enc_id) {
	if (str.empty()){
		return u"";
	}
	const char* const enc = encodings[enc_id];
	iconv_t cd_sb_ucs = iconv_open(UTF16_LITER, enc);
	if (cd_sb_ucs<0){
		std::cerr << "Unable to use iconv. Error:" << errno << std::endl;
	}
	
	char* inp = (char*) str.c_str();
	size_t sym_num = str.size();
	char16_t* out_buf = new char16_t[sym_num];

	size_t in_sz_b = sizeof(char) * sym_num;
	size_t out_sz_b = sizeof(char16_t) * sym_num;
	char* outp = (char*) out_buf;
	size_t res = iconv(cd_sb_ucs, &inp, &in_sz_b, &outp, &out_sz_b);
	if (res<0){
		reportError();
	}

	u16string ustr(out_buf, sym_num - out_sz_b/sizeof(char16_t));

	delete out_buf;
	iconv_close(cd_sb_ucs);

	return ustr;
}

string convert_to_chars(u16string str, const encoding_id enc_id) {
	if (str.empty()){
		return "";
	}
	const char* const enc = encodings[enc_id];
	iconv_t cd_ucs_sb = iconv_open(enc, UTF16_LITER);
	if (cd_ucs_sb<0){
		std::cerr << "Unable to use iconv. Error:" << errno << std::endl;
	}
	
	char16_t* inp = (char16_t*) str.c_str();
	size_t sym_num = str.size();
	char* out_buf = new char[sym_num];

	size_t in_sz_b = sizeof(char16_t) * sym_num;
	size_t out_sz_b = sizeof(char) * sym_num;
	char* outp = (char*) out_buf;
	size_t res = iconv(cd_ucs_sb, (char**)&inp, &in_sz_b, &outp, &out_sz_b);
	if (res<0){
		reportError();
	}

	string bstr(out_buf, sym_num - out_sz_b/sizeof(char));

	delete out_buf;
	iconv_close(cd_ucs_sb);

	return bstr;
}

static void reportError(){
	char* errDescr = "unknown";
	switch(errno){
		case E2BIG:
			errDescr = "no space";
			break;
		case EILSEQ:
			errDescr = "incorrect input string";
			break;
		case EINVAL:
			errDescr = "incomplete string";
			break;
		default:
			errDescr = "unknown";
	}
	std::cerr << "Error conerting to chars: " << errDescr << std::endl;
}
