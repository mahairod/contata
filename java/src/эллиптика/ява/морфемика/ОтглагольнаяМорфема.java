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
 * @автор Антон Астафьев
 */
доступный сопряжение ОтглагольнаяМорфема расширяет Морфема {

	@Подмени
	запасной ЧастьРечи базоваяЧастьРечи() {
		верни ЧастьРечи.глагол;
	}
}
