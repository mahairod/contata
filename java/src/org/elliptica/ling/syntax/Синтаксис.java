/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling.syntax;

внеся java.io.StringReader;
внеся java.util.logging.Level;
внеся java.util.logging.Logger;
внеся java.util.ПорядковыйСписок;
внеся java.util.Список;
внеся javax.xml.bind.JAXBContext;
внеся javax.xml.bind.JAXBException;
внеся javax.xml.bind.Unmarshaller;
внеся org.elliptica.ling.Syntax;
внеся эллиптика.ява.язык.Настройки;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс Синтаксис {

	доступный Синтаксис(СинтаксисаВоплощение воплощение) {
		синтВопл = воплощение;
		Unmarshaller unmrsh = ничто;
		попробуй {
			JAXBContext jaxbc = JAXBContext.newInstance("org.elliptica.ling.syntax:java.util", Thread.currentThread().getContextClassLoader() );
//			Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema( новый File("src/elliptica-ling.xsd") );
//			SchemaOutputResolver sor = новый MySchemaOutputResolver();
//			jaxbc.generateSchema(sor);
			unmrsh = jaxbc.createUnmarshaller();
//			unmrsh.setSchema(schema);
		} ловя (JAXBException ex) {
			ЛОГ.log(Level.SEVERE, ничто, ex);
			кинь новый RuntimeException("Не удалось инициализировать привязку XML", ex);
//		} ловя (IOException ex) {
//			LOG.log(Level.SEVERE, ничто, ex);
//		} ловя (SAXException ex) {
//			LOG.log(Level.SEVERE, ничто, ex);
		} напоследок {
			unmarshaller = unmrsh;
		}
	}

	доступный Синтаксис() {
		это(новый Syntax(Настройки.корень()));
	}

	доступный СинтаксическоеДерево разборТекста(Строка текст) {
		Список<Строка> список = новый ПорядковыйСписок<>();
		Строка xmlTree = синтВопл.parseRawText(список, текст);
//		Строка xmlTree = "<текст><предложения><предложение><списокСвязейФрагментов></списокСвязейФрагментов><списокФрагментов>"
//				+ "</списокФрагментов></предложение></предложения></текст>";
		если ( xmlTree != ничто ) {
			попробуй {
				СинтаксическоеДерево дерево = (СинтаксическоеДерево) unmarshaller.unmarshal( новый StringReader(xmlTree) );
				для (Предложение предложение: дерево.getПредложения()){
					для (Фрагмент фрагмент : предложение.getСписокФрагментов()) {
						для (Вариант вариант : фрагмент.getВарианты()) {
							вариант.постОбработка();
						}
					}
				}
				верни дерево;
			} ловя (JAXBException ex) {
				ЛОГ.log(Level.SEVERE, ничто, ex);
				кинь новый RuntimeException("Не удалось разобрать XML", ex);
			}
		} иначе {
			верни ничто;
		}
	}

	доступный Список<Строка> разборТекстаТест(Строка текст) {
		СинтаксическоеДерево дерево = разборТекста(текст);
		если ( дерево != ничто ) {
			Список<Строка> список = новый ПорядковыйСписок<>();
			для (Предложение предложение: дерево.getПредложения()){
				список.добавь(предложение.toString());
			}
			верни список;
		} иначе {
			верни ничто;
		}
	}

	личный итоговый СинтаксисаВоплощение синтВопл;
	личный итоговый Unmarshaller unmarshaller;

	статичный итоговый Logger ЛОГ = Logger.getLogger(Синтаксис.класс.getName());

}
