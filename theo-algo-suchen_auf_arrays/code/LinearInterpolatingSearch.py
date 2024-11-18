def linear_interpolating_search(A, needle):
    upper = len(A) -1
    lower = 0 
    vL = A[lower]
    if vL == needle:
        return lower
    vU = A[upper - 1]
    if vU == needle:
        return upper - 1

    while upper > lower:
        pos = round(lower * (needle - vU) / (vL - vU) +
                    upper * (needle - vL) / (vU - vL))
        pos = max(lower + 1, min(upper - 1, pos))
        print(f"lower: {lower}, upper: {upper} => pos: {pos}")  
        value = A[pos]

        if value == needle:
            return pos
        elif value < needle:
            lower = pos
            vL = A[lower]
        else:
            upper = pos
            vU = A[upper - 1]

    return None

# Beispielaufruf:
A = [1, 3, 5, 7, 9, 11, 13, 15]
print(f"Gefundener Index für 7: {linear_interpolating_search(A, 7)}")
print(f"Gefundener Index für 13: {linear_interpolating_search(A, 13)}")
print(f"Gefundener Index für 14: {linear_interpolating_search(A, 14)}")