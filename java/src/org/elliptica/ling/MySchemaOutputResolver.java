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

внеся java.io.File;
внеся java.io.IOException;
внеся javax.xml.bind.SchemaOutputResolver;
внеся javax.xml.transform.Result;
внеся javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Антон Александрович Астафьев {@literal <anton@astafiev.me>} (Anton Astafiev)
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
