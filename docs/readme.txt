Java JNI API currently only supports Russian morphology extraction from input words.

The source C/C++ files for this JNI interface are at
https://sourceforge.net/projects/seman/ , see Source/JNI* folders there.

To run the Java API, you have to set the RML system enviroment variable containing the pathname for RML 
(RML variable must point to the trunk/ of svn of https://sourceforge.net/projects/seman/ ).

To run the Java API, you also have to build the RML, as this builds the dictionaries.

To test your installation, run the java class "ru.aot.morph.test.Test". If all is OK, it will output "TEST PASSED". 

You can also use the Test class as an example of the usage of this API.
   