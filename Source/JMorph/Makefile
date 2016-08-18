VPATH = ../

include ../common/common_mak

common_parms := -I. -I../Jcommon $(JAVA_INCLUDES) -std=c++0x -fextended-identifiers -fvisibility=hidden -D HAVE_CONFIG_H

include ../common/common_mak

LibName = JMorph


objects = JMorph.o$(ct) \
../Jcommon/JByteArray.o$(ct) \
../Jcommon/jcommon.o$(ct) \
../Jcommon/JString.o$(ct) \
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


pcre = \
../PCRE/pcre_tables.o$(ct)\
../PCRE/pcre_get.o$(ct)\
../PCRE/pcre_study.o$(ct)\
../PCRE/pcre_ucp_searchfuncs.o$(ct)\
../PCRE/pcre_rml.o$(ct)\
../PCRE/pcre_xclass.o$(ct)\
../PCRE/pcre_dfa_exec.o$(ct)\
../PCRE/pcre_newline.o$(ct)\
../PCRE/pcreposix.o$(ct)\
../PCRE/pcre_info.o$(ct)\
../PCRE/pcre_refcount.o$(ct)\
../PCRE/pcre_globals.o$(ct)\
../PCRE/pcre_version.o$(ct)\
../PCRE/pcre_valid_utf8.o$(ct)\
../PCRE/pcre_config.o$(ct)\
../PCRE/pcre_exec.o$(ct)\
../PCRE/pcre_chartables.o$(ct)\
../PCRE/pcre_fullinfo.o$(ct)\
../PCRE/pcre_try_flipped.o$(ct)\
../PCRE/pcre_maketables.o$(ct)\
../PCRE/pcre_compile.o$(ct)\
../PCRE/pcre_ord2utf8.o$(ct)\

objects += $(pcre)


#$(PCRE_OBJECTS_LIB)/libpcre.a \
#$(PCRE_OBJECTS_LIB)/libpcrecpp.a \
#$(PCRE_OBJECTS_LIB)/libpcreposix.a


libs =  ../PCRE/libPCRE$(ct).$(lib_ext)


include ../common/common_lib_mak
