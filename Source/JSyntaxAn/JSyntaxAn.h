#ifndef _JSYNTAXAN_H_
#define _JSYNTAXAN_H_

#include <SyntaxHolder.h>
#include <JObject.h>


class JSyntaxAn: public JObject {
public:
    JSyntaxAn(jobject);
	jboolean processText(jobject, jstring);
private:
	CSyntaxHolder syntaxHolder;
	static jmethodID methId_fillList;
	static jclass jSyntaxClass;
};

#endif // _JSYNTAXAN_H_
