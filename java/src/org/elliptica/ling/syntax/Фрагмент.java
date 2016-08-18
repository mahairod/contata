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
внеся javax.xml.bind.Unmarshaller;
внеся javax.xml.bind.annotation.XmlIDREF;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
@XmlRootElement
доступный класс Фрагмент расширяет Диапазон {

	@XmlElementWrapper
	@XmlElement(name = "вариант")
	доступный Список<Вариант> getВарианты() {
		верни варианты;
	}

	@XmlElementWrapper
	@XmlElement(name = "позицияСоюза")
	доступный Список<ПозицияСоюза> getСоюзы() {
		верни союзы;
	}

	@XmlElementWrapper
	@XmlElement(name = "типФрагмента")
	доступный Список<ТипФрагмента> getТипы() {
		верни типы;
	}

	@XmlElement
	доступный ОбъектСинт getПредшествующий() {
		верни предшествующий;
	}

	@XmlElement
	доступный цел getЧислоПунктЗнаков() {
		верни числоПунктЗнаков;
	}

	@XmlElement
	@XmlIDREF
	доступный Омоним getРодственноеСлово() {
		верни родственноеСлово;
	}

	доступный Предложение getПредложение() {
		верни предложение;
	}

	доступный тщетный setПредшествующий(ОбъектСинт предшествующий) {
		это.предшествующий = предшествующий;
	}

	доступный тщетный setЧислоПунктЗнаков(цел числоПунктЗнаков) {
		это.числоПунктЗнаков = числоПунктЗнаков;
	}

	доступный тщетный setРодственноеСлово(Омоним родственноеСлово) {
		this.родственноеСлово = родственноеСлово;
	}

	тщетный afterUnmarshal(Unmarshaller unmarshaller, Object parent){
		предложение = (Предложение) parent;
	}

	Список<Вариант> варианты = новый ПорядковыйСписок<>();
	Список<ПозицияСоюза> союзы = новый ПорядковыйСписок<>();
	Список<ТипФрагмента> типы = новый ПорядковыйСписок<>();
	ОбъектСинт предшествующий;
	цел числоПунктЗнаков;
	Предложение предложение;
	Омоним родственноеСлово;

	переч Тип {
		
	}
}
