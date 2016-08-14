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
@XmlRootElement(name = "текст")
доступный класс СинтаксическоеДерево расширяет ОбъектСинт {
	@XmlElementWrapper
	@XmlElement(name = "предложение")
	доступный Список<Предложение> getПредложения() {
		верни предложения;
	}
	личный итоговый Список<Предложение> предложения = новый ПорядковыйСписок<>();

	доступный Строка toString() {
		верни "синтаксис: " + предложения.toString();
	}

}
