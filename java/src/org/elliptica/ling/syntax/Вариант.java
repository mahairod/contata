/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2015 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package org.elliptica.ling.syntax;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ява.инструм.ПорядковыйСписок;
import ява.инструм.Список;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
@XmlRootElement
public class Вариант extends ОбъектСинт {

	@XmlElementWrapper
	@XmlElement(name = "юнит")
	public Список<Юнит> getЮниты() {
		return юниты;
	}

	@XmlElementWrapper
	@XmlElement(name = "группа")
	public Список<Группа> getГруппы() {
		return группы;
	}

	Список<Юнит> юниты = new ПорядковыйСписок<>();
	Список<Группа> группы = new ПорядковыйСписок<>();
}
