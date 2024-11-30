def quickselect(arr :list[int], k):
    if len(arr) == 1:
        return arr[0]
    
    # 1. Pivot wählen
    pivot = arr[len(arr)-1] # jedes andere Element ist auch möglich!

    # 2. Partitionieren
    lows = [] # Elemente kleiner als Pivot
    highs = [] # Elemente größer als Pivot
    pivotsCount = 0 # Anzahl der Elemente gleich dem Pivot-Element
    for x in arr:
        if x < pivot:
            lows.append(x)
        elif x > pivot:
            highs.append(x)
        else:
            pivotsCount += 1
            
    print("arr:", arr, ", len(arr):", len(arr), ", k:", k, ", pivot:", pivot,", lows:", lows, ", highs:", highs, ", pivotsCount:", pivotsCount)

    # 3. Vergleiche k mit den Größen der Partitionen
    if k < len(lows):
        return quickselect(lows, k)
    elif k < len(lows) + pivotsCount:
        return pivot  # das k-te Element ist ein Pivot-Element
    else:
        return quickselect(highs, k - len(lows) -  pivotsCount)

def find_median(arr):
    n = len(arr)
    if n % 2 == 1:  # Ungerade Anzahl
        return quickselect(arr, n // 2)
    else:  # Gerade Anzahl
        left = quickselect(arr, n // 2 - 1)
        right = quickselect(arr, n // 2)
        return (left + right) / 2

# Beispiel-Array
nums = [3, 1, 9, 7, 5]
print("Median:", find_median(nums))
print("Median:", find_median([2,4]))
print("Median:", find_median([23,335,2,24,566,3,233,54,42,6,667,7,5,7,7]))

print("Quickselect - worst case:")   
print("k=0 =>", quickselect([-1, 3, 5, 9, 13, 15], 0))
print("Quickselect - typical case:")   
print("k=0 =>", quickselect([-1, 5, 3, 9, 13, 8], 0))
print("Quickselect - \"very\" good case:")   
print("k=0 =>", quickselect([-1, 5, 3, 9, 13, 2], 0))

print("Quickselect - best case:")   
print("k=0 =>", quickselect([1, 5, 3, 9, 13, -1], 0))