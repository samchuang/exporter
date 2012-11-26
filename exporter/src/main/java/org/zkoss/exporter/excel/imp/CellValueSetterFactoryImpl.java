/* CellValueSetterFactoryImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 19, 2012 3:13:48 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.excel.imp;


import java.util.Locale;

import org.zkoss.exporter.excel.CellValueSetter;
import org.zkoss.exporter.excel.CellValueSetterFactory;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;

/**
 * @author Sam
 *
 */
public class CellValueSetterFactoryImpl implements CellValueSetterFactory {

	@Override
	public <T> CellValueSetter<T> getCellValueSetter(Class<T> cls) {
		if (cls.isAssignableFrom(Component.class)) {
			return (CellValueSetter<T>)new CellValueSetterImpl(getLocale());
		}
		return null;
	}
	
	public Locale getLocale() {
		return Locales.getCurrent();
	}
}
