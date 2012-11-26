/* RowRenderer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 15, 2012 4:07:28 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter;


/**
 * 
 * @author Sam
 *
 */
public interface RowRenderer <T, D> {

	/**
	 * Renders the data to the specified row.
	 * 
	 * @param target
	 * @param data
	 * @param oddRow
	 */
	public void render(T target, D data, boolean oddRow);
}
