/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package org.elliptica.ling;

import java.util.Set;
import org.elliptica.ling.Morph.Граммема;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public interface Словоформа {
	Set<Граммема> дайГраммемы();
	String дайСлово();
	Morph.ЧастьРечи дайЧастьРечи();
}
