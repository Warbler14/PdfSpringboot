package com.lotus.jewel.booker.pdf.converter;

import com.lotus.jewel.booker.pdf.converter.data.DataStore;

public class PrintConvertStore implements ConvertStore<Object, Object> {

	@Override
	public boolean convertLineAndStore(String str) {
		System.out.println(str);
		return false;
	}

	@Override
	public DataStore<Object, Object> getDataStore() {
		return null;
	}

}
