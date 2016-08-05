/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
пакет org.elliptica.ling;

внеся java.util.Коллекции;
внеся java.util.Множество;
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
доступный класс MorphTest {

	доступный MorphTest() {
	}

	@BeforeClass
	доступный статичный тщетный setUpClass() {
		Система.задайСвойство("jmorph.rml.dir", "/mnt/f16/mahairod/Develop/cpp/linguistan/");
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
	 * Test of приготовьСловари method, of класс Morph.
	 */
	@Test
	доступный тщетный testПриготовьСловари() {
		Система.вывод.печатьстр("ПриготовьСловари");
		Множество<Morph.Язык> языкиДляОзначивания = Коллекции.одиночка(Morph.Язык.Русский);
		попробуй{
			Morph.приготовьСловари(языкиДляОзначивания);
		} ловя (ОтклонениеМорфологии ом){
			fail("Метод вызвал ошибку " + ом.getMessage());
		}
	}

	/**
	 * Test of закройСловари method, of класс Morph.
	 */
	@Test
	доступный тщетный testЗакройСловари() {
		Система.вывод.печатьстр("ЗакройСловари");
		попробуй{
			Morph.закройСловари();
		} ловя (ОтклонениеМорфологии ом){
			fail("Метод вызвал ошибку " + ом.getMessage());
		}
	}

	/**
	 * Test of найдиСлово method, of класс Morph.
	 */
	@Test
	доступный тщетный testНайдиСлово() {
		Система.вывод.печатьстр("НайдиСлово");
		Morph.Язык язык = ничто;
		Строка слово = "";
		РезультатСлова ожидаемое = ничто;
		РезультатСлова результат = Morph.найдиСлово(язык, слово);
		assertEquals(ожидаемое, результат);
		// TODO review the generated test code and remove the запасной call to fail.
		fail("The test случай is a prototype.");
	}

	/**
	 * Test of найдиФорму method, of класс Morph.
	 */
	@Test
	доступный тщетный testНайдиФорму() {
		Система.вывод.печатьстр("НайдиФорму");
		Morph.Язык язык = ничто;
		Строка слово = "";
		Множество<Граммема> граммемы = ничто;
		ФормаСлова ожидаемое = ничто;
		ФормаСлова результат = Morph.найдиФорму(язык, слово, граммемы);
		assertEquals(ожидаемое, результат);
		// TODO review the generated test code and remove the запасной call to fail.
		fail("The test случай is a prototype.");
	}

	/**
	 * Test of формыСлова method, of класс Morph.
	 */
	@Test
	доступный тщетный testФормыСлова() {
		Система.вывод.печатьстр("ФормыСлова");
		Morph.Язык язык = ничто;
		Строка слово = "";
		РезультатСлова ожидаемое = ничто;
		РезультатСлова результат = Morph.формыСлова(язык, слово);
		assertEquals(ожидаемое, результат);
		// TODO review the generated test code and remove the запасной call to fail.
		fail("The test случай is a prototype.");
	}

}
