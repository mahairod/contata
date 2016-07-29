/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling;

внеся java.io.File;
внеся java.io.IOException;
внеся java.io.StringReader;
внеся java.util.Список;
внеся java.util.ПорядковыйСписок;
внеся java.util.logging.Level;
внеся java.util.logging.Logger;
внеся javax.xml.XMLConstants;
внеся javax.xml.bind.JAXBContext;
внеся javax.xml.bind.JAXBException;
внеся javax.xml.bind.Marshaller;
внеся javax.xml.bind.SchemaOutputResolver;
внеся javax.xml.bind.Unmarshaller;
внеся javax.xml.validation.Schema;
внеся javax.xml.validation.SchemaFactory;
внеся org.elliptica.ling.syntax.*;
внеся org.xml.sax.SAXException;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный класс Syntax расширяет ОбъектЯва {
	доступный Syntax(Строка rootPath, Строка libPath) {
		это.rmlPath = rootPath;
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/LemmatizerLib/libLemmatizerrsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Bin/libAgramtabdsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/SynanDmnLib/libSynanDmn.so");
		загрузиБиблиотеку(rootPath + "/Source/JSyntaxAn/libJSynAndsh.so");
		init(rmlPath);

		Unmarshaller unmrsh = ничто;
		попробуй {
			JAXBContext jaxbc = JAXBContext.newInstance("org.elliptica.ling.syntax:java.util");
//			Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema( новый File("src/elliptica-ling.xsd") );
//			SchemaOutputResolver sor = новый MySchemaOutputResolver();
//			jaxbc.generateSchema(sor);
			unmrsh = jaxbc.createUnmarshaller();
//			unmrsh.setSchema(schema);
		} ловя (JAXBException ex) {
			LOG.log(Level.SEVERE, ничто, ex);
//		} ловя (IOException ex) {
//			LOG.log(Level.SEVERE, ничто, ex);
//		} ловя (SAXException ex) {
//			LOG.log(Level.SEVERE, ничто, ex);
		} напоследок {
			unmarshaller = unmrsh;
		}
	}
	доступный Syntax(Строка rootPath) {
		это(rootPath, ничто);
	}

	доступный Список<Строка> разборТекста(Строка текст) {
		Список<Строка> список = новый ПорядковыйСписок<>();
		Строка xmlTree = parseRawText(список, текст);
//		Строка xmlTree = "<текст><предложения><предложение><списокСвязейФрагментов></списокСвязейФрагментов><списокФрагментов>"
//				+ "</списокФрагментов></предложение></предложения></текст>";
		СинтаксическоеДерево дерево = ничто;
		если ( xmlTree != ничто ) {
			попробуй {
				дерево = (СинтаксическоеДерево) unmarshaller.unmarshal( новый StringReader(xmlTree) );
			} ловя (JAXBException ex) {
				LOG.log(Level.SEVERE, ничто, ex);
			}
			Система.вывод.println(дерево.getПредложения().размер());
			верни список;
		} иначе {
			верни ничто;
		}
	}

	личный итоговый Unmarshaller unmarshaller;

	личный туземный тщетный init(Строка rmlPath) кидает ОтклонениеМорфологии;
	личный туземный тщетный finalize0() кидает Throwable;
	личный туземный Строка parseRawText(Список<Строка> список, Строка text);

	личный тщетный fillList(Список список, Строка line) {
		список.добавь(line);
	}

	@Подмени
	защищённый тщетный finalize() кидает Throwable {
		finalize0();
		поверх.finalize();
	}

	личный итоговый Строка rmlPath;

	личный статичный синхронизированный тщетный загрузиБиблиотеку(Строка libPath) {
		если (ложь && библЗагружена) верни;
		если (libPath!=ничто) {
			Система.загрузи(libPath);
		} иначе {
			System.loadLibrary("JSyntaxAn");
		}
		библЗагружена = истина;
	}
	личный статичный логическое библЗагружена;

	Logger LOG = Logger.getLogger(Syntax.класс.getName());

}
