/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package ru.aot.morph;

import java.util.Set;
import ru.aot.morph.JavaMorphAPI.Граммема;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public interface Словоформа {
	Set<Граммема> дайГраммемы();
	String дайСлово();
	JavaMorphAPI.ЧастьРечи дайЧастьРечи();
}
