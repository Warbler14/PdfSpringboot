package com.lotus.jewel.booker.pdf.converter;

import com.lotus.jewel.booker.pdf.converter.data.DataStore;

public interface ConvertStore <K, V>{
	
	public boolean convertLineAndStore(final String str);
	
	public DataStore<K, V> getDataStore();
	
}
