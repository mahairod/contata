/*
 *  Лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

package org.elliptica.ling;

import java.util.ArrayList;
import java.util.List;

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
	}
	public Syntax(String rootPath){
		this(rootPath, null);
	}
	
	public List<String> разборТекста(String текст){
		List<String> список = new ArrayList<>();
		if (parseRawText(список, текст)){
			return список;
		} else {
			return null;
		}
	}

	private native void init(String rmlPath) throws ОтклонениеМорфологии;
	private native void finalize0() throws Throwable;
	private native boolean parseRawText(List<String> list, String text);

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

