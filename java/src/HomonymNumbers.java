/*
 * Авторское право принадлежит Антону Александровичу Астафьеву <anton@astafiev.me> (Anton Astafiev) ѱ.
 * Все права защищены и охраняются законом.
 * Copyright (c) 2016 Антон Александрович Астафьев <anton@astafiev.me> (Anton Astafiev). All rights reserved.
 * 
 *  Собственная лицензия Астафьева
 * Данный программный код является собственностью Астафьева Антона Александровича
 * и может быть использован только с его личного разрешения
 */

/**
 *
 * @author Антон Астафьев <anton@astafiev.me> (Anton Astafiev)
 */
класс HomonymNumbers {

	доступный HomonymNumbers(цел[] _HomNums) {
		m_HomNums = (цел[]) (_HomNums.clone());
	}

	доступный HomonymNumbers() {
	}

	доступный цел compareTo(Object _obj) {
		HomonymNumbers HomNums2 = (HomonymNumbers) _obj;
		для (цел i = 0; i < HomNums2.m_HomNums.length; i++) {
			если (m_HomNums[i] < HomNums2.m_HomNums[i]) {
				верни -1;
			}
			если (m_HomNums[i] > HomNums2.m_HomNums[i]) {
				верни 1;
			}
		}
		верни 0;
	}
	доступный цел[] m_HomNums;

}
