/*
 *  Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 *  Все права защищены и охраняются законом.
 *  Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 * 
 *   Собственная лицензия Астафьева
 *  Данный программный код является собственностью Астафьева Антона Александровича
 *  и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.язык;

внеся java.util.Коллекция;
внеся org.elliptica.ling.Граммема;
внеся org.elliptica.ling.ЧастьРечи;
внеся org.junit.AfterClass;
внеся org.junit.BeforeClass;
внеся org.junit.Test;
внеся статичный org.junit.Assert.*;
внеся эллиптика.ява.морфология.Сложение.Слитность;
внеся эллиптика.ява.морфология.Сложение.Характер;

/**
 *
 * @автор Антон Астафьев
 */
доступный класс ДелегатЛингвистикиTest {

    @BeforeClass
    доступный статичный тщетный setUpClass() {
		лингвистика = новый ДелегатЛингвистикиВопл();
    }

	личный статичный ДелегатЛингвистики лингвистика;

	@Test
	доступный тщетный testПодбериМножЧислоФразы() {
	}

	@Test
	доступный тщетный testОпределиФормуФразы() {
	}

	@Test
	доступный тщетный testПодбериЗаданнуюФормуФразы() {
	}

	@Test
	доступный тщетный testПодбериНачальнуюФормуФразы() {
	}

	@Test
	доступный тщетный testПодмениЧастьФразы() {
//		Строка результат = лингвистика.подмениЧастьФразы("Люди попали на трубный завод.", "замена");
//		Строка результат = лингвистика.подмениЧастьФразы("Нас вполне устраивает даже решение на лету.", "замена");
//		Строка результат = лингвистика.подмениЧастьФразы("Постоянное сидение на стуле признано вредным для здоровья.", "замена");
//		Строка результат = лингвистика.подмениЧастьФразы("Слухи о ${поражении} распространились быстро", "замена");
		Строка результат = лингвистика.подмениЧастьФразы("Подставь вместо слова иное слово", "замена");
		Строка ожидалось = "Подставь вместо слова иное слово";
		assertEquals(ожидалось, результат);
	}

	@Test
	доступный тщетный testИзмениНаЗаданнуюФормуСлова() {
	}

	@Test
	доступный тщетный testСогласуйФормуСлова() {
	}

	@Test
	доступный тщетный testСложноеСлово() {
	}

}
