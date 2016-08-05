/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.ввыв;

внеся java.io.IOException;
внеся java.io.PrintStream;
внеся java.util.Locale;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>}
 */
доступный класс ПотокПечатиДелегирующий расширяет ПотокПечати {
	личный итоговый PrintStream делегат;

	доступный ПотокПечатиДелегирующий(PrintStream делегат) {
		поверх(делегат);
		это.делегат = делегат;
	}

	доступный тщетный flush() {
		делегат.flush();
	}

	доступный тщетный close() {
		делегат.close();
	}

	доступный логическое checkError() {
		верни делегат.checkError();
	}

	доступный тщетный write(цел b) {
		делегат.write(b);
	}

	доступный тщетный write(байт[] buf, цел off, цел len) {
		делегат.write(buf, off, len);
	}

	доступный тщетный print(логическое b) {
		делегат.print(b);
	}

	доступный тщетный print(символ c) {
		делегат.print(c);
	}

	доступный тщетный print(цел i) {
		делегат.print(i);
	}

	доступный тщетный print(ширцел l) {
		делегат.print(l);
	}

	доступный тщетный print(дроб f) {
		делегат.print(f);
	}

	доступный тщетный print(ширдроб d) {
		делегат.print(d);
	}

	доступный тщетный print(символ[] s) {
		делегат.print(s);
	}

	доступный тщетный print(Строка s) {
		делегат.print(s);
	}

	доступный тщетный print(Object obj) {
		делегат.print(obj);
	}

	доступный тщетный println() {
		делегат.println();
	}

	доступный тщетный println(логическое x) {
		делегат.println(x);
	}

	доступный тщетный println(символ x) {
		делегат.println(x);
	}

	доступный тщетный println(цел x) {
		делегат.println(x);
	}

	доступный тщетный println(ширцел x) {
		делегат.println(x);
	}

	доступный тщетный println(дроб x) {
		делегат.println(x);
	}

	доступный тщетный println(ширдроб x) {
		делегат.println(x);
	}

	доступный тщетный println(символ[] x) {
		делегат.println(x);
	}

	доступный тщетный println(Строка x) {
		делегат.println(x);
	}

	доступный тщетный println(Object x) {
		делегат.println(x);
	}

	доступный PrintStream printf(Строка format, Object... args) {
		верни делегат.printf(format, args);
	}

	доступный PrintStream printf(Locale l, Строка format, Object... args) {
		верни делегат.printf(l, format, args);
	}

	доступный PrintStream format(Строка format, Object... args) {
		верни делегат.format(format, args);
	}

	доступный PrintStream format(Locale l, Строка format, Object... args) {
		верни делегат.format(l, format, args);
	}

	доступный PrintStream append(CharSequence csq) {
		верни делегат.append(csq);
	}

	доступный PrintStream append(CharSequence csq, цел start, цел end) {
		верни делегат.append(csq, start, end);
	}

	доступный PrintStream append(символ c) {
		верни делегат.append(c);
	}

	доступный тщетный write(байт[] b) кидает IOException {
		делегат.write(b);
	}

}
