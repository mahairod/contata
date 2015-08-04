package org.elliptica.ling;

import java.io.File;
import java.util.*;

public class Morph{
	public static enum Язык{Русский};

	public static void приготовьСловари(Set<Язык> языкиДляОзначивания) throws ИсключениеМорфологии{
		int наборБитов = 0;
		for(Язык яз:языкиДляОзначивания){
			наборБитов|=(1<<яз.ordinal());
		}
		initImpl(наборБитов, РАБОЧИЙ_КАТАЛОГ==null? null: РАБОЧИЙ_КАТАЛОГ.getAbsolutePath() );
	}

	public static void закройСловари(){
		closeImpl();
	}

	public static РезультатСлова найдиСлово(Язык язык, String слово) throws ИсключениеМорфологии {
		final byte[] байты = байтыСлова(язык, слово);
		return lookupWordImpl(язык.ordinal(), байты);
	}

	public static ФормаСлова найдиФорму(Язык язык, String слово, Set<Граммема> граммемы) throws ИсключениеМорфологии {
		final byte[] байты = байтыСлова(язык, слово);
		long маска = маскаГраммем( граммемы );
		final РезультатСлова рс = lookupFormImpl(язык.ordinal(), байты);
		for (Парадигма парадигма: рс.дайПарадигмы() ){
			for (ФормаСлова словоформа: парадигма.формы()){
				long образец = словоформа.дайМаскуГраммем();
				if ( согласиеФорм(маска, образец) ){
					return словоформа;
				}
			}
		}
		return null;
	}

	public static РезультатСлова формыСлова(Язык язык, String слово) throws ИсключениеМорфологии {
		final byte[] байты = байтыСлова(язык, слово);
		final РезультатСлова рс = lookupWordImpl(язык.ordinal(), байты);
		Set<Парадигма> парадигмы = new HashSet();
		for (Парадигма парадигма: рс.дайПарадигмы() ){
			final byte[] байты_парадигмы = байтыСлова(язык, парадигма.дайБазовуюФорму());
			final РезультатСлова рс_формы = lookupFormImpl(язык.ordinal(), байты_парадигмы);
			парадигмы.addAll( рс_формы.дайПарадигмы() );
		}
		return new РезультатСловаВопл(парадигмы);
	}

	private static boolean согласиеФорм(long ф1, long ф2){
		long маскаРода =	ТипГраммемы.Род.маска();
		long маскаЧисла =	ТипГраммемы.Число.маска();
		long маскаПад =	ТипГраммемы.Падеж.маска();
		return
			((маскаПад	& ф1 & ф2) > 0 || 0==(маскаПад	& ф1) || 0==(маскаПад	& ф2)) &&
			((маскаЧисла& ф1 & ф2) > 0 || 0==(маскаЧисла	& ф1) || 0==(маскаЧисла	& ф2)) &&
		    ((маскаРода	& ф1 & ф2) > 0 || 0==(маскаРода	& ф1) || 0==(маскаРода	& ф2));
	}

	private static byte[] байтыСлова(Язык язык, String слово) {
		switch(язык){
		case Русский:
			try {
				return слово.getBytes(кодировка);
			} catch (Exception откл) {
				throw new IllegalStateException(откл);
			}
		default:
			throw new IllegalArgumentException("неизвестный язык: " + язык);
		}
	}

	private static native void initImpl(int битовыйНаборЯзыков, String env_path);
	private static native void closeImpl();
	private static native РезультатСлова lookupWordImpl(int идЯзыка, byte[] слово);
	private static native РезультатСлова lookupFormImpl(int идЯзыка, byte[] слово);

	// вызывается из низкоуровнего кода
	private static String преобразованиеРегионКодировки(byte[] байты){
		try {
			return new String(байты, кодировка);
		} catch (Exception откл) {
			throw new IllegalArgumentException(откл);
		}
	}

	private static long маскаГраммем(Collection<Граммема> граммемы){
		long result = 0;
		for (Граммема г: граммемы){
			result |= (1 << г.ordinal());
		}
		return result;
	}

	// вызывается из низкоуровнего кода
	private static void добавьГраммемуКМножеству(HashSet<Граммема> множествоГраммем, int идГраммемы){
		множествоГраммем.add(значения_граммем[идГраммемы]);
	}

	// вызывается из низкоуровнего кода
	private static void добавьПарадигмуКМножеству(HashSet<Парадигма> множествоПарадигм, final HashSet<Граммема> множествоГраммем, final String базоваяФорма, final boolean найдено, int идЧастиРечи){
		final ЧастьРечи чречи = значения_чречи[идЧастиРечи];
		множествоПарадигм.add(new ПарадигмаНормальная(найдено, чречи, множествоГраммем, базоваяФорма));
	}

	// вызывается из низкоуровнего кода
	private static void добавьПарадигму(HashSet<Парадигма> множествоПарадигм, Парадигма парадигма){
		множествоПарадигм.add(парадигма);
	}

	// вызывается из низкоуровнего кода
	private static РезультатСлова создайРезультатСлова(final HashSet<Парадигма> множествоПарадигм){
		return new РезультатСловаВопл(множествоПарадигм);
	}

	// вызывается из низкоуровнего кода
	private static Парадигма добавьСловоформуКПарадигме(Парадигма парадигма, String форма, long граммемы, int идЧастиРечи){
		final ЧастьРечи частьРечи = значения_чречи[идЧастиРечи];
		if (парадигма == null){
			парадигма = new ПарадигмаПолная(true, частьРечи);
		}
		if (!(парадигма instanceof ПарадигмаПолная)){
			return null;
		}
		ПарадигмаПолная парадигмаПолная = (ПарадигмаПолная) парадигма;
		ФормаСлова формаСлова = new ФормаСлова(частьРечи, форма, граммемы, парадигма);
		парадигмаПолная.добавьФорму(формаСлова);
		return парадигма;
	}

	private static void загрузиБиблиотеку(String библ){
		System.load(new File(ТЕКУЩИЙ_КАТАЛОГ, библ + ".so").getAbsolutePath());
	}

	final private static File ТЕКУЩИЙ_КАТАЛОГ;
	final private static File РАБОЧИЙ_КАТАЛОГ;

	static{
		{
			String раб_катал = System.getProperty("JMorph-rml-dir");
			РАБОЧИЙ_КАТАЛОГ = (раб_катал==null) ? null : new File(раб_катал);

			String бин_катал = System.getProperty("JMorph-jni-lib-dir");
			if(бин_катал!=null){
				ТЕКУЩИЙ_КАТАЛОГ = new File(бин_катал);
			} else {
				if (РАБОЧИЙ_КАТАЛОГ!=null){
					ТЕКУЩИЙ_КАТАЛОГ = new File(РАБОЧИЙ_КАТАЛОГ, "Bin/");
				} else {
					ТЕКУЩИЙ_КАТАЛОГ = new File("jni-lib");
				}
			}
		}

		try{
			загрузиБиблиотеку("JNIMorphAPI");
		}catch(Throwable tr){
			System.err.println("Ошибка загрузки библиотеки JNIMorphAPI");
			throw new ExceptionInInitializerError(tr);
		}
	}
	private static final Граммема[] значения_граммем = Граммема.values();
	private static final ЧастьРечи[] значения_чречи = ЧастьРечи.values();
	private static final String кодировка = "cp1251";
}