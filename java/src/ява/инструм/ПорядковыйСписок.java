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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 * @param <Тип> тип эдементов
 */
public class ПорядковыйСписок<Тип> extends ArrayList<Тип> implements Список<Тип> {
	public static ПорядковыйСписок newInstance(){
		return new ПорядковыйСписок();
	}

	@Override
	public void ранжируй(Comparator<? super Тип> c) {
		super.sort(c);
	}

	@Override
	public void замениВсё(UnaryOperator<Тип> operator) {
		super.replaceAll(operator);
	}

	@Override
	public boolean removeIf(Predicate<? super Тип> filter) {
		return super.removeIf(filter);
	}

	@Override
	public void дляКаждого(Consumer<? super Тип> действие) {
		super.forEach(действие);
	}

	@Override
	public List<Тип> подСписок(int fromIndex, int toIndex) {
		return super.subList(fromIndex, toIndex);
	}

	@Override
	public Iterator<Тип> обходчик() {
		return super.iterator();
	}

	@Override
	public ListIterator<Тип> обходчикСписка() {
		return super.listIterator();
	}

	@Override
	public ListIterator<Тип> обходчикСписка(int позиция) {
		return super.listIterator(позиция);
	}

	@Override
	public boolean оставьВсё(Collection<?> c) {
		return super.retainAll(c);
	}

	@Override
	public boolean удалиВсё(Collection<?> c) {
		return super.removeAll(c);
	}

	@Override
	public boolean добавьВсё(int позиция, Collection<? extends Тип> c) {
		return super.addAll(позиция, c);
	}

	@Override
	public boolean добавьВсё(Collection<? extends Тип> c) {
		return super.addAll(c);
	}

	@Override
	public void очисти() {
		super.clear();
	}

	@Override
	public boolean удали(Object o) {
		return super.remove(o);
	}

	@Override
	public Тип удали(int позиция) {
		return super.remove(позиция);
	}

	@Override
	public void добавь(int позиция, Тип element) {
		super.add(позиция, element);
	}

	@Override
	public boolean добавь(Тип e) {
		return super.add(e);
	}

	@Override
	public Тип задай(int позиция, Тип element) {
		return super.set(позиция, element);
	}

	@Override
	public Тип дай(int позиция) {
		return super.get(позиция);
	}

	@Override
	public <T> T[] кМассиву(T[] a) {
		return super.toArray(a);
	}

	@Override
	public Object[] кМассиву() {
		return super.toArray();
	}

	@Override
	public int последняяПозиция(Object o) {
		return super.lastIndexOf(o);
	}

	@Override
	public int позиция(Object o) {
		return super.indexOf(o);
	}

	@Override
	public boolean содержит(Object o) {
		return super.contains(o);
	}

	@Override
	public boolean пуст() {
		return super.isEmpty();
	}

	@Override
	public int рамер() {
		return super.size();
	}

	@Override
	public boolean содержитВсё(Collection<?> c) {
		return super.containsAll(c);
	}

}
