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

внеси javax.xml.bind.annotation.XmlElement;
внеси javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
@XmlRootElement
доступный класс Диапазон расширяет ОбъектСинт {

	@XmlElement
	доступный цел getНачало(){
		верни начало;
	}
	
	@XmlElement
	доступный цел getКонец(){
		верни конец;
	}
	
	@XmlElement
	доступный цел getДлина(){
		верни конец - начало;
	}

	цел начало;
	цел конец;
	
}
