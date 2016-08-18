#include "JObjectRef.h"

JObjectRef::JObjectRef(){

}

JObjectRef::~JObjectRef(){

}

std::ostream& JObjectRef::operator>>(std::ostream& ss) const {
	if ( !isActive() ){
		return ss;
	}
	ss << "<" << className();
	this->internal_export(ss);
	ss << "/>";
	return ss;
}

void JObjectRef::internal_export (std::ostream& ss) const {
	ss << " refid='" << className() << ":";
	reference(ss);
	ss << "'";
}
