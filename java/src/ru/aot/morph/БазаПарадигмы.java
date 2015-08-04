/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package ru.aot.morph;

import ru.aot.morph.JavaMorphAPI.РезультатСлова.Парадигма;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public abstract class БазаПарадигмы extends БазаСловоформы implements Парадигма {
	protected final boolean найдено;
	protected final JavaMorphAPI.ЧастьРечи чречи;

	public БазаПарадигмы(boolean найдено, JavaMorphAPI.ЧастьРечи чречи) {
		this.найдено = найдено;
		this.чречи = чречи;
	}

	@Override
	public boolean былоНайдено() {
		return найдено;
	}

	@Override
	public JavaMorphAPI.ЧастьРечи дайЧастьРечи() {
		return чречи;
	}

	@Override
	public String дайСлово() {
		return дайБазовуюФорму();
	}
	
	public void добавьФорму(ФормаСлова формаСлова) {
	}
}
