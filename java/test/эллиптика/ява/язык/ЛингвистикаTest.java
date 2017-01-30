/*
 *  Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 *  Все права защищены и охраняются законом.
 *  Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *   Собственная лицензия Астафьева
 *  Данный программный код является собственностью Астафьева Антона Александровича
 *  и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.язык;

внеся java.util.logging.Logger;
внеся org.elliptica.ling.Граммема;
внеся статичный org.elliptica.ling.Граммема.*;
внеся org.elliptica.ling.ЧастьРечи;
внеся org.junit.AfterClass;
внеся org.junit.BeforeClass;
внеся org.junit.Test;
внеся статичный org.junit.Assert.*;

/**
 *
 * @author Антон Астафьев
 */
доступный класс ЛингвистикаTest {

    доступный ЛингвистикаTest() {
    }

    @BeforeClass
    доступный статичный тщетный setUpClass() {
    }

    @AfterClass
    доступный статичный тщетный tearDownClass() {
    }

	@Test
	доступный тщетный testПодбораЗаданойФормыФразы(){
		Лингвистика.подбериЗаданнуюФормуФразы("размер".кМассивуСимволов(), дательный);
	}

	@Test
	доступный тщетный testСогласуйФормуСлова(){
		assertEquals("размер имелся", согласуй("размер", "имелся", ЧастьРечи.глагол));
		assertEquals("размер имелся", согласуй("размер", "имелся", ЧастьРечи.глагол));
		assertEquals("размер имелся", согласуй("размер", "имелась", ЧастьРечи.глагол));
		assertEquals("метро имелось", согласуй("метро", "имелась", ЧастьРечи.глагол));
		assertEquals("стена разрушится", согласуй("стена", "разрушатся", ЧастьРечи.глагол));
		assertEquals("стена разрушилась", согласуй("стена", "разрушился", ЧастьРечи.глагол));
		assertEquals("метро имеет", согласуй("метро", "имею", ЧастьРечи.глагол));
		assertEquals("метро имело", согласуй("метро", "имели", ЧастьРечи.глагол));
	}

	личный статичный Строка согласуй(Строка образец, Строка исходное, ЧастьРечи частьРечи){
		верни (Строка) Лингвистика.согласуйФормуСлова(
				образец.кМассивуСимволов(),
				исходное.кМассивуСимволов(),
				частьРечи);
	}

	@Test
	доступный тщетный testСогласуйЧастнуюФормуСлова() {
		символ[] символыФразы = "коллекция".кМассивуСимволов();
		Граммема[] роды = Лингвистика.определиРодФразы(символыФразы);
		assertEquals("выбранная", Лингвистика.измениФормуСлова("выбранный".кМассивуСимволов(), ЧастьРечи.причастие, роды[0]));
	}

	статичный итоговый Logger ЛОГ = Logger.getLogger(ЛингвистикаTest.класс.дайИмя());

}
