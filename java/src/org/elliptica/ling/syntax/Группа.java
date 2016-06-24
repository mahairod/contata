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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
@XmlRootElement
public class Группа extends Диапазон {

	private String описание;
	private String тип;

	@XmlElement
	public String getОписание() {
		return описание;
	}

	public void setОписание(String описание) {
		this.описание = описание;
	}

	@XmlElement
	public String getТип() {
		return тип;
	}

	public void setТип(String тип) {
		this.тип = тип;
	}

}
