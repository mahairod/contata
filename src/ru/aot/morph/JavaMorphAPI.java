package ru.aot.morph;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import ru.aot.morph.JavaMorphAPI.РезультатСлова.Парадигма;

public class JavaMorphAPI{
	private static void загрузиБиблиотеку(String библ){
		System.load(new File(ТЕКУЩИЙ_КАТАЛОГ, библ + ".so").getAbsolutePath());
	}
	final private static File ТЕКУЩИЙ_КАТАЛОГ;
	static{
		{
			String катал = System.getProperty("JNIMorphAPI-jni-lib-dir");
			if(катал==null)
				катал = "jni-lib";
			ТЕКУЩИЙ_КАТАЛОГ = new File(катал);
		}
		
		try{
//			loadLibrary("libStructDictrsh");
//			loadLibrary("libAgramtabrsh");
//			loadLibrary("libLemmatizerrsh");
//			loadLibrary("libPCRErsh");
//			loadLibrary("libGraphanrsh");
//			loadLibrary("libMorphWizardrsh");
			загрузиБиблиотеку("JNIMorphAPI");
		}catch(Throwable tr){
			System.err.println("Ошибка загрузки библиотеки JNIMorphAPI");
			throw new ExceptionInInitializerError(tr);
		}
	}
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
		for(Язык яз:языкиДляОзначивания)
			наборБитов|=(1<<яз.ordinal());
		initImpl(наборБитов);
	}
	
	public static void закройСловари(){closeImpl();}

	public static enum ЧастьРечи{
		noun,  // 0
		adjective, // 1
		verb, // 2
		mestoim_noun, // 3
		mestoim_adjective, // 4
		mestoim_predikativ, // 5
		chislitelnoeKolichestv, // 6
		chislitelnoePoryadkovoe, // 7
		narechie, // 8
		predikativ, //9
		predlog, // 10
		posl, // 11
		soyuz, // 12
		mejdometie, // 13
		vvodnoe_slovo,// 14
		fraz, // 15
		chastica, // 16
		kratkoePrilagat,  // 17
		prichastie, //18
		deeprichastie, //19
		kratkoePrichastie, // 20
		verbInfinitive  //21
	};
	
	private static final String ПАДЕЖ = "падеж";
	private static final String _ПАДЕЖ = " падеж";

	private static final String РОД = "род";
	private static final String _РОД = " род";

	private static final String ВРЕМЯ = "время";
	private static final String _ВРЕМЯ = " время";

	private static final String ЛИЦО = "лицо";
	private static final String _ЛИЦО = " лицо";

	private static final String НАКЛОНЕНИЕ = "наклонение";
	private static final String _НАКЛОНЕНИЕ = " наклонение";

	private static final String ЧИСЛО = "число";
	private static final String _ЧИСЛО = " число";

	private static final String ЗАЛОГ = "залог";
	private static final String _ЗАЛОГ = " залог";

	private static final String ВИД = "вид";
	private static final String _ВИД = " вид";

	private static final String СТЕПЕНЬ_СРАВНЕНИЯ = "степень";
	private static final String _СТЕПЕНЬ_СРАВНЕНИЯ = " степень";

	private static final String РАЗРЯД = "разряд";
	private static final String _РАЗРЯД = " разряд";

	private static final String ОДУШЕВЛЁННОСТЬ = "одушевлённость";
	private static final String ПЕРЕХОДНОСТЬ = "переходность";

	private static final String[] числа = {
		"множественное", "единственное", "двойственное", "тройственное", "паукальное"
	};
	private static final String[] числа_кратко = {
		"мн", "ед", "дв", "тр", "паук"
	};
	private static final String[] роды = {
		"мужской", "женский", "средний", "обоюдный", "общий"
	};
	private static final String[] роды_кратко = {
		"муж", "жен", "сред", "обоюд", "общ"
	};
	private static final String[] падежи = {
		"именительный", "родительный", "дательный", "винительный", "творительный", "предложный", "звательный"
	};
	private static final String[] падежи_кратко = {
		"им", "род", "дат", "вин", "тв", "пр", "зв"
	};
	private static final String[] наклонения = {
		"изъявительное", "условное", "повелительное", "желательное",
		"юссив", //косв. повеление 3-му лицу
		"гортатив", //поощрение (давай ...)
		"инъюктив", //собств.намерение
		"прохибитив", //отрицательно-повелительное
		"ирреальное", //ирреалис
		"парафразис" //чужая речь
	};
	private static final String[] одушевлённости = {
		"одушевлённое", "неодушевлённое",
	};
	private static final String[] лица = {
		"первое", "второе", "третье", "четвёртое"
	};
	private static final String[] времена = {
		"настоящее", "будущее", "прошлое"
	};
	private static final String[] залоги = {
		"действительный", "страдательный", "возвратный", "взаимный",
		"средний", "антистрадательный", "эргативный", "совместный", "безличный",
		"побудительный"/*каузатив*/, "декаузативный", "аппликативный"
	};
	private static final String[] переходности = {
		"непереходное", "переходное"
	};
	private static final String[] валентности = переходности;
	private static final String[] видыГлагола = {
		"совершенный", "несовершенный"
	};
	private static final String[] степениСравнения = {
		"положительная", "сравнительная", "превосходная"
	};
	private static final String[] разряды = {
		"качественное", "относительное", "притяжательное", "порядковое"
	};
	
	private static final String[][] названия = {
		{"нет названия"},
		числа,			падежи,			роды,		времена, лица, одушевлённости, видыГлагола, переходности, залоги, наклонения, степениСравнения, разряды
	};

	private static final String[][] названия_кратко = {
		{"нет названия"},
		числа_кратко,	падежи_кратко,	роды_кратко, времена, лица, одушевлённости, видыГлагола, переходности, залоги, наклонения, степениСравнения, разряды
	};

	public static enum Граммема {
		// 0..1
		plural, singular,
		// 2..8
		padejImen, padejRodit, padejDatel, padejVinit, padejTvor, padejPredl, padejZvateln,
		// род 9-12
		rodMuj, rodJen, rodSred, rodMujJen,
		// 13..15
		present, future, past,
		// 16..18
		lico1, lico2, lico3,
		// 19
		povelitelnFormaGlagola,
		// 20..21
		odush, neodush,
		// 22
		sravnitelnFormaPrilagat,
		// 23..24
		vidSov, vidNesov,
		// 25..26
		neperehodnyiGlagol, perehodnyiGlagol,
		// 27..28
		deistvitZalog, stradatZalog,
		// 29-31
		neizmenyaemoe, abbrev, otchestvo,
		// 32-33
		lokativnost, organizaciya,
		// 34-35
		kachestv, neImeetMnojestvChisla,
		// 36-37 (наречия)
		voprositNarech, ukazat,
		// 38..39
		firstName, lastName,
		// 40
		bezlichnGlagol,
		// 41,42
		jargon, opechatka,
		// 43,44,45
		razgovorn, posessive, archaic,
		// для второго родительного и второго предложного
		padej2,
		poet, professionalizm,
		prev, poloj;

		public String коротко() {
			int смещение = ordinal()-база().ordinal();
			switch (this){
				case padejImen: case padejRodit: case padejDatel: case padejVinit: case padejTvor: case padejPredl: case padejZvateln:
					return падежи_кратко[смещение];
				case plural: case singular:
					return числа_кратко[смещение];
				case rodMuj: case rodJen: case rodSred: case rodMujJen:
					return роды_кратко[смещение];
				default:
					return toString();
			}
		}

		public Граммема база() {
			switch (this){
				case plural: case singular:
					return plural;
				case padejImen: case padejRodit: case padejDatel: case padejVinit: case padejTvor: case padejPredl: case padejZvateln:
					return padejImen;
				case rodMuj: case rodJen: case rodSred: case rodMujJen:
					return rodMuj;
				case present: case future: case past:
					return present;
				case lico1: case lico2: case lico3:
					return lico1;
				case odush: case neodush:
					return odush;
				case vidSov: case vidNesov:
					return vidSov;
				case neperehodnyiGlagol: case perehodnyiGlagol:
					return neperehodnyiGlagol;
				case deistvitZalog: case stradatZalog:
					return deistvitZalog;
				default:
					return this;
			}
		}
		public ТипГраммемы тип(){
			Граммема база = this.база();
			for (int i=0; i<базы.length; i++){
				if (база==базы[i]){
					return ТипГраммемы.values()[i];
				}
			}
			return ТипГраммемы.НетТипа;
		}
		
		public boolean тип(ТипГраммемы проверка){
			int позицияТипа = проверка.ordinal();
			return this.база() == базы[позицияТипа];
		}
		
		public static enum ТипГраммемы {
			НетТипа,		Число,	Падеж,		Род,		Время,	Лицо,	Одушевлённость,	Вид,		Переходность,		Залог,			Наклонение,				Степень_сравнения,		Разряд, 
		}
		private static final Граммема[] базы = {
			null,		plural,	padejImen,	rodMuj,	present,	lico1,	odush,			vidSov,	neperehodnyiGlagol, deistvitZalog,	povelitelnFormaGlagola,	sravnitelnFormaPrilagat,	kachestv
		};

		private String описание(int смещение, ТипГраммемы типГраммемы){
			return null;
		}

		@Override
		public String toString() {
			int смещение = ordinal()-база().ordinal();
			ТипГраммемы типГраммемы = тип();
			switch (this){
				case plural: case singular:
					return числа[смещение] + _ЧИСЛО;
				case padejImen: case padejRodit: case padejDatel: case padejVinit: case padejTvor: case padejPredl: case padejZvateln:
					return падежи[смещение] + _ПАДЕЖ;
				case rodMuj: case rodJen: case rodSred: case rodMujJen:
					return роды[смещение] + _РОД;
				case present: case future: case past:
					return времена[смещение] + _ВРЕМЯ;
				case lico1: case lico2: case lico3:
					return лица[смещение] + _ЛИЦО;
				case odush: case neodush:
					return одушевлённости[смещение];
				case vidSov: case vidNesov:
					return видыГлагола[смещение] + _ВИД;
				case neperehodnyiGlagol: case perehodnyiGlagol:
					return переходности[смещение];
				case deistvitZalog: case stradatZalog:
					return залоги[смещение] + _ЗАЛОГ;
				case povelitelnFormaGlagola:
					return наклонения[2];
				case sravnitelnFormaPrilagat:
					return степениСравнения[1];
				case neizmenyaemoe:
					return "неизменяемое";
				case abbrev:
					return "аббревиатура";
				case otchestvo:
					return "отчество";
				case lokativnost:
					return "локативность";
				case organizaciya:
					return "организация";
				case kachestv:
					return разряды[0];
					
/*
					neImeetMnojestvChisla,
					// 36-37 (наречия)
					voprositNarech, ukazat,
*/
				case firstName:
					return "имя";
				case lastName:
					return "фамилия";
/*					
					// 40
					bezlichnGlagol,
					// 41,42
					jargon, opechatka,
					// 43,44,45
					razgovorn, posessive, archaic,
					// для второго родительного и второго предложного
					padej2,
					poet, professionalizm,
					prev, poloj;
*/
					default:
						return this.name();
				
			}
		}

	};
	
	public static interface РезультатСлова{
		public static interface Парадигма{
			boolean былоНайдено();
			String дайБазовуюФорму();
			ЧастьРечи дайЧастьРечи();
			Set<Граммема> дайГраммемы();
			@Override
			String toString();
		}
		Set<Парадигма> дайПарадигмы();
	}
	
	public static РезультатСлова найдиСлово(Язык язык, String слово) throws ИсключениеЯваСопряженияМорфологии {
		final byte[] байты = байтыСлова(язык, слово);
		return lookupWordImpl(язык.ordinal(), байты);
	}

	public static Парадигма найдиФорму(Язык язык, String слово, Set<Граммема> граммемы) throws ИсключениеЯваСопряженияМорфологии {
		final byte[] байты = байтыСлова(язык, слово);
		long маска = маскаГраммем( граммемы );
		final РезультатСлова рс = lookupWordImpl(язык.ordinal(), байты);
		for (Парадигма парадигма: рс.дайПарадигмы() ){
			long образец = маскаГраммем( парадигма.дайГраммемы() );
			if ( (маска & образец) != 0){
				return парадигма;
			}
		}
		return null;
	}

	private static byte[] байтыСлова(Язык язык, String слово) {
		switch(язык){
		case Русский:
			try {
				return слово.getBytes("cp1251");
			} catch (UnsupportedEncodingException и) {
				throw new AssertionError(и);
			}
		default:
			throw new AssertionError("неизвестный язык: " + язык);
		}
	}

	private static native РезультатСлова lookupWordImpl(int идЯзыка, byte[] слово);
	private static native РезультатСлова lookupFormImpl(int идЯзыка, byte[] слово);
	private static native void initImpl(int битовыйНаборЯзыков);
	private static native void closeImpl();

	//used in natives
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

	//used in natives
	private static void добавьГраммемуКМножеству(HashSet<Граммема> множествоГраммем, int идГраммемы){
		множествоГраммем.add(значения_граммем[идГраммемы]);
	}
	
	//used in natives
	private static void добавьПарадигмуКМножеству(HashSet<Парадигма> множествоПарадигм, final HashSet<Граммема> множествоГраммем, final String базоваяФорма, final boolean найдено, int идЧастиРечи){
		final ЧастьРечи чречи = значения_чречи[идЧастиРечи];
		множествоПарадигм.add(new Парадигма() {
			
			@Override
			public boolean былоНайдено() {
				return найдено;
			}
			
			@Override
			public ЧастьРечи дайЧастьРечи() {
				return чречи;
			}
			
			@Override
			public Set<Граммема> дайГраммемы() {
				return множествоГраммем;
			}
			
			@Override
			public String дайБазовуюФорму() {
				return базоваяФорма;
			}

			@Override
			public String toString() {
				String результат = дайЧастьРечи().toString() + " " + дайБазовуюФорму() + "\n";
				for (Граммема граммема: дайГраммемы()){
					результат += "\t" + граммема.toString() + "\n";
				}
				return результат;
			}
			
		});
	}

	//used in natives
	private static РезультатСлова создайРезультатСлова(final HashSet<Парадигма> множествоПарадигм){
		return new РезультатСлова() {
			
			@Override
			public Set<Парадигма> дайПарадигмы() {
				return множествоПарадигм;
			}
		};
	}

	public static void main(String[] аргументы){
		try{
			JavaMorphAPI.приготовьСловари(Collections.singleton(JavaMorphAPI.Язык.Русский));
			РезультатСлова рс = JavaMorphAPI.найдиСлово(JavaMorphAPI.Язык.Русский, "свой");
			for (Парадигма парадигма: рс.дайПарадигмы()){
				System.out.println(парадигма.toString());
			}
			String бф = рс.дайПарадигмы().iterator().next().дайБазовуюФорму();
			if (бф.equals("ЗЕЛЕНЫЙ")){
				System.err.println("TEST PASSED");
				return;
			}
		}catch(Throwable ош){
			ош.printStackTrace();
		}
		System.err.println("TEST FAILED");
	}

}