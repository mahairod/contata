/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package org.elliptica.ling;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public interface Парадигма {

	boolean былоНайдено();

	String дайБазовуюФорму();

	ЧастьРечи дайЧастьРечи();

	Set<Граммема> дайГраммемы();

	List<ФормаСлова> формы();

	@Override
	String toString();
	
}
