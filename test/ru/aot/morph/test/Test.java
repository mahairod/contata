package ru.aot.morph.test;

import java.util.Collections;

import ru.aot.morph.JavaMorphAPI;
import ru.aot.morph.JavaMorphAPI.РезультатСлова;
import ru.aot.morph.JavaMorphAPI.РезультатСлова.Парадигма;

public class Test {
	public static void main(String[] args){
		try{
			JavaMorphAPI.приготовьСловари(Collections.singleton(JavaMorphAPI.Язык.Russian));
			РезультатСлова wr=JavaMorphAPI.найдиСлово(JavaMorphAPI.Язык.Russian, "свой");
			for (Парадигма парадигма: wr.дайПарадигмы()){
				System.out.println(парадигма.toString());
				
			}
			String bf=wr.дайПарадигмы().итератор().следующий().дайБазовуюФорму();
			if (bf.equals("ЗЕЛЕНЫЙ")){
				System.err.println("TEST PASSED");
				return;
			}
		}catch(Throwable tr){
			tr.printStackTrace();
		}
		System.err.println("TEST FAILED");
	}
}
