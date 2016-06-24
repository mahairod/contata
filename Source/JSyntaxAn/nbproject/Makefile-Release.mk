#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-Linux-x86
CND_DLIB_EXT=so
CND_CONF=Release
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/_ext/58556586/JSyntaxAn.o \
	${OBJECTDIR}/_ext/1995833979/JByteArray.o \
	${OBJECTDIR}/_ext/1995833979/JObject.o \
	${OBJECTDIR}/_ext/1995833979/JString.o \
	${OBJECTDIR}/_ext/1995833979/NString.o \
	${OBJECTDIR}/_ext/1995833979/jcommon.o \
	${OBJECTDIR}/_ext/141722637/NGramFile.o \
	${OBJECTDIR}/JConjunction.o \
	${OBJECTDIR}/JFragment.o \
	${OBJECTDIR}/JFragmentCollection.o \
	${OBJECTDIR}/JFragmentRelation.o \
	${OBJECTDIR}/JFragmentType.o \
	${OBJECTDIR}/JGroup.o \
	${OBJECTDIR}/JHomonym.o \
	${OBJECTDIR}/JObjectSynt.o \
	${OBJECTDIR}/JPeriod.o \
	${OBJECTDIR}/JSentence.o \
	${OBJECTDIR}/JSyntaxTree.o \
	${OBJECTDIR}/JUnit.o \
	${OBJECTDIR}/JVariant.o \
	${OBJECTDIR}/JWord.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libJSyntaxAn.${CND_DLIB_EXT}

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libJSyntaxAn.${CND_DLIB_EXT}: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.cc} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libJSyntaxAn.${CND_DLIB_EXT} ${OBJECTFILES} ${LDLIBSOPTIONS} -shared -fPIC

${OBJECTDIR}/_ext/58556586/JSyntaxAn.o: /mnt/f16/mahairod/Develop/cpp/linguistan/Source/JSyntaxAn/JSyntaxAn.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/58556586
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/58556586/JSyntaxAn.o /mnt/f16/mahairod/Develop/cpp/linguistan/Source/JSyntaxAn/JSyntaxAn.cpp

${OBJECTDIR}/_ext/1995833979/JByteArray.o: /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/JByteArray.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/1995833979
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/1995833979/JByteArray.o /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/JByteArray.cpp

${OBJECTDIR}/_ext/1995833979/JObject.o: /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/JObject.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/1995833979
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/1995833979/JObject.o /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/JObject.cpp

${OBJECTDIR}/_ext/1995833979/JString.o: /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/JString.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/1995833979
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/1995833979/JString.o /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/JString.cpp

${OBJECTDIR}/_ext/1995833979/NString.o: /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/NString.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/1995833979
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/1995833979/NString.o /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/NString.cpp

${OBJECTDIR}/_ext/1995833979/jcommon.o: /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/jcommon.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/1995833979
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/1995833979/jcommon.o /mnt/f16/mahairod/Develop/cpp/linguistan/Source/Jcommon/jcommon.cpp

${OBJECTDIR}/_ext/141722637/NGramFile.o: /mnt/f16/mahairod/Develop/cpp/linguistan/Source/TrigramLib/NGramFile.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/141722637
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/141722637/NGramFile.o /mnt/f16/mahairod/Develop/cpp/linguistan/Source/TrigramLib/NGramFile.cpp

${OBJECTDIR}/JConjunction.o: JConjunction.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JConjunction.o JConjunction.cpp

${OBJECTDIR}/JFragment.o: JFragment.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JFragment.o JFragment.cpp

${OBJECTDIR}/JFragmentCollection.o: JFragmentCollection.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JFragmentCollection.o JFragmentCollection.cpp

${OBJECTDIR}/JFragmentRelation.o: JFragmentRelation.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JFragmentRelation.o JFragmentRelation.cpp

${OBJECTDIR}/JFragmentType.o: JFragmentType.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JFragmentType.o JFragmentType.cpp

${OBJECTDIR}/JGroup.o: JGroup.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JGroup.o JGroup.cpp

${OBJECTDIR}/JHomonym.o: JHomonym.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JHomonym.o JHomonym.cpp

${OBJECTDIR}/JObjectSynt.o: JObjectSynt.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JObjectSynt.o JObjectSynt.cpp

${OBJECTDIR}/JPeriod.o: JPeriod.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JPeriod.o JPeriod.cpp

${OBJECTDIR}/JSentence.o: JSentence.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JSentence.o JSentence.cpp

${OBJECTDIR}/JSyntaxTree.o: JSyntaxTree.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JSyntaxTree.o JSyntaxTree.cpp

${OBJECTDIR}/JUnit.o: JUnit.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JUnit.o JUnit.cpp

${OBJECTDIR}/JVariant.o: JVariant.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JVariant.o JVariant.cpp

${OBJECTDIR}/JWord.o: JWord.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -I/usr/java/jdk1.8.0_45/include -I/usr/java/jdk1.8.0_45/include/linux -fPIC  -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/JWord.o JWord.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libJSyntaxAn.${CND_DLIB_EXT}

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
