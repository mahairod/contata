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
public class КоллекцияФрагментов extends ОбъектСинт {

	@XmlElementWrapper
	@XmlElement(name = "фрагмент")
	public Список<Фрагмент> getСписокФрагментов() {
		return списокФрагментов;
	}

	@XmlElementWrapper
	@XmlElement(name = "связьФрагментов", type = ПорядковыйСписок.class)
	public Список<СвязьФрагментов> getСписокСвязейФрагментов() {
		return списокСвязейФрагментов;
	}

	Список<Фрагмент> списокФрагментов = new ПорядковыйСписок<>();
	Список<СвязьФрагментов> списокСвязейФрагментов = new ПорядковыйСписок<>();
}
