/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.синтаксис.структ;

внеся java.util.concurrent.ArrayBlockingQueue;
внеся java.util.Итератор;
внеся java.util.Коллекция;
внеся java.util.Массивы;
внеся java.util.Очередь;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 * @param <Тип>
 */
доступный класс СливающийИтератор<Тип> воплощает Итератор<Тип> {

	доступный СливающийИтератор(Итератор<Тип> ... итераторы) {
		это(Массивы.списком(итераторы));
	}
	доступный СливающийИтератор(Коллекция<Итератор<Тип>> итераторы) {
		это.итераторы = новый ArrayBlockingQueue<>(итераторы.размер());
		для (Итератор<Тип> итератор : итераторы) {
			если (итератор.естьСледующий())  {
				это.итераторы.добавь(итератор);
			}
		}
	}

	доступный логическое естьСледующий() {
		верни !итераторы.пусто();
	}

	доступный Тип следующий() {
		Итератор<Тип> итератор = итераторы.удали();
		Тип значение = итератор.следующий();
		если (итератор.естьСледующий())  {
			итераторы.добавь(итератор);
		}
		верни значение;
	}

	личный итоговый Очередь<Итератор<Тип>> итераторы;
}
