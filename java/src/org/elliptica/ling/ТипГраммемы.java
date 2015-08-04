/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package org.elliptica.ling;

import java.util.Arrays;
import org.elliptica.ling.Morph.Граммема;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public enum ТипГраммемы {

	НетТипа, Число, Падеж, Род, Время, Лицо, Одушевлённость, Вид, Переходность, Залог, Наклонение, Степень_сравнения, Разряд, Стиль, Собственное, Специальный_падеж;

	private static final Граммема[][] границы = new Граммема[ ТипГраммемы.values().length ][];
	
	void updateLimits(Граммема граммема){
		int номер = ordinal();
		if (границы[номер]==null){
			границы[номер] = new Граммема[] {граммема, граммема};
			return;
		}
		Граммема[] typeLimits = границы[номер];
		if			(граммема.ordinal() < typeLimits[0].ordinal() ){
			typeLimits[0] = граммема;
		} else if	(граммема.ordinal() > typeLimits[1].ordinal() ){
			typeLimits[1] = граммема;
		}
	}
	
	protected String имя() {
		switch (this) {
			case Переходность:
				return "глагол";
			case Одушевлённость:
				return "имя";
			case Степень_сравнения:
				return "степень сравнения";
			default:
				char[] chars = name().toCharArray();
				chars[0] = Character.toLowerCase(chars[0]);
				return new String(chars);
		}
	}
	
	public Граммема[] границы(){
		return границы[ordinal()];
	}

	public Morph.Граммема[] значения() {
		Граммема[] диапазон = границы();
		return Arrays.copyOfRange(Граммема.values(), диапазон[0].ordinal(), диапазон[1].ordinal() + 1);
	}
	
	public long маска(){
		if (маска==null){
			Граммема[] диапазон = границы();
			маска = ( 1L << (диапазон[1].ordinal()+1) ) - ( 1L <<  диапазон[0].ordinal() );
		}
		return маска;
	}
	
	private Long маска;

}
