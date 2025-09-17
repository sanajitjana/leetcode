/*
Problem: Design a Food Rating System
LeetCode Link: https://leetcode.com/problems/design-a-food-rating-system/

Approach:
We need a data structure to:
- Quickly find the highest-rated food in a given cuisine.
- Efficiently update the rating of a food.

Use three maps:
1. foodCuisines: Maps food → cuisine.
2. foodRating: Maps food → rating.
3. cuisinesRatingFood: Maps cuisine → TreeSet of pairs (-rating, foodName).

Store ratings as negative in the TreeSet, so the highest-rated food is always the first element.

On changeRating(food, newRating):
- Remove the old pair (-oldRating, food).
- Insert the new pair (-newRating, food).

On highestRated(cuisine):
- Return the first element from the TreeSet.

Time Complexity:
- Initialization: O(N log N), where N is the number of foods.
- changeRating: O(log F), where F is the number of foods in the cuisine.
- highestRated: O(1).

Space Complexity:
O(N), where N is the number of foods.
*/

import javafx.util.Pair;
import java.util.*;

public class FoodRatings {

    private Map<String, TreeSet<Pair<Integer, String>>> cuisinesRatingFood = new HashMap<>();
    private Map<String, String> foodCuisines = new HashMap<>();
    private Map<String, Integer> foodRating = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; ++i) {
            foodCuisines.put(foods[i], cuisines[i]);
            foodRating.put(foods[i], ratings[i]);

            cuisinesRatingFood.computeIfAbsent(cuisines[i], e -> new TreeSet<>(
                    (a, b) -> a.getKey().equals(b.getKey()) 
                        ? a.getValue().compareTo(b.getValue()) 
                        : Integer.compare(a.getKey(), b.getKey())))
                    .add(new Pair<>(-ratings[i], foods[i]));
        }
    }

    public void changeRating(String food, int newRating) {
        int oldRating = foodRating.get(food);

        TreeSet<Pair<Integer, String>> cuisineSet = cuisinesRatingFood.get(foodCuisines.get(food));
        cuisineSet.remove(new Pair<>(-oldRating, food));

        foodRating.put(food, newRating);
        cuisineSet.add(new Pair<>(-newRating, food));
    }

    public String highestRated(String cuisine) {
        return cuisinesRatingFood.get(cuisine).first().getValue();
    }
}
