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
import org.elliptica.ling.Лемма;
import ява.инструм.ПорядковыйСписок;
import ява.инструм.Список;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
@XmlRootElement
public class Слово extends Лемма {
	public Слово(){
		
	}
	@XmlElement
	public String getЗначение() {
		return значение;
	}

	public void setЗначение(String значение) {
		this.значение = значение;
	}
	
	private String значение;

	@XmlElement(name = "омоним")
	@XmlElementWrapper
	public Список<Омоним> getОмонимы() {
		return омонимы;
	}

	Список<Омоним> омонимы = new ПорядковыйСписок<>();
}
