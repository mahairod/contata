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

внеся javax.xml.bind.annotation.XmlElement;
внеся javax.xml.bind.annotation.XmlElementWrapper;
внеся javax.xml.bind.annotation.XmlRootElement;
внеся java.util.ПорядковыйСписок;
внеся java.util.Список;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
@XmlRootElement
доступный класс Предложение расширяет КоллекцияФрагментов {

	доступный Список<Слово> дайСписокСлов() {
		верни списокСлов;
	}

	@XmlElementWrapper
	@XmlElement(name = "слово")
	доступный Список<Слово> getСписокСлов() {
		верни дайСписокСлов();
	}

	Список<Слово> списокСлов = новый ПорядковыйСписок<>();

	доступный Строка toString() {
		верни "пр: " + списокСлов.toString();
	}
}
