class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        Arrays.sort(potions);
        int m = potions.length;
        int n = spells.length;

        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            int currentSpell = spells[i];

            int low = 0;
            int high = m - 1;
            int firstSuccessfulPotionIndex = m;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if ((long) potions[mid] * currentSpell >= success) {
                    firstSuccessfulPotionIndex = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            results[i] = m - firstSuccessfulPotionIndex;
        }

        return results;
    }
}