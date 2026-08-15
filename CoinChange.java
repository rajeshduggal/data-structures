/**
 * Coin Change
 * 
 * Given an int[] "typesOfCoins" and an int "amount" representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * Input: coins = [1,2,5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1
 * Input: coins = [2], amount = 3 Output: -1
 * % java CoinChange.java "1,2,5" 11
 */
void main(String[] args) {
    // parse first cli param into an int[].
    String[] coinParts = args[0].split(",");
    int[] coins = new int[coinParts.length];
    for (int i=0; i < coinParts.length; i++) {
        coins[i] = Integer.parseInt(coinParts[i].trim());
    }

    // parse second cli param into an int.
    int amount = Integer.parseInt(args[1].trim());

    IO.println(coinChange(coins, amount));
}

/**
 * 
 * @param coins is the array of diff coins available
 * @param amt the amount we're trying to reach
 * @return the minimum number of coins needed to reach the amount. 
 */
int coinChange(int[] coins, int amt) {
    int[] numOfCoinsToReachPos = new int[amt+1];
    
    // put amount + 1 in all the elements of numOfCointsToReachPos array
    // e.g. If we're targeting 11, then put 12 for all of the array elements.
    // It doesn't need to be +1, but just something larger than the amount.
    // so we can check for it later on. By using +1 we reduce the chance
    // of overflowing the INTEGER.MAX_VALUE.
    Arrays.fill(numOfCoinsToReachPos, amt + 1);
    // to reach zero amt, we use zero coins.
    numOfCoinsToReachPos[0] = 0;
    
    // while we haven't reached the amount yet,
    // iterate through the numOfCoinsToReachPos array at each position see
    // how each of the coins would land us at the current position by adding
    // the coin value from that value behind. e.g. if it's a 2 coin, compare
    // the current value to the value 2 places back, plus 1 (more coin, this 2 coin),
    // in the array. Using this technique we can keep populating the array elements
    // with the least number of coins to get to that position.
    for (int i = 0; i <= amt; i++) {
        for (int coin : coins) {
            if (coin <= i) {
                IO.println(Arrays.toString(numOfCoinsToReachPos));
                numOfCoinsToReachPos[i] = Math.min(numOfCoinsToReachPos[i], numOfCoinsToReachPos[i - coin] + 1);
            }
        } 
    }
    return numOfCoinsToReachPos[amt] > amt ? - 1 : numOfCoinsToReachPos[amt];
}