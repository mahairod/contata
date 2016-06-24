/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2015 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

пакет org.elliptica.ling;

внеси java.io.File;
внеси java.io.IOException;
внеси javax.xml.bind.SchemaOutputResolver;
внеси javax.xml.transform.Result;
внеси javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
доступный класс MySchemaOutputResolver расширяет SchemaOutputResolver {

	@Подмени
	доступный Result createOutput(Строка namespaceUri, Строка suggestedFileName) кидает IOException {
		File file = новый File(suggestedFileName);
		StreamResult result = новый StreamResult(file);
		result.setSystemId(file.toURI().toURL().toString());
		верни result;
	}

}
