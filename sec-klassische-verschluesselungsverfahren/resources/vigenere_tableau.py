# Header
print("/ ",end="")
for k in range(ord('a'),ord('a')+26):
    print("**"+chr(k)+"** ",end="")
print()

# Tableau
for k in range (0,26):
    print("**"+chr(ord('A')+k)+"** ", end="")
    for c in range(0,26):
        i = (c+k) % 26
        print(chr(ord('A')+i)+ " ",end="")
    print()