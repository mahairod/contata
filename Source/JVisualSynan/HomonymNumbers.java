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
class HomonymNumbers {

	public HomonymNumbers(int[] _HomNums) {
		m_HomNums = (int[]) (_HomNums.clone());
	}

	public HomonymNumbers() {
	}

	public int compareTo(Object _obj) {
		HomonymNumbers HomNums2 = (HomonymNumbers) _obj;
		for (int i = 0; i < HomNums2.m_HomNums.length; i++) {
			if (m_HomNums[i] < HomNums2.m_HomNums[i]) {
				return -1;
			}
			if (m_HomNums[i] > HomNums2.m_HomNums[i]) {
				return 1;
			}
		}
		return 0;
	}
	public int[] m_HomNums;

}
