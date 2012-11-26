/* CellValueSetterFactory.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 19, 2012 3:09:54 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.excel;

/**
 * @author Sam
 *
 */
public interface CellValueSetterFactory {

	public <T> CellValueSetter<T> getCellValueSetter(Class<T> cls);
}
