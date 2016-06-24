/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package org.elliptica.ling;

import java.util.List;
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
public class SyntaxTest {
	
	public SyntaxTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
		instance = new Syntax(
				"/mnt/f16/mahairod/Develop/cpp/linguistan",
				"/mnt/f16/mahairod/Develop/cpp/linguistan/Source/JSyntaxAn/dist/Debug/GNU-Linux-x86/libJSyntaxAn.so"
		);
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
	 * Test of разборТекста method, of class Syntax.
	 */
	@Test
	public void testРазборТекста() {
		System.out.println("РазборТекста");
		String текст = "В ходе поисков были обследованы более 70 тысяч квадратных километров, но никаких следов или обломков найти не удалось. С приходом зимы операцию пришлось прервать. Летом поиски возобновились. Работы осложнялись горным рельефом местности.";
		List<String> result = instance.разборТекста(текст);
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	/**
	 * Test of finalize method, of class Syntax.
	 * @throws java.lang.Throwable
	 */
	@Test
	public void testFinalize() throws Throwable {
		System.out.println("finalize");
		instance.finalize();
	}
	
	private static Syntax instance = null;
}
