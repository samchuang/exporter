/* CellValueSetter.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 14, 2012 10:43:07 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.excel;

import org.zkoss.poi.ss.usermodel.Cell;

/**
 * @author Sam
 *
 */
public interface CellValueSetter <T> {

	public void setCellValue(T param, Cell cell);
}
