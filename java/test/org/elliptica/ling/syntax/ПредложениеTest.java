/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2015 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */
package org.elliptica.ling.syntax;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ПорядковыйСписок;
import java.util.Список;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public class ПредложениеTest {
	
	public ПредложениеTest() {
	}
	
	static Class[] classes = new Class[]{
			Предложение.class, Фрагмент.class, СвязьФрагментов.class, Слово.class, Диапазон.class
	};
	
	@BeforeClass
	public static void setUpClass() {
		try {
			jc = JAXBContext.newInstance(classes);
		} catch (JAXBException ex) {
			Logger.getLogger(ПредложениеTest.class.getName()).log(Level.SEVERE, null, ex);
			fail(ex.getMessage());
		}
	}
	
	private static JAXBContext jc;

	/**
	 * Test of getСписокСлов method, of class Предложение.
	 */
	@Test
	public void testGetСписокСлов() throws JAXBException {
		System.out.println("getСписокСлов");
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		Предложение предложение = new Предложение();

		предложение.списокСлов = new ПорядковыйСписок<>();
		предложение.списокСлов.добавь(new Слово());
		предложение.списокСлов.добавь(new Слово());
		(предложение.списокСлов.дай(0).омонимы = new ПорядковыйСписок<>()).добавь(new Омоним());
		
		Фрагмент фрагмент = new Фрагмент();
		
		предложение.списокФрагментов = new ПорядковыйСписок<>();
		предложение.списокФрагментов.добавь(фрагмент);
		предложение.списокФрагментов.добавь(фрагмент);
		
		фрагмент.варианты = new ПорядковыйСписок<>();
		фрагмент.варианты.add(new Вариант());
		фрагмент.союзы = new ПорядковыйСписок<>();
		фрагмент.союзы.add(new ПозицияСоюза());
		фрагмент.типы = new ПорядковыйСписок<>();
		фрагмент.типы.add(new ТипФрагмента());
		фрагмент.предложение = предложение;
		фрагмент.начало = 5;
		фрагмент.конец = 20;
		
		предложение.списокСвязейФрагментов = new ПорядковыйСписок<>();
		СвязьФрагментов связьФрагментов = new СвязьФрагментов();
		предложение.списокСвязейФрагментов.добавь(связьФрагментов);

		связьФрагментов.начало = 0;
		связьФрагментов.конец = 23;
		
		marshaller.marshal(предложение, System.out);

		System.out.println();
	}
	
}
