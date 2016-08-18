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

внеся java.util.ArrayList;
внеся java.util.Spliterators;
внеся java.util.function.BiConsumer;
внеся java.util.stream.Collector;
внеся java.util.stream.StreamSupport;
внеся java.util.Итератор;
внеся java.util.Список;
внеся org.elliptica.ling.syntax.Группа;
внеся org.elliptica.ling.syntax.Омоним;
внеся org.elliptica.ling.Граммема;
внеся org.elliptica.ling.ЧастьРечи;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс ГруппаСтруктуры расширяет Единица {

	доступный ГруппаСтруктуры(Единица начало, Единица конец, Единица корень, Группа.Название тип ) {
		поверх(ничто);
		это.начало = начало;
		это.конец = конец;
		это.корень = корень;
		это.тип = тип;
		начало.задайТару(это);
		конец.задайТару(это);
	}

	доступный Единица дайНачало() {
		верни начало;
	}

	доступный Единица дайКонец() {
		верни конец;
	}

	доступный Группа.Название дайТип() {
		верни тип;
	}

	@Подмени
	доступный Омоним дайГлавноеСлово() {
		верни корень.дайГлавноеСлово();
	}

	@Подмени
	доступный Омоним дайПервоеСлово() {
		верни начало.дайПервоеСлово();
	}

	@Подмени
	доступный Омоним дайПоследнееСлово() {
		верни конец.дайПоследнееСлово();
	}

	@Подмени
	доступный ЧастьРечи дайЧастьРечи() {
		верни корень.дайЧастьРечи();
	}

	@Подмени
	доступный Список<Граммема> дайГраммемы() {
		верни корень.дайГраммемы();
	}

	@Подмени
	доступный Список<Строка> представление(){
		Итератор<Строка> итератор = новый СливающийИтератор<Строка>(
				начало.представление().итератор(),
				конец .представление().итератор()
		);
		
		класс СборщикСтрок воплощает BiConsumer<Список<Строка>, Строка> {
			цел счётчик = 0;
			доступный тщетный accept(Список<Строка> спис, Строка элем) {
				если (счётчик % 2 == 1){
					Строка хвост = спис.удали(спис.размер()-1);
					спис.добавь(хвост + "\t" + элем);
					спис.добавь("");
				} иначе {
					спис.добавь(элем);
				}
			}
		}
		
		Список<Строка> результат = 
			StreamSupport.stream(Spliterators.spliteratorUnknownSize(итератор, 0), ложь)
				.filter(строка -> !строка.isEmpty())
				.collect( 
					Collector.of(ArrayList::новый, (спис, элем) -> {
						если (спис.размер() % 2 == 1){
							Строка хвост = спис.удали(спис.размер()-1);
							спис.добавь(хвост + "\t" + элем);
							спис.добавь("");
						} иначе {
							спис.добавь(элем);
						}
					}, (элем1, элем2) -> {
						кинь новый UnsupportedOperationException("Don't run in parallel");
					})
				);

		цел максДлина = 0;
		для (Строка строка : результат) {
			максДлина = максДлина > строка.length() ? максДлина : строка.length();
		}
		итоговый Строка локПредст = toString();
		максДлина = максДлина > локПредст.length() ? максДлина : локПредст.length();
		результат.добавь(0, максДлина < локПредст.length() ? локПредст : локПредст + ПРОБЕЛЫ.substring(0, максДлина+3 - локПредст.length()));
		верни результат;
	}
	
	личный статичный итоговый Строка ПРОБЕЛЫ = множСтр("      ");
	личный статичный Строка множСтр(Строка стр){
		верни множСтр_(множСтр_(множСтр_(стр+стр+стр)));
	};

	личный статичный Строка множСтр_(Строка стр){
		верни стр+стр+стр;
	};

	доступный Строка toString() {
		верни тип.toString() + граммемы();
	}

	личный итоговый Единица начало;
	личный итоговый Единица конец;
	личный итоговый Единица корень;
	личный итоговый Группа.Название тип;
}
