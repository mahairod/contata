/*
 *  Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 *  Все права защищены и охраняются законом.
 *  Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 * 
 *   Собственная лицензия Астафьева
 *  Данный программный код является собственностью Астафьева Антона Александровича
 *  и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.морфемика.логика;

внеся java.util.Массивы;
внеся org.elliptica.ling.Граммема;
внеся статичный org.elliptica.ling.Граммема.*;
внеся org.elliptica.ling.ЧастьРечи;
внеся org.junit.BeforeClass;
внеся org.junit.Test;
внеся статичный org.junit.Assert.*;
внеся sun.reflect.Reflection;

/**
 *
 * @автор Антон Астафьев
 */
доступный класс ПодборщикTest {

    доступный ПодборщикTest() {
    }

    @BeforeClass
    доступный статичный тщетный setUpClass() {
		instance = новый Подборщик();
    }
	статичный Подборщик instance;

	@Test
	доступный тщетный testЗапуск() кидает Exception {
		System.out.println("запуск");
		Строка суффиксы[] = {"ат", "ив", "н", "ость"};
		Строка expResult = "декларативность";
		Строка result = instance.запуск("деклар", суффиксы);
		assertEquals(expResult, result);
	}

	личный статичный итоговый Строка КОНСТ_ативность = "/-ат-ив-н-ость/-ив-н-ость/-н-ость/-ивн-ость";

	@Test
	доступный тщетный декларативность() кидает Exception {
		запускСущ(КОНСТ_ативность);
	}
	@Test
	доступный тщетный декоративность() кидает Exception {
		запускСущ(КОНСТ_ативность);
	}
	@Test
	доступный тщетный декорация() кидает Exception {
		запускСущ("/-ациj");
	}
	@Test
	доступный тщетный демонстрация() кидает Exception {
		запускСущ("/-ациj");
	}

	@Test
	доступный тщетный консервирование() кидает Exception {
		Строка ож = "/-иров-а-ниj/-иров-а-ниj/-ова-ниj/-ирова-ниj";
		запускСущ(средний, ож+ож+ож+ож);
	}

	@Test
	доступный тщетный консервировка() кидает Exception {
		запускСущ(женский, "/-иров-к/-к/-к");
	}

	@Test
	доступный тщетный кассир() кидает Exception {
		запускСущ("/-ир");
	}

	тщетный запускСущ(Граммема род, Строка expResult){
		запускСущ_(expResult, род);
	}

	тщетный запускСущ(Строка expResult){
		запускСущ_(expResult);
	}

	тщетный запускСущ_(Строка expResult, Граммема... граммемы){
		Строка метод = Thread.currentThread().getStackTrace()[3].getMethodName();
		System.out.println(метод);
		Граммема[] грамм = Массивы.copyOf(граммемы, граммемы.length+2);
		грамм[граммемы.length] = именительный;
		грамм[граммемы.length+1] = единственное;
		instance.подбери(метод, ЧастьРечи.существительное, грамм);
		Строка рез = итог();
		assertEquals(expResult, рез);
	}

	личный Строка итог(){
		верни instance.итог.поток().map(спис -> спис.поток().map(морф -> морф.значение()).reduce("", (л,п) -> л+п)).reduce("", (л,п) -> л+"/"+п);
	}

}