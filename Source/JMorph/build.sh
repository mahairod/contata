cd `dirname $0`
export RML=`pwd`/../../
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk
export JAVA_INCLUDES="-I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/"
export PCRE_HOME=/home/mahairod/rpmbuild/BUILDROOT/pcre-8.12-9.fc16.x86_64/usr
export PCRE_OBJECTS_LIB=$PCRE_HOME/lib64

echo используем Ява в $JAVA_HOME
echo используем pcre в $PCRE_OBJECTS_LIB

make mode=debug libmode=shared

