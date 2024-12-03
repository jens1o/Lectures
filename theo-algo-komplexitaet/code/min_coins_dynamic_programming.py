import math

def minCoinsDynamic(n : int, coins : list[int], results : list[int]) -> int:
    print("Call: ",n)
    if n == 0:
        return 0

    min = math.inf
    for coin in coins:
        newN = n - coin
        if newN > 0:
            if results[newN] != -1:
                v = results[newN]
            else: 
                v = minCoinsDynamic(n - coin, coins, results) 
            if v < min:
                min = v + 1
        elif newN == 0:
            min = 1
            break
    results[n] = min 
    return min 

def minCoins(n : int, coins : list[int]) -> int:
    results = [-1] * (n+1)
    return minCoinsDynamic(n, coins, results)

print(minCoins(1, [1, 2, 5])) # 2
print(minCoins(3, [1, 2, 5])) # 2
print(minCoins(5, [1, 2, 5])) # 1
print(minCoins(8, [1, 2, 5])) # 3
print(minCoins(10, [1, 2, 5])) # 2
print(minCoins(14, [1, 2, 5])) # 4
print(minCoins(16, [1, 2, 5])) # 4
