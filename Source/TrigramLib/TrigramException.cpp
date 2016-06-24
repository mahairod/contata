#include "TrigramException.h"

TrigramException::TrigramException(std::string msg_):msg(msg_){
}

const char* TrigramException::what() const noexcept{
    return msg.c_str();
}

