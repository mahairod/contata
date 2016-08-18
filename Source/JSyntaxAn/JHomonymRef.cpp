#include "JHomonymRef.h"

JHomonymRef::JHomonymRef():JHomonymRef(-1,0){
}

JHomonymRef::JHomonymRef(int w, int h){
	word = w;
	homonym = h;
}

JHomonymRef::~JHomonymRef(){

}

std::string JHomonymRef::className() const {
	return "омоним";
}

void JHomonymRef::reference(std::ostream& ss) const {
	ss << word << ":" << homonym;
}

bool JHomonymRef::isActive() const {
	return word >= 0;
}
