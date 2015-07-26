package ru.aot.morph.test;

import java.util.Collections;

import ru.aot.morph.JavaMorphAPI;
import ru.aot.morph.JavaMorphAPI.РезультатСлова;
import ru.aot.morph.JavaMorphAPI.РезультатСлова.Парадигма;

public class Test {
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
