import math

def minCoins(n : int, coins : list[int]) -> int:
    print("Call: ",n)
    if n == 0:
        return 0

    min = math.inf
    for coin in coins:
        newN = n - coin
        if newN > 0:
            v = minCoins(n - coin, coins) 
            if v < min:
                min = v
        elif newN == 0:
            return 1
    return min + 1

# print(minCoins(3, [1, 2, 5]))
# print(minCoins(5, [1, 2, 5]))
# print(minCoins(8, [1, 2, 5]))
# print(minCoins(10, [1, 2, 5]))
print(minCoins(14, [1, 2, 5]))
