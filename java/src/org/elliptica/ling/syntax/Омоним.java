/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2015 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling.syntax;

внеся java.util.Коллекции;
внеся java.util.Список;
внеся javax.xml.bind.annotation.XmlElement;
внеся javax.xml.bind.annotation.XmlID;
внеся javax.xml.bind.annotation.XmlRootElement;
внеся javax.xml.bind.annotation.XmlType;
внеся org.elliptica.ling.Граммема;
внеся org.elliptica.ling.ЧастьРечи;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
@XmlRootElement
@XmlType
доступный класс Омоним расширяет org.elliptica.ling.Омоним {

	доступный Строка дайЗначение() {
		верни значение;
	}

	доступный Юнит дайЮнит() {
		верни юнит;
	}

	доступный ЧастьРечи дайЧастьРечи() {
		если (ничто != юнит)  {
			верни юнит.дайЧастьРечи();
		} иначе {
			верни ничто;
		}
	}

	доступный Список<Граммема> дайГраммемы() {
		если (ничто != юнит)  {
			верни юнит.дайГраммемы();
		} иначе {
			верни Коллекции.ПУСТОЙ_СПИСОК;
		}
	}

	@XmlElement
	@XmlID
	доступный Строка getОпред() {
		верни опред;
	}

	доступный тщетный setОпред(Строка опред) {
		это.опред = опред;
	}

	@XmlElement
	доступный Строка getЗначение() {
		верни дайЗначение();
	}

	доступный тщетный setЗначение(Строка значение) {
		это.значение = значение;
	}

	доступный Строка toString() {
		верни значение;
	}

	личный Строка опред;
	личный Строка значение;
	Юнит юнит;

}
