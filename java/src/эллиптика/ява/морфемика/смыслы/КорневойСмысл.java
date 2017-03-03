/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.морфемика.смыслы;

внеся эллиптика.ява.морфемика.Корень;


/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс КорневойСмысл расширяет Смысл {

	доступный КорневойСмысл(Корень корень) {
		это.корень = корень;
	}

	@Подмени
	доступный Строка строкой(){
		верни корень.базоваяЧастьРечи().кратко() + " " + корень.значение();
	}

	личный итоговый Корень корень;
}
