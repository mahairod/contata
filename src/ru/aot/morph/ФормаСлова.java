/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет ru.aot.morph;

внеся java.util.HashSet;
внеся java.util.Множество;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный класс ФормаСлова расширяет БазаСловоформы воплощает Словоформа {

	доступный ФормаСлова(ЧастьРечи частьРечи, Строка слово, ширцел граммемы, Парадигма парадигма){
		это.частьРечи = частьРечи;
		это.слово = слово;
		маскаГраммем = граммемы;
		это.парадигма = парадигма;
	}
	
	@Подмени
	доступный Строка дайСлово() {
		верни слово;
	}
	@Подмени
	доступный ЧастьРечи дайЧастьРечи() {
		верни частьРечи;
	}
	@Подмени
	доступный Множество<Граммема> дайГраммемы(){
		Множество<Граммема> result = новый HashSet<>(Long.bitCount(маскаГраммем));
		Граммема[] граммемы = Граммема.values();
		ширцел workCopy = маскаГраммем;
		пока (workCopy!=0){
			ширцел bit = Long.lowestOneBit(workCopy);
			workCopy ^= bit;
			цел pos = Long.numberOfTrailingZeros(bit);
			Граммема граммема = граммемы[pos];
			result.add(граммема);
		}
		верни result;
	}

	доступный ширцел дайМаскуГраммем() {
		верни маскаГраммем;
	}

	доступный Парадигма дайПарадигму() {
		верни парадигма;
	}

	личный итоговый ЧастьРечи частьРечи;
	личный итоговый Строка слово;
	личный итоговый ширцел маскаГраммем;
	личный итоговый Парадигма парадигма;
}
