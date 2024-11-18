
import time

def timeit(f):
     start = time.time()
     r = f()
     end = time.time()
     print("It took: " + str((end-start)))
     return r

gW = [ (1, 1), (3, 4), (5, 8), (2, 3) ] # (Gewicht, Wert)

def bestWertIterativ(n):
    best = [0] * (n + 1)  # best[i] = bester Wert für Traglast i
    for i in range(len(gW)):
        (gewt, wert) = gW[i]
        for j in range(gewt, n + 1):
            test = best[j - gewt] + wert
            if test > best[j]:
                best[j] = test
    
    return best[n]


n = 10;
while n < 1000000000:
    r = timeit(lambda: bestWertIterativ(n))
    print(r) 
    n *= 10


