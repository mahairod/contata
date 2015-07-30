/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package ru.aot.morph;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
abstract class БазаСловоформы implements Словоформа {
	@Override
	public String toString() {
		String результат = дайЧастьРечи().toString() + " " + дайСлово() + "\n";
		for (JavaMorphAPI.Граммема граммема: дайГраммемы()){
			результат += "\t" + граммема.toString() + "\n";
		}
		return результат;
	}

}
