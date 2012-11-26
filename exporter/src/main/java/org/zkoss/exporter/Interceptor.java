/* Interceptor.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 19, 2012 11:42:43 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter;


/**
 * @author Sam
 *
 */
public interface Interceptor <T> {

	/**
	 * Called before rendering
	 * 
	 * @param target
	 */
	public void beforeRendering(T target);
	
	/**
	 * Called after rendering
	 * 
	 * @param target
	 */
	public void afterRendering(T target);
}
