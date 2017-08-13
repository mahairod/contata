/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.морфемика.ячейки;

внеся java.sql.SQLException;
внеся java.util.HashMap;
внеся java.util.logging.Level;
внеся java.util.logging.Logger;
внеся java.util.Словарь;
внеся java.util.ТаблицаСвёрток;
внеся javax.persistence.AttributeConverter;
внеся javax.persistence.Converter;
внеся org.elliptica.ling.ЧастьРечи;
внеся org.postgresql.util.PGobject;
внеся эллиптика.ява.морфемика.ТипМорфемы;


/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
@Converter
доступный класс ПреобразовательЧастиРечи воплощает AttributeConverter<ЧастьРечи, PGobject> {

	@Подмени
	доступный PGobject convertToDatabaseColumn(ЧастьРечи атрибут) {
		если (атрибут == ничто){
			верни ничто;
		}
		PGobject зн = новый PGobject();
		попробуй {
			зн.setType("partofspeech");
			зн.setValue(атрибут.name());
		} ловя (SQLException ex) {
			ЖУРНАЛ.log(Level.SEVERE, ничто, ex);
		}
		верни зн;
	}

	@Подмени
	доступный ЧастьРечи convertToEntityAttribute(PGobject данныеБД) {
		если (данныеБД экземпляр PGobject){
			верни ЧастьРечи.valueOf(данныеБД.getValue());
//			верни СООТВ.дай(данныеБД.getValue());
		} иначе {
			верни ничто;
		}
	}

	личный статичный итоговый Словарь<Строка,ЧастьРечи> СООТВ = новый ТаблицаСвёрток<>();
	статичный {
		для (ЧастьРечи чр: ЧастьРечи.values()){
			СООТВ.клади(чр.кратко(), чр);
		}
	}

	личный статичный итоговый Logger ЖУРНАЛ = Logger.getLogger(ПреобразовательТипаМорфемы.класс.дайИмя());
}
