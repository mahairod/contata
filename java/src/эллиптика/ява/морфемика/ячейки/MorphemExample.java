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

внеся java.io.Serializable;
внеся javax.persistence.Entity;
внеся javax.persistence.FetchType;
внеся javax.persistence.JoinColumn;
внеся javax.persistence.ManyToOne;
внеся javax.persistence.NamedQueries;
внеся javax.persistence.NamedQuery;
внеся javax.persistence.Table;
внеся эллиптика.ява.вэб.сущности.СущностьАХЯНаКлючеInteger;


/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
@Entity
@Table(name = "morphem_example")
@NamedQueries({
	@NamedQuery(name = "MorphemExample.найдиВсе", query = "SELECT m FROM MorphemExample m"),
	@NamedQuery(name = "MorphemExample.найдиПоContent", query = "SELECT m FROM MorphemExample m WHERE m.content = :content")})
доступный класс MorphemExample расширяет СущностьАХЯНаКлючеInteger воплощает Serializable {

	личный статичный итоговый ширцел serialVersionUID = 1L;
	личный Строка content;
	@JoinColumn(name = "morphem", referencedColumnName = "разл")
    @ManyToOne(optional = ложь, fetch = FetchType.EAGER)
	личный MorphemDescr morphemDescr;

	доступный MorphemExample() {
	}

	@Deprecated
	доступный Строка getContent() {
		верни дайContent();
	}

	доступный Строка дайContent() {
		верни content;
	}

	@Deprecated
	доступный тщетный setContent(Строка content) {
		это.задайContent(content);
	}

	доступный тщетный задайContent(Строка content) {
		это.content = content;
	}

	@Deprecated
	доступный MorphemDescr getMorphemDescr() {
		верни дайMorphemdescr();
	}

	доступный MorphemDescr дайMorphemdescr() {
		верни morphemDescr;
	}

	@Deprecated
	доступный тщетный setMorphemDescr(MorphemDescr morphemDescr) {
		это.задайMorphemdescr(morphemDescr);
	}

	доступный тщетный задайMorphemdescr(MorphemDescr morphemDescr) {
		это.morphemDescr = morphemDescr;
	}

}
