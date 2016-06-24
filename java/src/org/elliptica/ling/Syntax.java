/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling;

внеси java.io.File;
внеси java.io.IOException;
внеси java.io.StringReader;
внеси java.util.ArrayList;
внеси java.util.Список;
внеси java.util.logging.Level;
внеси java.util.logging.Logger;
внеси javax.xml.XMLConstants;
внеси javax.xml.bind.JAXBContext;
внеси javax.xml.bind.JAXBException;
внеси javax.xml.bind.Marshaller;
внеси javax.xml.bind.SchemaOutputResolver;
внеси javax.xml.bind.Unmarshaller;
внеси javax.xml.validation.Schema;
внеси javax.xml.validation.SchemaFactory;
внеси org.elliptica.ling.syntax.*;
внеси org.xml.sax.SAXException;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный класс Syntax расширяет ОбъектЯва {
	доступный Syntax(Строка rootPath, Строка libPath){
		это.rmlPath = rootPath;
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/LemmatizerLib/libLemmatizerrsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Bin/libAgramtabdsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/SynanDmnLib/libSynanDmn.so");
		загрузиБиблиотеку(rootPath + "/Source/JSyntaxAn/libJSynAndsh.so");
		init(rmlPath);

		Unmarshaller unmrsh = ничто;
		попробуй {
			JAXBContext jaxbc = JAXBContext.newInstance("org.elliptica.ling.syntax:ява.инструм");
//			Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema( новый File("src/elliptica-ling.xsd") );
//			SchemaOutputResolver sor = новый MySchemaOutputResolver();
//			jaxbc.generateSchema(sor);
			unmrsh = jaxbc.createUnmarshaller();
//			unmrsh.setSchema(schema);
		} ловя (JAXBException ex) {
			Logger.getLogger(Syntax.класс.getName()).log(Level.SEVERE, ничто, ex);
//		} ловя (IOException ex) {
//			Logger.getLogger(Syntax.класс.getName()).log(Level.SEVERE, ничто, ex);
//		} ловя (SAXException ex) {
//			Logger.getLogger(Syntax.класс.getName()).log(Level.SEVERE, ничто, ex);
		} напоследок {
			unmarshaller = unmrsh;
		}
	}
	доступный Syntax(Строка rootPath){
		это(rootPath, ничто);
	}
	
	доступный Список<Строка> разборТекста(Строка текст){
		Список<Строка> список = новый ArrayList<>();
		Строка xmlTree = parseRawText(список, текст);
//		Строка xmlTree = "<текст><предложения><предложение><списокСвязейФрагментов></списокСвязейФрагментов><списокФрагментов>"
//				+ "</списокФрагментов></предложение></предложения></текст>";
		СинтаксическоеДерево дерево = ничто;
		если ( xmlTree != ничто ){
			попробуй {
				дерево = (СинтаксическоеДерево) unmarshaller.unmarshal( новый StringReader(xmlTree) );
			} ловя (JAXBException ex) {
				Logger.getLogger(Syntax.класс.getName()).log(Level.SEVERE, ничто, ex);
			}
			System.out.println(дерево.getПредложения().рамер());
			верни список;
		} иначе {
			верни ничто;
		}
	}
	
	личный итоговый Unmarshaller unmarshaller;
	
	личный туземный тщетный init(Строка rmlPath) кидает ОтклонениеМорфологии;
	личный туземный тщетный finalize0() кидает Throwable;
	личный туземный Строка parseRawText(Список<Строка> list, Строка text);

	личный тщетный fillList(Список list, Строка line){
		list.add(line);
	}

	@Подмени
	защищённый тщетный finalize() кидает Throwable {
		finalize0();
		поверх.finalize();
	}

	личный итоговый Строка rmlPath;

	личный статичный синхронизированный тщетный загрузиБиблиотеку(Строка libPath){
		если (ложь && библЗагружена) верни;
		если (libPath!=ничто){
			System.load(libPath);
		} иначе {
			System.loadLibrary("JSyntaxAn");
		}
		библЗагружена = истина;
	}
	личный статичный логическое библЗагружена;

}

