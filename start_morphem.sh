#!/bin/sh

~/EllipticaJDK/build/elliptica-b22/images/j2sdk-image/bin/java -jar -Xbootclasspath/p:~/EllipticaJDK/build/comp20.jar java/dist/Linguitics.jar $*

#~/EllipticaJDK/build/elliptica-b22/images/j2sdk-image/bin/java \
#	-Xbootclasspath/p:/mnt/f16/mahairod/Develop/NetBeansProjects/NB81/main/nbbuild/netbeans/ide/modules/ext/jaxb/api/jaxb-api.jar \
#	-Dfile.encoding=UTF-8 \
#	-classpath `find . -name *.jar | grep -v Linguitics.jar | sed -e s,'$',':', | tr -d "\n" && echo java/build/classes` \
#	 эллиптика.ява.язык.сеть.Сервер