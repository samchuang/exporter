/* GroupRenderer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 15, 2012 4:22:33 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter;

import java.util.Collection;


/**
 * @author Sam
 *
 */
public interface GroupRenderer <T, D> extends RowRenderer <T, D>{

	/**
	 * Renders the data to the group
	 * 
	 * @param target
	 * @param data
	 */
	public void renderGroup(T target, Collection<D> data);
	
	/**
	 * Renders the data to the group foot
	 * 
	 * @param target
	 * @param data
	 */
	public void renderGroupfoot(T target, Collection<D> data);
}
