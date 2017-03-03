/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.морфемика;

внеся org.elliptica.ling.ЧастьРечи;

/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
доступный абстрактный класс АбстрактнаяМорфема расширяет ТиповаяМорфема {

	доступный АбстрактнаяМорфема(ЧастьРечи базоваяЧастьРечи, ЧастьРечи целеваяЧастьРечи) {
		это.базоваяЧастьРечи = базоваяЧастьРечи;
		это.целеваяЧастьРечи = целеваяЧастьРечи;
	}

	итоговый защищённый ЧастьРечи базоваяЧастьРечи;
	итоговый защищённый ЧастьРечи целеваяЧастьРечи;

	@Подмени
	доступный ЧастьРечи базоваяЧастьРечи() {
		верни базоваяЧастьРечи;
	}

	@Подмени
	доступный ЧастьРечи целеваяЧастьРечи() {
		верни целеваяЧастьРечи;
	}

}
