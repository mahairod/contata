/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling;

внеси java.util.Arrays;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный переч ТипГраммемы {

	НетТипа, Число, Падеж, Род, Время, Лицо, Одушевлённость, Вид, Переходность, Залог, Наклонение, Степень_сравнения, Разряд, Стиль, Собственное, Специальный_падеж;

	личный статичный итоговый Граммема[][] границы = новый Граммема[ ТипГраммемы.values().length ][];
	
	тщетный updateLimits(Граммема граммема){
		цел номер = ordinal();
		если (границы[номер]==ничто){
			границы[номер] = новый Граммема[] {граммема, граммема};
			верни;
		}
		Граммема[] typeLimits = границы[номер];
		если			(граммема.ordinal() < typeLimits[0].ordinal() ){
			typeLimits[0] = граммема;
		} иначе если	(граммема.ordinal() > typeLimits[1].ordinal() ){
			typeLimits[1] = граммема;
		}
	}
	
	защищённый Строка имя() {
		выбери (это) {
			случай Переходность:
				верни "глагол";
			случай Одушевлённость:
				верни "имя";
			случай Степень_сравнения:
				верни "степень сравнения";
			запасной:
				символ[] chars = name().toCharArray();
				chars[0] = Character.toLowerCase(chars[0]);
				верни новый Строка(chars);
		}
	}
	
	доступный Граммема[] границы(){
		верни границы[ordinal()];
	}

	доступный Граммема[] значения() {
		Граммема[] диапазон = границы();
		верни Arrays.copyOfRange(Граммема.values(), диапазон[0].ordinal(), диапазон[1].ordinal() + 1);
	}
	
	доступный ширцел маска(){
		если (маска==ничто){
			Граммема[] диапазон = границы();
			маска = ( 1L << (диапазон[1].ordinal()+1) ) - ( 1L <<  диапазон[0].ordinal() );
		}
		верни маска;
	}
	
	личный Long маска;

}
