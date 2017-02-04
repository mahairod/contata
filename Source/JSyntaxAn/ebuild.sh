cd `dirname $0`
export RML=`pwd`/../../
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk
export JAVA_INCLUDES="-I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/"
echo используем Ява в $JAVA_HOME

make -f Makefile_exe mode=debug

