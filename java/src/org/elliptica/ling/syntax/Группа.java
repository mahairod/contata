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
внеся javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
@XmlRootElement
доступный класс Группа расширяет Диапазон {

	доступный переч Тип {
		ОСНОВА, ГРУППА, ФРАГМЕНТ
	}
	доступный переч Название {
						ПРИЛ_СУЩ,			КОЛИЧ,
		СЛОЖ_ЧИСЛ,		НАР_ЧИСЛ_СУЩ,		СУЩ_ЧИСЛ,
		ПГ,				ОДНОР_ПРИЛ,			НАРЕЧ_ГЛАГОЛ,
		НАР_ПРИЛ,		НАР_НАР,				СРАВН_СТЕПЕНЬ,
		ОТР_ФОРМА,		ПРЯМ_ДОП,			ГЕНИТ_ИГ,
		ОДНОР_НАР,		ПЕР_ГЛАГ_ИНФ,		ОДНОР_ИНФ,
		ФИО,			ОДНОР_ИГ,			ЧИСЛ_СУЩ,
		ИГ,				ЭЛЕКТ_ИГ,			ЭЛ_АДРЕС,
		ОДНОР_ЧИСЛ,		МОДИФ_ПРИЛ,			АППРОКС_ИГ,
		НАР_НАР_ЧИСЛ,	АППРОКС_ПГ,			ПРИЧ_СУЩ,
		ПОДЛ,			ОБОРОТ,				ПОДКЛАУЗА,
		ПРИЛОЖЕНИЕ,		СУЩ_ОБС_ПРИЛ,		КЛВ,
		РАЗРЫВ_СОЮЗ,		ОТСОЮЗ,				НАР_ПРЕДИК,
		ПРИЛ_ПОСТПОС,	ПРИДАТ_ОПР,			АНАТ_СРАВН,
		ВВОДН_КЛАУЗА,	ДОЛЖ_ФИО,			ИНОСТР_ЛЕКС,
		ОТСРАВН,		ПРЯМ_ДОП_РЕВ,		ИНСТР_ДОП
	}

	доступный Название дайНазвание() {
		верни Название.valueOf(описание.toUpperCase());
	}

	доступный Тип дайТип() {
		если ("gr".equals(тип)){
			верни Тип.ГРУППА;
		}
		если ("sp".equals(тип)){
			верни Тип.ОСНОВА;
		}
		если ("cl".equals(тип)){
			верни Тип.ФРАГМЕНТ;
		}
		верни ничто;
	}

	личный Строка описание;
	личный Строка тип;

	@XmlElement
	доступный Строка getОписание() {
		верни описание;
	}

	доступный тщетный setОписание(Строка описание) {
		это.описание = описание;
	}

	@XmlElement
	доступный Строка getТип() {
		верни тип;
	}

	доступный тщетный setТип(Строка тип) {
		это.тип = тип;
	}

}
