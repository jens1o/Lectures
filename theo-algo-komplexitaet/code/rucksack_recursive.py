
import time

def timeit(f):
     start = time.time()
     r = f()
     end = time.time()
     print("It took: " + str((end-start)))
     return r

gW = [ (1, 1), (3, 4), (5, 8), (2, 3) ] # [(Gewicht, Wert)...]

def bestWertRekursiv(n):
    best = 0
    for i in range(len(gW)):
        (gewt,wert) = gW[i]
        if n >= gewt: 
            test = wert + bestWertRekursiv(n - gewt)
            if test > best:
                best = test
    return best

n = 1;
while n < 101:
    r = timeit(lambda: bestWertRekursiv(n))
    print(r) 
    n *= 10