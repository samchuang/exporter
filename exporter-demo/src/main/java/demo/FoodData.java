/* FoodData.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 19, 2012 4:15:45 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sam
 *
 */
public class FoodData {
	
    private static List<Food> foods = new ArrayList<Food>();
    static {
        foods.add(new Food("Vegetables", "Asparagus", "Vitamin K", 115, 43, "1 cup - 92 grams"));
        foods.add(new Food("Vegetables", "Beets", "Folate", 33, 74, "1 cup - 170 grams"));
        foods.add(new Food("Vegetables", "Bell peppers", "Vitamin C", 291, 24, "1 cup - 92 grams"));
        foods.add(new Food("Vegetables", "Cauliflower", "Vitamin C", 92, 28, "1 cup - 124 grams"));
        foods.add(new Food("Vegetables", "Eggplant", "Dietary Fiber", 10, 27, "1 cup - 99 grams"));
        foods.add(new Food("Vegetables", "Onions", "Chromium", 21, 60, "1 cup - 160 grams"));
        foods.add(new Food("Vegetables", "Potatoes", "Vitamin C", 26, 132, "1 cup - 122 grams"));
        foods.add(new Food("Vegetables", "Spinach", "Vitamin K", 1110, 41, "1 cup - 180 grams"));
        foods.add(new Food("Vegetables", "Tomatoes", "Vitamin C", 57, 37, "1 cup - 180 grams"));
        foods.add(new Food("Seafood", "Salmon", "Tryptophan", 103, 261, "4 oz - 113.4 grams"));
        foods.add(new Food("Seafood", "Shrimp", "Tryptophan", 103, 112, "4 oz - 113.4 grams"));
        foods.add(new Food("Seafood", "Scallops", "Tryptophan", 81, 151, "4 oz - 113.4 grams"));
        foods.add(new Food("Seafood", "Cod", "Tryptophan", 90, 119, "4 oz - 113.4 grams"));
        foods.add(new Food("Fruits", "Apples", "Manganese", 33, 61, "1 cup - 160 grams"));
        foods.add(new Food("Fruits", "Cantaloupe", "Vitamin C", 112, 56, "1 cup - 160 grams"));
        foods.add(new Food("Fruits", "Grapes", "Manganese", 33, 61, "1 cup - 92 grams"));
        foods.add(new Food("Fruits", "Pineapple", "Manganese", 128, 75, "1 cup - 155 grams"));
        foods.add(new Food("Fruits", "Strawberries", "Vitamin C", 24, 48, "1 cup - 150 grams"));
        foods.add(new Food("Fruits", "Watermelon", "Vitamin C", 24, 48, "1 cup - 152 grams"));
        foods.add(new Food("Poultry & Lean Meats", "Beef, lean organic", "Tryptophan", 112, 240, "4 oz - 113.4 grams"));
        foods.add(new Food("Poultry & Lean Meats", "Lamb", "Tryptophan", 109, 229, "4 oz - 113.4 grams"));
        foods.add(new Food("Poultry & Lean Meats", "Chicken", "Tryptophan", 121, 223, "4 oz - 113.4 grams"));
        foods.add(new Food("Poultry & Lean Meats", "Venison ", "Protein", 69, 179, "4 oz - 113.4 grams"));
        foods.add(new Food("Grains", "Corn ", "Vatamin B1", 24, 177, "1 cup - 164 grams"));
        foods.add(new Food("Grains", "Oats ", "Manganese", 69, 147, "1 cup - 234 grams"));
        foods.add(new Food("Grains", "Barley ", "Dietary Fiber", 54, 270, "1 cup - 200 grams"));
    }
 
    public static List<Food> getAllFoods() {
        return new ArrayList<Food>(foods);
    }
    public static Object[] getAllFoodsArray() {
        return foods.toArray();
    }
 
    // This Method only used in "Header and footer" Demo
    public static List<Food> getFoodsByCategory(String category) {
        List<Food> somefoods = new ArrayList<Food>();
        for (Iterator<Food> i = foods.iterator(); i.hasNext();) {
            Food tmp = i.next();
            if (tmp.getCategory().equalsIgnoreCase(category)){
                somefoods.add(tmp);
            }
        }
        return somefoods;
    }
}
