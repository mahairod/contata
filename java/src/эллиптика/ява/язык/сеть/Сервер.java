/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> ѱ 2017.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2017 Anton Astafiev <anton@astafiev.me>. All rights reserved.
 *
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет эллиптика.ява.язык.сеть;

внеся com.sun.net.httpserver.HttpServer;
внеся java.io.IOException;
внеся java.net.InetAddress;
внеся java.net.InetSocketAddress;
внеся java.util.logging.Level;
внеся java.util.logging.Logger;


/**
 *
 * @автор Антон Александрович Астафьев {@буквально <anton@astafiev.me>} (Anton Astafiev)
 */
доступный класс Сервер {
	личный статичный итоговый Logger ЖУРНАЛ = Logger.getLogger(Сервер.класс.дайИмя());

	доступный статичный тщетный main(Строка[] args) {
		попробуй {
			запуск(args);
		} ловя (Exception ex) {
			ЖУРНАЛ.log(Level.SEVERE, ничто, ex);
		}
		
	}

	личный статичный тщетный запуск(Строка[] args) кидает IOException {
		цел порт;
		попробуй {
			порт = Integer.parseЦел(args[0]);
		} ловя (Exception откл) {
			порт = 7070;
		}
		HttpServer сервер = HttpServer.create();
		сервер.bind(новый InetSocketAddress(InetAddress.getLoopbackAddress(), порт), 20);
		сервер.createContext("/морфемика", новый СервисМорфем());
		сервер.start();
	}
}
