#ifndef _TRIGRAMEXCEPTION_H_
#define _TRIGRAMEXCEPTION_H_

#include <exception>
#include <string>


class TrigramException : public std::exception{
    const std::string msg;
public:
    TrigramException(std::string msg);
    virtual const char* what() const noexcept;
};

#endif // _TRIGRAMEXCEPTION_H_
