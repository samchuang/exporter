/* FoodComparator.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 19, 2012 4:16:22 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package demo;

import java.io.Serializable;
import java.util.Comparator;

import org.zkoss.zul.GroupComparator;

/**
 * @author Sam
 *
 */
public class FoodComparator implements Comparator<Food>, GroupComparator<Food>, Serializable {
	
    public int compare(Food o1, Food o2) {
        return o1.getCategory().compareTo(o2.getCategory().toString());
    }
 
    public int compareGroup(Food o1, Food o2) {
        if(o1.getCategory().equals(o2.getCategory()))
            return 0;
        else
            return 1;
    }
}
