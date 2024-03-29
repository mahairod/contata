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

внеся java.util.Список;
внеся org.elliptica.ling.Граммема;
внеся org.elliptica.ling.ЧастьРечи;
внеся эллиптика.ява.морфемика.логика.ОшибкаСловообразования;
внеся эллиптика.ява.морфемика.смыслы.СмысловойТип;

/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
доступный сопряжение Морфема {

	Строка значение();

	СмысловойТип смысловаяФорма();

	Граммема[] граммемы();

	ТипМорфемы дайТип();

	ЧастьРечи базоваяЧастьРечи();
	ЧастьРечи целеваяЧастьРечи();

	Список<Морфема> примениК(Список<Морфема> слово) кидает ОшибкаСловообразования;
	логическое применимК(Морфема то);
}
