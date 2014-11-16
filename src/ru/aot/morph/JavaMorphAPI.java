package ru.aot.morph;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Множество;

import ru.aot.morph.JavaMorphAPI.РезультатСлова.Парадигма;

public class JavaMorphAPI{
	private static void загрузиБиблиотеку(String библ){
		System.load(new File(ТЕКУЩИЙ_КАТАЛОГ, библ + ".so").getAbsolutePath());
	}
	final private static File ТЕКУЩИЙ_КАТАЛОГ;
	static{
		{
			String катал=System.getProperty("JNIMorphAPI-jni-lib-dir");
			if(катал==null)
				катал="jni-lib";
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
			System.err.println("Error loading JNIMorphAPI library");
			throw new ExceptionInInitializerError(tr);
		}
	}
	public static enum Язык{Russian};
	
	public static class ИсключениеЯваСопряженияМорфологии extends RuntimeException{
		private static final long serialVersionUID = 6844719078250020184L;
		public ИсключениеЯваСопряженияМорфологии() {super();}
		public ИсключениеЯваСопряженияМорфологии(String message, Throwable cause) {super(message, cause);}
		public ИсключениеЯваСопряженияМорфологии(String message) {super(message);}
		public ИсключениеЯваСопряженияМорфологии(Throwable cause) {super(cause);}
	}

	public static void приготовьСловари(Множество<Язык> языкиДляОзначивания) throws ИсключениеЯваСопряженияМорфологии{
		int наборБитов = 0;
		for(Язык яз:языкиДляОзначивания)
			наборБитов|=(1<<яз.ordinal());
		реализОбозначив(наборБитов);
	}
	
	public static void закройСловари(){реализЗакрой();}

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
	private static final String[] роды = {
		"мужской", "женский", "средний", "обоюдный", "общий"
	};
	private static final String[] падежи = {
		"именительный", "родительный", "дательный", "винительный", "творительный", "предложный", "звательный"
	};
	private static final String[] наклонения = {
		"изъявительное", "условное", "повелительное", "желательное",
		"юссив", //косв. повеление 3-му лицу
		"гортатив", //поощрение (давай ...)
		"инъюктив", //собств.намерение
		"прохибитив", //отрицательно-повелительное
		"ирреальное", //ииреалис
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
	private static final String[] переходность = {
		"непереходное", "переходное"
	};
	private static final String[] валентность = переходность;
	private static final String[] видыГлагола = {
		"совершенный", "несовершенный"
	};
	private static final String[] степениСравнения = {
		"положительная", "сравнительная", "превосходная"
	};
	private static final String[] разряды = {
		"качественное", "относительное", "притяжательное", "порядковые"
	};

	public static enum Граммема{
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
		
		@Override
		public String toString() {
			switch (this){
				case plural: case singular:
					return числа[ordinal()-plural.ordinal()] + _ЧИСЛО;
				case padejImen: case padejRodit: case padejDatel: case padejVinit: case padejTvor: case padejPredl: case padejZvateln:
					return падежи[ordinal()-padejImen.ordinal()] + _ПАДЕЖ;
				case rodMuj: case rodJen: case rodSred: case rodMujJen:
					return роды[ordinal()-rodMuj.ordinal()] + _РОД;
				case present: case future: case past:
					return времена[ordinal()-present.ordinal()] + _ВРЕМЯ;
				case lico1: case lico2: case lico3:
					return лица[ordinal()-lico1.ordinal()] + _ЛИЦО;
				case povelitelnFormaGlagola:
					return наклонения[2];
				case odush:
					return одушевлённости[0];
				case neodush:
					return одушевлённости[1];
				case sravnitelnFormaPrilagat:
					return степениСравнения[1];
				case vidSov: case vidNesov:
					return видыГлагола[ordinal()-vidSov.ordinal()] + _ВИД;
				case neperehodnyiGlagol: case perehodnyiGlagol:
					return переходность[ordinal()-neperehodnyiGlagol.ordinal()];
				case deistvitZalog: case stradatZalog:
					return залоги[ordinal()-deistvitZalog.ordinal()] + _ЗАЛОГ;
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
			Множество<Граммема> дайГраммемы();
			String toString();
		}
		Множество<Парадигма> дайПарадигмы();
	}
	
	public static РезультатСлова найдиСлово(Язык язык, String слово)throws ИсключениеЯваСопряженияМорфологии{
		final byte[] байты;
		switch(язык){
		case Russian:
			try {
				байты = слово.getBytes("cp1251");
			} catch (UnsupportedEncodingException и) {
				throw new AssertionError(и);
			}
			break;
		default:
			throw new AssertionError("unknown language: " + язык);
		}
		return реализНайдиСлово(язык.ordinal(), байты);
	}

	private static native РезультатСлова реализНайдиСлово(int идЯзыка, byte[] слово);
	private static native void реализОбозначив(int битовыйНаборЯзыков);
	private static native void реализЗакрой();

	//used in natives
	private static String convertFromCP1251(byte[] байты_cp1251){
		try {
			return new String(байты_cp1251, "cp1251");
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}
	
	private static final Граммема[] значения_граммем = Граммема.values();
	private static final ЧастьРечи[] значения_чречи = ЧастьРечи.values();

	//used in natives
	private static void добавьГраммемуКМножеству(HashSet<Граммема> множествоГраммем, int идГраммемы){
		множествоГраммем.добавь(значения_граммем[идГраммемы]);
	}
	
	//used in natives
	private static void добавьПарадигмуКМножеству(HashSet<Парадигма> множествоПарадигм, final HashSet<Граммема> множествоГраммем, final String базоваяФорма, final boolean найдено, int идЧастиРечи){
		final ЧастьРечи чречи = значения_чречи[идЧастиРечи];
		множествоПарадигм.добавь(new Парадигма() {
			
			@Override
			public boolean былоНайдено() {
				return найдено;
			}
			
			@Override
			public ЧастьРечи дайЧастьРечи() {
				return чречи;
			}
			
			@Override
			public Множество<Граммема> дайГраммемы() {
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
			public Множество<Парадигма> дайПарадигмы() {
				return множествоПарадигм;
			}
		};
	}
}