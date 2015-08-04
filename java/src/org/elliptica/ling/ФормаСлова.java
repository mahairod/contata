/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package org.elliptica.ling;

import java.util.HashSet;
import java.util.Set;
import org.elliptica.ling.Morph.Граммема;
import org.elliptica.ling.Morph.РезультатСлова.Парадигма;
import org.elliptica.ling.Morph.ЧастьРечи;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public class ФормаСлова extends БазаСловоформы implements Словоформа {

	public ФормаСлова(ЧастьРечи частьРечи, String слово, long граммемы, Парадигма парадигма){
		this.частьРечи = частьРечи;
		this.слово = слово;
		маскаГраммем = граммемы;
		this.парадигма = парадигма;
	}
	
	@Override
	public String дайСлово() {
		return слово;
	}
	@Override
	public ЧастьРечи дайЧастьРечи() {
		return частьРечи;
	}
	@Override
	public Set<Граммема> дайГраммемы(){
		Set<Граммема> result = new HashSet<>(Long.bitCount(маскаГраммем));
		Граммема[] граммемы = Граммема.values();
		long workCopy = маскаГраммем;
		while (workCopy!=0){
			long bit = Long.lowestOneBit(workCopy);
			workCopy ^= bit;
			int pos = Long.numberOfTrailingZeros(bit);
			Граммема граммема = граммемы[pos];
			result.add(граммема);
		}
		return result;
	}

	public long дайМаскуГраммем() {
		return маскаГраммем;
	}

	public Парадигма дайПарадигму() {
		return парадигма;
	}

	private final ЧастьРечи частьРечи;
	private final String слово;
	private final long маскаГраммем;
	private final Парадигма парадигма;
}
