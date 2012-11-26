/* GroupsModelArrayAdapter.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Oct 26, 2012 10:21:30 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.exporter.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.zkoss.zul.GroupsModelArray;

/**
 * @author Sam
 *
 */
public class GroupsModelArrayAdapter<D, H, F, E> extends GroupsModelArray<D, H, F, E>{

	public GroupsModelArrayAdapter(D[] data, Comparator<D> cmpr) {
		super(data, cmpr);
	}
	
	private Collection< Collection<D> > _d;
	
	public Collection< Collection<D> > getData() {
		if (_d == null) {
			_d = new ArrayList<Collection<D>>(_data.length);
			for (D[] d : _data) {
				_d.add(Arrays.asList(d));
			}
		}
		return _d;
	}
}
