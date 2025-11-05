# Design a Food Rating System - <a href="https://leetcode.com/problems/design-a-food-rating-system/" target="_blank">Link</a>

## Question Description
Design a food rating system that can do the following:

- **Modify** the rating of a food item listed in the system.
- **Return** the highest rated food item for a type of cuisine in the system.

Implement the `FoodRatings` class:

- `FoodRatings(String[] foods, String[] cuisines, int[] ratings)` Initializes the system. The food items are paired with their cuisines and ratings.
- `void changeRating(String food, int newRating)` Changes the rating of the food item.
- `String highestRated(String cuisine)` Returns the name of the food item that has the highest rating for the given cuisine. If there is a tie, return the lexicographically smaller name.

---

## Constraints
- `1 <= foods.length <= 2 * 10^4`
- `foods[i].length == cuisines[i].length == ratings[i]`
- `1 <= foods[i].length, cuisines[i].length <= 10`
- `1 <= ratings[i] <= 10^8`
- `foods[i], cuisines[i]` consist of lowercase English letters and the `' '` space character
- `foods[i]`, cuisines[i]` do not contain leading or trailing spaces
- `All foods[i]` are distinct
- `1 <= newRating <= 10^8`
- `At most 2 * 10^4 calls will be made to changeRating and highestRated`

---

## Approach
Use three maps:
1. `foodCuisines`: Maps food → cuisine
2. `foodRating`: Maps food → rating
3. `cuisinesRatingFood`: Maps cuisine → TreeSet of pairs (-rating, foodName)

Store ratings as negative in the TreeSet, so the highest-rated food is always the first element (TreeSet sorts in ascending order, so -10 comes before -5).

**Why this approach works:**
- TreeSet maintains sorted order automatically
- Using negative ratings ensures highest rating comes first
- HashMap provides O(1) lookup for food-to-cuisine and food-to-rating mappings
- TreeSet handles updates efficiently with O(log n) operations

**Alternative approaches considered:**
- Could use priority queue, but TreeSet handles duplicates and ordering better
- Could use multiple data structures for each cuisine, but single TreeSet per cuisine is efficient

---

## Dry Run
Example Input:
```
foods = ["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"]
cuisines = ["korean", "japanese", "japanese", "greek", "japanese", "korean"]
ratings = [9, 12, 8, 15, 14, 7]
```

Step-by-step execution:
- Step 1: Initialize mappings:
  - foodCuisines: {"kimchi":"korean", "miso":"japanese", ...}
  - foodRating: {"kimchi":9, "miso":12, ...}
  - cuisinesRatingFood:
    - "korean": [(-9,"kimchi"), (-7,"bulgogi")]
    - "japanese": [(-12,"miso"), (-14,"ramen"), (-8,"sushi")]
    - "greek": [(-15,"moussaka")]
- Step 2: highestRated("japanese") returns "ramen" (highest rating 14)
- Step 3: changeRating("ramen", 16) updates TreeSet
- Step 4: highestRated("japanese") returns "ramen" (highest rating 16)

Final Answer = `"ramen"`

---

## Solution
```java
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
```

---

## Time and Space Complexity
- **Time Complexity:**
  - Initialization: O(N log N) where N is number of foods
  - changeRating: O(log F) where F is number of foods in the cuisine
  - highestRated: O(1)
- **Space Complexity:** O(N) where N is number of foods
