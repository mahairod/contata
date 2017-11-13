/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.язык.сеть;

внеся org.elliptica.ling.Граммема;
внеся эллиптика.ява.язык.Лингвистика;
внеся эллиптика.ява.язык.СборщикСтрок;

/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
@ПодСервис("/согласование/повеление")
доступный класс СогласованиеПовеление расширяет ОтглагольноеСогласование {

	@Подмени
	доступный цел обработай(Строка запрос, СборщикСтрок вывод) кидает Exception {
		поверх.обработай(запрос, вывод);
		Строка[] слова = запрос.разорви(",");
		Строка форма = (Строка) Лингвистика.подбериЗаданнуюФормуФразы(
				слова[0].кМассивуСимволов(), Граммема.повелительное, Граммема.единственное, Граммема.второе);
		вывод.дополни(',');
		если (форма != ничто) {
			вывод.дополни(форма);
		} иначе {
			вывод.дополни(слова[0]);
		}
		верни Сервис.УСПЕХ;
	}

}
