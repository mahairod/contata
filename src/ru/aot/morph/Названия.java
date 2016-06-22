/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
пакет ru.aot.morph;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный сопряжение Названия {
	статичный итоговый Строка[] числа = {
		"множественное", "единственное", "двойственное", "тройственное", "паукальное"
	};
	статичный итоговый Строка[] числа_кратко = {
		"мн", "ед", "дв", "тр", "паук"
	};
	статичный итоговый Строка[] роды = {
		"мужской", "женский", "средний", "обоюдный", "общий"
	};
	статичный итоговый Строка[] роды_кратко = {
		"муж", "жен", "сред", "обоюд", "общ"
	};
	статичный итоговый Строка[] падежи = {
		"именительный", "родительный", "дательный", "винительный", "творительный", "предложный", "звательный"
	};
	статичный итоговый Строка[] падежи_кратко = {
		"им", "род", "дат", "вин", "тв", "пр", "зв"
	};
	статичный итоговый Строка[] наклонения = {
		"изъявительное", "условное", "повелительное", "желательное",
		"юссив", //косв. повеление 3-му лицу
		"гортатив", //поощрение (давай ...)
		"инъюктив", //собств.намерение
		"прохибитив", //отрицательно-повелительное
		"ирреальное", //ирреалис
		"парафразис" //чужая речь
	};
	статичный итоговый Строка[] одушевлённости = {
		"одушевлённое", "неодушевлённое",
	};
	статичный итоговый Строка[] лица = {
		"первое", "второе", "третье", "четвёртое"
	};
	статичный итоговый Строка[] времена = {
		"настоящее", "будущее", "прошлое"
	};
	статичный итоговый Строка[] залоги = {
		"действительный", "страдательный", "возвратный", "взаимный",
		"средний", "антистрадательный", "эргативный", "совместный", "безличный",
		"побудительный"/*каузатив*/, "декаузативный", "аппликативный"
	};
	статичный итоговый Строка[] переходности = {
		"непереходный", "переходный"
	};
	статичный итоговый Строка[] валентности = переходности;
	статичный итоговый Строка[] видыГлагола = {
		"совершенный", "несовершенный"
	};
	статичный итоговый Строка[] степениСравнения = {
		"положительная", "сравнительная", "превосходная"
	};
	статичный итоговый Строка[] разряды = {
		"качественное", "относительное", "притяжательное", "порядковое"
	};

	статичный итоговый Строка[][] полные_имена = {
		{"нет названия"},
		числа,			падежи,			роды,		времена, лица, одушевлённости, видыГлагола, переходности, залоги, наклонения, степениСравнения, разряды
	};

	статичный итоговый Строка[][] краткие_имена = {
		{"нет названия"},
		числа_кратко,	падежи_кратко,	роды_кратко, времена, лица, одушевлённости, видыГлагола, переходности, залоги, наклонения, степениСравнения, разряды
	};

}
