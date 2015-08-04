package org.elliptica.ling;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import static org.elliptica.ling.ТипГраммемы.*;
import org.elliptica.ling.Morph.РезультатСлова.Парадигма;

public class Morph{
	public static enum Язык{Русский};

	public static class ИсключениеЯваСопряженияМорфологии extends RuntimeException{
		private static final long serialVersionUID = 6844719078250020184L;
		public ИсключениеЯваСопряженияМорфологии() {super();}
		public ИсключениеЯваСопряженияМорфологии(String сообщение, Throwable причина) {super(сообщение, причина);}
		public ИсключениеЯваСопряженияМорфологии(String сообщение) {super(сообщение);}
		public ИсключениеЯваСопряженияМорфологии(Throwable причина) {super(причина);}
	}

	public static void приготовьСловари(Set<Язык> языкиДляОзначивания) throws ИсключениеЯваСопряженияМорфологии{
		int наборБитов = 0;
		for(Язык яз:языкиДляОзначивания){
			наборБитов|=(1<<яз.ordinal());
		}
		initImpl(наборБитов, РАБОЧИЙ_КАТАЛОГ==null? null: РАБОЧИЙ_КАТАЛОГ.getAbsolutePath() );
	}

	public static void закройСловари(){
		closeImpl();
	}

	public static enum ЧастьРечи{
		существительное,	// 0
		прилагательное,
		глагол,				// 2
		местоименное_существительное,	// 3
		местоименное_прилагательное,
		местоименный_предикатив,
		числительное_количественное,
		числительное_порядковое,		// 7
		наречие,			// 8
		предикатив,
		предлог,	
		пословица,
		союз,
		междометие,
		вводное_слово,		// 14
		фраза,
		частица,
		краткое_прилагательное,//17
		причастие,
		деепричастие,
		краткое_причастие,	//20
		инфинитив
		;

		@Override
		public String toString() {
			return name().replace('_', ' ');
		}

		public String кратко(){
			if (ordinal() <= предлог.ordinal()){
				return краткие_названия[ordinal()];
			} else {
				return name();
			}
		}

		private final String[] краткие_названия = new String[] {"сущ", "прил", "гл", "мест", "нар", "пред"};
	};

	private static final String[][] названия = Названия.полные_имена;

	private static final String[][] названия_кратко = Названия.краткие_имена;

	public static enum Граммема {
		множественное(Число), единственное(Число), //1
		именительный(Падеж), родительный(Падеж), дательный(Падеж), винительный(Падеж), творительный(Падеж), предложный(Падеж), звательный(Падеж), //8
		мужской(Род), женский(Род), средний(Род), обоюдный(Род), //12
		настоящее(Время), будущее(Время), прошлое(Время),
		первое(Лицо), второе(Лицо), третье(Лицо),//18
		повелительное(Наклонение),//19
		одушевлённое(Одушевлённость), неодушевлённое(Одушевлённость),
		сравнительная(Степень_сравнения),//22
		совершенный(Вид), несовершенный(Вид),
		непереходный(Переходность), переходный(Переходность),
		действительный(Залог), страдательный(Залог),//28
		неизменяемое(НетТипа), аббревиатура(Собственное), отчество(Собственное),//31
		локативность(Собственное), организация(Собственное),
		качественное(Разряд),
		нетМножЧисла(НетТипа),//35
		вопросительное(НетТипа), указательное(НетТипа), // для наречий
		имя(Собственное), фамилия(Собственное),//39
		безличный(НетТипа),
		жаргонизм(Стиль), опечатка(Стиль),//42
		разговорное(Стиль), притяжательное(Специальный_падеж), архаизм(Стиль),//45
		нестандПадеж(Специальный_падеж), // для второго родительного и второго предложного
		поэтизм(Стиль), профессионализм(Стиль),//48
		превосходная(Степень_сравнения), poloj(НетТипа)//50
		;

		Граммема(ТипГраммемы типГраммемы){
			this.типГраммемы = типГраммемы;
			типГраммемы.updateLimits(this);
		}

		private final ТипГраммемы типГраммемы;

		public String коротко() {
			if (ordinal() <= третье.ordinal() ){
				int смещение = ordinal()-база().ordinal();
				return названия_кратко[типГраммемы.ordinal()][смещение];
			} else {
				return toString();
			}
		}

		public Граммема база() {
			return тип().границы()[0];
		}
		public ТипГраммемы тип(){
			return типГраммемы;
		}

		public boolean тип(ТипГраммемы проверка){
			return this.тип() == проверка;
		}

		private String описание(int смещение){
			StringBuilder sb = new StringBuilder(64);
			String название = названия[тип().ordinal()][смещение];
			sb.append(название).append(' ').append(типГраммемы.имя());
			return sb.toString();
		}

		@Override
		public String toString() {
			switch (this){
				case повелительное:
					return описание(2);
				case сравнительная:
					return описание(1);
				case превосходная:
					return описание(2);
				default:
					if ( ordinal() <= страдательный.ordinal() ){
						int смещение = ordinal()-база().ordinal();
						return описание(смещение);
					} else {
						return this.name();
					}
			}
		}

	};

	public static interface РезультатСлова{
		public static interface Парадигма{
			boolean былоНайдено();
			String дайБазовуюФорму();
			ЧастьРечи дайЧастьРечи();
			Set<Граммема> дайГраммемы();
			List<ФормаСлова> формы();
			@Override
			String toString();
		}
		Set<Парадигма> дайПарадигмы();
	}

	public static РезультатСлова найдиСлово(Язык язык, String слово) throws ИсключениеЯваСопряженияМорфологии {
		final byte[] байты = байтыСлова(язык, слово);
		return lookupWordImpl(язык.ordinal(), байты);
	}

	public static ФормаСлова найдиФорму(Язык язык, String слово, Set<Граммема> граммемы) throws ИсключениеЯваСопряженияМорфологии {
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

	public static РезультатСлова формыСлова(Язык язык, String слово) throws ИсключениеЯваСопряженияМорфологии {
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
				return слово.getBytes("cp1251");
			} catch (UnsupportedEncodingException и) {
				throw new IllegalStateException(и);
			}
		default:
			throw new IllegalArgumentException("неизвестный язык: " + язык);
		}
	}

	private static native РезультатСлова lookupWordImpl(int идЯзыка, byte[] слово);
	private static native РезультатСлова lookupFormImpl(int идЯзыка, byte[] слово);
	private static native void initImpl(int битовыйНаборЯзыков, String env_path);
	private static native void closeImpl();

	// вызывается из низкоуровнего кода
	private static String convertFromCP1251(byte[] байты_cp1251){
		try {
			return new String(байты_cp1251, "cp1251");
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}

	private static long маскаГраммем(Collection<Граммема> граммемы){
		long result = 0;
		for (Граммема г: граммемы){
			result |= (1 << г.ordinal());
		}
		return result;
	}

	private static final Граммема[] значения_граммем = Граммема.values();
	private static final ЧастьРечи[] значения_чречи = ЧастьРечи.values();

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
			String раб_катал = System.getProperty("JNIMorphAPI-rml-dir");
			РАБОЧИЙ_КАТАЛОГ = (раб_катал==null) ? null : new File(раб_катал);

			String бин_катал = System.getProperty("JNIMorphAPI-jni-lib-dir");
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

}