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
public class Фрагмент extends Диапазон {

	@XmlElementWrapper
	@XmlElement(name = "вариант")
	public Список<Вариант> getВарианты() {
		return варианты;
	}

	@XmlElementWrapper
	@XmlElement(name = "позицияСоюза")
	public Список<ПозицияСоюза> getСоюзы() {
		return союзы;
	}

	@XmlElementWrapper
	@XmlElement(name = "типФрагмента")
	public Список<ТипФрагмента> getТипы() {
		return типы;
	}

	@XmlElement
	public ОбъектСинт getПредшествующий() {
		return предшествующий;
	}

	@XmlElement
	public int getЧислоПунктЗнаков() {
		return числоПунктЗнаков;
	}

	public Предложение getПредложение() {
		return предложение;
	}

	public void setПредшествующий(ОбъектСинт предшествующий) {
		this.предшествующий = предшествующий;
	}

	public void setЧислоПунктЗнаков(int числоПунктЗнаков) {
		this.числоПунктЗнаков = числоПунктЗнаков;
	}


	Список<Вариант> варианты = new ПорядковыйСписок<>();
	Список<ПозицияСоюза> союзы = new ПорядковыйСписок<>();
	Список<ТипФрагмента> типы = new ПорядковыйСписок<>();
	ОбъектСинт предшествующий;
	int числоПунктЗнаков;
	Предложение предложение;

	enum Тип {
		
	}
}
