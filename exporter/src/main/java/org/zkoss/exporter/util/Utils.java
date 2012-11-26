/* Utils.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 12, 2012 11:01:30 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.util;

import static org.zkoss.exporter.util.Utils.getHeaders;
import static org.zkoss.exporter.util.Utils.invokeComponentGetter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.impl.HeadersElement;
import org.zkoss.zul.impl.MeshElement;

/**
 * @author Sam
 *
 */
public class Utils {
	private Utils() {};
	
	public static MeshElement getTarget(Component cmp) {
		MeshElement target = (MeshElement) invokeComponentGetter(cmp, "getListbox", "getGrid");
		if (target == null)
			throw new IllegalArgumentException(cmp + " cannot find tagret (MeshElement)");
		return target;
	}
	
	public static Component getFooters(Component target) {
		//get Grid's foot component or get Listbox's Listfoot component
		return (Component)invokeComponentGetter(target, "getFoot", "getListfoot");
	}
	
	public static Component getFooterColumnHeader(Component footer) {
		return (Component)invokeComponentGetter(footer, "getColumn", "getListheader");
	}
	
	public static String getStringValue(Component component) {
		return (String)invokeComponentGetter(component, "getLabel", "getText", "getValue");
	}
	
	public static String getAlign(Component cmp) {
		return (String) invokeComponentGetter(cmp, "getAlign");
	}
	
	public static int getHeaderSize(Component target) {
		Component headers = getHeaders(target);
		if (headers != null) {
			return headers.getChildren().size();
		}
		
		//cannot find head component, guess size by row child side
		List<Component> children = target.getChildren();
		int size = 0;
		for (Component cmp : children) {
			if (cmp instanceof Listitem || cmp instanceof Row) {
				size = Math.max(size, cmp.getChildren().size());
			}
		}
		return size;
	}
	
	public static Component getHeaders(Component target) {
		for(Component cmp : target.getChildren()) {
			if (cmp instanceof HeadersElement) {
				return cmp;
			}
		}
		return null;
	}
	
	public static Object invokeComponentGetter(Component target, String... methods) {
		Class<? extends Component> cls = target.getClass();
		for (String methodName : methods) {
			try {
				Method method = cls.getMethod(methodName, null);
				Object ret = method.invoke(target, null);
				if (ret != null)
					return ret;
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		return null;
	}
}
