/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package org.elliptica.ling;

import java.util.Collections;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public class MorphTest {

	public MorphTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		System.setProperty("jmorph.rml.dir", "/mnt/f16/mahairod/Develop/cpp/linguistan/");
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of приготовьСловари method, of class Morph.
	 */
	@Test
	public void testПриготовьСловари() {
		System.out.println("ПриготовьСловари");
		Set<Morph.Язык> языкиДляОзначивания = Collections.singleton(Morph.Язык.Русский);
		try{
			Morph.приготовьСловари(языкиДляОзначивания);
		} catch (ОтклонениеМорфологии ом){
			fail("Метод вызвал ошибку " + ом.getMessage());
		}
	}

	/**
	 * Test of закройСловари method, of class Morph.
	 */
	@Test
	public void testЗакройСловари() {
		System.out.println("ЗакройСловари");
		try{
			Morph.закройСловари();
		} catch (ОтклонениеМорфологии ом){
			fail("Метод вызвал ошибку " + ом.getMessage());
		}
	}

	/**
	 * Test of найдиСлово method, of class Morph.
	 */
	@Test
	public void testНайдиСлово() {
		System.out.println("НайдиСлово");
		Morph.Язык язык = null;
		String слово = "";
		РезультатСлова expResult = null;
		РезультатСлова result = Morph.найдиСлово(язык, слово);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of найдиФорму method, of class Morph.
	 */
	@Test
	public void testНайдиФорму() {
		System.out.println("НайдиФорму");
		Morph.Язык язык = null;
		String слово = "";
		Set<Граммема> граммемы = null;
		ФормаСлова expResult = null;
		ФормаСлова result = Morph.найдиФорму(язык, слово, граммемы);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of формыСлова method, of class Morph.
	 */
	@Test
	public void testФормыСлова() {
		System.out.println("ФормыСлова");
		Morph.Язык язык = null;
		String слово = "";
		РезультатСлова expResult = null;
		РезультатСлова result = Morph.формыСлова(язык, слово);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
