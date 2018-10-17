/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.морфемика.логика;

внеся статичный эллиптика.ява.морфемика.логика.Биты.битЕсть;


/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс Буква {
	итоговый символ сим;
	доступный статичный итоговый символ НУЛЕВОЙ = '∅';
	доступный статичный итоговый Буква НУЛЕВАЯ = новый Буква(НУЛЕВОЙ);

	доступный Буква(символ сим) {
		это.сим = сим;
	}
	
	доступный логическое из(Строка набор) {
		верни !неиз(набор);
	}
	
	доступный логическое неиз(Строка набор) {
		верни набор.номер(сим) < 0;
	}

	личный цел смещение() {
		верни сим !='∅' ? сим-'а' : 64;
	}

	доступный логическое из(цел набор) {
		верни битЕсть(набор, смещение());
	}
	
	@Подмени
	доступный цел hashCode() {
		верни сим;
	}

	@Подмени
	доступный логическое equals(Object obj) {
		если (это == obj) {
			верни истина;
		}
		если (obj == ничто) {
			верни ложь;
		}
		если (дайКласс() != obj.дайКласс()) {
			если (obj.дайКласс() != Character.класс) верни ложь;
			Character ch = (Character) obj;
			верни это.сим == ch.символValue();
		} иначе {
			итоговый Буква other = (Буква) obj;
			верни это.сим == other.сим;
		}
	}

	статичный Буква начало(Строка источник) {
		верни начало(источник, НУЛЕВОЙ);
	}
	статичный Буква начало(Строка источник, символ терминал) {
		верни новый Буква( источник.пуста() ? терминал : источник.символВ(0) );
	}

	статичный Буква конец(Строка источник) {
		верни конец(источник, НУЛЕВОЙ);
	}
	статичный Буква конец(Строка источник, символ терминал) {
		верни новый Буква( источник.пуста() ? терминал : источник.символВ(источник.длина()-1) );
	}
}
