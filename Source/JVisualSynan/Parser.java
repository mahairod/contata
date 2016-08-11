import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

        public static void main(String args[]){
			InputStreamReader r = null;
			try {
				String file = args[0];
				File f = new File(file);
				r = new FileReader(f);
				java.io.BufferedReader br = new BufferedReader(r);
				br.lines().forEach(new Consumer<String>() {
					@Override
					public void accept(String t) {
						processLine(t);
					}
				});
			} catch (FileNotFoundException ex) {
				LOG.log(Level.SEVERE, null, ex);
			} finally {
				try {
					r.close();
				} catch (IOException ex) {
					LOG.log(Level.SEVERE, null, ex);
				}
			}
        }
		
		private static void processLine(String t){
			String[] parts = t.split(":");
			int line = Integer.parseInt(parts[1]);
			
			AtomicInteger curLine = new AtomicInteger(1);
			
			final String src = parts[2];
			String dst = src;
			for (int i=1; i<constants.length; i+=2 ){
				dst = dst.replaceAll("\\b" + constants[i] + "\\b", constants[i-1]);
			}
			final String dst_ = dst;
			
			
			Path rp = Paths.get(parts[0]);
			Path wp = Paths.get(parts[0]+".ява");
			try (BufferedWriter bw = Files.newBufferedWriter(wp)){
				Files.lines(Paths.get(parts[0])).map(
						str -> {
							int cur = curLine.getAndIncrement();
							if (cur == line){
								return str.replace(src, dst_);
							}
							return str;
						}
				)
				.forEach(str -> {
					try {
						bw.write(str);
						bw.newLine();
					} catch (IOException ex) {
						LOG.log(Level.SEVERE, null, ex);
					}
					
				});
			} catch (IOException ex) {
				LOG.log(Level.SEVERE, null, ex);
			}

			wp.toFile().renameTo(rp.toFile());

		}

		private static final String[] constants = {
			"abstract", "абстрактный",
			"assert", "проверь",
			"boolean", "логическое",
			"break", "прерви",
			"byte", "байт",
			"case", "случай",
			"catch", "ловя",
			"char", "символ",
			"class", "класс",
			"const", "неизм",
			"continue", "возобнови",
			"default", "запасной",
			"do", "делай",
			"double", "ширдроб",
			"else", "иначе",
			"enum", "переч",
			"extends", "расширяет",
			"final", "итоговый",
			"finally", "напоследок",
			"float", "дроб",
			"for", "для",
			"goto", "идик",
			"if", "если",
			"implements", "воплощает",
			"import", "внеси",
			"instanceof", "экземпляр",
			"int", "цел",
			"interface", "сопряжение",
			"long", "ширцел",
			"native", "туземный",
			"new", "новый",
			"package", "пакет",
			"private", "личный",
			"protected", "защищённый",
			"public", "доступный",
			"return", "верни",
			"short", "узцел",
			"static", "статичный",
			"strictfp", "строгарифм",
			"super", "поверх",
			"switch", "выбери",
			"synchronized", "синхронизированный",
			"this", "это",
			"throw", "кинь",
			"throws", "кидает",
			"transient", "мимолётный",
			"try", "попробуй",
			"void", "тщетный",
			"volatile", "изменчивый",
			"while", "пока",
			"true", "истина",
			"false", "ложь",
			"null", "ничто",
		};

		private static final Logger LOG = Logger.getLogger(Parser.class.getName());
}