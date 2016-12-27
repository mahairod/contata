/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.синтаксис.структ;

внеся java.util.Коллекции;
внеся java.util.Массивы;
внеся java.util.МножествоСвёрток;
внеся java.util.Список;
внеся org.elliptica.ling.Morph;
внеся org.elliptica.ling.syntax.Омоним;
внеся org.elliptica.ling.Граммема;
внеся org.elliptica.ling.ФормаСлова;
внеся org.elliptica.ling.ЧастьРечи;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс Единица {

	доступный Единица(Строка форма, Омоним омоним) {
		это.форма = форма;
		это.омоним = омоним;
	}

	доступный ЧастьРечи дайЧастьРечи() {
		верни омоним.дайЧастьРечи();
	}

	доступный Список<Граммема> дайГраммемы() {
		верни омоним.дайГраммемы();
	}

	доступный Строка дайФразу(){
		верни форма;
	}

	доступный тщетный смениФорму(Список<Граммема> граммемы){
		ФормаСлова формаСлова = Morph.найдиФорму(омоним.дайЗначение(), новый МножествоСвёрток<>(граммемы));
		форма = формаСлова.дайСлово().toLowerCase();
		омоним = новый ОмонимПроизводный(граммемы, омоним.дайЗначение());
	}

	доступный Омоним дайГлавноеСлово() {
		верни омоним;
	}

	доступный Омоним дайПервоеСлово() {
		верни дайГлавноеСлово();
	}

	доступный Омоним дайПоследнееСлово() {
		верни дайГлавноеСлово();
	}

	доступный ГруппаСтруктуры дайТару() {
		верни тара;
	}

	тщетный задайТару(ГруппаСтруктуры тара) {
		это.тара = тара;
	}

	доступный Список<Строка> представление(){
		верни Коллекции.одиночкаСписок(toString());
	}

	доступный Строка toString() {
		верни форма + ":" + омоним.toString() + граммемы();
	}

	доступный Строка граммемы() {
		Строка рез = "";
		для (Граммема граммема : дайГраммемы()) {
			рез += (рез.isEmpty() ? "" : "," ) + граммема.коротко();
		}
		верни "(" + рез + ")";
	}

	личный Строка форма;
	личный Омоним омоним;
	личный ГруппаСтруктуры тара;

}
