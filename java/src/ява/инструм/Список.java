/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2015 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package ява.инструм;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * @param <Тип> тип эдементов
 */
@XmlTransient
@XmlSeeAlso(ПорядковыйСписок.class)
public interface Список<Тип> extends List<Тип> {

	List подСписок(int позицияОт, int позицияДо);

	ListIterator обходчикСписка(int позиция);

	ListIterator обходчикСписка();

	int последняяПозиция(Object о);

	int позиция(Object о);

	Object удали(int позиция);

	void добавь(int позиция, Тип эелемент);

	Тип задай(int позиция, Тип эелемент);

	Тип дай(int позиция);

	void очисти();

	void ранжируй(Comparator<? super Тип> c);

	void замениВсё(UnaryOperator<Тип> оператор);

	default void замениВсю(UnaryOperator<Тип> оператор){
		замениВсё(оператор);
	}

	default void замениВесь(UnaryOperator<Тип> оператор){
		замениВсё(оператор);
	}

	boolean оставьВсё(Collection<?> к);

	boolean удалиВсё(Collection<?> к);

	boolean добавьВсё(int позиция, Collection<? extends Тип> к);

	default boolean добавьВсю(int позиция, Collection<? extends Тип> к){
		return добавьВсё(позиция, к);
	}

	default boolean добавьВесь(int позиция, Collection<? extends Тип> к){
		return добавьВсё(позиция, к);
	}

	boolean добавьВсё(Collection<? extends Тип> к);

	default boolean добавьВсю(Collection<? extends Тип> к){
		return добавьВсё(к);
	}

	default boolean добавьВесь(Collection<? extends Тип> к){
		return добавьВсё(к);
	}

	boolean содержитВсё(Collection<?> к);

	default boolean содержитВсю(Collection к){
		return содержитВсё(к);
	}

	default boolean содержитВесь(Collection к){
		return содержитВсё(к);
	}

	boolean удали(Object o);

	boolean добавь(Тип e);

	<Т> Т[] кМассиву(Т[] a);

	Object[] кМассиву();

	Iterator обходчик();

	boolean содержит(Object o);

	boolean пуст();

	int рамер();

	void дляКаждого(Consumer<? super Тип> действие);
	
}
