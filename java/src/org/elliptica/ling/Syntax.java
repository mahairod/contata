/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling;

внеся java.util.Список;
внеся java.util.logging.Logger;
внеся org.elliptica.ling.syntax.СинтаксисаВоплощение;
внеся эллиптика.ява.язык.Система;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс Syntax расширяет ОбъектЯва воплощает СинтаксисаВоплощение {
	доступный Syntax(Строка rootPath, Строка libPath) {
		это.rmlPath = rootPath;
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/LemmatizerLib/libLemmatizerrsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Bin/libAgramtabdsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/SynanDmnLib/libSynanDmn.so");
		загрузиБиблиотеку(rootPath + "/Source/JSyntaxAn/libJSynAndsh.so");
		init(rmlPath);
	}

	доступный Syntax(Строка rootPath) {
		это(rootPath, ничто);
	}

	личный туземный тщетный init(Строка rmlPath) кидает ОтклонениеМорфологии;
	личный туземный тщетный finalize0() кидает Throwable;
	доступный туземный Строка parseRawText(Список<Строка> список, Строка text);

	личный тщетный fillList(Список список, Строка line) {
		список.добавь(line);
	}

	@Подмени
	защищённый тщетный finalize() кидает Throwable {
		finalize0();
		поверх.finalize();
	}

	личный итоговый Строка rmlPath;

	личный статичный синхронизированный тщетный загрузиБиблиотеку(Строка libPath) {
		если (ложь && библЗагружена) верни;
		если (libPath!=ничто) {
			Система.загрузи(libPath);
		} иначе {
			System.loadLibrary("JSyntaxAn");
		}
		библЗагружена = истина;
	}
	личный статичный логическое библЗагружена;

	Logger LOG = Logger.getLogger(Syntax.класс.getName());

}
