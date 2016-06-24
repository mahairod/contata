VPATH = ../

include ../common/common_mak

common_parms := -I. $(JAVA_INCLUDES) -I../SynanDmnLib -I../common -I../Jcommon -std=c++0x -fextended-identifiers -fvisibility=hidden -g -rdynamic -D USE_TRIGRAM_LEMMATIZER

include ../common/common_mak

LibName = libJSynAn$(ct)


#objects = JSyntaxAn.o$(ct) \
../Jcommon/JObject.o$(ct) ../Jcommon/JByteArray.o$(ct) ../Jcommon/jcommon.o$(ct) \
../Jcommon/JString.o$(ct)\
../SynanDmnLib/Morphan.o$(ct) ../SynanDmnLib/SynanDmn.o$(ct) \
../common/SyntaxHolder.o$(ct) ../common/BigramsReader.o$(ct) ../common/string_socket.o$(ct) \
../LemmatizerLib/libLemmatizerdst.a  ../GraphanLib/libGraphandst.a ../StructDictLib/libStructDictdst.a ../SynCommonLib/libSynCommondst.a \
../AgramtabLib/EngGramTab.odst ../AgramtabLib/GerGramTab.odst ../AgramtabLib/RusGramTab.odst ../AgramtabLib/agramtab_.odst

#libs =  ../SynanDmnLib/libSynanDmn.$(lib_ext)

objects = JSyntaxAn.o$(ct) \
../Jcommon/JObject.o$(ct) ../Jcommon/JByteArray.o$(ct) ../Jcommon/jcommon.o$(ct) \
../Jcommon/JString.o$(ct) ../Jcommon/NString.o$(ct) \

include allobjects.mk

cparams += -ftemplate-depth=100
lparams += -rdynamic

include ../common/common_lib_mak
