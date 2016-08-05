VPATH = ../

include ../common/common_mak

common_parms := -I. -I../Jcommon $(JAVA_INCLUDES) -std=c++0x -fextended-identifiers -fvisibility=hidden

include ../common/common_mak

LibName = JMorph


objects = JMorph.o$(ct) ../Jcommon/JByteArray.o$(ct) ../Jcommon/jcommon.o$(ct) \
../common/utilit.o$(ct) \
../LemmatizerLib/Lemmatizers.o$(ct) \
../LemmatizerLib/Paradigm.o$(ct) \
../LemmatizerLib/CreatePredictionBase.o$(ct) \
../LemmatizerLib/PLMLineCollection.o$(ct) \
../LemmatizerLib/Statistic.o$(ct) \
../LemmatizerLib/MorphAutomat.o$(ct) \
../LemmatizerLib/MorphAutomBuilder.o$(ct) \
../LemmatizerLib/MorphDict.o$(ct) \
../LemmatizerLib/MorphDictBuilder.o$(ct) \
../common/PlmLine.o$(ct) \
../LemmatizerLib/Predict.o$(ct) \
../common/MorphologyHolder.o$(ct) \
../AgramtabLib/EngGramTab.o$(ct) \
../AgramtabLib/GerGramTab.o$(ct) \
../AgramtabLib/RusGramTab.o$(ct) \
../AgramtabLib/agramtab_.o$(ct) \
../MorphWizardLib/wizard.o$(ct) \
../PCRE/pcre_rml.o$(ct) \
../StructDictLib/ItemsContainer.o$(ct) \
../StructDictLib/Ross.o$(ct) \
../StructDictLib/TempArticle.o$(ct) \
../GraphanLib/C_desc.o$(ct) \
../GraphanLib/Consent.o$(ct) \
../GraphanLib/../common/gra_descr.o$(ct) \
../GraphanLib/Cs_hiera.o$(ct) \
../GraphanLib/GraphmatFile.o$(ct) \
../GraphanLib/HtmlConv.o$(ct) \
../GraphanLib/Nc_desc.o$(ct) \
../GraphanLib/Writegra.o$(ct) \
../GraphanLib/abbrev.o$(ct) \
../GraphanLib/graline.o$(ct) \
../GraphanLib/Oborots.o$(ct) \
../GraphanLib/UnitHolder.o$(ct) \
../GraphanLib/GraphanDicts.o$(ct) \
../GraphanLib/Sentences.o$(ct) \

#$(PCRE_OBJECTS_LIB)/libpcre.a \
#$(PCRE_OBJECTS_LIB)/libpcrecpp.a \
#$(PCRE_OBJECTS_LIB)/libpcreposix.a


libs =  ../PCRE/libPCRE$(ct).$(lib_ext)


include ../common/common_lib_mak
