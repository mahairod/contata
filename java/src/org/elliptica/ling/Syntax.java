/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package org.elliptica.ling;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.elliptica.ling.syntax.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
public class Syntax extends ОбъектЯва {
	public Syntax(String rootPath, String libPath){
		this.rmlPath = rootPath;
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/LemmatizerLib/libLemmatizerrsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Bin/libAgramtabdsh.so");
//		загрузиБиблиотеку("/mnt/f16/mahairod/Develop/cpp/linguistan/Source/SynanDmnLib/libSynanDmn.so");
		загрузиБиблиотеку(rootPath + "/Source/JSyntaxAn/libJSynAndsh.so");
		init(rmlPath);

		Unmarshaller unmrsh = null;
		try {
			JAXBContext jaxbc = JAXBContext.newInstance("org.elliptica.ling.syntax:ява.инструм");
//			Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema( new File("src/elliptica-ling.xsd") );
//			SchemaOutputResolver sor = new MySchemaOutputResolver();
//			jaxbc.generateSchema(sor);
			unmrsh = jaxbc.createUnmarshaller();
//			unmrsh.setSchema(schema);
		} catch (JAXBException ex) {
			Logger.getLogger(Syntax.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (IOException ex) {
//			Logger.getLogger(Syntax.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (SAXException ex) {
//			Logger.getLogger(Syntax.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			unmarshaller = unmrsh;
		}
	}
	public Syntax(String rootPath){
		this(rootPath, null);
	}
	
	public List<String> разборТекста(String текст){
		List<String> список = new ArrayList<>();
		String xmlTree = parseRawText(список, текст);
//		String xmlTree = "<текст><предложения><предложение><списокСвязейФрагментов></списокСвязейФрагментов><списокФрагментов>"
//				+ "</списокФрагментов></предложение></предложения></текст>";
		СинтаксическоеДерево дерево = null;
		if ( xmlTree != null ){
			try {
				дерево = (СинтаксическоеДерево) unmarshaller.unmarshal( new StringReader(xmlTree) );
			} catch (JAXBException ex) {
				Logger.getLogger(Syntax.class.getName()).log(Level.SEVERE, null, ex);
			}
			System.out.println(дерево.getПредложения().рамер());
			return список;
		} else {
			return null;
		}
	}
	
	private final Unmarshaller unmarshaller;
	
	private native void init(String rmlPath) throws ОтклонениеМорфологии;
	private native void finalize0() throws Throwable;
	private native String parseRawText(List<String> list, String text);

	private void fillList(List list, String line){
		list.add(line);
	}

	@Override
	protected void finalize() throws Throwable {
		finalize0();
		super.finalize();
	}

	private final String rmlPath;

	private static synchronized void загрузиБиблиотеку(String libPath){
		if (false && библЗагружена) return;
		if (libPath!=null){
			System.load(libPath);
		} else {
			System.loadLibrary("JSyntaxAn");
		}
		библЗагружена = true;
	}
	private static boolean библЗагружена;

}

