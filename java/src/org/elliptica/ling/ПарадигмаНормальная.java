/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package org.elliptica.ling;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
class ПарадигмаНормальная extends БазаПарадигмы {
	private final HashSet<Граммема> множествоГраммем;
	private final String базоваяФорма;

	public ПарадигмаНормальная(boolean найдено, ЧастьРечи чречи, HashSet<Граммема> множествоГраммем, String базоваяФорма) {
		super(найдено, чречи);
		this.множествоГраммем = множествоГраммем;
		this.базоваяФорма = базоваяФорма;
	}

	@Override
	public Set<Граммема> дайГраммемы() {
		return множествоГраммем;
	}

	@Override
	public String дайБазовуюФорму() {
		return базоваяФорма;
	}

	@Override
	public List<ФормаСлова> формы() {
		return Collections.EMPTY_LIST;
	}

}
