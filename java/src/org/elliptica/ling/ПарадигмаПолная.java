/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package org.elliptica.ling;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public class ПарадигмаПолная extends БазаПарадигмы {

	public ПарадигмаПолная(boolean найдено, ЧастьРечи чречи) {
		super(найдено, чречи);
		формыСлова = new ArrayList<>(12);
	}

	@Override
	public void добавьФорму(ФормаСлова формаСлова) {
		формыСлова.add(формаСлова);
	}

	@Override
	public List<ФормаСлова> формы() {
		return формыСлова;
	}

	@Override
	public Set<Граммема> дайГраммемы() {
		return формы().get(0).дайГраммемы();
	}

	@Override
	public String дайБазовуюФорму() {
		return формы().get(0).дайСлово();
	}
	
	private final List<ФормаСлова> формыСлова;

}
