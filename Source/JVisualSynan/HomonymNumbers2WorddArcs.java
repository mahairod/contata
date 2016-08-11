
import java.util.Vector;

/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
class HomonymNumbers2WorddArcs {
	public HomonymNumbers m_HomonymNumbers;
	public Vector m_Arcs;
	public Vector m_SubjArcs;

	public boolean equals(HomonymNumbers homonymNumbers) {
		return m_HomonymNumbers.compareTo(homonymNumbers) == 0;
	}

}
