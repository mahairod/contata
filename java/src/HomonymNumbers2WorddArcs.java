
внеся java.util.ПорядковыйСписок;
внеся java.util.Список;

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
класс HomonymNumbers2WorddArcs {
	доступный HomonymNumbers m_HomonymNumbers = новый HomonymNumbers();
	доступный Список<WordArc> m_Arcs = новый ПорядковыйСписок<>();
	доступный Список<WordArc> m_SubjArcs = новый ПорядковыйСписок<>();

	доступный логическое equals(HomonymNumbers homonymNumbers) {
		верни m_HomonymNumbers.compareTo(homonymNumbers) == 0;
	}

}
