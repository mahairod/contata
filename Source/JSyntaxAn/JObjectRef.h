#ifndef JOBJECTREF_H
#define JOBJECTREF_H

#include "JObjectSynt.h"

class JObjectRef : public JObjectSynt {
public:
	JObjectRef();
	virtual ~JObjectRef();
	virtual std::ostream& operator>>(std::ostream& ss) const;
protected:
	virtual void internal_export (std::ostream& ss) const;
	virtual void reference(std::ostream& ss) const = 0;
	virtual bool isActive() const = 0;
};

#endif // JOBJECTREF_H
