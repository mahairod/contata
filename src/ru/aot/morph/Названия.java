/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package ru.aot.morph;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public interface Названия {
	static final String[] числа = {
		"множественное", "единственное", "двойственное", "тройственное", "паукальное"
	};
	static final String[] числа_кратко = {
		"мн", "ед", "дв", "тр", "паук"
	};
	static final String[] роды = {
		"мужской", "женский", "средний", "обоюдный", "общий"
	};
	static final String[] роды_кратко = {
		"муж", "жен", "сред", "обоюд", "общ"
	};
	static final String[] падежи = {
		"именительный", "родительный", "дательный", "винительный", "творительный", "предложный", "звательный"
	};
	static final String[] падежи_кратко = {
		"им", "род", "дат", "вин", "тв", "пр", "зв"
	};
	static final String[] наклонения = {
		"изъявительное", "условное", "повелительное", "желательное",
		"юссив", //косв. повеление 3-му лицу
		"гортатив", //поощрение (давай ...)
		"инъюктив", //собств.намерение
		"прохибитив", //отрицательно-повелительное
		"ирреальное", //ирреалис
		"парафразис" //чужая речь
	};
	static final String[] одушевлённости = {
		"одушевлённое", "неодушевлённое",
	};
	static final String[] лица = {
		"первое", "второе", "третье", "четвёртое"
	};
	static final String[] времена = {
		"настоящее", "будущее", "прошлое"
	};
	static final String[] залоги = {
		"действительный", "страдательный", "возвратный", "взаимный",
		"средний", "антистрадательный", "эргативный", "совместный", "безличный",
		"побудительный"/*каузатив*/, "декаузативный", "аппликативный"
	};
	static final String[] переходности = {
		"непереходный", "переходный"
	};
	static final String[] валентности = переходности;
	static final String[] видыГлагола = {
		"совершенный", "несовершенный"
	};
	static final String[] степениСравнения = {
		"положительная", "сравнительная", "превосходная"
	};
	static final String[] разряды = {
		"качественное", "относительное", "притяжательное", "порядковое"
	};

	static final String[][] полные_имена = {
		{"нет названия"},
		числа,			падежи,			роды,		времена, лица, одушевлённости, видыГлагола, переходности, залоги, наклонения, степениСравнения, разряды
	};

	static final String[][] краткие_имена = {
		{"нет названия"},
		числа_кратко,	падежи_кратко,	роды_кратко, времена, лица, одушевлённости, видыГлагола, переходности, залоги, наклонения, степениСравнения, разряды
	};

}
