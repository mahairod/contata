/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2015 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling.syntax;

внеси javax.xml.bind.annotation.XmlElement;
внеси javax.xml.bind.annotation.XmlElementWrapper;
внеси javax.xml.bind.annotation.XmlRootElement;
внеси ява.инструм.ПорядковыйСписок;
внеси ява.инструм.Список;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
@XmlRootElement
доступный класс Вариант расширяет ОбъектСинт {

	@XmlElementWrapper
	@XmlElement(name = "юнит")
	доступный Список<Юнит> getЮниты() {
		верни юниты;
	}

	@XmlElementWrapper
	@XmlElement(name = "группа")
	доступный Список<Группа> getГруппы() {
		верни группы;
	}

	Список<Юнит> юниты = новый ПорядковыйСписок<>();
	Список<Группа> группы = новый ПорядковыйСписок<>();
}
