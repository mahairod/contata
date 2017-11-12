/*
 *  Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 *  Все права защищены и охраняются законом.
 *  Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 * 
 *   Собственная лицензия Астафьева
 *  Данный программный код является собственностью Астафьева Антона Александровича
 *  и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.язык.сеть;

внеся java.lang.annotation.ElementType;
внеся java.lang.annotation.RetentionPolicy;
внеся java.lang.annotation.Выдержка;
внеся java.lang.annotation.Цель;

/**
 *
 * @автор Антон Астафьев
 */
@Выдержка(RetentionPolicy.RUNTIME)
@Цель(ElementType.TYPE)
доступный @сопряжение ПодСервисы {
	Class<? расширяет Сервисок>[] значение();
}
