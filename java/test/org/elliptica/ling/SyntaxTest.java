/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
пакет org.elliptica.ling;

внеся java.util.Список;
внеся org.elliptica.ling.syntax.Синтаксис;
внеся org.junit.After;
внеся org.junit.AfterClass;
внеся org.junit.Before;
внеся org.junit.BeforeClass;
внеся org.junit.Test;
внеся статичный org.junit.Assert.*;
внеся эллиптика.ява.язык.Система;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс SyntaxTest {
	
	доступный SyntaxTest() {
	}
	
	@BeforeClass
	доступный статичный тщетный setUpClass() {
		instance = новый Syntax(
				"/mnt/f16/mahairod/Develop/cpp/linguistan",
				"/mnt/f16/mahairod/Develop/cpp/linguistan/Source/JSyntaxAn/dist/Debug/GNU-Linux-x86/libJSyntaxAn.so"
		);
	}
	
	@AfterClass
	доступный статичный тщетный tearDownClass() {
	}
	
	@Before
	доступный тщетный setUp() {
	}
	
	@After
	доступный тщетный tearDown() {
	}

	/**
	 * Test of разборТекста method, of класс Syntax.
	 */
	@Test
	доступный тщетный testРазборТекста() {
		Система.вывод.печатьстр("РазборТекста");
		Строка текст = "В ходе поисков были обследованы более 70 тысяч квадратных километров, но никаких следов или обломков найти не удалось. С приходом зимы операцию пришлось прервать. Летом поиски возобновились. Работы осложнялись горным рельефом местности.";
		Список<Строка> результат = новый Синтаксис(instance).разборТекстаТест(текст);
		assertNotNull(результат);
		assertFalse(результат.isEmpty());
	}

	/**
	 * Test of finalize method, of класс Syntax.
	 * @кидает java.lang.Throwable
	 */
//	@Test
	доступный тщетный testFinalize() кидает Throwable {
		Система.вывод.печатьстр("finalize");
		instance.finalize();
	}
	
	личный статичный Syntax instance = ничто;
}
