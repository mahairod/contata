#ifndef _JHOMONYMREF_H_
#define _JHOMONYMREF_H_

#include "JObjectRef.h"

class JHomonymRef : public JObjectRef {
public:
	JHomonymRef();
	JHomonymRef(int w, int h);
	virtual ~JHomonymRef();
	virtual std::string className() const;
protected:
	virtual void reference(std::ostream& ss) const;
	virtual bool isActive() const;
private:
	int word;
	int homonym;
};

#endif // _JHOMONYMREF_H_
