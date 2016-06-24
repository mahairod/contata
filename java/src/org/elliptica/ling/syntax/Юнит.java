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
public class Юнит {

	private int номер;

	private String частьРечи;

	@XmlElement
	public String getЧастьРечи() {
		return частьРечи;
	}

	public void setЧастьРечи(String частьРечи) {
		this.частьРечи = частьРечи;
	}

	@XmlElement
	public int getНомер() {
		return номер;
	}

	public void setНомер(int номер) {
		this.номер = номер;
	}

	private long граммемы;

	@XmlElement
	public long getГраммемы() {
		return граммемы;
	}

	public void setГраммемы(long граммемы) {
		this.граммемы = граммемы;
	}

}
