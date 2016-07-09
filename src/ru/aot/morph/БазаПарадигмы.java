/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет ru.aot.morph;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный абстрактный класс БазаПарадигмы расширяет БазаСловоформы воплощает Парадигма {
	защищённый итоговый логическое найдено;
	защищённый итоговый ЧастьРечи чречи;

	доступный БазаПарадигмы(логическое найдено, ЧастьРечи чречи) {
		это.найдено = найдено;
		это.чречи = чречи;
	}

	@Подмени
	доступный логическое былоНайдено() {
		верни найдено;
	}

	@Подмени
	доступный ЧастьРечи дайЧастьРечи() {
		верни чречи;
	}

	@Подмени
	доступный Строка дайСлово() {
		верни дайБазовуюФорму();
	}
	
	доступный тщетный добавьФорму(ФормаСлова формаСлова) {
	}
}
