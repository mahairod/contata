/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.морфология;

внеся java.util.Коллекции;
внеся org.elliptica.ling.JavaMorphAPI;
внеся org.elliptica.ling.РезультатСлова;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный класс МорфологияЯва {

    /**
     * @param аргы аргументы командной строки
     */
    доступный статичный тщетный main(Строка[] аргы) {
		Система.задайСвойство("JNIMorphAPI-rml-dir", "/mnt/f16/mahairod/Develop/eclipse-4.3/wsp/dialing/svn-native/sf");
		JavaMorphAPI.приготовьСловари(Коллекции.одиночка(JavaMorphAPI.Язык.Русский));
		РезультатСлова рс = JavaMorphAPI.формыСлова(JavaMorphAPI.Язык.Русский, "пример");
    }

}
