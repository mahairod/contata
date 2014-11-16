echo Параметры: « $* »
class=`echo $1 | sed -e s/"bin\/"// | sed -e s/"\.class"// | sed -e s/"\/"/"\."/g`
echo Запускаем $class
RML=/media/home2/mahairod/Develop/eclipse-4.3/wsp/dialing/svn-native/sf/ /media/home2/mahairod/Develop/eclipse-4.3/wsp/RuOpenJDK-8/jdk/bin/java -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -cp bin $class
#-verbose:jni -verbose:class
