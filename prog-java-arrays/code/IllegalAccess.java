void main() {
    final int[] a = {1, 2, 3};
    println(a[0] = -1);
    println(Arrays.toString(a));
    // a = new int[]{}; // IllegalAccess.java: 
                        // error: cannot assign a value to final variable a
}
